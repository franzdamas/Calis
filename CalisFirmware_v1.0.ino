/*====================================================
 Calis Firmware v1.0
=====================================================*/

#include <Arduino.h>

/*
  Calis BLE connection configuration:
  1 Teacher + 5 Students = 6 simultaneous BLE connections.

  IMPORTANT:
  - RID 0 is reserved for the teacher.
  - RIDs 1-5 are reserved for five students.
  - NimBLE-Arduino 2.5.0 uses CONFIG_BT_NIMBLE_MAX_CONNECTIONS.
  - Set CONFIG_BT_NIMBLE_MAX_CONNECTIONS to 6 in NimBLE-Arduino/src/nimconfig.h.
  - This sketch checks the active NimBLE connection limit at compile time.
*/
#include <NimBLEDevice.h>
#include <Preferences.h>
#include <Wire.h>
#include <U8g2lib.h>

#ifndef NIMBLE_MAX_CONNECTIONS
#error "NimBLE-Arduino maximum-connection macro is unavailable. Check NimBLE-Arduino 2.5.0 installation."
#endif

#if NIMBLE_MAX_CONNECTIONS != 6
#error "Calis requires NimBLE_MAX_CONNECTIONS=6. Set CONFIG_BT_NIMBLE_MAX_CONNECTIONS to 6 in NimBLE-Arduino/src/nimconfig.h."
#endif

/*=========================
  Hardware / BLE IDs
=========================*/
#define DEVICE_NAME "CALIS"

static const char *SERVICE_UUID = "CA113726-49D2-4D7F-A5A0-000000000001";
static const char *RX_UUID      = "CA113726-49D2-4D7F-A5A0-000000000002";
static const char *TX_UUID      = "CA113726-49D2-4D7F-A5A0-000000000003";

/*=========================
  Display
=========================*/
U8G2_SSD1306_128X64_NONAME_F_HW_I2C oled(U8G2_R0, U8X8_PIN_NONE);

/*=========================
  Limits / Timers
=========================*/
static const uint8_t MAX_BLE_CONNECTIONS = 6;  // 1 Teacher + 5 Students
static const uint8_t MAX_STUDENTS = MAX_BLE_CONNECTIONS - 1;
static const uint8_t MAX_PACKET_FIELDS = 16;
static const unsigned long AUTH_TIMEOUT_MS = 5000UL;
static const uint8_t LED_PIN = 2;
static const unsigned long LED_SESSION_BLINK_MS = 500UL;
static const unsigned long LED_AUTH_PULSE_MS = 150UL;
static const unsigned long ADVERTISING_REFRESH_DELAY_MS = 150UL;

// BLE advertising recovery:
// A failed/aborted connection attempt can occasionally leave advertising off.
// The watchdog retries only when at least one connection slot is free.
static const unsigned long ADVERTISING_WATCHDOG_INTERVAL_MS = 1000UL;
static const unsigned long ADVERTISING_RESTART_COOLDOWN_MS = 750UL;

/*=========================
  Firmware State
=========================*/
enum DeviceState {
  STATE_FACTORY,
  STATE_REGISTERING,
  STATE_AUTHENTICATE,
  STATE_SESSION_INACTIVE,
  STATE_SESSION_OPEN
};

enum PacketCommand {
  CMD_UNKNOWN,
  CMD_REG,
  CMD_AUTH,
  CMD_START_SESSION,
  CMD_END_SESSION,
  CMD_RESET,
  CMD_UNREG,
  CMD_ST_REG,
  CMD_CHK_IN,
  CMD_TK_SBM,
  CMD_REQ_UPD,
  CMD_REG_APPROVED,
  CMD_REG_REJECTED,
  CMD_ATD_ACCEPTED,
  CMD_ATD_REJECTED,
  CMD_TK,
  CMD_MARKED,
  CMD_DONE
};

DeviceState deviceState = STATE_FACTORY;

/*=========================
  NVS / Runtime
=========================*/
Preferences prefs;

String clsID;
String tchID;
String teacherName;
String classroomName;

bool teacherConnected = false;
bool teacherAuthenticated = false;
bool sessionActive = false;
bool bulkStudentDisconnect = false;

uint16_t teacherHandle = 0xFFFF;
unsigned long authStart = 0;
unsigned long ledLastToggle = 0;
unsigned long authLedPulseUntil = 0;
bool ledBlinkState = false;

uint16_t studentHandle[MAX_STUDENTS];
bool ridUsed[MAX_STUDENTS];

/*=========================
  BLE Objects
=========================*/
NimBLEServer *server = nullptr;
NimBLEService *service = nullptr;
NimBLECharacteristic *rxChar = nullptr;
NimBLECharacteristic *txChar = nullptr;
NimBLEAdvertising *advertising = nullptr;
static volatile bool advertisingRestartPending = false;
static unsigned long advertisingRestartDue = 0UL;
static unsigned long advertisingWatchdogLastCheck = 0UL;
static unsigned long advertisingRestartNotBefore = 0UL;

/*=========================
  Packet Buffer
=========================*/
String packetFields[MAX_PACKET_FIELDS];
uint8_t packetFieldCount = 0;

/*=========================
  Forward Declarations
=========================*/
void setDeviceState(DeviceState s);
void refreshOLED();
void refreshAdvertising();
void serviceAdvertisingWatchdog();
void updateStatusLED();
void triggerAuthSuccessBlink();
bool storageIsRegistered();
bool storageLoad();
bool storageSave();
void storageClear();

