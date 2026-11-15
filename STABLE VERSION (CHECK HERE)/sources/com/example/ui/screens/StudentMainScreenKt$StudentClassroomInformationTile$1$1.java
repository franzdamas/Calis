package com.example.ui.screens;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: StudentMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.StudentMainScreenKt$StudentClassroomInformationTile$1$1", f = "StudentMainScreen.kt", i = {0, 0, 0, 0}, l = {2806}, m = "invokeSuspend", n = {"$this$LaunchedEffect", "timeFormat", "dateFormat", "now"}, s = {"L$0", "L$1", "L$2", "L$3"})
final class StudentMainScreenKt$StudentClassroomInformationTile$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $currentDateStr$delegate;
    final /* synthetic */ MutableState<String> $currentTimeStr$delegate;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StudentMainScreenKt$StudentClassroomInformationTile$1$1(MutableState<String> mutableState, MutableState<String> mutableState2, Continuation<? super StudentMainScreenKt$StudentClassroomInformationTile$1$1> continuation) {
        super(2, continuation);
        this.$currentTimeStr$delegate = mutableState;
        this.$currentDateStr$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        StudentMainScreenKt$StudentClassroomInformationTile$1$1 studentMainScreenKt$StudentClassroomInformationTile$1$1 = new StudentMainScreenKt$StudentClassroomInformationTile$1$1(this.$currentTimeStr$delegate, this.$currentDateStr$delegate, continuation);
        studentMainScreenKt$StudentClassroomInformationTile$1$1.L$0 = obj;
        return studentMainScreenKt$StudentClassroomInformationTile$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StudentMainScreenKt$StudentClassroomInformationTile$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0045  */
    /* JADX WARN: Code duplicated, block: B:12:0x007e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:13:0x007f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x007f -> B:14:0x0084). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r10.label
            switch(r2) {
                case 0: goto L28;
                case 1: goto L15;
                default: goto Ld;
            }
        Ld:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L15:
            java.lang.Object r2 = r10.L$3
            java.util.Date r2 = (java.util.Date) r2
            java.lang.Object r3 = r10.L$2
            java.text.SimpleDateFormat r3 = (java.text.SimpleDateFormat) r3
            java.lang.Object r4 = r10.L$1
            java.text.SimpleDateFormat r4 = (java.text.SimpleDateFormat) r4
            kotlin.ResultKt.throwOnFailure(r11)
            r5 = r4
            r4 = r3
            r3 = r10
            goto L84
        L28:
            kotlin.ResultKt.throwOnFailure(r11)
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r3 = "hh:mm:ss a"
            java.util.Locale r4 = java.util.Locale.US
            r2.<init>(r3, r4)
            java.text.SimpleDateFormat r3 = new java.text.SimpleDateFormat
            java.lang.String r4 = "EEEE, MMMM dd, yyyy"
            java.util.Locale r5 = java.util.Locale.US
            r3.<init>(r4, r5)
            r4 = r2
            r2 = r10
        L3f:
            boolean r5 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)
            if (r5 == 0) goto L88
            java.util.Date r5 = new java.util.Date
            r5.<init>()
            androidx.compose.runtime.MutableState<java.lang.String> r6 = r2.$currentTimeStr$delegate
            java.lang.String r7 = r4.format(r5)
            java.lang.String r8 = "format(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            com.example.ui.screens.StudentMainScreenKt.access$StudentClassroomInformationTile$lambda$425(r6, r7)
            androidx.compose.runtime.MutableState<java.lang.String> r6 = r2.$currentDateStr$delegate
            java.lang.String r7 = r3.format(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            com.example.ui.screens.StudentMainScreenKt.access$StudentClassroomInformationTile$lambda$428(r6, r7)
            r6 = r2
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r2.L$0 = r0
            r2.L$1 = r4
            r2.L$2 = r3
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
            r2.L$3 = r7
            r7 = 1
            r2.label = r7
            r7 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r7, r6)
            if (r6 != r1) goto L7f
            return r1
        L7f:
            r9 = r3
            r3 = r2
            r2 = r5
            r5 = r4
            r4 = r9
        L84:
            r2 = r3
            r3 = r4
            r4 = r5
            goto L3f
        L88:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.StudentMainScreenKt$StudentClassroomInformationTile$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
