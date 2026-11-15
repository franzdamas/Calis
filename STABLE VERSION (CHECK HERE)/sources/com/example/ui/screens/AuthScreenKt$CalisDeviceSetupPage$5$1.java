package com.example.ui.screens;

import android.util.Log;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.ble.BleManager;
import com.example.ble.BlePacket;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: AuthScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.AuthScreenKt$CalisDeviceSetupPage$5$1", f = "AuthScreen.kt", i = {}, l = {2387}, m = "invokeSuspend", n = {}, s = {})
final class AuthScreenKt$CalisDeviceSetupPage$5$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BleManager $bleManager;
    final /* synthetic */ String $clsName;
    final /* synthetic */ State<BleManager.ConnectionState> $connectionState$delegate;
    final /* synthetic */ MutableState<Boolean> $isSuccessfullyRegistered$delegate;
    final /* synthetic */ MutableState<Boolean> $isWaitingForReconnect$delegate;
    final /* synthetic */ MutableState<String> $registeringAddress$delegate;
    final /* synthetic */ String $tcName;
    final /* synthetic */ String $teacherCid;
    final /* synthetic */ String $teacherTid;
    final /* synthetic */ MutableState<BlePacket.RegisteredResponse> $tempRegisteredResponse$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    AuthScreenKt$CalisDeviceSetupPage$5$1(BleManager bleManager, String str, String str2, String str3, String str4, State<? extends BleManager.ConnectionState> state, MutableState<BlePacket.RegisteredResponse> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, MutableState<String> mutableState4, Continuation<? super AuthScreenKt$CalisDeviceSetupPage$5$1> continuation) {
        super(2, continuation);
        this.$bleManager = bleManager;
        this.$teacherCid = str;
        this.$teacherTid = str2;
        this.$clsName = str3;
        this.$tcName = str4;
        this.$connectionState$delegate = state;
        this.$tempRegisteredResponse$delegate = mutableState;
        this.$isSuccessfullyRegistered$delegate = mutableState2;
        this.$isWaitingForReconnect$delegate = mutableState3;
        this.$registeringAddress$delegate = mutableState4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthScreenKt$CalisDeviceSetupPage$5$1(this.$bleManager, this.$teacherCid, this.$teacherTid, this.$clsName, this.$tcName, this.$connectionState$delegate, this.$tempRegisteredResponse$delegate, this.$isSuccessfullyRegistered$delegate, this.$isWaitingForReconnect$delegate, this.$registeringAddress$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthScreenKt$CalisDeviceSetupPage$5$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (AuthScreenKt.CalisDeviceSetupPage$lambda$438(this.$connectionState$delegate) instanceof BleManager.ConnectionState.Disconnected) {
                    if (AuthScreenKt.CalisDeviceSetupPage$lambda$428(this.$tempRegisteredResponse$delegate) == null || AuthScreenKt.CalisDeviceSetupPage$lambda$434(this.$isSuccessfullyRegistered$delegate)) {
                        if (AuthScreenKt.CalisDeviceSetupPage$lambda$428(this.$tempRegisteredResponse$delegate) == null && AuthScreenKt.CalisDeviceSetupPage$lambda$422(this.$registeringAddress$delegate) != null && !AuthScreenKt.CalisDeviceSetupPage$lambda$434(this.$isSuccessfullyRegistered$delegate)) {
                            Log.d("CALIS", "[REG] Disconnected from device during Registering");
                        }
                    } else {
                        Log.d("CALIS", "[REG] Expected device disconnect");
                        Log.d("CALIS", "[REG] Waiting for BLE restart");
                        this.label = 1;
                        if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        AuthScreenKt.CalisDeviceSetupPage$lambda$432(this.$isWaitingForReconnect$delegate, true);
                        Log.d("CALIS", "[REG] Starting verification scan");
                        BleManager.startScan$default(this.$bleManager, false, 1, null);
                    }
                } else if (AuthScreenKt.CalisDeviceSetupPage$lambda$438(this.$connectionState$delegate) instanceof BleManager.ConnectionState.Connected) {
                    BleManager.ConnectionState connectionStateCalisDeviceSetupPage$lambda$438 = AuthScreenKt.CalisDeviceSetupPage$lambda$438(this.$connectionState$delegate);
                    Intrinsics.checkNotNull(connectionStateCalisDeviceSetupPage$lambda$438, "null cannot be cast to non-null type com.example.ble.BleManager.ConnectionState.Connected");
                    BleManager.ConnectionState.Connected conn = (BleManager.ConnectionState.Connected) connectionStateCalisDeviceSetupPage$lambda$438;
                    if (AuthScreenKt.CalisDeviceSetupPage$lambda$428(this.$tempRegisteredResponse$delegate) != null || !Intrinsics.areEqual(conn.getAddress(), AuthScreenKt.CalisDeviceSetupPage$lambda$422(this.$registeringAddress$delegate))) {
                        if (!AuthScreenKt.CalisDeviceSetupPage$lambda$434(this.$isSuccessfullyRegistered$delegate) && AuthScreenKt.CalisDeviceSetupPage$lambda$428(this.$tempRegisteredResponse$delegate) != null) {
                            Log.d("CALIS", "[REG] Reconnection established");
                            Log.d("CALIS", "[REG] Service UUID verified");
                            Log.d("CALIS", "[REG] Classroom name verified");
                            Log.d("CALIS", "[REG] Manufacturer Data = 0xFF00");
                            Log.d("CALIS", "[REG] Sending AUTH packet: AUTH|" + this.$teacherTid + "|" + this.$teacherCid);
                            BleManager.sendPacket$default(this.$bleManager, new BlePacket.AuthCommand(this.$teacherTid, this.$teacherCid), null, 2, null);
                        }
                    } else {
                        Log.d("CALIS", "[REG] Initial connection established");
                        Log.d("CALIS", "[REG] Device entered Registering state");
                        Log.d("CALIS", "[REG] Sending REG packet: REG|" + this.$teacherCid + "|" + this.$teacherTid + "|" + this.$clsName + "|" + this.$tcName);
                        BleManager.sendPacket$default(this.$bleManager, new BlePacket.RegCommand(this.$teacherCid, this.$teacherTid, this.$clsName, this.$tcName), null, 2, null);
                    }
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                AuthScreenKt.CalisDeviceSetupPage$lambda$432(this.$isWaitingForReconnect$delegate, true);
                Log.d("CALIS", "[REG] Starting verification scan");
                BleManager.startScan$default(this.$bleManager, false, 1, null);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