void clearAllRID();
void clearAllStudentConnections();
uint8_t getRIDFromHandle(uint16_t handle);
uint16_t getHandleFromRID(uint8_t rid);
bool isTeacherHandle(uint16_t handle);
int8_t allocateRID(uint16_t handle);
void releaseRID(uint8_t rid);
uint8_t getActiveConnectionCount();
void disconnectTeacher();
void disconnectStudent(uint8_t rid);
void disconnectAllStudents();
void forceEndSession();
void startSession();
void endSession();

bool parsePacket(const String &packet);
PacketCommand getCommand();
bool isTeacherPacketCommand(PacketCommand cmd);
bool isTeacherPacketAllowed(PacketCommand cmd);
bool isStudentPacketAllowed(PacketCommand cmd);
String buildPacketFromFields(uint8_t firstField, uint8_t lastField);
String buildCurrentPacket();
String appendRID(const String &packet, uint8_t rid);
String removeRID();

bool sendTeacherPacket(const String &packet);
bool notifyTeacher(const String &packet);
bool notifyStudent(uint8_t rid, const String &packet);
void sendRegistered();
void sendUnregistered();
void sendAuthOK();
void sendAuthFailed();
void sendResetOK();

void packetReceive(uint16_t connHandle, const String &packet);
void packetDispatch(uint16_t connHandle, PacketCommand cmd);
void handleTeacherPacket(uint16_t connHandle, PacketCommand cmd);
void handleStudentPacket(uint16_t connHandle, PacketCommand cmd);

void initBLE();
void processBLE();
void bleApplyAdvertisement();
void bleRestartAdvertising();
void printBLEConnectionSummary(const char *event, uint16_t handle, int reason = 0);

/*=========================
  Storage
=========================*/
bool storageIsRegistered() {
  return !clsID.isEmpty() && !tchID.isEmpty() && !teacherName.isEmpty() && !classroomName.isEmpty();
}

bool storageLoad() {
  prefs.begin("calis", true);

  clsID = prefs.getString("cid", "");
  tchID = prefs.getString("tid", "");
  teacherName = prefs.getString("tname", "");
  classroomName = prefs.getString("cname", "");

  prefs.end();
  return storageIsRegistered();
}

bool storageSave() {
  if (clsID.isEmpty() || tchID.isEmpty() || teacherName.isEmpty() || classroomName.isEmpty()) {
    return false;
  }

  prefs.begin("calis", false);
  prefs.putString("cid", clsID);
  prefs.putString("tid", tchID);
  prefs.putString("tname", teacherName);
  prefs.putString("cname", classroomName);
  prefs.end();
  return true;
}

void storageClear() {
  prefs.begin("calis", false);
  prefs.clear();
  prefs.end();

  clsID = "";
  tchID = "";
  teacherName = "";
  classroomName = "";
}

/*=========================
  OLED helpers
=========================*/
static void drawCenteredLine(int16_t y, const String &text) {
  if (text.isEmpty()) return;
  int16_t w = oled.getStrWidth(text.c_str());
  int16_t x = (128 - w) / 2;
  if (x < 0) x = 0;
  oled.drawStr(x, y, text.c_str());
}

static void drawCommonHeader() {
  oled.setFont(u8g2_font_6x12_tf);
  oled.setFontPosTop();
  drawCenteredLine(0, "CALIS");
}

static void drawTeacherNameArea(int16_t y, const String &text) {
  if (text.isEmpty()) return;

  oled.setFont(u8g2_font_6x12_tf);
  oled.setFontPosTop();

  if (oled.getStrWidth(text.c_str()) <= 128) {
    drawCenteredLine(y, text);
    return;
  }

  int split = text.length() / 2;
  int breakPos = -1;

  for (int i = split; i >= 0; --i) {
    if (text.charAt(i) == ' ') {
      breakPos = i;
      break;
    }
  }

  if (breakPos < 0) {
    for (int i = split; i < (int)text.length(); ++i) {
      if (text.charAt(i) == ' ') {
        breakPos = i;
        break;
      }
    }
  }

  String line1;
  String line2;

  if (breakPos >= 0) {
    line1 = text.substring(0, breakPos);
    line2 = text.substring(breakPos + 1);
  } else {
    int cut = text.length() / 2;
    if (cut < 1) cut = 1;
    if (cut >= (int)text.length()) cut = text.length() - 1;
    line1 = text.substring(0, cut);
    line2 = text.substring(cut);
  }

  line1.trim();
  line2.trim();

  oled.setFont(u8g2_font_6x10_tf);
  oled.setFontPosTop();

  // Fallback if either line is still too wide.
  if (oled.getStrWidth(line1.c_str()) > 128 || oled.getStrWidth(line2.c_str()) > 128) {
    int cut = text.length() / 2;
    if (cut < 1) cut = 1;
    if (cut >= (int)text.length()) cut = text.length() - 1;
    line1 = text.substring(0, cut);
    line2 = text.substring(cut);
    line1.trim();
    line2.trim();
  }

  drawCenteredLine(44, line1);
  drawCenteredLine(54, line2);
}

