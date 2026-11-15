package com.example.ui.screens;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import com.example.util.CsvExportUtils;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TeacherMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1", f = "TeacherMainScreen.kt", i = {0}, l = {3924}, m = "invokeSuspend", n = {"$this$pointerInput"}, s = {"L$0"})
final class TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<List<CsvExportUtils.AttendanceColumnKey>> $attendanceColumnOrder$delegate;
    final /* synthetic */ CsvExportUtils.AttendanceColumnKey $colKey;
    final /* synthetic */ State<List<CsvExportUtils.AttendanceColumnKey>> $currentOrderState;
    final /* synthetic */ MutableFloatState $dragOffsetY$delegate;
    final /* synthetic */ MutableState<Integer> $draggingIndex$delegate;
    final /* synthetic */ MutableState<Boolean> $isDraggingActive$delegate;
    final /* synthetic */ MutableFloatState $itemHeightPx$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1(State<? extends List<? extends CsvExportUtils.AttendanceColumnKey>> state, CsvExportUtils.AttendanceColumnKey attendanceColumnKey, MutableState<Integer> mutableState, MutableState<Boolean> mutableState2, MutableFloatState mutableFloatState, MutableFloatState mutableFloatState2, MutableState<List<CsvExportUtils.AttendanceColumnKey>> mutableState3, Continuation<? super TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1> continuation) {
        super(2, continuation);
        this.$currentOrderState = state;
        this.$colKey = attendanceColumnKey;
        this.$draggingIndex$delegate = mutableState;
        this.$isDraggingActive$delegate = mutableState2;
        this.$dragOffsetY$delegate = mutableFloatState;
        this.$itemHeightPx$delegate = mutableFloatState2;
        this.$attendanceColumnOrder$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1 teacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1 = new TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1(this.$currentOrderState, this.$colKey, this.$draggingIndex$delegate, this.$isDraggingActive$delegate, this.$dragOffsetY$delegate, this.$itemHeightPx$delegate, this.$attendanceColumnOrder$delegate, continuation);
        teacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1.L$0 = obj;
        return teacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final State<List<CsvExportUtils.AttendanceColumnKey>> state = this.$currentOrderState;
                final CsvExportUtils.AttendanceColumnKey attendanceColumnKey = this.$colKey;
                final MutableState<Integer> mutableState = this.$draggingIndex$delegate;
                final MutableState<Boolean> mutableState2 = this.$isDraggingActive$delegate;
                final MutableFloatState mutableFloatState = this.$dragOffsetY$delegate;
                Function1<Offset, Unit> function1 = new Function1<Offset, Unit>() { // from class: com.example.ui.screens.TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m7055invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m7055invokek4lQ0M(long offset) {
                        int currentIdx = state.getValue().indexOf(attendanceColumnKey);
                        if (currentIdx == -1) {
                            return;
                        }
                        mutableState.setValue(Integer.valueOf(currentIdx));
                        TeacherMainScreenKt.ExportCenterView$lambda$568(mutableState2, true);
                        mutableFloatState.setFloatValue(0.0f);
                    }
                };
                final MutableState<Integer> mutableState3 = this.$draggingIndex$delegate;
                final MutableState<Boolean> mutableState4 = this.$isDraggingActive$delegate;
                final MutableFloatState mutableFloatState2 = this.$dragOffsetY$delegate;
                Function0<Unit> function0 = new Function0<Unit>() { // from class: com.example.ui.screens.TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1.2
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        mutableState3.setValue(null);
                        TeacherMainScreenKt.ExportCenterView$lambda$568(mutableState4, false);
                        mutableFloatState2.setFloatValue(0.0f);
                    }
                };
                final MutableState<Integer> mutableState5 = this.$draggingIndex$delegate;
                final MutableState<Boolean> mutableState6 = this.$isDraggingActive$delegate;
                final MutableFloatState mutableFloatState3 = this.$dragOffsetY$delegate;
                Function0<Unit> function2 = new Function0<Unit>() { // from class: com.example.ui.screens.TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1.3
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        mutableState5.setValue(null);
                        TeacherMainScreenKt.ExportCenterView$lambda$568(mutableState6, false);
                        mutableFloatState3.setFloatValue(0.0f);
                    }
                };
                final State<List<CsvExportUtils.AttendanceColumnKey>> state2 = this.$currentOrderState;
                final CsvExportUtils.AttendanceColumnKey attendanceColumnKey2 = this.$colKey;
                final MutableFloatState mutableFloatState4 = this.$dragOffsetY$delegate;
                final MutableFloatState mutableFloatState5 = this.$itemHeightPx$delegate;
                final MutableState<List<CsvExportUtils.AttendanceColumnKey>> mutableState7 = this.$attendanceColumnOrder$delegate;
                final MutableState<Integer> mutableState8 = this.$draggingIndex$delegate;
                this.L$0 = SpillingKt.nullOutSpilledVariable($this$pointerInput);
                this.label = 1;
                if (DragGestureDetectorKt.detectDragGesturesAfterLongPress($this$pointerInput, function1, function0, function2, new Function2<PointerInputChange, Offset, Unit>() { // from class: com.example.ui.screens.TeacherMainScreenKt$ExportCenterView$4$3$1$4$1$2$3$1.4
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Offset offset) {
                        m7056invokeUv8p0NA(pointerInputChange, offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                    public final void m7056invokeUv8p0NA(PointerInputChange change, long dragAmount) {
                        Intrinsics.checkNotNullParameter(change, "change");
                        change.consume();
                        mutableFloatState4.setFloatValue(TeacherMainScreenKt.ExportCenterView$lambda$684$lambda$650$lambda$649$lambda$635(mutableFloatState4) + Offset.m3919getYimpl(dragAmount));
                        int idx = state2.getValue().indexOf(attendanceColumnKey2);
                        if (idx != -1) {
                            float step = TeacherMainScreenKt.ExportCenterView$lambda$684$lambda$650$lambda$649$lambda$638(mutableFloatState5) > 0.0f ? TeacherMainScreenKt.ExportCenterView$lambda$684$lambda$650$lambda$649$lambda$638(mutableFloatState5) : 100.0f;
                            if (TeacherMainScreenKt.ExportCenterView$lambda$684$lambda$650$lambda$649$lambda$635(mutableFloatState4) > step * 0.5f && idx < state2.getValue().size() - 1) {
                                int nextIndex = idx + 1;
                                List mutable = CollectionsKt.toMutableList((Collection) state2.getValue());
                                CsvExportUtils.AttendanceColumnKey item = (CsvExportUtils.AttendanceColumnKey) mutable.remove(idx);
                                mutable.add(nextIndex, item);
                                mutableState7.setValue(mutable);
                                mutableState8.setValue(Integer.valueOf(nextIndex));
                                mutableFloatState4.setFloatValue(TeacherMainScreenKt.ExportCenterView$lambda$684$lambda$650$lambda$649$lambda$635(mutableFloatState4) - step);
                                return;
                            }
                            if (TeacherMainScreenKt.ExportCenterView$lambda$684$lambda$650$lambda$649$lambda$635(mutableFloatState4) < (-step) * 0.5f && idx > 0) {
                                int prevIndex = idx - 1;
                                List mutable2 = CollectionsKt.toMutableList((Collection) state2.getValue());
                                CsvExportUtils.AttendanceColumnKey item2 = (CsvExportUtils.AttendanceColumnKey) mutable2.remove(idx);
                                mutable2.add(prevIndex, item2);
                                mutableState7.setValue(mutable2);
                                mutableState8.setValue(Integer.valueOf(prevIndex));
                                mutableFloatState4.setFloatValue(TeacherMainScreenKt.ExportCenterView$lambda$684$lambda$650$lambda$649$lambda$635(mutableFloatState4) + step);
                            }
                        }
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
        return Unit.INSTANCE;
    }
}
