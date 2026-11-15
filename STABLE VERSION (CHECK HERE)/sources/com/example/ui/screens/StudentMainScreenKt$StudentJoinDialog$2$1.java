package com.example.ui.screens;

import androidx.compose.runtime.State;
import com.example.ble.BleManager;
import com.example.ui.viewmodel.StudentViewModel;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: StudentMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.StudentMainScreenKt$StudentJoinDialog$2$1", f = "StudentMainScreen.kt", i = {0}, l = {2594}, m = "invokeSuspend", n = {"$this$LaunchedEffect"}, s = {"L$0"})
final class StudentMainScreenKt$StudentJoinDialog$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<String> $registrationStatus$delegate;
    final /* synthetic */ StudentViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StudentMainScreenKt$StudentJoinDialog$2$1(StudentViewModel studentViewModel, State<String> state, Continuation<? super StudentMainScreenKt$StudentJoinDialog$2$1> continuation) {
        super(2, continuation);
        this.$viewModel = studentViewModel;
        this.$registrationStatus$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        StudentMainScreenKt$StudentJoinDialog$2$1 studentMainScreenKt$StudentJoinDialog$2$1 = new StudentMainScreenKt$StudentJoinDialog$2$1(this.$viewModel, this.$registrationStatus$delegate, continuation);
        studentMainScreenKt$StudentJoinDialog$2$1.L$0 = obj;
        return studentMainScreenKt$StudentJoinDialog$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StudentMainScreenKt$StudentJoinDialog$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0032  */
    /* JADX WARN: Code duplicated, block: B:14:0x003f  */
    /* JADX WARN: Code duplicated, block: B:26:0x006d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:? A[LOOP:0: B:10:0x002c->B:29:?, LOOP_END, SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        BleManager.ConnectionState conn;
        CoroutineScope $this$LaunchedEffect = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (Intrinsics.areEqual(StudentMainScreenKt.StudentJoinDialog$lambda$386(this.$registrationStatus$delegate), "NOT_REGISTERED")) {
                    while (CoroutineScopeKt.isActive($this$LaunchedEffect)) {
                        if (this.$viewModel.getBleManager().isBluetoothEnabled()) {
                            conn = this.$viewModel.getBleManager().getConnectionState().getValue();
                            if (!(conn instanceof BleManager.ConnectionState.Connected) && !(conn instanceof BleManager.ConnectionState.Connecting)) {
                                StudentViewModel.startBleScan$default(this.$viewModel, false, 1, null);
                            }
                        }
                        this.L$0 = $this$LaunchedEffect;
                        this.label = 1;
                        if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    this.$viewModel.stopBleScan();
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                while (CoroutineScopeKt.isActive($this$LaunchedEffect)) {
                    if (this.$viewModel.getBleManager().isBluetoothEnabled()) {
                        conn = this.$viewModel.getBleManager().getConnectionState().getValue();
                        if (!(conn instanceof BleManager.ConnectionState.Connected)) {
                            StudentViewModel.startBleScan$default(this.$viewModel, false, 1, null);
                        }
                    }
                    this.L$0 = $this$LaunchedEffect;
                    this.label = 1;
                    if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
