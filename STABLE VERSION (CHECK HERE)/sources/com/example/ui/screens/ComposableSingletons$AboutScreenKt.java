package com.example.ui.screens;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ImageKt;
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
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.AutoAwesomeKt;
import androidx.compose.material.icons.filled.CodeKt;
import androidx.compose.material.icons.filled.DevicesKt;
import androidx.compose.material.icons.filled.PersonKt;
import androidx.compose.material.icons.filled.VerifiedKt;
import androidx.compose.material3.DividerKt;
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
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.R;
import com.example.ui.theme.ColorKt;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AboutScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$AboutScreenKt {
    public static final ComposableSingletons$AboutScreenKt INSTANCE = new ComposableSingletons$AboutScreenKt();

    /* JADX INFO: renamed from: lambda$-1677932106, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f95lambda$1677932106 = ComposableLambdaKt.composableLambdaInstance(-1677932106, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$AboutScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AboutScreenKt.lambda__1677932106$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$665150509 = ComposableLambdaKt.composableLambdaInstance(665150509, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$AboutScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AboutScreenKt.lambda_665150509$lambda$4((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$92853841 = ComposableLambdaKt.composableLambdaInstance(92853841, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$AboutScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AboutScreenKt.lambda_92853841$lambda$6((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$968007994 = ComposableLambdaKt.composableLambdaInstance(968007994, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$AboutScreenKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AboutScreenKt.lambda_968007994$lambda$9((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-841895591, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f96lambda$841895591 = ComposableLambdaKt.composableLambdaInstance(-841895591, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$AboutScreenKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AboutScreenKt.lambda__841895591$lambda$12((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$1189292054 = ComposableLambdaKt.composableLambdaInstance(1189292054, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$AboutScreenKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AboutScreenKt.lambda_1189292054$lambda$20((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1677932106$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6946getLambda$1677932106$app() {
        return f95lambda$1677932106;
    }

    /* JADX INFO: renamed from: getLambda$-841895591$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6947getLambda$841895591$app() {
        return f96lambda$841895591;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$1189292054$app() {
        return lambda$1189292054;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$665150509$app() {
        return lambda$665150509;
    }

    public final Function2<Composer, Integer, Unit> getLambda$92853841$app() {
        return lambda$92853841;
    }

    public final Function2<Composer, Integer, Unit> getLambda$968007994$app() {
        return lambda$968007994;
    }

    /* JADX WARN: Code duplicated, block: B:58:0x0530  */
    static final Unit lambda_665150509$lambda$4(ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Composer composer;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C60@2436L2753:AboutScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(665150509, $changed, -1, "com.example.ui.screens.ComposableSingletons$AboutScreenKt.lambda$665150509.<anonymous> (AboutScreen.kt:60)");
            }
            Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ColorKt.getAccentGradient(), null, 0.0f, 6, null), Dp.m6622constructorimpl(20));
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
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
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
            ComposerKt.sourceInformationMarkerStart($composer, 2136157279, "C67@2682L2493:AboutScreen.kt#2thlc2");
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical verticalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM550spacedBy0680j_4, centerHorizontally, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((432 << 3) & 112) << 6) & 896) | 6;
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
            if (composerM3652constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1463914300, "C72@2964L64,71@2899L267,77@3208L600,94@3929L10,92@3830L250,99@4102L585,114@4953L10,112@4709L448:AboutScreen.kt#2thlc2");
            ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.logo_calis_inverted, $composer, 0), "Calis Logo", SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(56)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, $composer, 432, 120);
            Modifier modifierClip = ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(56)), RoundedCornerShapeKt.getCircleShape());
            long jM4196getWhite0d7_KjU = Color.INSTANCE.m4196getWhite0d7_KjU();
            Modifier modifierM225backgroundbw27NRU$default = BackgroundKt.m225backgroundbw27NRU$default(modifierClip, Color.m4157copywmQWz5c(jM4196getWhite0d7_KjU, (14 & 1) != 0 ? Color.m4161getAlphaimpl(jM4196getWhite0d7_KjU) : 0.25f, (14 & 2) != 0 ? Color.m4165getRedimpl(jM4196getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(jM4196getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(jM4196getWhite0d7_KjU) : 0.0f), null, 2, null);
            Alignment center2 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM225backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM3652constructorimpl3 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl3.getInserting()) {
                composer = $composer;
            } else {
                composer = $composer;
                if (!Intrinsics.areEqual(composerM3652constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                Composer composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i9 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, 1176371541, "C84@3527L259:AboutScreen.kt#2thlc2");
                IconKt.m2150Iconww6aTOc(DevicesKt.getDevices(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(32)), Color.INSTANCE.m4196getWhite0d7_KjU(), composer2, 3504, 0);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                TextKt.m2693Text4IGK_g("Calis Mobile", (Modifier) null, Color.INSTANCE.m4196getWhite0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getHeadlineMedium(), $composer, 196998, 0, 65498);
                RoundedCornerShape RoundedCornerShape = RoundedCornerShapeKt.RoundedCornerShape(50);
                long jM4196getWhite0d7_KjU2 = Color.INSTANCE.m4196getWhite0d7_KjU();
                SurfaceKt.m2543SurfaceT9BRK9s(null, RoundedCornerShape, Color.m4157copywmQWz5c(jM4196getWhite0d7_KjU2, (14 & 1) != 0 ? Color.m4161getAlphaimpl(jM4196getWhite0d7_KjU2) : 0.2f, (14 & 2) != 0 ? Color.m4165getRedimpl(jM4196getWhite0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(jM4196getWhite0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(jM4196getWhite0d7_KjU2) : 0.0f), 0L, 0.0f, 0.0f, null, f95lambda$1677932106, $composer, 12583296, 121);
                TextStyle bodySmall = MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall();
                long jM4196getWhite0d7_KjU3 = Color.INSTANCE.m4196getWhite0d7_KjU();
                TextKt.m2693Text4IGK_g("A physical classroom management application powered by Bluetooth Low Energy (BLE) GATT bridging Teacher and Student devices through dedicated Calis hardware.", (Modifier) null, Color.m4157copywmQWz5c(jM4196getWhite0d7_KjU3, (14 & 1) != 0 ? Color.m4161getAlphaimpl(jM4196getWhite0d7_KjU3) : 0.9f, (14 & 2) != 0 ? Color.m4165getRedimpl(jM4196getWhite0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(jM4196getWhite0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(jM4196getWhite0d7_KjU3) : 0.0f), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), TextUnitKt.getSp(18), 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodySmall, $composer, 390, 6, 63994);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
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
            composerM3652constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
            composerM3652constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            Updater.m3659setimpl(composerM3652constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = (i7 >> 6) & 14;
            Composer composer3 = composer;
            ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i11 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, 1176371541, "C84@3527L259:AboutScreen.kt#2thlc2");
            IconKt.m2150Iconww6aTOc(DevicesKt.getDevices(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(32)), Color.INSTANCE.m4196getWhite0d7_KjU(), composer3, 3504, 0);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            TextKt.m2693Text4IGK_g("Calis Mobile", (Modifier) null, Color.INSTANCE.m4196getWhite0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getHeadlineMedium(), $composer, 196998, 0, 65498);
            RoundedCornerShape RoundedCornerShape2 = RoundedCornerShapeKt.RoundedCornerShape(50);
            long jM4196getWhite0d7_KjU4 = Color.INSTANCE.m4196getWhite0d7_KjU();
            SurfaceKt.m2543SurfaceT9BRK9s(null, RoundedCornerShape2, Color.m4157copywmQWz5c(jM4196getWhite0d7_KjU4, (14 & 1) != 0 ? Color.m4161getAlphaimpl(jM4196getWhite0d7_KjU4) : 0.2f, (14 & 2) != 0 ? Color.m4165getRedimpl(jM4196getWhite0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(jM4196getWhite0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(jM4196getWhite0d7_KjU4) : 0.0f), 0L, 0.0f, 0.0f, null, f95lambda$1677932106, $composer, 12583296, 121);
            TextStyle bodySmall2 = MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall();
            long jM4196getWhite0d7_KjU5 = Color.INSTANCE.m4196getWhite0d7_KjU();
            TextKt.m2693Text4IGK_g("A physical classroom management application powered by Bluetooth Low Energy (BLE) GATT bridging Teacher and Student devices through dedicated Calis hardware.", (Modifier) null, Color.m4157copywmQWz5c(jM4196getWhite0d7_KjU5, (14 & 1) != 0 ? Color.m4161getAlphaimpl(jM4196getWhite0d7_KjU5) : 0.9f, (14 & 2) != 0 ? Color.m4165getRedimpl(jM4196getWhite0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(jM4196getWhite0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(jM4196getWhite0d7_KjU5) : 0.0f), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m6482boximpl(TextAlign.INSTANCE.m6489getCentere0LSkKk()), TextUnitKt.getSp(18), 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodySmall2, $composer, 390, 6, 63994);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
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

    static final Unit lambda__1677932106$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C106@4511L10,103@4278L387:AboutScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1677932106, $changed, -1, "com.example.ui.screens.ComposableSingletons$AboutScreenKt.lambda$-1677932106.<anonymous> (AboutScreen.kt:103)");
            }
            TextKt.m2693Text4IGK_g("Offline BLE Classroom Management • Prototype", PaddingKt.m671paddingVpY3zN4(Modifier.INSTANCE, Dp.m6622constructorimpl(12), Dp.m6622constructorimpl(4)), Color.INSTANCE.m4196getWhite0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 197046, 0, 65496);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x01cc  */
    static final Unit lambda_92853841$lambda$6(Composer $composer, int $changed) {
        int i;
        ComposerKt.sourceInformation($composer, "C129@5446L878:AboutScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(92853841, $changed, -1, "com.example.ui.screens.ComposableSingletons$AboutScreenKt.lambda$92853841.<anonymous> (AboutScreen.kt:129)");
            }
            Arrangement.Vertical verticalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM550spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
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
            Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
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
                ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i4 = ((i >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -246430840, "C130@5522L176,135@5755L11,135@5715L86,136@5818L198,141@6073L11,141@6033L86,142@6136L174:AboutScreen.kt#2thlc2");
                AboutScreenKt.CreditRoleItem("Lead Researcher", "FRANZEL C. DAMASCO", PersonKt.getPerson(Icons.INSTANCE.getDefault()), $composer, 54);
                long outlineVariant = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutlineVariant();
                DividerKt.m2072HorizontalDivider9IZ8Weo(null, 0.0f, Color.m4157copywmQWz5c(outlineVariant, (14 & 1) != 0 ? Color.m4161getAlphaimpl(outlineVariant) : 0.3f, (14 & 2) != 0 ? Color.m4165getRedimpl(outlineVariant) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(outlineVariant) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(outlineVariant) : 0.0f), $composer, 0, 3);
                AboutScreenKt.CreditRoleItem("System Designer & AI-Assisted Developer", "FRANZEL C. DAMASCO", CodeKt.getCode(Icons.INSTANCE.getDefault()), $composer, 54);
                long outlineVariant2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutlineVariant();
                DividerKt.m2072HorizontalDivider9IZ8Weo(null, 0.0f, Color.m4157copywmQWz5c(outlineVariant2, (14 & 1) != 0 ? Color.m4161getAlphaimpl(outlineVariant2) : 0.3f, (14 & 2) != 0 ? Color.m4165getRedimpl(outlineVariant2) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(outlineVariant2) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(outlineVariant2) : 0.0f), $composer, 0, 3);
                AboutScreenKt.CreditRoleItem("UI Designer", "FRANZEL C. DAMASCO", VerifiedKt.getVerified(Icons.INSTANCE.getDefault()), $composer, 54);
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
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i6 = ((i >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -246430840, "C130@5522L176,135@5755L11,135@5715L86,136@5818L198,141@6073L11,141@6033L86,142@6136L174:AboutScreen.kt#2thlc2");
            AboutScreenKt.CreditRoleItem("Lead Researcher", "FRANZEL C. DAMASCO", PersonKt.getPerson(Icons.INSTANCE.getDefault()), $composer, 54);
            long outlineVariant3 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutlineVariant();
            DividerKt.m2072HorizontalDivider9IZ8Weo(null, 0.0f, Color.m4157copywmQWz5c(outlineVariant3, (14 & 1) != 0 ? Color.m4161getAlphaimpl(outlineVariant3) : 0.3f, (14 & 2) != 0 ? Color.m4165getRedimpl(outlineVariant3) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(outlineVariant3) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(outlineVariant3) : 0.0f), $composer, 0, 3);
            AboutScreenKt.CreditRoleItem("System Designer & AI-Assisted Developer", "FRANZEL C. DAMASCO", CodeKt.getCode(Icons.INSTANCE.getDefault()), $composer, 54);
            long outlineVariant4 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutlineVariant();
            DividerKt.m2072HorizontalDivider9IZ8Weo(null, 0.0f, Color.m4157copywmQWz5c(outlineVariant4, (14 & 1) != 0 ? Color.m4161getAlphaimpl(outlineVariant4) : 0.3f, (14 & 2) != 0 ? Color.m4165getRedimpl(outlineVariant4) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(outlineVariant4) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(outlineVariant4) : 0.0f), $composer, 0, 3);
            AboutScreenKt.CreditRoleItem("UI Designer", "FRANZEL C. DAMASCO", VerifiedKt.getVerified(Icons.INSTANCE.getDefault()), $composer, 54);
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

    static final Unit lambda_968007994$lambda$9(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C166@6861L282:AboutScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(968007994, $changed, -1, "com.example.ui.screens.ComposableSingletons$AboutScreenKt.lambda$968007994.<anonymous> (AboutScreen.kt:156)");
            }
            Iterable testers = CollectionsKt.listOf((Object[]) new String[]{"JHENA MAY A. LUCERO", "JHONDEL S. TESORO", "JOHN MAVERICK M. DIONIO", "KIM KIRVY C. GUZMAN", "MICKAELA B. MENDOZA", "PRINCESS PAULEEN TIBALLA", "RICA P. GALLENTES"});
            Arrangement.Vertical verticalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM550spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1301541234, "C:AboutScreen.kt#2thlc2");
            $composer.startReplaceGroup(41985449);
            ComposerKt.sourceInformation($composer, "*168@6996L115");
            Iterable iterable = testers;
            int i4 = 0;
            int i5 = 0;
            for (Object obj : iterable) {
                int i6 = i5 + 1;
                if (i5 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                AboutScreenKt.NameListItem(i5 + 1, (String) obj, $composer, 0);
                i5 = i6;
                iterable = iterable;
                i4 = i4;
                i3 = i3;
            }
            $composer.endReplaceGroup();
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

    static final Unit lambda__841895591$lambda$12(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C198@7930L286:AboutScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-841895591, $changed, -1, "com.example.ui.screens.ComposableSingletons$AboutScreenKt.lambda$-841895591.<anonymous> (AboutScreen.kt:182)");
            }
            Iterable researchers = CollectionsKt.listOf((Object[]) new String[]{"CEDRIC P. NOPRE", "FRANZEL C. DAMASCO", "GABRIELLE ANNE S. MERCADEJAS", "GIAN CARLOS H. PALOMA", "JHENA MAY A. LUCERO", "JHONDEL S. TESORO", "JOHN EMERALD R. MALINAO", "JOHN MAVERICK M. DIONIO", "JONNA C. ALMORADIE", "KIM KIRVY C. GUZMAN", "MICKAELA B. MENDOZA", "PRINCESS PAULEEN TIBALLA", "RICA P. GALLENTES"});
            Arrangement.Vertical verticalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM550spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1483274607, "C:AboutScreen.kt#2thlc2");
            $composer.startReplaceGroup(186395272);
            ComposerKt.sourceInformation($composer, "*200@8069L115");
            Iterable iterable = researchers;
            int i4 = 0;
            int i5 = 0;
            for (Object obj : iterable) {
                int i6 = i5 + 1;
                if (i5 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                AboutScreenKt.NameListItem(i5 + 1, (String) obj, $composer, 0);
                i5 = i6;
                iterable = iterable;
                i4 = i4;
                i3 = i3;
            }
            $composer.endReplaceGroup();
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

    /* JADX WARN: Code duplicated, block: B:104:0x0903 A[LOOP:0: B:102:0x08fd->B:104:0x0903, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:107:0x0956  */
    /* JADX WARN: Code duplicated, block: B:88:0x0845  */
    /* JADX WARN: Code duplicated, block: B:91:0x0851  */
    /* JADX WARN: Code duplicated, block: B:92:0x0857  */
    /* JADX WARN: Code duplicated, block: B:95:0x0888  */
    /* JADX WARN: Code duplicated, block: B:98:0x089b  */
    /* JADX WARN: Code duplicated, block: B:99:0x089e  */
    static final Unit lambda_1189292054$lambda$20(ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Function0<ComposeUiNode> function4;
        Composer composer;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function5;
        Composer composerM3652constructorimpl;
        Composer composer2;
        Composer composer3;
        Iterable iterable;
        int i;
        Iterator it;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C217@8619L3281:AboutScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1189292054, $changed, -1, "com.example.ui.screens.ComposableSingletons$AboutScreenKt.lambda$1189292054.<anonymous> (AboutScreen.kt:217)");
            }
            Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(16));
            Arrangement.Vertical verticalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(14));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM550spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM670padding3ABfNKs);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i2 = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor2;
                $composer.createNode(function0);
            } else {
                function0 = constructor2;
                $composer.useNode();
            }
            Composer composerM3652constructorimpl2 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash);
            }
            Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i4 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1486257022, "C222@8813L1584,257@10455L11,257@10415L86,260@10558L760,288@11677L209:AboutScreen.kt#2thlc2");
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM550spacedBy0680j_4, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i5 = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function1 = constructor3;
                $composer.createNode(function1);
            } else {
                function1 = constructor3;
                $composer.useNode();
            }
            Composer composerM3652constructorimpl3 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl3, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl3, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM3652constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM3652constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash2);
            }
            Updater.m3659setimpl(composerM3652constructorimpl3, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i6 = (i5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i7 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 168045626, "C227@9054L652,242@9728L651:AboutScreen.kt#2thlc2");
            Modifier modifierM225backgroundbw27NRU$default = BackgroundKt.m225backgroundbw27NRU$default(ClipKt.clip(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(44)), RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(12))), androidx.compose.ui.graphics.ColorKt.Color(4293456126L), null, 2, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM225backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i8 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function2 = constructor4;
                $composer.createNode(function2);
            } else {
                function2 = constructor4;
                $composer.useNode();
            }
            Composer composerM3652constructorimpl4 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl4, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM3652constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM3652constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash3);
            }
            Updater.m3659setimpl(composerM3652constructorimpl4, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i9 = (i8 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i10 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -786387466, "C235@9417L52,234@9373L311:AboutScreen.kt#2thlc2");
            IconKt.m2149Iconww6aTOc(PainterResources_androidKt.painterResource(R.drawable.ic_google_ai_studio, $composer, 0), "Google AI Studio Logo", SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(28)), Color.INSTANCE.m4195getUnspecified0d7_KjU(), $composer, 3504, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            int i11 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function3 = constructor5;
                $composer.createNode(function3);
            } else {
                function3 = constructor5;
                $composer.useNode();
            }
            Composer composerM3652constructorimpl5 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl5, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl5, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                composerM3652constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composerM3652constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash4);
            }
            Updater.m3659setimpl(composerM3652constructorimpl5, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i12 = (i11 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i13 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 702373097, "C245@9883L10,247@10020L11,243@9761L306,251@10231L10,252@10303L11,249@10092L265:AboutScreen.kt#2thlc2");
            TextKt.m2693Text4IGK_g("Powered by Google AI Studio", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 196614, 0, 65498);
            TextKt.m2693Text4IGK_g("Artificial Intelligence Engineering Platform", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 65530);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long outlineVariant = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutlineVariant();
            DividerKt.m2072HorizontalDivider9IZ8Weo(null, 0.0f, Color.m4157copywmQWz5c(outlineVariant, (14 & 1) != 0 ? Color.m4161getAlphaimpl(outlineVariant) : 0.3f, (14 & 2) != 0 ? Color.m4165getRedimpl(outlineVariant) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(outlineVariant) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(outlineVariant) : 0.0f), $composer, 0, 3);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal horizontalM550spacedBy0680j_5 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(6));
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            Modifier modifier2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalM550spacedBy0680j_5, centerVertically2, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifier2);
            Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
            int i14 = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function4 = constructor6;
                $composer.createNode(function4);
            } else {
                function4 = constructor6;
                $composer.useNode();
            }
            Composer composerM3652constructorimpl6 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl6, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl6, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl6.getInserting()) {
                composer = $composer;
            } else {
                composer = $composer;
                if (!Intrinsics.areEqual(composerM3652constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl6, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                int i15 = (i14 >> 6) & 14;
                Composer composer4 = composer;
                ComposerKt.sourceInformationMarkerStart(composer4, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i16 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, 1709803344, "C264@10742L249,272@11136L10,274@11259L11,270@11012L288:AboutScreen.kt#2thlc2");
                IconKt.m2150Iconww6aTOc(AutoAwesomeKt.getAutoAwesome(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(16)), androidx.compose.ui.graphics.ColorKt.Color(4279923688L), composer4, 3504, 0);
                TextKt.m2693Text4IGK_g("AI Models Used Throughout Development", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer4, MaterialTheme.$stable).getPrimary(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer4, MaterialTheme.$stable).getLabelLarge(), composer4, 196614, 0, 65498);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Iterable iterableListOf = CollectionsKt.listOf((Object[]) new String[]{"Gemini Flash Latest", "Gemini Pro Latest", "Gemini 3.8 Flash", "Gemini 3.7 Flash", "Gemini 3.6 Flash", "Gemini 3.5 Flash", "Gemini 3.0 Flash"});
                Arrangement.Vertical verticalM550spacedBy0680j_5 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                Modifier modifier3 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(verticalM550spacedBy0680j_5, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifier3);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i17 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function5 = constructor;
                    $composer.createNode(function5);
                } else {
                    function5 = constructor;
                    $composer.useNode();
                }
                composerM3652constructorimpl = Updater.m3652constructorimpl($composer);
                Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM3652constructorimpl.getInserting()) {
                    composer2 = $composer;
                } else {
                    composer2 = $composer;
                    if (!Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    }
                    Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                    int i18 = (i17 >> 6) & 14;
                    composer3 = composer2;
                    ComposerKt.sourceInformationMarkerStart(composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                    int i19 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, 1157143017, "C:AboutScreen.kt#2thlc2");
                    composer3.startReplaceGroup(37327473);
                    ComposerKt.sourceInformation(composer3, "*290@11812L34");
                    iterable = iterableListOf;
                    i = 0;
                    it = iterable.iterator();
                    while (it.hasNext()) {
                        AboutScreenKt.AiModelItem((String) it.next(), composer3, 0);
                        iterable = iterable;
                        i = i;
                    }
                    composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
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
                composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash6);
                Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                int i110 = (i17 >> 6) & 14;
                composer3 = composer2;
                ComposerKt.sourceInformationMarkerStart(composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                int i111 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, 1157143017, "C:AboutScreen.kt#2thlc2");
                composer3.startReplaceGroup(37327473);
                ComposerKt.sourceInformation(composer3, "*290@11812L34");
                iterable = iterableListOf;
                i = 0;
                it = iterable.iterator();
                while (it.hasNext()) {
                    AboutScreenKt.AiModelItem((String) it.next(), composer3, 0);
                    iterable = iterable;
                    i = i;
                }
                composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
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
            composerM3652constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
            composerM3652constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash5);
            Updater.m3659setimpl(composerM3652constructorimpl6, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            int i112 = (i14 >> 6) & 14;
            Composer composer5 = composer;
            ComposerKt.sourceInformationMarkerStart(composer5, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
            int i113 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer5, 1709803344, "C264@10742L249,272@11136L10,274@11259L11,270@11012L288:AboutScreen.kt#2thlc2");
            IconKt.m2150Iconww6aTOc(AutoAwesomeKt.getAutoAwesome(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(16)), androidx.compose.ui.graphics.ColorKt.Color(4279923688L), composer5, 3504, 0);
            TextKt.m2693Text4IGK_g("AI Models Used Throughout Development", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer5, MaterialTheme.$stable).getPrimary(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer5, MaterialTheme.$stable).getLabelLarge(), composer5, 196614, 0, 65498);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            Iterable iterableListOf2 = CollectionsKt.listOf((Object[]) new String[]{"Gemini Flash Latest", "Gemini Pro Latest", "Gemini 3.8 Flash", "Gemini 3.7 Flash", "Gemini 3.6 Flash", "Gemini 3.5 Flash", "Gemini 3.0 Flash"});
            Arrangement.Vertical verticalM550spacedBy0680j_6 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            Modifier modifier4 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(verticalM550spacedBy0680j_6, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap7 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier($composer, modifier4);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i114 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function5 = constructor;
                $composer.createNode(function5);
            } else {
                function5 = constructor;
                $composer.useNode();
            }
            composerM3652constructorimpl = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyColumnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl.getInserting()) {
                composer2 = $composer;
                if (!Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                int i115 = (i114 >> 6) & 14;
                composer3 = composer2;
                ComposerKt.sourceInformationMarkerStart(composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                int i116 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, 1157143017, "C:AboutScreen.kt#2thlc2");
                composer3.startReplaceGroup(37327473);
                ComposerKt.sourceInformation(composer3, "*290@11812L34");
                iterable = iterableListOf2;
                i = 0;
                it = iterable.iterator();
                while (it.hasNext()) {
                    AboutScreenKt.AiModelItem((String) it.next(), composer3, 0);
                    iterable = iterable;
                    i = i;
                }
                composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer2 = $composer;
            }
            composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash7);
            Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
            int i117 = (i114 >> 6) & 14;
            composer3 = composer2;
            ComposerKt.sourceInformationMarkerStart(composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
            int i118 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, 1157143017, "C:AboutScreen.kt#2thlc2");
            composer3.startReplaceGroup(37327473);
            ComposerKt.sourceInformation(composer3, "*290@11812L34");
            iterable = iterableListOf2;
            i = 0;
            it = iterable.iterator();
            while (it.hasNext()) {
                AboutScreenKt.AiModelItem((String) it.next(), composer3, 0);
                iterable = iterable;
                i = i;
            }
            composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
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
