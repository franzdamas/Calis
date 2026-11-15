package com.example.ui.screens;

import android.util.Log;
import androidx.compose.runtime.MutableState;
import com.example.ble.BleManager;
import com.example.ble.BlePacket;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;

/* JADX INFO: compiled from: AuthScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.AuthScreenKt$CalisDeviceSetupPage$4$1", f = "AuthScreen.kt", i = {}, l = {2359}, m = "invokeSuspend", n = {}, s = {})
final class AuthScreenKt$CalisDeviceSetupPage$4$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BleManager $bleManager;
    final /* synthetic */ MutableState<Boolean> $isSuccessfullyRegistered$delegate;
    final /* synthetic */ MutableState<Boolean> $isWaitingForReconnect$delegate;
    final /* synthetic */ MutableState<String> $registeringAddress$delegate;
    final /* synthetic */ MutableState<String> $savedMacAddress$delegate;
    final /* synthetic */ MutableState<BlePacket.RegisteredResponse> $tempRegisteredResponse$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AuthScreenKt$CalisDeviceSetupPage$4$1(BleManager bleManager, MutableState<BlePacket.RegisteredResponse> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<Boolean> mutableState4, MutableState<Boolean> mutableState5, Continuation<? super AuthScreenKt$CalisDeviceSetupPage$4$1> continuation) {
        super(2, continuation);
        this.$bleManager = bleManager;
        this.$tempRegisteredResponse$delegate = mutableState;
        this.$registeringAddress$delegate = mutableState2;
        this.$savedMacAddress$delegate = mutableState3;
        this.$isSuccessfullyRegistered$delegate = mutableState4;
        this.$isWaitingForReconnect$delegate = mutableState5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthScreenKt$CalisDeviceSetupPage$4$1(this.$bleManager, this.$tempRegisteredResponse$delegate, this.$registeringAddress$delegate, this.$savedMacAddress$delegate, this.$isSuccessfullyRegistered$delegate, this.$isWaitingForReconnect$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthScreenKt$CalisDeviceSetupPage$4$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                SharedFlow<BlePacket> incomingPackets = this.$bleManager.getIncomingPackets();
                final BleManager bleManager = this.$bleManager;
                final MutableState<BlePacket.RegisteredResponse> mutableState = this.$tempRegisteredResponse$delegate;
                final MutableState<String> mutableState2 = this.$registeringAddress$delegate;
                final MutableState<String> mutableState3 = this.$savedMacAddress$delegate;
                final MutableState<Boolean> mutableState4 = this.$isSuccessfullyRegistered$delegate;
                final MutableState<Boolean> mutableState5 = this.$isWaitingForReconnect$delegate;
                this.label = 1;
                if (incomingPackets.collect(new FlowCollector() { // from class: com.example.ui.screens.AuthScreenKt$CalisDeviceSetupPage$4$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((BlePacket) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(BlePacket packet, Continuation<? super Unit> continuation) {
                        if (packet instanceof BlePacket.RegisteredResponse) {
                            Log.d("CALIS", "[REG] REGISTERED received");
                            mutableState.setValue((BlePacket.RegisteredResponse) packet);
                            if (AuthScreenKt.CalisDeviceSetupPage$lambda$422(mutableState2) != null) {
                                mutableState3.setValue(AuthScreenKt.CalisDeviceSetupPage$lambda$422(mutableState2));
                            }
                        } else if (packet instanceof BlePacket.AuthOkResponse) {
                            Log.d("CALIS", "[REG] AUTH_OK received");
                            Log.d("CALIS", "[REG] Registration verification successful");
                            Log.d("CALIS", "[REG] Navigating to Registration Success");
                            AuthScreenKt.CalisDeviceSetupPage$lambda$435(mutableState4, true);
                            AuthScreenKt.CalisDeviceSetupPage$lambda$432(mutableState5, false);
                            bleManager.disconnect();
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        throw new KotlinNothingValueException();
    }
}
