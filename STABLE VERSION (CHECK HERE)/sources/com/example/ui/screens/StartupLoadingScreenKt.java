package com.example.ui.screens;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.draw.ScaleKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.unit.Dp;
import com.example.R;
import com.example.ui.theme.ColorKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: StartupLoadingScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"StartupLoadingScreen", "", "statusMessage", "", "modifier", "Landroidx/compose/ui/Modifier;", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class StartupLoadingScreenKt {
    static final Unit StartupLoadingScreen$lambda$6(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        StartupLoadingScreen(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:68:0x030e  */
    /* JADX WARN: Code duplicated, block: B:71:0x031a  */
    /* JADX WARN: Code duplicated, block: B:72:0x0320  */
    /* JADX WARN: Code duplicated, block: B:75:0x0351  */
    /* JADX WARN: Code duplicated, block: B:78:0x0364  */
    /* JADX WARN: Code duplicated, block: B:79:0x0367  */
    /* JADX WARN: Code duplicated, block: B:83:0x0470  */
    public static final void StartupLoadingScreen(String statusMessage, Modifier modifier, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        String statusMessage2;
        Modifier.Companion modifier3;
        Object objAnimatable$default;
        Object objAnimatable$default2;
        StartupLoadingScreenKt$StartupLoadingScreen$1$1 startupLoadingScreenKt$StartupLoadingScreen$1$1;
        StartupLoadingScreenKt$StartupLoadingScreen$2$1 startupLoadingScreenKt$StartupLoadingScreen$2$1;
        Function0<ComposeUiNode> function0;
        Modifier modifier4;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function1;
        Composer composerM3652constructorimpl;
        Composer composer;
        final String statusMessage3;
        Composer $composer2 = $composer.startRestartGroup(-1860251162);
        ComposerKt.sourceInformation($composer2, "C(StartupLoadingScreen)P(1)49@2100L27,50@2148L30,52@2205L162,52@2184L183,59@2394L162,59@2373L183,66@2562L1175:StartupLoadingScreen.kt#2thlc2");
        int $dirty = $changed;
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($dirty & 17) == 16 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            statusMessage3 = statusMessage;
        } else {
            if ((i & 1) != 0) {
                statusMessage2 = "Initializing Calis...";
            } else {
                statusMessage2 = statusMessage;
            }
            if (i2 == 0) {
                modifier3 = modifier2;
            } else {
                modifier3 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1860251162, $dirty, -1, "com.example.ui.screens.StartupLoadingScreen (StartupLoadingScreen.kt:48)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -994966751, "CC(remember):StartupLoadingScreen.kt#9igjgp");
            Object objRememberedValue = $composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objAnimatable$default = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
                $composer2.updateRememberedValue(objAnimatable$default);
            } else {
                objAnimatable$default = objRememberedValue;
            }
            Animatable alphaAnim = (Animatable) objAnimatable$default;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -994965212, "CC(remember):StartupLoadingScreen.kt#9igjgp");
            Object objRememberedValue2 = $composer2.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objAnimatable$default2 = AnimatableKt.Animatable$default(0.92f, 0.0f, 2, null);
                $composer2.updateRememberedValue(objAnimatable$default2);
            } else {
                objAnimatable$default2 = objRememberedValue2;
            }
            Animatable scaleAnim = (Animatable) objAnimatable$default2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -994963256, "CC(remember):StartupLoadingScreen.kt#9igjgp");
            boolean zChangedInstance = $composer2.changedInstance(alphaAnim);
            Object objRememberedValue3 = $composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                startupLoadingScreenKt$StartupLoadingScreen$1$1 = new StartupLoadingScreenKt$StartupLoadingScreen$1$1(alphaAnim, null);
                $composer2.updateRememberedValue(startupLoadingScreenKt$StartupLoadingScreen$1$1);
            } else {
                startupLoadingScreenKt$StartupLoadingScreen$1$1 = objRememberedValue3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) startupLoadingScreenKt$StartupLoadingScreen$1$1, $composer2, 6);
            Unit unit2 = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -994957208, "CC(remember):StartupLoadingScreen.kt#9igjgp");
            boolean zChangedInstance2 = $composer2.changedInstance(scaleAnim);
            Object objRememberedValue4 = $composer2.rememberedValue();
            if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                startupLoadingScreenKt$StartupLoadingScreen$2$1 = new StartupLoadingScreenKt$StartupLoadingScreen$2$1(scaleAnim, null);
                $composer2.updateRememberedValue(startupLoadingScreenKt$StartupLoadingScreen$2$1);
            } else {
                startupLoadingScreenKt$StartupLoadingScreen$2$1 = objRememberedValue4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(unit2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) startupLoadingScreenKt$StartupLoadingScreen$2$1, $composer2, 6);
            Modifier modifierNavigationBarsPadding = WindowInsetsPadding_androidKt.navigationBarsPadding(WindowInsetsPadding_androidKt.statusBarsPadding(BackgroundKt.m225backgroundbw27NRU$default(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), Color.INSTANCE.m4196getWhite0d7_KjU(), null, 2, null)));
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierNavigationBarsPadding);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor2;
                $composer2.createNode(function0);
            } else {
                function0 = constructor2;
                $composer2.useNode();
            }
            Composer composerM3652constructorimpl2 = Updater.m3652constructorimpl($composer2);
            Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl2.getInserting()) {
                modifier4 = modifier3;
            } else {
                modifier4 = modifier3;
                if (!Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = (i3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                int i5 = ((0 >> 6) & 112) | 6;
                BoxScope boxScope = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -1171271491, "C73@2745L986:StartupLoadingScreen.kt#2thlc2");
                Modifier modifierScale = ScaleKt.scale(AlphaKt.alpha(PaddingKt.m672paddingVpY3zN4$default(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), Dp.m6622constructorimpl(32), 0.0f, 2, null), ((Number) alphaAnim.getValue()).floatValue()), ((Number) scaleAnim.getValue()).floatValue());
                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.Vertical center = Arrangement.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer2, modifierScale);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i6 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor;
                    $composer2.useNode();
                }
                composerM3652constructorimpl = Updater.m3652constructorimpl($composer2);
                Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3652constructorimpl.getInserting()) {
                    composer = $composer2;
                    if (!Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    }
                    Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i7 = (i6 >> 6) & 14;
                    Composer composer2 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer2, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i8 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer2, 1223076874, "C84@3207L64,83@3150L236,89@3400L41,92@3488L233:StartupLoadingScreen.kt#2thlc2");
                    ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.logo_calis_inverted, composer2, 0), "Calis Logo", SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(ComposerKt.invocationKey)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer2, 432, 120);
                    SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(48)), composer2, 6);
                    Modifier modifierM715size3ABfNKs = SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(48));
                    float fM6622constructorimpl = Dp.m6622constructorimpl(4);
                    long calisPrimaryBlue = ColorKt.getCalisPrimaryBlue();
                    long calisPrimaryBlue2 = ColorKt.getCalisPrimaryBlue();
                    ProgressIndicatorKt.m2367CircularProgressIndicatorLxG7B9w(modifierM715size3ABfNKs, calisPrimaryBlue, fM6622constructorimpl, Color.m4157copywmQWz5c(calisPrimaryBlue2, (14 & 1) != 0 ? Color.m4161getAlphaimpl(calisPrimaryBlue2) : 0.15f, (14 & 2) != 0 ? Color.m4165getRedimpl(calisPrimaryBlue2) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(calisPrimaryBlue2) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(calisPrimaryBlue2) : 0.0f), 0, composer2, 3510, 16);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    statusMessage3 = statusMessage2;
                    modifier2 = modifier4;
                } else {
                    composer = $composer2;
                }
                composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash2);
                Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = (i6 >> 6) & 14;
                Composer composer3 = composer;
                ComposerKt.sourceInformationMarkerStart(composer3, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i10 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, 1223076874, "C84@3207L64,83@3150L236,89@3400L41,92@3488L233:StartupLoadingScreen.kt#2thlc2");
                ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.logo_calis_inverted, composer3, 0), "Calis Logo", SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(ComposerKt.invocationKey)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer3, 432, 120);
                SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(48)), composer3, 6);
                Modifier modifierM715size3ABfNKs2 = SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(48));
                float fM6622constructorimpl2 = Dp.m6622constructorimpl(4);
                long calisPrimaryBlue3 = ColorKt.getCalisPrimaryBlue();
                long calisPrimaryBlue4 = ColorKt.getCalisPrimaryBlue();
                ProgressIndicatorKt.m2367CircularProgressIndicatorLxG7B9w(modifierM715size3ABfNKs2, calisPrimaryBlue3, fM6622constructorimpl2, Color.m4157copywmQWz5c(calisPrimaryBlue4, (14 & 1) != 0 ? Color.m4161getAlphaimpl(calisPrimaryBlue4) : 0.15f, (14 & 2) != 0 ? Color.m4165getRedimpl(calisPrimaryBlue4) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(calisPrimaryBlue4) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(calisPrimaryBlue4) : 0.0f), 0, composer3, 3510, 16);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                statusMessage3 = statusMessage2;
                modifier2 = modifier4;
            }
            composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
            composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash);
            Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            int i12 = ((0 >> 6) & 112) | 6;
            BoxScope boxScope2 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -1171271491, "C73@2745L986:StartupLoadingScreen.kt#2thlc2");
            Modifier modifierScale2 = ScaleKt.scale(AlphaKt.alpha(PaddingKt.m672paddingVpY3zN4$default(boxScope2.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), Dp.m6622constructorimpl(32), 0.0f, 2, null), ((Number) alphaAnim.getValue()).floatValue()), ((Number) scaleAnim.getValue()).floatValue());
            Alignment.Horizontal centerHorizontally2 = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical center2 = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(center2, centerHorizontally2, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer2, modifierScale2);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i13 = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function1 = constructor;
                $composer2.createNode(function1);
            } else {
                function1 = constructor;
                $composer2.useNode();
            }
            composerM3652constructorimpl = Updater.m3652constructorimpl($composer2);
            Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3652constructorimpl.getInserting()) {
                composer = $composer2;
                if (!Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = (i13 >> 6) & 14;
                Composer composer4 = composer;
                ComposerKt.sourceInformationMarkerStart(composer4, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                int i15 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, 1223076874, "C84@3207L64,83@3150L236,89@3400L41,92@3488L233:StartupLoadingScreen.kt#2thlc2");
                ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.logo_calis_inverted, composer4, 0), "Calis Logo", SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(ComposerKt.invocationKey)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer4, 432, 120);
                SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(48)), composer4, 6);
                Modifier modifierM715size3ABfNKs3 = SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(48));
                float fM6622constructorimpl3 = Dp.m6622constructorimpl(4);
                long calisPrimaryBlue5 = ColorKt.getCalisPrimaryBlue();
                long calisPrimaryBlue6 = ColorKt.getCalisPrimaryBlue();
                ProgressIndicatorKt.m2367CircularProgressIndicatorLxG7B9w(modifierM715size3ABfNKs3, calisPrimaryBlue5, fM6622constructorimpl3, Color.m4157copywmQWz5c(calisPrimaryBlue6, (14 & 1) != 0 ? Color.m4161getAlphaimpl(calisPrimaryBlue6) : 0.15f, (14 & 2) != 0 ? Color.m4165getRedimpl(calisPrimaryBlue6) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(calisPrimaryBlue6) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(calisPrimaryBlue6) : 0.0f), 0, composer4, 3510, 16);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                statusMessage3 = statusMessage2;
                modifier2 = modifier4;
            } else {
                composer = $composer2;
            }
            composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash3);
            Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i16 = (i13 >> 6) & 14;
            Composer composer5 = composer;
            ComposerKt.sourceInformationMarkerStart(composer5, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
            int i17 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer5, 1223076874, "C84@3207L64,83@3150L236,89@3400L41,92@3488L233:StartupLoadingScreen.kt#2thlc2");
            ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.logo_calis_inverted, composer5, 0), "Calis Logo", SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(ComposerKt.invocationKey)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer5, 432, 120);
            SpacerKt.Spacer(SizeKt.m701height3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(48)), composer5, 6);
            Modifier modifierM715size3ABfNKs4 = SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(48));
            float fM6622constructorimpl4 = Dp.m6622constructorimpl(4);
            long calisPrimaryBlue7 = ColorKt.getCalisPrimaryBlue();
            long calisPrimaryBlue8 = ColorKt.getCalisPrimaryBlue();
            ProgressIndicatorKt.m2367CircularProgressIndicatorLxG7B9w(modifierM715size3ABfNKs4, calisPrimaryBlue7, fM6622constructorimpl4, Color.m4157copywmQWz5c(calisPrimaryBlue8, (14 & 1) != 0 ? Color.m4161getAlphaimpl(calisPrimaryBlue8) : 0.15f, (14 & 2) != 0 ? Color.m4165getRedimpl(calisPrimaryBlue8) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(calisPrimaryBlue8) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(calisPrimaryBlue8) : 0.0f), 0, composer5, 3510, 16);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            statusMessage3 = statusMessage2;
            modifier2 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.ui.screens.StartupLoadingScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return StartupLoadingScreenKt.StartupLoadingScreen$lambda$6(statusMessage3, modifier2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
