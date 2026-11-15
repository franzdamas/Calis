package com.example.ui.screens;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material.icons.automirrored.filled.ExitToAppKt;
import androidx.compose.material.icons.filled.AssignmentKt;
import androidx.compose.material.icons.filled.BluetoothKt;
import androidx.compose.material.icons.filled.CancelKt;
import androidx.compose.material.icons.filled.CheckCircleKt;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.InfoKt;
import androidx.compose.material.icons.filled.MenuBookKt;
import androidx.compose.material.icons.filled.MenuKt;
import androidx.compose.material.icons.filled.PersonKt;
import androidx.compose.material.icons.filled.SchoolKt;
import androidx.compose.material.icons.filled.SettingsKt;
import androidx.compose.material.icons.outlined.HelpOutlineKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
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
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StudentMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$StudentMainScreenKt {
    public static final ComposableSingletons$StudentMainScreenKt INSTANCE = new ComposableSingletons$StudentMainScreenKt();

    /* JADX INFO: renamed from: lambda$-2096555225, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f133lambda$2096555225 = ComposableLambdaKt.composableLambdaInstance(-2096555225, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda__2096555225$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-904085701, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f143lambda$904085701 = ComposableLambdaKt.composableLambdaInstance(-904085701, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__904085701$lambda$1((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-202151108, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f130lambda$202151108 = ComposableLambdaKt.composableLambdaInstance(-202151108, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__202151108$lambda$2((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-759676006, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f141lambda$759676006 = ComposableLambdaKt.composableLambdaInstance(-759676006, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda26
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__759676006$lambda$3((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-615266311, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f139lambda$615266311 = ComposableLambdaKt.composableLambdaInstance(-615266311, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda38
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__615266311$lambda$4((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1235181273 = ComposableLambdaKt.composableLambdaInstance(1235181273, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda40
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1235181273$lambda$5((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2069517347, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f132lambda$2069517347 = ComposableLambdaKt.composableLambdaInstance(-2069517347, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda41
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__2069517347$lambda$6((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$368116368 = ComposableLambdaKt.composableLambdaInstance(368116368, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda42
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_368116368$lambda$7((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1466425708, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f122lambda$1466425708 = ComposableLambdaKt.composableLambdaInstance(-1466425708, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda43
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__1466425708$lambda$8((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1002415377 = ComposableLambdaKt.composableLambdaInstance(1002415377, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda45
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1002415377$lambda$9((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-832126699, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f142lambda$832126699 = ComposableLambdaKt.composableLambdaInstance(-832126699, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__832126699$lambda$10((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1636714386 = ComposableLambdaKt.composableLambdaInstance(1636714386, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda22
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1636714386$lambda$11((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-197827690, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f128lambda$197827690 = ComposableLambdaKt.composableLambdaInstance(-197827690, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda33
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__197827690$lambda$12((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2023953901, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f131lambda$2023953901 = ComposableLambdaKt.composableLambdaInstance(-2023953901, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda44
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__2023953901$lambda$13((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$436471319 = ComposableLambdaKt.composableLambdaInstance(436471319, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda46
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_436471319$lambda$14((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$445000942 = ComposableLambdaKt.composableLambdaInstance(445000942, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda47
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_445000942$lambda$15((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$358692133 = ComposableLambdaKt.composableLambdaInstance(358692133, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda48
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_358692133$lambda$16((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1834446618, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f126lambda$1834446618 = ComposableLambdaKt.composableLambdaInstance(-1834446618, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda49
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__1834446618$lambda$17((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$267381927 = ComposableLambdaKt.composableLambdaInstance(267381927, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda50
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_267381927$lambda$18((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1025595269 = ComposableLambdaKt.composableLambdaInstance(1025595269, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1025595269$lambda$19((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1639930918, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f124lambda$1639930918 = ComposableLambdaKt.composableLambdaInstance(-1639930918, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda__1639930918$lambda$20((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-354813514, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f136lambda$354813514 = ComposableLambdaKt.composableLambdaInstance(-354813514, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__354813514$lambda$21((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$832716307 = ComposableLambdaKt.composableLambdaInstance(832716307, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_832716307$lambda$22((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1849141209, reason: not valid java name */
    private static Function3<BoxScope, Composer, Integer, Unit> f127lambda$1849141209 = ComposableLambdaKt.composableLambdaInstance(-1849141209, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda__1849141209$lambda$23((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1986194358, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f129lambda$1986194358 = ComposableLambdaKt.composableLambdaInstance(-1986194358, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__1986194358$lambda$24((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$891846126 = ComposableLambdaKt.composableLambdaInstance(891846126, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_891846126$lambda$25((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-353296629, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f135lambda$353296629 = ComposableLambdaKt.composableLambdaInstance(-353296629, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__353296629$lambda$26((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<BoxScope, Composer, Integer, Unit> lambda$1416654249 = ComposableLambdaKt.composableLambdaInstance(1416654249, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1416654249$lambda$27((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1279601100 = ComposableLambdaKt.composableLambdaInstance(1279601100, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1279601100$lambda$28((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$553708405 = ComposableLambdaKt.composableLambdaInstance(553708405, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda13
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_553708405$lambda$29((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-667871702, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f140lambda$667871702 = ComposableLambdaKt.composableLambdaInstance(-667871702, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda__667871702$lambda$30((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1774390207, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f125lambda$1774390207 = ComposableLambdaKt.composableLambdaInstance(-1774390207, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda16
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda__1774390207$lambda$31((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-496744599, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f138lambda$496744599 = ComposableLambdaKt.composableLambdaInstance(-496744599, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda17
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda__496744599$lambda$32((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1698393683 = ComposableLambdaKt.composableLambdaInstance(1698393683, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda18
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1698393683$lambda$33((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1395432270, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f121lambda$1395432270 = ComposableLambdaKt.composableLambdaInstance(-1395432270, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda19
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda__1395432270$lambda$34((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1526609062, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f123lambda$1526609062 = ComposableLambdaKt.composableLambdaInstance(-1526609062, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda20
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda__1526609062$lambda$35((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1939061676 = ComposableLambdaKt.composableLambdaInstance(1939061676, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda21
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1939061676$lambda$37((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-344923609, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f134lambda$344923609 = ComposableLambdaKt.composableLambdaInstance(-344923609, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda23
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__344923609$lambda$38((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-425011794, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f137lambda$425011794 = ComposableLambdaKt.composableLambdaInstance(-425011794, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda24
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda__425011794$lambda$39((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$1980558776 = ComposableLambdaKt.composableLambdaInstance(1980558776, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda25
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1980558776$lambda$42((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<LazyItemScope, Composer, Integer, Unit> lambda$1975906726 = ComposableLambdaKt.composableLambdaInstance(1975906726, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda27
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1975906726$lambda$44((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<LazyItemScope, Composer, Integer, Unit> lambda$19745355 = ComposableLambdaKt.composableLambdaInstance(19745355, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda28
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda_19745355$lambda$46((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1302715843 = ComposableLambdaKt.composableLambdaInstance(1302715843, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda29
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1302715843$lambda$47((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1364702802 = ComposableLambdaKt.composableLambdaInstance(1364702802, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda30
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1364702802$lambda$48((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$683750248 = ComposableLambdaKt.composableLambdaInstance(683750248, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda31
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_683750248$lambda$49((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1850815612 = ComposableLambdaKt.composableLambdaInstance(1850815612, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda32
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1850815612$lambda$50((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1725505153 = ComposableLambdaKt.composableLambdaInstance(1725505153, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda34
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1725505153$lambda$52((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1466824859 = ComposableLambdaKt.composableLambdaInstance(1466824859, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda35
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1466824859$lambda$54((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1677588204 = ComposableLambdaKt.composableLambdaInstance(1677588204, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda36
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$StudentMainScreenKt.lambda_1677588204$lambda$55((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$525466419 = ComposableLambdaKt.composableLambdaInstance(525466419, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda37
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_525466419$lambda$56((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$287707541 = ComposableLambdaKt.composableLambdaInstance(287707541, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$StudentMainScreenKt$$ExternalSyntheticLambda39
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$StudentMainScreenKt.lambda_287707541$lambda$58((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1395432270$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6972getLambda$1395432270$app() {
        return f121lambda$1395432270;
    }

    /* JADX INFO: renamed from: getLambda$-1466425708$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6973getLambda$1466425708$app() {
        return f122lambda$1466425708;
    }

    /* JADX INFO: renamed from: getLambda$-1526609062$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6974getLambda$1526609062$app() {
        return f123lambda$1526609062;
    }

    /* JADX INFO: renamed from: getLambda$-1639930918$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6975getLambda$1639930918$app() {
        return f124lambda$1639930918;
    }

    /* JADX INFO: renamed from: getLambda$-1774390207$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6976getLambda$1774390207$app() {
        return f125lambda$1774390207;
    }

    /* JADX INFO: renamed from: getLambda$-1834446618$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6977getLambda$1834446618$app() {
        return f126lambda$1834446618;
    }

    /* JADX INFO: renamed from: getLambda$-1849141209$app, reason: not valid java name */
    public final Function3<BoxScope, Composer, Integer, Unit> m6978getLambda$1849141209$app() {
        return f127lambda$1849141209;
    }

    /* JADX INFO: renamed from: getLambda$-197827690$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6979getLambda$197827690$app() {
        return f128lambda$197827690;
    }

    /* JADX INFO: renamed from: getLambda$-1986194358$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6980getLambda$1986194358$app() {
        return f129lambda$1986194358;
    }

    /* JADX INFO: renamed from: getLambda$-202151108$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6981getLambda$202151108$app() {
        return f130lambda$202151108;
    }

    /* JADX INFO: renamed from: getLambda$-2023953901$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6982getLambda$2023953901$app() {
        return f131lambda$2023953901;
    }

    /* JADX INFO: renamed from: getLambda$-2069517347$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6983getLambda$2069517347$app() {
        return f132lambda$2069517347;
    }

    /* JADX INFO: renamed from: getLambda$-2096555225$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6984getLambda$2096555225$app() {
        return f133lambda$2096555225;
    }

    /* JADX INFO: renamed from: getLambda$-344923609$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6985getLambda$344923609$app() {
        return f134lambda$344923609;
    }

    /* JADX INFO: renamed from: getLambda$-353296629$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6986getLambda$353296629$app() {
        return f135lambda$353296629;
    }

    /* JADX INFO: renamed from: getLambda$-354813514$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6987getLambda$354813514$app() {
        return f136lambda$354813514;
    }

    /* JADX INFO: renamed from: getLambda$-425011794$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6988getLambda$425011794$app() {
        return f137lambda$425011794;
    }

    /* JADX INFO: renamed from: getLambda$-496744599$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6989getLambda$496744599$app() {
        return f138lambda$496744599;
    }

    /* JADX INFO: renamed from: getLambda$-615266311$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6990getLambda$615266311$app() {
        return f139lambda$615266311;
    }

    /* JADX INFO: renamed from: getLambda$-667871702$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6991getLambda$667871702$app() {
        return f140lambda$667871702;
    }

    /* JADX INFO: renamed from: getLambda$-759676006$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6992getLambda$759676006$app() {
        return f141lambda$759676006;
    }

    /* JADX INFO: renamed from: getLambda$-832126699$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6993getLambda$832126699$app() {
        return f142lambda$832126699;
    }

    /* JADX INFO: renamed from: getLambda$-904085701$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6994getLambda$904085701$app() {
        return f143lambda$904085701;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1002415377$app() {
        return lambda$1002415377;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1025595269$app() {
        return lambda$1025595269;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1235181273$app() {
        return lambda$1235181273;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1279601100$app() {
        return lambda$1279601100;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1302715843$app() {
        return lambda$1302715843;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1364702802$app() {
        return lambda$1364702802;
    }

    public final Function3<BoxScope, Composer, Integer, Unit> getLambda$1416654249$app() {
        return lambda$1416654249;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1466824859$app() {
        return lambda$1466824859;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1636714386$app() {
        return lambda$1636714386;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1677588204$app() {
        return lambda$1677588204;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1698393683$app() {
        return lambda$1698393683;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1725505153$app() {
        return lambda$1725505153;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1850815612$app() {
        return lambda$1850815612;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1939061676$app() {
        return lambda$1939061676;
    }

    public final Function3<LazyItemScope, Composer, Integer, Unit> getLambda$19745355$app() {
        return lambda$19745355;
    }

    public final Function3<LazyItemScope, Composer, Integer, Unit> getLambda$1975906726$app() {
        return lambda$1975906726;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$1980558776$app() {
        return lambda$1980558776;
    }

    public final Function2<Composer, Integer, Unit> getLambda$267381927$app() {
        return lambda$267381927;
    }

    public final Function2<Composer, Integer, Unit> getLambda$287707541$app() {
        return lambda$287707541;
    }

    public final Function2<Composer, Integer, Unit> getLambda$358692133$app() {
        return lambda$358692133;
    }

    public final Function2<Composer, Integer, Unit> getLambda$368116368$app() {
        return lambda$368116368;
    }

    public final Function2<Composer, Integer, Unit> getLambda$436471319$app() {
        return lambda$436471319;
    }

    public final Function2<Composer, Integer, Unit> getLambda$445000942$app() {
        return lambda$445000942;
    }

    public final Function2<Composer, Integer, Unit> getLambda$525466419$app() {
        return lambda$525466419;
    }

    public final Function2<Composer, Integer, Unit> getLambda$553708405$app() {
        return lambda$553708405;
    }

    public final Function2<Composer, Integer, Unit> getLambda$683750248$app() {
        return lambda$683750248;
    }

    public final Function2<Composer, Integer, Unit> getLambda$832716307$app() {
        return lambda$832716307;
    }

    public final Function2<Composer, Integer, Unit> getLambda$891846126$app() {
        return lambda$891846126;
    }

    static final Unit lambda__904085701$lambda$1(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C380@18925L10,382@19040L11,378@18829L246:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-904085701, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-904085701.<anonymous> (StudentMainScreen.kt:378)");
            }
            TextStyle titleLarge = MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleLarge();
            TextKt.m2693Text4IGK_g("Classroom Expired", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, titleLarge, $composer, 196614, 0, 65498);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__202151108$lambda$2(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C388@19321L10,386@19128L232:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-202151108, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-202151108.<anonymous> (StudentMainScreen.kt:386)");
            }
            TextKt.m2693Text4IGK_g("This classroom has expired. The Calis Device has been unregistered or renamed by the teacher back to Factory Mode.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 6, 0, 65534);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2096555225$lambda$0(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C396@19655L40:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2096555225, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-2096555225.<anonymous> (StudentMainScreen.kt:396)");
            }
            TextKt.m2693Text4IGK_g("OK", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__759676006$lambda$3(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C413@20309L10,411@20202L196:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-759676006, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-759676006.<anonymous> (StudentMainScreen.kt:411)");
            }
            TextKt.m2693Text4IGK_g("Attendance Check-In Progress", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleLarge(), $composer, 196614, 0, 65502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__615266311$lambda$4(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C472@24251L10,470@24142L198:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-615266311, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-615266311.<anonymous> (StudentMainScreen.kt:470)");
            }
            TextKt.m2693Text4IGK_g("Task Score Submission Progress", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleLarge(), $composer, 196614, 0, 65502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2069517347$lambda$6(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C584@30343L53:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2069517347, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-2069517347.<anonymous> (StudentMainScreen.kt:584)");
            }
            IconKt.m2150Iconww6aTOc(SchoolKt.getSchool(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1235181273$lambda$5(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C585@30434L34:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1235181273, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1235181273.<anonymous> (StudentMainScreen.kt:585)");
            }
            TextKt.m2693Text4IGK_g("Classroom & Device Control", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1466425708$lambda$8(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C598@31057L53:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1466425708, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-1466425708.<anonymous> (StudentMainScreen.kt:598)");
            }
            IconKt.m2150Iconww6aTOc(PersonKt.getPerson(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_368116368$lambda$7(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C599@31148L15:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(368116368, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$368116368.<anonymous> (StudentMainScreen.kt:599)");
            }
            TextKt.m2693Text4IGK_g("Profile", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__832126699$lambda$10(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C612@31758L55:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-832126699, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-832126699.<anonymous> (StudentMainScreen.kt:612)");
            }
            IconKt.m2150Iconww6aTOc(SettingsKt.getSettings(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1002415377$lambda$9(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C613@31851L16:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1002415377, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1002415377.<anonymous> (StudentMainScreen.kt:613)");
            }
            TextKt.m2693Text4IGK_g("Settings", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__197827690$lambda$12(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C626@32464L51:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-197827690, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-197827690.<anonymous> (StudentMainScreen.kt:626)");
            }
            IconKt.m2150Iconww6aTOc(InfoKt.getInfo(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1636714386$lambda$11(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C627@32553L13:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1636714386, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1636714386.<anonymous> (StudentMainScreen.kt:627)");
            }
            TextKt.m2693Text4IGK_g("About", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_436471319$lambda$14(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C648@33526L11,645@33320L253:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(436471319, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$436471319.<anonymous> (StudentMainScreen.kt:645)");
            }
            IconKt.m2150Iconww6aTOc(ExitToAppKt.getExitToApp(Icons.AutoMirrored.Filled.INSTANCE), "Log Out", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), $composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2023953901$lambda$13(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C655@33835L11,652@33663L219:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2023953901, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-2023953901.<anonymous> (StudentMainScreen.kt:652)");
            }
            TextKt.m2693Text4IGK_g("Log Out", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131034);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_445000942$lambda$15(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C743@38484L202:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(445000942, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$445000942.<anonymous> (StudentMainScreen.kt:743)");
            }
            IconKt.m2150Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_358692133$lambda$16(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C754@39123L202:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(358692133, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$358692133.<anonymous> (StudentMainScreen.kt:754)");
            }
            IconKt.m2150Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1834446618$lambda$17(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C765@39769L202:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1834446618, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-1834446618.<anonymous> (StudentMainScreen.kt:765)");
            }
            IconKt.m2150Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_267381927$lambda$18(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C776@40405L202:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(267381927, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$267381927.<anonymous> (StudentMainScreen.kt:776)");
            }
            IconKt.m2150Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1025595269$lambda$19(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C784@40842L60:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1025595269, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1025595269.<anonymous> (StudentMainScreen.kt:784)");
            }
            IconKt.m2150Iconww6aTOc(MenuKt.getMenu(Icons.INSTANCE.getDefault()), "Open Drawer", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1639930918$lambda$20(RowScope TopAppBar, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TopAppBar, "$this$TopAppBar");
        ComposerKt.sourceInformation($composer, "C:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1639930918, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-1639930918.<anonymous> (StudentMainScreen.kt:789)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__354813514$lambda$21(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C800@41473L53:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-354813514, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-354813514.<anonymous> (StudentMainScreen.kt:800)");
            }
            IconKt.m2150Iconww6aTOc(SchoolKt.getSchool(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_832716307$lambda$22(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C801@41564L17:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(832716307, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$832716307.<anonymous> (StudentMainScreen.kt:801)");
            }
            TextKt.m2693Text4IGK_g("Classroom", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1849141209$lambda$23(BoxScope BadgedBox, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(BadgedBox, "$this$BadgedBox");
        ComposerKt.sourceInformation($composer, "C822@42571L58:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1849141209, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-1849141209.<anonymous> (StudentMainScreen.kt:822)");
            }
            IconKt.m2150Iconww6aTOc(CheckCircleKt.getCheckCircle(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1986194358$lambda$24(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C825@42721L16:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1986194358, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-1986194358.<anonymous> (StudentMainScreen.kt:825)");
            }
            TextKt.m2693Text4IGK_g("Check-In", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_891846126$lambda$25(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C836@43248L55:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(891846126, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$891846126.<anonymous> (StudentMainScreen.kt:836)");
            }
            IconKt.m2150Iconww6aTOc(MenuBookKt.getMenuBook(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__353296629$lambda$26(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C837@43341L16:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-353296629, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-353296629.<anonymous> (StudentMainScreen.kt:837)");
            }
            TextKt.m2693Text4IGK_g("Subjects", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1416654249$lambda$27(BoxScope BadgedBox, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(BadgedBox, "$this$BadgedBox");
        ComposerKt.sourceInformation($composer, "C858@44348L57:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1416654249, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1416654249.<anonymous> (StudentMainScreen.kt:858)");
            }
            IconKt.m2150Iconww6aTOc(AssignmentKt.getAssignment(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1279601100$lambda$28(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C861@44497L13:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1279601100, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1279601100.<anonymous> (StudentMainScreen.kt:861)");
            }
            TextKt.m2693Text4IGK_g("Tasks", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_553708405$lambda$29(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1015@52548L13:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(553708405, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$553708405.<anonymous> (StudentMainScreen.kt:1015)");
            }
            AboutScreenKt.AboutScreen(null, $composer, 0, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__667871702$lambda$30(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1235@61610L66:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-667871702, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-667871702.<anonymous> (StudentMainScreen.kt:1235)");
            }
            TextKt.m2693Text4IGK_g("Grant", (Modifier) null, 0L, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1774390207$lambda$31(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1255@62682L66:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1774390207, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-1774390207.<anonymous> (StudentMainScreen.kt:1255)");
            }
            TextKt.m2693Text4IGK_g("Grant", (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__496744599$lambda$32(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1320@66107L66:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-496744599, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-496744599.<anonymous> (StudentMainScreen.kt:1320)");
            }
            TextKt.m2693Text4IGK_g("Allow", (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1698393683$lambda$33(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1343@67242L67:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1698393683, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1698393683.<anonymous> (StudentMainScreen.kt:1343)");
            }
            TextKt.m2693Text4IGK_g("Enable", (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1395432270$lambda$34(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1367@68519L65:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1395432270, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-1395432270.<anonymous> (StudentMainScreen.kt:1367)");
            }
            TextKt.m2693Text4IGK_g("Join", (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__344923609$lambda$38(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1398@69771L566:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-344923609, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-344923609.<anonymous> (StudentMainScreen.kt:1398)");
            }
            SurfaceKt.m2543SurfaceT9BRK9s(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(44)), RoundedCornerShapeKt.getCircleShape(), ColorKt.Color(4294963698L), 0L, 0.0f, 0.0f, null, lambda$1939061676, $composer, 12583302, 120);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x016c  */
    static final Unit lambda_1939061676$lambda$37(Composer $composer, int $changed) {
        int i;
        ComposerKt.sourceInformation($composer, "C1403@69960L359:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1939061676, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1939061676.<anonymous> (StudentMainScreen.kt:1403)");
            }
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i2 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
                i = 48;
            } else {
                i = 48;
                if (!Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = (i2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((i >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1527105149, "C1404@70027L270:StudentMainScreen.kt#2thlc2");
                IconKt.m2150Iconww6aTOc(HelpOutlineKt.getHelpOutline(Icons.Outlined.INSTANCE), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(24)), ColorKt.Color(4292943176L), $composer, 3504, 0);
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
            ComposerKt.sourceInformationMarkerStart($composer, -1527105149, "C1404@70027L270:StudentMainScreen.kt#2thlc2");
            IconKt.m2150Iconww6aTOc(HelpOutlineKt.getHelpOutline(Icons.Outlined.INSTANCE), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(24)), ColorKt.Color(4292943176L), $composer, 3504, 0);
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

    static final Unit lambda__1526609062$lambda$35(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1445@71717L44:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1526609062, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-1526609062.<anonymous> (StudentMainScreen.kt:1445)");
            }
            TextKt.m2693Text4IGK_g("Got it", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__425011794$lambda$39(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1770@87960L205:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-425011794, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$-425011794.<anonymous> (StudentMainScreen.kt:1770)");
            }
            IconKt.m2150Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Close", (Modifier) null, ColorKt.Color(4283127139L), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x035f  */
    static final Unit lambda_1980558776$lambda$42(ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer composer;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C1791@88854L1392:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1980558776, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1980558776.<anonymous> (StudentMainScreen.kt:1791)");
            }
            Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(16));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM550spacedBy0680j_4, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM670padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM3652constructorimpl = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 726494834, "C1796@89115L277,1802@89417L807:StudentMainScreen.kt#2thlc2");
            IconKt.m2150Iconww6aTOc(InfoKt.getInfo(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(24)), ColorKt.Color(4283127139L), $composer, 3504, 0);
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM3652constructorimpl2 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl2.getInserting()) {
                composer = $composer;
            } else {
                composer = $composer;
                if (!Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                Composer composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i6 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, 580510089, "C1805@89574L10,1803@89454L305,1809@89788L40,1812@90074L10,1810@89857L341:StudentMainScreen.kt#2thlc2");
                TextKt.m2693Text4IGK_g("Classroom Expired", (Modifier) null, ColorKt.Color(4280232247L), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getTitleSmall(), composer2, 196998, 0, 65498);
                SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(2)), composer2, 6);
                TextKt.m2693Text4IGK_g("This classroom has expired. The Calis Device has been unregistered or renamed by the teacher back to Factory Mode.", (Modifier) null, ColorKt.Color(4283127139L), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getBodySmall(), composer2, 390, 0, 65530);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
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
            composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
            composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = (i4 >> 6) & 14;
            Composer composer3 = composer;
            ComposerKt.sourceInformationMarkerStart(composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i8 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, 580510089, "C1805@89574L10,1803@89454L305,1809@89788L40,1812@90074L10,1810@89857L341:StudentMainScreen.kt#2thlc2");
            TextKt.m2693Text4IGK_g("Classroom Expired", (Modifier) null, ColorKt.Color(4280232247L), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer3, MaterialTheme.$stable).getTitleSmall(), composer3, 196998, 0, 65498);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(2)), composer3, 6);
            TextKt.m2693Text4IGK_g("This classroom has expired. The Calis Device has been unregistered or renamed by the teacher back to Factory Mode.", (Modifier) null, ColorKt.Color(4283127139L), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer3, MaterialTheme.$stable).getBodySmall(), composer3, 390, 0, 65530);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
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

    static final Unit lambda_1975906726$lambda$44(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C2368@119807L660:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1975906726, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1975906726.<anonymous> (StudentMainScreen.kt:2368)");
            }
            Modifier modifierM672paddingVpY3zN4$default = PaddingKt.m672paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m6622constructorimpl(40), 1, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM672paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
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
            if (composerM3652constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 664120250, "C2376@120229L10,2377@120302L11,2374@120064L381:StudentMainScreen.kt#2thlc2");
            TextKt.m2693Text4IGK_g("No tasks received yet. Refresh or check back when teacher posts tasks.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 6, 0, 65018);
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

    static final Unit lambda_19745355$lambda$46(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C2465@125954L582:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(19745355, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$19745355.<anonymous> (StudentMainScreen.kt:2465)");
            }
            Modifier modifierM674paddingqDBjuR0$default = PaddingKt.m674paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m6622constructorimpl(24), 0.0f, Dp.m6622constructorimpl(120), 5, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM674paddingqDBjuR0$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((390 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((390 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1961018837, "C2473@126324L10,2474@126393L11,2471@126217L301:StudentMainScreen.kt#2thlc2");
            TextStyle bodyMedium = MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium();
            long onSurfaceVariant = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant();
            TextKt.m2693Text4IGK_g("Pull down to refresh", (Modifier) null, Color.m4157copywmQWz5c(onSurfaceVariant, (14 & 1) != 0 ? Color.m4161getAlphaimpl(onSurfaceVariant) : 0.7f, (14 & 2) != 0 ? Color.m4165getRedimpl(onSurfaceVariant) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(onSurfaceVariant) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(onSurfaceVariant) : 0.0f), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodyMedium, $composer, 196614, 0, 65498);
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

    static final Unit lambda_683750248$lambda$49(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2519@127976L22:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(683750248, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$683750248.<anonymous> (StudentMainScreen.kt:2519)");
            }
            TextKt.m2693Text4IGK_g("Obtained Score", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1302715843$lambda$47(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C2542@128962L20:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1302715843, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1302715843.<anonymous> (StudentMainScreen.kt:2542)");
            }
            TextKt.m2693Text4IGK_g("Submit Score", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1364702802$lambda$48(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C2546@129080L14:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1364702802, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1364702802.<anonymous> (StudentMainScreen.kt:2546)");
            }
            TextKt.m2693Text4IGK_g("Cancel", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1725505153$lambda$52(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2691@136040L491:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1725505153, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1725505153.<anonymous> (StudentMainScreen.kt:2691)");
            }
            Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(12));
            Arrangement.Horizontal horizontalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(8));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM550spacedBy0680j_4, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM670padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1400656769, "C2692@136268L11,2692@136214L72,2693@136397L10,2693@136442L11,2693@136319L182:StudentMainScreen.kt#2thlc2");
            IconKt.m2150Iconww6aTOc(CancelKt.getCancel(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), $composer, 48, 4);
            TextKt.m2693Text4IGK_g("The Teacher rejected your registration request.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnErrorContainer(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 196614, 0, 65498);
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

    static final Unit lambda_1466824859$lambda$54(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2717@138631L605:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1466824859, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1466824859.<anonymous> (StudentMainScreen.kt:2717)");
            }
            Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(16));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM670padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 113648518, "C2718@138863L11,2718@138806L119,2719@138962L40,2720@139099L10,2720@139143L11,2720@139039L163:StudentMainScreen.kt#2thlc2");
            IconKt.m2150Iconww6aTOc(BluetoothKt.getBluetooth(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(28)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), $composer, 432, 0);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(8)), $composer, 6);
            TextKt.m2693Text4IGK_g("Scanning for Calis Devices...", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 65018);
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

    static final Unit lambda_1850815612$lambda$50(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C2781@143146L14:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1850815612, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1850815612.<anonymous> (StudentMainScreen.kt:2781)");
            }
            TextKt.m2693Text4IGK_g("Cancel", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_525466419$lambda$56(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2831@144959L29:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(525466419, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$525466419.<anonymous> (StudentMainScreen.kt:2831)");
            }
            TextKt.m2693Text4IGK_g("My Attendance History", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1677588204$lambda$55(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C2889@148475L13:StudentMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1677588204, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$1677588204.<anonymous> (StudentMainScreen.kt:2889)");
            }
            TextKt.m2693Text4IGK_g("Close", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0173  */
    static final Unit lambda_287707541$lambda$58(Composer $composer, int $changed) {
        int i;
        ComposerKt.sourceInformation($composer, "C3520@175867L371:StudentMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(287707541, $changed, -1, "com.example.ui.screens.ComposableSingletons$StudentMainScreenKt.lambda$287707541.<anonymous> (StudentMainScreen.kt:3520)");
            }
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i2 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
                i = 48;
            } else {
                i = 48;
                if (!Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = (i2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((i >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1643475654, "C3524@176108L11,3521@175934L282:StudentMainScreen.kt#2thlc2");
                IconKt.m2150Iconww6aTOc(PersonKt.getPerson(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(44)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimary(), $composer, 432, 0);
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
            ComposerKt.sourceInformationMarkerStart($composer, -1643475654, "C3524@176108L11,3521@175934L282:StudentMainScreen.kt#2thlc2");
            IconKt.m2150Iconww6aTOc(PersonKt.getPerson(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(44)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimary(), $composer, 432, 0);
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
}
