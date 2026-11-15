package com.example.ui.screens;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AnimatedDottedLine.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"AnimatedDottedLine", "", "isActive", "", "modifier", "Landroidx/compose/ui/Modifier;", "activeColor", "Landroidx/compose/ui/graphics/Color;", "inactiveColor", "AnimatedDottedLine-eaDK9VM", "(ZLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AnimatedDottedLineKt {
    static final Unit AnimatedDottedLine_eaDK9VM$lambda$5(boolean z, Modifier modifier, long j, long j2, int i, int i2, Composer composer, int i3) {
        m6944AnimatedDottedLineeaDK9VM(z, modifier, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: AnimatedDottedLine-eaDK9VM, reason: not valid java name */
    public static final void m6944AnimatedDottedLineeaDK9VM(final boolean isActive, Modifier modifier, long activeColor, long inactiveColor, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long inactiveColor2;
        Modifier.Companion modifier3;
        final long activeColor2;
        Object objAnimatable$default;
        Object objAnimatable$default2;
        AnimatedDottedLineKt$AnimatedDottedLine$1$1 animatedDottedLineKt$AnimatedDottedLine$1$1;
        final long inactiveColor3;
        Object obj;
        final Modifier modifier4;
        final long activeColor3;
        final long inactiveColor4;
        Composer $composer2 = $composer.startRestartGroup(-1506439279);
        ComposerKt.sourceInformation($composer2, "C(AnimatedDottedLine)P(2,3,0:c#ui.graphics.Color,1:c#ui.graphics.Color)21@759L49,22@832L49,24@912L476,24@887L501,36@1437L951,36@1394L994:AnimatedDottedLine.kt#2thlc2");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(isActive) ? 4 : 2;
        }
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
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            j = activeColor;
        } else if (($changed & 384) == 0) {
            j = activeColor;
            $dirty |= $composer2.changed(j) ? 256 : 128;
        } else {
            j = activeColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                inactiveColor2 = inactiveColor;
                int i4 = $composer2.changed(inactiveColor2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                inactiveColor2 = inactiveColor;
            }
            $dirty |= i4;
        } else {
            inactiveColor2 = inactiveColor;
        }
        if (($dirty & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            activeColor3 = j;
            inactiveColor4 = inactiveColor2;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "18@621L11");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                long activeColor4 = i3 != 0 ? ColorKt.Color(4282090230L) : j;
                if ((i & 8) != 0) {
                    long onSurfaceVariant = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnSurfaceVariant();
                    $dirty &= -7169;
                    activeColor2 = activeColor4;
                    inactiveColor2 = Color.m4157copywmQWz5c(onSurfaceVariant, (14 & 1) != 0 ? Color.m4161getAlphaimpl(onSurfaceVariant) : 0.5f, (14 & 2) != 0 ? Color.m4165getRedimpl(onSurfaceVariant) : 0.0f, (14 & 4) != 0 ? Color.m4164getGreenimpl(onSurfaceVariant) : 0.0f, (14 & 8) != 0 ? Color.m4162getBlueimpl(onSurfaceVariant) : 0.0f);
                } else {
                    activeColor2 = activeColor4;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier3 = modifier2;
                activeColor2 = j;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1506439279, $dirty, -1, "com.example.ui.screens.AnimatedDottedLine (AnimatedDottedLine.kt:19)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -284158174, "CC(remember):AnimatedDottedLine.kt#9igjgp");
            Object objRememberedValue = $composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objAnimatable$default = AnimatableKt.Animatable$default(isActive ? 1.0f : 0.0f, 0.0f, 2, null);
                $composer2.updateRememberedValue(objAnimatable$default);
            } else {
                objAnimatable$default = objRememberedValue;
            }
            final Animatable headProgress = (Animatable) objAnimatable$default;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -284155838, "CC(remember):AnimatedDottedLine.kt#9igjgp");
            Object objRememberedValue2 = $composer2.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objAnimatable$default2 = AnimatableKt.Animatable$default(isActive ? 0.0f : 1.0f, 0.0f, 2, null);
                $composer2.updateRememberedValue(objAnimatable$default2);
            } else {
                objAnimatable$default2 = objRememberedValue2;
            }
            final Animatable tailProgress = (Animatable) objAnimatable$default2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Boolean boolValueOf = Boolean.valueOf(isActive);
            ComposerKt.sourceInformationMarkerStart($composer2, -284152851, "CC(remember):AnimatedDottedLine.kt#9igjgp");
            boolean zChangedInstance = (($dirty & 14) == 4) | $composer2.changedInstance(tailProgress) | $composer2.changedInstance(headProgress);
            Object objRememberedValue3 = $composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                animatedDottedLineKt$AnimatedDottedLine$1$1 = new AnimatedDottedLineKt$AnimatedDottedLine$1$1(isActive, tailProgress, headProgress, null);
                $composer2.updateRememberedValue(animatedDottedLineKt$AnimatedDottedLine$1$1);
            } else {
                animatedDottedLineKt$AnimatedDottedLine$1$1 = objRememberedValue3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) animatedDottedLineKt$AnimatedDottedLine$1$1, $composer2, $dirty & 14);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -284135576, "CC(remember):AnimatedDottedLine.kt#9igjgp");
            boolean zChangedInstance2 = $composer2.changedInstance(headProgress) | $composer2.changedInstance(tailProgress) | (($dirty & 896) == 256) | (((($dirty & 7168) ^ 3072) > 2048 && $composer2.changed(inactiveColor2)) || ($dirty & 3072) == 2048);
            Object objRememberedValue4 = $composer2.rememberedValue();
            if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                inactiveColor3 = inactiveColor2;
                obj = new Function1() { // from class: com.example.ui.screens.AnimatedDottedLineKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return AnimatedDottedLineKt.AnimatedDottedLine_eaDK9VM$lambda$4$lambda$3(headProgress, tailProgress, activeColor2, inactiveColor3, (DrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue4;
                inactiveColor3 = inactiveColor2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierFillMaxWidth$default, (Function1) obj, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            activeColor3 = activeColor2;
            inactiveColor4 = inactiveColor3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.ui.screens.AnimatedDottedLineKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return AnimatedDottedLineKt.AnimatedDottedLine_eaDK9VM$lambda$5(isActive, modifier4, activeColor3, inactiveColor4, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final Unit AnimatedDottedLine_eaDK9VM$lambda$4$lambda$3(Animatable $headProgress, Animatable $tailProgress, long $activeColor, long $inactiveColor, DrawScope $this$Canvas) {
        DrawScope Canvas = $this$Canvas;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float dotRadius = Canvas.mo362toPx0680j_4(Dp.m6622constructorimpl(3));
        float spacing = Canvas.mo362toPx0680j_4(Dp.m6622constructorimpl(12));
        float totalWidth = Size.m3987getWidthimpl(Canvas.mo4709getSizeNHjbRc());
        float y = Size.m3984getHeightimpl(Canvas.mo4709getSizeNHjbRc()) / 2.0f;
        int totalDots = Math.max(1, ((int) ((totalWidth - (dotRadius * 2.0f)) / spacing)) + 1);
        float drawnWidth = (totalDots - 1) * spacing;
        float startX = (totalWidth - drawnWidth) / 2.0f;
        int i = 0;
        while (i < totalDots) {
            float x = startX + (i * spacing);
            float dotPos = ((totalDots - 1) - i) / totalDots;
            boolean isBlue = dotPos < ((Number) $headProgress.getValue()).floatValue() && dotPos >= ((Number) $tailProgress.getValue()).floatValue();
            long color = isBlue ? $activeColor : $inactiveColor;
            DrawScope.m4690drawCircleVaOC9Bg$default(Canvas, color, dotRadius, OffsetKt.Offset(x, y), 0.0f, null, null, 0, 120, null);
            i++;
            Canvas = $this$Canvas;
            totalDots = totalDots;
        }
        return Unit.INSTANCE;
    }
}
