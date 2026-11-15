package com.example.ui.screens;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material.icons.automirrored.filled.ExitToAppKt;
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRightKt;
import androidx.compose.material.icons.filled.AddKt;
import androidx.compose.material.icons.filled.AssignmentKt;
import androidx.compose.material.icons.filled.BluetoothKt;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.ClearKt;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.DownloadKt;
import androidx.compose.material.icons.filled.GroupAddKt;
import androidx.compose.material.icons.filled.GroupKt;
import androidx.compose.material.icons.filled.HistoryKt;
import androidx.compose.material.icons.filled.InfoKt;
import androidx.compose.material.icons.filled.MenuBookKt;
import androidx.compose.material.icons.filled.MenuKt;
import androidx.compose.material.icons.filled.PersonKt;
import androidx.compose.material.icons.filled.PlayArrowKt;
import androidx.compose.material.icons.filled.SchoolKt;
import androidx.compose.material.icons.filled.SearchKt;
import androidx.compose.material.icons.filled.SettingsKt;
import androidx.compose.material.icons.filled.StopKt;
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
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TeacherMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$TeacherMainScreenKt {
    public static final ComposableSingletons$TeacherMainScreenKt INSTANCE = new ComposableSingletons$TeacherMainScreenKt();

    /* JADX INFO: renamed from: lambda$-2068277426, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f170lambda$2068277426 = ComposableLambdaKt.composableLambdaInstance(-2068277426, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__2068277426$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1636196782, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f157lambda$1636196782 = ComposableLambdaKt.composableLambdaInstance(-1636196782, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda21
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1636196782$lambda$1((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-121857915, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f147lambda$121857915 = ComposableLambdaKt.composableLambdaInstance(-121857915, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda33
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__121857915$lambda$2((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1504186743, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f151lambda$1504186743 = ComposableLambdaKt.composableLambdaInstance(-1504186743, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda45
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1504186743$lambda$3((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-235468410, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f175lambda$235468410 = ComposableLambdaKt.composableLambdaInstance(-235468410, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda57
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__235468410$lambda$4((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1617797238, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f156lambda$1617797238 = ComposableLambdaKt.composableLambdaInstance(-1617797238, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda69
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1617797238$lambda$5((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-349078905, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f181lambda$349078905 = ComposableLambdaKt.composableLambdaInstance(-349078905, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda81
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__349078905$lambda$6((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1731407733, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f162lambda$1731407733 = ComposableLambdaKt.composableLambdaInstance(-1731407733, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda93
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1731407733$lambda$7((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-462689400, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f183lambda$462689400 = ComposableLambdaKt.composableLambdaInstance(-462689400, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda105
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__462689400$lambda$8((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1845018228, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f165lambda$1845018228 = ComposableLambdaKt.composableLambdaInstance(-1845018228, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1845018228$lambda$9((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-576299895, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f187lambda$576299895 = ComposableLambdaKt.composableLambdaInstance(-576299895, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__576299895$lambda$10((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1958628723, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f168lambda$1958628723 = ComposableLambdaKt.composableLambdaInstance(-1958628723, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1958628723$lambda$11((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1756799961 = ComposableLambdaKt.composableLambdaInstance(1756799961, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1756799961$lambda$12((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1670491152 = ComposableLambdaKt.composableLambdaInstance(1670491152, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda13
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1670491152$lambda$13((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-522647599, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f184lambda$522647599 = ComposableLambdaKt.composableLambdaInstance(-522647599, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__522647599$lambda$14((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1579180946 = ComposableLambdaKt.composableLambdaInstance(1579180946, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1579180946$lambda$15((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-613957805, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f188lambda$613957805 = ComposableLambdaKt.composableLambdaInstance(-613957805, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda16
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__613957805$lambda$16((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1957573008, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f167lambda$1957573008 = ComposableLambdaKt.composableLambdaInstance(-1957573008, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda17
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1957573008$lambda$17((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-328131899, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f180lambda$328131899 = ComposableLambdaKt.composableLambdaInstance(-328131899, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda18
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__328131899$lambda$18((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$956985505 = ComposableLambdaKt.composableLambdaInstance(956985505, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda19
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_956985505$lambda$19((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$2144515326 = ComposableLambdaKt.composableLambdaInstance(2144515326, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda22
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_2144515326$lambda$20((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-537342190, reason: not valid java name */
    private static Function3<BoxScope, Composer, Integer, Unit> f185lambda$537342190 = ComposableLambdaKt.composableLambdaInstance(-537342190, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda23
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__537342190$lambda$21((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-674395339, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f190lambda$674395339 = ComposableLambdaKt.composableLambdaInstance(-674395339, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda24
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__674395339$lambda$22((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2091322151, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f171lambda$2091322151 = ComposableLambdaKt.composableLambdaInstance(-2091322151, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda25
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__2091322151$lambda$23((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$958502390 = ComposableLambdaKt.composableLambdaInstance(958502390, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda26
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_958502390$lambda$24((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1566514028, reason: not valid java name */
    private static Function3<BoxScope, Composer, Integer, Unit> f152lambda$1566514028 = ComposableLambdaKt.composableLambdaInstance(-1566514028, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda27
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1566514028$lambda$25((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1703567177, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f161lambda$1703567177 = ComposableLambdaKt.composableLambdaInstance(-1703567177, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda28
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1703567177$lambda$26((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-29147658, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f177lambda$29147658 = ComposableLambdaKt.composableLambdaInstance(-29147658, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda29
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__29147658$lambda$27((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$706516469 = ComposableLambdaKt.composableLambdaInstance(706516469, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda30
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_706516469$lambda$28((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1198171233, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f146lambda$1198171233 = ComposableLambdaKt.composableLambdaInstance(-1198171233, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda32
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1198171233$lambda$29((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1959840420 = ComposableLambdaKt.composableLambdaInstance(1959840420, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda34
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1959840420$lambda$30((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1916579245, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f166lambda$1916579245 = ComposableLambdaKt.composableLambdaInstance(-1916579245, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda35
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1916579245$lambda$32((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1832240809, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f164lambda$1832240809 = ComposableLambdaKt.composableLambdaInstance(-1832240809, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda36
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1832240809$lambda$33((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1155847617 = ComposableLambdaKt.composableLambdaInstance(1155847617, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda37
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1155847617$lambda$34((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1685191925, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f159lambda$1685191925 = ComposableLambdaKt.composableLambdaInstance(-1685191925, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda38
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1685191925$lambda$35((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-268738841, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f176lambda$268738841 = ComposableLambdaKt.composableLambdaInstance(-268738841, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda39
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__268738841$lambda$37((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1151097503 = ComposableLambdaKt.composableLambdaInstance(1151097503, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda40
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1151097503$lambda$38((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1941838969 = ComposableLambdaKt.composableLambdaInstance(1941838969, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda41
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1941838969$lambda$39((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$264645501 = ComposableLambdaKt.composableLambdaInstance(264645501, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda43
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_264645501$lambda$40((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1787771021 = ComposableLambdaKt.composableLambdaInstance(1787771021, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda44
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1787771021$lambda$41((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1069143410, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f145lambda$1069143410 = ComposableLambdaKt.composableLambdaInstance(-1069143410, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda46
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1069143410$lambda$42((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1329066166 = ComposableLambdaKt.composableLambdaInstance(1329066166, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda47
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1329066166$lambda$43((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1535510067 = ComposableLambdaKt.composableLambdaInstance(1535510067, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda48
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1535510067$lambda$44((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-542636399, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f186lambda$542636399 = ComposableLambdaKt.composableLambdaInstance(-542636399, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda49
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__542636399$lambda$45((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$288578178 = ComposableLambdaKt.composableLambdaInstance(288578178, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda50
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_288578178$lambda$46((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-316410896, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f179lambda$316410896 = ComposableLambdaKt.composableLambdaInstance(-316410896, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda51
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__316410896$lambda$48((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-657531883, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f189lambda$657531883 = ComposableLambdaKt.composableLambdaInstance(-657531883, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda52
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__657531883$lambda$49((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$214763092 = ComposableLambdaKt.composableLambdaInstance(214763092, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda54
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_214763092$lambda$50((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-885868135, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f194lambda$885868135 = ComposableLambdaKt.composableLambdaInstance(-885868135, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda55
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__885868135$lambda$51((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$568822002 = ComposableLambdaKt.composableLambdaInstance(568822002, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda56
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_568822002$lambda$52((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1867232835 = ComposableLambdaKt.composableLambdaInstance(1867232835, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda58
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1867232835$lambda$53((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-886105670, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f195lambda$886105670 = ComposableLambdaKt.composableLambdaInstance(-886105670, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda59
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__886105670$lambda$54((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-980476287, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f199lambda$980476287 = ComposableLambdaKt.composableLambdaInstance(-980476287, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda60
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__980476287$lambda$55((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$941119442 = ComposableLambdaKt.composableLambdaInstance(941119442, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda61
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_941119442$lambda$56((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-787119891, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f192lambda$787119891 = ComposableLambdaKt.composableLambdaInstance(-787119891, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda62
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__787119891$lambda$57((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$552160650 = ComposableLambdaKt.composableLambdaInstance(552160650, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda63
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_552160650$lambda$58((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1306499891 = ComposableLambdaKt.composableLambdaInstance(1306499891, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda65
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1306499891$lambda$59((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$615595922 = ComposableLambdaKt.composableLambdaInstance(615595922, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda66
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_615595922$lambda$60((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1693196316, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f160lambda$1693196316 = ComposableLambdaKt.composableLambdaInstance(-1693196316, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda67
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1693196316$lambda$61((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$162739855 = ComposableLambdaKt.composableLambdaInstance(162739855, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda68
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_162739855$lambda$62((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1651456237, reason: not valid java name */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f158lambda$1651456237 = ComposableLambdaKt.composableLambdaInstance(-1651456237, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda70
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1651456237$lambda$63((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1588158751, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f153lambda$1588158751 = ComposableLambdaKt.composableLambdaInstance(-1588158751, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda71
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1588158751$lambda$64((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1060403958, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f144lambda$1060403958 = ComposableLambdaKt.composableLambdaInstance(-1060403958, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda72
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1060403958$lambda$65((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-366745226, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f182lambda$366745226 = ComposableLambdaKt.composableLambdaInstance(-366745226, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda73
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__366745226$lambda$66((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-950921249, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f196lambda$950921249 = ComposableLambdaKt.composableLambdaInstance(-950921249, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda74
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__950921249$lambda$67((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$283143038 = ComposableLambdaKt.composableLambdaInstance(283143038, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda76
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_283143038$lambda$68((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1359580626, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f148lambda$1359580626 = ComposableLambdaKt.composableLambdaInstance(-1359580626, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda77
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1359580626$lambda$69((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1833075796 = ComposableLambdaKt.composableLambdaInstance(1833075796, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda78
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1833075796$lambda$70((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1386219395, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f149lambda$1386219395 = ComposableLambdaKt.composableLambdaInstance(-1386219395, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda79
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1386219395$lambda$71((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1868796396 = ComposableLambdaKt.composableLambdaInstance(1868796396, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda80
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1868796396$lambda$73((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$2105945645 = ComposableLambdaKt.composableLambdaInstance(2105945645, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda82
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_2105945645$lambda$74((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-72996482, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f191lambda$72996482 = ComposableLambdaKt.composableLambdaInstance(-72996482, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda83
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__72996482$lambda$75((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$907471001 = ComposableLambdaKt.composableLambdaInstance(907471001, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda84
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_907471001$lambda$76((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$321970984 = ComposableLambdaKt.composableLambdaInstance(321970984, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda85
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_321970984$lambda$77((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-86691823, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f193lambda$86691823 = ComposableLambdaKt.composableLambdaInstance(-86691823, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda87
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__86691823$lambda$78((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1624586544 = ComposableLambdaKt.composableLambdaInstance(1624586544, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda88
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1624586544$lambda$79((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-959102385, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f197lambda$959102385 = ComposableLambdaKt.composableLambdaInstance(-959102385, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda89
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__959102385$lambda$80((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$752175982 = ComposableLambdaKt.composableLambdaInstance(752175982, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda90
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_752175982$lambda$81((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1613542046, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f155lambda$1613542046 = ComposableLambdaKt.composableLambdaInstance(-1613542046, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda91
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1613542046$lambda$82((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1650836337 = ComposableLambdaKt.composableLambdaInstance(1650836337, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda92
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1650836337$lambda$83((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-96813194, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f198lambda$96813194 = ComposableLambdaKt.composableLambdaInstance(-96813194, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda94
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__96813194$lambda$84((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$569447549 = ComposableLambdaKt.composableLambdaInstance(569447549, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda95
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_569447549$lambda$85((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2134821772, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f173lambda$2134821772 = ComposableLambdaKt.composableLambdaInstance(-2134821772, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda96
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__2134821772$lambda$86((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-299533278, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f178lambda$299533278 = ComposableLambdaKt.composableLambdaInstance(-299533278, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda98
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__299533278$lambda$87((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1466409852 = ComposableLambdaKt.composableLambdaInstance(1466409852, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda99
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1466409852$lambda$88((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$925980346 = ComposableLambdaKt.composableLambdaInstance(925980346, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda100
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_925980346$lambda$89((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2058364506, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f169lambda$2058364506 = ComposableLambdaKt.composableLambdaInstance(-2058364506, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda101
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__2058364506$lambda$90((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$100719588 = ComposableLambdaKt.composableLambdaInstance(100719588, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda102
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_100719588$lambda$91((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1767187826, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f163lambda$1767187826 = ComposableLambdaKt.composableLambdaInstance(-1767187826, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda103
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1767187826$lambda$92((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$2071946895 = ComposableLambdaKt.composableLambdaInstance(2071946895, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda104
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_2071946895$lambda$93((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$111662216 = ComposableLambdaKt.composableLambdaInstance(111662216, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda106
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_111662216$lambda$94((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$667338170 = ComposableLambdaKt.composableLambdaInstance(667338170, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda107
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_667338170$lambda$95((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1871396593 = ComposableLambdaKt.composableLambdaInstance(1871396593, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1871396593$lambda$96((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1329082162 = ComposableLambdaKt.composableLambdaInstance(1329082162, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1329082162$lambda$97((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<LazyItemScope, Composer, Integer, Unit> lambda$1848334767 = ComposableLambdaKt.composableLambdaInstance(1848334767, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1848334767$lambda$98((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2125065894, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f172lambda$2125065894 = ComposableLambdaKt.composableLambdaInstance(-2125065894, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__2125065894$lambda$99((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$47658032 = ComposableLambdaKt.composableLambdaInstance(47658032, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_47658032$lambda$100((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1165191449 = ComposableLambdaKt.composableLambdaInstance(1165191449, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1165191449$lambda$101((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1157811128 = ComposableLambdaKt.composableLambdaInstance(1157811128, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1157811128$lambda$102((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1439190952, reason: not valid java name */
    private static Function3<ColumnScope, Composer, Integer, Unit> f150lambda$1439190952 = ComposableLambdaKt.composableLambdaInstance(-1439190952, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__1439190952$lambda$104((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-228093449, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f174lambda$228093449 = ComposableLambdaKt.composableLambdaInstance(-228093449, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda20
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__228093449$lambda$105((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$433656494 = ComposableLambdaKt.composableLambdaInstance(433656494, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda31
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_433656494$lambda$106((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1683232623 = ComposableLambdaKt.composableLambdaInstance(1683232623, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda42
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1683232623$lambda$107((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<LazyItemScope, Composer, Integer, Unit> lambda$132290185 = ComposableLambdaKt.composableLambdaInstance(132290185, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda53
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_132290185$lambda$108((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<LazyItemScope, Composer, Integer, Unit> lambda$1912672512 = ComposableLambdaKt.composableLambdaInstance(1912672512, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda64
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_1912672512$lambda$109((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$454968775 = ComposableLambdaKt.composableLambdaInstance(454968775, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda75
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_454968775$lambda$111((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-159334980, reason: not valid java name */
    private static Function3<LazyItemScope, Composer, Integer, Unit> f154lambda$159334980 = ComposableLambdaKt.composableLambdaInstance(-159334980, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda86
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda__159334980$lambda$112((LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$953287915 = ComposableLambdaKt.composableLambdaInstance(953287915, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda97
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_953287915$lambda$113((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$762687205 = ComposableLambdaKt.composableLambdaInstance(762687205, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt$$ExternalSyntheticLambda108
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TeacherMainScreenKt.lambda_762687205$lambda$115((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1060403958$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6995getLambda$1060403958$app() {
        return f144lambda$1060403958;
    }

    /* JADX INFO: renamed from: getLambda$-1069143410$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6996getLambda$1069143410$app() {
        return f145lambda$1069143410;
    }

    /* JADX INFO: renamed from: getLambda$-1198171233$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6997getLambda$1198171233$app() {
        return f146lambda$1198171233;
    }

    /* JADX INFO: renamed from: getLambda$-121857915$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6998getLambda$121857915$app() {
        return f147lambda$121857915;
    }

    /* JADX INFO: renamed from: getLambda$-1359580626$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6999getLambda$1359580626$app() {
        return f148lambda$1359580626;
    }

    /* JADX INFO: renamed from: getLambda$-1386219395$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7000getLambda$1386219395$app() {
        return f149lambda$1386219395;
    }

    /* JADX INFO: renamed from: getLambda$-1439190952$app, reason: not valid java name */
    public final Function3<ColumnScope, Composer, Integer, Unit> m7001getLambda$1439190952$app() {
        return f150lambda$1439190952;
    }

    /* JADX INFO: renamed from: getLambda$-1504186743$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7002getLambda$1504186743$app() {
        return f151lambda$1504186743;
    }

    /* JADX INFO: renamed from: getLambda$-1566514028$app, reason: not valid java name */
    public final Function3<BoxScope, Composer, Integer, Unit> m7003getLambda$1566514028$app() {
        return f152lambda$1566514028;
    }

    /* JADX INFO: renamed from: getLambda$-1588158751$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7004getLambda$1588158751$app() {
        return f153lambda$1588158751;
    }

    /* JADX INFO: renamed from: getLambda$-159334980$app, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7005getLambda$159334980$app() {
        return f154lambda$159334980;
    }

    /* JADX INFO: renamed from: getLambda$-1613542046$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7006getLambda$1613542046$app() {
        return f155lambda$1613542046;
    }

    /* JADX INFO: renamed from: getLambda$-1617797238$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7007getLambda$1617797238$app() {
        return f156lambda$1617797238;
    }

    /* JADX INFO: renamed from: getLambda$-1636196782$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7008getLambda$1636196782$app() {
        return f157lambda$1636196782;
    }

    /* JADX INFO: renamed from: getLambda$-1651456237$app, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7009getLambda$1651456237$app() {
        return f158lambda$1651456237;
    }

    /* JADX INFO: renamed from: getLambda$-1685191925$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7010getLambda$1685191925$app() {
        return f159lambda$1685191925;
    }

    /* JADX INFO: renamed from: getLambda$-1693196316$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7011getLambda$1693196316$app() {
        return f160lambda$1693196316;
    }

    /* JADX INFO: renamed from: getLambda$-1703567177$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7012getLambda$1703567177$app() {
        return f161lambda$1703567177;
    }

    /* JADX INFO: renamed from: getLambda$-1731407733$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7013getLambda$1731407733$app() {
        return f162lambda$1731407733;
    }

    /* JADX INFO: renamed from: getLambda$-1767187826$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7014getLambda$1767187826$app() {
        return f163lambda$1767187826;
    }

    /* JADX INFO: renamed from: getLambda$-1832240809$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7015getLambda$1832240809$app() {
        return f164lambda$1832240809;
    }

    /* JADX INFO: renamed from: getLambda$-1845018228$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7016getLambda$1845018228$app() {
        return f165lambda$1845018228;
    }

    /* JADX INFO: renamed from: getLambda$-1916579245$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7017getLambda$1916579245$app() {
        return f166lambda$1916579245;
    }

    /* JADX INFO: renamed from: getLambda$-1957573008$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7018getLambda$1957573008$app() {
        return f167lambda$1957573008;
    }

    /* JADX INFO: renamed from: getLambda$-1958628723$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7019getLambda$1958628723$app() {
        return f168lambda$1958628723;
    }

    /* JADX INFO: renamed from: getLambda$-2058364506$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7020getLambda$2058364506$app() {
        return f169lambda$2058364506;
    }

    /* JADX INFO: renamed from: getLambda$-2068277426$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7021getLambda$2068277426$app() {
        return f170lambda$2068277426;
    }

    /* JADX INFO: renamed from: getLambda$-2091322151$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7022getLambda$2091322151$app() {
        return f171lambda$2091322151;
    }

    /* JADX INFO: renamed from: getLambda$-2125065894$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7023getLambda$2125065894$app() {
        return f172lambda$2125065894;
    }

    /* JADX INFO: renamed from: getLambda$-2134821772$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7024getLambda$2134821772$app() {
        return f173lambda$2134821772;
    }

    /* JADX INFO: renamed from: getLambda$-228093449$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7025getLambda$228093449$app() {
        return f174lambda$228093449;
    }

    /* JADX INFO: renamed from: getLambda$-235468410$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7026getLambda$235468410$app() {
        return f175lambda$235468410;
    }

    /* JADX INFO: renamed from: getLambda$-268738841$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7027getLambda$268738841$app() {
        return f176lambda$268738841;
    }

    /* JADX INFO: renamed from: getLambda$-29147658$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7028getLambda$29147658$app() {
        return f177lambda$29147658;
    }

    /* JADX INFO: renamed from: getLambda$-299533278$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7029getLambda$299533278$app() {
        return f178lambda$299533278;
    }

    /* JADX INFO: renamed from: getLambda$-316410896$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7030getLambda$316410896$app() {
        return f179lambda$316410896;
    }

    /* JADX INFO: renamed from: getLambda$-328131899$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7031getLambda$328131899$app() {
        return f180lambda$328131899;
    }

    /* JADX INFO: renamed from: getLambda$-349078905$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7032getLambda$349078905$app() {
        return f181lambda$349078905;
    }

    /* JADX INFO: renamed from: getLambda$-366745226$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7033getLambda$366745226$app() {
        return f182lambda$366745226;
    }

    /* JADX INFO: renamed from: getLambda$-462689400$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7034getLambda$462689400$app() {
        return f183lambda$462689400;
    }

    /* JADX INFO: renamed from: getLambda$-522647599$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7035getLambda$522647599$app() {
        return f184lambda$522647599;
    }

    /* JADX INFO: renamed from: getLambda$-537342190$app, reason: not valid java name */
    public final Function3<BoxScope, Composer, Integer, Unit> m7036getLambda$537342190$app() {
        return f185lambda$537342190;
    }

    /* JADX INFO: renamed from: getLambda$-542636399$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7037getLambda$542636399$app() {
        return f186lambda$542636399;
    }

    /* JADX INFO: renamed from: getLambda$-576299895$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7038getLambda$576299895$app() {
        return f187lambda$576299895;
    }

    /* JADX INFO: renamed from: getLambda$-613957805$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7039getLambda$613957805$app() {
        return f188lambda$613957805;
    }

    /* JADX INFO: renamed from: getLambda$-657531883$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7040getLambda$657531883$app() {
        return f189lambda$657531883;
    }

    /* JADX INFO: renamed from: getLambda$-674395339$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7041getLambda$674395339$app() {
        return f190lambda$674395339;
    }

    /* JADX INFO: renamed from: getLambda$-72996482$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7042getLambda$72996482$app() {
        return f191lambda$72996482;
    }

    /* JADX INFO: renamed from: getLambda$-787119891$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7043getLambda$787119891$app() {
        return f192lambda$787119891;
    }

    /* JADX INFO: renamed from: getLambda$-86691823$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7044getLambda$86691823$app() {
        return f193lambda$86691823;
    }

    /* JADX INFO: renamed from: getLambda$-885868135$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7045getLambda$885868135$app() {
        return f194lambda$885868135;
    }

    /* JADX INFO: renamed from: getLambda$-886105670$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7046getLambda$886105670$app() {
        return f195lambda$886105670;
    }

    /* JADX INFO: renamed from: getLambda$-950921249$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7047getLambda$950921249$app() {
        return f196lambda$950921249;
    }

    /* JADX INFO: renamed from: getLambda$-959102385$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7048getLambda$959102385$app() {
        return f197lambda$959102385;
    }

    /* JADX INFO: renamed from: getLambda$-96813194$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7049getLambda$96813194$app() {
        return f198lambda$96813194;
    }

    /* JADX INFO: renamed from: getLambda$-980476287$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7050getLambda$980476287$app() {
        return f199lambda$980476287;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$100719588$app() {
        return lambda$100719588;
    }

    public final Function2<Composer, Integer, Unit> getLambda$111662216$app() {
        return lambda$111662216;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1151097503$app() {
        return lambda$1151097503;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1155847617$app() {
        return lambda$1155847617;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1157811128$app() {
        return lambda$1157811128;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1165191449$app() {
        return lambda$1165191449;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1306499891$app() {
        return lambda$1306499891;
    }

    public final Function3<LazyItemScope, Composer, Integer, Unit> getLambda$132290185$app() {
        return lambda$132290185;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1329066166$app() {
        return lambda$1329066166;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1329082162$app() {
        return lambda$1329082162;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1466409852$app() {
        return lambda$1466409852;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1535510067$app() {
        return lambda$1535510067;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1579180946$app() {
        return lambda$1579180946;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1624586544$app() {
        return lambda$1624586544;
    }

    public final Function2<Composer, Integer, Unit> getLambda$162739855$app() {
        return lambda$162739855;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1650836337$app() {
        return lambda$1650836337;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1670491152$app() {
        return lambda$1670491152;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1683232623$app() {
        return lambda$1683232623;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1756799961$app() {
        return lambda$1756799961;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1787771021$app() {
        return lambda$1787771021;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1833075796$app() {
        return lambda$1833075796;
    }

    public final Function3<LazyItemScope, Composer, Integer, Unit> getLambda$1848334767$app() {
        return lambda$1848334767;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1867232835$app() {
        return lambda$1867232835;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1868796396$app() {
        return lambda$1868796396;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1871396593$app() {
        return lambda$1871396593;
    }

    public final Function3<LazyItemScope, Composer, Integer, Unit> getLambda$1912672512$app() {
        return lambda$1912672512;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1941838969$app() {
        return lambda$1941838969;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1959840420$app() {
        return lambda$1959840420;
    }

    public final Function2<Composer, Integer, Unit> getLambda$2071946895$app() {
        return lambda$2071946895;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$2105945645$app() {
        return lambda$2105945645;
    }

    public final Function2<Composer, Integer, Unit> getLambda$2144515326$app() {
        return lambda$2144515326;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$214763092$app() {
        return lambda$214763092;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$264645501$app() {
        return lambda$264645501;
    }

    public final Function2<Composer, Integer, Unit> getLambda$283143038$app() {
        return lambda$283143038;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$288578178$app() {
        return lambda$288578178;
    }

    public final Function2<Composer, Integer, Unit> getLambda$321970984$app() {
        return lambda$321970984;
    }

    public final Function2<Composer, Integer, Unit> getLambda$433656494$app() {
        return lambda$433656494;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$454968775$app() {
        return lambda$454968775;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$47658032$app() {
        return lambda$47658032;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$552160650$app() {
        return lambda$552160650;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$568822002$app() {
        return lambda$568822002;
    }

    public final Function2<Composer, Integer, Unit> getLambda$569447549$app() {
        return lambda$569447549;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$615595922$app() {
        return lambda$615595922;
    }

    public final Function2<Composer, Integer, Unit> getLambda$667338170$app() {
        return lambda$667338170;
    }

    public final Function2<Composer, Integer, Unit> getLambda$706516469$app() {
        return lambda$706516469;
    }

    public final Function2<Composer, Integer, Unit> getLambda$752175982$app() {
        return lambda$752175982;
    }

    public final Function2<Composer, Integer, Unit> getLambda$762687205$app() {
        return lambda$762687205;
    }

    public final Function2<Composer, Integer, Unit> getLambda$907471001$app() {
        return lambda$907471001;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$925980346$app() {
        return lambda$925980346;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$941119442$app() {
        return lambda$941119442;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$953287915$app() {
        return lambda$953287915;
    }

    public final Function2<Composer, Integer, Unit> getLambda$956985505$app() {
        return lambda$956985505;
    }

    public final Function2<Composer, Integer, Unit> getLambda$958502390$app() {
        return lambda$958502390;
    }

    static final Unit lambda__1636196782$lambda$1(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C473@23350L53:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1636196782, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1636196782.<anonymous> (TeacherMainScreen.kt:473)");
            }
            IconKt.m2150Iconww6aTOc(SchoolKt.getSchool(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2068277426$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C474@23437L17:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2068277426, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-2068277426.<anonymous> (TeacherMainScreen.kt:474)");
            }
            TextKt.m2693Text4IGK_g("Classroom", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1504186743$lambda$3(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C493@24373L53:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1504186743, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1504186743.<anonymous> (TeacherMainScreen.kt:493)");
            }
            IconKt.m2150Iconww6aTOc(PersonKt.getPerson(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__121857915$lambda$2(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C494@24460L15:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-121857915, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-121857915.<anonymous> (TeacherMainScreen.kt:494)");
            }
            TextKt.m2693Text4IGK_g("Profile", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1617797238$lambda$5(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C513@25390L55:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1617797238, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1617797238.<anonymous> (TeacherMainScreen.kt:513)");
            }
            IconKt.m2150Iconww6aTOc(DownloadKt.getDownload(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__235468410$lambda$4(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C514@25479L21:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-235468410, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-235468410.<anonymous> (TeacherMainScreen.kt:514)");
            }
            TextKt.m2693Text4IGK_g("Export Center", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1731407733$lambda$7(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C534@26458L55:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1731407733, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1731407733.<anonymous> (TeacherMainScreen.kt:534)");
            }
            IconKt.m2150Iconww6aTOc(SettingsKt.getSettings(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__349078905$lambda$6(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C535@26547L16:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-349078905, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-349078905.<anonymous> (TeacherMainScreen.kt:535)");
            }
            TextKt.m2693Text4IGK_g("Settings", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1845018228$lambda$9(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C554@27480L51:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1845018228, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1845018228.<anonymous> (TeacherMainScreen.kt:554)");
            }
            IconKt.m2150Iconww6aTOc(InfoKt.getInfo(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__462689400$lambda$8(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C555@27565L13:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-462689400, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-462689400.<anonymous> (TeacherMainScreen.kt:555)");
            }
            TextKt.m2693Text4IGK_g("About", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1958628723$lambda$11(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C582@28834L11,579@28640L237:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1958628723, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1958628723.<anonymous> (TeacherMainScreen.kt:579)");
            }
            IconKt.m2150Iconww6aTOc(ExitToAppKt.getExitToApp(Icons.AutoMirrored.Filled.INSTANCE), "Log Out", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), $composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__576299895$lambda$10(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C589@29115L11,586@28955L203:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-576299895, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-576299895.<anonymous> (TeacherMainScreen.kt:586)");
            }
            TextKt.m2693Text4IGK_g("Log Out", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131034);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1756799961$lambda$12(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C706@35740L202:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1756799961, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1756799961.<anonymous> (TeacherMainScreen.kt:706)");
            }
            IconKt.m2150Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1670491152$lambda$13(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C717@36381L202:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1670491152, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1670491152.<anonymous> (TeacherMainScreen.kt:717)");
            }
            IconKt.m2150Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__522647599$lambda$14(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C737@37638L202:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-522647599, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-522647599.<anonymous> (TeacherMainScreen.kt:737)");
            }
            IconKt.m2150Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1579180946$lambda$15(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C748@38293L214:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1579180946, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1579180946.<anonymous> (TeacherMainScreen.kt:748)");
            }
            IconKt.m2150Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back to Students", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__613957805$lambda$16(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C759@38971L211:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-613957805, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-613957805.<anonymous> (TeacherMainScreen.kt:759)");
            }
            IconKt.m2150Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back to Tasks", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1957573008$lambda$17(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C767@39417L60:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1957573008, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1957573008.<anonymous> (TeacherMainScreen.kt:767)");
            }
            IconKt.m2150Iconww6aTOc(MenuKt.getMenu(Icons.INSTANCE.getDefault()), "Open Drawer", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__328131899$lambda$18(RowScope TopAppBar, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TopAppBar, "$this$TopAppBar");
        ComposerKt.sourceInformation($composer, "C:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-328131899, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-328131899.<anonymous> (TeacherMainScreen.kt:772)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__29147658$lambda$27(RowScope ExtendedFloatingActionButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ExtendedFloatingActionButton, "$this$ExtendedFloatingActionButton");
        ComposerKt.sourceInformation($composer, "C795@40885L20:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-29147658, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-29147658.<anonymous> (TeacherMainScreen.kt:795)");
            }
            TextKt.m2693Text4IGK_g("Export Tasks", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_706516469$lambda$28(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C804@41480L59:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(706516469, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$706516469.<anonymous> (TeacherMainScreen.kt:804)");
            }
            IconKt.m2150Iconww6aTOc(AddKt.getAdd(Icons.INSTANCE.getDefault()), "Create Task", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1198171233$lambda$29(RowScope ExtendedFloatingActionButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ExtendedFloatingActionButton, "$this$ExtendedFloatingActionButton");
        ComposerKt.sourceInformation($composer, "C822@42613L15:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1198171233, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1198171233.<anonymous> (TeacherMainScreen.kt:822)");
            }
            TextKt.m2693Text4IGK_g("History", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1959840420$lambda$30(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C843@43915L198:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1959840420, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1959840420.<anonymous> (TeacherMainScreen.kt:843)");
            }
            IconKt.m2150Iconww6aTOc(GroupAddKt.getGroupAdd(Icons.INSTANCE.getDefault()), "Join Requests", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_956985505$lambda$19(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C858@44479L53:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(956985505, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$956985505.<anonymous> (TeacherMainScreen.kt:858)");
            }
            IconKt.m2150Iconww6aTOc(SchoolKt.getSchool(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2144515326$lambda$20(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C859@44570L17:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2144515326, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$2144515326.<anonymous> (TeacherMainScreen.kt:859)");
            }
            TextKt.m2693Text4IGK_g("Classroom", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__537342190$lambda$21(BoxScope BadgedBox, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(BadgedBox, "$this$BadgedBox");
        ComposerKt.sourceInformation($composer, "C883@45822L52:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-537342190, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-537342190.<anonymous> (TeacherMainScreen.kt:883)");
            }
            IconKt.m2150Iconww6aTOc(GroupKt.getGroup(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__674395339$lambda$22(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C886@45966L16:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-674395339, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-674395339.<anonymous> (TeacherMainScreen.kt:886)");
            }
            TextKt.m2693Text4IGK_g("Students", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2091322151$lambda$23(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C902@46833L55:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2091322151, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-2091322151.<anonymous> (TeacherMainScreen.kt:902)");
            }
            IconKt.m2150Iconww6aTOc(MenuBookKt.getMenuBook(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_958502390$lambda$24(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C903@46926L16:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(958502390, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$958502390.<anonymous> (TeacherMainScreen.kt:903)");
            }
            TextKt.m2693Text4IGK_g("Subjects", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1566514028$lambda$25(BoxScope BadgedBox, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(BadgedBox, "$this$BadgedBox");
        ComposerKt.sourceInformation($composer, "C927@48152L57:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1566514028, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1566514028.<anonymous> (TeacherMainScreen.kt:927)");
            }
            IconKt.m2150Iconww6aTOc(AssignmentKt.getAssignment(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1703567177$lambda$26(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C930@48301L13:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1703567177, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1703567177.<anonymous> (TeacherMainScreen.kt:930)");
            }
            TextKt.m2693Text4IGK_g("Tasks", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1916579245$lambda$32(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1032@54334L1026:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1916579245, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1916579245.<anonymous> (TeacherMainScreen.kt:1032)");
            }
            Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6622constructorimpl(32));
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
            ComposerKt.sourceInformationMarkerStart($composer, -1811413661, "C1039@54733L10,1039@54812L11,1039@54691L141,1040@54861L41,1044@55200L11,1045@55284L10,1041@54931L403:TeacherMainScreen.kt#2thlc2");
            TextKt.m2693Text4IGK_g("Coming Soon", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getHeadlineMedium(), $composer, 196614, 0, 65498);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(16)), $composer, 6);
            TextKt.m2693Text4IGK_g("Calis is currently under development. Thank you for testing this app out!", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyLarge(), $composer, 6, 0, 65018);
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

    static final Unit lambda__1832240809$lambda$33(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1114@59062L13:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1832240809, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1832240809.<anonymous> (TeacherMainScreen.kt:1114)");
            }
            AboutScreenKt.AboutScreen(null, $composer, 0, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__268738841$lambda$37(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1356@69718L1161:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-268738841, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-268738841.<anonymous> (TeacherMainScreen.kt:1356)");
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
            ComposerKt.sourceInformationMarkerStart($composer, 1763971084, "C1361@70132L11,1361@70075L119,1362@70239L40,1365@70562L10,1366@70654L11,1363@70324L513:TeacherMainScreen.kt#2thlc2");
            IconKt.m2150Iconww6aTOc(BluetoothKt.getBluetooth(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(28)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), $composer, 432, 0);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(8)), $composer, 6);
            TextKt.m2693Text4IGK_g("Searching for unregistered Calis Devices near you... Make sure hardware is powered and in Factory Mode.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 65018);
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

    static final Unit lambda_1151097503$lambda$38(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1404@73527L64:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1151097503, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1151097503.<anonymous> (TeacherMainScreen.kt:1404)");
            }
            TextKt.m2693Text4IGK_g("Register", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1155847617$lambda$34(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1673@89433L12:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1155847617, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1155847617.<anonymous> (TeacherMainScreen.kt:1673)");
            }
            TextKt.m2693Text4IGK_g("Done", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1685191925$lambda$35(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C1682@89748L14:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1685191925, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1685191925.<anonymous> (TeacherMainScreen.kt:1682)");
            }
            TextKt.m2693Text4IGK_g("Cancel", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1787771021$lambda$41(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1882@98098L62:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1787771021, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1787771021.<anonymous> (TeacherMainScreen.kt:1882)");
            }
            TextKt.m2693Text4IGK_g("Unregister Calis Device?", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1069143410$lambda$42(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1883@98185L83:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1069143410, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1069143410.<anonymous> (TeacherMainScreen.kt:1883)");
            }
            TextKt.m2693Text4IGK_g("Are you sure you want to unregister this Calis Device back to Factory Mode?", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1941838969$lambda$39(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1892@98606L48:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1941838969, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1941838969.<anonymous> (TeacherMainScreen.kt:1892)");
            }
            TextKt.m2693Text4IGK_g("Unregister", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_264645501$lambda$40(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C1897@98817L14:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(264645501, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$264645501.<anonymous> (TeacherMainScreen.kt:1897)");
            }
            TextKt.m2693Text4IGK_g("Cancel", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1329066166$lambda$43(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1959@101889L66:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1329066166, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1329066166.<anonymous> (TeacherMainScreen.kt:1959)");
            }
            TextKt.m2693Text4IGK_g("Grant", (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1535510067$lambda$44(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C2025@105968L66:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1535510067, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1535510067.<anonymous> (TeacherMainScreen.kt:2025)");
            }
            TextKt.m2693Text4IGK_g("Allow", (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__542636399$lambda$45(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C2056@107911L67:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-542636399, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-542636399.<anonymous> (TeacherMainScreen.kt:2056)");
            }
            TextKt.m2693Text4IGK_g("Enable", (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__657531883$lambda$49(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2116@111042L670:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-657531883, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-657531883.<anonymous> (TeacherMainScreen.kt:2116)");
            }
            SurfaceKt.m2543SurfaceT9BRK9s(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(44)), RoundedCornerShapeKt.getCircleShape(), ColorKt.Color(4294963698L), 0L, 0.0f, 0.0f, null, f179lambda$316410896, $composer, 12583302, 120);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x016c  */
    static final Unit lambda__316410896$lambda$48(Composer $composer, int $changed) {
        int i;
        ComposerKt.sourceInformation($composer, "C2121@111271L415:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-316410896, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-316410896.<anonymous> (TeacherMainScreen.kt:2121)");
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
                ComposerKt.sourceInformationMarkerStart($composer, -233239325, "C2122@111346L310:TeacherMainScreen.kt#2thlc2");
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
            ComposerKt.sourceInformationMarkerStart($composer, -233239325, "C2122@111346L310:TeacherMainScreen.kt#2thlc2");
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

    static final Unit lambda_288578178$lambda$46(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C2163@113364L44:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(288578178, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$288578178.<anonymous> (TeacherMainScreen.kt:2163)");
            }
            TextKt.m2693Text4IGK_g("Got it", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_214763092$lambda$50(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C2723@148790L114:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(214763092, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$214763092.<anonymous> (TeacherMainScreen.kt:2723)");
            }
            TextKt.m2693Text4IGK_g("Unregister", (Modifier) null, 0L, TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6539getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 3120, 120790);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__885868135$lambda$51(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C2736@149679L84,2737@149800L39,2738@149876L116:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-885868135, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-885868135.<anonymous> (TeacherMainScreen.kt:2736)");
            }
            IconKt.m2150Iconww6aTOc(StopKt.getStop(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(18)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.m720width3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(6)), $composer, 6);
            TextKt.m2693Text4IGK_g("Stop Session", (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6539getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 3120, 120790);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_568822002$lambda$52(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C2752@150845L89,2753@150971L39,2754@151047L117:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(568822002, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$568822002.<anonymous> (TeacherMainScreen.kt:2752)");
            }
            IconKt.m2150Iconww6aTOc(PlayArrowKt.getPlayArrow(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(18)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.m720width3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(6)), $composer, 6);
            TextKt.m2693Text4IGK_g("Start Session", (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6539getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 3120, 120790);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1867232835$lambda$53(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2878@156981L84:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1867232835, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1867232835.<anonymous> (TeacherMainScreen.kt:2878)");
            }
            IconKt.m2150Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), "Approve", (Modifier) null, com.example.ui.theme.ColorKt.getStatusPresentGreen(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__886105670$lambda$54(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2884@157371L80:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-886105670, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-886105670.<anonymous> (TeacherMainScreen.kt:2884)");
            }
            IconKt.m2150Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Reject", (Modifier) null, com.example.ui.theme.ColorKt.getStatusAbsentRed(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__787119891$lambda$57(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2904@158238L23:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-787119891, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-787119891.<anonymous> (TeacherMainScreen.kt:2904)");
            }
            TextKt.m2693Text4IGK_g("Student Profile", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__980476287$lambda$55(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C2920@158985L15:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-980476287, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-980476287.<anonymous> (TeacherMainScreen.kt:2920)");
            }
            TextKt.m2693Text4IGK_g("Approve", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_941119442$lambda$56(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C2928@159226L39:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(941119442, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$941119442.<anonymous> (TeacherMainScreen.kt:2928)");
            }
            TextKt.m2693Text4IGK_g("Reject", (Modifier) null, com.example.ui.theme.ColorKt.getStatusAbsentRed(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 390, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_552160650$lambda$58(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C3044@164719L33:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(552160650, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$552160650.<anonymous> (TeacherMainScreen.kt:3044)");
            }
            TextKt.m2693Text4IGK_g("Present", (Modifier) null, 0L, TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1306499891$lambda$59(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C3050@165058L30:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1306499891, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1306499891.<anonymous> (TeacherMainScreen.kt:3050)");
            }
            TextKt.m2693Text4IGK_g("Late", (Modifier) null, 0L, TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_615595922$lambda$60(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C3055@165323L32:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(615595922, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$615595922.<anonymous> (TeacherMainScreen.kt:3055)");
            }
            TextKt.m2693Text4IGK_g("Absent", (Modifier) null, 0L, TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1693196316$lambda$61(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C3368@182105L69:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1693196316, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1693196316.<anonymous> (TeacherMainScreen.kt:3368)");
            }
            TextKt.m2693Text4IGK_g("Create a Task", (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_162739855$lambda$62(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C3456@187752L233:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(162739855, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$162739855.<anonymous> (TeacherMainScreen.kt:3456)");
            }
            IconKt.m2150Iconww6aTOc(KeyboardArrowRightKt.getKeyboardArrowRight(Icons.AutoMirrored.Filled.INSTANCE), "View Submissions", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1651456237$lambda$63(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C3486@189260L41:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1651456237, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1651456237.<anonymous> (TeacherMainScreen.kt:3486)");
            }
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(80)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1588158751$lambda$64(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C3687@198819L36:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1588158751, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1588158751.<anonymous> (TeacherMainScreen.kt:3687)");
            }
            TextKt.m2693Text4IGK_g("Select All", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1060403958$lambda$65(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C3693@199164L31:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1060403958, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1060403958.<anonymous> (TeacherMainScreen.kt:3693)");
            }
            TextKt.m2693Text4IGK_g("Clear", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__366745226$lambda$66(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C3826@206953L21:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-366745226, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-366745226.<anonymous> (TeacherMainScreen.kt:3826)");
            }
            TextKt.m2693Text4IGK_g("Present Label", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__950921249$lambda$67(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C3827@207294L20:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-950921249, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-950921249.<anonymous> (TeacherMainScreen.kt:3827)");
            }
            TextKt.m2693Text4IGK_g("Absent Label", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_283143038$lambda$68(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C3831@207713L18:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(283143038, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$283143038.<anonymous> (TeacherMainScreen.kt:3831)");
            }
            TextKt.m2693Text4IGK_g("Late Label", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1359580626$lambda$69(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C3864@209509L61:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1359580626, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1359580626.<anonymous> (TeacherMainScreen.kt:3864)");
            }
            TextKt.m2693Text4IGK_g("Reset", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1833075796$lambda$70(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C4118@225074L36:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1833075796, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1833075796.<anonymous> (TeacherMainScreen.kt:4118)");
            }
            TextKt.m2693Text4IGK_g("Select All", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1386219395$lambda$71(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C4124@225444L31:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1386219395, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1386219395.<anonymous> (TeacherMainScreen.kt:4124)");
            }
            TextKt.m2693Text4IGK_g("Clear", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1868796396$lambda$73(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4151@226609L1537:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1868796396, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1868796396.<anonymous> (TeacherMainScreen.kt:4151)");
            }
            Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6622constructorimpl(24));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM670padding3ABfNKs);
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
            ComposerKt.sourceInformationMarkerStart($composer, 185204082, "C4160@227159L11,4157@226945L344,4163@227326L40,4166@227550L10,4168@227709L11,4164@227403L372,4172@227971L10,4173@228055L11,4170@227812L300:TeacherMainScreen.kt#2thlc2");
            IconKt.m2150Iconww6aTOc(AssignmentKt.getAssignment(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(36)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutline(), $composer, 432, 0);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(8)), $composer, 6);
            TextKt.m2693Text4IGK_g("No tasks available to export", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 196614, 0, 65498);
            TextKt.m2693Text4IGK_g("Create tasks in the Classroom tab first.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutline(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 65530);
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

    static final Unit lambda_907471001$lambda$76(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4307@235460L39:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(907471001, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$907471001.<anonymous> (TeacherMainScreen.kt:4307)");
            }
            TextKt.m2693Text4IGK_g("Register Calis Device Classroom", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_321970984$lambda$77(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4316@235900L33:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(321970984, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$321970984.<anonymous> (TeacherMainScreen.kt:4316)");
            }
            TextKt.m2693Text4IGK_g("Classroom / Section (CLS)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__86691823$lambda$78(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4324@236310L32:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-86691823, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-86691823.<anonymous> (TeacherMainScreen.kt:4324)");
            }
            TextKt.m2693Text4IGK_g("Days (DAYS e.g. MON-FRI)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1624586544$lambda$79(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4331@236624L31:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1624586544, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1624586544.<anonymous> (TeacherMainScreen.kt:4331)");
            }
            TextKt.m2693Text4IGK_g("Start Time (START HHMM)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__959102385$lambda$80(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4338@236937L33:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-959102385, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-959102385.<anonymous> (TeacherMainScreen.kt:4338)");
            }
            TextKt.m2693Text4IGK_g("Grace Period (GRACE HHMM)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_752175982$lambda$81(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4345@237248L33:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(752175982, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$752175982.<anonymous> (TeacherMainScreen.kt:4345)");
            }
            TextKt.m2693Text4IGK_g("Dismissal Time (END HHMM)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2105945645$lambda$74(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C4356@237660L22:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2105945645, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$2105945645.<anonymous> (TeacherMainScreen.kt:4356)");
            }
            TextKt.m2693Text4IGK_g("Register (REG)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__72996482$lambda$75(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C4360@237780L14:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-72996482, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-72996482.<anonymous> (TeacherMainScreen.kt:4360)");
            }
            TextKt.m2693Text4IGK_g("Cancel", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__96813194$lambda$84(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4444@240952L11,4444@240880L94:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-96813194, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-96813194.<anonymous> (TeacherMainScreen.kt:4444)");
            }
            TextKt.m2693Text4IGK_g("Create Task", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131034);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_569447549$lambda$85(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4457@241523L18:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(569447549, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$569447549.<anonymous> (TeacherMainScreen.kt:4457)");
            }
            TextKt.m2693Text4IGK_g("Task Title", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2134821772$lambda$86(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4507@244115L24:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2134821772, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-2134821772.<anonymous> (TeacherMainScreen.kt:4507)");
            }
            TextKt.m2693Text4IGK_g("Task Description", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__299533278$lambda$87(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C4647@251399L32:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-299533278, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-299533278.<anonymous> (TeacherMainScreen.kt:4647)");
            }
            TextKt.m2693Text4IGK_g("Maximum Score (e.g. 100)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1613542046$lambda$82(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C4686@253390L19:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1613542046, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1613542046.<anonymous> (TeacherMainScreen.kt:4686)");
            }
            TextKt.m2693Text4IGK_g("Create Task", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1650836337$lambda$83(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C4690@253507L14:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1650836337, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1650836337.<anonymous> (TeacherMainScreen.kt:4690)");
            }
            TextKt.m2693Text4IGK_g("Cancel", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1466409852$lambda$88(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C4735@255069L40:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1466409852, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1466409852.<anonymous> (TeacherMainScreen.kt:4735)");
            }
            TextKt.m2693Text4IGK_g("OK", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_925980346$lambda$89(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C4740@255223L14:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(925980346, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$925980346.<anonymous> (TeacherMainScreen.kt:4740)");
            }
            TextKt.m2693Text4IGK_g("Cancel", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2058364506$lambda$90(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C4796@257439L40:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2058364506, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-2058364506.<anonymous> (TeacherMainScreen.kt:4796)");
            }
            TextKt.m2693Text4IGK_g("OK", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 196614, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_100719588$lambda$91(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C4801@257593L14:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(100719588, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$100719588.<anonymous> (TeacherMainScreen.kt:4801)");
            }
            TextKt.m2693Text4IGK_g("Cancel", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1767187826$lambda$92(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C5068@269597L32:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1767187826, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1767187826.<anonymous> (TeacherMainScreen.kt:5068)");
            }
            TextKt.m2693Text4IGK_g("Search name, SID, LRN...", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2071946895$lambda$93(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C5069@269665L57:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2071946895, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$2071946895.<anonymous> (TeacherMainScreen.kt:5069)");
            }
            IconKt.m2150Iconww6aTOc(SearchKt.getSearch(Icons.INSTANCE.getDefault()), "Search", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_111662216$lambda$94(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C5073@269908L62:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(111662216, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$111662216.<anonymous> (TeacherMainScreen.kt:5073)");
            }
            IconKt.m2150Iconww6aTOc(ClearKt.getClear(Icons.INSTANCE.getDefault()), "Clear search", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_667338170$lambda$95(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C5313@283035L15:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(667338170, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$667338170.<anonymous> (TeacherMainScreen.kt:5313)");
            }
            TextKt.m2693Text4IGK_g("Present", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1871396593$lambda$96(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C5320@283512L12:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1871396593, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1871396593.<anonymous> (TeacherMainScreen.kt:5320)");
            }
            TextKt.m2693Text4IGK_g("Late", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1329082162$lambda$97(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C5327@283983L14:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1329082162, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1329082162.<anonymous> (TeacherMainScreen.kt:5327)");
            }
            TextKt.m2693Text4IGK_g("Absent", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1848334767$lambda$98(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C5339@284521L41:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1848334767, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1848334767.<anonymous> (TeacherMainScreen.kt:5339)");
            }
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(80)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2125065894$lambda$99(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C5508@291140L11,5505@290959L218:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2125065894, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-2125065894.<anonymous> (TeacherMainScreen.kt:5505)");
            }
            IconKt.m2150Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back to Masterlist", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_47658032$lambda$100(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C5771@303689L33:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(47658032, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$47658032.<anonymous> (TeacherMainScreen.kt:5771)");
            }
            TextKt.m2693Text4IGK_g("Present", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1165191449$lambda$101(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C5784@304419L30:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1165191449, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1165191449.<anonymous> (TeacherMainScreen.kt:5784)");
            }
            TextKt.m2693Text4IGK_g("Late", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1157811128$lambda$102(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C5797@305154L32:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1157811128, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1157811128.<anonymous> (TeacherMainScreen.kt:5797)");
            }
            TextKt.m2693Text4IGK_g("Absent", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1439190952$lambda$104(ColumnScope Card, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C5819@305947L538:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1439190952, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-1439190952.<anonymous> (TeacherMainScreen.kt:5819)");
            }
            Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6622constructorimpl(24));
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM670padding3ABfNKs);
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
            ComposerKt.sourceInformationMarkerStart($composer, 1488558755, "C5827@306337L10,5828@306409L11,5825@306193L270:TeacherMainScreen.kt#2thlc2");
            TextKt.m2693Text4IGK_g("No attendance records found for this student yet.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 65530);
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

    static final Unit lambda__228093449$lambda$105(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C6235@324780L15:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-228093449, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-228093449.<anonymous> (TeacherMainScreen.kt:6235)");
            }
            TextKt.m2693Text4IGK_g("Present", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_433656494$lambda$106(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C6242@325236L12:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(433656494, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$433656494.<anonymous> (TeacherMainScreen.kt:6242)");
            }
            TextKt.m2693Text4IGK_g("Late", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1683232623$lambda$107(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C6249@325686L14:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1683232623, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1683232623.<anonymous> (TeacherMainScreen.kt:6249)");
            }
            TextKt.m2693Text4IGK_g("Absent", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_132290185$lambda$108(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C6261@326183L41:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(132290185, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$132290185.<anonymous> (TeacherMainScreen.kt:6261)");
            }
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(80)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1912672512$lambda$109(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C6333@330121L41:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1912672512, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$1912672512.<anonymous> (TeacherMainScreen.kt:6333)");
            }
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(80)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_454968775$lambda$111(ColumnScope Card, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C6348@330716L1517:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(454968775, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$454968775.<anonymous> (TeacherMainScreen.kt:6348)");
            }
            Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6622constructorimpl(32));
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
            ComposerKt.sourceInformationMarkerStart($composer, -1622663131, "C6359@331282L11,6355@331046L309,6361@331380L41,6364@331571L10,6366@331703L11,6362@331446L304,6368@331775L40,6371@331996L10,6372@332068L11,6369@331840L371:TeacherMainScreen.kt#2thlc2");
            ImageVector history = HistoryKt.getHistory(Icons.INSTANCE.getDefault());
            Modifier modifierM715size3ABfNKs = SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(48));
            long onSurfaceVariant = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant();
            IconKt.m2150Iconww6aTOc(history, (String) null, modifierM715size3ABfNKs, Color.m4157copywmQWz5c(onSurfaceVariant, (14 & 1) != 0 ? Color.m4161getAlphaimpl(onSurfaceVariant) : 0.6f, (14 & 2) != 0 ? Color.m4165getRedimpl(onSurfaceVariant) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(onSurfaceVariant) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(onSurfaceVariant) : 0.0f), $composer, 432, 0);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(12)), $composer, 6);
            TextKt.m2693Text4IGK_g("No Previous Attendance Records", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 196614, 0, 65498);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(4)), $composer, 6);
            TextKt.m2693Text4IGK_g("Attendance logs for past completed sessions will appear here.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 65018);
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

    static final Unit lambda__159334980$lambda$112(LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C6447@336328L41:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-159334980, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$-159334980.<anonymous> (TeacherMainScreen.kt:6447)");
            }
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(80)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_953287915$lambda$113(RowScope ExtendedFloatingActionButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ExtendedFloatingActionButton, "$this$ExtendedFloatingActionButton");
        ComposerKt.sourceInformation($composer, "C6465@337010L25:TeacherMainScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(953287915, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$953287915.<anonymous> (TeacherMainScreen.kt:6465)");
            }
            TextKt.m2693Text4IGK_g("Export Attendance", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0173  */
    static final Unit lambda_762687205$lambda$115(Composer $composer, int $changed) {
        int i;
        ComposerKt.sourceInformation($composer, "C7326@375744L371:TeacherMainScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(762687205, $changed, -1, "com.example.ui.screens.ComposableSingletons$TeacherMainScreenKt.lambda$762687205.<anonymous> (TeacherMainScreen.kt:7326)");
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
                ComposerKt.sourceInformationMarkerStart($composer, 837513246, "C7330@375985L11,7327@375811L282:TeacherMainScreen.kt#2thlc2");
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
            ComposerKt.sourceInformationMarkerStart($composer, 837513246, "C7330@375985L11,7327@375811L282:TeacherMainScreen.kt#2thlc2");
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
