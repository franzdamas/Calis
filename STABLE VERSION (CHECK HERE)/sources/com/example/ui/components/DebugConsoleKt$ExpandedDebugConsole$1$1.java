package com.example.ui.components;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.State;
import com.example.ble.BleManager;
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

/* JADX INFO: compiled from: DebugConsole.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.components.DebugConsoleKt$ExpandedDebugConsole$1$1", f = "DebugConsole.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
final class DebugConsoleKt$ExpandedDebugConsole$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LazyListState $listState;
    final /* synthetic */ State<List<BleManager.DebugLog>> $logs$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DebugConsoleKt$ExpandedDebugConsole$1$1(LazyListState lazyListState, State<? extends List<BleManager.DebugLog>> state, Continuation<? super DebugConsoleKt$ExpandedDebugConsole$1$1> continuation) {
        super(2, continuation);
        this.$listState = lazyListState;
        this.$logs$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DebugConsoleKt$ExpandedDebugConsole$1$1(this.$listState, this.$logs$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DebugConsoleKt$ExpandedDebugConsole$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$41(this.$logs$delegate).isEmpty()) {
                    this.label = 1;
                    if (LazyListState.animateScrollToItem$default(this.$listState, DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$41(this.$logs$delegate).size() - 1, 0, this, 2, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
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
