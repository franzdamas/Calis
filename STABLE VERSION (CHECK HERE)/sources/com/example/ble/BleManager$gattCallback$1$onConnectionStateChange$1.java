package com.example.ble;

import android.bluetooth.BluetoothGatt;
import android.util.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: BleManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.ble.BleManager$gattCallback$1$onConnectionStateChange$1", f = "BleManager.kt", i = {}, l = {809, 813}, m = "invokeSuspend", n = {}, s = {})
final class BleManager$gattCallback$1$onConnectionStateChange$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BluetoothGatt $gatt;
    int label;
    final /* synthetic */ BleManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BleManager$gattCallback$1$onConnectionStateChange$1(BluetoothGatt bluetoothGatt, BleManager bleManager, Continuation<? super BleManager$gattCallback$1$onConnectionStateChange$1> continuation) {
        super(2, continuation);
        this.$gatt = bluetoothGatt;
        this.this$0 = bleManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BleManager$gattCallback$1$onConnectionStateChange$1(this.$gatt, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BleManager$gattCallback$1$onConnectionStateChange$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0036  */
    /* JADX WARN: Code duplicated, block: B:16:0x004d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x0052  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        BluetoothGatt bluetoothGatt;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (DelayKt.delay(300L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Log.d("CALIS", "[REG] Requesting MTU size 255...");
                bluetoothGatt = this.$gatt;
                if (bluetoothGatt != null) {
                    Boxing.boxBoolean(bluetoothGatt.requestMtu(255));
                }
                this.label = 2;
                if (DelayKt.delay(1500L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (this.$gatt != null && this.this$0.rxCharacteristic == null) {
                    Log.d("CALIS", "[REG] Failsafe: Triggering service discovery...");
                    this.$gatt.discoverServices();
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                Log.d("CALIS", "[REG] Requesting MTU size 255...");
                bluetoothGatt = this.$gatt;
                if (bluetoothGatt != null) {
                    Boxing.boxBoolean(bluetoothGatt.requestMtu(255));
                }
                this.label = 2;
                if (DelayKt.delay(1500L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (this.$gatt != null) {
                    Log.d("CALIS", "[REG] Failsafe: Triggering service discovery...");
                    this.$gatt.discoverServices();
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                if (this.$gatt != null) {
                    Log.d("CALIS", "[REG] Failsafe: Triggering service discovery...");
                    this.$gatt.discoverServices();
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