void refreshOLED() {
  oled.clearBuffer();

  switch (deviceState) {
    case STATE_FACTORY:
      drawCommonHeader();
      drawCenteredLine(16, "Factory Mode");
      drawCenteredLine(32, "Setup Calis");
      break;

    case STATE_REGISTERING:
      drawCommonHeader();
      drawCenteredLine(16, "Registering");
      drawCenteredLine(32, "Setup Calis");
      break;

    case STATE_AUTHENTICATE:
      drawCommonHeader();
      drawCenteredLine(16, "Authenticate");
      drawCenteredLine(32, classroomName);
      drawTeacherNameArea(48, teacherName);
      break;

    case STATE_SESSION_INACTIVE:
      drawCommonHeader();
      drawCenteredLine(16, "Session Inactive");
      drawCenteredLine(32, classroomName);
      drawTeacherNameArea(48, teacherName);
      break;

    case STATE_SESSION_OPEN:
      drawCommonHeader();
      drawCenteredLine(16, "Session Active");
      drawCenteredLine(32, classroomName);
      drawTeacherNameArea(48, teacherName);
      break;
  }

  // Small BLE advertising indicator in the top-left corner.
  // Filled = NimBLE is currently advertising; empty = not advertising.
  oled.setDrawColor(advertising && advertising->isAdvertising() ? 1 : 0);
  oled.drawBox(0, 0, 3, 3);
  oled.setDrawColor(1);

  oled.sendBuffer();
}

/*=========================
  Manufacturer data / state
=========================*/
static uint8_t currentManufacturer[2] = {0xFF, 0xFF};  // Status bytes only (2-byte Calis state)

static const char* stateAdvertisementName(DeviceState s) {
  switch (s) {
    case STATE_FACTORY:
    case STATE_REGISTERING:
      return "Setup Calis";
    case STATE_AUTHENTICATE:
    case STATE_SESSION_INACTIVE:
    case STATE_SESSION_OPEN:
      return classroomName.isEmpty() ? DEVICE_NAME : classroomName.c_str();
  }
  return DEVICE_NAME;
}

static void updateManufacturerBytes() {
  switch (deviceState) {
    case STATE_FACTORY:
      currentManufacturer[0] = 0xFF; currentManufacturer[1] = 0xFF; break;
    case STATE_REGISTERING:
      currentManufacturer[0] = 0xFF; currentManufacturer[1] = 0xF1; break;
    case STATE_AUTHENTICATE:
      currentManufacturer[0] = 0x00; currentManufacturer[1] = 0xFF; break;
    case STATE_SESSION_INACTIVE:
      currentManufacturer[0] = 0x01; currentManufacturer[1] = 0xFF; break;
    case STATE_SESSION_OPEN:
      currentManufacturer[0] = 0x11; currentManufacturer[1] = 0x00; break;
  }
}

void refreshAdvertising() {
  if (!advertising) return;

  updateManufacturerBytes();

  const uint8_t activeConnections = getActiveConnectionCount();

  // When all 6 BLE connection slots are occupied, the CD is intentionally
  // unavailable to additional centrals. Keep advertising OFF until a slot
  // is released.
  if (activeConnections >= MAX_BLE_CONNECTIONS) {
    advertisingRestartPending = false;

    if (advertising->isAdvertising()) {
      Serial.printf(
        "[BLE] %u/%u connections occupied; stopping advertising\n",
        activeConnections,
        MAX_BLE_CONNECTIONS
      );
      advertising->stop();
    }
    return;
  }

  NimBLEAdvertisementData adv;
  adv.setName(stateAdvertisementName(deviceState));
  adv.setManufacturerData(
    std::string((char*)currentManufacturer, sizeof(currentManufacturer))
  );
  adv.setCompleteServices(NimBLEUUID(SERVICE_UUID));

  // Update the advertisement without forcing a stop/start when it is already
  // active. This keeps normal state/manufacturer-data changes lightweight.
  advertising->setAdvertisementData(adv);

  if (advertising->isAdvertising()) {
    advertising->refreshAdvertisingData();
    return;
  }

  // Do not repeatedly hammer NimBLE while it is still processing a recent
  // connection/disconnection event or a previous restart attempt.
  if ((long)(millis() - advertisingRestartNotBefore) < 0) {
    return;
  }

  Serial.printf(
    "[BLE] Advertising OFF with %u/%u connections; attempting restart\n",
    activeConnections,
    MAX_BLE_CONNECTIONS
  );

  advertising->start();

  // Give NimBLE time to settle before another retry.
  advertisingRestartNotBefore = millis() + ADVERTISING_RESTART_COOLDOWN_MS;

  if (advertising->isAdvertising()) {
    Serial.println("[BLE] Advertising restart successful");
  } else {
    Serial.println("[BLE] Advertising restart not confirmed; watchdog will retry");
  }
}

void requestAdvertisingRefresh() {
  const unsigned long due = millis() + ADVERTISING_REFRESH_DELAY_MS;

  // Coalesce multiple refresh requests into one controlled refresh.
  if (!advertisingRestartPending || (long)(due - advertisingRestartDue) < 0) {
    advertisingRestartDue = due;
  }
  advertisingRestartPending = true;
}

void servicePendingAdvertising() {
  if (!advertising || !advertisingRestartPending) return;

  // Give NimBLE a short grace period after connect/disconnect before
  // changing or restarting advertising.
  if ((long)(millis() - advertisingRestartDue) < 0) return;

  advertisingRestartPending = false;
  refreshAdvertising();
}

