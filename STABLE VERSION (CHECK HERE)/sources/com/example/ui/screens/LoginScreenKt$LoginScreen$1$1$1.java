package com.example.ui.screens;

import android.content.Context;
import androidx.compose.runtime.State;
import com.example.ui.viewmodel.AuthViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LoginScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.LoginScreenKt$LoginScreen$1$1$1", f = "LoginScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class LoginScreenKt$LoginScreen$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AuthViewModel $authViewModel;
    final /* synthetic */ Context $context;
    final /* synthetic */ State<Boolean> $enableBiometrics$delegate;
    final /* synthetic */ Function0<Unit> $onLoginSuccess;
    final /* synthetic */ boolean $supportsBiometrics;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LoginScreenKt$LoginScreen$1$1$1(boolean z, State<Boolean> state, Context context, AuthViewModel authViewModel, Function0<Unit> function0, Continuation<? super LoginScreenKt$LoginScreen$1$1$1> continuation) {
        super(2, continuation);
        this.$supportsBiometrics = z;
        this.$enableBiometrics$delegate = state;
        this.$context = context;
        this.$authViewModel = authViewModel;
        this.$onLoginSuccess = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LoginScreenKt$LoginScreen$1$1$1(this.$supportsBiometrics, this.$enableBiometrics$delegate, this.$context, this.$authViewModel, this.$onLoginSuccess, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoginScreenKt$LoginScreen$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (LoginScreenKt.LoginScreen$lambda$26$lambda$1(this.$enableBiometrics$delegate) && this.$supportsBiometrics) {
                    LoginScreenKt.LoginScreen$lambda$26$triggerBiometricLogin(this.$context, this.$authViewModel, this.$onLoginSuccess);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
