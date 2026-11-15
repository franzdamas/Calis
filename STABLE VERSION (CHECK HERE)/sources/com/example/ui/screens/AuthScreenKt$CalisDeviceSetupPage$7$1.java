package com.example.ui.screens;

import android.util.Log;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.ble.BleManager;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AuthScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.AuthScreenKt$CalisDeviceSetupPage$7$1", f = "AuthScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class AuthScreenKt$CalisDeviceSetupPage$7$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BleManager $bleManager;
    final /* synthetic */ String $clsName;
    final /* synthetic */ State<BleManager.ConnectionState> $connectionState$delegate;
    final /* synthetic */ MutableState<Boolean> $isWaitingForReconnect$delegate;
    final /* synthetic */ State<List<BleManager.ScannedDevice>> $scannedDevices$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    AuthScreenKt$CalisDeviceSetupPage$7$1(BleManager bleManager, MutableState<Boolean> mutableState, State<? extends BleManager.ConnectionState> state, State<? extends List<BleManager.ScannedDevice>> state2, String str, Continuation<? super AuthScreenKt$CalisDeviceSetupPage$7$1> continuation) {
        super(2, continuation);
        this.$bleManager = bleManager;
        this.$isWaitingForReconnect$delegate = mutableState;
        this.$connectionState$delegate = state;
        this.$scannedDevices$delegate = state2;
        this.$clsName = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthScreenKt$CalisDeviceSetupPage$7$1(this.$bleManager, this.$isWaitingForReconnect$delegate, this.$connectionState$delegate, this.$scannedDevices$delegate, this.$clsName, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthScreenKt$CalisDeviceSetupPage$7$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0088  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object next;
        BleManager.ScannedDevice device;
        boolean z;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (AuthScreenKt.CalisDeviceSetupPage$lambda$431(this.$isWaitingForReconnect$delegate) && (AuthScreenKt.CalisDeviceSetupPage$lambda$438(this.$connectionState$delegate) instanceof BleManager.ConnectionState.Disconnected)) {
                    List listCalisDeviceSetupPage$lambda$437 = AuthScreenKt.CalisDeviceSetupPage$lambda$437(this.$scannedDevices$delegate);
                    String str = this.$clsName;
                    BleManager bleManager = this.$bleManager;
                    Iterator it = listCalisDeviceSetupPage$lambda$437.iterator();
                    do {
                        if (it.hasNext()) {
                            next = it.next();
                            BleManager.ScannedDevice scannedDevice = (BleManager.ScannedDevice) next;
                            boolean hasServiceUuid = scannedDevice.getHasServiceUuid();
                            z = true;
                            boolean z2 = StringsKt.equals(scannedDevice.getName(), str, true) || StringsKt.contains((CharSequence) scannedDevice.getName(), (CharSequence) str, true) || (bleManager.getIsSimulatedMode() && Intrinsics.areEqual(scannedDevice.getAddress(), "VIRTUAL_CALIS_DEVICE"));
                            if (!hasServiceUuid || !z2) {
                                z = false;
                            }
                        } else {
                            next = null;
                        }
                        device = (BleManager.ScannedDevice) next;
                        if (device != null) {
                            Log.d("CALIS", "[REG] Found device: " + device.getName() + ", state: " + device.getState().name());
                            AuthScreenKt.CalisDeviceSetupPage$lambda$432(this.$isWaitingForReconnect$delegate, false);
                            this.$bleManager.stopScan();
                            this.$bleManager.connect(device.getAddress());
                        }
                    } while (!z);
                    device = (BleManager.ScannedDevice) next;
                    if (device != null) {
                        Log.d("CALIS", "[REG] Found device: " + device.getName() + ", state: " + device.getState().name());
                        AuthScreenKt.CalisDeviceSetupPage$lambda$432(this.$isWaitingForReconnect$delegate, false);
                        this.$bleManager.stopScan();
                        this.$bleManager.connect(device.getAddress());
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
