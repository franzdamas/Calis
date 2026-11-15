package com.example.ble

enum class DeviceState(val manufacturerHex: String, val displayName: String) {
    FACTORY_MODE("FFFF", "Factory Mode (Unregistered)"),
    REGISTERING("F1FF", "Registering"),
    AUTHENTICATE("FF00", "Authenticate/Idle"),
    SESSION_INACTIVE("FF01", "Session Inactive"),
    SESSION_ACTIVE_OPEN("0011", "Session Active (Open)"),
    SESSION_ACTIVE_BUSY("0111", "Session Busy"),
    UNKNOWN("0000", "Unknown State");

    companion object {
        fun fromHex(hex: String): DeviceState {
            val cleanHex = hex.replace(" ", "").uppercase()
            // Priority 1: Active session states (0x0111 busy, 0x0011 open)
            // MUST be checked before FACTORY_MODE ("FFFF") because ESP32 packets often use
            // Company ID 0xFFFF followed by status 0x0011 or 0x11!
            if (cleanHex.contains("0111") || cleanHex.contains("1101")) return SESSION_ACTIVE_BUSY
            if (cleanHex.contains("0011") || cleanHex.contains("1100") || cleanHex == "11" || cleanHex.endsWith("11")) {
                return SESSION_ACTIVE_OPEN
            }
            if (cleanHex.contains("FF01") || cleanHex.contains("01FF")) return SESSION_INACTIVE
            if (cleanHex.contains("FF00") || cleanHex.contains("00FF")) return AUTHENTICATE
            if (cleanHex.contains("F1FF") || cleanHex.contains("FFF1")) return REGISTERING
            if (cleanHex.contains("FFFF")) return FACTORY_MODE

            return UNKNOWN
        }

        fun fromBytes(bytes: ByteArray?): DeviceState {
            if (bytes == null || bytes.isEmpty()) return UNKNOWN
            // Check for single byte 0x11 status
            if (bytes.size == 1 && (bytes[0].toInt() and 0xFF) == 0x11) {
                return SESSION_ACTIVE_OPEN
            }
            val sb = StringBuilder()
            for (b in bytes) {
                sb.append(String.format("%02X", b))
            }
            val hex = sb.toString()
            val state = fromHex(hex)
            if (state != UNKNOWN) return state

            // Secondary check: look for 0x11 status byte anywhere in the array
            for (b in bytes) {
                if ((b.toInt() and 0xFF) == 0x11) {
                    return SESSION_ACTIVE_OPEN
                }
            }
            return UNKNOWN
        }
    }
}
