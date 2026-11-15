package com.example.ui.screens;

import androidx.compose.runtime.MutableState;
import com.example.ble.BleManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AuthScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.AuthScreenKt$CalisDeviceSetupPage$3$1", f = "AuthScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class AuthScreenKt$CalisDeviceSetupPage$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BleManager $bleManager;
    final /* synthetic */ MutableState<Boolean> $hasPermissions$delegate;
    final /* synthetic */ MutableState<Boolean> $isBtEnabled$delegate;
    final /* synthetic */ MutableState<Boolean> $isLocationEnabled$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AuthScreenKt$CalisDeviceSetupPage$3$1(BleManager bleManager, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, Continuation<? super AuthScreenKt$CalisDeviceSetupPage$3$1> continuation) {
        super(2, continuation);
        this.$bleManager = bleManager;
        this.$hasPermissions$delegate = mutableState;
        this.$isBtEnabled$delegate = mutableState2;
        this.$isLocationEnabled$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthScreenKt$CalisDeviceSetupPage$3$1(this.$bleManager, this.$hasPermissions$delegate, this.$isBtEnabled$delegate, this.$isLocationEnabled$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthScreenKt$CalisDeviceSetupPage$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (AuthScreenKt.CalisDeviceSetupPage$lambda$397(this.$hasPermissions$delegate) && AuthScreenKt.CalisDeviceSetupPage$lambda$400(this.$isBtEnabled$delegate) && AuthScreenKt.CalisDeviceSetupPage$lambda$409(this.$isLocationEnabled$delegate)) {
                    BleManager.startScan$default(this.$bleManager, false, 1, null);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
