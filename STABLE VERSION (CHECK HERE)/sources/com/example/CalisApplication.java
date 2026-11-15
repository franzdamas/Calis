package com.example;

import android.app.Application;
import android.util.Log;
import com.example.ble.BleManager;
import com.example.util.ReminderNotificationHelper;
import kotlin.Metadata;

/* JADX INFO: compiled from: CalisApplication.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/example/CalisApplication;", "Landroid/app/Application;", "<init>", "()V", "onCreate", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CalisApplication extends Application {
    public static final int $stable = 8;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Log.d("CalisApplication", "Application onCreate: initializing Calis");
        BleManager bleManager = BleManager.INSTANCE.getInstance(this);
        bleManager.resetBleStateOnStartup(false);
        ReminderNotificationHelper.INSTANCE.startPeriodicChecker(this);
    }
}
