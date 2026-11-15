package com.example.ui.screens;

import androidx.compose.foundation.lazy.LazyListState;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AuthScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.AuthScreenKt$WheelPicker$2$1", f = "AuthScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class AuthScreenKt$WheelPicker$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<String> $items;
    final /* synthetic */ LazyListState $listState;
    final /* synthetic */ Function1<String, Unit> $onItemSelected;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    AuthScreenKt$WheelPicker$2$1(LazyListState lazyListState, List<String> list, Function1<? super String, Unit> function1, Continuation<? super AuthScreenKt$WheelPicker$2$1> continuation) {
        super(2, continuation);
        this.$listState = lazyListState;
        this.$items = list;
        this.$onItemSelected = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthScreenKt$WheelPicker$2$1(this.$listState, this.$items, this.$onItemSelected, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthScreenKt$WheelPicker$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!this.$listState.isScrollInProgress()) {
                    int centerIndex = this.$listState.getFirstVisibleItemIndex();
                    boolean z = false;
                    if (centerIndex >= 0 && centerIndex < this.$items.size()) {
                        z = true;
                    }
                    if (z) {
                        this.$onItemSelected.invoke(this.$items.get(centerIndex));
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
