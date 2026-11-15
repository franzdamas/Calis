package com.example;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.example.ui.components.DebugConsoleKt;
import com.example.ui.theme.AppThemeMode;
import com.example.ui.theme.ThemeConfig;
import com.example.ui.theme.ThemeKt;
import com.example.ui.viewmodel.AuthViewModel;
import com.example.ui.viewmodel.StudentViewModel;
import com.example.ui.viewmodel.TeacherViewModel;
import com.example.util.BlePermissionUtils;
import com.example.util.ReminderNotificationHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\u0012\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u00152\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u0015H\u0014R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/example/MainActivity;", "Landroidx/fragment/app/FragmentActivity;", "<init>", "()V", "authViewModel", "Lcom/example/ui/viewmodel/AuthViewModel;", "getAuthViewModel", "()Lcom/example/ui/viewmodel/AuthViewModel;", "authViewModel$delegate", "Lkotlin/Lazy;", "teacherViewModel", "Lcom/example/ui/viewmodel/TeacherViewModel;", "getTeacherViewModel", "()Lcom/example/ui/viewmodel/TeacherViewModel;", "teacherViewModel$delegate", "studentViewModel", "Lcom/example/ui/viewmodel/StudentViewModel;", "getStudentViewModel", "()Lcom/example/ui/viewmodel/StudentViewModel;", "studentViewModel$delegate", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "applyOverrideConfiguration", "overrideConfiguration", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MainActivity extends FragmentActivity {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: authViewModel$delegate, reason: from kotlin metadata */
    private final Lazy authViewModel;

    /* JADX INFO: renamed from: studentViewModel$delegate, reason: from kotlin metadata */
    private final Lazy studentViewModel;

    /* JADX INFO: renamed from: teacherViewModel$delegate, reason: from kotlin metadata */
    private final Lazy teacherViewModel;

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AppThemeMode.values().length];
            try {
                iArr[AppThemeMode.LIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AppThemeMode.DARK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AppThemeMode.SYSTEM.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MainActivity() {
        final MainActivity mainActivity = this;
        final Function0 function0 = null;
        this.authViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(AuthViewModel.class), new Function0<ViewModelStore>() { // from class: com.example.MainActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return mainActivity.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.example.MainActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return mainActivity.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.example.MainActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? mainActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
        final MainActivity mainActivity2 = this;
        final Function0 function1 = null;
        this.teacherViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(TeacherViewModel.class), new Function0<ViewModelStore>() { // from class: com.example.MainActivity$special$$inlined$viewModels$default$5
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return mainActivity2.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.example.MainActivity$special$$inlined$viewModels$default$4
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return mainActivity2.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.example.MainActivity$special$$inlined$viewModels$default$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function2 = function1;
                return (function2 == null || (creationExtras = (CreationExtras) function2.invoke()) == null) ? mainActivity2.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
        final MainActivity mainActivity3 = this;
        final Function0 function2 = null;
        this.studentViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(StudentViewModel.class), new Function0<ViewModelStore>() { // from class: com.example.MainActivity$special$$inlined$viewModels$default$8
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return mainActivity3.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.example.MainActivity$special$$inlined$viewModels$default$7
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return mainActivity3.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.example.MainActivity$special$$inlined$viewModels$default$9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function3 = function2;
                return (function3 == null || (creationExtras = (CreationExtras) function3.invoke()) == null) ? mainActivity3.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    private final AuthViewModel getAuthViewModel() {
        return (AuthViewModel) this.authViewModel.getValue();
    }

    private final TeacherViewModel getTeacherViewModel() {
        return (TeacherViewModel) this.teacherViewModel.getValue();
    }

    private final StudentViewModel getStudentViewModel() {
        return (StudentViewModel) this.studentViewModel.getValue();
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        Intrinsics.checkNotNullParameter(newBase, "newBase");
        Configuration config = new Configuration(newBase.getResources().getConfiguration());
        if (config.fontScale > 1.0f) {
            config.fontScale = 1.0f;
        }
        DisplayMetrics metrics = newBase.getResources().getDisplayMetrics();
        int minDimensionPx = Math.min(metrics.widthPixels, metrics.heightPixels);
        int maxAllowedDpi = (int) ((minDimensionPx / 380.0f) * 160.0f);
        if (maxAllowedDpi > 0 && config.densityDpi > maxAllowedDpi) {
            config.densityDpi = maxAllowedDpi;
        }
        Context context = newBase.createConfigurationContext(config);
        super.attachBaseContext(context);
    }

    @Override // android.view.ContextThemeWrapper
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if (overrideConfiguration != null) {
            if (overrideConfiguration.fontScale > 1.0f) {
                overrideConfiguration.fontScale = 1.0f;
            }
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int minDimensionPx = Math.min(metrics.widthPixels, metrics.heightPixels);
            int maxAllowedDpi = (int) ((minDimensionPx / 380.0f) * 160.0f);
            if (maxAllowedDpi > 0 && overrideConfiguration.densityDpi > maxAllowedDpi) {
                overrideConfiguration.densityDpi = maxAllowedDpi;
            }
        }
        super.applyOverrideConfiguration(overrideConfiguration);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlePermissionUtils.INSTANCE.setAppContext(getApplicationContext());
        boolean isTablet = getResources().getConfiguration().smallestScreenWidthDp >= 600;
        if (isTablet) {
            setRequestedOrientation(-1);
        } else {
            setRequestedOrientation(1);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            getWindow().getDecorView().setForceDarkAllowed(false);
        }
        EdgeToEdge.enable$default(this, null, null, 3, null);
        Log.d("MainActivity", "[DIAGNOSTIC] CalisBleService is now managed by TeacherViewModel");
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-601144069, true, new Function2() { // from class: com.example.MainActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.onCreate$lambda$3(this.f$0, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    static final Unit onCreate$lambda$3(final MainActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C110@4665L609,110@4623L651:MainActivity.kt#to5c3");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-601144069, $changed, -1, "com.example.MainActivity.onCreate.<anonymous> (MainActivity.kt:103)");
            }
            AppThemeMode themeMode = ThemeConfig.INSTANCE.getThemeMode();
            boolean zIsSystemInDarkTheme = false;
            switch (WhenMappings.$EnumSwitchMapping$0[themeMode.ordinal()]) {
                case 1:
                    $composer.startReplaceGroup(1330804021);
                    $composer.endReplaceGroup();
                    break;
                case 2:
                    $composer.startReplaceGroup(1330846640);
                    $composer.endReplaceGroup();
                    zIsSystemInDarkTheme = true;
                    break;
                case 3:
                    $composer.startReplaceGroup(2121142800);
                    ComposerKt.sourceInformation($composer, "107@4574L21");
                    zIsSystemInDarkTheme = DarkThemeKt.isSystemInDarkTheme($composer, 0);
                    $composer.endReplaceGroup();
                    break;
                default:
                    $composer.startReplaceGroup(2121137482);
                    $composer.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
            }
            boolean isDarkTheme = zIsSystemInDarkTheme;
            ThemeKt.CalisMobileTheme(isDarkTheme, false, ComposableLambdaKt.rememberComposableLambda(-1407068675, true, new Function2() { // from class: com.example.MainActivity$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivity.onCreate$lambda$3$lambda$2(this.f$0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 384, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$3$lambda$2(final MainActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C113@4789L11,114@4830L430,111@4683L577:MainActivity.kt#to5c3");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1407068675, $changed, -1, "com.example.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:111)");
            }
            SurfaceKt.m2543SurfaceT9BRK9s(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getBackground(), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1027886142, true, new Function2() { // from class: com.example.MainActivity$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivity.onCreate$lambda$3$lambda$2$lambda$1(this.f$0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 12582918, 122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x015b  */
    static final Unit onCreate$lambda$3$lambda$2$lambda$1(MainActivity this$0, Composer $composer, int $changed) {
        int i;
        ComposerKt.sourceInformation($composer, "C115@4852L390:MainActivity.kt#to5c3");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1027886142, $changed, -1, "com.example.MainActivity.onCreate.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:115)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i2 = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composerM3652constructorimpl = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl.getInserting()) {
                i = 6;
            } else {
                i = 6;
                if (!Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = (i2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((i >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1310173740, "C116@4917L233,121@5201L19:MainActivity.kt#to5c3");
                MainActivityKt.CalisAppNavigation(this$0.getAuthViewModel(), this$0.getTeacherViewModel(), this$0.getStudentViewModel(), $composer, 0);
                DebugConsoleKt.DebugConsolePopup($composer, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i6 = ((i >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1310173740, "C116@4917L233,121@5201L19:MainActivity.kt#to5c3");
            MainActivityKt.CalisAppNavigation(this$0.getAuthViewModel(), this$0.getTeacherViewModel(), this$0.getStudentViewModel(), $composer, 0);
            DebugConsoleKt.DebugConsolePopup($composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("calis_auth_prefs", 0);
        String role = prefs.getString("user_role", null);
        String lastSeenDay = prefs.getString("last_seen_day_str", null);
        String currentDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        if (lastSeenDay != null && !Intrinsics.areEqual(lastSeenDay, currentDay)) {
            ReminderNotificationHelper reminderNotificationHelper = ReminderNotificationHelper.INSTANCE;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            reminderNotificationHelper.clearTaskReminderKeys(applicationContext);
            ReminderNotificationHelper reminderNotificationHelper2 = ReminderNotificationHelper.INSTANCE;
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
            reminderNotificationHelper2.clearSessionReminderKeys(applicationContext2);
        }
        prefs.edit().putString("last_seen_day_str", currentDay).apply();
        if (Intrinsics.areEqual(role, "TEACHER") && prefs.getBoolean("remind_start_session", false)) {
            ReminderNotificationHelper reminderNotificationHelper3 = ReminderNotificationHelper.INSTANCE;
            Context applicationContext3 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext3, "getApplicationContext(...)");
            reminderNotificationHelper3.checkAndPostSessionReminder(applicationContext3);
            return;
        }
        if (prefs.getBoolean("remind_task_deadline", false)) {
            ReminderNotificationHelper reminderNotificationHelper4 = ReminderNotificationHelper.INSTANCE;
            Context applicationContext4 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext4, "getApplicationContext(...)");
            reminderNotificationHelper4.checkAndPostTaskDeadlineReminder(applicationContext4);
        }
    }
}
