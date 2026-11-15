package com.example.ui.components;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.KeyboardArrowDownKt;
import androidx.compose.material.icons.filled.SendKt;
import androidx.compose.material.icons.filled.ShareKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
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
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DebugConsole.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$DebugConsoleKt {
    public static final ComposableSingletons$DebugConsoleKt INSTANCE = new ComposableSingletons$DebugConsoleKt();

    /* JADX INFO: renamed from: lambda$-446005588, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f88lambda$446005588 = ComposableLambdaKt.composableLambdaInstance(-446005588, false, new Function2() { // from class: com.example.ui.components.ComposableSingletons$DebugConsoleKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$DebugConsoleKt.lambda__446005588$lambda$1((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1816108031, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f87lambda$1816108031 = ComposableLambdaKt.composableLambdaInstance(-1816108031, false, new Function2() { // from class: com.example.ui.components.ComposableSingletons$DebugConsoleKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$DebugConsoleKt.lambda__1816108031$lambda$2((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-526565267, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f90lambda$526565267 = ComposableLambdaKt.composableLambdaInstance(-526565267, false, new Function2() { // from class: com.example.ui.components.ComposableSingletons$DebugConsoleKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$DebugConsoleKt.lambda__526565267$lambda$3((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1956307401 = ComposableLambdaKt.composableLambdaInstance(1956307401, false, new Function2() { // from class: com.example.ui.components.ComposableSingletons$DebugConsoleKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$DebugConsoleKt.lambda_1956307401$lambda$4((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-457536590, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f89lambda$457536590 = ComposableLambdaKt.composableLambdaInstance(-457536590, false, new Function2() { // from class: com.example.ui.components.ComposableSingletons$DebugConsoleKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$DebugConsoleKt.lambda__457536590$lambda$5((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1816108031$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6933getLambda$1816108031$app() {
        return f87lambda$1816108031;
    }

    /* JADX INFO: renamed from: getLambda$-446005588$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6934getLambda$446005588$app() {
        return f88lambda$446005588;
    }

    /* JADX INFO: renamed from: getLambda$-457536590$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6935getLambda$457536590$app() {
        return f89lambda$457536590;
    }

    /* JADX INFO: renamed from: getLambda$-526565267$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6936getLambda$526565267$app() {
        return f90lambda$526565267;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1956307401$app() {
        return lambda$1956307401;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0174  */
    static final Unit lambda__446005588$lambda$1(Composer $composer, int $changed) {
        int i;
        ComposerKt.sourceInformation($composer, "C137@5636L122:DebugConsole.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-446005588, $changed, -1, "com.example.ui.components.ComposableSingletons$DebugConsoleKt.lambda$-446005588.<anonymous> (DebugConsole.kt:137)");
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
                ComposerKt.sourceInformationMarkerStart($composer, 227990840, "C138@5725L10,138@5691L57:DebugConsole.kt#qonjpd");
                TextKt.m2693Text4IGK_g("DBG", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 6, 0, 65534);
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
            ComposerKt.sourceInformationMarkerStart($composer, 227990840, "C138@5725L10,138@5691L57:DebugConsole.kt#qonjpd");
            TextKt.m2693Text4IGK_g("DBG", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 6, 0, 65534);
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

    static final Unit lambda__1816108031$lambda$2(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C208@8064L73:DebugConsole.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1816108031, $changed, -1, "com.example.ui.components.ComposableSingletons$DebugConsoleKt.lambda$-1816108031.<anonymous> (DebugConsole.kt:208)");
            }
            IconKt.m2150Iconww6aTOc(KeyboardArrowDownKt.getKeyboardArrowDown(Icons.INSTANCE.getDefault()), "Minimize", (Modifier) null, Color.INSTANCE.m4191getLightGray0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__526565267$lambda$3(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C306@12657L62:DebugConsole.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-526565267, $changed, -1, "com.example.ui.components.ComposableSingletons$DebugConsoleKt.lambda$-526565267.<anonymous> (DebugConsole.kt:306)");
            }
            TextKt.m2693Text4IGK_g("Custom TX packet", (Modifier) null, Color.INSTANCE.m4189getGray0d7_KjU(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3462, 0, 131058);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1956307401$lambda$4(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C329@13852L85:DebugConsole.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1956307401, $changed, -1, "com.example.ui.components.ComposableSingletons$DebugConsoleKt.lambda$1956307401.<anonymous> (DebugConsole.kt:329)");
            }
            IconKt.m2150Iconww6aTOc(SendKt.getSend(Icons.INSTANCE.getDefault()), "Send", SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(18)), Color.INSTANCE.m4196getWhite0d7_KjU(), $composer, 3504, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__457536590$lambda$5(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C338@14298L91:DebugConsole.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-457536590, $changed, -1, "com.example.ui.components.ComposableSingletons$DebugConsoleKt.lambda$-457536590.<anonymous> (DebugConsole.kt:338)");
            }
            IconKt.m2150Iconww6aTOc(ShareKt.getShare(Icons.INSTANCE.getDefault()), "Save Logs", SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(18)), Color.INSTANCE.m4196getWhite0d7_KjU(), $composer, 3504, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
