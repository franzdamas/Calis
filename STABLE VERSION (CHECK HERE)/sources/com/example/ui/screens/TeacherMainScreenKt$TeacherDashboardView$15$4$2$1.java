package com.example.ui.screens;

import com.example.ui.viewmodel.TeacherViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TeacherMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
final /* synthetic */ class TeacherMainScreenKt$TeacherDashboardView$15$4$2$1 extends FunctionReferenceImpl implements Function3<String, String, String, Unit> {
    TeacherMainScreenKt$TeacherDashboardView$15$4$2$1(Object obj) {
        super(3, obj, TeacherViewModel.class, "updateAttendanceStatus", "updateAttendanceStatus(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, String str3) {
        invoke2(str, str2, str3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String p0, String p1, String p2) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        ((TeacherViewModel) this.receiver).updateAttendanceStatus(p0, p1, p2);
    }
}
