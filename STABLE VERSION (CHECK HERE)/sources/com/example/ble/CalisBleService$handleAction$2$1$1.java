package com.example.ble;

import com.example.data.db.CalisDatabase;
import com.example.data.db.ClassroomEntity;
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

/* JADX INFO: compiled from: CalisBleService.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ble.CalisBleService$handleAction$2$1$1", f = "CalisBleService.kt", i = {}, l = {586}, m = "invokeSuspend", n = {}, s = {})
final class CalisBleService$handleAction$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ClassroomEntity $classroom;
    final /* synthetic */ boolean $success;
    int label;
    final /* synthetic */ CalisBleService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CalisBleService$handleAction$2$1$1(boolean z, CalisBleService calisBleService, ClassroomEntity classroomEntity, Continuation<? super CalisBleService$handleAction$2$1$1> continuation) {
        super(2, continuation);
        this.$success = z;
        this.this$0 = calisBleService;
        this.$classroom = classroomEntity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CalisBleService$handleAction$2$1$1(this.$success, this.this$0, this.$classroom, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CalisBleService$handleAction$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                boolean z = this.$success;
                CalisBleService calisBleService = this.this$0;
                CalisDatabase calisDatabase = null;
                if (z) {
                    CalisDatabase calisDatabase2 = calisBleService.database;
                    if (calisDatabase2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("database");
                    } else {
                        calisDatabase = calisDatabase2;
                    }
                    this.label = 1;
                    if (calisDatabase.classroomDao().updateClassroom(ClassroomEntity.copy$default(this.$classroom, null, null, null, null, null, null, null, null, false, false, null, 1791, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    calisBleService.isSessionActiveOverride = null;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.updateToolbarNotificationImmediate();
        return Unit.INSTANCE;
    }
}