void serviceAdvertisingWatchdog() {
  if (!advertising) return;

  const unsigned long now = millis();
  if ((now - advertisingWatchdogLastCheck) < ADVERTISING_WATCHDOG_INTERVAL_MS) {
    return;
  }
  advertisingWatchdogLastCheck = now;

  const uint8_t activeConnections = getActiveConnectionCount();

  // Full capacity is an intentional non-advertising condition.
  if (activeConnections >= MAX_BLE_CONNECTIONS) {
    if (advertising->isAdvertising()) {
      Serial.printf(
        "[BLE] Watchdog: %u/%u connections occupied; stopping advertising\n",
        activeConnections,
        MAX_BLE_CONNECTIONS
      );
      advertising->stop();
    }
    return;
  }

  // Already advertising: nothing to recover.
  if (advertising->isAdvertising()) return;

  // Let a normal deferred refresh/recovery finish first.
  if (advertisingRestartPending) return;

  // Respect the cooldown after recent BLE activity or a restart attempt.
  if ((long)(now - advertisingRestartNotBefore) < 0) return;

  Serial.printf(
    "[BLE] Watchdog: advertising unexpectedly OFF at %u/%u connections; restarting\n",
    activeConnections,
    MAX_BLE_CONNECTIONS
  );

  refreshAdvertising();
}

static bool lastOLEDAdvertisingState = false;
static bool oledAdvertisingStateInitialized = false;

void updateOLEDAdvertisingIndicator() {
  if (!advertising) return;

  bool advertisingNow = advertising->isAdvertising();

  if (!oledAdvertisingStateInitialized || advertisingNow != lastOLEDAdvertisingState) {
    lastOLEDAdvertisingState = advertisingNow;
    oledAdvertisingStateInitialized = true;
    refreshOLED();
  }
}

void setDeviceState(DeviceState s) {
  bool stateChanged = (deviceState != s);

  if (stateChanged) {
    deviceState = s;

    if (deviceState == STATE_FACTORY) {
      ledBlinkState = false;
      ledLastToggle = 0UL;
    } else if (deviceState == STATE_SESSION_OPEN) {
      ledBlinkState = false;
      ledLastToggle = millis();
    } else {
      ledBlinkState = false;
      ledLastToggle = 0UL;
    }

    refreshOLED();
  }

  // Always restore the current advertisement, even when the logical state
  // does not change. A central connection can stop BLE advertising.
  requestAdvertisingRefresh();
  updateStatusLED();
}

void triggerAuthSuccessBlink() {
  authLedPulseUntil = millis() + LED_AUTH_PULSE_MS;
  digitalWrite(LED_PIN, HIGH);
}

void updateStatusLED() {
  unsigned long now = millis();

  if (authLedPulseUntil != 0UL) {
    if (now < authLedPulseUntil) {
      digitalWrite(LED_PIN, HIGH);
      return;
    }
    authLedPulseUntil = 0UL;
  }

  switch (deviceState) {
    case STATE_FACTORY:
      if (ledLastToggle == 0UL || (now - ledLastToggle) >= LED_SESSION_BLINK_MS) {
        ledLastToggle = now;
        ledBlinkState = !ledBlinkState;
      }
      digitalWrite(LED_PIN, ledBlinkState ? HIGH : LOW);
      break;

    case STATE_SESSION_OPEN:
      ledBlinkState = false;
      ledLastToggle = 0UL;
      digitalWrite(LED_PIN, HIGH);
      break;

    case STATE_AUTHENTICATE:
    case STATE_REGISTERING:
    case STATE_SESSION_INACTIVE:
    default:
      ledBlinkState = false;
      ledLastToggle = 0UL;
      digitalWrite(LED_PIN, LOW);
      break;
  }
}

/*=========================
  RID Manager
=========================*/
void clearAllRID() {
  for (uint8_t i = 0; i < MAX_STUDENTS; i++) {
    ridUsed[i] = false;
    studentHandle[i] = 0xFFFF;
  }
}

void clearAllStudentConnections() {
  for (uint8_t i = 0; i < MAX_STUDENTS; i++) {
    if (!ridUsed[i]) continue;

    uint16_t h = studentHandle[i];
    if (h != 0xFFFF && server) {
      // Keep the RID reserved until NimBLE confirms the disconnect through
      // onDisconnect(). This prevents immediate RID reuse while the old BLE
      // link is still being torn down.
      bool requested = server->disconnect(h);
      if (!requested) {
        releaseRID(i + 1);
      }
    } else {
      releaseRID(i + 1);
    }
  }
}

uint8_t getRIDFromHandle(uint16_t handle) {
  if (teacherConnected && handle == teacherHandle) return 0;
  for (uint8_t i = 0; i < MAX_STUDENTS; i++) {
    if (ridUsed[i] && studentHandle[i] == handle) return i + 1;
  }
  return 0xFF;
}

uint16_t getHandleFromRID(uint8_t rid) {
  if (rid < 1 || rid > MAX_STUDENTS) return 0xFFFF;
  return studentHandle[rid - 1];
}

bool isTeacherHandle(uint16_t handle) {
  return teacherConnected && handle == teacherHandle;
}

bool isStudentHandle(uint16_t handle) {
  for (uint8_t i = 0; i < MAX_STUDENTS; i++) {
    if (ridUsed[i] && studentHandle[i] == handle) return true;
  }
  return false;
}

int8_t allocateRID(uint16_t handle) {
  for (uint8_t i = 0; i < MAX_STUDENTS; i++) {
    if (!ridUsed[i]) {
      ridUsed[i] = true;
      studentHandle[i] = handle;
      return i + 1;
    }
  }
  return -1;
}

void releaseRID(uint8_t rid) {
  if (rid < 1 || rid > MAX_STUDENTS) return;
  ridUsed[rid - 1] = false;
  studentHandle[rid - 1] = 0xFFFF;
}

