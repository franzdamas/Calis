package com.example;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.ble.BleManager;
import com.example.ble.CalisBleService;
import com.example.ui.viewmodel.TeacherViewModel;
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

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.MainActivityKt$CalisAppNavigation$1$1", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class MainActivityKt$CalisAppNavigation$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $activeScreen$delegate;
    final /* synthetic */ Context $context;
    final /* synthetic */ State<Boolean> $isDbLoaded$delegate;
    final /* synthetic */ TeacherViewModel $teacherViewModel;
    final /* synthetic */ State<String> $userRole$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivityKt$CalisAppNavigation$1$1(TeacherViewModel teacherViewModel, Context context, State<Boolean> state, State<String> state2, MutableState<String> mutableState, Continuation<? super MainActivityKt$CalisAppNavigation$1$1> continuation) {
        super(2, continuation);
        this.$teacherViewModel = teacherViewModel;
        this.$context = context;
        this.$isDbLoaded$delegate = state;
        this.$userRole$delegate = state2;
        this.$activeScreen$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivityKt$CalisAppNavigation$1$1(this.$teacherViewModel, this.$context, this.$isDbLoaded$delegate, this.$userRole$delegate, this.$activeScreen$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivityKt$CalisAppNavigation$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!MainActivityKt.CalisAppNavigation$lambda$5(this.$isDbLoaded$delegate)) {
                    return Unit.INSTANCE;
                }
                boolean isServiceActive = CalisBleService.INSTANCE.isServiceRunning();
                boolean isConnected = this.$teacherViewModel.getBleManager().getConnectionState().getValue() instanceof BleManager.ConnectionState.Connected;
                if (!isConnected && !isServiceActive) {
                    if (!Intrinsics.areEqual(MainActivityKt.CalisAppNavigation$lambda$2(this.$userRole$delegate), "STUDENT") && !Intrinsics.areEqual(MainActivityKt.CalisAppNavigation$lambda$8(this.$activeScreen$delegate), "STUDENT_MAIN")) {
                        Intrinsics.areEqual(MainActivityKt.CalisAppNavigation$lambda$2(this.$userRole$delegate), "TEACHER");
                    } else {
                        CalisBleService.INSTANCE.stopService(this.$context);
                    }
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
