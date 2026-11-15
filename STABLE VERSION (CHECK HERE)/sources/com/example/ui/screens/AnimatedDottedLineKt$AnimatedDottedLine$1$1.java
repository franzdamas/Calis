package com.example.ui.screens;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AnimatedDottedLine.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.AnimatedDottedLineKt$AnimatedDottedLine$1$1", f = "AnimatedDottedLine.kt", i = {}, l = {28, 29, 32, 33}, m = "invokeSuspend", n = {}, s = {})
final class AnimatedDottedLineKt$AnimatedDottedLine$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $headProgress;
    final /* synthetic */ boolean $isActive;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $tailProgress;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnimatedDottedLineKt$AnimatedDottedLine$1$1(boolean z, Animatable<Float, AnimationVector1D> animatable, Animatable<Float, AnimationVector1D> animatable2, Continuation<? super AnimatedDottedLineKt$AnimatedDottedLine$1$1> continuation) {
        super(2, continuation);
        this.$isActive = z;
        this.$tailProgress = animatable;
        this.$headProgress = animatable2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AnimatedDottedLineKt$AnimatedDottedLine$1$1(this.$isActive, this.$tailProgress, this.$headProgress, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AnimatedDottedLineKt$AnimatedDottedLine$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0068 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x009e A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Animatable<Float, AnimationVector1D> animatable;
        Float fBoxFloat;
        TweenSpec tweenSpecTween$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                boolean z = this.$isActive;
                Animatable<Float, AnimationVector1D> animatable2 = this.$tailProgress;
                if (z) {
                    this.label = 1;
                    if (animatable2.snapTo(Boxing.boxFloat(0.0f), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    animatable = this.$headProgress;
                    fBoxFloat = Boxing.boxFloat(1.0f);
                    tweenSpecTween$default = AnimationSpecKt.tween$default(800, 0, EasingKt.getLinearEasing(), 2, null);
                    this.label = 2;
                    if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : tweenSpecTween$default, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                Float fBoxFloat2 = Boxing.boxFloat(1.0f);
                TweenSpec tweenSpecTween$default2 = AnimationSpecKt.tween$default(800, 0, EasingKt.getLinearEasing(), 2, null);
                this.label = 3;
                if (animatable2.animateTo(fBoxFloat2, (4 & 2) != 0 ? animatable2.defaultSpringSpec : tweenSpecTween$default2, (4 & 4) != 0 ? animatable2.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                this.label = 4;
                if (this.$headProgress.snapTo(Boxing.boxFloat(0.0f), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Unit unit = Unit.INSTANCE;
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                animatable = this.$headProgress;
                fBoxFloat = Boxing.boxFloat(1.0f);
                tweenSpecTween$default = AnimationSpecKt.tween$default(800, 0, EasingKt.getLinearEasing(), 2, null);
                this.label = 2;
                if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : tweenSpecTween$default, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            case 3:
                ResultKt.throwOnFailure($result);
                this.label = 4;
                if (this.$headProgress.snapTo(Boxing.boxFloat(0.0f), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Unit unit2 = Unit.INSTANCE;
                return Unit.INSTANCE;
            case 4:
                ResultKt.throwOnFailure($result);
                Unit unit3 = Unit.INSTANCE;
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
