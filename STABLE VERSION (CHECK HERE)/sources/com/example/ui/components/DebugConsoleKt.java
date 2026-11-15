package com.example.ui.components;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Environment;
import android.widget.Toast;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldDefaults;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.profileinstaller.ProfileVerifier;
import com.example.ble.BleManager;
import io.ktor.sse.ServerSentEventKt;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DebugConsole.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002\u001a\u001b\u0010\u0003\u001a\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a;\u0010\u0007\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001e\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002\u001a\r\u0010\u0016\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002¨\u0006\u0017²\u0006\n\u0010\u0018\u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010\u001a\u001a\u00020\u0019X\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\u001cX\u008a\u008e\u0002²\u0006\n\u0010\u001d\u001a\u00020\u001cX\u008a\u008e\u0002²\u0006\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u008a\u0084\u0002²\u0006\n\u0010\u001e\u001a\u00020\u001fX\u008a\u0084\u0002²\u0006\n\u0010 \u001a\u00020!X\u008a\u0084\u0002²\u0006\n\u0010\"\u001a\u00020#X\u008a\u008e\u0002"}, d2 = {"DebugConsolePopup", "", "(Landroidx/compose/runtime/Composer;I)V", "MinimizedDebugConsole", "onExpand", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "ExpandedDebugConsole", "onMinimize", "dragModifier", "Landroidx/compose/ui/Modifier;", "widthDp", "Landroidx/compose/ui/unit/Dp;", "heightDp", "ExpandedDebugConsole-UuyPYSY", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;FFLandroidx/compose/runtime/Composer;II)V", "saveLogs", "context", "Landroid/content/Context;", "logs", "", "Lcom/example/ble/BleManager$DebugLog;", "VerboseLogOverlay", "app", "isVisible", "", "isMinimized", "offsetX", "", "offsetY", "connState", "Lcom/example/ble/BleManager$ConnectionState;", "rssi", "", "customPacket", ""}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class DebugConsoleKt {
    static final Unit DebugConsolePopup$lambda$1(int i, Composer composer, int i2) {
        DebugConsolePopup(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DebugConsolePopup$lambda$38(int i, Composer composer, int i2) {
        DebugConsolePopup(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ExpandedDebugConsole_UuyPYSY$lambda$69(Function0 function0, Modifier modifier, float f, float f2, int i, int i2, Composer composer, int i3) {
        m6941ExpandedDebugConsoleUuyPYSY(function0, modifier, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MinimizedDebugConsole$lambda$39(Function0 function0, int i, Composer composer, int i2) {
        MinimizedDebugConsole(function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit VerboseLogOverlay$lambda$71(int i, Composer composer, int i2) {
        VerboseLogOverlay(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:105:0x04ac  */
    /* JADX WARN: Code duplicated, block: B:106:0x04ba  */
    /* JADX WARN: Code duplicated, block: B:109:0x052b  */
    /* JADX WARN: Code duplicated, block: B:112:0x0537  */
    /* JADX WARN: Code duplicated, block: B:113:0x053d  */
    /* JADX WARN: Code duplicated, block: B:124:0x05dd  */
    /* JADX WARN: Code duplicated, block: B:126:0x0648  */
    /* JADX WARN: Code duplicated, block: B:129:0x0654  */
    /* JADX WARN: Code duplicated, block: B:130:0x065a  */
    /* JADX WARN: Code duplicated, block: B:133:0x068b  */
    /* JADX WARN: Code duplicated, block: B:136:0x069e  */
    /* JADX WARN: Code duplicated, block: B:137:0x06a1  */
    /* JADX WARN: Code duplicated, block: B:141:0x0716  */
    /* JADX WARN: Code duplicated, block: B:144:0x0721  */
    /* JADX WARN: Code duplicated, block: B:145:0x072d  */
    /* JADX WARN: Code duplicated, block: B:148:0x076f  */
    /* JADX WARN: Code duplicated, block: B:150:0x079c  */
    /* JADX WARN: Code duplicated, block: B:151:0x07a8  */
    /* JADX WARN: Code duplicated, block: B:155:0x07e2  */
    public static final void DebugConsolePopup(Composer $composer, final int $changed) {
        Object objMutableStateOf$default;
        Object objValueOf;
        Object objValueOf2;
        Object objM6620boximpl;
        Object objM6620boximpl2;
        Object objValueOf3;
        Object objValueOf4;
        Object objValueOf5;
        Object objValueOf6;
        Object objValueOf7;
        Object objMutableFloatStateOf;
        Object objMutableFloatStateOf2;
        float screenHeightPx;
        float expandedHeightPx;
        float expandedHeightPx2;
        final MutableFloatState offsetY$delegate;
        final MutableFloatState offsetX$delegate;
        Object obj;
        Modifier dragModifier;
        Object objRememberedValue;
        Composer $composer2;
        Object obj2;
        final MutableFloatState offsetX$delegate2;
        final MutableFloatState offsetY$delegate2;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer composerM3652constructorimpl;
        Object objRememberedValue2;
        Object obj3;
        Composer composer;
        int currentCompositeKeyHash2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function1;
        Composer composerM3652constructorimpl2;
        Composer composer2;
        boolean zChanged;
        Object objRememberedValue3;
        float screenHeightPx2;
        Object obj4;
        Composer $composer3 = $composer.startRestartGroup(1551075875);
        ComposerKt.sourceInformation($composer3, "C(DebugConsolePopup)49@2032L16,52@2100L33,53@2177L7,54@2216L7,56@2249L118,59@2393L120,63@2541L106,66@2675L108,70@2811L91,73@2930L93,77@3051L52,79@3124L92,80@3236L95,81@3351L42,82@3413L42,84@3581L628,99@4268L57,97@4215L1142:DebugConsole.kt#qonjpd");
        if ($changed == 0 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1551075875, $changed, -1, "com.example.ui.components.DebugConsolePopup (DebugConsole.kt:48)");
            }
            State isVisible$delegate = SnapshotStateKt.collectAsState(BleManager.INSTANCE.isDebugConsoleVisible(), null, $composer3, 0, 1);
            if (!DebugConsolePopup$lambda$0(isVisible$delegate)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj5, Object obj6) {
                            return DebugConsoleKt.DebugConsolePopup$lambda$1($changed, (Composer) obj5, ((Integer) obj6).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 230269316, "CC(remember):DebugConsole.kt#9igjgp");
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
            } else {
                objMutableStateOf$default = objRememberedValue4;
            }
            final MutableState isMinimized$delegate = (MutableState) objMutableStateOf$default;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Configuration configuration = (Configuration) objConsume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) objConsume2;
            int i = configuration.screenWidthDp;
            ComposerKt.sourceInformationMarkerStart($composer3, 230274169, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged2 = $composer3.changed(i) | $composer3.changed(density);
            Object objRememberedValue5 = $composer3.rememberedValue();
            if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objValueOf = Float.valueOf(density.mo362toPx0680j_4(Dp.m6622constructorimpl(configuration.screenWidthDp)));
                $composer3.updateRememberedValue(objValueOf);
            } else {
                objValueOf = objRememberedValue5;
            }
            final float screenWidthPx = ((Number) objValueOf).floatValue();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int i2 = configuration.screenHeightDp;
            ComposerKt.sourceInformationMarkerStart($composer3, 230278779, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged3 = $composer3.changed(i2) | $composer3.changed(density);
            Object objRememberedValue6 = $composer3.rememberedValue();
            if (zChanged3 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objValueOf2 = Float.valueOf(density.mo362toPx0680j_4(Dp.m6622constructorimpl(configuration.screenHeightDp)));
                $composer3.updateRememberedValue(objValueOf2);
            } else {
                objValueOf2 = objRememberedValue6;
            }
            float screenHeightPx3 = ((Number) objValueOf2).floatValue();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int i3 = configuration.screenWidthDp;
            ComposerKt.sourceInformationMarkerStart($composer3, 230283501, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged4 = $composer3.changed(i3);
            Object objRememberedValue7 = $composer3.rememberedValue();
            if (zChanged4 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objM6620boximpl = Dp.m6620boximpl(((Dp) ComparisonsKt.minOf(Dp.m6620boximpl(Dp.m6622constructorimpl(340)), Dp.m6620boximpl(Dp.m6622constructorimpl(configuration.screenWidthDp - 20)))).m6636unboximpl());
                $composer3.updateRememberedValue(objM6620boximpl);
            } else {
                objM6620boximpl = objRememberedValue7;
            }
            float expandedWidthDp = ((Dp) objM6620boximpl).m6636unboximpl();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int i4 = configuration.screenHeightDp;
            ComposerKt.sourceInformationMarkerStart($composer3, 230287791, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged5 = $composer3.changed(i4);
            Object objRememberedValue8 = $composer3.rememberedValue();
            if (zChanged5 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objM6620boximpl2 = Dp.m6620boximpl(((Dp) ComparisonsKt.minOf(Dp.m6620boximpl(Dp.m6622constructorimpl(450)), Dp.m6620boximpl(Dp.m6622constructorimpl(configuration.screenHeightDp - 80)))).m6636unboximpl());
                $composer3.updateRememberedValue(objM6620boximpl2);
            } else {
                objM6620boximpl2 = objRememberedValue8;
            }
            float expandedHeightDp = ((Dp) objM6620boximpl2).m6636unboximpl();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 230292126, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged6 = $composer3.changed(expandedWidthDp) | $composer3.changed(density);
            Object objRememberedValue9 = $composer3.rememberedValue();
            if (zChanged6 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objValueOf3 = Float.valueOf(density.mo362toPx0680j_4(expandedWidthDp));
                $composer3.updateRememberedValue(objValueOf3);
            } else {
                objValueOf3 = objRememberedValue9;
            }
            final float expandedWidthPx = ((Number) objValueOf3).floatValue();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 230295936, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged7 = $composer3.changed(expandedHeightDp) | $composer3.changed(density);
            Object objRememberedValue10 = $composer3.rememberedValue();
            if (zChanged7 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                objValueOf4 = Float.valueOf(density.mo362toPx0680j_4(expandedHeightDp));
                $composer3.updateRememberedValue(objValueOf4);
            } else {
                objValueOf4 = objRememberedValue10;
            }
            float expandedHeightPx3 = ((Number) objValueOf4).floatValue();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 230299767, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged8 = $composer3.changed(density);
            Object objRememberedValue11 = $composer3.rememberedValue();
            if (zChanged8 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objValueOf5 = Float.valueOf(density.mo362toPx0680j_4(Dp.m6622constructorimpl(48)));
                $composer3.updateRememberedValue(objValueOf5);
            } else {
                objValueOf5 = objRememberedValue11;
            }
            float minBubbleSizePx = ((Number) objValueOf5).floatValue();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 230302143, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged9 = $composer3.changed(screenWidthPx);
            Object objRememberedValue12 = $composer3.rememberedValue();
            if (zChanged9 || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                objValueOf6 = Float.valueOf((screenWidthPx - minBubbleSizePx) - density.mo362toPx0680j_4(Dp.m6622constructorimpl(16)));
                $composer3.updateRememberedValue(objValueOf6);
            } else {
                objValueOf6 = objRememberedValue12;
            }
            float initialY = ((Number) objValueOf6).floatValue();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 230305730, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged10 = $composer3.changed(screenHeightPx3);
            Object objRememberedValue13 = $composer3.rememberedValue();
            if (zChanged10 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                objValueOf7 = Float.valueOf((screenHeightPx3 - minBubbleSizePx) - Float.valueOf(density.mo362toPx0680j_4(Dp.m6622constructorimpl(100))).floatValue());
                $composer3.updateRememberedValue(objValueOf7);
            } else {
                objValueOf7 = objRememberedValue13;
            }
            float initialY2 = ((Number) objValueOf7).floatValue();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 230309357, "CC(remember):DebugConsole.kt#9igjgp");
            Object objRememberedValue14 = $composer3.rememberedValue();
            if (objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                objMutableFloatStateOf = PrimitiveSnapshotStateKt.mutableFloatStateOf(initialY);
                $composer3.updateRememberedValue(objMutableFloatStateOf);
            } else {
                objMutableFloatStateOf = objRememberedValue14;
            }
            MutableFloatState offsetX$delegate3 = (MutableFloatState) objMutableFloatStateOf;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 230311341, "CC(remember):DebugConsole.kt#9igjgp");
            Object objRememberedValue15 = $composer3.rememberedValue();
            if (objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                objMutableFloatStateOf2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(initialY2);
                $composer3.updateRememberedValue(objMutableFloatStateOf2);
            } else {
                objMutableFloatStateOf2 = objRememberedValue15;
            }
            MutableFloatState offsetY$delegate3 = (MutableFloatState) objMutableFloatStateOf2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier.Companion companion = Modifier.INSTANCE;
            Object[] objArr = {Boolean.valueOf(DebugConsolePopup$lambda$3(isMinimized$delegate)), Float.valueOf(screenWidthPx), Float.valueOf(screenHeightPx3), Float.valueOf(expandedWidthPx), Float.valueOf(expandedHeightPx3)};
            ComposerKt.sourceInformationMarkerStart($composer3, 230317303, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged11 = $composer3.changed(minBubbleSizePx) | $composer3.changed(expandedWidthPx) | $composer3.changed(expandedHeightPx3) | $composer3.changed(screenWidthPx) | $composer3.changed(density) | $composer3.changed(screenHeightPx3);
            Object objRememberedValue16 = $composer3.rememberedValue();
            if (zChanged11) {
                screenHeightPx = screenHeightPx3;
            } else {
                screenHeightPx = screenHeightPx3;
                if (objRememberedValue16 != Composer.INSTANCE.getEmpty()) {
                    offsetY$delegate = offsetY$delegate3;
                    obj = objRememberedValue16;
                    offsetX$delegate = offsetX$delegate3;
                    expandedHeightPx2 = expandedHeightPx3;
                    expandedHeightPx = screenHeightPx;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                dragModifier = SuspendingPointerInputFilterKt.pointerInput((Modifier) companion, objArr, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) obj);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 230338716, "CC(remember):DebugConsole.kt#9igjgp");
                objRememberedValue = $composer3.rememberedValue();
                $composer2 = $composer3;
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    obj2 = new Function1() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj5) {
                            return DebugConsoleKt.DebugConsolePopup$lambda$29$lambda$28(offsetX$delegate, offsetY$delegate, (Density) obj5);
                        }
                    };
                    $composer3.updateRememberedValue(obj2);
                } else {
                    obj2 = objRememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierOffset = OffsetKt.offset(companion2, (Function1) obj2);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                offsetX$delegate2 = offsetX$delegate;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                offsetY$delegate2 = offsetY$delegate;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierOffset);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i5 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer2.useNode();
                }
                composerM3652constructorimpl = Updater.m3652constructorimpl($composer2);
                Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3652constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i6 = (i5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i7 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 164602613, "C:DebugConsole.kt#qonjpd");
                if (DebugConsolePopup$lambda$3(isMinimized$delegate)) {
                    $composer2.startReplaceGroup(164611199);
                    ComposerKt.sourceInformation($composer2, "102@4373L718");
                    ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer2, dragModifier);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i8 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        function1 = constructor2;
                        $composer2.createNode(function1);
                    } else {
                        function1 = constructor2;
                        $composer2.useNode();
                    }
                    composerM3652constructorimpl2 = Updater.m3652constructorimpl($composer2);
                    Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM3652constructorimpl2.getInserting()) {
                        composer2 = $composer2;
                    } else {
                        composer2 = $composer2;
                        if (!Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        }
                        Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i9 = (i8 >> 6) & 14;
                        Composer composer3 = composer2;
                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        int i10 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, -2103846154, "C104@4474L585,103@4420L657:DebugConsole.kt#qonjpd");
                        ComposerKt.sourceInformationMarkerStart(composer3, -2146074331, "CC(remember):DebugConsole.kt#9igjgp");
                        zChanged = composer3.changed(screenWidthPx) | composer3.changed(expandedWidthPx) | composer3.changed(density) | composer3.changed(expandedHeightPx) | composer3.changed(expandedHeightPx2);
                        objRememberedValue3 = composer3.rememberedValue();
                        if (zChanged) {
                            screenHeightPx2 = expandedHeightPx;
                        } else {
                            screenHeightPx2 = expandedHeightPx;
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                obj4 = objRememberedValue3;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            MinimizedDebugConsole((Function0) obj4, composer3, 0);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            $composer2.endReplaceGroup();
                            composer = $composer2;
                        }
                        final float screenHeightPx4 = screenHeightPx2;
                        final float expandedHeightPx4 = expandedHeightPx2;
                        obj4 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return DebugConsoleKt.DebugConsolePopup$lambda$37$lambda$34$lambda$33$lambda$32(screenWidthPx, expandedWidthPx, density, screenHeightPx4, expandedHeightPx4, offsetX$delegate2, offsetY$delegate2, isMinimized$delegate);
                            }
                        };
                        composer3.updateRememberedValue(obj4);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        MinimizedDebugConsole((Function0) obj4, composer3, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        $composer2.endReplaceGroup();
                        composer = $composer2;
                    }
                    composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11 = (i8 >> 6) & 14;
                    Composer composer4 = composer2;
                    ComposerKt.sourceInformationMarkerStart(composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i12 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer4, -2103846154, "C104@4474L585,103@4420L657:DebugConsole.kt#qonjpd");
                    ComposerKt.sourceInformationMarkerStart(composer4, -2146074331, "CC(remember):DebugConsole.kt#9igjgp");
                    zChanged = composer4.changed(screenWidthPx) | composer4.changed(expandedWidthPx) | composer4.changed(density) | composer4.changed(expandedHeightPx) | composer4.changed(expandedHeightPx2);
                    objRememberedValue3 = composer4.rememberedValue();
                    if (zChanged) {
                        screenHeightPx2 = expandedHeightPx;
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            obj4 = objRememberedValue3;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        MinimizedDebugConsole((Function0) obj4, composer4, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        $composer2.endReplaceGroup();
                        composer = $composer2;
                    } else {
                        screenHeightPx2 = expandedHeightPx;
                    }
                    final float screenHeightPx5 = screenHeightPx2;
                    final float expandedHeightPx5 = expandedHeightPx2;
                    obj4 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DebugConsoleKt.DebugConsolePopup$lambda$37$lambda$34$lambda$33$lambda$32(screenWidthPx, expandedWidthPx, density, screenHeightPx5, expandedHeightPx5, offsetX$delegate2, offsetY$delegate2, isMinimized$delegate);
                        }
                    };
                    composer4.updateRememberedValue(obj4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    MinimizedDebugConsole((Function0) obj4, composer4, 0);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    $composer2.endReplaceGroup();
                    composer = $composer2;
                } else {
                    $composer2.startReplaceGroup(165337777);
                    ComposerKt.sourceInformation($composer2, "119@5172L22,118@5121L220");
                    ComposerKt.sourceInformationMarkerStart($composer2, 420977331, "CC(remember):DebugConsole.kt#9igjgp");
                    objRememberedValue2 = $composer2.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        obj3 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return DebugConsoleKt.DebugConsolePopup$lambda$37$lambda$36$lambda$35(isMinimized$delegate);
                            }
                        };
                        $composer2.updateRememberedValue(obj3);
                    } else {
                        obj3 = objRememberedValue2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    m6941ExpandedDebugConsoleUuyPYSY((Function0) obj3, dragModifier, expandedWidthDp, expandedHeightDp, $composer2, 6, 0);
                    composer = $composer2;
                    composer.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            float screenHeightPx6 = screenHeightPx;
            DebugConsoleKt$DebugConsolePopup$dragModifier$1$1 debugConsoleKt$DebugConsolePopup$dragModifier$1$1 = new DebugConsoleKt$DebugConsolePopup$dragModifier$1$1(minBubbleSizePx, expandedWidthPx, expandedHeightPx3, screenWidthPx, density, screenHeightPx6, isMinimized$delegate, offsetX$delegate3, offsetY$delegate3, null);
            expandedHeightPx = screenHeightPx6;
            expandedHeightPx2 = expandedHeightPx3;
            density = density;
            offsetY$delegate = offsetY$delegate3;
            screenWidthPx = screenWidthPx;
            offsetX$delegate = offsetX$delegate3;
            obj = debugConsoleKt$DebugConsolePopup$dragModifier$1$1;
            $composer3.updateRememberedValue(obj);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            dragModifier = SuspendingPointerInputFilterKt.pointerInput((Modifier) companion, objArr, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) obj);
            Modifier.Companion companion3 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 230338716, "CC(remember):DebugConsole.kt#9igjgp");
            objRememberedValue = $composer3.rememberedValue();
            $composer2 = $composer3;
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                obj2 = new Function1() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj5) {
                        return DebugConsoleKt.DebugConsolePopup$lambda$29$lambda$28(offsetX$delegate, offsetY$delegate, (Density) obj5);
                    }
                };
                $composer3.updateRememberedValue(obj2);
            } else {
                obj2 = objRememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierOffset2 = OffsetKt.offset(companion3, (Function1) obj2);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            offsetX$delegate2 = offsetX$delegate;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            offsetY$delegate2 = offsetY$delegate;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer2, modifierOffset2);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i13 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            composerM3652constructorimpl = Updater.m3652constructorimpl($composer2);
            Updater.m3659setimpl(composerM3652constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3652constructorimpl.getInserting()) {
            }
            composerM3652constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM3652constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash3);
            Updater.m3659setimpl(composerM3652constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i14 = (i13 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            int i15 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 164602613, "C:DebugConsole.kt#qonjpd");
            if (DebugConsolePopup$lambda$3(isMinimized$delegate)) {
                $composer2.startReplaceGroup(164611199);
                ComposerKt.sourceInformation($composer2, "102@4373L718");
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap currentCompositionLocalMap4 = $composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer2, dragModifier);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i16 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor2;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer2.useNode();
                }
                composerM3652constructorimpl2 = Updater.m3652constructorimpl($composer2);
                Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM3652constructorimpl2.getInserting()) {
                    composer2 = $composer2;
                    if (!Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    }
                    Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i17 = (i16 >> 6) & 14;
                    Composer composer5 = composer2;
                    ComposerKt.sourceInformationMarkerStart(composer5, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    int i18 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer5, -2103846154, "C104@4474L585,103@4420L657:DebugConsole.kt#qonjpd");
                    ComposerKt.sourceInformationMarkerStart(composer5, -2146074331, "CC(remember):DebugConsole.kt#9igjgp");
                    zChanged = composer5.changed(screenWidthPx) | composer5.changed(expandedWidthPx) | composer5.changed(density) | composer5.changed(expandedHeightPx) | composer5.changed(expandedHeightPx2);
                    objRememberedValue3 = composer5.rememberedValue();
                    if (zChanged) {
                        screenHeightPx2 = expandedHeightPx;
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            obj4 = objRememberedValue3;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        MinimizedDebugConsole((Function0) obj4, composer5, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        $composer2.endReplaceGroup();
                        composer = $composer2;
                    } else {
                        screenHeightPx2 = expandedHeightPx;
                    }
                    final float screenHeightPx7 = screenHeightPx2;
                    final float expandedHeightPx6 = expandedHeightPx2;
                    obj4 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DebugConsoleKt.DebugConsolePopup$lambda$37$lambda$34$lambda$33$lambda$32(screenWidthPx, expandedWidthPx, density, screenHeightPx7, expandedHeightPx6, offsetX$delegate2, offsetY$delegate2, isMinimized$delegate);
                        }
                    };
                    composer5.updateRememberedValue(obj4);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    MinimizedDebugConsole((Function0) obj4, composer5, 0);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    $composer2.endReplaceGroup();
                    composer = $composer2;
                } else {
                    composer2 = $composer2;
                }
                composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash4);
                Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i19 = (i16 >> 6) & 14;
                Composer composer6 = composer2;
                ComposerKt.sourceInformationMarkerStart(composer6, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                int i110 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer6, -2103846154, "C104@4474L585,103@4420L657:DebugConsole.kt#qonjpd");
                ComposerKt.sourceInformationMarkerStart(composer6, -2146074331, "CC(remember):DebugConsole.kt#9igjgp");
                zChanged = composer6.changed(screenWidthPx) | composer6.changed(expandedWidthPx) | composer6.changed(density) | composer6.changed(expandedHeightPx) | composer6.changed(expandedHeightPx2);
                objRememberedValue3 = composer6.rememberedValue();
                if (zChanged) {
                    screenHeightPx2 = expandedHeightPx;
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        obj4 = objRememberedValue3;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    MinimizedDebugConsole((Function0) obj4, composer6, 0);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    $composer2.endReplaceGroup();
                    composer = $composer2;
                } else {
                    screenHeightPx2 = expandedHeightPx;
                }
                final float screenHeightPx8 = screenHeightPx2;
                final float expandedHeightPx7 = expandedHeightPx2;
                obj4 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DebugConsoleKt.DebugConsolePopup$lambda$37$lambda$34$lambda$33$lambda$32(screenWidthPx, expandedWidthPx, density, screenHeightPx8, expandedHeightPx7, offsetX$delegate2, offsetY$delegate2, isMinimized$delegate);
                    }
                };
                composer6.updateRememberedValue(obj4);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                MinimizedDebugConsole((Function0) obj4, composer6, 0);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                $composer2.endReplaceGroup();
                composer = $composer2;
            } else {
                $composer2.startReplaceGroup(165337777);
                ComposerKt.sourceInformation($composer2, "119@5172L22,118@5121L220");
                ComposerKt.sourceInformationMarkerStart($composer2, 420977331, "CC(remember):DebugConsole.kt#9igjgp");
                objRememberedValue2 = $composer2.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    obj3 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DebugConsoleKt.DebugConsolePopup$lambda$37$lambda$36$lambda$35(isMinimized$delegate);
                        }
                    };
                    $composer2.updateRememberedValue(obj3);
                } else {
                    obj3 = objRememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                m6941ExpandedDebugConsoleUuyPYSY((Function0) obj3, dragModifier, expandedWidthDp, expandedHeightDp, $composer2, 6, 0);
                composer = $composer2;
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj5, Object obj6) {
                    return DebugConsoleKt.DebugConsolePopup$lambda$38($changed, (Composer) obj5, ((Integer) obj6).intValue());
                }
            });
        }
    }

    private static final boolean DebugConsolePopup$lambda$0(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DebugConsolePopup$lambda$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void DebugConsolePopup$lambda$4(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float DebugConsolePopup$lambda$22(MutableFloatState $offsetX$delegate) {
        return $offsetX$delegate.getFloatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float DebugConsolePopup$lambda$25(MutableFloatState $offsetY$delegate) {
        return $offsetY$delegate.getFloatValue();
    }

    static final IntOffset DebugConsolePopup$lambda$29$lambda$28(MutableFloatState $offsetX$delegate, MutableFloatState $offsetY$delegate, Density offset) {
        Intrinsics.checkNotNullParameter(offset, "$this$offset");
        return IntOffset.m6741boximpl(IntOffsetKt.IntOffset(MathKt.roundToInt(DebugConsolePopup$lambda$22($offsetX$delegate)), MathKt.roundToInt(DebugConsolePopup$lambda$25($offsetY$delegate))));
    }

    static final Unit DebugConsolePopup$lambda$37$lambda$34$lambda$33$lambda$32(float $screenWidthPx, float $expandedWidthPx, Density $density, float $screenHeightPx, float $expandedHeightPx, MutableFloatState $offsetX$delegate, MutableFloatState $offsetY$delegate, MutableState $isMinimized$delegate) {
        float maxExpandedX = RangesKt.coerceAtLeast(($screenWidthPx - $expandedWidthPx) - $density.mo362toPx0680j_4(Dp.m6622constructorimpl(8)), 0.0f);
        float maxExpandedY = RangesKt.coerceAtLeast(($screenHeightPx - $expandedHeightPx) - $density.mo362toPx0680j_4(Dp.m6622constructorimpl(8)), 0.0f);
        if (DebugConsolePopup$lambda$22($offsetX$delegate) > maxExpandedX) {
            $offsetX$delegate.setFloatValue(maxExpandedX);
        }
        if (DebugConsolePopup$lambda$25($offsetY$delegate) > maxExpandedY) {
            $offsetY$delegate.setFloatValue(maxExpandedY);
        }
        DebugConsolePopup$lambda$4($isMinimized$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit DebugConsolePopup$lambda$37$lambda$36$lambda$35(MutableState $isMinimized$delegate) {
        DebugConsolePopup$lambda$4($isMinimized$delegate, true);
        return Unit.INSTANCE;
    }

    public static final void MinimizedDebugConsole(final Function0<Unit> onExpand, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(onExpand, "onExpand");
        Composer $composer2 = $composer.startRestartGroup(1328445089);
        ComposerKt.sourceInformation($composer2, "C(MinimizedDebugConsole)132@5509L11,130@5427L337:DebugConsole.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(onExpand) ? 4 : 2;
        }
        if (($dirty & 3) == 2 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1328445089, $dirty, -1, "com.example.ui.components.MinimizedDebugConsole (DebugConsole.kt:129)");
            }
            SurfaceKt.m2546Surfaceo_FOJdg(onExpand, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(48)), false, RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(24)), MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getPrimaryContainer(), 0L, 0.0f, 0.0f, null, null, ComposableSingletons$DebugConsoleKt.INSTANCE.m6934getLambda$446005588$app(), $composer2, ($dirty & 14) | 48, 6, 996);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DebugConsoleKt.MinimizedDebugConsole$lambda$39(onExpand, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:86:0x024f  */
    /* JADX WARN: Code duplicated, block: B:87:0x025a  */
    /* JADX WARN: Code duplicated, block: B:90:0x027c  */
    /* JADX WARN: Code duplicated, block: B:91:0x0289  */
    /* JADX WARN: Code duplicated, block: B:94:0x02ee  */
    /* JADX INFO: renamed from: ExpandedDebugConsole-UuyPYSY, reason: not valid java name */
    public static final void m6941ExpandedDebugConsoleUuyPYSY(final Function0<Unit> onMinimize, Modifier dragModifier, float widthDp, float heightDp, Composer $composer, final int $changed, final int i) {
        Modifier modifier;
        float f;
        float f2;
        Modifier.Companion dragModifier2;
        float widthDp2;
        float heightDp2;
        Object companion;
        Object objMutableStateOf$default;
        LazyListState listState;
        Object objRememberedValue;
        Object objMutableInteractionSource;
        Object objRememberedValue2;
        Object obj;
        Composer $composer2;
        final Modifier dragModifier3;
        final float widthDp3;
        final float heightDp3;
        Intrinsics.checkNotNullParameter(onMinimize, "onMinimize");
        Composer $composer3 = $composer.startRestartGroup(1433694973);
        ComposerKt.sourceInformation($composer3, "C(ExpandedDebugConsole)P(2!1,3:c#ui.unit.Dp,1:c#ui.unit.Dp)150@6010L7,151@6039L44,152@6121L16,153@6186L16,154@6242L16,156@6284L31,157@6336L23,159@6391L107,159@6365L133,165@6530L165,165@6504L191,180@6966L39,182@7068L2,184@7091L7346,172@6701L7736:DebugConsole.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(onMinimize) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier = dragModifier;
        } else if (($changed & 48) == 0) {
            modifier = dragModifier;
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        } else {
            modifier = dragModifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            f = widthDp;
        } else if (($changed & 384) == 0) {
            f = widthDp;
            $dirty |= $composer3.changed(f) ? 256 : 128;
        } else {
            f = widthDp;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            f2 = heightDp;
        } else if (($changed & 3072) == 0) {
            f2 = heightDp;
            $dirty |= $composer3.changed(f2) ? 2048 : 1024;
        } else {
            f2 = heightDp;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            dragModifier3 = modifier;
            widthDp3 = f;
            heightDp3 = f2;
            $composer2 = $composer3;
        } else {
            if (i2 != 0) {
                dragModifier2 = Modifier.INSTANCE;
            } else {
                dragModifier2 = modifier;
            }
            if (i3 == 0) {
                widthDp2 = f;
            } else {
                widthDp2 = Dp.m6622constructorimpl(340);
            }
            if (i4 == 0) {
                heightDp2 = f2;
            } else {
                heightDp2 = Dp.m6622constructorimpl(450);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1433694973, $dirty2, -1, "com.example.ui.components.ExpandedDebugConsole (DebugConsole.kt:149)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer3, -903624247, "CC(remember):DebugConsole.kt#9igjgp");
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                companion = BleManager.INSTANCE.getInstance(context);
                $composer3.updateRememberedValue(companion);
            } else {
                companion = objRememberedValue3;
            }
            final BleManager bleManager = (BleManager) companion;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final State logs$delegate = SnapshotStateKt.collectAsState(bleManager.getDebugLogs(), null, $composer3, 0, 1);
            final State connState$delegate = SnapshotStateKt.collectAsState(bleManager.getConnectionState(), null, $composer3, 0, 1);
            final State rssi$delegate = SnapshotStateKt.collectAsState(bleManager.getCurrentRssi(), null, $composer3, 0, 1);
            ComposerKt.sourceInformationMarkerStart($composer3, -903616420, "CC(remember):DebugConsole.kt#9igjgp");
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
            } else {
                objMutableStateOf$default = objRememberedValue4;
            }
            final MutableState customPacket$delegate = (MutableState) objMutableStateOf$default;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LazyListState listState2 = LazyListStateKt.rememberLazyListState(0, 0, $composer3, 0, 3);
            Integer numValueOf = Integer.valueOf(ExpandedDebugConsole_UuyPYSY$lambda$41(logs$delegate).size());
            ComposerKt.sourceInformationMarkerStart($composer3, -903612920, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged = $composer3.changed(logs$delegate) | $composer3.changed(listState2);
            DebugConsoleKt$ExpandedDebugConsole$1$1 debugConsoleKt$ExpandedDebugConsole$1$1RememberedValue = $composer3.rememberedValue();
            if (zChanged || debugConsoleKt$ExpandedDebugConsole$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                debugConsoleKt$ExpandedDebugConsole$1$1RememberedValue = new DebugConsoleKt$ExpandedDebugConsole$1$1(listState2, logs$delegate, null);
                $composer3.updateRememberedValue(debugConsoleKt$ExpandedDebugConsole$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(numValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) debugConsoleKt$ExpandedDebugConsole$1$1RememberedValue, $composer3, 0);
            BleManager.ConnectionState connectionStateExpandedDebugConsole_UuyPYSY$lambda$42 = ExpandedDebugConsole_UuyPYSY$lambda$42(connState$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, -903608414, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged2 = $composer3.changed(connState$delegate) | $composer3.changedInstance(bleManager);
            DebugConsoleKt$ExpandedDebugConsole$2$1 debugConsoleKt$ExpandedDebugConsole$2$1RememberedValue = $composer3.rememberedValue();
            if (!zChanged2) {
                listState = listState2;
                if (debugConsoleKt$ExpandedDebugConsole$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(connectionStateExpandedDebugConsole_UuyPYSY$lambda$42, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) debugConsoleKt$ExpandedDebugConsole$2$1RememberedValue, $composer3, 0);
                RoundedCornerShape roundedCornerShapeM953RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(12));
                long jColor = ColorKt.Color(4280163870L);
                float fM6622constructorimpl = Dp.m6622constructorimpl(0);
                Modifier modifierM701height3ABfNKs = SizeKt.m701height3ABfNKs(SizeKt.m720width3ABfNKs(Modifier.INSTANCE, widthDp2), heightDp2);
                ComposerKt.sourceInformationMarkerStart($composer3, -903594588, "CC(remember):DebugConsole.kt#9igjgp");
                objRememberedValue = $composer3.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(objMutableInteractionSource);
                } else {
                    objMutableInteractionSource = objRememberedValue;
                }
                MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objMutableInteractionSource;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -903591361, "CC(remember):DebugConsole.kt#9igjgp");
                objRememberedValue2 = $composer3.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    obj = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    $composer3.updateRememberedValue(obj);
                } else {
                    obj = objRememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierM255clickableO2vRcR0 = ClickableKt.m255clickableO2vRcR0(modifierM701height3ABfNKs, mutableInteractionSource, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) obj);
                RoundedCornerShape roundedCornerShape = roundedCornerShapeM953RoundedCornerShape0680j_4;
                final Modifier dragModifier4 = dragModifier2;
                final LazyListState listState3 = listState;
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-473651688, true, new Function2() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$68(dragModifier4, listState3, logs$delegate, onMinimize, connState$delegate, rssi$delegate, bleManager, context, customPacket$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer3, 54);
                float widthDp4 = widthDp2;
                float heightDp4 = heightDp2;
                $composer2 = $composer3;
                SurfaceKt.m2543SurfaceT9BRK9s(modifierM255clickableO2vRcR0, roundedCornerShape, jColor, 0L, 0.0f, fM6622constructorimpl, null, composableLambdaRememberComposableLambda, $composer2, 12779904, 88);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                dragModifier3 = dragModifier4;
                widthDp3 = widthDp4;
                heightDp3 = heightDp4;
            } else {
                listState = listState2;
            }
            debugConsoleKt$ExpandedDebugConsole$2$1RememberedValue = new DebugConsoleKt$ExpandedDebugConsole$2$1(bleManager, connState$delegate, null);
            $composer3.updateRememberedValue(debugConsoleKt$ExpandedDebugConsole$2$1RememberedValue);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(connectionStateExpandedDebugConsole_UuyPYSY$lambda$42, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) debugConsoleKt$ExpandedDebugConsole$2$1RememberedValue, $composer3, 0);
            RoundedCornerShape roundedCornerShapeM953RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(12));
            long jColor2 = ColorKt.Color(4280163870L);
            float fM6622constructorimpl2 = Dp.m6622constructorimpl(0);
            Modifier modifierM701height3ABfNKs2 = SizeKt.m701height3ABfNKs(SizeKt.m720width3ABfNKs(Modifier.INSTANCE, widthDp2), heightDp2);
            ComposerKt.sourceInformationMarkerStart($composer3, -903594588, "CC(remember):DebugConsole.kt#9igjgp");
            objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(objMutableInteractionSource);
            } else {
                objMutableInteractionSource = objRememberedValue;
            }
            MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) objMutableInteractionSource;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -903591361, "CC(remember):DebugConsole.kt#9igjgp");
            objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer3.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM255clickableO2vRcR1 = ClickableKt.m255clickableO2vRcR0(modifierM701height3ABfNKs2, mutableInteractionSource2, null, (24 & 4) != 0, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (Function0) obj);
            RoundedCornerShape roundedCornerShape2 = roundedCornerShapeM953RoundedCornerShape0680j_5;
            final Modifier dragModifier5 = dragModifier2;
            final LazyListState listState4 = listState;
            ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-473651688, true, new Function2() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$68(dragModifier5, listState4, logs$delegate, onMinimize, connState$delegate, rssi$delegate, bleManager, context, customPacket$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54);
            float widthDp5 = widthDp2;
            float heightDp5 = heightDp2;
            $composer2 = $composer3;
            SurfaceKt.m2543SurfaceT9BRK9s(modifierM255clickableO2vRcR1, roundedCornerShape2, jColor2, 0L, 0.0f, fM6622constructorimpl2, null, composableLambdaRememberComposableLambda2, $composer2, 12779904, 88);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            dragModifier3 = dragModifier5;
            widthDp3 = widthDp5;
            heightDp3 = heightDp5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$69(onMinimize, dragModifier3, widthDp3, heightDp3, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<BleManager.DebugLog> ExpandedDebugConsole_UuyPYSY$lambda$41(State<? extends List<BleManager.DebugLog>> state) {
        return (List) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BleManager.ConnectionState ExpandedDebugConsole_UuyPYSY$lambda$42(State<? extends BleManager.ConnectionState> state) {
        return (BleManager.ConnectionState) state.getValue();
    }

    private static final int ExpandedDebugConsole_UuyPYSY$lambda$43(State<Integer> state) {
        return ((Number) state.getValue()).intValue();
    }

    private static final String ExpandedDebugConsole_UuyPYSY$lambda$45(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:120:0x0a0f  */
    /* JADX WARN: Code duplicated, block: B:121:0x0a1d  */
    /* JADX WARN: Code duplicated, block: B:136:0x0b34  */
    /* JADX WARN: Code duplicated, block: B:139:0x0bbe  */
    static final Unit ExpandedDebugConsole_UuyPYSY$lambda$68(Modifier $dragModifier, LazyListState $listState, final State $logs$delegate, Function0 $onMinimize, State $connState$delegate, State $rssi$delegate, final BleManager $bleManager, final Context $context, final MutableState $customPacket$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        String str;
        Object obj;
        Function0<ComposeUiNode> function4;
        Composer composer;
        Composer composer2;
        Object objRememberedValue;
        boolean zChangedInstance;
        Object objRememberedValue2;
        boolean zChangedInstance2;
        Object obj2;
        ComposerKt.sourceInformation($composer, "C185@7101L7330:DebugConsole.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-473651688, $changed, -1, "com.example.ui.components.ExpandedDebugConsole.<anonymous> (DebugConsole.kt:185)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((6 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1674215383, "C187@7179L990,213@8209L1420,247@10004L2132,239@9687L2449,295@12176L2245:DebugConsole.kt#qonjpd");
            Modifier modifierM673paddingqDBjuR0 = PaddingKt.m673paddingqDBjuR0(BackgroundKt.m225backgroundbw27NRU$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ColorKt.Color(4281150765L), null, 2, null), Dp.m6622constructorimpl(12), Dp.m6622constructorimpl(4), Dp.m6622constructorimpl(4), Dp.m6622constructorimpl(4));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierM673paddingqDBjuR0);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m3659setimpl(composerM3652constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3652constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3652constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3659setimpl(composerM3652constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            int i6 = ((384 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 452734600, "C194@7486L473,207@7976L179:DebugConsole.kt#qonjpd");
            Modifier modifierM672paddingVpY3zN4$default = PaddingKt.m672paddingVpY3zN4$default(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null).then($dragModifier), 0.0f, Dp.m6622constructorimpl(4), 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM672paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m3659setimpl(composerM3652constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM3652constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM3652constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m3659setimpl(composerM3652constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i9 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1121445733, "C203@7838L10,200@7701L240:DebugConsole.kt#qonjpd");
            TextKt.m2693Text4IGK_g("BLE Terminal", (Modifier) null, Color.INSTANCE.m4196getWhite0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 390, 0, 65466);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton($onMinimize, SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(36)), false, null, null, ComposableSingletons$DebugConsoleKt.INSTANCE.m6933getLambda$1816108031$app(), $composer, 196656, 28);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierM671paddingVpY3zN4 = PaddingKt.m671paddingVpY3zN4(BackgroundKt.m225backgroundbw27NRU$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ColorKt.Color(4281545523L), null, 2, null).then($dragModifier), Dp.m6622constructorimpl(12), Dp.m6622constructorimpl(6));
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(spaceBetween, centerVertically2, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierM671paddingVpY3zN4);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((432 << 3) & 112) << 6) & 896) | 6;
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
            Composer composerM3652constructorimpl4 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM3652constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM3652constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM3652constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m3659setimpl(composerM3652constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i12 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -106283638, "C231@9311L10,231@9251L117:DebugConsole.kt#qonjpd");
            BleManager.ConnectionState connectionStateExpandedDebugConsole_UuyPYSY$lambda$42 = ExpandedDebugConsole_UuyPYSY$lambda$42($connState$delegate);
            if (connectionStateExpandedDebugConsole_UuyPYSY$lambda$42 instanceof BleManager.ConnectionState.Connected) {
                str = "Connected: " + ((BleManager.ConnectionState.Connected) connectionStateExpandedDebugConsole_UuyPYSY$lambda$42).getAddress();
            } else if (connectionStateExpandedDebugConsole_UuyPYSY$lambda$42 instanceof BleManager.ConnectionState.Connecting) {
                str = "Connecting...";
            } else if (connectionStateExpandedDebugConsole_UuyPYSY$lambda$42 instanceof BleManager.ConnectionState.Disconnected) {
                str = "Disconnected";
            } else if (connectionStateExpandedDebugConsole_UuyPYSY$lambda$42 instanceof BleManager.ConnectionState.Error) {
                str = "Error";
            } else {
                str = connectionStateExpandedDebugConsole_UuyPYSY$lambda$42 instanceof BleManager.ConnectionState.Scanning ? "Scanning..." : "Unknown";
            }
            TextKt.m2693Text4IGK_g(str, (Modifier) null, ColorKt.Color(ExpandedDebugConsole_UuyPYSY$lambda$42($connState$delegate) instanceof BleManager.ConnectionState.Connected ? 4283215696L : 4294947584L), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 0, 0, 65466);
            if (ExpandedDebugConsole_UuyPYSY$lambda$42($connState$delegate) instanceof BleManager.ConnectionState.Connected) {
                $composer.startReplaceGroup(-105464867);
                ComposerKt.sourceInformation($composer, "234@9540L10,234@9479L118");
                TextKt.m2693Text4IGK_g(ExpandedDebugConsole_UuyPYSY$lambda$43($rssi$delegate) + " dBm", (Modifier) null, Color.INSTANCE.m4196getWhite0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 384, 0, 65466);
            } else {
                $composer.startReplaceGroup(-114852101);
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierM670padding3ABfNKs = PaddingKt.m670padding3ABfNKs(BackgroundKt.m225backgroundbw27NRU$default(SizeKt.fillMaxWidth$default(ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 1, null), ColorKt.Color(4279374354L), null, 2, null), Dp.m6622constructorimpl(8));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM550spacedBy0680j_4 = Arrangement.INSTANCE.m550spacedBy0680j_4(Dp.m6622constructorimpl(6));
            ComposerKt.sourceInformationMarkerStart($composer, 331186902, "CC(remember):DebugConsole.kt#9igjgp");
            boolean zChanged = $composer.changed($logs$delegate);
            Object objRememberedValue3 = $composer.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                obj = new Function1() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$59$lambda$58($logs$delegate, (LazyListScope) obj3);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            LazyDslKt.LazyColumn(modifierM670padding3ABfNKs, $listState, null, false, horizontalOrVerticalM550spacedBy0680j_4, null, null, false, (Function1) obj, $composer, 24576, 236);
            Modifier modifierM670padding3ABfNKs2 = PaddingKt.m670padding3ABfNKs(BackgroundKt.m225backgroundbw27NRU$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ColorKt.Color(4281150765L), null, 2, null), Dp.m6622constructorimpl(8));
            Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifierM670padding3ABfNKs2);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            int i13 = ((((390 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function4 = constructor5;
                $composer.createNode(function4);
            } else {
                function4 = constructor5;
                $composer.useNode();
            }
            Composer composerM3652constructorimpl5 = Updater.m3652constructorimpl($composer);
            Updater.m3659setimpl(composerM3652constructorimpl5, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3659setimpl(composerM3652constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3652constructorimpl5.getInserting()) {
                composer = $composer;
            } else {
                composer = $composer;
                if (!Intrinsics.areEqual(composerM3652constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                }
                Updater.m3659setimpl(composerM3652constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = (i13 >> 6) & 14;
                composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                int i15 = ((390 >> 6) & 112) | 6;
                RowScope rowScope2 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer2, 1403768009, "C308@12809L7,309@12944L300,304@12532L21,302@12435L882,317@13334L39,319@13432L219,318@13390L565,331@13972L39,333@14070L27,332@14028L379:DebugConsole.kt#qonjpd");
                String strExpandedDebugConsole_UuyPYSY$lambda$45 = ExpandedDebugConsole_UuyPYSY$lambda$45($customPacket$delegate);
                Modifier modifierM701height3ABfNKs = SizeKt.m701height3ABfNKs(RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m6622constructorimpl(50));
                ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart(composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer2.consume(localTextStyle);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                TextStyle textStyleM6101copyp1EtxEg$default = TextStyle.m6101copyp1EtxEg$default((TextStyle) objConsume, Color.INSTANCE.m4196getWhite0d7_KjU(), TextUnitKt.getSp(12), null, null, null, FontFamily.INSTANCE.getMonospace(), null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777180, null);
                TextFieldColors textFieldColorsM2674colors0hiis_0 = TextFieldDefaults.INSTANCE.m2674colors0hiis_0(0L, 0L, 0L, 0L, ColorKt.Color(4280163870L), ColorKt.Color(4280163870L), 0L, 0L, 0L, 0L, null, Color.INSTANCE.m4194getTransparent0d7_KjU(), Color.INSTANCE.m4194getTransparent0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer2, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                RoundedCornerShape roundedCornerShapeM953RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(25));
                ComposerKt.sourceInformationMarkerStart(composer2, -924547333, "CC(remember):DebugConsole.kt#9igjgp");
                objRememberedValue = composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$66$lambda$61$lambda$60($customPacket$delegate, (String) obj3);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                OutlinedTextFieldKt.OutlinedTextField(strExpandedDebugConsole_UuyPYSY$lambda$45, (Function1<? super String, Unit>) objRememberedValue, modifierM701height3ABfNKs, false, false, textStyleM6101copyp1EtxEg$default, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DebugConsoleKt.INSTANCE.m6936getLambda$526565267$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM953RoundedCornerShape0680j_4, textFieldColorsM2674colors0hiis_0, composer2, 12582960, 12582912, 0, 1965912);
                SpacerKt.Spacer(SizeKt.m720width3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(8)), composer2, 6);
                ComposerKt.sourceInformationMarkerStart(composer2, -924518335, "CC(remember):DebugConsole.kt#9igjgp");
                zChangedInstance = composer2.changedInstance($bleManager);
                objRememberedValue2 = composer2.rememberedValue();
                if (!zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$66$lambda$63$lambda$62($bleManager, $customPacket$delegate);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                IconButtonKt.IconButton((Function0) objRememberedValue2, BackgroundKt.m224backgroundbw27NRU(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(40)), ColorKt.Color(4281236786L), RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(20))), false, null, null, ComposableSingletons$DebugConsoleKt.INSTANCE.getLambda$1956307401$app(), composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 28);
                SpacerKt.Spacer(SizeKt.m720width3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(6)), composer2, 6);
                ComposerKt.sourceInformationMarkerStart(composer2, -924498111, "CC(remember):DebugConsole.kt#9igjgp");
                zChangedInstance2 = composer2.changedInstance($context) | composer2.changed($logs$delegate);
                Object objRememberedValue4 = composer2.rememberedValue();
                if (!zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    obj2 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$66$lambda$65$lambda$64($context, $logs$delegate);
                        }
                    };
                    composer2.updateRememberedValue(obj2);
                } else {
                    obj2 = objRememberedValue4;
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                IconButtonKt.IconButton((Function0) obj2, BackgroundKt.m224backgroundbw27NRU(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(40)), ColorKt.Color(4282532418L), RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(20))), false, null, null, ComposableSingletons$DebugConsoleKt.INSTANCE.m6935getLambda$457536590$app(), composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 28);
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
            composerM3652constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
            composerM3652constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            Updater.m3659setimpl(composerM3652constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            int i16 = (i13 >> 6) & 14;
            composer2 = composer;
            ComposerKt.sourceInformationMarkerStart(composer2, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            int i17 = ((390 >> 6) & 112) | 6;
            RowScope rowScope3 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer2, 1403768009, "C308@12809L7,309@12944L300,304@12532L21,302@12435L882,317@13334L39,319@13432L219,318@13390L565,331@13972L39,333@14070L27,332@14028L379:DebugConsole.kt#qonjpd");
            String strExpandedDebugConsole_UuyPYSY$lambda$46 = ExpandedDebugConsole_UuyPYSY$lambda$45($customPacket$delegate);
            Modifier modifierM701height3ABfNKs2 = SizeKt.m701height3ABfNKs(RowScope.weight$default(rowScope3, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m6622constructorimpl(50));
            ProvidableCompositionLocal<TextStyle> localTextStyle2 = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart(composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composer2.consume(localTextStyle2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            TextStyle textStyleM6101copyp1EtxEg$default2 = TextStyle.m6101copyp1EtxEg$default((TextStyle) objConsume2, Color.INSTANCE.m4196getWhite0d7_KjU(), TextUnitKt.getSp(12), null, null, null, FontFamily.INSTANCE.getMonospace(), null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777180, null);
            TextFieldColors textFieldColorsM2674colors0hiis_1 = TextFieldDefaults.INSTANCE.m2674colors0hiis_0(0L, 0L, 0L, 0L, ColorKt.Color(4280163870L), ColorKt.Color(4280163870L), 0L, 0L, 0L, 0L, null, Color.INSTANCE.m4194getTransparent0d7_KjU(), Color.INSTANCE.m4194getTransparent0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer2, 221184, 432, 0, 0, 3072, 2147477455, 4095);
            RoundedCornerShape roundedCornerShapeM953RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(25));
            ComposerKt.sourceInformationMarkerStart(composer2, -924547333, "CC(remember):DebugConsole.kt#9igjgp");
            objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$66$lambda$61$lambda$60($customPacket$delegate, (String) obj3);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            OutlinedTextFieldKt.OutlinedTextField(strExpandedDebugConsole_UuyPYSY$lambda$46, (Function1<? super String, Unit>) objRememberedValue, modifierM701height3ABfNKs2, false, false, textStyleM6101copyp1EtxEg$default2, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DebugConsoleKt.INSTANCE.m6936getLambda$526565267$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM953RoundedCornerShape0680j_5, textFieldColorsM2674colors0hiis_1, composer2, 12582960, 12582912, 0, 1965912);
            SpacerKt.Spacer(SizeKt.m720width3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(8)), composer2, 6);
            ComposerKt.sourceInformationMarkerStart(composer2, -924518335, "CC(remember):DebugConsole.kt#9igjgp");
            zChangedInstance = composer2.changedInstance($bleManager);
            objRememberedValue2 = composer2.rememberedValue();
            if (!zChangedInstance) {
            }
            objRememberedValue2 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$66$lambda$63$lambda$62($bleManager, $customPacket$delegate);
                }
            };
            composer2.updateRememberedValue(objRememberedValue2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            IconButtonKt.IconButton((Function0) objRememberedValue2, BackgroundKt.m224backgroundbw27NRU(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(40)), ColorKt.Color(4281236786L), RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(20))), false, null, null, ComposableSingletons$DebugConsoleKt.INSTANCE.getLambda$1956307401$app(), composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 28);
            SpacerKt.Spacer(SizeKt.m720width3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(6)), composer2, 6);
            ComposerKt.sourceInformationMarkerStart(composer2, -924498111, "CC(remember):DebugConsole.kt#9igjgp");
            zChangedInstance2 = composer2.changedInstance($context) | composer2.changed($logs$delegate);
            Object objRememberedValue5 = composer2.rememberedValue();
            if (zChangedInstance2) {
                obj2 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$66$lambda$65$lambda$64($context, $logs$delegate);
                    }
                };
                composer2.updateRememberedValue(obj2);
            } else {
                obj2 = new Function0() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DebugConsoleKt.ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$66$lambda$65$lambda$64($context, $logs$delegate);
                    }
                };
                composer2.updateRememberedValue(obj2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            IconButtonKt.IconButton((Function0) obj2, BackgroundKt.m224backgroundbw27NRU(SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(40)), ColorKt.Color(4282532418L), RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(20))), false, null, null, ComposableSingletons$DebugConsoleKt.INSTANCE.m6935getLambda$457536590$app(), composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 28);
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
        return Unit.INSTANCE;
    }

    static final Unit ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$59$lambda$58(State $logs$delegate, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final List<BleManager.DebugLog> listExpandedDebugConsole_UuyPYSY$lambda$41 = ExpandedDebugConsole_UuyPYSY$lambda$41($logs$delegate);
        final Function1 function1 = new Function1() { // from class: com.example.ui.components.DebugConsoleKt$ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$59$lambda$58$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((BleManager.DebugLog) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(BleManager.DebugLog debugLog) {
                return null;
            }
        };
        LazyColumn.items(listExpandedDebugConsole_UuyPYSY$lambda$41.size(), null, new Function1<Integer, Object>() { // from class: com.example.ui.components.DebugConsoleKt$ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$59$lambda$58$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke(listExpandedDebugConsole_UuyPYSY$lambda$41.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.components.DebugConsoleKt$ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$59$lambda$58$$inlined$items$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                Alignment centerStart;
                long jColor;
                Function0<ComposeUiNode> function0;
                Function0<ComposeUiNode> function2;
                ComposerKt.sourceInformation($composer, "C152@7074L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if (($dirty & 147) == 146 && $composer.getSkipping()) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-632812321, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                }
                int i = $dirty & 14;
                BleManager.DebugLog debugLog = (BleManager.DebugLog) listExpandedDebugConsole_UuyPYSY$lambda$41.get(it);
                $composer.startReplaceGroup(1064326591);
                ComposerKt.sourceInformation($composer, "C*264@10767L1337:DebugConsole.kt#qonjpd");
                String str = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US).format(new Date(debugLog.getTimestamp()));
                if (debugLog.isTx()) {
                    centerStart = Alignment.INSTANCE.getCenterEnd();
                } else {
                    centerStart = debugLog.isRx() ? Alignment.INSTANCE.getCenterStart() : Alignment.INSTANCE.getCenter();
                }
                if (debugLog.isError()) {
                    jColor = ColorKt.Color(4289724448L);
                } else if (debugLog.isTx()) {
                    jColor = ColorKt.Color(4281236786L);
                } else {
                    jColor = debugLog.isRx() ? ColorKt.Color(4279592384L) : ColorKt.Color(4282532418L);
                }
                long j = jColor;
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(centerStart, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i2 = ((((6 << 3) & 112) << 6) & 896) | 6;
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
                int i3 = (i2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1283066283, "C268@10933L1149:DebugConsole.kt#qonjpd");
                Modifier modifierM671paddingVpY3zN4 = PaddingKt.m671paddingVpY3zN4(BackgroundKt.m225backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m953RoundedCornerShape0680j_4(Dp.m6622constructorimpl(8))), j, null, 2, null), Dp.m6622constructorimpl(10), Dp.m6622constructorimpl(6));
                Alignment.Horizontal end = debugLog.isTx() ? Alignment.INSTANCE.getEnd() : Alignment.INSTANCE.getStart();
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), end, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierM671paddingVpY3zN4);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i5 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor2;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor2;
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
                int i6 = (i5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i7 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1105285712, "C275@11340L252,282@11751L305:DebugConsole.kt#qonjpd");
                Intrinsics.checkNotNull(str);
                TextKt.m2693Text4IGK_g(str, (Modifier) null, Color.INSTANCE.m4191getLightGray0d7_KjU(), TextUnitKt.getSp(8), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3456, 0, 130994);
                TextKt.m2693Text4IGK_g(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(debugLog.getMessage(), (CharSequence) "RX: "), (CharSequence) "TX: "), (CharSequence) "Sent raw to RX: "), (Modifier) null, Color.INSTANCE.m4196getWhite0d7_KjU(), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) FontFamily.INSTANCE.getMonospace(), 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(14), 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3456, 6, 129970);
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
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    static final Unit ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$66$lambda$61$lambda$60(MutableState $customPacket$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $customPacket$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$66$lambda$63$lambda$62(BleManager $bleManager, MutableState $customPacket$delegate) {
        if (ExpandedDebugConsole_UuyPYSY$lambda$45($customPacket$delegate).length() > 0) {
            $bleManager.sendRawPacket(ExpandedDebugConsole_UuyPYSY$lambda$45($customPacket$delegate));
            $customPacket$delegate.setValue("");
        }
        return Unit.INSTANCE;
    }

    static final Unit ExpandedDebugConsole_UuyPYSY$lambda$68$lambda$67$lambda$66$lambda$65$lambda$64(Context $context, State $logs$delegate) {
        saveLogs($context, ExpandedDebugConsole_UuyPYSY$lambda$41($logs$delegate));
        return Unit.INSTANCE;
    }

    private static final void saveLogs(Context context, List<BleManager.DebugLog> list) {
        String str;
        try {
            String fileName = "Calis_Debug_Log_" + System.currentTimeMillis() + ".txt";
            File dir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, fileName);
            FileOutputStream fos = new FileOutputStream(file);
            StringBuilder sb = new StringBuilder();
            for (BleManager.DebugLog debugLog : list) {
                File dir2 = dir;
                File file2 = file;
                String str2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).format(new Date(debugLog.getTimestamp()));
                if (debugLog.isError()) {
                    str = "[ERROR]";
                } else if (debugLog.isRx()) {
                    str = "[RX]";
                } else {
                    str = debugLog.isTx() ? "[TX]" : "[INFO]";
                }
                sb.append(str2 + ServerSentEventKt.SPACE + str + ServerSentEventKt.SPACE + debugLog.getMessage() + "\n");
                dir = dir2;
                file = file2;
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            byte[] bytes = string.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            fos.write(bytes);
            fos.close();
            Toast.makeText(context, "Log saved to Documents: " + fileName, 1).show();
        } catch (Exception e) {
            Toast.makeText(context, "Failed to save log: " + e.getMessage(), 0).show();
        }
    }

    public static final void VerboseLogOverlay(Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-427502371);
        ComposerKt.sourceInformation($composer2, "C(VerboseLogOverlay):DebugConsole.kt#qonjpd");
        if ($changed == 0 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-427502371, $changed, -1, "com.example.ui.components.VerboseLogOverlay (DebugConsole.kt:376)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.DebugConsoleKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DebugConsoleKt.VerboseLogOverlay$lambda$71($changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
