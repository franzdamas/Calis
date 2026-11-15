package com.example;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.example.ble.BleManager;
import com.example.ble.CalisBleService;
import com.example.data.db.StudentProfileEntity;
import com.example.data.db.TeacherEntity;
import com.example.ui.screens.AuthScreenKt;
import com.example.ui.screens.LoginScreenKt;
import com.example.ui.screens.StartupLoadingScreenKt;
import com.example.ui.screens.StudentMainScreenKt;
import com.example.ui.screens.TeacherMainScreenKt;
import com.example.ui.viewmodel.AuthViewModel;
import com.example.ui.viewmodel.StudentViewModel;
import com.example.ui.viewmodel.TeacherViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b¨\u0006\t²\u0006\f\u0010\n\u001a\u0004\u0018\u00010\u000bX\u008a\u0084\u0002²\u0006\f\u0010\f\u001a\u0004\u0018\u00010\rX\u008a\u0084\u0002²\u0006\f\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u008a\u0084\u0002²\u0006\n\u0010\u0010\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010\u0013\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010\u0014\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010\u0015\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0017X\u008a\u008e\u0002²\u0006\n\u0010\u0018\u001a\u00020\u000fX\u008a\u008e\u0002"}, d2 = {"CalisAppNavigation", "", "authViewModel", "Lcom/example/ui/viewmodel/AuthViewModel;", "teacherViewModel", "Lcom/example/ui/viewmodel/TeacherViewModel;", "studentViewModel", "Lcom/example/ui/viewmodel/StudentViewModel;", "(Lcom/example/ui/viewmodel/AuthViewModel;Lcom/example/ui/viewmodel/TeacherViewModel;Lcom/example/ui/viewmodel/StudentViewModel;Landroidx/compose/runtime/Composer;I)V", "app", "currentTeacher", "Lcom/example/data/db/TeacherEntity;", "currentStudentProfile", "Lcom/example/data/db/StudentProfileEntity;", "userRole", "", "saveLogin", "", "isLoggedIn", "isDbLoaded", "hasAccount", "activeScreen", "authScreenInitialStep", "", "startupStatusText"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class MainActivityKt {
    static final Unit CalisAppNavigation$lambda$36(AuthViewModel authViewModel, TeacherViewModel teacherViewModel, StudentViewModel studentViewModel, int i, Composer composer, int i2) {
        CalisAppNavigation(authViewModel, teacherViewModel, studentViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:128:0x04f3 A[PHI: r12
      0x04f3: PHI (r12v6 '$composer' androidx.compose.runtime.Composer) = 
      (r12v4 '$composer' androidx.compose.runtime.Composer)
      (r12v5 '$composer' androidx.compose.runtime.Composer)
      (r12v7 '$composer' androidx.compose.runtime.Composer)
     binds: [B:127:0x04f1, B:105:0x0422, B:101:0x03f5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:90:0x036a  */
    public static final void CalisAppNavigation(AuthViewModel authViewModel, TeacherViewModel teacherViewModel, StudentViewModel studentViewModel, Composer $composer, final int $changed) {
        Object objMutableStateOf$default;
        Object objMutableStateOf$default2;
        Object objMutableStateOf$default3;
        boolean isAlreadyRunningInBackground;
        Object obj;
        TeacherViewModel teacherViewModel2;
        MutableState activeScreen$delegate;
        final StudentViewModel studentViewModel2;
        MutableState startupStatusText$delegate;
        final MutableState authScreenInitialStep$delegate;
        final MutableState activeScreen$delegate2;
        Composer $composer2;
        MainActivityKt$CalisAppNavigation$7$1 mainActivityKt$CalisAppNavigation$7$1;
        Object obj2;
        Object obj3;
        MainActivityKt$CalisAppNavigation$10$1 mainActivityKt$CalisAppNavigation$10$1;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        final AuthViewModel authViewModel2 = authViewModel;
        final TeacherViewModel teacherViewModel3 = teacherViewModel;
        Intrinsics.checkNotNullParameter(authViewModel2, "authViewModel");
        Intrinsics.checkNotNullParameter(teacherViewModel3, "teacherViewModel");
        Intrinsics.checkNotNullParameter(studentViewModel, "studentViewModel");
        Composer $composer3 = $composer.startRestartGroup(-113584359);
        ComposerKt.sourceInformation($composer3, "C(CalisAppNavigation)P(!1,2)156@6776L7,157@6839L16,158@6925L16,159@6985L16,160@7047L16,161@7111L16,162@7175L16,163@7239L16,169@7537L104,172@7675L30,173@7735L48,175@7840L695,175@7789L746,193@8562L2252,193@8541L2273:MainActivity.kt#to5c3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(authViewModel2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(teacherViewModel3) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(studentViewModel) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            studentViewModel2 = studentViewModel;
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-113584359, $dirty2, -1, "com.example.CalisAppNavigation (MainActivity.kt:155)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) objConsume;
            boolean isAlreadyRunningInBackground2 = true;
            SnapshotStateKt.collectAsState(authViewModel2.getCurrentTeacher(), null, $composer3, 0, 1);
            SnapshotStateKt.collectAsState(authViewModel2.getCurrentStudentProfile(), null, $composer3, 0, 1);
            final State userRole$delegate = SnapshotStateKt.collectAsState(authViewModel2.getUserRole(), null, $composer3, 0, 1);
            SnapshotStateKt.collectAsState(authViewModel2.getSaveLogin(), null, $composer3, 0, 1);
            SnapshotStateKt.collectAsState(authViewModel2.isLoggedIn(), null, $composer3, 0, 1);
            State isDbLoaded$delegate = SnapshotStateKt.collectAsState(authViewModel2.isDbLoaded(), null, $composer3, 0, 1);
            SnapshotStateKt.collectAsState(authViewModel2.getHasAccount(), null, $composer3, 0, 1);
            boolean isBleConnected = teacherViewModel3.getBleManager().getConnectionState().getValue() instanceof BleManager.ConnectionState.Connected;
            boolean isBgServiceRunning = CalisBleService.INSTANCE.isServiceRunning();
            if (!isBleConnected && !isBgServiceRunning) {
                isAlreadyRunningInBackground2 = false;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1780356255, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(isAlreadyRunningInBackground2 ? "TEACHER_MAIN" : "STARTUP", null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
            } else {
                objMutableStateOf$default = objRememberedValue;
            }
            MutableState activeScreen$delegate3 = (MutableState) objMutableStateOf$default;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1780351913, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default2);
            } else {
                objMutableStateOf$default2 = objRememberedValue2;
            }
            MutableState authScreenInitialStep$delegate2 = (MutableState) objMutableStateOf$default2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1780349975, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("Starting Calis...", null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default3);
            } else {
                objMutableStateOf$default3 = objRememberedValue3;
            }
            MutableState startupStatusText$delegate2 = (MutableState) objMutableStateOf$default3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String strCalisAppNavigation$lambda$8 = CalisAppNavigation$lambda$8(activeScreen$delegate3);
            String strCalisAppNavigation$lambda$2 = CalisAppNavigation$lambda$2(userRole$delegate);
            Boolean boolValueOf = Boolean.valueOf(CalisAppNavigation$lambda$5(isDbLoaded$delegate));
            ComposerKt.sourceInformationMarkerStart($composer3, -1780345968, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChanged = $composer3.changed(isDbLoaded$delegate) | $composer3.changedInstance(teacherViewModel3) | $composer3.changed(userRole$delegate) | $composer3.changedInstance(context);
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                isAlreadyRunningInBackground = isAlreadyRunningInBackground2;
                obj = "TEACHER_MAIN";
                MainActivityKt$CalisAppNavigation$1$1 mainActivityKt$CalisAppNavigation$1$1 = new MainActivityKt$CalisAppNavigation$1$1(teacherViewModel3, context, isDbLoaded$delegate, userRole$delegate, activeScreen$delegate3, null);
                teacherViewModel2 = teacherViewModel3;
                activeScreen$delegate = activeScreen$delegate3;
                objRememberedValue4 = mainActivityKt$CalisAppNavigation$1$1;
                $composer3.updateRememberedValue(objRememberedValue4);
            } else {
                teacherViewModel2 = teacherViewModel3;
                activeScreen$delegate = activeScreen$delegate3;
                obj = "TEACHER_MAIN";
                isAlreadyRunningInBackground = isAlreadyRunningInBackground2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(strCalisAppNavigation$lambda$8, strCalisAppNavigation$lambda$2, boolValueOf, (Function2) objRememberedValue4, $composer3, 0);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1780321307, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChanged2 = $composer3.changed(isAlreadyRunningInBackground) | $composer3.changedInstance(authViewModel2) | $composer3.changedInstance(teacherViewModel2) | $composer3.changedInstance(studentViewModel);
            Object objRememberedValue5 = $composer3.rememberedValue();
            if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                TeacherViewModel teacherViewModel4 = teacherViewModel2;
                MutableState activeScreen$delegate4 = activeScreen$delegate;
                MainActivityKt$CalisAppNavigation$2$1 mainActivityKt$CalisAppNavigation$2$1 = new MainActivityKt$CalisAppNavigation$2$1(isAlreadyRunningInBackground, authViewModel2, teacherViewModel4, studentViewModel, activeScreen$delegate4, startupStatusText$delegate2, authScreenInitialStep$delegate2, null);
                authViewModel2 = authViewModel2;
                studentViewModel2 = studentViewModel;
                startupStatusText$delegate = startupStatusText$delegate2;
                authScreenInitialStep$delegate = authScreenInitialStep$delegate2;
                teacherViewModel3 = teacherViewModel4;
                activeScreen$delegate2 = activeScreen$delegate4;
                objRememberedValue5 = mainActivityKt$CalisAppNavigation$2$1;
                $composer3.updateRememberedValue(objRememberedValue5);
            } else {
                studentViewModel2 = studentViewModel;
                teacherViewModel3 = teacherViewModel2;
                startupStatusText$delegate = startupStatusText$delegate2;
                authScreenInitialStep$delegate = authScreenInitialStep$delegate2;
                activeScreen$delegate2 = activeScreen$delegate;
            }
            ComposerKt.sourceInformationMarkerEnd(r3);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, r3, 6);
            String strCalisAppNavigation$lambda$9 = CalisAppNavigation$lambda$8(activeScreen$delegate2);
            switch (strCalisAppNavigation$lambda$9.hashCode()) {
                case -2027165674:
                    final MutableState activeScreen$delegate5 = activeScreen$delegate2;
                    $composer2 = r3;
                    final MutableState authScreenInitialStep$delegate3 = authScreenInitialStep$delegate;
                    if (!strCalisAppNavigation$lambda$9.equals(obj)) {
                        authViewModel2 = authViewModel;
                        $composer2.startReplaceGroup(636050025);
                        $composer2.endReplaceGroup();
                        Unit unit2 = Unit.INSTANCE;
                    } else {
                        $composer2.startReplaceGroup(647630633);
                        ComposerKt.sourceInformation($composer2, "278@11716L202,278@11680L238,285@12077L62,288@12176L465,282@11931L724");
                        ComposerKt.sourceInformationMarkerStart($composer2, -1780222429, "CC(remember):MainActivity.kt#9igjgp");
                        boolean zChangedInstance = $composer2.changedInstance(context);
                        Object objRememberedValue6 = $composer2.rememberedValue();
                        if (zChangedInstance || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            mainActivityKt$CalisAppNavigation$7$1 = new MainActivityKt$CalisAppNavigation$7$1(context, null);
                            $composer2.updateRememberedValue(mainActivityKt$CalisAppNavigation$7$1);
                        } else {
                            mainActivityKt$CalisAppNavigation$7$1 = objRememberedValue6;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        EffectsKt.LaunchedEffect("teacher_reminders", (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) mainActivityKt$CalisAppNavigation$7$1, $composer2, 6);
                        ComposerKt.sourceInformationMarkerStart($composer2, -1780211017, "CC(remember):MainActivity.kt#9igjgp");
                        Object objRememberedValue7 = $composer2.rememberedValue();
                        if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                            obj2 = new Function0() { // from class: com.example.MainActivityKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.CalisAppNavigation$lambda$28$lambda$27(activeScreen$delegate5);
                                }
                            };
                            $composer2.updateRememberedValue(obj2);
                        } else {
                            obj2 = objRememberedValue7;
                        }
                        Function0 function0 = (Function0) obj2;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerStart($composer2, -1780207446, "CC(remember):MainActivity.kt#9igjgp");
                        boolean zChangedInstance2 = $composer2.changedInstance(context) | $composer2.changedInstance(teacherViewModel3);
                        Object objRememberedValue8 = $composer2.rememberedValue();
                        if (zChangedInstance2 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                            obj3 = new Function0() { // from class: com.example.MainActivityKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.CalisAppNavigation$lambda$30$lambda$29(context, teacherViewModel3, authScreenInitialStep$delegate3, activeScreen$delegate5);
                                }
                            };
                            $composer2.updateRememberedValue(obj3);
                        } else {
                            obj3 = objRememberedValue8;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        authViewModel2 = authViewModel;
                        TeacherViewModel teacherViewModel5 = teacherViewModel3;
                        TeacherMainScreenKt.TeacherMainScreen(teacherViewModel5, authViewModel2, function0, (Function0) obj3, $composer2, (($dirty2 >> 3) & 14) | 384 | (($dirty2 << 3) & 112));
                        teacherViewModel3 = teacherViewModel5;
                        $composer2.endReplaceGroup();
                        Unit unit3 = Unit.INSTANCE;
                    }
                    break;
                case -1967111843:
                    final MutableState activeScreen$delegate6 = activeScreen$delegate2;
                    $composer2 = r3;
                    final MutableState authScreenInitialStep$delegate4 = authScreenInitialStep$delegate;
                    if (!strCalisAppNavigation$lambda$9.equals("STUDENT_MAIN")) {
                        authViewModel2 = authViewModel;
                        $composer2.startReplaceGroup(636050025);
                        $composer2.endReplaceGroup();
                        Unit unit4 = Unit.INSTANCE;
                    } else {
                        $composer2.startReplaceGroup(648674372);
                        ComposerKt.sourceInformation($composer2, "302@12768L207,302@12732L243,309@13134L62,312@13233L465,306@12988L724");
                        ComposerKt.sourceInformationMarkerStart($composer2, -1780188760, "CC(remember):MainActivity.kt#9igjgp");
                        boolean zChangedInstance3 = $composer2.changedInstance(context);
                        Object objRememberedValue9 = $composer2.rememberedValue();
                        if (zChangedInstance3 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                            mainActivityKt$CalisAppNavigation$10$1 = new MainActivityKt$CalisAppNavigation$10$1(context, null);
                            $composer2.updateRememberedValue(mainActivityKt$CalisAppNavigation$10$1);
                        } else {
                            mainActivityKt$CalisAppNavigation$10$1 = objRememberedValue9;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        EffectsKt.LaunchedEffect("student_reminders", (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) mainActivityKt$CalisAppNavigation$10$1, $composer2, 6);
                        ComposerKt.sourceInformationMarkerStart($composer2, -1780177193, "CC(remember):MainActivity.kt#9igjgp");
                        Object objRememberedValue10 = $composer2.rememberedValue();
                        if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                            obj4 = new Function0() { // from class: com.example.MainActivityKt$$ExternalSyntheticLambda6
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.CalisAppNavigation$lambda$33$lambda$32(activeScreen$delegate6);
                                }
                            };
                            $composer2.updateRememberedValue(obj4);
                        } else {
                            obj4 = objRememberedValue10;
                        }
                        Function0 function1 = (Function0) obj4;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerStart($composer2, -1780173622, "CC(remember):MainActivity.kt#9igjgp");
                        boolean zChangedInstance4 = $composer2.changedInstance(context) | $composer2.changedInstance(studentViewModel2);
                        Object objRememberedValue11 = $composer2.rememberedValue();
                        if (zChangedInstance4 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                            obj5 = new Function0() { // from class: com.example.MainActivityKt$$ExternalSyntheticLambda7
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.CalisAppNavigation$lambda$35$lambda$34(context, studentViewModel2, authScreenInitialStep$delegate4, activeScreen$delegate6);
                                }
                            };
                            $composer2.updateRememberedValue(obj5);
                        } else {
                            obj5 = objRememberedValue11;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        authViewModel2 = authViewModel;
                        StudentMainScreenKt.StudentMainScreen(studentViewModel2, authViewModel2, function1, (Function0) obj5, $composer2, (($dirty2 >> 6) & 14) | 384 | (($dirty2 << 3) & 112));
                        $composer2.endReplaceGroup();
                        Unit unit5 = Unit.INSTANCE;
                        teacherViewModel3 = teacherViewModel3;
                    }
                    break;
                case -1179201955:
                    $composer2 = r3;
                    if (!strCalisAppNavigation$lambda$9.equals("STARTUP")) {
                        authViewModel2 = authViewModel;
                        $composer2.startReplaceGroup(636050025);
                        $composer2.endReplaceGroup();
                        Unit unit6 = Unit.INSTANCE;
                    } else {
                        $composer2.startReplaceGroup(646829562);
                        ComposerKt.sourceInformation($composer2, "255@10877L55");
                        StartupLoadingScreenKt.StartupLoadingScreen(CalisAppNavigation$lambda$14(startupStatusText$delegate), null, $composer2, 0, 2);
                        $composer2.endReplaceGroup();
                        Unit unit7 = Unit.INSTANCE;
                        authViewModel2 = authViewModel;
                        teacherViewModel3 = teacherViewModel3;
                    }
                    break;
                case 2020776:
                    if (!strCalisAppNavigation$lambda$9.equals("AUTH")) {
                        $composer2 = r3;
                        $composer2.startReplaceGroup(636050025);
                        $composer2.endReplaceGroup();
                        Unit unit8 = Unit.INSTANCE;
                    } else {
                        r3.startReplaceGroup(-1780246513);
                        ComposerKt.sourceInformation(r3, "261@11098L33,262@11165L33,258@10962L246");
                        int iCalisAppNavigation$lambda$11 = CalisAppNavigation$lambda$11(authScreenInitialStep$delegate);
                        ComposerKt.sourceInformationMarkerStart(r3, -1780242374, "CC(remember):MainActivity.kt#9igjgp");
                        Object objRememberedValue12 = r3.rememberedValue();
                        if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                            obj6 = new Function0() { // from class: com.example.MainActivityKt$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.CalisAppNavigation$lambda$19$lambda$18(activeScreen$delegate2);
                                }
                            };
                            r3.updateRememberedValue(obj6);
                        } else {
                            obj6 = objRememberedValue12;
                        }
                        Function0 function2 = (Function0) obj6;
                        ComposerKt.sourceInformationMarkerEnd(r3);
                        ComposerKt.sourceInformationMarkerStart(r3, -1780240230, "CC(remember):MainActivity.kt#9igjgp");
                        Object objRememberedValue13 = r3.rememberedValue();
                        if (objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                            obj7 = new Function0() { // from class: com.example.MainActivityKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.CalisAppNavigation$lambda$21$lambda$20(activeScreen$delegate2);
                                }
                            };
                            r3.updateRememberedValue(obj7);
                        } else {
                            obj7 = objRememberedValue13;
                        }
                        ComposerKt.sourceInformationMarkerEnd(r3);
                        AuthScreenKt.AuthScreen(authViewModel, iCalisAppNavigation$lambda$11, function2, (Function0) obj7, r3, ($dirty2 & 14) | 3456, 0);
                        $composer2 = r3;
                        $composer2.endReplaceGroup();
                        Unit unit9 = Unit.INSTANCE;
                        authViewModel2 = authViewModel;
                        teacherViewModel3 = teacherViewModel3;
                    }
                    break;
                case 72611657:
                    if (!strCalisAppNavigation$lambda$9.equals("LOGIN")) {
                        $composer2 = r3;
                        $composer2.startReplaceGroup(636050025);
                        $composer2.endReplaceGroup();
                        Unit unit10 = Unit.INSTANCE;
                    } else {
                        r3.startReplaceGroup(-1780237831);
                        ComposerKt.sourceInformation(r3, "267@11314L108,270@11463L140,265@11229L384");
                        ComposerKt.sourceInformationMarkerStart(r3, -1780235387, "CC(remember):MainActivity.kt#9igjgp");
                        boolean zChanged3 = r3.changed(userRole$delegate);
                        Object objRememberedValue14 = r3.rememberedValue();
                        if (zChanged3 || objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                            obj8 = new Function0() { // from class: com.example.MainActivityKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.CalisAppNavigation$lambda$23$lambda$22(userRole$delegate, activeScreen$delegate2);
                                }
                            };
                            r3.updateRememberedValue(obj8);
                        } else {
                            obj8 = objRememberedValue14;
                        }
                        Function0 function3 = (Function0) obj8;
                        ComposerKt.sourceInformationMarkerEnd(r3);
                        ComposerKt.sourceInformationMarkerStart(r3, -1780230587, "CC(remember):MainActivity.kt#9igjgp");
                        boolean zChangedInstance5 = r3.changedInstance(authViewModel2);
                        Object objRememberedValue15 = r3.rememberedValue();
                        if (zChangedInstance5 || objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                            obj9 = new Function0() { // from class: com.example.MainActivityKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.CalisAppNavigation$lambda$25$lambda$24(authViewModel2, authScreenInitialStep$delegate, activeScreen$delegate2);
                                }
                            };
                            r3.updateRememberedValue(obj9);
                        } else {
                            obj9 = objRememberedValue15;
                        }
                        ComposerKt.sourceInformationMarkerEnd(r3);
                        LoginScreenKt.LoginScreen(authViewModel2, function3, (Function0) obj9, r3, $dirty2 & 14);
                        r3.endReplaceGroup();
                        Unit unit11 = Unit.INSTANCE;
                        teacherViewModel3 = teacherViewModel3;
                        $composer2 = r3;
                    }
                    break;
                default:
                    $composer2 = $composer3;
                    $composer2.startReplaceGroup(636050025);
                    $composer2.endReplaceGroup();
                    Unit unit12 = Unit.INSTANCE;
                    break;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.MainActivityKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj10, Object obj11) {
                    return MainActivityKt.CalisAppNavigation$lambda$36(authViewModel2, teacherViewModel3, studentViewModel2, $changed, (Composer) obj10, ((Integer) obj11).intValue());
                }
            });
        }
    }

    private static final TeacherEntity CalisAppNavigation$lambda$0(State<TeacherEntity> state) {
        return (TeacherEntity) state.getValue();
    }

    private static final StudentProfileEntity CalisAppNavigation$lambda$1(State<StudentProfileEntity> state) {
        return (StudentProfileEntity) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String CalisAppNavigation$lambda$2(State<String> state) {
        return (String) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CalisAppNavigation$lambda$5(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String CalisAppNavigation$lambda$8(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final int CalisAppNavigation$lambda$11(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CalisAppNavigation$lambda$12(MutableState<Integer> mutableState, int i) {
        mutableState.setValue(Integer.valueOf(i));
    }

    private static final String CalisAppNavigation$lambda$14(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit CalisAppNavigation$lambda$19$lambda$18(MutableState $activeScreen$delegate) {
        $activeScreen$delegate.setValue("TEACHER_MAIN");
        return Unit.INSTANCE;
    }

    static final Unit CalisAppNavigation$lambda$21$lambda$20(MutableState $activeScreen$delegate) {
        $activeScreen$delegate.setValue("STUDENT_MAIN");
        return Unit.INSTANCE;
    }

    static final Unit CalisAppNavigation$lambda$23$lambda$22(State $userRole$delegate, MutableState $activeScreen$delegate) {
        $activeScreen$delegate.setValue(Intrinsics.areEqual(CalisAppNavigation$lambda$2($userRole$delegate), "TEACHER") ? "TEACHER_MAIN" : "STUDENT_MAIN");
        return Unit.INSTANCE;
    }

    static final Unit CalisAppNavigation$lambda$25$lambda$24(AuthViewModel $authViewModel, MutableState $authScreenInitialStep$delegate, MutableState $activeScreen$delegate) {
        $authViewModel.selectRole("");
        CalisAppNavigation$lambda$12($authScreenInitialStep$delegate, 4);
        $activeScreen$delegate.setValue("AUTH");
        return Unit.INSTANCE;
    }

    static final Unit CalisAppNavigation$lambda$28$lambda$27(MutableState $activeScreen$delegate) {
        $activeScreen$delegate.setValue("LOGIN");
        return Unit.INSTANCE;
    }

    static final Unit CalisAppNavigation$lambda$30$lambda$29(Context $context, TeacherViewModel $teacherViewModel, MutableState $authScreenInitialStep$delegate, MutableState $activeScreen$delegate) {
        $context.getSharedPreferences("calis_app_prefs", 0).edit().remove("has_seen_prototype_dialog_teacher").remove("show_prototype_dialog_teacher").apply();
        $teacherViewModel.resetAllState();
        CalisAppNavigation$lambda$12($authScreenInitialStep$delegate, 0);
        $activeScreen$delegate.setValue("AUTH");
        return Unit.INSTANCE;
    }

    static final Unit CalisAppNavigation$lambda$33$lambda$32(MutableState $activeScreen$delegate) {
        $activeScreen$delegate.setValue("LOGIN");
        return Unit.INSTANCE;
    }

    static final Unit CalisAppNavigation$lambda$35$lambda$34(Context $context, StudentViewModel $studentViewModel, MutableState $authScreenInitialStep$delegate, MutableState $activeScreen$delegate) {
        $context.getSharedPreferences("calis_app_prefs", 0).edit().remove("has_seen_prototype_dialog_student").remove("show_prototype_dialog_student").apply();
        $studentViewModel.resetAllState();
        CalisAppNavigation$lambda$12($authScreenInitialStep$delegate, 0);
        $activeScreen$delegate.setValue("AUTH");
        return Unit.INSTANCE;
    }
}
