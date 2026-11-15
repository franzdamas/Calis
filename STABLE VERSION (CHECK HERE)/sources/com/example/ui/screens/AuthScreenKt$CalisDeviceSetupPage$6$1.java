package com.example.ui.screens;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.ble.BleManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AuthScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ui.screens.AuthScreenKt$CalisDeviceSetupPage$6$1", f = "AuthScreen.kt", i = {}, l = {2433}, m = "invokeSuspend", n = {}, s = {})
final class AuthScreenKt$CalisDeviceSetupPage$6$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BleManager $bleManager;
    final /* synthetic */ State<BleManager.ConnectionState> $connectionState$delegate;
    final /* synthetic */ MutableState<Boolean> $isWaitingForReconnect$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    AuthScreenKt$CalisDeviceSetupPage$6$1(BleManager bleManager, MutableState<Boolean> mutableState, State<? extends BleManager.ConnectionState> state, Continuation<? super AuthScreenKt$CalisDeviceSetupPage$6$1> continuation) {
        super(2, continuation);
        this.$bleManager = bleManager;
        this.$isWaitingForReconnect$delegate = mutableState;
        this.$connectionState$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthScreenKt$CalisDeviceSetupPage$6$1(this.$bleManager, this.$isWaitingForReconnect$delegate, this.$connectionState$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthScreenKt$CalisDeviceSetupPage$6$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:13:0x0032
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L16;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L11:
            kotlin.ResultKt.throwOnFailure(r7)
            r1 = r6
            goto L5c
        L16:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r1 = r6.$isWaitingForReconnect$delegate
            boolean r1 = com.example.ui.screens.AuthScreenKt.access$CalisDeviceSetupPage$lambda$431(r1)
            if (r1 == 0) goto L5d
            androidx.compose.runtime.State<com.example.ble.BleManager$ConnectionState> r1 = r6.$connectionState$delegate
            com.example.ble.BleManager$ConnectionState r1 = com.example.ui.screens.AuthScreenKt.access$CalisDeviceSetupPage$lambda$438(r1)
            boolean r1 = r1 instanceof com.example.ble.BleManager.ConnectionState.Disconnected
            if (r1 == 0) goto L5d
            r1 = r6
        L2c:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r2 = r1.$isWaitingForReconnect$delegate
            boolean r2 = com.example.ui.screens.AuthScreenKt.access$CalisDeviceSetupPage$lambda$431(r2)
            if (r2 == 0) goto L5e
            androidx.compose.runtime.State<com.example.ble.BleManager$ConnectionState> r2 = r1.$connectionState$delegate
            com.example.ble.BleManager$ConnectionState r2 = com.example.ui.screens.AuthScreenKt.access$CalisDeviceSetupPage$lambda$438(r2)
            boolean r2 = r2 instanceof com.example.ble.BleManager.ConnectionState.Disconnected
            if (r2 == 0) goto L5e
            com.example.ble.BleManager r2 = r1.$bleManager
            boolean r2 = r2.isBluetoothEnabled()
            r3 = 1
            if (r2 == 0) goto L4e
            com.example.ble.BleManager r2 = r1.$bleManager
            r4 = 0
            r5 = 0
            com.example.ble.BleManager.startScan$default(r2, r4, r3, r5)
        L4e:
            r2 = r1
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r1.label = r3
            r3 = 1500(0x5dc, double:7.41E-321)
            java.lang.Object r2 = kotlinx.coroutines.DelayKt.delay(r3, r2)
            if (r2 != r0) goto L5c
            return r0
        L5c:
            goto L2c
        L5d:
            r1 = r6
        L5e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.AuthScreenKt$CalisDeviceSetupPage$6$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
