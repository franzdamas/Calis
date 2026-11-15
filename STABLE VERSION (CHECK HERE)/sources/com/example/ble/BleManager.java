package com.example.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.example.util.BlePermissionUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: BleManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\b\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e*\u0004lw\u0081\u0001\b\u0007\u0018\u0000 \u0091\u00012\u00020\u0001:\b\u0091\u0001\u0092\u0001\u0093\u0001\u0094\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J,\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020!J\u0006\u0010$\u001a\u00020\u001dJ\u000e\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u001fJ\u000e\u0010?\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020!J\u0010\u0010A\u001a\u00020\u001d2\b\u0010B\u001a\u0004\u0018\u00010\u001fJ\u0012\u0010M\u001a\u00020!2\b\u0010N\u001a\u0004\u0018\u00010IH\u0002J\u001c\u0010O\u001a\u00020\u001d2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010Q\u001a\u00020!J\u0006\u0010R\u001a\u00020\u001dJ\u0006\u0010S\u001a\u00020\u001dJ\u0010\u0010T\u001a\u00020\u001d2\b\b\u0002\u0010U\u001a\u00020!J\u000e\u0010n\u001a\u00020\u001d2\u0006\u0010o\u001a\u00020\u0019J\u0006\u0010p\u001a\u00020!J\u0006\u0010q\u001a\u00020!J\u0012\u0010s\u001a\u00020\u001d2\b\b\u0002\u0010t\u001a\u00020!H\u0007J\b\u0010u\u001a\u00020\u001dH\u0007J\u0012\u0010y\u001a\u00020!2\b\u0010z\u001a\u0004\u0018\u00010{H\u0002J\u000e\u0010|\u001a\u00020\u001d2\u0006\u0010}\u001a\u00020\u001fJ\u0006\u0010~\u001a\u00020\u001dJ\u0006\u0010\u007f\u001a\u00020\u001dJ\u0012\u0010\u0083\u0001\u001a\u00020\u001d2\u0007\u0010\u0084\u0001\u001a\u000205H\u0002J*\u0010\u0085\u0001\u001a\u00020\u001d2\u0007\u0010\u0084\u0001\u001a\u0002052\u0018\b\u0002\u0010\u0086\u0001\u001a\u0011\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0087\u0001J\u0019\u0010\u0088\u0001\u001a\u00020\u001d2\u0007\u0010\u0089\u0001\u001a\u00020\u001f2\u0007\u0010\u008a\u0001\u001a\u00020!J\t\u0010\u008b\u0001\u001a\u00020\u001dH\u0002J\t\u0010\u008d\u0001\u001a\u00020\u001dH\u0002J\t\u0010\u008e\u0001\u001a\u00020\u001dH\u0002J\u000f\u0010\u008f\u0001\u001a\u00020\u001d2\u0006\u0010o\u001a\u00020,J\u001a\u0010\u0090\u0001\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u001f2\u0007\u0010\u0084\u0001\u001a\u000205H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0016\u0010\f\u001a\u0004\u0018\u00010\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020(0\u0015¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0017R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020,0\u0015¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0017R\u001a\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00120\u0015¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0017R\u0014\u00103\u001a\b\u0012\u0004\u0012\u00020504X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020507¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0016\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010;\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0015¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0017R\u0014\u0010=\u001a\b\u0012\u0004\u0012\u00020!0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010>\u001a\b\u0012\u0004\u0012\u00020!0\u0015¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0017R\u0014\u0010C\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0015¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u0017R\u000e\u0010F\u001a\u00020GX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010H\u001a\u0004\u0018\u00010IX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010V\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u001c\u0010Z\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\"\u0010`\u001a\u0004\u0018\u00010\u001f2\b\u0010_\u001a\u0004\u0018\u00010\u001f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\ba\u0010\\R\u0010\u0010b\u001a\u0004\u0018\u00010cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010d\u001a\u0004\u0018\u00010cX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010e\u001a\u00020!2\u0006\u0010_\u001a\u00020!@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010W\"\u0004\bf\u0010YR\u000e\u0010g\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010k\u001a\u00020lX\u0082\u0004¢\u0006\u0004\n\u0002\u0010mR\u000e\u0010r\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010v\u001a\u00020wX\u0082\u0004¢\u0006\u0004\n\u0002\u0010xR\u0013\u0010\u0080\u0001\u001a\u00030\u0081\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0082\u0001R\u0011\u0010\u008c\u0001\u001a\u0004\u0018\u00010cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0095\u0001"}, d2 = {"Lcom/example/ble/BleManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "bluetoothManager", "Landroid/bluetooth/BluetoothManager;", "getBluetoothManager", "()Landroid/bluetooth/BluetoothManager;", "bluetoothManager$delegate", "Lkotlin/Lazy;", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "getBluetoothAdapter", "()Landroid/bluetooth/BluetoothAdapter;", "_debugLogs", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/example/ble/BleManager$DebugLog;", "debugLogs", "Lkotlinx/coroutines/flow/StateFlow;", "getDebugLogs", "()Lkotlinx/coroutines/flow/StateFlow;", "_currentRssi", "", "currentRssi", "getCurrentRssi", "addDebugLog", "", "message", "", "isError", "", "isRx", "isTx", "requestRssi", "sendRawPacket", "payload", "_connectionState", "Lcom/example/ble/BleManager$ConnectionState;", "connectionState", "getConnectionState", "_deviceState", "Lcom/example/ble/DeviceState;", "deviceState", "getDeviceState", "_scannedDevices", "Lcom/example/ble/BleManager$ScannedDevice;", "scannedDevices", "getScannedDevices", "_incomingPackets", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/example/ble/BlePacket;", "incomingPackets", "Lkotlinx/coroutines/flow/SharedFlow;", "getIncomingPackets", "()Lkotlinx/coroutines/flow/SharedFlow;", "_pendingAction", "pendingAction", "getPendingAction", "_isAuthenticated", "isAuthenticated", "setAuthenticated", "auth", "setPendingAction", "action", "_lastTxResponse", "lastTxResponse", "getLastTxResponse", "scope", "Lkotlinx/coroutines/CoroutineScope;", "bluetoothGatt", "Landroid/bluetooth/BluetoothGatt;", "rxCharacteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "txCharacteristic", "refreshDeviceCache", "gatt", "teardownGatt", "gattToClose", "triggerScan", "clearBleObject", "clearTransactionState", "resetBleStateOnStartup", "force", "isSimulatedMode", "()Z", "setSimulatedMode", "(Z)V", "lastTargetAddress", "getLastTargetAddress", "()Ljava/lang/String;", "setLastTargetAddress", "(Ljava/lang/String;)V", "value", "queuedRawPacket", "getQueuedRawPacket", "disconnectJob", "Lkotlinx/coroutines/Job;", "retryScanJob", "isExplicitlyDisconnected", "setExplicitlyDisconnected", "virtualClassroomName", "virtualTeacherName", "virtualTid", "virtualCid", "btStateReceiver", "com/example/ble/BleManager$btStateReceiver$1", "Lcom/example/ble/BleManager$btStateReceiver$1;", "onBluetoothStateChanged", "state", "isBluetoothSupported", "isBluetoothEnabled", "isScanning", "startScan", "forceRestart", "stopScan", "scanCallback", "com/example/ble/BleManager$scanCallback$1", "Lcom/example/ble/BleManager$scanCallback$1;", "hasManufacturerDataInBytes", "rawBytes", "", "connect", "address", "clearScannedDevices", "disconnect", "gattCallback", "com/example/ble/BleManager$gattCallback$1", "Lcom/example/ble/BleManager$gattCallback$1;", "updateDeviceStateForPacket", "packet", "sendPacket", "onComplete", "Lkotlin/Function1;", "setVirtualClassroomInfo", "classroomName", "isBound", "enableVirtualMode", "virtualHeartbeatJob", "startVirtualHeartbeat", "connectVirtual", "setVirtualDeviceState", "handleVirtualPacket", "Companion", "ScannedDevice", "ConnectionState", "DebugLog", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BleManager {
    private static final UUID CCCD_UUID;
    private static volatile BleManager INSTANCE = null;
    private static final UUID RX_CHAR_UUID;
    private static final UUID SERVICE_UUID;
    public static final String TAG = "BleManager";
    private static final UUID TX_CHAR_UUID;
    private static final MutableStateFlow<Boolean> isDebugConsoleVisible;
    private final MutableStateFlow<ConnectionState> _connectionState;
    private final MutableStateFlow<Integer> _currentRssi;
    private final MutableStateFlow<List<DebugLog>> _debugLogs;
    private final MutableStateFlow<DeviceState> _deviceState;
    private final MutableSharedFlow<BlePacket> _incomingPackets;
    private final MutableStateFlow<Boolean> _isAuthenticated;
    private final MutableStateFlow<String> _lastTxResponse;
    private final MutableStateFlow<String> _pendingAction;
    private final MutableStateFlow<List<ScannedDevice>> _scannedDevices;
    private BluetoothGatt bluetoothGatt;

    /* JADX INFO: renamed from: bluetoothManager$delegate, reason: from kotlin metadata */
    private final Lazy bluetoothManager;
    private final BleManager$btStateReceiver$1 btStateReceiver;
    private final StateFlow<ConnectionState> connectionState;
    private final Context context;
    private final StateFlow<Integer> currentRssi;
    private final StateFlow<List<DebugLog>> debugLogs;
    private final StateFlow<DeviceState> deviceState;
    private Job disconnectJob;
    private final BleManager$gattCallback$1 gattCallback;
    private final SharedFlow<BlePacket> incomingPackets;
    private final StateFlow<Boolean> isAuthenticated;
    private boolean isExplicitlyDisconnected;
    private boolean isScanning;
    private boolean isSimulatedMode;
    private String lastTargetAddress;
    private final StateFlow<String> lastTxResponse;
    private final StateFlow<String> pendingAction;
    private String queuedRawPacket;
    private Job retryScanJob;
    private BluetoothGattCharacteristic rxCharacteristic;
    private final BleManager$scanCallback$1 scanCallback;
    private final StateFlow<List<ScannedDevice>> scannedDevices;
    private final CoroutineScope scope;
    private BluetoothGattCharacteristic txCharacteristic;
    private String virtualCid;
    private String virtualClassroomName;
    private Job virtualHeartbeatJob;
    private String virtualTeacherName;
    private String virtualTid;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: Type inference failed for: r0v13, types: [com.example.ble.BleManager$btStateReceiver$1] */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.example.ble.BleManager$gattCallback$1] */
    public BleManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.bluetoothManager = LazyKt.lazy(new Function0() { // from class: com.example.ble.BleManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BleManager.bluetoothManager_delegate$lambda$0(this.f$0);
            }
        });
        this._debugLogs = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.debugLogs = FlowKt.asStateFlow(this._debugLogs);
        this._currentRssi = StateFlowKt.MutableStateFlow(0);
        this.currentRssi = FlowKt.asStateFlow(this._currentRssi);
        this._connectionState = StateFlowKt.MutableStateFlow(ConnectionState.Disconnected.INSTANCE);
        this.connectionState = FlowKt.asStateFlow(this._connectionState);
        this._deviceState = StateFlowKt.MutableStateFlow(DeviceState.FACTORY_MODE);
        this.deviceState = FlowKt.asStateFlow(this._deviceState);
        this._scannedDevices = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.scannedDevices = FlowKt.asStateFlow(this._scannedDevices);
        this._incomingPackets = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this.incomingPackets = FlowKt.asSharedFlow(this._incomingPackets);
        this._pendingAction = StateFlowKt.MutableStateFlow(null);
        this.pendingAction = FlowKt.asStateFlow(this._pendingAction);
        this._isAuthenticated = StateFlowKt.MutableStateFlow(false);
        this.isAuthenticated = FlowKt.asStateFlow(this._isAuthenticated);
        this._lastTxResponse = StateFlowKt.MutableStateFlow("");
        this.lastTxResponse = FlowKt.asStateFlow(this._lastTxResponse);
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null)));
        this.virtualClassroomName = "Calis Classroom";
        this.virtualTeacherName = "Teacher Account";
        this.virtualTid = "";
        this.virtualCid = "";
        this.btStateReceiver = new BroadcastReceiver() { // from class: com.example.ble.BleManager$btStateReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context c, Intent intent) {
                if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "android.bluetooth.adapter.action.STATE_CHANGED")) {
                    int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    this.this$0.onBluetoothStateChanged(state);
                }
            }
        };
        try {
            IntentFilter filter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
            this.context.registerReceiver(this.btStateReceiver, filter);
        } catch (Exception e) {
            Log.e(TAG, "Error registering btStateReceiver in BleManager", e);
        }
        this.scanCallback = new BleManager$scanCallback$1(this);
        this.gattCallback = new BluetoothGattCallback() { // from class: com.example.ble.BleManager$gattCallback$1
            @Override // android.bluetooth.BluetoothGattCallback
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                Log.d("CALIS", "[REG] Connection state change: status=" + status + ", newState=" + newState + " (Connected=2, Disconnected=0)");
                Job job = this.this$0.disconnectJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                this.this$0.disconnectJob = null;
                boolean z = false;
                if (status != 0) {
                    Log.e("CALIS", "[REG] GATT error reported: status=" + status + ", newState=" + newState);
                    BleManager.addDebugLog$default(this.this$0, "GATT error (status " + status + ", newState " + newState + ")", true, false, false, 12, null);
                    this.this$0.teardownGatt(gatt, true);
                    this.this$0._connectionState.setValue(new BleManager.ConnectionState.Error("Connection failed (status " + status + ")"));
                    CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.this$0.context, false, 2, null);
                    BluetoothAdapter bluetoothAdapter = this.this$0.getBluetoothAdapter();
                    if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                        z = true;
                    }
                    if (z) {
                        this.this$0.startScan(true);
                    }
                    return;
                }
                switch (newState) {
                    case 0:
                        Log.d("CALIS", "[REG] Expected or unexpected device disconnect. Performing clean GATT teardown after callback.");
                        BleManager.addDebugLog$default(this.this$0, "Device disconnected callback. Calling close() and clearing state...", false, false, false, 14, null);
                        this.this$0.teardownGatt(gatt, true);
                        break;
                    case 2:
                        BleManager.addDebugLog$default(this.this$0, "GATT Connected, requesting MTU 255...", false, false, false, 14, null);
                        this.this$0._connectionState.setValue(BleManager.ConnectionState.Connecting.INSTANCE);
                        CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.this$0.context, false, 2, null);
                        BuildersKt__Builders_commonKt.launch$default(this.this$0.scope, null, null, new BleManager$gattCallback$1$onConnectionStateChange$1(gatt, this.this$0, null), 3, null);
                        break;
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                if (status == 0) {
                    this.this$0._currentRssi.setValue(Integer.valueOf(rssi));
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                Log.d("CALIS", "[REG] onMtuChanged: mtu=" + mtu + ", status=" + status + ". Discovering services...");
                if (gatt != null) {
                    gatt.discoverServices();
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                Log.d("CALIS", "[REG] Services discovered status: " + status);
                if (status != 0 || gatt == null) {
                    Log.e("CALIS", "[REG] Service discovery failed: status=" + status + ", gattIsNull=" + (gatt == null));
                    BleManager.addDebugLog$default(this.this$0, "Service discovery failed: status " + status, true, false, false, 12, null);
                    this.this$0.teardownGatt(gatt, true);
                    this.this$0._connectionState.setValue(new BleManager.ConnectionState.Error("Service discovery failed (status " + status + ")"));
                    CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.this$0.context, false, 2, null);
                    return;
                }
                BluetoothGattService service = gatt.getService(BleManager.INSTANCE.getSERVICE_UUID());
                if (service != null) {
                    this.this$0.rxCharacteristic = service.getCharacteristic(BleManager.INSTANCE.getRX_CHAR_UUID());
                    BluetoothGattCharacteristic txChar = service.getCharacteristic(BleManager.INSTANCE.getTX_CHAR_UUID());
                    this.this$0.txCharacteristic = txChar;
                    Log.d("CALIS", "[REG] RX=" + (this.this$0.rxCharacteristic != null) + ", TX=" + (txChar != null));
                    BluetoothGattCharacteristic bluetoothGattCharacteristic = this.this$0.rxCharacteristic;
                    if (bluetoothGattCharacteristic != null) {
                        gatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
                    }
                    if (txChar != null) {
                        gatt.setCharacteristicNotification(txChar, true);
                    }
                    BluetoothGattCharacteristic bluetoothGattCharacteristic2 = this.this$0.rxCharacteristic;
                    BluetoothGattDescriptor rxCccd = bluetoothGattCharacteristic2 != null ? bluetoothGattCharacteristic2.getDescriptor(BleManager.INSTANCE.getCCCD_UUID()) : null;
                    BluetoothGattDescriptor txCccd = txChar != null ? txChar.getDescriptor(BleManager.INSTANCE.getCCCD_UUID()) : null;
                    if (rxCccd != null) {
                        Log.d("CALIS", "[REG] Subscribing to RX notifications via CCCD...");
                        if (Build.VERSION.SDK_INT >= 33) {
                            gatt.writeDescriptor(rxCccd, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            return;
                        } else {
                            rxCccd.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            gatt.writeDescriptor(rxCccd);
                            return;
                        }
                    }
                    if (txCccd != null) {
                        Log.d("CALIS", "[REG] Subscribing to TX notifications via CCCD...");
                        if (Build.VERSION.SDK_INT >= 33) {
                            gatt.writeDescriptor(txCccd, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            return;
                        } else {
                            txCccd.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            gatt.writeDescriptor(txCccd);
                            return;
                        }
                    }
                    Log.d("CALIS", "[REG] No CCCD descriptors found. Setting ConnectionState to Connected.");
                    MutableStateFlow mutableStateFlow = this.this$0._connectionState;
                    String name = gatt.getDevice().getName();
                    if (name == null) {
                        name = "Calis Device";
                    }
                    String address = gatt.getDevice().getAddress();
                    Intrinsics.checkNotNullExpressionValue(address, "getAddress(...)");
                    mutableStateFlow.setValue(new BleManager.ConnectionState.Connected(name, address, (DeviceState) this.this$0._deviceState.getValue()));
                    CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.this$0.context, false, 2, null);
                    return;
                }
                Log.e("CALIS", "[REG] Service UUID " + BleManager.INSTANCE.getSERVICE_UUID() + " not found on device");
                BleManager.addDebugLog$default(this.this$0, "Required Calis service not found", true, false, false, 12, null);
                this.this$0.teardownGatt(gatt, true);
                this.this$0._connectionState.setValue(new BleManager.ConnectionState.Error("Service not found"));
                CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.this$0.context, false, 2, null);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                boolean writeResult;
                BluetoothGattCharacteristic characteristic;
                BluetoothGattCharacteristic characteristic2;
                Log.d("CALIS", "[REG] onDescriptorWrite status: " + status + ", descriptor UUID: " + (descriptor != null ? descriptor.getUuid() : null) + ", char UUID: " + ((descriptor == null || (characteristic2 = descriptor.getCharacteristic()) == null) ? null : characteristic2.getUuid()));
                if (status != 0) {
                    Log.w("CALIS", "[REG] Descriptor write status non-success: " + status + ", continuing...");
                }
                if (Intrinsics.areEqual((descriptor == null || (characteristic = descriptor.getCharacteristic()) == null) ? null : characteristic.getUuid(), BleManager.INSTANCE.getRX_CHAR_UUID())) {
                    BluetoothGattCharacteristic bluetoothGattCharacteristic = this.this$0.txCharacteristic;
                    BluetoothGattDescriptor txCccd = bluetoothGattCharacteristic != null ? bluetoothGattCharacteristic.getDescriptor(BleManager.INSTANCE.getCCCD_UUID()) : null;
                    if (txCccd != null && gatt != null) {
                        Log.d("CALIS", "[REG] Sequential write: Subscribing to TX notifications via CCCD...");
                        if (Build.VERSION.SDK_INT >= 33) {
                            writeResult = gatt.writeDescriptor(txCccd, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE) == 0;
                        } else {
                            txCccd.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            writeResult = gatt.writeDescriptor(txCccd);
                        }
                        if (writeResult) {
                            return;
                        }
                    }
                }
                if (gatt != null) {
                    Log.d("CALIS", "[REG] Notification configuration completed. Setting ConnectionState to Connected.");
                    MutableStateFlow mutableStateFlow = this.this$0._connectionState;
                    String name = gatt.getDevice().getName();
                    if (name == null) {
                        name = "Calis Device";
                    }
                    String address = gatt.getDevice().getAddress();
                    Intrinsics.checkNotNullExpressionValue(address, "getAddress(...)");
                    mutableStateFlow.setValue(new BleManager.ConnectionState.Connected(name, address, (DeviceState) this.this$0._deviceState.getValue()));
                    CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.this$0.context, false, 2, null);
                    String queued = this.this$0.getQueuedRawPacket();
                    if (queued != null) {
                        this.this$0.queuedRawPacket = null;
                        BuildersKt__Builders_commonKt.launch$default(this.this$0.scope, null, null, new BleManager$gattCallback$1$onDescriptorWrite$1(this.this$0, queued, null), 3, null);
                    }
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                Log.d(BleManager.TAG, "onCharacteristicWrite status: " + status + " for characteristic: " + (characteristic != null ? characteristic.getUuid() : null));
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, byte[] value) {
                Intrinsics.checkNotNullParameter(gatt, "gatt");
                Intrinsics.checkNotNullParameter(characteristic, "characteristic");
                Intrinsics.checkNotNullParameter(value, "value");
                String payload = StringsKt.trim((CharSequence) new String(value, Charsets.UTF_8)).toString();
                Log.d("CALIS", "[REG] Received notification packet: " + payload);
                BleManager.addDebugLog$default(this.this$0, "RX: " + payload, false, true, false, 10, null);
                this.this$0._lastTxResponse.setValue(payload);
                BlePacket packet = PacketParser.INSTANCE.parse(payload);
                if (packet instanceof BlePacket.AuthOkResponse) {
                    this.this$0._isAuthenticated.setValue(true);
                    this.this$0._deviceState.setValue(DeviceState.SESSION_INACTIVE);
                } else if ((packet instanceof BlePacket.AuthFailedResponse) || (packet instanceof BlePacket.UnregisteredResponse)) {
                    this.this$0._isAuthenticated.setValue(false);
                }
                BuildersKt__Builders_commonKt.launch$default(this.this$0.scope, null, null, new BleManager$gattCallback$1$onCharacteristicChanged$1(this.this$0, packet, null), 3, null);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            @Deprecated(message = "Deprecated in API 33")
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                byte[] bytes;
                if (Build.VERSION.SDK_INT >= 33 || characteristic == null || (bytes = characteristic.getValue()) == null) {
                    return;
                }
                String payload = StringsKt.trim((CharSequence) new String(bytes, Charsets.UTF_8)).toString();
                Log.d("CALIS", "[REG] Received notification packet (deprecated callback): " + payload);
                BleManager.addDebugLog$default(this.this$0, "RX: " + payload, false, true, false, 10, null);
                this.this$0._lastTxResponse.setValue(payload);
                BlePacket packet = PacketParser.INSTANCE.parse(payload);
                if (packet instanceof BlePacket.AuthOkResponse) {
                    this.this$0._isAuthenticated.setValue(true);
                    this.this$0._deviceState.setValue(DeviceState.SESSION_INACTIVE);
                } else if ((packet instanceof BlePacket.AuthFailedResponse) || (packet instanceof BlePacket.UnregisteredResponse)) {
                    this.this$0._isAuthenticated.setValue(false);
                }
                BuildersKt__Builders_commonKt.launch$default(this.this$0.scope, null, null, new BleManager$gattCallback$1$onCharacteristicChanged$2(this.this$0, packet, null), 3, null);
            }
        };
    }

    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/example/ble/BleManager$Companion;", "", "<init>", "()V", "TAG", "", "SERVICE_UUID", "Ljava/util/UUID;", "getSERVICE_UUID", "()Ljava/util/UUID;", "RX_CHAR_UUID", "getRX_CHAR_UUID", "TX_CHAR_UUID", "getTX_CHAR_UUID", "CCCD_UUID", "getCCCD_UUID", "isDebugConsoleVisible", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "INSTANCE", "Lcom/example/ble/BleManager;", "getInstance", "context", "Landroid/content/Context;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UUID getSERVICE_UUID() {
            return BleManager.SERVICE_UUID;
        }

        public final UUID getRX_CHAR_UUID() {
            return BleManager.RX_CHAR_UUID;
        }

        public final UUID getTX_CHAR_UUID() {
            return BleManager.TX_CHAR_UUID;
        }

        public final UUID getCCCD_UUID() {
            return BleManager.CCCD_UUID;
        }

        public final MutableStateFlow<Boolean> isDebugConsoleVisible() {
            return BleManager.isDebugConsoleVisible;
        }

        public final BleManager getInstance(Context context) {
            BleManager bleManager;
            Intrinsics.checkNotNullParameter(context, "context");
            BlePermissionUtils.INSTANCE.setAppContext(context.getApplicationContext());
            BleManager bleManager2 = BleManager.INSTANCE;
            if (bleManager2 != null) {
                return bleManager2;
            }
            synchronized (this) {
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                bleManager = new BleManager(applicationContext);
                Companion companion = BleManager.INSTANCE;
                BleManager.INSTANCE = bleManager;
            }
            return bleManager;
        }
    }

    static {
        UUID uuidFromString = UUID.fromString("CA113726-49D2-4D7F-A5A0-000000000001");
        Intrinsics.checkNotNullExpressionValue(uuidFromString, "fromString(...)");
        SERVICE_UUID = uuidFromString;
        UUID uuidFromString2 = UUID.fromString("CA113726-49D2-4D7F-A5A0-000000000002");
        Intrinsics.checkNotNullExpressionValue(uuidFromString2, "fromString(...)");
        RX_CHAR_UUID = uuidFromString2;
        UUID uuidFromString3 = UUID.fromString("CA113726-49D2-4D7F-A5A0-000000000003");
        Intrinsics.checkNotNullExpressionValue(uuidFromString3, "fromString(...)");
        TX_CHAR_UUID = uuidFromString3;
        UUID uuidFromString4 = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
        Intrinsics.checkNotNullExpressionValue(uuidFromString4, "fromString(...)");
        CCCD_UUID = uuidFromString4;
        isDebugConsoleVisible = StateFlowKt.MutableStateFlow(false);
    }

    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0019\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\t\u0010\u001f\u001a\u00020\fHÆ\u0003JE\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010!\u001a\u00020\n2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\bHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006%"}, d2 = {"Lcom/example/ble/BleManager$ScannedDevice;", "", "name", "", "address", "state", "Lcom/example/ble/DeviceState;", "rssi", "", "hasServiceUuid", "", "lastSeenTimestamp", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/example/ble/DeviceState;IZJ)V", "getName", "()Ljava/lang/String;", "getAddress", "getState", "()Lcom/example/ble/DeviceState;", "getRssi", "()I", "getHasServiceUuid", "()Z", "getLastSeenTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ScannedDevice {
        public static final int $stable = 0;
        private final String address;
        private final boolean hasServiceUuid;
        private final long lastSeenTimestamp;
        private final String name;
        private final int rssi;
        private final DeviceState state;

        public static /* synthetic */ ScannedDevice copy$default(ScannedDevice scannedDevice, String str, String str2, DeviceState deviceState, int i, boolean z, long j, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = scannedDevice.name;
            }
            if ((i2 & 2) != 0) {
                str2 = scannedDevice.address;
            }
            if ((i2 & 4) != 0) {
                deviceState = scannedDevice.state;
            }
            if ((i2 & 8) != 0) {
                i = scannedDevice.rssi;
            }
            if ((i2 & 16) != 0) {
                z = scannedDevice.hasServiceUuid;
            }
            if ((i2 & 32) != 0) {
                j = scannedDevice.lastSeenTimestamp;
            }
            long j2 = j;
            boolean z2 = z;
            DeviceState deviceState2 = deviceState;
            return scannedDevice.copy(str, str2, deviceState2, i, z2, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getAddress() {
            return this.address;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final DeviceState getState() {
            return this.state;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getRssi() {
            return this.rssi;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getHasServiceUuid() {
            return this.hasServiceUuid;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getLastSeenTimestamp() {
            return this.lastSeenTimestamp;
        }

        public final ScannedDevice copy(String name, String address, DeviceState state, int rssi, boolean hasServiceUuid, long lastSeenTimestamp) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(address, "address");
            Intrinsics.checkNotNullParameter(state, "state");
            return new ScannedDevice(name, address, state, rssi, hasServiceUuid, lastSeenTimestamp);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ScannedDevice)) {
                return false;
            }
            ScannedDevice scannedDevice = (ScannedDevice) other;
            return Intrinsics.areEqual(this.name, scannedDevice.name) && Intrinsics.areEqual(this.address, scannedDevice.address) && this.state == scannedDevice.state && this.rssi == scannedDevice.rssi && this.hasServiceUuid == scannedDevice.hasServiceUuid && this.lastSeenTimestamp == scannedDevice.lastSeenTimestamp;
        }

        public int hashCode() {
            return (((((((((this.name.hashCode() * 31) + this.address.hashCode()) * 31) + this.state.hashCode()) * 31) + Integer.hashCode(this.rssi)) * 31) + Boolean.hashCode(this.hasServiceUuid)) * 31) + Long.hashCode(this.lastSeenTimestamp);
        }

        public String toString() {
            return "ScannedDevice(name=" + this.name + ", address=" + this.address + ", state=" + this.state + ", rssi=" + this.rssi + ", hasServiceUuid=" + this.hasServiceUuid + ", lastSeenTimestamp=" + this.lastSeenTimestamp + ")";
        }

        public ScannedDevice(String name, String address, DeviceState state, int rssi, boolean hasServiceUuid, long lastSeenTimestamp) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(address, "address");
            Intrinsics.checkNotNullParameter(state, "state");
            this.name = name;
            this.address = address;
            this.state = state;
            this.rssi = rssi;
            this.hasServiceUuid = hasServiceUuid;
            this.lastSeenTimestamp = lastSeenTimestamp;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ ScannedDevice(String str, String str2, DeviceState deviceState, int i, boolean z, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            boolean z2;
            long jCurrentTimeMillis;
            if ((i2 & 16) == 0) {
                z2 = z;
            } else {
                z2 = false;
            }
            if ((i2 & 32) == 0) {
                jCurrentTimeMillis = j;
            } else {
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            this(str, str2, deviceState, i, z2, jCurrentTimeMillis);
        }

        public final String getName() {
            return this.name;
        }

        public final String getAddress() {
            return this.address;
        }

        public final DeviceState getState() {
            return this.state;
        }

        public final int getRssi() {
            return this.rssi;
        }

        public final boolean getHasServiceUuid() {
            return this.hasServiceUuid;
        }

        public final long getLastSeenTimestamp() {
            return this.lastSeenTimestamp;
        }
    }

    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0006\u0004\u0005\u0006\u0007\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0006\n\u000b\f\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lcom/example/ble/BleManager$ConnectionState;", "", "<init>", "()V", "Disconnected", "Scanning", "Connecting", "Disconnecting", "Connected", "Error", "Lcom/example/ble/BleManager$ConnectionState$Connected;", "Lcom/example/ble/BleManager$ConnectionState$Connecting;", "Lcom/example/ble/BleManager$ConnectionState$Disconnected;", "Lcom/example/ble/BleManager$ConnectionState$Disconnecting;", "Lcom/example/ble/BleManager$ConnectionState$Error;", "Lcom/example/ble/BleManager$ConnectionState$Scanning;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class ConnectionState {
        public static final int $stable = 0;

        public /* synthetic */ ConnectionState(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: BleManager.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/ble/BleManager$ConnectionState$Disconnected;", "Lcom/example/ble/BleManager$ConnectionState;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Disconnected extends ConnectionState {
            public static final int $stable = 0;
            public static final Disconnected INSTANCE = new Disconnected();

            private Disconnected() {
                super(null);
            }
        }

        private ConnectionState() {
        }

        /* JADX INFO: compiled from: BleManager.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/ble/BleManager$ConnectionState$Scanning;", "Lcom/example/ble/BleManager$ConnectionState;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Scanning extends ConnectionState {
            public static final int $stable = 0;
            public static final Scanning INSTANCE = new Scanning();

            private Scanning() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: BleManager.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/ble/BleManager$ConnectionState$Connecting;", "Lcom/example/ble/BleManager$ConnectionState;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Connecting extends ConnectionState {
            public static final int $stable = 0;
            public static final Connecting INSTANCE = new Connecting();

            private Connecting() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: BleManager.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/ble/BleManager$ConnectionState$Disconnecting;", "Lcom/example/ble/BleManager$ConnectionState;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Disconnecting extends ConnectionState {
            public static final int $stable = 0;
            public static final Disconnecting INSTANCE = new Disconnecting();

            private Disconnecting() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: BleManager.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/example/ble/BleManager$ConnectionState$Connected;", "Lcom/example/ble/BleManager$ConnectionState;", "deviceName", "", "address", "state", "Lcom/example/ble/DeviceState;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/example/ble/DeviceState;)V", "getDeviceName", "()Ljava/lang/String;", "getAddress", "getState", "()Lcom/example/ble/DeviceState;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Connected extends ConnectionState {
            public static final int $stable = 0;
            private final String address;
            private final String deviceName;
            private final DeviceState state;

            public static /* synthetic */ Connected copy$default(Connected connected, String str, String str2, DeviceState deviceState, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = connected.deviceName;
                }
                if ((i & 2) != 0) {
                    str2 = connected.address;
                }
                if ((i & 4) != 0) {
                    deviceState = connected.state;
                }
                return connected.copy(str, str2, deviceState);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getDeviceName() {
                return this.deviceName;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getAddress() {
                return this.address;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final DeviceState getState() {
                return this.state;
            }

            public final Connected copy(String deviceName, String address, DeviceState state) {
                Intrinsics.checkNotNullParameter(deviceName, "deviceName");
                Intrinsics.checkNotNullParameter(address, "address");
                Intrinsics.checkNotNullParameter(state, "state");
                return new Connected(deviceName, address, state);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Connected)) {
                    return false;
                }
                Connected connected = (Connected) other;
                return Intrinsics.areEqual(this.deviceName, connected.deviceName) && Intrinsics.areEqual(this.address, connected.address) && this.state == connected.state;
            }

            public int hashCode() {
                return (((this.deviceName.hashCode() * 31) + this.address.hashCode()) * 31) + this.state.hashCode();
            }

            public String toString() {
                return "Connected(deviceName=" + this.deviceName + ", address=" + this.address + ", state=" + this.state + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Connected(String deviceName, String address, DeviceState state) {
                super(null);
                Intrinsics.checkNotNullParameter(deviceName, "deviceName");
                Intrinsics.checkNotNullParameter(address, "address");
                Intrinsics.checkNotNullParameter(state, "state");
                this.deviceName = deviceName;
                this.address = address;
                this.state = state;
            }

            public final String getAddress() {
                return this.address;
            }

            public final String getDeviceName() {
                return this.deviceName;
            }

            public final DeviceState getState() {
                return this.state;
            }
        }

        /* JADX INFO: compiled from: BleManager.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/example/ble/BleManager$ConnectionState$Error;", "Lcom/example/ble/BleManager$ConnectionState;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Error extends ConnectionState {
            public static final int $stable = 0;
            private final String message;

            public static /* synthetic */ Error copy$default(Error error, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = error.message;
                }
                return error.copy(str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final Error copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new Error(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.message, ((Error) other).message);
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            public String toString() {
                return "Error(message=" + this.message + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Error(String message) {
                super(null);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }

            public final String getMessage() {
                return this.message;
            }
        }
    }

    private final BluetoothManager getBluetoothManager() {
        return (BluetoothManager) this.bluetoothManager.getValue();
    }

    static final BluetoothManager bluetoothManager_delegate$lambda$0(BleManager this$0) {
        Object systemService = this$0.context.getSystemService("bluetooth");
        if (systemService instanceof BluetoothManager) {
            return (BluetoothManager) systemService;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BluetoothAdapter getBluetoothAdapter() {
        BluetoothAdapter adapter;
        BluetoothManager bluetoothManager = getBluetoothManager();
        return (bluetoothManager == null || (adapter = bluetoothManager.getAdapter()) == null) ? BluetoothAdapter.getDefaultAdapter() : adapter;
    }

    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/example/ble/BleManager$DebugLog;", "", "timestamp", "", "message", "", "isError", "", "isRx", "isTx", "<init>", "(JLjava/lang/String;ZZZ)V", "getTimestamp", "()J", "getMessage", "()Ljava/lang/String;", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class DebugLog {
        public static final int $stable = 0;
        private final boolean isError;
        private final boolean isRx;
        private final boolean isTx;
        private final String message;
        private final long timestamp;

        public static /* synthetic */ DebugLog copy$default(DebugLog debugLog, long j, String str, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if ((i & 1) != 0) {
                j = debugLog.timestamp;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                str = debugLog.message;
            }
            String str2 = str;
            if ((i & 4) != 0) {
                z = debugLog.isError;
            }
            boolean z4 = z;
            if ((i & 8) != 0) {
                z2 = debugLog.isRx;
            }
            boolean z5 = z2;
            if ((i & 16) != 0) {
                z3 = debugLog.isTx;
            }
            return debugLog.copy(j2, str2, z4, z5, z3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getTimestamp() {
            return this.timestamp;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsError() {
            return this.isError;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsRx() {
            return this.isRx;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsTx() {
            return this.isTx;
        }

        public final DebugLog copy(long timestamp, String message, boolean isError, boolean isRx, boolean isTx) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new DebugLog(timestamp, message, isError, isRx, isTx);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DebugLog)) {
                return false;
            }
            DebugLog debugLog = (DebugLog) other;
            return this.timestamp == debugLog.timestamp && Intrinsics.areEqual(this.message, debugLog.message) && this.isError == debugLog.isError && this.isRx == debugLog.isRx && this.isTx == debugLog.isTx;
        }

        public int hashCode() {
            return (((((((Long.hashCode(this.timestamp) * 31) + this.message.hashCode()) * 31) + Boolean.hashCode(this.isError)) * 31) + Boolean.hashCode(this.isRx)) * 31) + Boolean.hashCode(this.isTx);
        }

        public String toString() {
            return "DebugLog(timestamp=" + this.timestamp + ", message=" + this.message + ", isError=" + this.isError + ", isRx=" + this.isRx + ", isTx=" + this.isTx + ")";
        }

        public DebugLog(long timestamp, String message, boolean isError, boolean isRx, boolean isTx) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.timestamp = timestamp;
            this.message = message;
            this.isError = isError;
            this.isRx = isRx;
            this.isTx = isTx;
        }

        public /* synthetic */ DebugLog(long j, String str, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, str, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? false : z3);
        }

        public final String getMessage() {
            return this.message;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public final boolean isError() {
            return this.isError;
        }

        public final boolean isRx() {
            return this.isRx;
        }

        public final boolean isTx() {
            return this.isTx;
        }
    }

    public final StateFlow<List<DebugLog>> getDebugLogs() {
        return this.debugLogs;
    }

    public final StateFlow<Integer> getCurrentRssi() {
        return this.currentRssi;
    }

    public static /* synthetic */ void addDebugLog$default(BleManager bleManager, String str, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        if ((i & 8) != 0) {
            z3 = false;
        }
        bleManager.addDebugLog(str, z, z2, z3);
    }

    public final void addDebugLog(String message, boolean isError, boolean isRx, boolean isTx) {
        Intrinsics.checkNotNullParameter(message, "message");
        DebugLog log = new DebugLog(System.currentTimeMillis(), message, isError, isRx, isTx);
        this._debugLogs.setValue(CollectionsKt.takeLast(CollectionsKt.plus((Collection<? extends DebugLog>) this._debugLogs.getValue(), log), 1000));
    }

    public final void requestRssi() {
        BluetoothGatt bluetoothGatt;
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.BLUETOOTH_CONNECT") != 0 || (bluetoothGatt = this.bluetoothGatt) == null) {
            return;
        }
        bluetoothGatt.readRemoteRssi();
    }

    public final void sendRawPacket(String payload) {
        int writeType;
        Intrinsics.checkNotNullParameter(payload, "payload");
        BluetoothGatt gatt = this.bluetoothGatt;
        BluetoothGattCharacteristic rx = this.rxCharacteristic;
        if (gatt == null || rx == null) {
            String target = this.lastTargetAddress;
            if (target != null) {
                addDebugLog$default(this, "Not connected. Connecting to " + target + " and queuing packet...", false, false, false, 12, null);
                this.queuedRawPacket = payload;
                connect(target);
                return;
            }
            addDebugLog$default(this, "Cannot send: Not connected or RX missing and no target address known", true, false, false, 12, null);
            return;
        }
        addDebugLog$default(this, "Sent raw to RX: " + payload, false, false, true, 6, null);
        if ((rx.getProperties() & 4) != 0) {
            writeType = 1;
        } else {
            writeType = 2;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            byte[] bytes = payload.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            gatt.writeCharacteristic(rx, bytes, writeType);
        } else {
            byte[] bytes2 = payload.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            rx.setValue(bytes2);
            rx.setWriteType(writeType);
            gatt.writeCharacteristic(rx);
        }
    }

    public final StateFlow<ConnectionState> getConnectionState() {
        return this.connectionState;
    }

    public final StateFlow<DeviceState> getDeviceState() {
        return this.deviceState;
    }

    public final StateFlow<List<ScannedDevice>> getScannedDevices() {
        return this.scannedDevices;
    }

    public final SharedFlow<BlePacket> getIncomingPackets() {
        return this.incomingPackets;
    }

    public final StateFlow<String> getPendingAction() {
        return this.pendingAction;
    }

    public final StateFlow<Boolean> isAuthenticated() {
        return this.isAuthenticated;
    }

    public final void setAuthenticated(boolean auth) {
        this._isAuthenticated.setValue(Boolean.valueOf(auth));
        CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
    }

    public final void setPendingAction(String action) {
        this._pendingAction.setValue(action);
    }

    public final StateFlow<String> getLastTxResponse() {
        return this.lastTxResponse;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0016  */
    private final boolean refreshDeviceCache(BluetoothGatt gatt) {
        Method method;
        if (gatt != null) {
            try {
                Class<?> cls = gatt.getClass();
                if (cls != null) {
                    method = cls.getMethod("refresh", new Class[0]);
                } else {
                    method = null;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            method = null;
        }
        Object objInvoke = method != null ? method.invoke(gatt, new Object[0]) : null;
        Boolean bool = objInvoke instanceof Boolean ? (Boolean) objInvoke : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static /* synthetic */ void teardownGatt$default(BleManager bleManager, BluetoothGatt bluetoothGatt, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            bluetoothGatt = null;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        bleManager.teardownGatt(bluetoothGatt, z);
    }

    /* JADX WARN: Code duplicated, block: B:41:0x00df A[Catch: all -> 0x011d, TryCatch #4 {, blocks: (B:3:0x0001, B:5:0x0007, B:6:0x000a, B:8:0x000e, B:14:0x0019, B:16:0x003c, B:19:0x0042, B:22:0x004b, B:24:0x006c, B:27:0x0073, B:30:0x007c, B:36:0x00bc, B:39:0x00c1, B:41:0x00df, B:44:0x00e9, B:46:0x0112, B:48:0x0118, B:43:0x00e7, B:34:0x009f), top: B:62:0x0001, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00e7 A[Catch: all -> 0x011d, TryCatch #4 {, blocks: (B:3:0x0001, B:5:0x0007, B:6:0x000a, B:8:0x000e, B:14:0x0019, B:16:0x003c, B:19:0x0042, B:22:0x004b, B:24:0x006c, B:27:0x0073, B:30:0x007c, B:36:0x00bc, B:39:0x00c1, B:41:0x00df, B:44:0x00e9, B:46:0x0112, B:48:0x0118, B:43:0x00e7, B:34:0x009f), top: B:62:0x0001, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x0112 A[Catch: all -> 0x011d, TryCatch #4 {, blocks: (B:3:0x0001, B:5:0x0007, B:6:0x000a, B:8:0x000e, B:14:0x0019, B:16:0x003c, B:19:0x0042, B:22:0x004b, B:24:0x006c, B:27:0x0073, B:30:0x007c, B:36:0x00bc, B:39:0x00c1, B:41:0x00df, B:44:0x00e9, B:46:0x0112, B:48:0x0118, B:43:0x00e7, B:34:0x009f), top: B:62:0x0001, inners: #0, #1 }] */
    public final synchronized void teardownGatt(BluetoothGatt gattToClose, boolean triggerScan) {
        Job job = this.disconnectJob;
        boolean z = true;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.disconnectJob = null;
        BluetoothGatt gatt = gattToClose == null ? this.bluetoothGatt : gattToClose;
        if (gatt == null) {
            z = false;
        }
        Log.d(TAG, "teardownGatt invoked. gatt=" + z + ", triggerScan=" + triggerScan);
        if (gatt != null) {
            try {
                BluetoothGattCharacteristic bluetoothGattCharacteristic = this.txCharacteristic;
                if (bluetoothGattCharacteristic != null) {
                    try {
                        Boolean.valueOf(gatt.setCharacteristicNotification(bluetoothGattCharacteristic, false));
                    } catch (Exception e) {
                        Integer.valueOf(Log.w(TAG, "Could not unsubscribe notification: " + e.getMessage()));
                    }
                }
                BluetoothGattCharacteristic bluetoothGattCharacteristic2 = this.rxCharacteristic;
                if (bluetoothGattCharacteristic2 != null) {
                    try {
                        Boolean.valueOf(gatt.setCharacteristicNotification(bluetoothGattCharacteristic2, false));
                    } catch (Exception e2) {
                        Integer.valueOf(Log.w(TAG, "Could not unsubscribe notification: " + e2.getMessage()));
                    }
                    try {
                        gatt.close();
                    } catch (Exception e3) {
                        Log.e(TAG, "Error closing GATT: " + e3.getMessage());
                    }
                    if (gattToClose != null || Intrinsics.areEqual(gattToClose, this.bluetoothGatt)) {
                        this.bluetoothGatt = null;
                    }
                    this.rxCharacteristic = null;
                    this.txCharacteristic = null;
                    this.queuedRawPacket = null;
                    this._isAuthenticated.setValue(false);
                    this._currentRssi.setValue(0);
                    this._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
                    CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
                    if (triggerScan && isBluetoothEnabled()) {
                        startScan(false);
                    }
                } else {
                    gatt.close();
                    if (gattToClose != null) {
                        this.bluetoothGatt = null;
                    } else {
                        this.bluetoothGatt = null;
                    }
                    this.rxCharacteristic = null;
                    this.txCharacteristic = null;
                    this.queuedRawPacket = null;
                    this._isAuthenticated.setValue(false);
                    this._currentRssi.setValue(0);
                    this._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
                    CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
                    if (triggerScan) {
                        startScan(false);
                    }
                }
            } catch (Exception e4) {
                Log.w(TAG, "Error cleaning notifications: " + e4.getMessage());
            }
        } else {
            if (gattToClose != null) {
                this.bluetoothGatt = null;
            } else {
                this.bluetoothGatt = null;
            }
            this.rxCharacteristic = null;
            this.txCharacteristic = null;
            this.queuedRawPacket = null;
            this._isAuthenticated.setValue(false);
            this._currentRssi.setValue(0);
            this._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
            CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
            if (triggerScan) {
                startScan(false);
            }
        }
        throw th;
    }

    public final void clearBleObject() {
        clearTransactionState();
        teardownGatt(null, isBluetoothEnabled());
    }

    public final void clearTransactionState() {
        this.queuedRawPacket = null;
        this._lastTxResponse.setValue("");
        this._pendingAction.setValue(null);
    }

    public static /* synthetic */ void resetBleStateOnStartup$default(BleManager bleManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        bleManager.resetBleStateOnStartup(z);
    }

    public final void resetBleStateOnStartup(boolean force) {
        boolean isConnected = (this._connectionState.getValue() instanceof ConnectionState.Connected) || this.bluetoothGatt != null;
        boolean isServiceRunning = CalisBleService.INSTANCE.isServiceRunning();
        if (force || (!isConnected && !isServiceRunning)) {
            Log.d(TAG, "resetBleStateOnStartup: Fresh application startup detected. Clearing BLE state and cached objects.");
            teardownGatt(this.bluetoothGatt, false);
            this._scannedDevices.setValue(CollectionsKt.emptyList());
            this._pendingAction.setValue(null);
            this._isAuthenticated.setValue(false);
            this._currentRssi.setValue(0);
            setExplicitlyDisconnected(false);
            this._deviceState.setValue(DeviceState.FACTORY_MODE);
            this._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
            return;
        }
        Log.d(TAG, "resetBleStateOnStartup: App is running in background or BLE is connected. Preserving active BLE state.");
    }

    /* JADX INFO: renamed from: isSimulatedMode, reason: from getter */
    public final boolean getIsSimulatedMode() {
        return this.isSimulatedMode;
    }

    public final void setSimulatedMode(boolean z) {
        this.isSimulatedMode = z;
    }

    public final String getLastTargetAddress() {
        return this.lastTargetAddress;
    }

    public final void setLastTargetAddress(String str) {
        this.lastTargetAddress = str;
    }

    public final String getQueuedRawPacket() {
        return this.queuedRawPacket;
    }

    /* JADX INFO: renamed from: isExplicitlyDisconnected, reason: from getter */
    public final boolean getIsExplicitlyDisconnected() {
        return this.isExplicitlyDisconnected;
    }

    public final void setExplicitlyDisconnected(boolean value) {
        this.isExplicitlyDisconnected = value;
        if (value) {
            this._isAuthenticated.setValue(false);
        }
    }

    public final void onBluetoothStateChanged(int state) {
        switch (state) {
            case 10:
            case 13:
                Log.d(TAG, "[BLE] Bluetooth turned OFF (" + state + "). Resetting scanning & GATT state via teardownGatt.");
                Job job = this.retryScanJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                this.retryScanJob = null;
                this.isScanning = false;
                teardownGatt$default(this, null, false, 1, null);
                this._scannedDevices.setValue(CollectionsKt.emptyList());
                CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
                break;
            case 12:
                Log.d(TAG, "[BLE] Bluetooth turned ON (STATE_ON). Scheduling scan restart...");
                this.isScanning = false;
                if (this._connectionState.getValue() instanceof ConnectionState.Scanning) {
                    this._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
                }
                Job job2 = this.retryScanJob;
                if (job2 != null) {
                    Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                }
                this.retryScanJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C07521(null), 3, null);
                break;
        }
    }

    /* JADX INFO: renamed from: com.example.ble.BleManager$onBluetoothStateChanged$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.BleManager$onBluetoothStateChanged$1", f = "BleManager.kt", i = {}, l = {343}, m = "invokeSuspend", n = {}, s = {})
    static final class C07521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C07521(Continuation<? super C07521> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BleManager.this.new C07521(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(350L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (BleManager.this.isBluetoothEnabled() && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Connected) && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Connecting) && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Disconnecting)) {
                BleManager.this.startScan(true);
            }
            return Unit.INSTANCE;
        }
    }

    public final boolean isBluetoothSupported() {
        return getBluetoothAdapter() != null;
    }

    public final boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public static /* synthetic */ void startScan$default(BleManager bleManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        bleManager.startScan(z);
    }

    public final void startScan(boolean forceRestart) {
        BluetoothAdapter adapter = getBluetoothAdapter();
        if (adapter == null || !adapter.isEnabled()) {
            Log.d(TAG, "Bluetooth is disabled or adapter is null. Cannot start scan.");
            this.isScanning = false;
            if (this._connectionState.getValue() instanceof ConnectionState.Scanning) {
                this._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
                return;
            }
            return;
        }
        if ((this._connectionState.getValue() instanceof ConnectionState.Connected) || (this._connectionState.getValue() instanceof ConnectionState.Connecting) || (this._connectionState.getValue() instanceof ConnectionState.Disconnecting)) {
            Log.d(TAG, "Already connected/connecting/disconnecting. Skipping scan to protect active state.");
            return;
        }
        if (!forceRestart && this.isScanning && (this._connectionState.getValue() instanceof ConnectionState.Scanning)) {
            Log.d(TAG, "Scan is already active. Preserving continuous scan.");
            return;
        }
        boolean z = true;
        try {
            try {
                BluetoothLeScanner scanner = adapter.getBluetoothLeScanner();
                if (scanner == null) {
                    Log.d(TAG, "BluetoothLeScanner is null. Retrying scan in 400ms...");
                    this.isScanning = false;
                    Job job = this.retryScanJob;
                    if (job != null) {
                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    }
                    this.retryScanJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C07531(null), 3, null);
                    return;
                }
                try {
                    scanner.stopScan(this.scanCallback);
                } catch (Exception e) {
                }
                this._connectionState.setValue(ConnectionState.Scanning.INSTANCE);
                this.isScanning = true;
                long now = System.currentTimeMillis();
                MutableStateFlow<List<ScannedDevice>> mutableStateFlow = this._scannedDevices;
                Iterable value = this._scannedDevices.getValue();
                Collection arrayList = new ArrayList();
                for (Object obj : value) {
                    if (now - ((ScannedDevice) obj).getLastSeenTimestamp() <= 60000 ? z : false) {
                        arrayList.add(obj);
                    }
                    z = true;
                }
                mutableStateFlow.setValue((List) arrayList);
                ScanSettings settings = new ScanSettings.Builder().setScanMode(2).setReportDelay(0L).build();
                scanner.startScan((List<ScanFilter>) null, settings, this.scanCallback);
                Log.d(TAG, "Started continuous BLE scan with SCAN_MODE_LOW_LATENCY");
            } catch (Exception e2) {
                Log.e(TAG, "Scan failed", e2);
                this.isScanning = false;
                this._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
                Job job2 = this.retryScanJob;
                if (job2 != null) {
                    Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                }
                this.retryScanJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass4(null), 3, null);
            }
        } catch (IllegalStateException e3) {
            Log.w(TAG, "BluetoothLeScanner not ready (IllegalStateException). Retrying in 500ms...", e3);
            this.isScanning = false;
            Job job3 = this.retryScanJob;
            if (job3 != null) {
                Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
            }
            this.retryScanJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass3(null), 3, null);
        } catch (SecurityException e4) {
            Log.e(TAG, "Scan failed due to missing permission", e4);
            this.isScanning = false;
            this._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.example.ble.BleManager$startScan$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.BleManager$startScan$1", f = "BleManager.kt", i = {}, l = {397}, m = "invokeSuspend", n = {}, s = {})
    static final class C07531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C07531(Continuation<? super C07531> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BleManager.this.new C07531(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(400L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (BleManager.this.isBluetoothEnabled() && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Connected) && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Connecting) && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Disconnecting)) {
                BleManager.this.startScan(true);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.example.ble.BleManager$startScan$3, reason: invalid class name */
    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.BleManager$startScan$3", f = "BleManager.kt", i = {}, l = {436}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BleManager.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(500L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (BleManager.this.isBluetoothEnabled() && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Connected) && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Connecting) && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Disconnecting)) {
                BleManager.this.startScan(true);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.example.ble.BleManager$startScan$4, reason: invalid class name */
    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.BleManager$startScan$4", f = "BleManager.kt", i = {}, l = {451}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BleManager.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (BleManager.this.isBluetoothEnabled() && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Connected) && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Connecting) && !(BleManager.this._connectionState.getValue() instanceof ConnectionState.Disconnecting)) {
                BleManager.this.startScan(true);
            }
            return Unit.INSTANCE;
        }
    }

    public final void stopScan() {
        BluetoothLeScanner bluetoothLeScanner;
        this.isScanning = false;
        Job job = this.retryScanJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.retryScanJob = null;
        try {
            BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
            if (bluetoothAdapter != null && (bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner()) != null) {
                bluetoothLeScanner.stopScan(this.scanCallback);
            }
            if (this._connectionState.getValue() instanceof ConnectionState.Scanning) {
                this._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error stopping scan", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasManufacturerDataInBytes(byte[] rawBytes) {
        int length;
        if (rawBytes != null) {
            if (!(rawBytes.length == 0)) {
                int idx = 0;
                while (idx < rawBytes.length - 1 && (length = rawBytes[idx] & UByte.MAX_VALUE) != 0 && idx + length < rawBytes.length) {
                    int type = rawBytes[idx + 1] & 255;
                    if (type == 255) {
                        return true;
                    }
                    idx += length + 1;
                }
                return false;
            }
        }
        return false;
    }

    public final void connect(String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        ConnectionState currentConn = this._connectionState.getValue();
        if ((currentConn instanceof ConnectionState.Connected) && ((StringsKt.equals(((ConnectionState.Connected) currentConn).getAddress(), address, true) || StringsKt.startsWith$default(address, "VIRTUAL_", false, 2, (Object) null)) && (this.bluetoothGatt != null || this.isSimulatedMode))) {
            Log.d("CALIS", "connect called but already connected to " + address + ". Preserving active connection.");
            return;
        }
        if ((currentConn instanceof ConnectionState.Connecting) && ((StringsKt.equals(this.lastTargetAddress, address, true) || StringsKt.startsWith$default(address, "VIRTUAL_", false, 2, (Object) null)) && (this.bluetoothGatt != null || this.isSimulatedMode))) {
            Log.d("CALIS", "connect called but already connecting to " + address + ". Preserving connection attempt.");
            return;
        }
        Job job = this.disconnectJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.disconnectJob = null;
        this.lastTargetAddress = address;
        setExplicitlyDisconnected(false);
        this._isAuthenticated.setValue(false);
        this._deviceState.setValue(DeviceState.SESSION_INACTIVE);
        CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
        Log.d("CALIS", "connect called with address: " + address + ", isSimulatedMode=" + this.isSimulatedMode);
        if (this.isSimulatedMode || StringsKt.startsWith$default(address, "VIRTUAL_", false, 2, (Object) null)) {
            connectVirtual();
            return;
        }
        try {
            try {
                if (this.isScanning) {
                    stopScan();
                }
                BluetoothGatt oldGatt = this.bluetoothGatt;
                if (oldGatt != null) {
                    try {
                        oldGatt.close();
                    } catch (Exception e) {
                    }
                    this.bluetoothGatt = null;
                }
                this.rxCharacteristic = null;
                this.txCharacteristic = null;
                BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
                BluetoothDevice device = bluetoothAdapter != null ? bluetoothAdapter.getRemoteDevice(address) : null;
                if (device == null) {
                    this._connectionState.setValue(new ConnectionState.Error("Device not found: " + address));
                    CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
                } else {
                    addDebugLog$default(this, "Connecting to " + address + "...", false, false, false, 14, null);
                    this._connectionState.setValue(ConnectionState.Connecting.INSTANCE);
                    CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
                    this.bluetoothGatt = device.connectGatt(this.context, false, this.gattCallback, 2);
                }
            } catch (Exception e2) {
                Log.e(TAG, "Connection failed", e2);
                this._connectionState.setValue(new ConnectionState.Error("Connection failed: " + e2.getMessage()));
                CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
            }
        } catch (SecurityException e3) {
            Log.e(TAG, "Connection failed due to missing permission", e3);
            this._connectionState.setValue(new ConnectionState.Error("Missing BLE permissions"));
            CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
        }
    }

    public final void clearScannedDevices() {
        this._scannedDevices.setValue(CollectionsKt.emptyList());
        CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
    }

    public final void disconnect() {
        setExplicitlyDisconnected(true);
        this._isAuthenticated.setValue(false);
        this._deviceState.setValue(DeviceState.SESSION_INACTIVE);
        BluetoothGatt gatt = this.bluetoothGatt;
        boolean wasActive = (this._connectionState.getValue() instanceof ConnectionState.Connected) || (this._connectionState.getValue() instanceof ConnectionState.Connecting) || gatt != null;
        if (!wasActive) {
            teardownGatt(null, true);
            return;
        }
        this._connectionState.setValue(ConnectionState.Disconnecting.INSTANCE);
        CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
        Log.d(TAG, "Disconnect requested. isSimulatedMode=" + this.isSimulatedMode);
        if (gatt != null) {
            try {
                gatt.disconnect();
            } catch (Exception e) {
                Log.e(TAG, "Error requesting GATT disconnect: " + e.getMessage());
            }
        }
        Job job = this.disconnectJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.disconnectJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(gatt, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.ble.BleManager$disconnect$1, reason: invalid class name */
    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.BleManager$disconnect$1", f = "BleManager.kt", i = {}, l = {780}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BluetoothGatt $gatt;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(BluetoothGatt bluetoothGatt, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$gatt = bluetoothGatt;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BleManager.this.new AnonymousClass1(this.$gatt, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(1200L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Log.w(BleManager.TAG, "Disconnect callback timed out. Executing failsafe teardownGatt.");
            BleManager.this.teardownGatt(this.$gatt, true);
            return Unit.INSTANCE;
        }
    }

    private final void updateDeviceStateForPacket(BlePacket packet) {
        if (packet instanceof BlePacket.StartSessionCommand) {
            this._deviceState.setValue(DeviceState.SESSION_ACTIVE_OPEN);
            CalisBleService.INSTANCE.setSessionActive(this.context, true);
        } else if (packet instanceof BlePacket.EndSessionCommand) {
            this._deviceState.setValue(DeviceState.SESSION_INACTIVE);
            CalisBleService.INSTANCE.setSessionActive(this.context, false);
        } else if (packet instanceof BlePacket.UnregCommand) {
            this._deviceState.setValue(DeviceState.FACTORY_MODE);
            this._isAuthenticated.setValue(false);
            CalisBleService.INSTANCE.setSessionActive(this.context, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void sendPacket$default(BleManager bleManager, BlePacket blePacket, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        bleManager.sendPacket(blePacket, function1);
    }

    public final void sendPacket(BlePacket packet, Function1<? super Boolean, Unit> onComplete) {
        String strEncode;
        int i;
        Intrinsics.checkNotNullParameter(packet, "packet");
        if (packet instanceof BlePacket.RegCommand) {
            strEncode = ((BlePacket.RegCommand) packet).encode();
        } else if (packet instanceof BlePacket.AuthCommand) {
            strEncode = ((BlePacket.AuthCommand) packet).encode();
        } else if (packet instanceof BlePacket.StartSessionCommand) {
            strEncode = ((BlePacket.StartSessionCommand) packet).encode();
        } else if (packet instanceof BlePacket.EndSessionCommand) {
            strEncode = ((BlePacket.EndSessionCommand) packet).encode();
        } else if (packet instanceof BlePacket.TaskCommand) {
            strEncode = ((BlePacket.TaskCommand) packet).encode();
        } else if (packet instanceof BlePacket.UnregCommand) {
            strEncode = ((BlePacket.UnregCommand) packet).encode();
        } else if (packet instanceof BlePacket.StudentRegRequest) {
            strEncode = ((BlePacket.StudentRegRequest) packet).encodeForDevice();
        } else if (packet instanceof BlePacket.CheckInRequest) {
            strEncode = ((BlePacket.CheckInRequest) packet).encodeForDevice();
        } else if (packet instanceof BlePacket.TaskSubmissionRequest) {
            strEncode = ((BlePacket.TaskSubmissionRequest) packet).encodeForDevice();
        } else if (packet instanceof BlePacket.RequestUpdateRequest) {
            strEncode = ((BlePacket.RequestUpdateRequest) packet).encodeForDevice();
        } else if (packet instanceof BlePacket.RegApprovedResponse) {
            strEncode = ((BlePacket.RegApprovedResponse) packet).encode();
        } else if (packet instanceof BlePacket.RegRejectedResponse) {
            strEncode = ((BlePacket.RegRejectedResponse) packet).encode();
        } else if (packet instanceof BlePacket.AtdAcceptedResponse) {
            strEncode = ((BlePacket.AtdAcceptedResponse) packet).encode();
        } else if (packet instanceof BlePacket.AtdRejectedResponse) {
            strEncode = ((BlePacket.AtdRejectedResponse) packet).encode();
        } else if (packet instanceof BlePacket.MarkedResponse) {
            strEncode = ((BlePacket.MarkedResponse) packet).encode();
        } else if (packet instanceof BlePacket.SchedResponse) {
            strEncode = ((BlePacket.SchedResponse) packet).encode();
        } else {
            strEncode = packet instanceof BlePacket.DoneResponse ? ((BlePacket.DoneResponse) packet).encode() : "";
        }
        String payload = strEncode;
        if (payload.length() == 0) {
            if (onComplete != null) {
                onComplete.invoke(false);
                return;
            }
            return;
        }
        Log.d(TAG, "Sending packet payload: " + payload + " (isSimulatedMode=" + this.isSimulatedMode + ")");
        if (this.isSimulatedMode) {
            if (onComplete != null) {
                onComplete.invoke(true);
            }
            handleVirtualPacket(payload, packet);
            return;
        }
        BluetoothGatt gatt = this.bluetoothGatt;
        BluetoothGattCharacteristic rx = this.rxCharacteristic;
        if (gatt == null || rx == null) {
            Log.e(TAG, "Cannot send packet '" + payload + "': gatt=" + gatt + ", rx=" + rx);
            if (onComplete != null) {
                onComplete.invoke(false);
                return;
            }
            return;
        }
        if ((rx.getProperties() & 4) != 0) {
            i = 1;
        } else {
            i = 2;
        }
        int writeType = i;
        if (Build.VERSION.SDK_INT >= 33) {
            byte[] bytes = payload.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            int status = gatt.writeCharacteristic(rx, bytes, writeType);
            boolean success = status == 0;
            Log.d(TAG, "Sent packet to RX: '" + payload + "' status=" + status + " success=" + success + " writeType=" + writeType);
            addDebugLog$default(this, "TX: " + payload, false, false, true, 6, null);
            if (success) {
                updateDeviceStateForPacket(packet);
            }
            if (onComplete != null) {
                onComplete.invoke(Boolean.valueOf(success));
                return;
            }
            return;
        }
        byte[] bytes2 = payload.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
        rx.setValue(bytes2);
        rx.setWriteType(writeType);
        boolean success2 = gatt.writeCharacteristic(rx);
        Log.d(TAG, "Sent packet to RX: '" + payload + "' success=" + success2 + " writeType=" + writeType);
        addDebugLog$default(this, "TX: " + payload, false, false, true, 6, null);
        if (success2) {
            updateDeviceStateForPacket(packet);
        }
        if (onComplete != null) {
            onComplete.invoke(Boolean.valueOf(success2));
        }
    }

    public final void setVirtualClassroomInfo(String classroomName, boolean isBound) {
        Intrinsics.checkNotNullParameter(classroomName, "classroomName");
        if (!StringsKt.isBlank(classroomName)) {
            this.virtualClassroomName = classroomName;
        }
        if (isBound) {
            if (this._deviceState.getValue() == DeviceState.FACTORY_MODE || this._deviceState.getValue() == DeviceState.REGISTERING) {
                this._deviceState.setValue(DeviceState.SESSION_INACTIVE);
            }
        }
    }

    private final void enableVirtualMode() {
        String str;
        this.isSimulatedMode = true;
        if (this._deviceState.getValue() == DeviceState.FACTORY_MODE || this._deviceState.getValue() == DeviceState.REGISTERING) {
            str = "Setup Calis (Virtual)";
        } else {
            str = this.virtualClassroomName;
        }
        String currentName = str;
        ScannedDevice currentDev = new ScannedDevice(currentName, "VIRTUAL_CALIS_DEVICE", this._deviceState.getValue(), -50, true, 0L, 32, null);
        this._scannedDevices.setValue(CollectionsKt.listOf(currentDev));
        this._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
        startVirtualHeartbeat();
    }

    /* JADX INFO: renamed from: com.example.ble.BleManager$startVirtualHeartbeat$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.BleManager$startVirtualHeartbeat$1", f = "BleManager.kt", i = {0}, l = {1137}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class C07541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C07541(Continuation<? super C07541> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07541 c07541 = BleManager.this.new C07541(continuation);
            c07541.L$0 = obj;
            return c07541;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x002e  */
        /* JADX WARN: Code duplicated, block: B:12:0x003e A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:15:0x0047  */
        /* JADX WARN: Code duplicated, block: B:18:0x007a A[LOOP:0: B:16:0x0074->B:18:0x007a, LOOP_END] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x003c -> B:13:0x003f). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:15:0x0047
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r25) {
            /*
                r24 = this;
                r0 = r24
                java.lang.Object r1 = r0.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r3 = r0.label
                switch(r3) {
                    case 0: goto L20;
                    case 1: goto L17;
                    default: goto Lf;
                }
            Lf:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r2)
                throw r0
            L17:
                kotlin.ResultKt.throwOnFailure(r25)
                r4 = r0
                r3 = r2
                r2 = r1
                r1 = r25
                goto L3f
            L20:
                kotlin.ResultKt.throwOnFailure(r25)
                r4 = r0
                r3 = r2
                r2 = r1
                r1 = r25
            L28:
                boolean r5 = kotlinx.coroutines.CoroutineScopeKt.isActive(r2)
                if (r5 == 0) goto Lb0
                r5 = r4
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r4.L$0 = r2
                r6 = 1
                r4.label = r6
                r6 = 2000(0x7d0, double:9.88E-321)
                java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r6, r5)
                if (r5 != r3) goto L3f
                return r3
            L3f:
                com.example.ble.BleManager r5 = com.example.ble.BleManager.this
                boolean r5 = r5.getIsSimulatedMode()
                if (r5 == 0) goto Lac
                long r12 = java.lang.System.currentTimeMillis()
                com.example.ble.BleManager r5 = com.example.ble.BleManager.this
                kotlinx.coroutines.flow.MutableStateFlow r5 = com.example.ble.BleManager.access$get_scannedDevices$p(r5)
                com.example.ble.BleManager r6 = com.example.ble.BleManager.this
                kotlinx.coroutines.flow.MutableStateFlow r6 = com.example.ble.BleManager.access$get_scannedDevices$p(r6)
                java.lang.Object r6 = r6.getValue()
                java.lang.Iterable r6 = (java.lang.Iterable) r6
                r16 = 0
                java.util.ArrayList r7 = new java.util.ArrayList
                r8 = 10
                int r8 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r6, r8)
                r7.<init>(r8)
                java.util.Collection r7 = (java.util.Collection) r7
                r17 = r6
                r18 = 0
                java.util.Iterator r19 = r17.iterator()
            L74:
                boolean r8 = r19.hasNext()
                if (r8 == 0) goto La3
                java.lang.Object r20 = r19.next()
                r8 = r6
                r6 = r20
                com.example.ble.BleManager$ScannedDevice r6 = (com.example.ble.BleManager.ScannedDevice) r6
                r21 = 0
                r14 = 31
                r15 = 0
                r9 = r7
                r7 = 0
                r10 = r8
                r8 = 0
                r11 = r9
                r9 = 0
                r22 = r10
                r10 = 0
                r23 = r11
                r11 = 0
                r0 = r23
                com.example.ble.BleManager$ScannedDevice r6 = com.example.ble.BleManager.ScannedDevice.copy$default(r6, r7, r8, r9, r10, r11, r12, r14, r15)
                r0.add(r6)
                r7 = r0
                r6 = r22
                r0 = r24
                goto L74
            La3:
                r22 = r6
                r0 = r7
                java.util.List r0 = (java.util.List) r0
                r5.setValue(r0)
            Lac:
                r0 = r24
                goto L28
            Lb0:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.ble.BleManager.C07541.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final void startVirtualHeartbeat() {
        Job job = this.virtualHeartbeatJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.virtualHeartbeatJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C07541(null), 3, null);
    }

    private final void connectVirtual() {
        this.isSimulatedMode = true;
        this._connectionState.setValue(new ConnectionState.Connected((this._deviceState.getValue() == DeviceState.FACTORY_MODE || this._deviceState.getValue() == DeviceState.REGISTERING) ? "Setup Calis" : this.virtualClassroomName, "VIRTUAL_CALIS_DEVICE", this._deviceState.getValue()));
        CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
    }

    public final void setVirtualDeviceState(DeviceState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this._deviceState.setValue(state);
        String name = (state == DeviceState.FACTORY_MODE || state == DeviceState.REGISTERING) ? "Setup Calis" : this.virtualClassroomName;
        ScannedDevice virtDev = new ScannedDevice(name, "VIRTUAL_CALIS_DEVICE", state, -50, true, 0L, 32, null);
        this._scannedDevices.setValue(CollectionsKt.listOf(virtDev));
        if (this._connectionState.getValue() instanceof ConnectionState.Connected) {
            this._connectionState.setValue(new ConnectionState.Connected(name, "VIRTUAL_CALIS_DEVICE", state));
        }
        if (state == DeviceState.SESSION_ACTIVE_OPEN || state == DeviceState.SESSION_ACTIVE_BUSY) {
            CalisBleService.INSTANCE.setSessionActive(this.context, true);
        } else if (state == DeviceState.SESSION_INACTIVE) {
            CalisBleService.INSTANCE.setSessionActive(this.context, false);
        }
        CalisBleService.Companion.updateNotification$default(CalisBleService.INSTANCE, this.context, false, 2, null);
    }

    /* JADX INFO: renamed from: com.example.ble.BleManager$handleVirtualPacket$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BleManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.BleManager$handleVirtualPacket$1", f = "BleManager.kt", i = {5, 6, 7, 8}, l = {1186, 1195, 1208, 1215, 1232, 1244, 1252, 1262, 1270, 1281}, m = "invokeSuspend", n = {"forwarded", "forwarded", "forwarded", "forwarded"}, s = {"L$0", "L$0", "L$0", "L$0"})
    static final class C07511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BlePacket $packet;
        Object L$0;
        int label;
        final /* synthetic */ BleManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07511(BlePacket blePacket, BleManager bleManager, Continuation<? super C07511> continuation) {
            super(2, continuation);
            this.$packet = blePacket;
            this.this$0 = bleManager;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07511(this.$packet, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:100:0x02fb A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:21:0x0069  */
        /* JADX WARN: Code duplicated, block: B:23:0x00c3 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:25:0x00dd  */
        /* JADX WARN: Code duplicated, block: B:27:0x00e2  */
        /* JADX WARN: Code duplicated, block: B:29:0x00f0  */
        /* JADX WARN: Code duplicated, block: B:30:0x00f2  */
        /* JADX WARN: Code duplicated, block: B:38:0x0138  */
        /* JADX WARN: Code duplicated, block: B:40:0x0185 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:42:0x0188  */
        /* JADX WARN: Code duplicated, block: B:44:0x018c  */
        /* JADX WARN: Code duplicated, block: B:45:0x0195  */
        /* JADX WARN: Code duplicated, block: B:47:0x0199  */
        /* JADX WARN: Code duplicated, block: B:48:0x01a2  */
        /* JADX WARN: Code duplicated, block: B:50:0x01a6  */
        /* JADX WARN: Code duplicated, block: B:52:0x01da A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:54:0x01dd  */
        /* JADX WARN: Code duplicated, block: B:56:0x01e1  */
        /* JADX WARN: Code duplicated, block: B:58:0x0228 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:59:0x0229  */
        /* JADX WARN: Code duplicated, block: B:61:0x022c  */
        /* JADX WARN: Code duplicated, block: B:63:0x0230  */
        /* JADX WARN: Code duplicated, block: B:65:0x0256 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:66:0x0257  */
        /* JADX WARN: Code duplicated, block: B:68:0x025a  */
        /* JADX WARN: Code duplicated, block: B:70:0x025e  */
        /* JADX WARN: Code duplicated, block: B:72:0x0295 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:73:0x0296  */
        /* JADX WARN: Code duplicated, block: B:75:0x0298  */
        /* JADX WARN: Code duplicated, block: B:77:0x029c  */
        /* JADX WARN: Code duplicated, block: B:79:0x02c3 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:80:0x02c4  */
        /* JADX WARN: Code duplicated, block: B:82:0x02c6  */
        /* JADX WARN: Code duplicated, block: B:98:0x02e6  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            BlePacket blePacket;
            BlePacket.RequestUpdateRequest forwarded;
            BlePacket.TaskSubmissionRequest forwarded2;
            BlePacket.CheckInRequest forwarded3;
            BlePacket.StudentRegRequest forwarded4;
            boolean z;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(150L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    blePacket = this.$packet;
                    if (blePacket instanceof BlePacket.RegCommand) {
                        this.this$0.virtualCid = ((BlePacket.RegCommand) this.$packet).getCid();
                        this.this$0.virtualTid = ((BlePacket.RegCommand) this.$packet).getTid();
                        this.this$0.virtualClassroomName = ((BlePacket.RegCommand) this.$packet).getCls();
                        this.this$0.virtualTeacherName = ((BlePacket.RegCommand) this.$packet).getTc();
                        this.this$0.setVirtualDeviceState(DeviceState.REGISTERING);
                        this.this$0._lastTxResponse.setValue("REGISTERED");
                        this.label = 2;
                        if (this.this$0._incomingPackets.emit(BlePacket.RegisteredResponse.INSTANCE, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        BuildersKt__Builders_commonKt.launch$default(this.this$0.scope, null, null, new C01591(this.this$0, null), 3, null);
                    } else if (blePacket instanceof BlePacket.AuthCommand) {
                        if (this.this$0.virtualTid.length() > 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z || Intrinsics.areEqual(((BlePacket.AuthCommand) this.$packet).getTid(), this.this$0.virtualTid)) {
                            this.this$0.virtualTid = ((BlePacket.AuthCommand) this.$packet).getTid();
                            this.this$0.virtualCid = ((BlePacket.AuthCommand) this.$packet).getCid();
                            this.this$0.setVirtualDeviceState(DeviceState.SESSION_INACTIVE);
                            this.this$0._lastTxResponse.setValue("AUTH_OK");
                            this.this$0._isAuthenticated.setValue(Boxing.boxBoolean(true));
                            this.label = 4;
                            if (this.this$0._incomingPackets.emit(BlePacket.AuthOkResponse.INSTANCE, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            this.this$0._lastTxResponse.setValue("AUTH_FAILED");
                            this.this$0._isAuthenticated.setValue(Boxing.boxBoolean(false));
                            this.label = 3;
                            if (this.this$0._incomingPackets.emit(BlePacket.AuthFailedResponse.INSTANCE, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else if (blePacket instanceof BlePacket.StartSessionCommand) {
                        this.this$0.setVirtualDeviceState(DeviceState.SESSION_ACTIVE_OPEN);
                    } else if (blePacket instanceof BlePacket.EndSessionCommand) {
                        this.this$0.setVirtualDeviceState(DeviceState.SESSION_INACTIVE);
                    } else if (blePacket instanceof BlePacket.UnregCommand) {
                        this.this$0.virtualTid = "";
                        this.this$0.virtualCid = "";
                        this.this$0._isAuthenticated.setValue(Boxing.boxBoolean(false));
                        this.this$0.setVirtualDeviceState(DeviceState.FACTORY_MODE);
                        this.label = 5;
                        if (this.this$0._incomingPackets.emit(BlePacket.UnregisteredResponse.INSTANCE, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (blePacket instanceof BlePacket.StudentRegRequest) {
                        forwarded4 = new BlePacket.StudentRegRequest(((BlePacket.StudentRegRequest) this.$packet).getSid(), ((BlePacket.StudentRegRequest) this.$packet).getSt(), ((BlePacket.StudentRegRequest) this.$packet).getSex(), ((BlePacket.StudentRegRequest) this.$packet).getLrn(), ((BlePacket.StudentRegRequest) this.$packet).getBirth(), 1);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(forwarded4);
                        this.label = 6;
                        if (this.this$0._incomingPackets.emit(forwarded4, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (blePacket instanceof BlePacket.CheckInRequest) {
                        forwarded3 = new BlePacket.CheckInRequest(((BlePacket.CheckInRequest) this.$packet).getSid(), 1);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(forwarded3);
                        this.label = 7;
                        if (this.this$0._incomingPackets.emit(forwarded3, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (blePacket instanceof BlePacket.TaskSubmissionRequest) {
                        forwarded2 = new BlePacket.TaskSubmissionRequest(((BlePacket.TaskSubmissionRequest) this.$packet).getSid(), ((BlePacket.TaskSubmissionRequest) this.$packet).getTkid(), ((BlePacket.TaskSubmissionRequest) this.$packet).getScore(), 1);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(forwarded2);
                        this.label = 8;
                        if (this.this$0._incomingPackets.emit(forwarded2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (blePacket instanceof BlePacket.RequestUpdateRequest) {
                        forwarded = new BlePacket.RequestUpdateRequest(((BlePacket.RequestUpdateRequest) this.$packet).getSid(), 1);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(forwarded);
                        this.label = 9;
                        if (this.this$0._incomingPackets.emit(forwarded, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if ((blePacket instanceof BlePacket.RegApprovedResponse) || (blePacket instanceof BlePacket.RegRejectedResponse) || (blePacket instanceof BlePacket.AtdAcceptedResponse) || (blePacket instanceof BlePacket.AtdRejectedResponse) || (blePacket instanceof BlePacket.MarkedResponse) || (blePacket instanceof BlePacket.SchedResponse) || (blePacket instanceof BlePacket.TaskCommand) || (blePacket instanceof BlePacket.DoneResponse)) {
                        this.label = 10;
                        if (this.this$0._incomingPackets.emit(this.$packet, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    blePacket = this.$packet;
                    if (blePacket instanceof BlePacket.RegCommand) {
                        this.this$0.virtualCid = ((BlePacket.RegCommand) this.$packet).getCid();
                        this.this$0.virtualTid = ((BlePacket.RegCommand) this.$packet).getTid();
                        this.this$0.virtualClassroomName = ((BlePacket.RegCommand) this.$packet).getCls();
                        this.this$0.virtualTeacherName = ((BlePacket.RegCommand) this.$packet).getTc();
                        this.this$0.setVirtualDeviceState(DeviceState.REGISTERING);
                        this.this$0._lastTxResponse.setValue("REGISTERED");
                        this.label = 2;
                        if (this.this$0._incomingPackets.emit(BlePacket.RegisteredResponse.INSTANCE, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        BuildersKt__Builders_commonKt.launch$default(this.this$0.scope, null, null, new C01591(this.this$0, null), 3, null);
                    } else if (blePacket instanceof BlePacket.AuthCommand) {
                        if (this.this$0.virtualTid.length() > 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            this.this$0.virtualTid = ((BlePacket.AuthCommand) this.$packet).getTid();
                            this.this$0.virtualCid = ((BlePacket.AuthCommand) this.$packet).getCid();
                            this.this$0.setVirtualDeviceState(DeviceState.SESSION_INACTIVE);
                            this.this$0._lastTxResponse.setValue("AUTH_OK");
                            this.this$0._isAuthenticated.setValue(Boxing.boxBoolean(true));
                            this.label = 4;
                            if (this.this$0._incomingPackets.emit(BlePacket.AuthOkResponse.INSTANCE, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            this.this$0.virtualTid = ((BlePacket.AuthCommand) this.$packet).getTid();
                            this.this$0.virtualCid = ((BlePacket.AuthCommand) this.$packet).getCid();
                            this.this$0.setVirtualDeviceState(DeviceState.SESSION_INACTIVE);
                            this.this$0._lastTxResponse.setValue("AUTH_OK");
                            this.this$0._isAuthenticated.setValue(Boxing.boxBoolean(true));
                            this.label = 4;
                            if (this.this$0._incomingPackets.emit(BlePacket.AuthOkResponse.INSTANCE, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else if (blePacket instanceof BlePacket.StartSessionCommand) {
                        this.this$0.setVirtualDeviceState(DeviceState.SESSION_ACTIVE_OPEN);
                    } else if (blePacket instanceof BlePacket.EndSessionCommand) {
                        this.this$0.setVirtualDeviceState(DeviceState.SESSION_INACTIVE);
                    } else if (blePacket instanceof BlePacket.UnregCommand) {
                        this.this$0.virtualTid = "";
                        this.this$0.virtualCid = "";
                        this.this$0._isAuthenticated.setValue(Boxing.boxBoolean(false));
                        this.this$0.setVirtualDeviceState(DeviceState.FACTORY_MODE);
                        this.label = 5;
                        if (this.this$0._incomingPackets.emit(BlePacket.UnregisteredResponse.INSTANCE, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (blePacket instanceof BlePacket.StudentRegRequest) {
                        forwarded4 = new BlePacket.StudentRegRequest(((BlePacket.StudentRegRequest) this.$packet).getSid(), ((BlePacket.StudentRegRequest) this.$packet).getSt(), ((BlePacket.StudentRegRequest) this.$packet).getSex(), ((BlePacket.StudentRegRequest) this.$packet).getLrn(), ((BlePacket.StudentRegRequest) this.$packet).getBirth(), 1);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(forwarded4);
                        this.label = 6;
                        if (this.this$0._incomingPackets.emit(forwarded4, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (blePacket instanceof BlePacket.CheckInRequest) {
                        forwarded3 = new BlePacket.CheckInRequest(((BlePacket.CheckInRequest) this.$packet).getSid(), 1);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(forwarded3);
                        this.label = 7;
                        if (this.this$0._incomingPackets.emit(forwarded3, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (blePacket instanceof BlePacket.TaskSubmissionRequest) {
                        forwarded2 = new BlePacket.TaskSubmissionRequest(((BlePacket.TaskSubmissionRequest) this.$packet).getSid(), ((BlePacket.TaskSubmissionRequest) this.$packet).getTkid(), ((BlePacket.TaskSubmissionRequest) this.$packet).getScore(), 1);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(forwarded2);
                        this.label = 8;
                        if (this.this$0._incomingPackets.emit(forwarded2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (blePacket instanceof BlePacket.RequestUpdateRequest) {
                        forwarded = new BlePacket.RequestUpdateRequest(((BlePacket.RequestUpdateRequest) this.$packet).getSid(), 1);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(forwarded);
                        this.label = 9;
                        if (this.this$0._incomingPackets.emit(forwarded, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (blePacket instanceof BlePacket.RegApprovedResponse) {
                        this.label = 10;
                        if (this.this$0._incomingPackets.emit(this.$packet, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        this.label = 10;
                        if (this.this$0._incomingPackets.emit(this.$packet, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    ResultKt.throwOnFailure($result);
                    BuildersKt__Builders_commonKt.launch$default(this.this$0.scope, null, null, new C01591(this.this$0, null), 3, null);
                    return Unit.INSTANCE;
                case 3:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 4:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 5:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 6:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 7:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 8:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 9:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 10:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: com.example.ble.BleManager$handleVirtualPacket$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BleManager.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.ble.BleManager$handleVirtualPacket$1$1", f = "BleManager.kt", i = {}, l = {1198}, m = "invokeSuspend", n = {}, s = {})
        static final class C01591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ BleManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01591(BleManager bleManager, Continuation<? super C01591> continuation) {
                super(2, continuation);
                this.this$0 = bleManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01591(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (DelayKt.delay(600L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                this.this$0.setVirtualDeviceState(DeviceState.AUTHENTICATE);
                this.this$0._connectionState.setValue(ConnectionState.Disconnected.INSTANCE);
                return Unit.INSTANCE;
            }
        }
    }

    private final void handleVirtualPacket(String payload, BlePacket packet) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C07511(packet, this, null), 3, null);
    }
}
