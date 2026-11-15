package com.example.ui.screens;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import com.example.util.BlePermissionUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: TeacherMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.TeacherMainScreenKt$TeacherDashboardView$8$1", f = "TeacherMainScreen.kt", i = {0}, l = {1843}, m = "invokeSuspend", n = {"attempts"}, s = {"I$0"})
final class TeacherMainScreenKt$TeacherDashboardView$8$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<Boolean> $isBtEnabled$delegate;
    final /* synthetic */ MutableState<Boolean> $isBtEnabling$delegate;
    int I$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TeacherMainScreenKt$TeacherDashboardView$8$1(Context context, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, Continuation<? super TeacherMainScreenKt$TeacherDashboardView$8$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$isBtEnabling$delegate = mutableState;
        this.$isBtEnabled$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TeacherMainScreenKt$TeacherDashboardView$8$1(this.$context, this.$isBtEnabling$delegate, this.$isBtEnabled$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TeacherMainScreenKt$TeacherDashboardView$8$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x002b  */
    /* JADX WARN: Code duplicated, block: B:19:0x004d  */
    /* JADX WARN: Code duplicated, block: B:20:0x0058  */
    /* JADX WARN: Code duplicated, block: B:23:0x0063  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0040 -> B:17:0x0043). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int attempts;
        TeacherMainScreenKt$TeacherDashboardView$8$1 teacherMainScreenKt$TeacherDashboardView$8$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (TeacherMainScreenKt.TeacherDashboardView$lambda$302(this.$isBtEnabling$delegate)) {
                    attempts = 0;
                    teacherMainScreenKt$TeacherDashboardView$8$1 = this;
                    if (attempts < 20 || !TeacherMainScreenKt.TeacherDashboardView$lambda$302(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabling$delegate)) {
                        if (TeacherMainScreenKt.TeacherDashboardView$lambda$302(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabling$delegate)) {
                            TeacherMainScreenKt.TeacherDashboardView$lambda$300(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabled$delegate, BlePermissionUtils.INSTANCE.isBluetoothEnabled(teacherMainScreenKt$TeacherDashboardView$8$1.$context));
                            TeacherMainScreenKt.TeacherDashboardView$lambda$303(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabling$delegate, false);
                        }
                    } else {
                        teacherMainScreenKt$TeacherDashboardView$8$1.I$0 = attempts;
                        teacherMainScreenKt$TeacherDashboardView$8$1.label = 1;
                        if (DelayKt.delay(500L, teacherMainScreenKt$TeacherDashboardView$8$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (BlePermissionUtils.INSTANCE.isBluetoothEnabled(teacherMainScreenKt$TeacherDashboardView$8$1.$context)) {
                            TeacherMainScreenKt.TeacherDashboardView$lambda$300(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabled$delegate, true);
                            TeacherMainScreenKt.TeacherDashboardView$lambda$303(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabling$delegate, false);
                        } else {
                            attempts++;
                            if (attempts < 20) {
                            }
                        }
                        if (TeacherMainScreenKt.TeacherDashboardView$lambda$302(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabling$delegate)) {
                            TeacherMainScreenKt.TeacherDashboardView$lambda$300(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabled$delegate, BlePermissionUtils.INSTANCE.isBluetoothEnabled(teacherMainScreenKt$TeacherDashboardView$8$1.$context));
                            TeacherMainScreenKt.TeacherDashboardView$lambda$303(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabling$delegate, false);
                        }
                    }
                }
                return Unit.INSTANCE;
            case 1:
                attempts = this.I$0;
                ResultKt.throwOnFailure($result);
                teacherMainScreenKt$TeacherDashboardView$8$1 = this;
                if (BlePermissionUtils.INSTANCE.isBluetoothEnabled(teacherMainScreenKt$TeacherDashboardView$8$1.$context)) {
                    TeacherMainScreenKt.TeacherDashboardView$lambda$300(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabled$delegate, true);
                    TeacherMainScreenKt.TeacherDashboardView$lambda$303(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabling$delegate, false);
                } else {
                    attempts++;
                    if (attempts < 20) {
                    }
                    break;
                }
                if (TeacherMainScreenKt.TeacherDashboardView$lambda$302(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabling$delegate)) {
                    TeacherMainScreenKt.TeacherDashboardView$lambda$300(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabled$delegate, BlePermissionUtils.INSTANCE.isBluetoothEnabled(teacherMainScreenKt$TeacherDashboardView$8$1.$context));
                    TeacherMainScreenKt.TeacherDashboardView$lambda$303(teacherMainScreenKt$TeacherDashboardView$8$1.$isBtEnabling$delegate, false);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