uint8_t getActiveConnectionCount() {
  uint8_t count = teacherConnected ? 1 : 0;

  for (uint8_t i = 0; i < MAX_STUDENTS; i++) {
    if (ridUsed[i]) count++;
  }

  return count;
}

void disconnectStudent(uint8_t rid) {
  uint16_t h = getHandleFromRID(rid);
  if (h != 0xFFFF && server) {
    // Keep the RID reserved until onDisconnect(). Releasing it here can let a
    // fast reconnect reuse the RID before the previous BLE link is fully gone.
    bool requested = server->disconnect(h);
    if (!requested) {
      releaseRID(rid);
    }
    return;
  }

  releaseRID(rid);
}

void disconnectTeacher() {
  if (teacherConnected && teacherHandle != 0xFFFF && server) {
    server->disconnect(teacherHandle);
  }
}

void disconnectAllStudents() {
  clearAllStudentConnections();
}

/*=========================
  Packet parser / builder
=========================*/
bool parsePacket(const String &packet) {
  packetFieldCount = 0;

  if (packet.isEmpty() || packet.length() > 512) return false;

  int start = 0;
  while (start <= packet.length() && packetFieldCount < MAX_PACKET_FIELDS) {
    int sep = packet.indexOf('|', start);
    if (sep < 0) sep = packet.length();

    String field = packet.substring(start, sep);
    if (field.length() == 0) return false;

    packetFields[packetFieldCount++] = field;
    if (sep >= packet.length()) break;
    start = sep + 1;
  }

  return packetFieldCount > 0;
}

PacketCommand getCommand() {
  if (packetFieldCount == 0) return CMD_UNKNOWN;

  const String &c = packetFields[0];
  if (c == "REG") return CMD_REG;
  if (c == "AUTH") return CMD_AUTH;
  if (c == "START_SESSION") return CMD_START_SESSION;
  if (c == "END_SESSION") return CMD_END_SESSION;
  if (c == "RESET") return CMD_RESET;
  if (c == "UNREG") return CMD_UNREG;
  if (c == "ST_REG") return CMD_ST_REG;
  if (c == "CHK_IN") return CMD_CHK_IN;
  if (c == "TK_SBM") return CMD_TK_SBM;
  if (c == "REQ_UPD") return CMD_REQ_UPD;
  if (c == "REG_APPROVED") return CMD_REG_APPROVED;
  if (c == "REG_REJECTED") return CMD_REG_REJECTED;
  if (c == "ATD_ACCEPTED") return CMD_ATD_ACCEPTED;
  if (c == "ATD_REJECTED") return CMD_ATD_REJECTED;
  if (c == "TK") return CMD_TK;
  if (c == "MARKED") return CMD_MARKED;
  if (c == "DONE") return CMD_DONE;
  return CMD_UNKNOWN;
}

bool isTeacherPacketCommand(PacketCommand cmd) {
  switch (cmd) {
    case CMD_REG:
    case CMD_AUTH:
    case CMD_START_SESSION:
    case CMD_END_SESSION:
    case CMD_RESET:
    case CMD_UNREG:
    case CMD_REG_APPROVED:
    case CMD_REG_REJECTED:
    case CMD_ATD_ACCEPTED:
    case CMD_ATD_REJECTED:
    case CMD_TK:
    case CMD_MARKED:
    case CMD_DONE:
      return true;
    default:
      return false;
  }
}

bool isTeacherPacketAllowed(PacketCommand cmd) {
  switch (cmd) {
    case CMD_REG:
      return deviceState == STATE_FACTORY || deviceState == STATE_REGISTERING;
    case CMD_AUTH:
      return deviceState == STATE_AUTHENTICATE;
    case CMD_START_SESSION:
      return deviceState == STATE_SESSION_INACTIVE;
    case CMD_END_SESSION:
      return deviceState == STATE_SESSION_OPEN;
    case CMD_RESET:
      return deviceState == STATE_AUTHENTICATE ||
             deviceState == STATE_SESSION_INACTIVE ||
             deviceState == STATE_SESSION_OPEN;
    case CMD_UNREG:
      return deviceState == STATE_SESSION_INACTIVE;
    case CMD_REG_APPROVED:
    case CMD_REG_REJECTED:
    case CMD_ATD_ACCEPTED:
    case CMD_ATD_REJECTED:
    case CMD_TK:
    case CMD_MARKED:
    case CMD_DONE:
      return deviceState == STATE_SESSION_OPEN;
    default:
      return false;
  }
}

bool isStudentPacketAllowed(PacketCommand cmd) {
  if (deviceState != STATE_SESSION_OPEN) return false;
  switch (cmd) {
    case CMD_ST_REG:
    case CMD_CHK_IN:
    case CMD_TK_SBM:
    case CMD_REQ_UPD:
      return true;
    default:
      return false;
  }
}

String buildPacketFromFields(uint8_t firstField, uint8_t lastField) {
  if (packetFieldCount == 0) return "";
  if (lastField >= packetFieldCount) lastField = packetFieldCount - 1;
  if (firstField > lastField) return "";

  String out;
  for (uint8_t i = firstField; i <= lastField; i++) {
    if (i > firstField) out += "|";
    out += packetFields[i];
  }
  return out;
}

String buildCurrentPacket() {
  if (packetFieldCount == 0) return "";
  return buildPacketFromFields(0, packetFieldCount - 1);
}

String appendRID(const String &packet, uint8_t rid) {
  if (packet.isEmpty()) return String(rid);
  return packet + "|" + String(rid);
}

