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

/* JADX INFO: compiled from: StudentMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.StudentMainScreenKt$StudentMainScreen$2$1", f = "StudentMainScreen.kt", i = {0}, l = {242}, m = "invokeSuspend", n = {"attempts"}, s = {"I$0"})
final class StudentMainScreenKt$StudentMainScreen$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<Boolean> $isBtEnabled$delegate;
    final /* synthetic */ MutableState<Boolean> $isBtEnabling$delegate;
    int I$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StudentMainScreenKt$StudentMainScreen$2$1(Context context, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, Continuation<? super StudentMainScreenKt$StudentMainScreen$2$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$isBtEnabling$delegate = mutableState;
        this.$isBtEnabled$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StudentMainScreenKt$StudentMainScreen$2$1(this.$context, this.$isBtEnabling$delegate, this.$isBtEnabled$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StudentMainScreenKt$StudentMainScreen$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x002b  */
    /* JADX WARN: Code duplicated, block: B:19:0x004d  */
    /* JADX WARN: Code duplicated, block: B:20:0x0058  */
    /* JADX WARN: Code duplicated, block: B:23:0x0063  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0040 -> B:17:0x0043). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int attempts;
        StudentMainScreenKt$StudentMainScreen$2$1 studentMainScreenKt$StudentMainScreen$2$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (StudentMainScreenKt.StudentMainScreen$lambda$31(this.$isBtEnabling$delegate)) {
                    attempts = 0;
                    studentMainScreenKt$StudentMainScreen$2$1 = this;
                    if (attempts < 20 || !StudentMainScreenKt.StudentMainScreen$lambda$31(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabling$delegate)) {
                        if (StudentMainScreenKt.StudentMainScreen$lambda$31(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabling$delegate)) {
                            StudentMainScreenKt.StudentMainScreen$lambda$29(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabled$delegate, BlePermissionUtils.INSTANCE.isBluetoothEnabled(studentMainScreenKt$StudentMainScreen$2$1.$context));
                            StudentMainScreenKt.StudentMainScreen$lambda$32(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabling$delegate, false);
                        }
                    } else {
                        studentMainScreenKt$StudentMainScreen$2$1.I$0 = attempts;
                        studentMainScreenKt$StudentMainScreen$2$1.label = 1;
                        if (DelayKt.delay(500L, studentMainScreenKt$StudentMainScreen$2$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (BlePermissionUtils.INSTANCE.isBluetoothEnabled(studentMainScreenKt$StudentMainScreen$2$1.$context)) {
                            StudentMainScreenKt.StudentMainScreen$lambda$29(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabled$delegate, true);
                            StudentMainScreenKt.StudentMainScreen$lambda$32(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabling$delegate, false);
                        } else {
                            attempts++;
                            if (attempts < 20) {
                            }
                        }
                        if (StudentMainScreenKt.StudentMainScreen$lambda$31(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabling$delegate)) {
                            StudentMainScreenKt.StudentMainScreen$lambda$29(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabled$delegate, BlePermissionUtils.INSTANCE.isBluetoothEnabled(studentMainScreenKt$StudentMainScreen$2$1.$context));
                            StudentMainScreenKt.StudentMainScreen$lambda$32(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabling$delegate, false);
                        }
                    }
                }
                return Unit.INSTANCE;
            case 1:
                attempts = this.I$0;
                ResultKt.throwOnFailure($result);
                studentMainScreenKt$StudentMainScreen$2$1 = this;
                if (BlePermissionUtils.INSTANCE.isBluetoothEnabled(studentMainScreenKt$StudentMainScreen$2$1.$context)) {
                    StudentMainScreenKt.StudentMainScreen$lambda$29(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabled$delegate, true);
                    StudentMainScreenKt.StudentMainScreen$lambda$32(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabling$delegate, false);
                } else {
                    attempts++;
                    if (attempts < 20) {
                    }
                    break;
                }
                if (StudentMainScreenKt.StudentMainScreen$lambda$31(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabling$delegate)) {
                    StudentMainScreenKt.StudentMainScreen$lambda$29(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabled$delegate, BlePermissionUtils.INSTANCE.isBluetoothEnabled(studentMainScreenKt$StudentMainScreen$2$1.$context));
                    StudentMainScreenKt.StudentMainScreen$lambda$32(studentMainScreenKt$StudentMainScreen$2$1.$isBtEnabling$delegate, false);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
