package com.example.ui.screens;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.data.db.AttendanceEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: TeacherMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.TeacherMainScreenKt$ExportCenterView$2$1", f = "TeacherMainScreen.kt", i = {}, l = {3515, 3518}, m = "invokeSuspend", n = {}, s = {})
final class TeacherMainScreenKt$ExportCenterView$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<List<AttendanceEntity>> $attendanceLogs$delegate;
    final /* synthetic */ MutableState<Boolean> $isAttendanceLoading$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    TeacherMainScreenKt$ExportCenterView$2$1(State<? extends List<AttendanceEntity>> state, MutableState<Boolean> mutableState, Continuation<? super TeacherMainScreenKt$ExportCenterView$2$1> continuation) {
        super(2, continuation);
        this.$attendanceLogs$delegate = state;
        this.$isAttendanceLoading$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TeacherMainScreenKt$ExportCenterView$2$1(this.$attendanceLogs$delegate, this.$isAttendanceLoading$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TeacherMainScreenKt$ExportCenterView$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!TeacherMainScreenKt.ExportCenterView$lambda$517(this.$attendanceLogs$delegate).isEmpty()) {
                    this.label = 1;
                    if (DelayKt.delay(400L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    TeacherMainScreenKt.ExportCenterView$lambda$525(this.$isAttendanceLoading$delegate, false);
                    return Unit.INSTANCE;
                }
                this.label = 2;
                if (DelayKt.delay(850L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                TeacherMainScreenKt.ExportCenterView$lambda$525(this.$isAttendanceLoading$delegate, false);
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                TeacherMainScreenKt.ExportCenterView$lambda$525(this.$isAttendanceLoading$delegate, false);
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                TeacherMainScreenKt.ExportCenterView$lambda$525(this.$isAttendanceLoading$delegate, false);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