String removeRID() {
  if (packetFieldCount < 2) return "";
  return buildPacketFromFields(0, packetFieldCount - 2);
}

/*=========================
  BLE send helpers
=========================*/
bool sendTeacherPacket(const String &packet) {
  if (!teacherConnected || !txChar) return false;
  txChar->setValue(packet.c_str());
  return txChar->notify(teacherHandle);
}

bool notifyTeacher(const String &packet) {
  return sendTeacherPacket(packet);
}

bool notifyStudent(uint8_t rid, const String &packet) {
  uint16_t h = getHandleFromRID(rid);
  if (h == 0xFFFF || !txChar) return false;
  txChar->setValue(packet.c_str());
  return txChar->notify(h);
}

void sendRegistered() { sendTeacherPacket("REGISTERED"); }
void sendUnregistered() { sendTeacherPacket("UNREGISTERED"); }
void sendAuthOK() { sendTeacherPacket("AUTH_OK"); }
void sendAuthFailed() { sendTeacherPacket("AUTH_FAILED"); }
void sendResetOK() { sendTeacherPacket("RESET_OK"); }

/*=========================
  Session helpers
=========================*/
void forceEndSession() {
  sessionActive = false;
  setDeviceState(STATE_SESSION_INACTIVE);
  bulkStudentDisconnect = true;
  disconnectAllStudents();
  // RIDs are released by onDisconnect() so they are not reused while the
  // underlying BLE links are still closing.
  bulkStudentDisconnect = false;
}

void startSession() {
  sessionActive = true;
  setDeviceState(STATE_SESSION_OPEN);
}

void endSession() {
  forceEndSession();
  sessionActive = false;
  setDeviceState(STATE_SESSION_INACTIVE);
}

/*=========================
  Packet routing
=========================*/
void packetDispatch(uint16_t connHandle, PacketCommand cmd) {
  if (isTeacherHandle(connHandle)) {
    if (!isTeacherPacketAllowed(cmd)) return;
    handleTeacherPacket(connHandle, cmd);
    return;
  }

  if (isStudentHandle(connHandle)) {
    if (!isStudentPacketAllowed(cmd)) return;
    handleStudentPacket(connHandle, cmd);
    return;
  }
}

void packetReceive(uint16_t connHandle, const String &packet) {
  if (!parsePacket(packet)) return;
  PacketCommand cmd = getCommand();
  if (cmd == CMD_UNKNOWN) return;
  packetDispatch(connHandle, cmd);
}

void handleTeacherPacket(uint16_t connHandle, PacketCommand cmd) {
  (void)connHandle;

  switch (cmd) {
    case CMD_RESET: {
      // Engineering recovery command: erase classroom config and return to Factory Mode.
      // Works even in Authenticate/Idle so the device can be recovered after the classroom
      // entry has been deleted from the app.
      if (!(deviceState == STATE_AUTHENTICATE ||
            deviceState == STATE_SESSION_INACTIVE ||
            deviceState == STATE_SESSION_OPEN)) {
        return;
      }

      bulkStudentDisconnect = true;
      disconnectAllStudents();
      bulkStudentDisconnect = false;

      storageClear();
      teacherAuthenticated = false;
      sessionActive = false;
      authStart = 0;

      sendResetOK();
      delay(100);

      disconnectTeacher();
      teacherConnected = false;
      teacherHandle = 0xFFFF;
      clearAllRID();
      setDeviceState(STATE_FACTORY);
      break;
    }

    case CMD_REG: {
      // REG|CID|TID|CLS|TC
      if (!(deviceState == STATE_FACTORY || deviceState == STATE_REGISTERING)) return;
      if (packetFieldCount < 5) return;

      clsID = packetFields[1];
      tchID = packetFields[2];
      classroomName = packetFields[3];
      teacherName = packetFields[4];

      if (!storageSave()) return;

      sendRegistered();
      delay(100);

      setDeviceState(STATE_AUTHENTICATE);
      teacherAuthenticated = false;
      authStart = 0;
      disconnectTeacher();
      break;
    }

    case CMD_AUTH: {
      // AUTH|TID|CID
      if (deviceState != STATE_AUTHENTICATE) return;
      if (packetFieldCount < 3) return;

      if (packetFields[1] == tchID && packetFields[2] == clsID) {
        teacherAuthenticated = true;
        authStart = 0;
        sendAuthOK();
        setDeviceState(STATE_SESSION_INACTIVE);
        triggerAuthSuccessBlink();
      } else {
        sendAuthFailed();
        teacherAuthenticated = false;
        authStart = 0;
        disconnectTeacher();
        if (storageIsRegistered()) setDeviceState(STATE_AUTHENTICATE);
        else setDeviceState(STATE_FACTORY);
      }
      break;
    }

    case CMD_START_SESSION: {
      if (!teacherAuthenticated || deviceState != STATE_SESSION_INACTIVE) return;
      startSession();
      break;
    }

    case CMD_END_SESSION: {
      if (!teacherAuthenticated) return;
      if (deviceState != STATE_SESSION_OPEN) return;
      endSession();
      break;
    }

    case CMD_UNREG: {
      if (!teacherAuthenticated || deviceState != STATE_SESSION_INACTIVE) return;
      storageClear();
      sendUnregistered();
      delay(100);
      teacherAuthenticated = false;
      authStart = 0;
      sessionActive = false;
      setDeviceState(STATE_FACTORY);
      disconnectTeacher();
      break;
    }

    case CMD_REG_APPROVED:
    case CMD_TK: {
      // teacher -> student, keep connection alive
      if (packetFieldCount < 2) return;
      uint8_t rid = (uint8_t)packetFields[packetFieldCount - 1].toInt();
      String out = removeRID();
      if (out.isEmpty()) return;
      notifyStudent(rid, out);
      break;
    }

    case CMD_REG_REJECTED:
    case CMD_ATD_REJECTED: {
      if (packetFieldCount < 2) return;
      uint8_t rid = (uint8_t)packetFields[packetFieldCount - 1].toInt();
      String out = removeRID();
      if (out.isEmpty()) return;
      notifyStudent(rid, out);
      disconnectStudent(rid);
      setDeviceState(STATE_SESSION_OPEN);
      break;
    }

    case CMD_ATD_ACCEPTED: {
      if (packetFieldCount < 2) return;
      uint8_t rid = (uint8_t)packetFields[packetFieldCount - 1].toInt();
      String out = removeRID();
      if (out.isEmpty()) return;
      notifyStudent(rid, out);
      break;
    }

    case CMD_MARKED:
    case CMD_DONE: {
      if (packetFieldCount < 2) return;
      uint8_t rid = (uint8_t)packetFields[packetFieldCount - 1].toInt();
      String out = removeRID();
      if (out.isEmpty()) return;
      notifyStudent(rid, out);
      disconnectStudent(rid);
      setDeviceState(STATE_SESSION_OPEN);
      break;
    }

    default:
      break;
  }
}

