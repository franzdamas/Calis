package com.example.ui.screens;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.Toast;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.LockKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.profileinstaller.ProfileVerifier;
import com.example.data.db.StudentProfileEntity;
import com.example.data.db.TeacherEntity;
import com.example.ui.theme.ThemeKt;
import com.example.ui.viewmodel.AuthViewModel;
import com.example.util.BlePermissionUtils;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LoginScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010\r¨\u0006\u000e²\u0006\n\u0010\u000f\u001a\u00020\u000bX\u008a\u0084\u0002²\u0006\n\u0010\u0010\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\f\u0010\u0012\u001a\u0004\u0018\u00010\u000bX\u008a\u0084\u0002²\u0006\f\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u008a\u0084\u0002²\u0006\f\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u008a\u0084\u0002²\u0006\n\u0010\u0017\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\f\u0010\u0018\u001a\u0004\u0018\u00010\u000bX\u008a\u008e\u0002"}, d2 = {"LoginScreen", "", "authViewModel", "Lcom/example/ui/viewmodel/AuthViewModel;", "onLoginSuccess", "Lkotlin/Function0;", "onSwitchAccountRequested", "(Lcom/example/ui/viewmodel/AuthViewModel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "NumberPadLogin", "onDigit", "Lkotlin/Function1;", "", "onBackspace", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app", "savedPin", "enableBiometrics", "", "userRole", "currentTeacher", "Lcom/example/data/db/TeacherEntity;", "currentStudentProfile", "Lcom/example/data/db/StudentProfileEntity;", "pinCode", "errorMessage"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class LoginScreenKt {
    static final Unit LoginScreen$lambda$27(AuthViewModel authViewModel, Function0 function0, Function0 function1, int i, Composer composer, int i2) {
        LoginScreen(authViewModel, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit NumberPadLogin$lambda$35(Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        NumberPadLogin(function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void LoginScreen(final AuthViewModel authViewModel, final Function0<Unit> onLoginSuccess, final Function0<Unit> onSwitchAccountRequested, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(authViewModel, "authViewModel");
        Intrinsics.checkNotNullParameter(onLoginSuccess, "onLoginSuccess");
        Intrinsics.checkNotNullParameter(onSwitchAccountRequested, "onSwitchAccountRequested");
        Composer $composer2 = $composer.startRestartGroup(-311025096);
        ComposerKt.sourceInformation($composer2, "C(LoginScreen)59@2475L11278,59@2439L11314:LoginScreen.kt#2thlc2");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(authViewModel) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(onLoginSuccess) ? 32 : 16;
        }
        if (($dirty & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-311025096, $dirty, -1, "com.example.ui.screens.LoginScreen (LoginScreen.kt:58)");
            }
            ThemeKt.CalisMobileTheme(false, false, ComposableLambdaKt.rememberComposableLambda(1804209014, true, new Function2() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LoginScreenKt.LoginScreen$lambda$26(authViewModel, onLoginSuccess, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LoginScreenKt.LoginScreen$lambda$27(authViewModel, onLoginSuccess, onSwitchAccountRequested, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:104:0x04b8  */
    /* JADX WARN: Code duplicated, block: B:107:0x04c4  */
    /* JADX WARN: Code duplicated, block: B:108:0x04ca  */
    /* JADX WARN: Code duplicated, block: B:119:0x05f4  */
    /* JADX WARN: Code duplicated, block: B:122:0x0600  */
    /* JADX WARN: Code duplicated, block: B:123:0x0606  */
    /* JADX WARN: Code duplicated, block: B:134:0x07ef  */
    /* JADX WARN: Code duplicated, block: B:135:0x0866  */
    /* JADX WARN: Code duplicated, block: B:138:0x08fb  */
    /* JADX WARN: Code duplicated, block: B:141:0x0907  */
    /* JADX WARN: Code duplicated, block: B:142:0x090d  */
    /* JADX WARN: Code duplicated, block: B:154:0x09b4  */
    /* JADX WARN: Code duplicated, block: B:156:0x09be  */
    /* JADX WARN: Code duplicated, block: B:157:0x09c0  */
    /* JADX WARN: Code duplicated, block: B:160:0x09e6  */
    /* JADX WARN: Code duplicated, block: B:161:0x0a03  */
    /* JADX WARN: Code duplicated, block: B:164:0x0a38  */
    /* JADX WARN: Code duplicated, block: B:165:0x0a50  */
    /* JADX WARN: Code duplicated, block: B:173:0x0af3  */
    /* JADX WARN: Code duplicated, block: B:176:0x0b2e  */
    /* JADX WARN: Code duplicated, block: B:177:0x0b3c  */
    /* JADX WARN: Code duplicated, block: B:189:0x0be8  */
    /* JADX WARN: Code duplicated, block: B:192:0x0c25  */
    /* JADX WARN: Code duplicated, block: B:89:0x0362  */
    /* JADX WARN: Code duplicated, block: B:92:0x036e  */
    /* JADX WARN: Code duplicated, block: B:93:0x0374  */
    static final Unit LoginScreen$lambda$26(AuthViewModel $authViewModel, Function0 $onLoginSuccess, Composer $composer, int $changed) {
        String fullName;
        String fullName2;
        String fullName3;
        Object objMutableStateOf$default;
        Object objMutableStateOf$default2;
        Object objValueOf;
        Context context;
        boolean supportsBiometrics;
        State enableBiometrics$delegate;
        final Context context2;
        LoginScreenKt$LoginScreen$1$1$1 loginScreenKt$LoginScreen$1$1$1;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer composerM3652constructorimpl;
        int currentCompositeKeyHash2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function1;
        Composer composerM3652constructorimpl2;
        int currentCompositeKeyHash3;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function2;
        Composer composerM3652constructorimpl3;
        MeasurePolicy measurePolicyRowMeasurePolicy;
        int currentCompositeKeyHash4;
        Function0<ComposeUiNode> constructor4;
        Function0<ComposeUiNode> function3;
        Composer composerM3652constructorimpl4;
        int i;
        int i2;
        boolean zChanged;
        Object objRememberedValue;
        Composer composer;
        final State savedPin$delegate;
        final MutableState errorMessage$delegate;
        final MutableState pinCode$delegate;
        Object objRememberedValue2;
        Object obj;
        Object obj2;
        boolean z;
        boolean z2;
        long j;
        long outline;
        final AuthViewModel authViewModel = $authViewModel;
        final Function0 function4 = $onLoginSuccess;
        ComposerKt.sourceInformation($composer, "C60@2512L7,61@2567L16,62@2647L16,63@2711L16,64@2787L16,65@2877L16,67@2921L706,79@3652L31,80@3712L42,82@3789L757,156@7725L123,156@7672L176,165@7971L11,162@7858L5889:LoginScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1804209014, $changed, -1, "com.example.ui.screens.LoginScreen.<anonymous> (LoginScreen.kt:60)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Context context3 = (Context) objConsume;
            State savedPin$delegate2 = SnapshotStateKt.collectAsState(authViewModel.getSavedPin(), null, $composer, 0, 1);
            State enableBiometrics$delegate2 = SnapshotStateKt.collectAsState(authViewModel.getEnableBiometrics(), null, $composer, 0, 1);
            State userRole$delegate = SnapshotStateKt.collectAsState(authViewModel.getUserRole(), null, $composer, 0, 1);
            State currentTeacher$delegate = SnapshotStateKt.collectAsState(authViewModel.getCurrentTeacher(), null, $composer, 0, 1);
            State currentStudentProfile$delegate = SnapshotStateKt.collectAsState(authViewModel.getCurrentStudentProfile(), null, $composer, 0, 1);
            String strLoginScreen$lambda$26$lambda$2 = LoginScreen$lambda$26$lambda$2(userRole$delegate);
            TeacherEntity teacherEntityLoginScreen$lambda$26$lambda$3 = LoginScreen$lambda$26$lambda$3(currentTeacher$delegate);
            StudentProfileEntity studentProfileEntityLoginScreen$lambda$26$lambda$4 = LoginScreen$lambda$26$lambda$4(currentStudentProfile$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1321910152, "CC(remember):LoginScreen.kt#9igjgp");
            boolean zChanged2 = $composer.changed(strLoginScreen$lambda$26$lambda$2) | $composer.changed(teacherEntityLoginScreen$lambda$26$lambda$3) | $composer.changed(studentProfileEntityLoginScreen$lambda$26$lambda$4);
            Object objRememberedValue3 = $composer.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                if (Intrinsics.areEqual(LoginScreen$lambda$26$lambda$2(userRole$delegate), "STUDENT")) {
                    StudentProfileEntity studentProfileEntityLoginScreen$lambda$26$lambda$5 = LoginScreen$lambda$26$lambda$4(currentStudentProfile$delegate);
                    if (studentProfileEntityLoginScreen$lambda$26$lambda$5 == null || (fullName3 = studentProfileEntityLoginScreen$lambda$26$lambda$5.getFullName()) == null) {
                        fullName3 = "Student Account";
                    }
                    String studentNameWithMiddleInitial = BlePermissionUtils.INSTANCE.formatStudentNameWithMiddleInitial(fullName3, context3);
                    if (StringsKt.isBlank(studentNameWithMiddleInitial)) {
                        studentNameWithMiddleInitial = "Student Account";
                    }
                    fullName = studentNameWithMiddleInitial;
                } else if (Intrinsics.areEqual(LoginScreen$lambda$26$lambda$2(userRole$delegate), "TEACHER")) {
                    TeacherEntity teacherEntityLoginScreen$lambda$26$lambda$4 = LoginScreen$lambda$26$lambda$3(currentTeacher$delegate);
                    if (teacherEntityLoginScreen$lambda$26$lambda$4 == null || (fullName2 = teacherEntityLoginScreen$lambda$26$lambda$4.getFullName()) == null) {
                        fullName2 = "Teacher Account";
                    }
                    String teacherNameWithMiddleInitial = BlePermissionUtils.INSTANCE.formatTeacherNameWithMiddleInitial(fullName2, context3);
                    if (StringsKt.isBlank(teacherNameWithMiddleInitial)) {
                        teacherNameWithMiddleInitial = "Teacher Account";
                    }
                    fullName = teacherNameWithMiddleInitial;
                } else {
                    StudentProfileEntity studentProfileEntityLoginScreen$lambda$26$lambda$6 = LoginScreen$lambda$26$lambda$4(currentStudentProfile$delegate);
                    if (studentProfileEntityLoginScreen$lambda$26$lambda$6 == null || (fullName = studentProfileEntityLoginScreen$lambda$26$lambda$6.getFullName()) == null) {
                        TeacherEntity teacherEntityLoginScreen$lambda$26$lambda$5 = LoginScreen$lambda$26$lambda$3(currentTeacher$delegate);
                        fullName = teacherEntityLoginScreen$lambda$26$lambda$5 != null ? teacherEntityLoginScreen$lambda$26$lambda$5.getFullName() : "Calis User Account";
                    }
                }
                $composer.updateRememberedValue(fullName);
            } else {
                fullName = objRememberedValue3;
            }
            String accountName = (String) fullName;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1321887435, "CC(remember):LoginScreen.kt#9igjgp");
            Object objRememberedValue4 = $composer.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer.updateRememberedValue(objMutableStateOf$default);
            } else {
                objMutableStateOf$default = objRememberedValue4;
            }
            MutableState mutableState = (MutableState) objMutableStateOf$default;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1321885504, "CC(remember):LoginScreen.kt#9igjgp");
            Object objRememberedValue5 = $composer.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer.updateRememberedValue(objMutableStateOf$default2);
            } else {
                objMutableStateOf$default2 = objRememberedValue5;
            }
            MutableState mutableState2 = (MutableState) objMutableStateOf$default2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1321882325, "CC(remember):LoginScreen.kt#9igjgp");
            boolean zChanged3 = $composer.changed(context3);
            Object objRememberedValue6 = $composer.rememberedValue();
            if (zChanged3 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                BiometricManager biometricManagerFrom = BiometricManager.from(context3);
                Intrinsics.checkNotNullExpressionValue(biometricManagerFrom, "from(...)");
                int iCanAuthenticate = biometricManagerFrom.canAuthenticate(255);
                objValueOf = Boolean.valueOf(iCanAuthenticate == 0 || iCanAuthenticate == 11 || context3.getPackageManager().hasSystemFeature("android.hardware.fingerprint") || context3.getPackageManager().hasSystemFeature("android.hardware.biometrics.face") || context3.getPackageManager().hasSystemFeature("android.hardware.biometrics.iris"));
                $composer.updateRememberedValue(objValueOf);
            } else {
                objValueOf = objRememberedValue6;
            }
            boolean supportsBiometrics2 = ((Boolean) objValueOf).booleanValue();
            ComposerKt.sourceInformationMarkerEnd($composer);
            Boolean boolValueOf = Boolean.valueOf(LoginScreen$lambda$26$lambda$1(enableBiometrics$delegate2));
            Boolean boolValueOf2 = Boolean.valueOf(supportsBiometrics2);
            ComposerKt.sourceInformationMarkerStart($composer, -1321757007, "CC(remember):LoginScreen.kt#9igjgp");
            boolean zChanged4 = $composer.changed(enableBiometrics$delegate2) | $composer.changed(supportsBiometrics2) | $composer.changedInstance(context3) | $composer.changedInstance(authViewModel) | $composer.changed(function4);
            Object objRememberedValue7 = $composer.rememberedValue();
            if (zChanged4) {
                context = context3;
            } else {
                context = context3;
                if (objRememberedValue7 != Composer.INSTANCE.getEmpty()) {
                    enableBiometrics$delegate = enableBiometrics$delegate2;
                    supportsBiometrics = supportsBiometrics2;
                    loginScreenKt$LoginScreen$1$1$1 = objRememberedValue7;
                    context2 = context;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                EffectsKt.LaunchedEffect(boolValueOf, boolValueOf2, (Function2) loginScreenKt$LoginScreen$1$1$1, $composer, 0);
                Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(BackgroundKt.m225backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getBackground(), null, 2, null), Dp.m6622constructorimpl(24));
                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.Vertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(spaceBetween, centerHorizontally, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM670padding3ABfNKs);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i3 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                composerM3652constructorimpl = Updater.m3652constructorimpl($composer);
                Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3652constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = (i3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                int i5 = ((432 >> 6) & 112) | 6;
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1440933341, "C170@8175L41,173@8260L5411:LoginScreen.kt#2thlc2");
                SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(16)), $composer, 6);
                Alignment.Horizontal centerHorizontally2 = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.Vertical center = Arrangement.INSTANCE.getCenter();
                Modifier modifierWeight$default = ColumnScope.weight$default(columnScope, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(center, centerHorizontally2, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i6 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                composerM3652constructorimpl2 = Updater.m3652constructorimpl($composer);
                Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3652constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i7 = (i6 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i8 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -197270278, "C184@8705L11,180@8530L578,195@9126L41,199@9276L10,201@9400L11,197@9185L302,207@9593L10,209@9713L11,205@9505L297,213@9820L40,217@9984L10,218@10048L11,215@9878L266,233@10621L41,236@10722L1064,259@11804L41,263@11943L344,272@12323L207,262@11897L651,280@12566L41:LoginScreen.kt#2thlc2");
                Modifier modifierM225backgroundbw27NRU$default = BackgroundKt.m225backgroundbw27NRU$default(ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), RoundedCornerShapeKt.getCircleShape()), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimaryContainer(), null, 2, null);
                Alignment center2 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM225backgroundbw27NRU$default);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i9 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                composerM3652constructorimpl3 = Updater.m3652constructorimpl($composer);
                Updater.m3659setimpl(composerM3652constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3659setimpl(composerM3652constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3652constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composerM3652constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3652constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3659setimpl(composerM3652constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i10 = (i9 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i11 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1016555673, "C190@8992L11,187@8832L258:LoginScreen.kt#2thlc2");
                IconKt.m2150Iconww6aTOc(LockKt.getLock(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(36)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(12)), $composer, 6);
                TextKt.m2693Text4IGK_g("Welcome Back", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getHeadlineMedium(), $composer, 196614, 0, 64986);
                TextKt.m2693Text4IGK_g(accountName, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface(), 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 64986);
                SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(4)), $composer, 6);
                TextKt.m2693Text4IGK_g("Enter 6-digit PIN to unlock", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 65018);
                if (LoginScreen$lambda$26$lambda$12(mutableState2) != null) {
                    $composer.startReplaceGroup(-195771615);
                    ComposerKt.sourceInformation($composer, "223@10210L40,226@10370L10,227@10438L11,224@10271L314");
                    SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(8)), $composer, 6);
                    String strLoginScreen$lambda$26$lambda$12 = LoginScreen$lambda$26$lambda$12(mutableState2);
                    Intrinsics.checkNotNull(strLoginScreen$lambda$26$lambda$12);
                    TextKt.m2693Text4IGK_g(strLoginScreen$lambda$26$lambda$12, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 64986);
                } else {
                    $composer.startReplaceGroup(-205891968);
                }
                $composer.endReplaceGroup();
                SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(20)), $composer, 6);
                Arrangement.Horizontal horizontalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(12));
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM550spacedBy0680j_4, centerVertically, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier);
                constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i12 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function3 = constructor4;
                    $composer.createNode(function3);
                } else {
                    function3 = constructor4;
                    $composer.useNode();
                }
                composerM3652constructorimpl4 = Updater.m3652constructorimpl($composer);
                Updater.m3659setimpl(composerM3652constructorimpl4, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3659setimpl(composerM3652constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3652constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    composerM3652constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM3652constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.m3659setimpl(composerM3652constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i13 = (i12 >> 6) & 14;
                i = 0;
                ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i14 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -994711552, "C:LoginScreen.kt#2thlc2");
                $composer.startReplaceGroup(522101859);
                ComposerKt.sourceInformation($composer, "*242@11012L734");
                i2 = 0;
                while (i2 < 6) {
                    if (i2 < LoginScreen$lambda$26$lambda$9(mutableState).length()) {
                        z = true;
                    } else {
                        z = false;
                    }
                    int i15 = i2;
                    z2 = z;
                    int i16 = i;
                    Modifier modifierClip = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(18)), RoundedCornerShapeKt.getCircleShape());
                    if (z2) {
                        $composer.startReplaceGroup(522113005);
                        ComposerKt.sourceInformation($composer, "247@11270L11");
                        long primary = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                        $composer.endReplaceGroup();
                        j = primary;
                    } else {
                        $composer.startReplaceGroup(522115412);
                        ComposerKt.sourceInformation($composer, "248@11345L11");
                        long surfaceVariant = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurfaceVariant();
                        $composer.endReplaceGroup();
                        j = surfaceVariant;
                    }
                    Modifier modifierM225backgroundbw27NRU$default2 = BackgroundKt.m225backgroundbw27NRU$default(modifierClip, j, null, 2, null);
                    int i17 = currentCompositeKeyHash4;
                    MeasurePolicy measurePolicy = measurePolicyRowMeasurePolicy;
                    float fM6622constructorimpl = Dp.m6622constructorimpl((float) 1.5d);
                    if (z2) {
                        $composer.startReplaceGroup(522122637);
                        ComposerKt.sourceInformation($composer, "252@11571L11");
                        outline = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                    } else {
                        $composer.startReplaceGroup(522123885);
                        ComposerKt.sourceInformation($composer, "252@11610L11");
                        outline = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutline();
                    }
                    $composer.endReplaceGroup();
                    BoxKt.Box(BorderKt.m236borderxT4_qwU(modifierM225backgroundbw27NRU$default2, fM6622constructorimpl, outline, RoundedCornerShapeKt.getCircleShape()), $composer, 0);
                    i2 = i15 + 1;
                    i = i16;
                    measurePolicyRowMeasurePolicy = measurePolicy;
                    currentCompositeKeyHash4 = i17;
                }
                $composer.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(24)), $composer, 6);
                ComposerKt.sourceInformationMarkerStart($composer, -1668827110, r1);
                zChanged = $composer.changed(savedPin$delegate2) | $composer.changedInstance(authViewModel) | $composer.changed(function4);
                objRememberedValue = $composer.rememberedValue();
                if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    composer = $composer;
                    savedPin$delegate = savedPin$delegate2;
                    errorMessage$delegate = mutableState2;
                    pinCode$delegate = mutableState;
                    objRememberedValue = new Function1() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return LoginScreenKt.LoginScreen$lambda$26$lambda$25$lambda$24$lambda$19$lambda$18(pinCode$delegate, authViewModel, function4, savedPin$delegate, errorMessage$delegate, (String) obj3);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue);
                } else {
                    composer = $composer;
                    savedPin$delegate = savedPin$delegate2;
                    pinCode$delegate = mutableState;
                    errorMessage$delegate = mutableState2;
                }
                Function1 function5 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, -1668815087, r1);
                objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    obj = new Function0() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return LoginScreenKt.LoginScreen$lambda$26$lambda$25$lambda$24$lambda$21$lambda$20(pinCode$delegate, errorMessage$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    obj = objRememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                NumberPadLogin(function5, (Function0) obj, $composer, 48);
                SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(16)), $composer, 6);
                if (LoginScreen$lambda$26$lambda$1(enableBiometrics$delegate) || !supportsBiometrics) {
                    $composer.startReplaceGroup(-205891968);
                } else {
                    $composer.startReplaceGroup(-193246572);
                    ComposerKt.sourceInformation($composer, "285@12789L27,284@12739L900");
                    RoundedCornerShape roundedCornerShapeM953RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(8));
                    Modifier modifierTestTag = TestTagKt.testTag(Modifier.INSTANCE, "login_biometric_button");
                    ComposerKt.sourceInformationMarkerStart($composer, -1668800355, "CC(remember):LoginScreen.kt#9igjgp");
                    boolean zChangedInstance = $composer.changedInstance(context2) | $composer.changedInstance(authViewModel) | $composer.changed(function4);
                    Object objRememberedValue8 = $composer.rememberedValue();
                    if (zChangedInstance || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                        obj2 = new Function0() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return LoginScreenKt.LoginScreen$lambda$26$lambda$25$lambda$24$lambda$23$lambda$22(context2, authViewModel, function4);
                            }
                        };
                        $composer.updateRememberedValue(obj2);
                    } else {
                        obj2 = objRememberedValue8;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ButtonKt.OutlinedButton((Function0) obj2, modifierTestTag, false, roundedCornerShapeM953RoundedCornerShape0680j_4, null, null, null, null, null, ComposableSingletons$LoginScreenKt.INSTANCE.m6961getLambda$200593242$app(), $composer, 805306416, 500);
                }
                $composer.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                r56.endNode();
                ComposerKt.sourceInformationMarkerEnd(r56);
                ComposerKt.sourceInformationMarkerEnd(r56);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            Context context4 = context;
            LoginScreenKt$LoginScreen$1$1$1 loginScreenKt$LoginScreen$1$1$2 = new LoginScreenKt$LoginScreen$1$1$1(supportsBiometrics2, enableBiometrics$delegate2, context4, authViewModel, function4, null);
            supportsBiometrics = supportsBiometrics2;
            enableBiometrics$delegate = enableBiometrics$delegate2;
            context2 = context4;
            authViewModel = authViewModel;
            function4 = function4;
            loginScreenKt$LoginScreen$1$1$1 = loginScreenKt$LoginScreen$1$1$2;
            $composer.updateRememberedValue(loginScreenKt$LoginScreen$1$1$1);
            ComposerKt.sourceInformationMarkerEnd($composer);
            EffectsKt.LaunchedEffect(boolValueOf, boolValueOf2, (Function2) loginScreenKt$LoginScreen$1$1$1, $composer, 0);
            Modifier modifierM670padding3ABfNKs2 = PaddingKt.m670padding3ABfNKs(BackgroundKt.m225backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getBackground(), null, 2, null), Dp.m6622constructorimpl(24));
            Alignment.Horizontal centerHorizontally3 = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical spaceBetween2 = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(spaceBetween2, centerHorizontally3, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifierM670padding3ABfNKs2);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i18 = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            composerM3652constructorimpl = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3652constructorimpl.getInserting()) {
            }
            composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash5);
            Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            int i19 = (i18 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            int i20 = ((432 >> 6) & 112) | 6;
            ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1440933341, "C170@8175L41,173@8260L5411:LoginScreen.kt#2thlc2");
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(16)), $composer, 6);
            Alignment.Horizontal centerHorizontally4 = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical center3 = Arrangement.INSTANCE.getCenter();
            Modifier modifierWeight$default2 = ColumnScope.weight$default(columnScope2, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(center3, centerHorizontally4, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default2);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i21 = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function1 = constructor2;
                $composer.createNode(function1);
            } else {
                function1 = constructor2;
                $composer.useNode();
            }
            composerM3652constructorimpl2 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyColumnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3652constructorimpl2.getInserting()) {
            }
            composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
            composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash6);
            Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
            int i22 = (i21 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i23 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -197270278, "C184@8705L11,180@8530L578,195@9126L41,199@9276L10,201@9400L11,197@9185L302,207@9593L10,209@9713L11,205@9505L297,213@9820L40,217@9984L10,218@10048L11,215@9878L266,233@10621L41,236@10722L1064,259@11804L41,263@11943L344,272@12323L207,262@11897L651,280@12566L41:LoginScreen.kt#2thlc2");
            Modifier modifierM225backgroundbw27NRU$default3 = BackgroundKt.m225backgroundbw27NRU$default(ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), RoundedCornerShapeKt.getCircleShape()), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimaryContainer(), null, 2, null);
            Alignment center4 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center4, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap7 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier($composer, modifierM225backgroundbw27NRU$default3);
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i24 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function2 = constructor3;
                $composer.createNode(function2);
            } else {
                function2 = constructor3;
                $composer.useNode();
            }
            composerM3652constructorimpl3 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl3, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3652constructorimpl3.getInserting()) {
            }
            composerM3652constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
            composerM3652constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash7);
            Updater.m3659setimpl(composerM3652constructorimpl3, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
            int i110 = (i24 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i111 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1016555673, "C190@8992L11,187@8832L258:LoginScreen.kt#2thlc2");
            IconKt.m2150Iconww6aTOc(LockKt.getLock(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(36)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 432, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(12)), $composer, 6);
            TextKt.m2693Text4IGK_g("Welcome Back", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getHeadlineMedium(), $composer, 196614, 0, 64986);
            TextKt.m2693Text4IGK_g(accountName, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface(), 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 64986);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(4)), $composer, 6);
            TextKt.m2693Text4IGK_g("Enter 6-digit PIN to unlock", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 65018);
            if (LoginScreen$lambda$26$lambda$12(mutableState2) != null) {
                $composer.startReplaceGroup(-195771615);
                ComposerKt.sourceInformation($composer, "223@10210L40,226@10370L10,227@10438L11,224@10271L314");
                SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(8)), $composer, 6);
                String strLoginScreen$lambda$26$lambda$13 = LoginScreen$lambda$26$lambda$12(mutableState2);
                Intrinsics.checkNotNull(strLoginScreen$lambda$26$lambda$13);
                TextKt.m2693Text4IGK_g(strLoginScreen$lambda$26$lambda$13, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 64986);
            } else {
                $composer.startReplaceGroup(-205891968);
            }
            $composer.endReplaceGroup();
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(20)), $composer, 6);
            Arrangement.Horizontal horizontalM550spacedBy0680j_5 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(12));
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Modifier modifier2 = Modifier.INSTANCE;
            measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM550spacedBy0680j_5, centerVertically2, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap8 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier($composer, modifier2);
            constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i112 = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function3 = constructor4;
                $composer.createNode(function3);
            } else {
                function3 = constructor4;
                $composer.useNode();
            }
            composerM3652constructorimpl4 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl4, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl4, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3652constructorimpl4.getInserting()) {
            }
            composerM3652constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
            composerM3652constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash8);
            Updater.m3659setimpl(composerM3652constructorimpl4, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
            int i113 = (i112 >> 6) & 14;
            i = 0;
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i114 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -994711552, "C:LoginScreen.kt#2thlc2");
            $composer.startReplaceGroup(522101859);
            ComposerKt.sourceInformation($composer, "*242@11012L734");
            i2 = 0;
            while (i2 < 6) {
                if (i2 < LoginScreen$lambda$26$lambda$9(mutableState).length()) {
                    z = true;
                } else {
                    z = false;
                }
                int i115 = i2;
                z2 = z;
                int i116 = i;
                Modifier modifierClip2 = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(18)), RoundedCornerShapeKt.getCircleShape());
                if (z2) {
                    $composer.startReplaceGroup(522113005);
                    ComposerKt.sourceInformation($composer, "247@11270L11");
                    long primary2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                    $composer.endReplaceGroup();
                    j = primary2;
                } else {
                    $composer.startReplaceGroup(522115412);
                    ComposerKt.sourceInformation($composer, "248@11345L11");
                    long surfaceVariant2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurfaceVariant();
                    $composer.endReplaceGroup();
                    j = surfaceVariant2;
                }
                Modifier modifierM225backgroundbw27NRU$default4 = BackgroundKt.m225backgroundbw27NRU$default(modifierClip2, j, null, 2, null);
                int i117 = currentCompositeKeyHash4;
                MeasurePolicy measurePolicy2 = measurePolicyRowMeasurePolicy;
                float fM6622constructorimpl2 = Dp.m6622constructorimpl((float) 1.5d);
                if (z2) {
                    $composer.startReplaceGroup(522122637);
                    ComposerKt.sourceInformation($composer, "252@11571L11");
                    outline = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                } else {
                    $composer.startReplaceGroup(522123885);
                    ComposerKt.sourceInformation($composer, "252@11610L11");
                    outline = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutline();
                }
                $composer.endReplaceGroup();
                BoxKt.Box(BorderKt.m236borderxT4_qwU(modifierM225backgroundbw27NRU$default4, fM6622constructorimpl2, outline, RoundedCornerShapeKt.getCircleShape()), $composer, 0);
                i2 = i115 + 1;
                i = i116;
                measurePolicyRowMeasurePolicy = measurePolicy2;
                currentCompositeKeyHash4 = i117;
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(24)), $composer, 6);
            ComposerKt.sourceInformationMarkerStart($composer, -1668827110, r1);
            zChanged = $composer.changed(savedPin$delegate2) | $composer.changedInstance(authViewModel) | $composer.changed(function4);
            objRememberedValue = $composer.rememberedValue();
            if (zChanged) {
                composer = $composer;
                savedPin$delegate = savedPin$delegate2;
                errorMessage$delegate = mutableState2;
                pinCode$delegate = mutableState;
                objRememberedValue = new Function1() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return LoginScreenKt.LoginScreen$lambda$26$lambda$25$lambda$24$lambda$19$lambda$18(pinCode$delegate, authViewModel, function4, savedPin$delegate, errorMessage$delegate, (String) obj3);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            } else {
                composer = $composer;
                savedPin$delegate = savedPin$delegate2;
                errorMessage$delegate = mutableState2;
                pinCode$delegate = mutableState;
                objRememberedValue = new Function1() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return LoginScreenKt.LoginScreen$lambda$26$lambda$25$lambda$24$lambda$19$lambda$18(pinCode$delegate, authViewModel, function4, savedPin$delegate, errorMessage$delegate, (String) obj3);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            }
            Function1 function6 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1668815087, r1);
            objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LoginScreenKt.LoginScreen$lambda$26$lambda$25$lambda$24$lambda$21$lambda$20(pinCode$delegate, errorMessage$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            NumberPadLogin(function6, (Function0) obj, $composer, 48);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(16)), $composer, 6);
            if (LoginScreen$lambda$26$lambda$1(enableBiometrics$delegate)) {
                $composer.startReplaceGroup(-205891968);
            } else {
                $composer.startReplaceGroup(-205891968);
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            r56.endNode();
            ComposerKt.sourceInformationMarkerEnd(r56);
            ComposerKt.sourceInformationMarkerEnd(r56);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final String LoginScreen$lambda$26$lambda$0(State<String> state) {
        return (String) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LoginScreen$lambda$26$lambda$1(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final String LoginScreen$lambda$26$lambda$2(State<String> state) {
        return (String) state.getValue();
    }

    private static final TeacherEntity LoginScreen$lambda$26$lambda$3(State<TeacherEntity> state) {
        return (TeacherEntity) state.getValue();
    }

    private static final StudentProfileEntity LoginScreen$lambda$26$lambda$4(State<StudentProfileEntity> state) {
        return (StudentProfileEntity) state.getValue();
    }

    private static final String LoginScreen$lambda$26$lambda$9(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String LoginScreen$lambda$26$lambda$12(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final void LoginScreen$lambda$26$verifyPin(AuthViewModel $authViewModel, Function0<Unit> function0, State<String> state, MutableState<String> mutableState, MutableState<String> mutableState2, String entered) {
        if (!Intrinsics.areEqual(entered, LoginScreen$lambda$26$lambda$0(state))) {
            if (!(LoginScreen$lambda$26$lambda$0(state).length() == 0)) {
                mutableState.setValue("Incorrect 6-Digit PIN. Please try again.");
                mutableState2.setValue("");
                return;
            }
        }
        mutableState.setValue(null);
        $authViewModel.setLoggedIn(true);
        function0.invoke();
    }

    private static final FragmentActivity LoginScreen$lambda$26$findActivity(Context $this$LoginScreen_u24lambda_u2426_u24findActivity) {
        Context currentContext = $this$LoginScreen_u24lambda_u2426_u24findActivity;
        while (currentContext instanceof ContextWrapper) {
            if (currentContext instanceof FragmentActivity) {
                return (FragmentActivity) currentContext;
            }
            Context baseContext = ((ContextWrapper) currentContext).getBaseContext();
            Intrinsics.checkNotNullExpressionValue(baseContext, "getBaseContext(...)");
            currentContext = baseContext;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LoginScreen$lambda$26$triggerBiometricLogin(final Context context, final AuthViewModel $authViewModel, final Function0<Unit> function0) {
        FragmentActivity activity = LoginScreen$lambda$26$findActivity(context);
        if (activity == null) {
            Toast.makeText(context, "Biometrics not available in this context.", 0).show();
            return;
        }
        Executor executor = ContextCompat.getMainExecutor(activity);
        Intrinsics.checkNotNullExpressionValue(executor, "getMainExecutor(...)");
        BiometricPrompt biometricPrompt = new BiometricPrompt(activity, executor, new BiometricPrompt.AuthenticationCallback() { // from class: com.example.ui.screens.LoginScreenKt$LoginScreen$1$triggerBiometricLogin$biometricPrompt$1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                super.onAuthenticationSucceeded(result);
                $authViewModel.setLoggedIn(true);
                Toast.makeText(context, "Biometric authentication successful!", 0).show();
                function0.invoke();
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                Intrinsics.checkNotNullParameter(errString, "errString");
                super.onAuthenticationError(errorCode, errString);
                if (errorCode != 10 && errorCode != 13) {
                    Toast.makeText(context, "Biometric Auth: " + ((Object) errString), 0).show();
                }
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(context, "Biometric authentication failed", 0).show();
            }
        });
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Calis Biometric Login").setSubtitle("Log in using your fingerprint or face").setNegativeButtonText("Use PIN").setAllowedAuthenticators(255).build();
        Intrinsics.checkNotNullExpressionValue(promptInfo, "build(...)");
        try {
            biometricPrompt.authenticate(promptInfo);
        } catch (Exception e) {
            Toast.makeText(context, "Failed to start biometrics: " + e.getMessage(), 0).show();
        }
    }

    static final Unit LoginScreen$lambda$26$lambda$25$lambda$24$lambda$19$lambda$18(MutableState $pinCode$delegate, AuthViewModel $authViewModel, Function0 $onLoginSuccess, State $savedPin$delegate, MutableState $errorMessage$delegate, String digit) {
        Intrinsics.checkNotNullParameter(digit, "digit");
        if (LoginScreen$lambda$26$lambda$9($pinCode$delegate).length() < 6) {
            String newPin = LoginScreen$lambda$26$lambda$9($pinCode$delegate) + digit;
            $pinCode$delegate.setValue(newPin);
            if (newPin.length() == 6) {
                LoginScreen$lambda$26$verifyPin($authViewModel, $onLoginSuccess, $savedPin$delegate, $errorMessage$delegate, $pinCode$delegate, newPin);
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit LoginScreen$lambda$26$lambda$25$lambda$24$lambda$21$lambda$20(MutableState $pinCode$delegate, MutableState $errorMessage$delegate) {
        if (LoginScreen$lambda$26$lambda$9($pinCode$delegate).length() > 0) {
            $pinCode$delegate.setValue(StringsKt.dropLast(LoginScreen$lambda$26$lambda$9($pinCode$delegate), 1));
            $errorMessage$delegate.setValue(null);
        }
        return Unit.INSTANCE;
    }

    static final Unit LoginScreen$lambda$26$lambda$25$lambda$24$lambda$23$lambda$22(Context $context, AuthViewModel $authViewModel, Function0 $onLoginSuccess) {
        LoginScreen$lambda$26$triggerBiometricLogin($context, $authViewModel, $onLoginSuccess);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x06b3  */
    /* JADX WARN: Code duplicated, block: B:43:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:45:0x0275  */
    /* JADX WARN: Code duplicated, block: B:48:0x0281  */
    /* JADX WARN: Code duplicated, block: B:49:0x0285  */
    /* JADX WARN: Code duplicated, block: B:52:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:55:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:56:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:61:0x032a  */
    /* JADX WARN: Code duplicated, block: B:63:0x033d  */
    /* JADX WARN: Code duplicated, block: B:64:0x033f  */
    /* JADX WARN: Code duplicated, block: B:66:0x0342  */
    /* JADX WARN: Code duplicated, block: B:67:0x0383  */
    /* JADX WARN: Code duplicated, block: B:69:0x038f  */
    /* JADX WARN: Code duplicated, block: B:70:0x041e  */
    /* JADX WARN: Code duplicated, block: B:72:0x0490  */
    /* JADX WARN: Code duplicated, block: B:73:0x0492  */
    /* JADX WARN: Code duplicated, block: B:76:0x04a1  */
    /* JADX WARN: Code duplicated, block: B:80:0x04ac  */
    /* JADX WARN: Code duplicated, block: B:83:0x0522  */
    /* JADX WARN: Code duplicated, block: B:86:0x052e  */
    /* JADX WARN: Code duplicated, block: B:87:0x0534  */
    /* JADX WARN: Code duplicated, block: B:90:0x0567  */
    /* JADX WARN: Code duplicated, block: B:93:0x057a  */
    /* JADX WARN: Code duplicated, block: B:94:0x057d  */
    private static final void NumberPadLogin(final Function1<? super String, Unit> function1, Function0<Unit> function0, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function2;
        Composer composer;
        Composer composer2;
        Composer composer3;
        String str;
        Composer composer4;
        MeasurePolicy measurePolicyRowMeasurePolicy;
        int currentCompositeKeyHash;
        Modifier modifierMaterializeModifier;
        Function0<ComposeUiNode> constructor;
        Composer composerM3652constructorimpl;
        Composer composer5;
        Composer composer6;
        Composer composer7;
        boolean z;
        Composer composer8;
        String str2;
        int i;
        int i2;
        String str3;
        MeasurePolicy measurePolicy;
        int $dirty;
        Composer composer9;
        String str4;
        Composer composer10;
        String str5;
        boolean z2;
        boolean zChanged;
        Object obj;
        int currentCompositeKeyHash2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function3;
        Composer composerM3652constructorimpl2;
        Composer composer11;
        final Function0<Unit> function4 = function0;
        Composer $composer2 = $composer.startRestartGroup(-981276881);
        ComposerKt.sourceInformation($composer2, "C(NumberPadLogin)P(1)322@14021L2123:LoginScreen.kt#2thlc2");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function4) ? 32 : 16;
        }
        int $dirty3 = $dirty2;
        if (($dirty3 & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-981276881, $dirty3, -1, "com.example.ui.screens.NumberPadLogin (LoginScreen.kt:314)");
            }
            String str6 = "";
            String str7 = "DEL";
            Iterable<Iterable> buttons = CollectionsKt.listOf((Object[]) new List[]{CollectionsKt.listOf((Object[]) new String[]{"1", "2", "3"}), CollectionsKt.listOf((Object[]) new String[]{"4", "5", "6"}), CollectionsKt.listOf((Object[]) new String[]{"7", "8", "9"}), CollectionsKt.listOf((Object[]) new String[]{"", "0", "DEL"})});
            Arrangement.Vertical verticalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(12));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM550spacedBy0680j_4, centerHorizontally, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer2, modifier);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = ((((432 << 3) & 112) << 6) & 896) | 6;
            int i4 = 6;
            String str8 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function2 = constructor3;
                $composer2.createNode(function2);
            } else {
                function2 = constructor3;
                $composer2.useNode();
            }
            Composer composerM3652constructorimpl3 = Updater.m3652constructorimpl($composer2);
            Updater.m3659setimpl(composerM3652constructorimpl3, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl3, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl3.getInserting()) {
                composer = $composer2;
            } else {
                composer = $composer2;
                if (!Intrinsics.areEqual(composerM3652constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl3, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i3 >> 6) & 14;
                composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i6 = ((432 >> 6) & 112) | 6;
                composer3 = composer2;
                str = "C:LoginScreen.kt#2thlc2";
                ComposerKt.sourceInformationMarkerStart(composer3, 903219923, "C:LoginScreen.kt#2thlc2");
                composer3.startReplaceGroup(1691704360);
                ComposerKt.sourceInformation(composer3, "*327@14200L1928");
                for (Iterable<String> iterable : buttons) {
                    Composer composer12 = composer2;
                    Modifier modifier2 = modifierMaterializeModifier2;
                    Arrangement.Horizontal horizontalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(18));
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    composer4 = composer3;
                    Composer composer13 = composer3;
                    MeasurePolicy measurePolicy2 = measurePolicyColumnMeasurePolicy;
                    ComposerKt.sourceInformationMarkerStart(composer4, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                    Modifier modifier3 = Modifier.INSTANCE;
                    measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM550spacedBy0680j_4, centerVertically, composer4, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                    CompositionLocalMap currentCompositionLocalMap2 = composer4.getCurrentCompositionLocalMap();
                    CompositionLocalMap compositionLocalMap = currentCompositionLocalMap;
                    modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifier3);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i7 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer4, -692256719, str8);
                    if (!(composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer4.startReusableNode();
                    if (composer4.getInserting()) {
                        composer4.createNode(constructor);
                    } else {
                        composer4.useNode();
                    }
                    composerM3652constructorimpl = Updater.m3652constructorimpl(composer4);
                    Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM3652constructorimpl.getInserting()) {
                        composer5 = composer4;
                    } else {
                        composer5 = composer4;
                        if (!Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        }
                        Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        int i8 = (i7 >> 6) & 14;
                        composer6 = composer5;
                        ComposerKt.sourceInformationMarkerStart(composer6, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        int i9 = ((432 >> 6) & 112) | 6;
                        composer7 = composer6;
                        ComposerKt.sourceInformationMarkerStart(composer7, -1714808974, str);
                        composer7.startReplaceGroup(-470958291);
                        ComposerKt.sourceInformation(composer7, str6);
                        for (final String str9 : iterable) {
                            if (str9.length() == 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z) {
                                composer7.startReplaceGroup(-731723685);
                                ComposerKt.sourceInformation(composer7, "333@14457L39");
                                int i10 = i4;
                                SpacerKt.Spacer(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), composer7, i10);
                                composer7.endReplaceGroup();
                                i2 = i10;
                                str3 = str6;
                                measurePolicy = measurePolicyRowMeasurePolicy;
                                $dirty = $dirty3;
                                composer9 = composer6;
                                str4 = str;
                                composer10 = composer7;
                                str5 = str8;
                            } else {
                                composer8 = composer6;
                                str2 = str;
                                i = i4;
                                if (Intrinsics.areEqual(str9, str7)) {
                                    composer7.startReplaceGroup(-731591780);
                                    ComposerKt.sourceInformation(composer7, "340@14834L11,335@14569L710");
                                    Modifier modifierClip = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), RoundedCornerShapeKt.getCircleShape());
                                    long surfaceVariant = MaterialTheme.INSTANCE.getColorScheme(composer7, MaterialTheme.$stable).getSurfaceVariant();
                                    Modifier modifierM225backgroundbw27NRU$default = BackgroundKt.m225backgroundbw27NRU$default(modifierClip, Color.m4157copywmQWz5c(surfaceVariant, (14 & 1) != 0 ? Color.m4161getAlphaimpl(surfaceVariant) : 0.5f, (14 & 2) != 0 ? Color.m4165getRedimpl(surfaceVariant) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(surfaceVariant) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(surfaceVariant) : 0.0f), null, 2, null);
                                    Function2<Composer, Integer, Unit> function2M6962getLambda$544237457$app = ComposableSingletons$LoginScreenKt.INSTANCE.m6962getLambda$544237457$app();
                                    int i11 = (($dirty3 >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                                    $dirty = $dirty3;
                                    composer9 = composer8;
                                    str4 = str2;
                                    str3 = str6;
                                    measurePolicy = measurePolicyRowMeasurePolicy;
                                    str5 = str8;
                                    i2 = 6;
                                    composer10 = composer7;
                                    IconButtonKt.IconButton(function0, modifierM225backgroundbw27NRU$default, false, null, null, function2M6962getLambda$544237457$app, composer10, i11, 28);
                                    composer10.endReplaceGroup();
                                } else {
                                    i2 = i;
                                    str3 = str6;
                                    measurePolicy = measurePolicyRowMeasurePolicy;
                                    $dirty = $dirty3;
                                    composer9 = composer8;
                                    str4 = str2;
                                    composer10 = composer7;
                                    str5 = str8;
                                    composer10.startReplaceGroup(-730832931);
                                    ComposerKt.sourceInformation(composer10, "354@15540L11,355@15632L17,350@15333L741");
                                    Modifier modifierClip2 = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), RoundedCornerShapeKt.getCircleShape());
                                    long primaryContainer = MaterialTheme.INSTANCE.getColorScheme(composer10, MaterialTheme.$stable).getPrimaryContainer();
                                    Modifier modifierM225backgroundbw27NRU$default2 = BackgroundKt.m225backgroundbw27NRU$default(modifierClip2, Color.m4157copywmQWz5c(primaryContainer, (14 & 1) != 0 ? Color.m4161getAlphaimpl(primaryContainer) : 0.5f, (14 & 2) != 0 ? Color.m4165getRedimpl(primaryContainer) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(primaryContainer) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(primaryContainer) : 0.0f), null, 2, null);
                                    ComposerKt.sourceInformationMarkerStart(composer10, 946265697, "CC(remember):LoginScreen.kt#9igjgp");
                                    if (($dirty & 14) == 4) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    zChanged = z2 | $composer2.changed(str9);
                                    Object objRememberedValue = composer10.rememberedValue();
                                    if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        obj = new Function0() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda5
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Object invoke() {
                                                return LoginScreenKt.NumberPadLogin$lambda$34$lambda$33$lambda$32$lambda$31$lambda$29$lambda$28(function1, str9);
                                            }
                                        };
                                        composer10.updateRememberedValue(obj);
                                    } else {
                                        obj = objRememberedValue;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer10);
                                    Modifier modifierM258clickableXHw0xAI$default = ClickableKt.m258clickableXHw0xAI$default(modifierM225backgroundbw27NRU$default2, false, null, null, (Function0) obj, 7, null);
                                    Alignment center = Alignment.INSTANCE.getCenter();
                                    ComposerKt.sourceInformationMarkerStart(composer10, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                                    ComposerKt.sourceInformationMarkerStart(composer10, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                    currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer10, 0);
                                    CompositionLocalMap currentCompositionLocalMap3 = composer10.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer10, modifierM258clickableXHw0xAI$default);
                                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i12 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer10, -692256719, str5);
                                    if (!(composer10.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer10.startReusableNode();
                                    if (composer10.getInserting()) {
                                        function3 = constructor2;
                                        composer10.createNode(function3);
                                    } else {
                                        function3 = constructor2;
                                        composer10.useNode();
                                    }
                                    composerM3652constructorimpl2 = Updater.m3652constructorimpl(composer10);
                                    Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM3652constructorimpl2.getInserting()) {
                                        composer11 = composer10;
                                    } else {
                                        composer11 = composer10;
                                        if (!Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        }
                                        Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                                        int i13 = (i12 >> 6) & 14;
                                        Composer composer14 = composer11;
                                        ComposerKt.sourceInformationMarkerStart(composer14, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        int i14 = ((48 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer14, 343705864, "C362@15988L11,358@15771L277:LoginScreen.kt#2thlc2");
                                        TextKt.m2693Text4IGK_g(str9, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer14, MaterialTheme.$stable).getOnPrimaryContainer(), TextUnitKt.getSp(24), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer14, 199680, 0, 131026);
                                        ComposerKt.sourceInformationMarkerEnd(composer14);
                                        ComposerKt.sourceInformationMarkerEnd(composer14);
                                        composer11.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer11);
                                        ComposerKt.sourceInformationMarkerEnd(composer11);
                                        ComposerKt.sourceInformationMarkerEnd(composer11);
                                        composer10.endReplaceGroup();
                                    }
                                    composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                    composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash3);
                                    Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i15 = (i12 >> 6) & 14;
                                    Composer composer15 = composer11;
                                    ComposerKt.sourceInformationMarkerStart(composer15, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                    int i16 = ((48 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer15, 343705864, "C362@15988L11,358@15771L277:LoginScreen.kt#2thlc2");
                                    TextKt.m2693Text4IGK_g(str9, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer15, MaterialTheme.$stable).getOnPrimaryContainer(), TextUnitKt.getSp(24), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer15, 199680, 0, 131026);
                                    ComposerKt.sourceInformationMarkerEnd(composer15);
                                    ComposerKt.sourceInformationMarkerEnd(composer15);
                                    composer11.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer11);
                                    ComposerKt.sourceInformationMarkerEnd(composer11);
                                    ComposerKt.sourceInformationMarkerEnd(composer11);
                                    composer10.endReplaceGroup();
                                }
                            }
                            composer7 = composer10;
                            str8 = str5;
                            measurePolicyRowMeasurePolicy = measurePolicy;
                            composer6 = composer9;
                            modifierMaterializeModifier = modifierMaterializeModifier;
                            str = str4;
                            str7 = str7;
                            str6 = str3;
                            $dirty3 = $dirty;
                            i3 = i3;
                            i4 = i2;
                        }
                        String str10 = str7;
                        Composer composer16 = composer7;
                        composer16.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer16);
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        composer5.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        composer2 = composer12;
                        modifierMaterializeModifier2 = modifier2;
                        composer3 = composer13;
                        measurePolicyColumnMeasurePolicy = measurePolicy2;
                        currentCompositionLocalMap = compositionLocalMap;
                        str7 = str10;
                        $dirty3 = $dirty3;
                        i4 = i4;
                    }
                    composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash2);
                    Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    int i17 = (i7 >> 6) & 14;
                    composer6 = composer5;
                    ComposerKt.sourceInformationMarkerStart(composer6, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    int i18 = ((432 >> 6) & 112) | 6;
                    composer7 = composer6;
                    ComposerKt.sourceInformationMarkerStart(composer7, -1714808974, str);
                    composer7.startReplaceGroup(-470958291);
                    ComposerKt.sourceInformation(composer7, str6);
                    while (r69.hasNext()) {
                        if (str9.length() == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            composer7.startReplaceGroup(-731723685);
                            ComposerKt.sourceInformation(composer7, "333@14457L39");
                            int i19 = i4;
                            SpacerKt.Spacer(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), composer7, i19);
                            composer7.endReplaceGroup();
                            i2 = i19;
                            str3 = str6;
                            measurePolicy = measurePolicyRowMeasurePolicy;
                            $dirty = $dirty3;
                            composer9 = composer6;
                            str4 = str;
                            composer10 = composer7;
                            str5 = str8;
                        } else {
                            composer8 = composer6;
                            str2 = str;
                            i = i4;
                            if (Intrinsics.areEqual(str9, str7)) {
                                composer7.startReplaceGroup(-731591780);
                                ComposerKt.sourceInformation(composer7, "340@14834L11,335@14569L710");
                                Modifier modifierClip3 = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), RoundedCornerShapeKt.getCircleShape());
                                long surfaceVariant2 = MaterialTheme.INSTANCE.getColorScheme(composer7, MaterialTheme.$stable).getSurfaceVariant();
                                Modifier modifierM225backgroundbw27NRU$default3 = BackgroundKt.m225backgroundbw27NRU$default(modifierClip3, Color.m4157copywmQWz5c(surfaceVariant2, (14 & 1) != 0 ? Color.m4161getAlphaimpl(surfaceVariant2) : 0.5f, (14 & 2) != 0 ? Color.m4165getRedimpl(surfaceVariant2) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(surfaceVariant2) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(surfaceVariant2) : 0.0f), null, 2, null);
                                Function2<Composer, Integer, Unit> function2M6962getLambda$544237457$app2 = ComposableSingletons$LoginScreenKt.INSTANCE.m6962getLambda$544237457$app();
                                int i110 = (($dirty3 >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                                $dirty = $dirty3;
                                composer9 = composer8;
                                str4 = str2;
                                str3 = str6;
                                measurePolicy = measurePolicyRowMeasurePolicy;
                                str5 = str8;
                                i2 = 6;
                                composer10 = composer7;
                                IconButtonKt.IconButton(function0, modifierM225backgroundbw27NRU$default3, false, null, null, function2M6962getLambda$544237457$app2, composer10, i110, 28);
                                composer10.endReplaceGroup();
                            } else {
                                i2 = i;
                                str3 = str6;
                                measurePolicy = measurePolicyRowMeasurePolicy;
                                $dirty = $dirty3;
                                composer9 = composer8;
                                str4 = str2;
                                composer10 = composer7;
                                str5 = str8;
                                composer10.startReplaceGroup(-730832931);
                                ComposerKt.sourceInformation(composer10, "354@15540L11,355@15632L17,350@15333L741");
                                Modifier modifierClip4 = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), RoundedCornerShapeKt.getCircleShape());
                                long primaryContainer2 = MaterialTheme.INSTANCE.getColorScheme(composer10, MaterialTheme.$stable).getPrimaryContainer();
                                Modifier modifierM225backgroundbw27NRU$default4 = BackgroundKt.m225backgroundbw27NRU$default(modifierClip4, Color.m4157copywmQWz5c(primaryContainer2, (14 & 1) != 0 ? Color.m4161getAlphaimpl(primaryContainer2) : 0.5f, (14 & 2) != 0 ? Color.m4165getRedimpl(primaryContainer2) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(primaryContainer2) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(primaryContainer2) : 0.0f), null, 2, null);
                                ComposerKt.sourceInformationMarkerStart(composer10, 946265697, "CC(remember):LoginScreen.kt#9igjgp");
                                if (($dirty & 14) == 4) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                zChanged = z2 | $composer2.changed(str9);
                                Object objRememberedValue2 = composer10.rememberedValue();
                                if (zChanged) {
                                    obj = new Function0() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda5
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return LoginScreenKt.NumberPadLogin$lambda$34$lambda$33$lambda$32$lambda$31$lambda$29$lambda$28(function1, str9);
                                        }
                                    };
                                    composer10.updateRememberedValue(obj);
                                } else {
                                    obj = new Function0() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda5
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return LoginScreenKt.NumberPadLogin$lambda$34$lambda$33$lambda$32$lambda$31$lambda$29$lambda$28(function1, str9);
                                        }
                                    };
                                    composer10.updateRememberedValue(obj);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer10);
                                Modifier modifierM258clickableXHw0xAI$default2 = ClickableKt.m258clickableXHw0xAI$default(modifierM225backgroundbw27NRU$default4, false, null, null, (Function0) obj, 7, null);
                                Alignment center2 = Alignment.INSTANCE.getCenter();
                                ComposerKt.sourceInformationMarkerStart(composer10, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                                ComposerKt.sourceInformationMarkerStart(composer10, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer10, 0);
                                CompositionLocalMap currentCompositionLocalMap4 = composer10.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer10, modifierM258clickableXHw0xAI$default2);
                                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                int i111 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer10, -692256719, str5);
                                if (!(composer10.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer10.startReusableNode();
                                if (composer10.getInserting()) {
                                    function3 = constructor2;
                                    composer10.createNode(function3);
                                } else {
                                    function3 = constructor2;
                                    composer10.useNode();
                                }
                                composerM3652constructorimpl2 = Updater.m3652constructorimpl(composer10);
                                Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3652constructorimpl2.getInserting()) {
                                    composer11 = composer10;
                                    if (!Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                    }
                                    Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i112 = (i111 >> 6) & 14;
                                    Composer composer17 = composer11;
                                    ComposerKt.sourceInformationMarkerStart(composer17, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                                    int i113 = ((48 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer17, 343705864, "C362@15988L11,358@15771L277:LoginScreen.kt#2thlc2");
                                    TextKt.m2693Text4IGK_g(str9, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer17, MaterialTheme.$stable).getOnPrimaryContainer(), TextUnitKt.getSp(24), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer17, 199680, 0, 131026);
                                    ComposerKt.sourceInformationMarkerEnd(composer17);
                                    ComposerKt.sourceInformationMarkerEnd(composer17);
                                    composer11.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer11);
                                    ComposerKt.sourceInformationMarkerEnd(composer11);
                                    ComposerKt.sourceInformationMarkerEnd(composer11);
                                    composer10.endReplaceGroup();
                                } else {
                                    composer11 = composer10;
                                }
                                composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash4);
                                Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                                int i114 = (i111 >> 6) & 14;
                                Composer composer18 = composer11;
                                ComposerKt.sourceInformationMarkerStart(composer18, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                                int i115 = ((48 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer18, 343705864, "C362@15988L11,358@15771L277:LoginScreen.kt#2thlc2");
                                TextKt.m2693Text4IGK_g(str9, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer18, MaterialTheme.$stable).getOnPrimaryContainer(), TextUnitKt.getSp(24), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer18, 199680, 0, 131026);
                                ComposerKt.sourceInformationMarkerEnd(composer18);
                                ComposerKt.sourceInformationMarkerEnd(composer18);
                                composer11.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                composer10.endReplaceGroup();
                            }
                        }
                        composer7 = composer10;
                        str8 = str5;
                        measurePolicyRowMeasurePolicy = measurePolicy;
                        composer6 = composer9;
                        modifierMaterializeModifier = modifierMaterializeModifier;
                        str = str4;
                        str7 = str7;
                        str6 = str3;
                        $dirty3 = $dirty;
                        i3 = i3;
                        i4 = i2;
                    }
                    String str11 = str7;
                    Composer composer19 = composer7;
                    composer19.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer19);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    composer5.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    composer2 = composer12;
                    modifierMaterializeModifier2 = modifier2;
                    composer3 = composer13;
                    measurePolicyColumnMeasurePolicy = measurePolicy2;
                    currentCompositionLocalMap = compositionLocalMap;
                    str7 = str11;
                    $dirty3 = $dirty3;
                    i4 = i4;
                }
                Composer composer20 = composer2;
                Composer composer21 = composer3;
                function4 = function0;
                composer21.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer21);
                ComposerKt.sourceInformationMarkerEnd(composer20);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            composerM3652constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
            composerM3652constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash);
            Updater.m3659setimpl(composerM3652constructorimpl3, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i20 = (i3 >> 6) & 14;
            composer2 = composer;
            ComposerKt.sourceInformationMarkerStart(composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i21 = ((432 >> 6) & 112) | 6;
            composer3 = composer2;
            str = "C:LoginScreen.kt#2thlc2";
            ComposerKt.sourceInformationMarkerStart(composer3, 903219923, "C:LoginScreen.kt#2thlc2");
            composer3.startReplaceGroup(1691704360);
            ComposerKt.sourceInformation(composer3, "*327@14200L1928");
            while (r40.hasNext()) {
                Composer composer110 = composer2;
                Modifier modifier4 = modifierMaterializeModifier2;
                Arrangement.Horizontal horizontalM550spacedBy0680j_5 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(18));
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                composer4 = composer3;
                Composer composer111 = composer3;
                MeasurePolicy measurePolicy3 = measurePolicyColumnMeasurePolicy;
                ComposerKt.sourceInformationMarkerStart(composer4, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                Modifier modifier5 = Modifier.INSTANCE;
                measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM550spacedBy0680j_5, centerVertically2, composer4, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                CompositionLocalMap currentCompositionLocalMap5 = composer4.getCurrentCompositionLocalMap();
                CompositionLocalMap compositionLocalMap2 = currentCompositionLocalMap;
                modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifier5);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i22 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, -692256719, str8);
                if (!(composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer4.startReusableNode();
                if (composer4.getInserting()) {
                    composer4.createNode(constructor);
                } else {
                    composer4.useNode();
                }
                composerM3652constructorimpl = Updater.m3652constructorimpl(composer4);
                Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM3652constructorimpl.getInserting()) {
                    composer5 = composer4;
                    if (!Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    }
                    Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    int i116 = (i22 >> 6) & 14;
                    composer6 = composer5;
                    ComposerKt.sourceInformationMarkerStart(composer6, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    int i117 = ((432 >> 6) & 112) | 6;
                    composer7 = composer6;
                    ComposerKt.sourceInformationMarkerStart(composer7, -1714808974, str);
                    composer7.startReplaceGroup(-470958291);
                    ComposerKt.sourceInformation(composer7, str6);
                    while (r69.hasNext()) {
                        if (str9.length() == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            composer7.startReplaceGroup(-731723685);
                            ComposerKt.sourceInformation(composer7, "333@14457L39");
                            int i118 = i4;
                            SpacerKt.Spacer(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), composer7, i118);
                            composer7.endReplaceGroup();
                            i2 = i118;
                            str3 = str6;
                            measurePolicy = measurePolicyRowMeasurePolicy;
                            $dirty = $dirty3;
                            composer9 = composer6;
                            str4 = str;
                            composer10 = composer7;
                            str5 = str8;
                        } else {
                            composer8 = composer6;
                            str2 = str;
                            i = i4;
                            if (Intrinsics.areEqual(str9, str7)) {
                                composer7.startReplaceGroup(-731591780);
                                ComposerKt.sourceInformation(composer7, "340@14834L11,335@14569L710");
                                Modifier modifierClip5 = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), RoundedCornerShapeKt.getCircleShape());
                                long surfaceVariant3 = MaterialTheme.INSTANCE.getColorScheme(composer7, MaterialTheme.$stable).getSurfaceVariant();
                                Modifier modifierM225backgroundbw27NRU$default5 = BackgroundKt.m225backgroundbw27NRU$default(modifierClip5, Color.m4157copywmQWz5c(surfaceVariant3, (14 & 1) != 0 ? Color.m4161getAlphaimpl(surfaceVariant3) : 0.5f, (14 & 2) != 0 ? Color.m4165getRedimpl(surfaceVariant3) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(surfaceVariant3) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(surfaceVariant3) : 0.0f), null, 2, null);
                                Function2<Composer, Integer, Unit> function2M6962getLambda$544237457$app3 = ComposableSingletons$LoginScreenKt.INSTANCE.m6962getLambda$544237457$app();
                                int i119 = (($dirty3 >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                                $dirty = $dirty3;
                                composer9 = composer8;
                                str4 = str2;
                                str3 = str6;
                                measurePolicy = measurePolicyRowMeasurePolicy;
                                str5 = str8;
                                i2 = 6;
                                composer10 = composer7;
                                IconButtonKt.IconButton(function0, modifierM225backgroundbw27NRU$default5, false, null, null, function2M6962getLambda$544237457$app3, composer10, i119, 28);
                                composer10.endReplaceGroup();
                            } else {
                                i2 = i;
                                str3 = str6;
                                measurePolicy = measurePolicyRowMeasurePolicy;
                                $dirty = $dirty3;
                                composer9 = composer8;
                                str4 = str2;
                                composer10 = composer7;
                                str5 = str8;
                                composer10.startReplaceGroup(-730832931);
                                ComposerKt.sourceInformation(composer10, "354@15540L11,355@15632L17,350@15333L741");
                                Modifier modifierClip6 = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), RoundedCornerShapeKt.getCircleShape());
                                long primaryContainer3 = MaterialTheme.INSTANCE.getColorScheme(composer10, MaterialTheme.$stable).getPrimaryContainer();
                                Modifier modifierM225backgroundbw27NRU$default6 = BackgroundKt.m225backgroundbw27NRU$default(modifierClip6, Color.m4157copywmQWz5c(primaryContainer3, (14 & 1) != 0 ? Color.m4161getAlphaimpl(primaryContainer3) : 0.5f, (14 & 2) != 0 ? Color.m4165getRedimpl(primaryContainer3) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(primaryContainer3) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(primaryContainer3) : 0.0f), null, 2, null);
                                ComposerKt.sourceInformationMarkerStart(composer10, 946265697, "CC(remember):LoginScreen.kt#9igjgp");
                                if (($dirty & 14) == 4) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                zChanged = z2 | $composer2.changed(str9);
                                Object objRememberedValue3 = composer10.rememberedValue();
                                if (zChanged) {
                                    obj = new Function0() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda5
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return LoginScreenKt.NumberPadLogin$lambda$34$lambda$33$lambda$32$lambda$31$lambda$29$lambda$28(function1, str9);
                                        }
                                    };
                                    composer10.updateRememberedValue(obj);
                                } else {
                                    obj = new Function0() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda5
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return LoginScreenKt.NumberPadLogin$lambda$34$lambda$33$lambda$32$lambda$31$lambda$29$lambda$28(function1, str9);
                                        }
                                    };
                                    composer10.updateRememberedValue(obj);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer10);
                                Modifier modifierM258clickableXHw0xAI$default3 = ClickableKt.m258clickableXHw0xAI$default(modifierM225backgroundbw27NRU$default6, false, null, null, (Function0) obj, 7, null);
                                Alignment center3 = Alignment.INSTANCE.getCenter();
                                ComposerKt.sourceInformationMarkerStart(composer10, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(center3, false);
                                ComposerKt.sourceInformationMarkerStart(composer10, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer10, 0);
                                CompositionLocalMap currentCompositionLocalMap6 = composer10.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer10, modifierM258clickableXHw0xAI$default3);
                                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                int i1110 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer10, -692256719, str5);
                                if (!(composer10.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer10.startReusableNode();
                                if (composer10.getInserting()) {
                                    function3 = constructor2;
                                    composer10.createNode(function3);
                                } else {
                                    function3 = constructor2;
                                    composer10.useNode();
                                }
                                composerM3652constructorimpl2 = Updater.m3652constructorimpl(composer10);
                                Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3652constructorimpl2.getInserting()) {
                                    composer11 = composer10;
                                    if (!Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                    }
                                    Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i1111 = (i1110 >> 6) & 14;
                                    Composer composer112 = composer11;
                                    ComposerKt.sourceInformationMarkerStart(composer112, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                                    int i1112 = ((48 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer112, 343705864, "C362@15988L11,358@15771L277:LoginScreen.kt#2thlc2");
                                    TextKt.m2693Text4IGK_g(str9, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer112, MaterialTheme.$stable).getOnPrimaryContainer(), TextUnitKt.getSp(24), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer112, 199680, 0, 131026);
                                    ComposerKt.sourceInformationMarkerEnd(composer112);
                                    ComposerKt.sourceInformationMarkerEnd(composer112);
                                    composer11.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer11);
                                    ComposerKt.sourceInformationMarkerEnd(composer11);
                                    ComposerKt.sourceInformationMarkerEnd(composer11);
                                    composer10.endReplaceGroup();
                                } else {
                                    composer11 = composer10;
                                }
                                composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash6);
                                Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                                int i1113 = (i1110 >> 6) & 14;
                                Composer composer113 = composer11;
                                ComposerKt.sourceInformationMarkerStart(composer113, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                                int i1114 = ((48 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer113, 343705864, "C362@15988L11,358@15771L277:LoginScreen.kt#2thlc2");
                                TextKt.m2693Text4IGK_g(str9, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer113, MaterialTheme.$stable).getOnPrimaryContainer(), TextUnitKt.getSp(24), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer113, 199680, 0, 131026);
                                ComposerKt.sourceInformationMarkerEnd(composer113);
                                ComposerKt.sourceInformationMarkerEnd(composer113);
                                composer11.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                composer10.endReplaceGroup();
                            }
                        }
                        composer7 = composer10;
                        str8 = str5;
                        measurePolicyRowMeasurePolicy = measurePolicy;
                        composer6 = composer9;
                        modifierMaterializeModifier = modifierMaterializeModifier;
                        str = str4;
                        str7 = str7;
                        str6 = str3;
                        $dirty3 = $dirty;
                        i3 = i3;
                        i4 = i2;
                    }
                    String str12 = str7;
                    Composer composer114 = composer7;
                    composer114.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer114);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    composer5.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    composer2 = composer110;
                    modifierMaterializeModifier2 = modifier4;
                    composer3 = composer111;
                    measurePolicyColumnMeasurePolicy = measurePolicy3;
                    currentCompositionLocalMap = compositionLocalMap2;
                    str7 = str12;
                    $dirty3 = $dirty3;
                    i4 = i4;
                } else {
                    composer5 = composer4;
                }
                composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash5);
                Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i1115 = (i22 >> 6) & 14;
                composer6 = composer5;
                ComposerKt.sourceInformationMarkerStart(composer6, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                int i1116 = ((432 >> 6) & 112) | 6;
                composer7 = composer6;
                ComposerKt.sourceInformationMarkerStart(composer7, -1714808974, str);
                composer7.startReplaceGroup(-470958291);
                ComposerKt.sourceInformation(composer7, str6);
                while (r69.hasNext()) {
                    if (str9.length() == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        composer7.startReplaceGroup(-731723685);
                        ComposerKt.sourceInformation(composer7, "333@14457L39");
                        int i1117 = i4;
                        SpacerKt.Spacer(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), composer7, i1117);
                        composer7.endReplaceGroup();
                        i2 = i1117;
                        str3 = str6;
                        measurePolicy = measurePolicyRowMeasurePolicy;
                        $dirty = $dirty3;
                        composer9 = composer6;
                        str4 = str;
                        composer10 = composer7;
                        str5 = str8;
                    } else {
                        composer8 = composer6;
                        str2 = str;
                        i = i4;
                        if (Intrinsics.areEqual(str9, str7)) {
                            composer7.startReplaceGroup(-731591780);
                            ComposerKt.sourceInformation(composer7, "340@14834L11,335@14569L710");
                            Modifier modifierClip7 = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), RoundedCornerShapeKt.getCircleShape());
                            long surfaceVariant4 = MaterialTheme.INSTANCE.getColorScheme(composer7, MaterialTheme.$stable).getSurfaceVariant();
                            Modifier modifierM225backgroundbw27NRU$default7 = BackgroundKt.m225backgroundbw27NRU$default(modifierClip7, Color.m4157copywmQWz5c(surfaceVariant4, (14 & 1) != 0 ? Color.m4161getAlphaimpl(surfaceVariant4) : 0.5f, (14 & 2) != 0 ? Color.m4165getRedimpl(surfaceVariant4) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(surfaceVariant4) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(surfaceVariant4) : 0.0f), null, 2, null);
                            Function2<Composer, Integer, Unit> function2M6962getLambda$544237457$app4 = ComposableSingletons$LoginScreenKt.INSTANCE.m6962getLambda$544237457$app();
                            int i1118 = (($dirty3 >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            $dirty = $dirty3;
                            composer9 = composer8;
                            str4 = str2;
                            str3 = str6;
                            measurePolicy = measurePolicyRowMeasurePolicy;
                            str5 = str8;
                            i2 = 6;
                            composer10 = composer7;
                            IconButtonKt.IconButton(function0, modifierM225backgroundbw27NRU$default7, false, null, null, function2M6962getLambda$544237457$app4, composer10, i1118, 28);
                            composer10.endReplaceGroup();
                        } else {
                            i2 = i;
                            str3 = str6;
                            measurePolicy = measurePolicyRowMeasurePolicy;
                            $dirty = $dirty3;
                            composer9 = composer8;
                            str4 = str2;
                            composer10 = composer7;
                            str5 = str8;
                            composer10.startReplaceGroup(-730832931);
                            ComposerKt.sourceInformation(composer10, "354@15540L11,355@15632L17,350@15333L741");
                            Modifier modifierClip8 = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(72)), RoundedCornerShapeKt.getCircleShape());
                            long primaryContainer4 = MaterialTheme.INSTANCE.getColorScheme(composer10, MaterialTheme.$stable).getPrimaryContainer();
                            Modifier modifierM225backgroundbw27NRU$default8 = BackgroundKt.m225backgroundbw27NRU$default(modifierClip8, Color.m4157copywmQWz5c(primaryContainer4, (14 & 1) != 0 ? Color.m4161getAlphaimpl(primaryContainer4) : 0.5f, (14 & 2) != 0 ? Color.m4165getRedimpl(primaryContainer4) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(primaryContainer4) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(primaryContainer4) : 0.0f), null, 2, null);
                            ComposerKt.sourceInformationMarkerStart(composer10, 946265697, "CC(remember):LoginScreen.kt#9igjgp");
                            if (($dirty & 14) == 4) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            zChanged = z2 | $composer2.changed(str9);
                            Object objRememberedValue4 = composer10.rememberedValue();
                            if (zChanged) {
                                obj = new Function0() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda5
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return LoginScreenKt.NumberPadLogin$lambda$34$lambda$33$lambda$32$lambda$31$lambda$29$lambda$28(function1, str9);
                                    }
                                };
                                composer10.updateRememberedValue(obj);
                            } else {
                                obj = new Function0() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda5
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return LoginScreenKt.NumberPadLogin$lambda$34$lambda$33$lambda$32$lambda$31$lambda$29$lambda$28(function1, str9);
                                    }
                                };
                                composer10.updateRememberedValue(obj);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer10);
                            Modifier modifierM258clickableXHw0xAI$default4 = ClickableKt.m258clickableXHw0xAI$default(modifierM225backgroundbw27NRU$default8, false, null, null, (Function0) obj, 7, null);
                            Alignment center4 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart(composer10, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(center4, false);
                            ComposerKt.sourceInformationMarkerStart(composer10, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                            currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer10, 0);
                            CompositionLocalMap currentCompositionLocalMap7 = composer10.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composer10, modifierM258clickableXHw0xAI$default4);
                            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            int i1119 = ((((48 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer10, -692256719, str5);
                            if (!(composer10.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer10.startReusableNode();
                            if (composer10.getInserting()) {
                                function3 = constructor2;
                                composer10.createNode(function3);
                            } else {
                                function3 = constructor2;
                                composer10.useNode();
                            }
                            composerM3652constructorimpl2 = Updater.m3652constructorimpl(composer10);
                            Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (composerM3652constructorimpl2.getInserting()) {
                                composer11 = composer10;
                                if (!Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                }
                                Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                                int i11110 = (i1119 >> 6) & 14;
                                Composer composer115 = composer11;
                                ComposerKt.sourceInformationMarkerStart(composer115, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                                int i11111 = ((48 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer115, 343705864, "C362@15988L11,358@15771L277:LoginScreen.kt#2thlc2");
                                TextKt.m2693Text4IGK_g(str9, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer115, MaterialTheme.$stable).getOnPrimaryContainer(), TextUnitKt.getSp(24), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer115, 199680, 0, 131026);
                                ComposerKt.sourceInformationMarkerEnd(composer115);
                                ComposerKt.sourceInformationMarkerEnd(composer115);
                                composer11.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                composer10.endReplaceGroup();
                            } else {
                                composer11 = composer10;
                            }
                            composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash7);
                            Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                            int i11112 = (i1119 >> 6) & 14;
                            Composer composer116 = composer11;
                            ComposerKt.sourceInformationMarkerStart(composer116, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
                            int i11113 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer116, 343705864, "C362@15988L11,358@15771L277:LoginScreen.kt#2thlc2");
                            TextKt.m2693Text4IGK_g(str9, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer116, MaterialTheme.$stable).getOnPrimaryContainer(), TextUnitKt.getSp(24), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer116, 199680, 0, 131026);
                            ComposerKt.sourceInformationMarkerEnd(composer116);
                            ComposerKt.sourceInformationMarkerEnd(composer116);
                            composer11.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer11);
                            ComposerKt.sourceInformationMarkerEnd(composer11);
                            ComposerKt.sourceInformationMarkerEnd(composer11);
                            composer10.endReplaceGroup();
                        }
                    }
                    composer7 = composer10;
                    str8 = str5;
                    measurePolicyRowMeasurePolicy = measurePolicy;
                    composer6 = composer9;
                    modifierMaterializeModifier = modifierMaterializeModifier;
                    str = str4;
                    str7 = str7;
                    str6 = str3;
                    $dirty3 = $dirty;
                    i3 = i3;
                    i4 = i2;
                }
                String str13 = str7;
                Composer composer117 = composer7;
                composer117.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer117);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                composer5.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer5);
                ComposerKt.sourceInformationMarkerEnd(composer5);
                ComposerKt.sourceInformationMarkerEnd(composer5);
                composer2 = composer110;
                modifierMaterializeModifier2 = modifier4;
                composer3 = composer111;
                measurePolicyColumnMeasurePolicy = measurePolicy3;
                currentCompositionLocalMap = compositionLocalMap2;
                str7 = str13;
                $dirty3 = $dirty3;
                i4 = i4;
            }
            Composer composer22 = composer2;
            Composer composer23 = composer3;
            function4 = function0;
            composer23.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer23);
            ComposerKt.sourceInformationMarkerEnd(composer22);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.ui.screens.LoginScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return LoginScreenKt.NumberPadLogin$lambda$35(function1, function4, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final Unit NumberPadLogin$lambda$34$lambda$33$lambda$32$lambda$31$lambda$29$lambda$28(Function1 $onDigit, String $item) {
        $onDigit.invoke($item);
        return Unit.INSTANCE;
    }
}
