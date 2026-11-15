package com.example.ui.components;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: DebugConsole.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.components.DebugConsoleKt$DebugConsolePopup$dragModifier$1$1", f = "DebugConsole.kt", i = {0}, l = {86}, m = "invokeSuspend", n = {"$this$pointerInput"}, s = {"L$0"})
final class DebugConsoleKt$DebugConsolePopup$dragModifier$1$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Density $density;
    final /* synthetic */ float $expandedHeightPx;
    final /* synthetic */ float $expandedWidthPx;
    final /* synthetic */ MutableState<Boolean> $isMinimized$delegate;
    final /* synthetic */ float $minBubbleSizePx;
    final /* synthetic */ MutableFloatState $offsetX$delegate;
    final /* synthetic */ MutableFloatState $offsetY$delegate;
    final /* synthetic */ float $screenHeightPx;
    final /* synthetic */ float $screenWidthPx;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DebugConsoleKt$DebugConsolePopup$dragModifier$1$1(float f, float f2, float f3, float f4, Density density, float f5, MutableState<Boolean> mutableState, MutableFloatState mutableFloatState, MutableFloatState mutableFloatState2, Continuation<? super DebugConsoleKt$DebugConsolePopup$dragModifier$1$1> continuation) {
        super(2, continuation);
        this.$minBubbleSizePx = f;
        this.$expandedWidthPx = f2;
        this.$expandedHeightPx = f3;
        this.$screenWidthPx = f4;
        this.$density = density;
        this.$screenHeightPx = f5;
        this.$isMinimized$delegate = mutableState;
        this.$offsetX$delegate = mutableFloatState;
        this.$offsetY$delegate = mutableFloatState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DebugConsoleKt$DebugConsolePopup$dragModifier$1$1 debugConsoleKt$DebugConsolePopup$dragModifier$1$1 = new DebugConsoleKt$DebugConsolePopup$dragModifier$1$1(this.$minBubbleSizePx, this.$expandedWidthPx, this.$expandedHeightPx, this.$screenWidthPx, this.$density, this.$screenHeightPx, this.$isMinimized$delegate, this.$offsetX$delegate, this.$offsetY$delegate, continuation);
        debugConsoleKt$DebugConsolePopup$dragModifier$1$1.L$0 = obj;
        return debugConsoleKt$DebugConsolePopup$dragModifier$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((DebugConsoleKt$DebugConsolePopup$dragModifier$1$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final float f = this.$minBubbleSizePx;
                final float f2 = this.$expandedWidthPx;
                final float f3 = this.$expandedHeightPx;
                final float f4 = this.$screenWidthPx;
                final Density density = this.$density;
                final float f5 = this.$screenHeightPx;
                final MutableState<Boolean> mutableState = this.$isMinimized$delegate;
                final MutableFloatState mutableFloatState = this.$offsetX$delegate;
                final MutableFloatState mutableFloatState2 = this.$offsetY$delegate;
                this.L$0 = SpillingKt.nullOutSpilledVariable($this$pointerInput);
                this.label = 1;
                if (DragGestureDetectorKt.detectDragGestures$default($this$pointerInput, null, null, null, new Function2() { // from class: com.example.ui.components.DebugConsoleKt$DebugConsolePopup$dragModifier$1$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return DebugConsoleKt$DebugConsolePopup$dragModifier$1$1.invokeSuspend$lambda$2(f, f2, f3, f4, density, f5, mutableState, mutableFloatState, mutableFloatState2, (PointerInputChange) obj, (Offset) obj2);
                    }
                }, this, 7, null) == coroutine_suspended) {
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

    static final Unit invokeSuspend$lambda$2(float $minBubbleSizePx, float $expandedWidthPx, float $expandedHeightPx, float $screenWidthPx, Density $density, float $screenHeightPx, MutableState $isMinimized$delegate, MutableFloatState $offsetX$delegate, MutableFloatState $offsetY$delegate, PointerInputChange change, Offset dragAmount) {
        change.consume();
        float curWidthPx = DebugConsoleKt.DebugConsolePopup$lambda$3($isMinimized$delegate) ? $minBubbleSizePx : $expandedWidthPx;
        float curHeightPx = DebugConsoleKt.DebugConsolePopup$lambda$3($isMinimized$delegate) ? $minBubbleSizePx : $expandedHeightPx;
        float maxOffsetX = RangesKt.coerceAtLeast(($screenWidthPx - curWidthPx) - $density.mo362toPx0680j_4(Dp.m6622constructorimpl(4)), 0.0f);
        float maxOffsetY = RangesKt.coerceAtLeast(($screenHeightPx - curHeightPx) - $density.mo362toPx0680j_4(Dp.m6622constructorimpl(4)), 0.0f);
        $offsetX$delegate.setFloatValue(RangesKt.coerceIn(DebugConsoleKt.DebugConsolePopup$lambda$22($offsetX$delegate) + Offset.m3918getXimpl(dragAmount.getPackedValue()), 4.0f, maxOffsetX));
        $offsetY$delegate.setFloatValue(RangesKt.coerceIn(DebugConsoleKt.DebugConsolePopup$lambda$25($offsetY$delegate) + Offset.m3919getYimpl(dragAmount.getPackedValue()), 4.0f, maxOffsetY));
        return Unit.INSTANCE;
    }
}