void handleStudentPacket(uint16_t connHandle, PacketCommand cmd) {
  if (deviceState != STATE_SESSION_OPEN) return;

  uint8_t rid = getRIDFromHandle(connHandle);
  if (rid == 0xFF || rid == 0) return;

  switch (cmd) {
    case CMD_ST_REG:
    case CMD_CHK_IN:
    case CMD_TK_SBM:
    case CMD_REQ_UPD: {
      String out = buildCurrentPacket();
      if (out.isEmpty()) return;
      out = appendRID(out, rid);
      notifyTeacher(out);
      break;
    }

    default:
      break;
  }
}

/*=========================
  BLE diagnostics
=========================*/
void printBLEConnectionSummary(const char *event, uint16_t handle, int reason) {
  uint8_t studentCount = 0;
  for (uint8_t i = 0; i < MAX_STUDENTS; ++i) {
    if (ridUsed[i]) ++studentCount;
  }

  Serial.printf(
    "[BLE] %s handle=%u reason=%d teacher=%s teacherHandle=%u students=%u/%u\n",
    event,
    handle,
    reason,
    teacherConnected ? "YES" : "NO",
    teacherHandle,
    studentCount,
    MAX_STUDENTS
  );
}

/*=========================
  BLE manager
=========================*/
class CalisServerCallbacks : public NimBLEServerCallbacks {
public:
  void onConnect(NimBLEServer* srv, NimBLEConnInfo& info) override {
    uint16_t h = info.getConnHandle();

    // Give NimBLE a brief recovery window around connection establishment.
    advertisingRestartNotBefore = millis() + ADVERTISING_RESTART_COOLDOWN_MS;

    printBLEConnectionSummary("CONNECT ATTEMPT", h);

    if (!teacherConnected) {
      // Only the teacher may claim RID 0.
      if (deviceState == STATE_SESSION_OPEN) {
        srv->disconnect(h);
        requestAdvertisingRefresh();
        return;
      }

      teacherConnected = true;
      teacherHandle = h;
      teacherAuthenticated = false;

      Serial.printf("[BLE] Assigned handle %u as TEACHER (RID 0)\n", h);
      sessionActive = false;

      if (storageIsRegistered()) {
        authStart = millis();
        setDeviceState(STATE_AUTHENTICATE);
      } else {
        authStart = 0;
        setDeviceState(STATE_REGISTERING);
      }

      requestAdvertisingRefresh();
      return;
    }

    // Teacher is already connected. Student connections are allowed only
    // during Session Active.
    if (deviceState != STATE_SESSION_OPEN) {
      // This also covers a student attempting to connect during Session Inactive.
      srv->disconnect(h);
      requestAdvertisingRefresh();
      return;
    }

    int8_t rid = allocateRID(h);
    if (rid < 0) {
      Serial.printf("[BLE] Student connection rejected: all %u student slots are occupied\n", MAX_STUDENTS);
      srv->disconnect(h);
      // No state change is needed. The connection limit itself controls
      // availability; advertising will remain off once all slots are occupied.
      requestAdvertisingRefresh();
      return;
    }

    Serial.printf("[BLE] Assigned handle %u as STUDENT RID %d\n", h, rid);

    setDeviceState(STATE_SESSION_OPEN);

    // Advertising remains available while at least one BLE slot is free.
    requestAdvertisingRefresh();
  }

