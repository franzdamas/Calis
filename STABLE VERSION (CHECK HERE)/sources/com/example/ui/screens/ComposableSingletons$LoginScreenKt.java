package com.example.ui.screens;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.BackspaceKt;
import androidx.compose.material.icons.filled.FingerprintKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$LoginScreenKt {
    public static final ComposableSingletons$LoginScreenKt INSTANCE = new ComposableSingletons$LoginScreenKt();

    /* JADX INFO: renamed from: lambda$-200593242, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f110lambda$200593242 = ComposableLambdaKt.composableLambdaInstance(-200593242, false, new Function3() { // from class: com.example.ui.screens.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$LoginScreenKt.lambda__200593242$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-544237457, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f111lambda$544237457 = ComposableLambdaKt.composableLambdaInstance(-544237457, false, new Function2() { // from class: com.example.ui.screens.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$LoginScreenKt.lambda__544237457$lambda$1((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-200593242$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6961getLambda$200593242$app() {
        return f110lambda$200593242;
    }

    /* JADX INFO: renamed from: getLambda$-544237457$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6962getLambda$544237457$app() {
        return f111lambda$544237457;
    }

    static final Unit lambda__200593242$lambda$0(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C292@13200L11,289@13002L304,295@13331L39,298@13512L10,296@13395L222:LoginScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-200593242, $changed, -1, "com.example.ui.screens.ComposableSingletons$LoginScreenKt.lambda$-200593242.<anonymous> (LoginScreen.kt:289)");
            }
            IconKt.m2150Iconww6aTOc(FingerprintKt.getFingerprint(Icons.INSTANCE.getDefault()), "Login with Biometrics", SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(22)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 432, 0);
            SpacerKt.Spacer(SizeKt.m720width3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(8)), $composer, 6);
            TextKt.m2693Text4IGK_g("Log in with Biometrics", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelLarge(), $composer, 196614, 0, 65502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__544237457$lambda$1(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C345@15130L11,342@14937L316:LoginScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-544237457, $changed, -1, "com.example.ui.screens.ComposableSingletons$LoginScreenKt.lambda$-544237457.<anonymous> (LoginScreen.kt:342)");
            }
            IconKt.m2150Iconww6aTOc(BackspaceKt.getBackspace(Icons.INSTANCE.getDefault()), "Delete", SizeKt.m715size3ABfNKs(Modifier.INSTANCE, Dp.m6622constructorimpl(28)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), $composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
