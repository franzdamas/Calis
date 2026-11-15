package com.example.ui.screens;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TeacherMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.TeacherMainScreenKt$BackAndForthMarqueeText$1$1$1", f = "TeacherMainScreen.kt", i = {0, 1, 1, 2, 2, 3, 3, 4}, l = {7236, 7238, 7242, 7243, 7249}, m = "invokeSuspend", n = {"$this$LaunchedEffect", "$this$LaunchedEffect", "duration", "$this$LaunchedEffect", "duration", "$this$LaunchedEffect", "duration", "$this$LaunchedEffect"}, s = {"L$0", "L$0", "I$0", "L$0", "I$0", "L$0", "I$0", "L$0"})
final class TeacherMainScreenKt$BackAndForthMarqueeText$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $offsetX;
    final /* synthetic */ float $overflow;
    int I$0;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TeacherMainScreenKt$BackAndForthMarqueeText$1$1$1(float f, Animatable<Float, AnimationVector1D> animatable, Continuation<? super TeacherMainScreenKt$BackAndForthMarqueeText$1$1$1> continuation) {
        super(2, continuation);
        this.$overflow = f;
        this.$offsetX = animatable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TeacherMainScreenKt$BackAndForthMarqueeText$1$1$1 teacherMainScreenKt$BackAndForthMarqueeText$1$1$1 = new TeacherMainScreenKt$BackAndForthMarqueeText$1$1$1(this.$overflow, this.$offsetX, continuation);
        teacherMainScreenKt$BackAndForthMarqueeText$1$1$1.L$0 = obj;
        return teacherMainScreenKt$BackAndForthMarqueeText$1$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TeacherMainScreenKt$BackAndForthMarqueeText$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0066  */
    /* JADX WARN: Code duplicated, block: B:18:0x0074 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:21:0x00ad A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x00be A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:27:0x00e7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x00e8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00e8 -> B:29:0x00ee). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r22) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.TeacherMainScreenKt$BackAndForthMarqueeText$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