  void onDisconnect(NimBLEServer* srv, NimBLEConnInfo& info, int reason) override {
    (void)srv;
    (void)reason;

    uint16_t h = info.getConnHandle();

    // Delay the restart slightly so NimBLE can finish tearing down the link.
    advertisingRestartNotBefore = millis() + ADVERTISING_RESTART_COOLDOWN_MS;

    printBLEConnectionSummary("DISCONNECT", h, reason);

    if (teacherConnected && h == teacherHandle) {
      teacherConnected = false;
      teacherHandle = 0xFFFF;
      teacherAuthenticated = false;
      authStart = 0;
      sessionActive = false;

      if (deviceState == STATE_REGISTERING) {
        setDeviceState(STATE_FACTORY);
      } else if (deviceState == STATE_SESSION_OPEN) {
        forceEndSession();
        if (storageIsRegistered()) setDeviceState(STATE_AUTHENTICATE);
        else setDeviceState(STATE_FACTORY);
      } else if (storageIsRegistered()) {
        setDeviceState(STATE_AUTHENTICATE);
      } else {
        setDeviceState(STATE_FACTORY);
      }

      requestAdvertisingRefresh();
      return;
    }

    // Release the RID only after NimBLE reports the BLE link is actually
    // disconnected. This prevents premature RID reuse on fast reconnects.
    uint8_t rid = getRIDFromHandle(h);
    if (rid != 0xFF && rid != 0) {
      Serial.printf("[BLE] Released student RID %u for handle %u\n", rid, h);
      releaseRID(rid);
    }

    if (bulkStudentDisconnect) {
      requestAdvertisingRefresh();
      return;
    }

    if (deviceState == STATE_SESSION_OPEN) {
      setDeviceState(STATE_SESSION_OPEN);
    }

    // Restore advertising after every student-side disconnect, including
    // rejected attempts that never received a RID.
    requestAdvertisingRefresh();
  }
};

class CalisRXCallbacks : public NimBLECharacteristicCallbacks {
public:
  void onWrite(NimBLECharacteristic *c, NimBLEConnInfo &info) override {
    std::string v = c->getValue();
    if (v.empty()) return;

    String packet = String(v.c_str());
    packetReceive(info.getConnHandle(), packet);
  }
};

void bleApplyAdvertisement() {
  refreshAdvertising();
}

void bleRestartAdvertising() {
  requestAdvertisingRefresh();
}

void initBLE() {
  // The library build configuration above must provide 6 simultaneous links.
  // This sketch uses them as RID 0 (teacher) and RID 1..5 (students).
  NimBLEDevice::init(DEVICE_NAME);
  NimBLEDevice::setPower(ESP_PWR_LVL_P9);

  server = NimBLEDevice::createServer();
  server->setCallbacks(new CalisServerCallbacks());

  // Let NimBLE restore advertising after a normal disconnect.
  // refreshAdvertising() still keeps advertising OFF when all 6 slots are occupied.
  server->advertiseOnDisconnect(true);

  service = server->createService(SERVICE_UUID);

  rxChar = service->createCharacteristic(RX_UUID, NIMBLE_PROPERTY::WRITE | NIMBLE_PROPERTY::WRITE_NR);
  txChar = service->createCharacteristic(TX_UUID, NIMBLE_PROPERTY::NOTIFY);

  rxChar->setCallbacks(new CalisRXCallbacks());

  service->start();

  advertising = NimBLEDevice::getAdvertising();
  advertising->addServiceUUID(SERVICE_UUID);
  bleApplyAdvertisement();
}

void processBLE() {
  if (deviceState == STATE_AUTHENTICATE && teacherConnected && !teacherAuthenticated) {
    if (authStart > 0 && (millis() - authStart >= AUTH_TIMEOUT_MS)) {
      sendAuthFailed();
      teacherAuthenticated = false;
      authStart = 0;
      disconnectTeacher();
      if (storageIsRegistered()) setDeviceState(STATE_AUTHENTICATE);
      else setDeviceState(STATE_FACTORY);
    }
  }
}

/*=========================
  Arduino setup / loop
=========================*/
void setup() {
  Serial.begin(115200);
  delay(200);

  Serial.printf(
    "[CALIS] App BLE capacity: %u total connections (%u teacher + %u students)\n",
    MAX_BLE_CONNECTIONS,
    1,
    MAX_STUDENTS
  );

#ifdef MYNEWT_VAL
  Serial.printf(
    "[CALIS] NimBLE host max connections: %d\n",
    MYNEWT_VAL(BLE_MAX_CONNECTIONS)
  );
#else
  Serial.println("[CALIS] NimBLE host max connections: unavailable");
#endif

#ifdef CONFIG_BTDM_CTRL_BLE_MAX_CONN
  Serial.printf(
    "[CALIS] ESP32 BLE controller max connections: %d\n",
    CONFIG_BTDM_CTRL_BLE_MAX_CONN
  );
#else
  Serial.println("[CALIS] ESP32 BLE controller max connections: unavailable");
#endif

  Wire.begin();
  Wire.setClock(400000UL);

  oled.begin();
  oled.setPowerSave(0);
  oled.clearBuffer();
  oled.sendBuffer();

  pinMode(LED_PIN, OUTPUT);
  digitalWrite(LED_PIN, LOW);

  clearAllRID();

  if (storageLoad()) {
    setDeviceState(STATE_AUTHENTICATE);
  } else {
    setDeviceState(STATE_FACTORY);
  }

  initBLE();

  advertisingWatchdogLastCheck = millis();
  advertisingRestartNotBefore = millis();

  Serial.printf("[CALIS] BLE capacity: %u total (%u teacher + %u students)\\n",
                (unsigned)NIMBLE_MAX_CONNECTIONS,
                1U,
                (unsigned)MAX_STUDENTS);
  Serial.printf("[CALIS] RID map: 0=Teacher, 1-5=Students\\n");

  refreshOLED();
}

void loop() {
  processBLE();
  servicePendingAdvertising();
  serviceAdvertisingWatchdog();
  updateOLEDAdvertisingIndicator();
  updateStatusLED();
  delay(5);
}
