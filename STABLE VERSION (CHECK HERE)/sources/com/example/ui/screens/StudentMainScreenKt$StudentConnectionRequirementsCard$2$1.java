package com.example.ui.screens;

import androidx.compose.foundation.pager.PagerState;
import java.util.ArrayList;
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
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: StudentMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.StudentMainScreenKt$StudentConnectionRequirementsCard$2$1", f = "StudentMainScreen.kt", i = {0, 0}, l = {1390}, m = "invokeSuspend", n = {"currentDisabled", "target"}, s = {"L$0", "I$0"})
final class StudentMainScreenKt$StudentConnectionRequirementsCard$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PagerState $pagerState;
    final /* synthetic */ List<StudentStepItem> $steps;
    int I$0;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StudentMainScreenKt$StudentConnectionRequirementsCard$2$1(List<StudentStepItem> list, PagerState pagerState, Continuation<? super StudentMainScreenKt$StudentConnectionRequirementsCard$2$1> continuation) {
        super(2, continuation);
        this.$steps = list;
        this.$pagerState = pagerState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StudentMainScreenKt$StudentConnectionRequirementsCard$2$1(this.$steps, this.$pagerState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StudentMainScreenKt$StudentConnectionRequirementsCard$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int target;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Iterable indices = CollectionsKt.getIndices(this.$steps);
                List<StudentStepItem> list = this.$steps;
                Collection arrayList = new ArrayList();
                for (Integer num : indices) {
                    if (!list.get(num.intValue()).isCompleted()) {
                        arrayList.add(num);
                    }
                }
                List currentDisabled = (List) arrayList;
                boolean z = false;
                int target2 = !currentDisabled.isEmpty() ? ((Number) CollectionsKt.first(currentDisabled)).intValue() : 0;
                if (target2 >= 0 && target2 < this.$steps.size()) {
                    z = true;
                }
                if (z && this.$pagerState.getCurrentPage() != target2) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(currentDisabled);
                    this.I$0 = target2;
                    this.label = 1;
                    if (PagerState.animateScrollToPage$default(this.$pagerState, target2, 0.0f, null, this, 6, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    target = target2;
                }
                return Unit.INSTANCE;
            case 1:
                target = this.I$0;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
