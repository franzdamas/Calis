package com.example.ui.screens;

import androidx.compose.runtime.MutableState;
import com.example.ble.BleManager;
import com.example.data.db.ClassroomEntity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TeacherMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.TeacherMainScreenKt$TeacherDashboardView$14$1", f = "TeacherMainScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class TeacherMainScreenKt$TeacherDashboardView$14$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ClassroomEntity $activeCls;
    final /* synthetic */ BleManager.ConnectionState $connectionState;
    final /* synthetic */ MutableState<Boolean> $hasPermissions$delegate;
    final /* synthetic */ MutableState<Boolean> $isBtEnabled$delegate;
    final /* synthetic */ boolean $isLocationConditionMet;
    final /* synthetic */ MutableState<Boolean> $isNotificationGranted$delegate;
    final /* synthetic */ Function0<Unit> $onScanDevices;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TeacherMainScreenKt$TeacherDashboardView$14$1(boolean z, BleManager.ConnectionState connectionState, ClassroomEntity classroomEntity, Function0<Unit> function0, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, Continuation<? super TeacherMainScreenKt$TeacherDashboardView$14$1> continuation) {
        super(2, continuation);
        this.$isLocationConditionMet = z;
        this.$connectionState = connectionState;
        this.$activeCls = classroomEntity;
        this.$onScanDevices = function0;
        this.$hasPermissions$delegate = mutableState;
        this.$isBtEnabled$delegate = mutableState2;
        this.$isNotificationGranted$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TeacherMainScreenKt$TeacherDashboardView$14$1(this.$isLocationConditionMet, this.$connectionState, this.$activeCls, this.$onScanDevices, this.$hasPermissions$delegate, this.$isBtEnabled$delegate, this.$isNotificationGranted$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TeacherMainScreenKt$TeacherDashboardView$14$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (TeacherMainScreenKt.TeacherDashboardView$lambda$296(this.$hasPermissions$delegate) && TeacherMainScreenKt.TeacherDashboardView$lambda$299(this.$isBtEnabled$delegate) && TeacherMainScreenKt.TeacherDashboardView$lambda$305(this.$isNotificationGranted$delegate) && this.$isLocationConditionMet && (this.$connectionState instanceof BleManager.ConnectionState.Disconnected)) {
                    ClassroomEntity classroomEntity = this.$activeCls;
                    boolean z = false;
                    if (classroomEntity != null && classroomEntity.isBound()) {
                        z = true;
                    }
                    if (z) {
                        this.$onScanDevices.invoke();
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
