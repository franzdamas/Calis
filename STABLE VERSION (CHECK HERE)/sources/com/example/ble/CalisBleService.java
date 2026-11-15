package com.example.ble;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.example.MainActivity;
import com.example.data.db.CalisDatabase;
import com.example.data.db.ClassroomEntity;
import com.example.data.db.TeacherEntity;
import com.example.util.BlePermissionUtils;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: CalisBleService.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0002$'\b\u0007\u0018\u0000 E2\u00020\u0001:\u0001EB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010)\u001a\u00020*H\u0016J\"\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u00020,2\u0006\u00100\u001a\u00020,H\u0016J\u0014\u00101\u001a\u0004\u0018\u0001022\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u00103\u001a\u00020*H\u0016J\b\u00104\u001a\u00020*H\u0002J\b\u00105\u001a\u00020*H\u0002J\b\u00106\u001a\u00020*H\u0002J\u0010\u00107\u001a\u00020*2\u0006\u00108\u001a\u00020\u001aH\u0003J\b\u00109\u001a\u00020*H\u0002J\u000e\u0010:\u001a\u00020*2\u0006\u0010;\u001a\u00020\u0016J\u0006\u0010<\u001a\u00020*J\u0006\u0010=\u001a\u00020*J\b\u0010>\u001a\u00020*H\u0002J\b\u0010?\u001a\u00020\u0016H\u0002J\b\u0010@\u001a\u00020AH\u0002J\u0014\u0010B\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\u001a0CH\u0002J\b\u0010D\u001a\u00020*H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001cX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0018\u00010!R\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%R\u0010\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0004\n\u0002\u0010(¨\u0006F"}, d2 = {"Lcom/example/ble/CalisBleService;", "Landroid/app/Service;", "<init>", "()V", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "bleManager", "Lcom/example/ble/BleManager;", "database", "Lcom/example/data/db/CalisDatabase;", "currentTeacher", "Lcom/example/data/db/TeacherEntity;", "currentClassroom", "Lcom/example/data/db/ClassroomEntity;", "currentConnectionState", "Lcom/example/ble/BleManager$ConnectionState;", "currentDeviceState", "Lcom/example/ble/DeviceState;", "scannedDevicesList", "", "Lcom/example/ble/BleManager$ScannedDevice;", "currentIsAuthenticated", "", "isSessionActiveOverride", "Ljava/lang/Boolean;", "lastPostedFingerprint", "", "lastNotificationPostTime", "", "pendingNotificationJob", "Lkotlinx/coroutines/Job;", "NOTIFICATION_MIN_INTERVAL_MS", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "screenReceiver", "com/example/ble/CalisBleService$screenReceiver$1", "Lcom/example/ble/CalisBleService$screenReceiver$1;", "bluetoothReceiver", "com/example/ble/CalisBleService$bluetoothReceiver$1", "Lcom/example/ble/CalisBleService$bluetoothReceiver$1;", "onCreate", "", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "onBind", "Landroid/os/IBinder;", "onDestroy", "updateWakeLock", "releaseWakeLock", "observeStateChanges", "handleAction", "action", "startForegroundWithToolbar", "setSessionActiveState", "isActive", "updateToolbarNotificationImmediate", "updateToolbarNotification", "postNotificationNow", "isBluetoothHardwareEnabled", "buildToolbarNotification", "Landroid/app/Notification;", "buildToolbarNotificationWithFingerprint", "Lkotlin/Pair;", "createNotificationChannel", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CalisBleService extends Service {
    public static final String ACTION_CONNECT = "com.example.calis.ACTION_CONNECT";
    public static final String ACTION_DISCONNECT = "com.example.calis.ACTION_DISCONNECT";
    public static final String ACTION_ENABLE_AND_START_SESSION = "com.example.calis.ACTION_ENABLE_AND_START_SESSION";
    public static final String ACTION_END_SESSION = "com.example.calis.ACTION_END_SESSION";
    public static final String ACTION_START_SESSION = "com.example.calis.ACTION_START_SESSION";
    public static final String ACTION_STOP_SERVICE = "com.example.calis.ACTION_STOP_SERVICE";
    public static final String ACTION_UPDATE_NOTIFICATION = "com.example.calis.ACTION_UPDATE_NOTIFICATION";
    public static final String CHANNEL_ID = "calis_tc_session_channel";
    private static final int COMBINED_NOTIF_ID = 9999;
    private static volatile CalisBleService INSTANCE = null;
    public static final int NOTIFICATION_ID = 1001;
    public static final String TAG = "CalisBleService";
    private static int combinedNotificationCount;
    private static Runnable updateRunnable;
    private BleManager bleManager;
    private ClassroomEntity currentClassroom;
    private boolean currentIsAuthenticated;
    private TeacherEntity currentTeacher;
    private CalisDatabase database;
    private Boolean isSessionActiveOverride;
    private long lastNotificationPostTime;
    private String lastPostedFingerprint;
    private Job pendingNotificationJob;
    private PowerManager.WakeLock wakeLock;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final ConcurrentHashMap.KeySetView<Integer, Boolean> activeTaskSubmissionNotifIds = ConcurrentHashMap.newKeySet();
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private final CoroutineScope serviceScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null)));
    private BleManager.ConnectionState currentConnectionState = BleManager.ConnectionState.Disconnected.INSTANCE;
    private DeviceState currentDeviceState = DeviceState.UNKNOWN;
    private List<BleManager.ScannedDevice> scannedDevicesList = CollectionsKt.emptyList();
    private final long NOTIFICATION_MIN_INTERVAL_MS = 600;
    private final CalisBleService$screenReceiver$1 screenReceiver = new BroadcastReceiver() { // from class: com.example.ble.CalisBleService$screenReceiver$1
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent != null ? intent.getAction() : null;
            if (action != null) {
                switch (action.hashCode()) {
                    case -2128145023:
                        if (action.equals("android.intent.action.SCREEN_OFF")) {
                            Log.d(CalisBleService.TAG, "Screen OFF detected while classroom session is active. Ensuring background BLE execution...");
                            this.this$0.updateWakeLock();
                            break;
                        }
                        break;
                    case -1454123155:
                        if (action.equals("android.intent.action.SCREEN_ON")) {
                            Log.d(CalisBleService.TAG, "Screen ON detected.");
                            this.this$0.updateWakeLock();
                            break;
                        }
                        break;
                }
            }
        }
    };
    private final CalisBleService$bluetoothReceiver$1 bluetoothReceiver = new BroadcastReceiver() { // from class: com.example.ble.CalisBleService$bluetoothReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "android.bluetooth.adapter.action.STATE_CHANGED")) {
                int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                Log.d(CalisBleService.TAG, "BluetoothAdapter state changed: " + state);
                this.this$0.updateToolbarNotificationImmediate();
            }
        }
    };

    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0013J\u0018\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u0013J.\u0010%\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\bJ\u000e\u0010*\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010+\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000RN\u0010\u001c\u001aB\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\b0\b\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u00130\u0013 \u001e* \u0012\f\u0012\n \u001e*\u0004\u0018\u00010\b0\b\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u001d0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/example/ble/CalisBleService$Companion;", "", "<init>", "()V", "TAG", "", "CHANNEL_ID", "NOTIFICATION_ID", "", "ACTION_START_SESSION", "ACTION_END_SESSION", "ACTION_CONNECT", "ACTION_DISCONNECT", "ACTION_ENABLE_AND_START_SESSION", "ACTION_UPDATE_NOTIFICATION", "ACTION_STOP_SERVICE", "INSTANCE", "Lcom/example/ble/CalisBleService;", "isServiceRunning", "", "startService", "", "context", "Landroid/content/Context;", "setSessionActive", "isActive", "updateNotification", "immediate", "activeTaskSubmissionNotifIds", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "kotlin.jvm.PlatformType", "COMBINED_NOTIF_ID", "combinedNotificationCount", "updateRunnable", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "showTaskSubmissionNotification", "studentName", "taskTitle", "score", "maxScore", "dismissTaskSubmissionNotification", "stopService", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isServiceRunning() {
            return CalisBleService.INSTANCE != null;
        }

        public final void startService(Context context) {
            ComponentName componentNameStartService;
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                SharedPreferences prefs = context.getSharedPreferences("calis_auth_prefs", 0);
                String userRole = prefs.getString("user_role", null);
                if (!Intrinsics.areEqual(userRole, "TEACHER")) {
                    Log.d(CalisBleService.TAG, "startService skipped: user is not TEACHER (role: " + userRole + ")");
                    return;
                }
                if (!BlePermissionUtils.INSTANCE.hasPermissions(context)) {
                    Log.d(CalisBleService.TAG, "startService skipped: Bluetooth permissions not granted yet");
                    return;
                }
                Intent intent = new Intent(context, (Class<?>) CalisBleService.class);
                if (Build.VERSION.SDK_INT >= 26) {
                    componentNameStartService = context.startForegroundService(intent);
                } else {
                    componentNameStartService = context.startService(intent);
                }
                ComponentName componentName = componentNameStartService;
            } catch (Throwable e) {
                Log.e(CalisBleService.TAG, "Failed to start CalisBleService: " + e.getMessage(), e);
            }
        }

        public final void setSessionActive(Context context, boolean isActive) {
            CalisBleService service;
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences prefs = context.getSharedPreferences("calis_auth_prefs", 0);
            String userRole = prefs.getString("user_role", null);
            if (Intrinsics.areEqual(userRole, "TEACHER") && (service = CalisBleService.INSTANCE) != null) {
                service.setSessionActiveState(isActive);
            }
        }

        public static /* synthetic */ void updateNotification$default(Companion companion, Context context, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            companion.updateNotification(context, z);
        }

        public final void updateNotification(Context context, boolean immediate) {
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences prefs = context.getSharedPreferences("calis_auth_prefs", 0);
            String userRole = prefs.getString("user_role", null);
            if (!Intrinsics.areEqual(userRole, "TEACHER")) {
                CalisBleService service = CalisBleService.INSTANCE;
                if (service != null) {
                    service.stopForeground(1);
                    service.stopSelf();
                    CalisBleService.INSTANCE = null;
                }
                Object systemService = context.getSystemService("notification");
                NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
                if (notificationManager != null) {
                    notificationManager.cancel(1001);
                    return;
                }
                return;
            }
            CalisBleService service2 = CalisBleService.INSTANCE;
            if (service2 != null) {
                if (immediate) {
                    service2.updateToolbarNotificationImmediate();
                } else {
                    service2.updateToolbarNotification();
                }
            }
        }

        public final void showTaskSubmissionNotification(final Context context, String studentName, String taskTitle, int score, int maxScore) {
            String scoreText;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(studentName, "studentName");
            Intrinsics.checkNotNullParameter(taskTitle, "taskTitle");
            try {
                Object systemService = context.getSystemService("notification");
                final NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
                if (notificationManager == null) {
                    return;
                }
                final String channelId = "calis_task_submissions";
                if (Build.VERSION.SDK_INT >= 26) {
                    NotificationChannel existingChannel = notificationManager.getNotificationChannel("calis_task_submissions");
                    if (existingChannel == null) {
                        NotificationChannel channel = new NotificationChannel("calis_task_submissions", "CALIS Task Submissions", 4);
                        channel.setDescription("Notifications for student task submissions and scores");
                        channel.setShowBadge(true);
                        channel.setLockscreenVisibility(1);
                        notificationManager.createNotificationChannel(channel);
                    }
                }
                if (maxScore > 0) {
                    try {
                        scoreText = score + " / " + maxScore + " pts";
                    } catch (Exception e) {
                        e = e;
                    }
                } else {
                    scoreText = "Complete";
                }
                Intent intent = new Intent(context, (Class<?>) MainActivity.class);
                intent.setFlags(603979776);
                try {
                    if (CalisBleService.combinedNotificationCount >= 3) {
                        CalisBleService.combinedNotificationCount++;
                        Iterator it = CalisBleService.activeTaskSubmissionNotifIds.iterator();
                        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                        while (it.hasNext()) {
                            Integer id = (Integer) it.next();
                            Intrinsics.checkNotNull(id);
                            notificationManager.cancel(id.intValue());
                        }
                        CalisBleService.activeTaskSubmissionNotifIds.clear();
                        try {
                            final PendingIntent pendingIntent = PendingIntent.getActivity(context, CalisBleService.COMBINED_NOTIF_ID, intent, 201326592);
                            Notification notification = new NotificationCompat.Builder(context, "calis_task_submissions").setSmallIcon(R.drawable.stat_notify_chat).setContentTitle("Multiple Submissions (" + CalisBleService.combinedNotificationCount + ")").setContentText(studentName + " submitted \"" + taskTitle + "\" (" + scoreText + ")").setStyle(new NotificationCompat.BigTextStyle().bigText("Student " + studentName + " submitted \"" + taskTitle + "\".\nResult: " + scoreText)).setPriority(1).setAutoCancel(true).setContentIntent(pendingIntent).build();
                            Intrinsics.checkNotNullExpressionValue(notification, "build(...)");
                            notificationManager.notify(CalisBleService.COMBINED_NOTIF_ID, notification);
                            Runnable runnable = CalisBleService.updateRunnable;
                            if (runnable != null) {
                                CalisBleService.handler.removeCallbacks(runnable);
                            }
                            Runnable run = new Runnable() { // from class: com.example.ble.CalisBleService$Companion$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    CalisBleService.Companion.showTaskSubmissionNotification$lambda$3(context, channelId, pendingIntent, notificationManager);
                                }
                            };
                            CalisBleService.updateRunnable = run;
                            CalisBleService.handler.postDelayed(run, 5000L);
                            return;
                        } catch (Exception e2) {
                            e = e2;
                        }
                    } else {
                        int notifId = ((int) (System.currentTimeMillis() % 100000)) + 2000;
                        CalisBleService.activeTaskSubmissionNotifIds.add(Integer.valueOf(notifId));
                        CalisBleService.combinedNotificationCount++;
                        Notification notification2 = new NotificationCompat.Builder(context, "calis_task_submissions").setSmallIcon(R.drawable.stat_notify_chat).setContentTitle("Task Submission: " + taskTitle).setContentText(studentName + " submitted \"" + taskTitle + "\" (" + scoreText + ")").setStyle(new NotificationCompat.BigTextStyle().bigText("Student " + studentName + " submitted \"" + taskTitle + "\".\nResult: " + scoreText)).setPriority(1).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(context, notifId, intent, 201326592)).build();
                        Intrinsics.checkNotNullExpressionValue(notification2, "build(...)");
                        notificationManager.notify(notifId, notification2);
                        return;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Exception e4) {
                e = e4;
            }
            Log.e(CalisBleService.TAG, "Error displaying task submission notification: " + e.getMessage());
        }

        static final void showTaskSubmissionNotification$lambda$3(Context $context, String $channelId, PendingIntent $pendingIntent, NotificationManager $notificationManager) {
            Notification finalNotif = new NotificationCompat.Builder($context, $channelId).setSmallIcon(R.drawable.stat_notify_chat).setContentTitle("Multiple Submissions (" + CalisBleService.combinedNotificationCount + ")").setContentText("student submitted, open app").setStyle(new NotificationCompat.BigTextStyle().bigText(CalisBleService.combinedNotificationCount + " student submissions received.\nOpen the app to view the results.")).setPriority(1).setAutoCancel(true).setContentIntent($pendingIntent).build();
            Intrinsics.checkNotNullExpressionValue(finalNotif, "build(...)");
            $notificationManager.notify(CalisBleService.COMBINED_NOTIF_ID, finalNotif);
        }

        public final void dismissTaskSubmissionNotification(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                Object systemService = context.getSystemService("notification");
                NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
                if (notificationManager == null) {
                    return;
                }
                Iterator it = CalisBleService.activeTaskSubmissionNotifIds.iterator();
                Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                while (it.hasNext()) {
                    Integer id = (Integer) it.next();
                    Intrinsics.checkNotNull(id);
                    notificationManager.cancel(id.intValue());
                }
                CalisBleService.activeTaskSubmissionNotifIds.clear();
                notificationManager.cancel(CalisBleService.COMBINED_NOTIF_ID);
                CalisBleService.combinedNotificationCount = 0;
                Runnable runnable = CalisBleService.updateRunnable;
                if (runnable != null) {
                    CalisBleService.handler.removeCallbacks(runnable);
                }
                CalisBleService.updateRunnable = null;
            } catch (Exception e) {
                Log.e(CalisBleService.TAG, "Error dismissing task submission notifications: " + e.getMessage());
            }
        }

        public final void stopService(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                CalisBleService service = CalisBleService.INSTANCE;
                if (service != null) {
                    service.stopForeground(1);
                    service.stopSelf();
                    CalisBleService.INSTANCE = null;
                }
                Object systemService = context.getSystemService("notification");
                NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
                if (notificationManager != null) {
                    notificationManager.cancel(1001);
                }
                Intent intent = new Intent(context, (Class<?>) CalisBleService.class);
                context.stopService(intent);
            } catch (Exception e) {
                Log.e(CalisBleService.TAG, "Error stopping CalisBleService: " + e.getMessage());
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("calis_auth_prefs", 0);
        String userRole = prefs.getString("user_role", null);
        if (!Intrinsics.areEqual(userRole, "TEACHER")) {
            Log.d(TAG, "CalisBleService created but user is not TEACHER (role: " + userRole + "). Stopping immediately.");
            stopSelf();
            return;
        }
        INSTANCE = this;
        BleManager.Companion companion = BleManager.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.bleManager = companion.getInstance(applicationContext);
        CalisDatabase.Companion companion2 = CalisDatabase.INSTANCE;
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
        this.database = companion2.getDatabase(applicationContext2);
        IntentFilter filter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(this.bluetoothReceiver, filter);
        IntentFilter screenFilter = new IntentFilter();
        screenFilter.addAction("android.intent.action.SCREEN_OFF");
        screenFilter.addAction("android.intent.action.SCREEN_ON");
        registerReceiver(this.screenReceiver, screenFilter);
        createNotificationChannel();
        startForegroundWithToolbar();
        observeStateChanges();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String string = getApplicationContext().getSharedPreferences("calis_auth_prefs", 0).getString("user_role", null);
        if (!Intrinsics.areEqual(string, "TEACHER")) {
            Log.d(TAG, "CalisBleService onStartCommand with non-TEACHER role: " + string + ". Stopping foreground and self.");
            stopForeground(1);
            Object systemService = getSystemService("notification");
            NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
            if (notificationManager != null) {
                notificationManager.cancel(1001);
            }
            stopSelf();
            return 2;
        }
        createNotificationChannel();
        startForegroundWithToolbar();
        String action = intent != null ? intent.getAction() : null;
        if (action != null) {
            handleAction(action);
        }
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (Intrinsics.areEqual(INSTANCE, this)) {
            INSTANCE = null;
        }
        Job job = this.pendingNotificationJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.pendingNotificationJob = null;
        try {
            CoroutineScopeKt.cancel$default(this.serviceScope, null, 1, null);
        } catch (Exception e) {
            Log.e(TAG, "Error cancelling serviceScope: " + e.getMessage());
        }
        try {
            if (this.bleManager != null) {
                BleManager bleManager = this.bleManager;
                if (bleManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                    bleManager = null;
                }
                bleManager.stopScan();
                BleManager bleManager2 = this.bleManager;
                if (bleManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                    bleManager2 = null;
                }
                bleManager2.disconnect();
            }
        } catch (Exception e2) {
            Log.e(TAG, "Error stopping BLE in service onDestroy: " + e2.getMessage());
        }
        try {
            Object systemService = getSystemService("notification");
            NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
            if (notificationManager != null) {
                notificationManager.cancel(1001);
            }
            if (notificationManager != null) {
                notificationManager.cancel(COMBINED_NOTIF_ID);
            }
        } catch (Exception e3) {
            Log.e(TAG, "Error cancelling notifications in onDestroy: " + e3.getMessage());
        }
        try {
            unregisterReceiver(this.bluetoothReceiver);
        } catch (Exception e4) {
            Log.e(TAG, "Error unregistering bluetoothReceiver: " + e4.getMessage());
        }
        try {
            unregisterReceiver(this.screenReceiver);
        } catch (Exception e5) {
            Log.e(TAG, "Error unregistering screenReceiver: " + e5.getMessage());
        }
        releaseWakeLock();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:13:0x0026  */
    public final void updateWakeLock() {
        boolean isSessionActive;
        PowerManager.WakeLock wakeLockNewWakeLock;
        boolean isConnected = this.currentConnectionState instanceof BleManager.ConnectionState.Connected;
        boolean z = true;
        if (Intrinsics.areEqual((Object) this.isSessionActiveOverride, (Object) true)) {
            isSessionActive = true;
        } else {
            ClassroomEntity classroomEntity = this.currentClassroom;
            if (classroomEntity != null && classroomEntity.isActiveSession()) {
                isSessionActive = true;
            } else {
                isSessionActive = false;
            }
        }
        boolean shouldHoldWakeLock = isConnected && isSessionActive;
        if (shouldHoldWakeLock) {
            try {
                if (this.wakeLock == null) {
                    Object systemService = getSystemService("power");
                    PowerManager.WakeLock wakeLock = null;
                    PowerManager powerManager = systemService instanceof PowerManager ? (PowerManager) systemService : null;
                    if (powerManager != null && (wakeLockNewWakeLock = powerManager.newWakeLock(1, "Calis:TeacherSessionWakeLock")) != null) {
                        wakeLockNewWakeLock.setReferenceCounted(false);
                        wakeLock = wakeLockNewWakeLock;
                    }
                    this.wakeLock = wakeLock;
                }
                PowerManager.WakeLock wakeLock2 = this.wakeLock;
                if (wakeLock2 == null || wakeLock2.isHeld()) {
                    z = false;
                }
                if (z) {
                    PowerManager.WakeLock wakeLock3 = this.wakeLock;
                    if (wakeLock3 != null) {
                        wakeLock3.acquire(7200000L);
                    }
                    Log.d(TAG, "Acquired PARTIAL_WAKE_LOCK: Keeping app running in background even while screen is off during active session");
                }
                Unit unit = Unit.INSTANCE;
                return;
            } catch (Exception e) {
                Integer.valueOf(Log.e(TAG, "Error acquiring wake lock: " + e.getMessage()));
                return;
            }
        }
        releaseWakeLock();
    }

    private final void releaseWakeLock() {
        try {
            PowerManager.WakeLock wakeLock = this.wakeLock;
            boolean z = false;
            if (wakeLock != null && wakeLock.isHeld()) {
                z = true;
            }
            if (z) {
                PowerManager.WakeLock wakeLock2 = this.wakeLock;
                if (wakeLock2 != null) {
                    wakeLock2.release();
                }
                Log.d(TAG, "Released PARTIAL_WAKE_LOCK");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error releasing wake lock: " + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$observeStateChanges$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$observeStateChanges$1", f = "CalisBleService.kt", i = {}, l = {453}, m = "invokeSuspend", n = {}, s = {})
    static final class C07551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C07551(Continuation<? super C07551> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisBleService.this.new C07551(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07551) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    BleManager bleManager = CalisBleService.this.bleManager;
                    if (bleManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager = null;
                    }
                    StateFlow<BleManager.ConnectionState> connectionState = bleManager.getConnectionState();
                    final CalisBleService calisBleService = CalisBleService.this;
                    this.label = 1;
                    if (connectionState.collect(new FlowCollector() { // from class: com.example.ble.CalisBleService.observeStateChanges.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((BleManager.ConnectionState) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(BleManager.ConnectionState state, Continuation<? super Unit> continuation) {
                            calisBleService.currentConnectionState = state;
                            calisBleService.updateToolbarNotificationImmediate();
                            calisBleService.updateWakeLock();
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    private final void observeStateChanges() {
        BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new C07551(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new C07562(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new C07573(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AnonymousClass4(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AnonymousClass5(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AnonymousClass6(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AnonymousClass7(null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$observeStateChanges$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$observeStateChanges$2", f = "CalisBleService.kt", i = {}, l = {461}, m = "invokeSuspend", n = {}, s = {})
    static final class C07562 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C07562(Continuation<? super C07562> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisBleService.this.new C07562(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07562) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    BleManager bleManager = CalisBleService.this.bleManager;
                    if (bleManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager = null;
                    }
                    StateFlow<Boolean> stateFlowIsAuthenticated = bleManager.isAuthenticated();
                    final CalisBleService calisBleService = CalisBleService.this;
                    this.label = 1;
                    if (stateFlowIsAuthenticated.collect(new FlowCollector() { // from class: com.example.ble.CalisBleService.observeStateChanges.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit(((Boolean) value).booleanValue(), (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(boolean auth, Continuation<? super Unit> continuation) {
                            calisBleService.currentIsAuthenticated = auth;
                            calisBleService.updateToolbarNotificationImmediate();
                            calisBleService.updateWakeLock();
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$observeStateChanges$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$observeStateChanges$3", f = "CalisBleService.kt", i = {}, l = {469}, m = "invokeSuspend", n = {}, s = {})
    static final class C07573 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C07573(Continuation<? super C07573> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisBleService.this.new C07573(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07573) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    BleManager bleManager = CalisBleService.this.bleManager;
                    if (bleManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager = null;
                    }
                    StateFlow<DeviceState> deviceState = bleManager.getDeviceState();
                    final CalisBleService calisBleService = CalisBleService.this;
                    this.label = 1;
                    if (deviceState.collect(new FlowCollector() { // from class: com.example.ble.CalisBleService.observeStateChanges.3.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((DeviceState) value, (Continuation<? super Unit>) $completion);
                        }

                        /* JADX WARN: Code duplicated, block: B:19:0x0045  */
                        public final Object emit(DeviceState dState, Continuation<? super Unit> continuation) {
                            calisBleService.currentDeviceState = dState;
                            if (dState == DeviceState.SESSION_ACTIVE_OPEN || dState == DeviceState.SESSION_ACTIVE_BUSY) {
                                calisBleService.isSessionActiveOverride = Boxing.boxBoolean(true);
                                calisBleService.updateToolbarNotificationImmediate();
                            } else {
                                DeviceState deviceState2 = DeviceState.SESSION_INACTIVE;
                                CalisBleService calisBleService2 = calisBleService;
                                if (dState == deviceState2) {
                                    if (Intrinsics.areEqual(calisBleService2.isSessionActiveOverride, Boxing.boxBoolean(true))) {
                                        ClassroomEntity classroomEntity = calisBleService.currentClassroom;
                                        if (!(classroomEntity != null && classroomEntity.isActiveSession())) {
                                            calisBleService.isSessionActiveOverride = Boxing.boxBoolean(false);
                                            calisBleService.updateToolbarNotificationImmediate();
                                        } else {
                                            calisBleService.updateToolbarNotification();
                                        }
                                    } else {
                                        calisBleService.updateToolbarNotification();
                                    }
                                } else {
                                    calisBleService2.updateToolbarNotification();
                                }
                            }
                            calisBleService.updateWakeLock();
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$observeStateChanges$4, reason: invalid class name */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$observeStateChanges$4", f = "CalisBleService.kt", i = {}, l = {489}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisBleService.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    BleManager bleManager = CalisBleService.this.bleManager;
                    if (bleManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager = null;
                    }
                    StateFlow<List<BleManager.ScannedDevice>> scannedDevices = bleManager.getScannedDevices();
                    final CalisBleService calisBleService = CalisBleService.this;
                    this.label = 1;
                    if (scannedDevices.collect(new FlowCollector() { // from class: com.example.ble.CalisBleService.observeStateChanges.4.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((List<BleManager.ScannedDevice>) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(List<BleManager.ScannedDevice> list, Continuation<? super Unit> continuation) {
                            calisBleService.scannedDevicesList = list;
                            if (!(calisBleService.currentConnectionState instanceof BleManager.ConnectionState.Connected)) {
                                calisBleService.updateToolbarNotification();
                            }
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$observeStateChanges$5, reason: invalid class name */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$observeStateChanges$5", f = "CalisBleService.kt", i = {}, l = {498}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass5(Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisBleService.this.new AnonymousClass5(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CalisDatabase calisDatabase = CalisBleService.this.database;
                    if (calisDatabase == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("database");
                        calisDatabase = null;
                    }
                    Flow<TeacherEntity> teacher = calisDatabase.teacherDao().getTeacher();
                    final CalisBleService calisBleService = CalisBleService.this;
                    this.label = 1;
                    if (teacher.collect(new FlowCollector() { // from class: com.example.ble.CalisBleService.observeStateChanges.5.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((TeacherEntity) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(TeacherEntity teacher2, Continuation<? super Unit> continuation) {
                            calisBleService.currentTeacher = teacher2;
                            calisBleService.updateToolbarNotification();
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$observeStateChanges$6, reason: invalid class name */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$observeStateChanges$6", f = "CalisBleService.kt", i = {}, l = {509}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass6(Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisBleService.this.new AnonymousClass6(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CalisDatabase calisDatabase = CalisBleService.this.database;
                    if (calisDatabase == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("database");
                        calisDatabase = null;
                    }
                    Flow<ClassroomEntity> activeBoundClassroom = calisDatabase.classroomDao().getActiveBoundClassroom();
                    CalisDatabase calisDatabase2 = CalisBleService.this.database;
                    if (calisDatabase2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("database");
                        calisDatabase2 = null;
                    }
                    Flow flowFlowCombine = FlowKt.flowCombine(activeBoundClassroom, calisDatabase2.classroomDao().getAnyClassroom(), new AnonymousClass1(null));
                    final CalisBleService calisBleService = CalisBleService.this;
                    this.label = 1;
                    if (flowFlowCombine.collect(new FlowCollector() { // from class: com.example.ble.CalisBleService.observeStateChanges.6.2
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((ClassroomEntity) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(ClassroomEntity classroom, Continuation<? super Unit> continuation) {
                            calisBleService.currentClassroom = classroom;
                            if (calisBleService.isSessionActiveOverride != null) {
                                if (Intrinsics.areEqual(classroom != null ? Boxing.boxBoolean(classroom.isActiveSession()) : null, calisBleService.isSessionActiveOverride)) {
                                    calisBleService.isSessionActiveOverride = null;
                                }
                            }
                            calisBleService.updateToolbarNotificationImmediate();
                            calisBleService.updateWakeLock();
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.example.ble.CalisBleService$observeStateChanges$6$1, reason: invalid class name */
        /* JADX INFO: compiled from: CalisBleService.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", "Lcom/example/data/db/ClassroomEntity;", "active", "any"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.ble.CalisBleService$observeStateChanges$6$1", f = "CalisBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function3<ClassroomEntity, ClassroomEntity, Continuation<? super ClassroomEntity>, Object> {
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            int label;

            AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(ClassroomEntity classroomEntity, ClassroomEntity classroomEntity2, Continuation<? super ClassroomEntity> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
                anonymousClass1.L$0 = classroomEntity;
                anonymousClass1.L$1 = classroomEntity2;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                ClassroomEntity active = (ClassroomEntity) this.L$0;
                ClassroomEntity any = (ClassroomEntity) this.L$1;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        return active == null ? any : active;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$observeStateChanges$7, reason: invalid class name */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$observeStateChanges$7", f = "CalisBleService.kt", i = {0}, l = {522}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass7(Continuation<? super AnonymousClass7> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass7 anonymousClass7 = CalisBleService.this.new AnonymousClass7(continuation);
            anonymousClass7.L$0 = obj;
            return anonymousClass7;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0025  */
        /* JADX WARN: Code duplicated, block: B:12:0x0034 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:15:0x0040  */
        /* JADX WARN: Code duplicated, block: B:18:0x004a  */
        /* JADX WARN: Code duplicated, block: B:20:0x0052  */
        /* JADX WARN: Code duplicated, block: B:23:0x0064  */
        /* JADX WARN: Code duplicated, block: B:25:0x006c  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0032 -> B:13:0x0035). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:15:0x0040
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = r8.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r8.label
                r3 = 1
                switch(r2) {
                    case 0: goto L1b;
                    case 1: goto L16;
                    default: goto Le;
                }
            Le:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r1
            L16:
                kotlin.ResultKt.throwOnFailure(r9)
                r2 = r8
                goto L35
            L1b:
                kotlin.ResultKt.throwOnFailure(r9)
                r2 = r8
            L1f:
                boolean r4 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)
                if (r4 == 0) goto L7a
                r4 = r2
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r2.L$0 = r0
                r2.label = r3
                r5 = 2000(0x7d0, double:9.88E-321)
                java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r5, r4)
                if (r4 != r1) goto L35
                return r1
            L35:
                com.example.ble.CalisBleService r4 = com.example.ble.CalisBleService.this
                com.example.ble.BleManager r4 = com.example.ble.CalisBleService.access$getBleManager$p(r4)
                java.lang.String r5 = "bleManager"
                r6 = 0
                if (r4 != 0) goto L44
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
                r4 = r6
            L44:
                boolean r4 = r4.isBluetoothEnabled()
                if (r4 == 0) goto L1f
                com.example.ble.CalisBleService r4 = com.example.ble.CalisBleService.this
                com.example.ble.BleManager r4 = com.example.ble.CalisBleService.access$getBleManager$p(r4)
                if (r4 != 0) goto L56
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
                r4 = r6
            L56:
                kotlinx.coroutines.flow.StateFlow r4 = r4.getConnectionState()
                java.lang.Object r4 = r4.getValue()
                com.example.ble.BleManager$ConnectionState r4 = (com.example.ble.BleManager.ConnectionState) r4
                boolean r7 = r4 instanceof com.example.ble.BleManager.ConnectionState.Disconnected
                if (r7 == 0) goto L1f
                com.example.ble.CalisBleService r7 = com.example.ble.CalisBleService.this
                com.example.ble.BleManager r7 = com.example.ble.CalisBleService.access$getBleManager$p(r7)
                if (r7 != 0) goto L70
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
                r7 = r6
            L70:
                r5 = 0
                com.example.ble.BleManager.startScan$default(r7, r5, r3, r6)
                com.example.ble.CalisBleService r5 = com.example.ble.CalisBleService.this
                r5.updateToolbarNotification()
                goto L1f
            L7a:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.ble.CalisBleService.AnonymousClass7.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:59:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:61:0x0101  */
    /* JADX WARN: Code duplicated, block: B:63:0x010d  */
    /* JADX WARN: Code duplicated, block: B:67:0x0118  */
    /* JADX WARN: Code duplicated, block: B:70:0x011d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:71:0x011f  */
    /* JADX WARN: Code duplicated, block: B:73:0x0127 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:74:0x0129  */
    /* JADX WARN: Code duplicated, block: B:77:0x0133  */
    /* JADX WARN: Code duplicated, block: B:79:0x0137  */
    /* JADX WARN: Code duplicated, block: B:83:0x0142  */
    /* JADX WARN: Code duplicated, block: B:84:0x0146  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void handleAction(String action) {
        Object next;
        BleManager.ScannedDevice found;
        BleManager bleManager;
        BleManager bleManager2;
        BleManager bleManager3;
        BleManager bleManager4;
        boolean z;
        String classroomName;
        Log.d(TAG, "Handling intent action: " + action);
        boolean z2 = false;
        BleManager bleManager5 = null;
        switch (action.hashCode()) {
            case -2072342153:
                if (action.equals(ACTION_ENABLE_AND_START_SESSION)) {
                    Object systemService = getSystemService("bluetooth");
                    BluetoothManager btManager = systemService instanceof BluetoothManager ? (BluetoothManager) systemService : null;
                    BluetoothAdapter adapter = btManager != null ? btManager.getAdapter() : null;
                    if (adapter != null) {
                        try {
                            if (adapter.isEnabled()) {
                                z2 = true;
                            }
                            if (!z2 && adapter != null) {
                                adapter.enable();
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "Error enabling bluetooth adapter: " + e.getMessage());
                        }
                    } else if (!z2) {
                        adapter.enable();
                    }
                    BleManager bleManager6 = this.bleManager;
                    if (bleManager6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                    } else {
                        bleManager5 = bleManager6;
                    }
                    bleManager5.setPendingAction("START_SESSION");
                    updateToolbarNotificationImmediate();
                    break;
                }
                break;
            case -1283928457:
                if (action.equals(ACTION_START_SESSION)) {
                    this.isSessionActiveOverride = true;
                    BleManager bleManager7 = this.bleManager;
                    if (bleManager7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager7 = null;
                    }
                    bleManager7.setPendingAction("START_SESSION");
                    updateToolbarNotificationImmediate();
                    BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AnonymousClass1(null), 3, null);
                    break;
                }
                break;
            case -374593954:
                if (action.equals(ACTION_DISCONNECT)) {
                    BleManager bleManager8 = this.bleManager;
                    if (bleManager8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager8 = null;
                    }
                    bleManager8.setPendingAction("DISCONNECT");
                    BleManager bleManager9 = this.bleManager;
                    if (bleManager9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager9 = null;
                    }
                    bleManager9.setExplicitlyDisconnected(true);
                    this.isSessionActiveOverride = false;
                    BleManager bleManager10 = this.bleManager;
                    if (bleManager10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager10 = null;
                    }
                    bleManager10.disconnect();
                    BleManager bleManager11 = this.bleManager;
                    if (bleManager11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager11 = null;
                    }
                    this.currentConnectionState = bleManager11.getConnectionState().getValue();
                    BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AnonymousClass3(null), 3, null);
                    updateToolbarNotificationImmediate();
                    break;
                }
                break;
            case 391458160:
                if (action.equals(ACTION_END_SESSION)) {
                    this.isSessionActiveOverride = false;
                    BleManager bleManager12 = this.bleManager;
                    if (bleManager12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager12 = null;
                    }
                    bleManager12.setPendingAction("END_SESSION");
                    updateToolbarNotificationImmediate();
                    BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AnonymousClass2(null), 3, null);
                    break;
                }
                break;
            case 918704282:
                if (action.equals(ACTION_STOP_SERVICE)) {
                    stopForeground(1);
                    stopSelf();
                    Process.killProcess(Process.myPid());
                    System.exit(0);
                    break;
                }
                break;
            case 1890732616:
                if (action.equals(ACTION_CONNECT)) {
                    BleManager bleManager13 = this.bleManager;
                    if (bleManager13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager13 = null;
                    }
                    bleManager13.setPendingAction("CONNECT");
                    BleManager bleManager14 = this.bleManager;
                    if (bleManager14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager14 = null;
                    }
                    bleManager14.setExplicitlyDisconnected(false);
                    this.currentConnectionState = BleManager.ConnectionState.Connecting.INSTANCE;
                    updateToolbarNotificationImmediate();
                    ClassroomEntity classroom = this.currentClassroom;
                    String targetMac = classroom != null ? classroom.getDeviceAddress() : null;
                    BleManager bleManager15 = this.bleManager;
                    if (bleManager15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        bleManager15 = null;
                    }
                    Iterator<T> it = bleManager15.getScannedDevices().getValue().iterator();
                    do {
                        if (it.hasNext()) {
                            next = it.next();
                            BleManager.ScannedDevice scannedDevice = (BleManager.ScannedDevice) next;
                            if (targetMac != null && StringsKt.equals(scannedDevice.getAddress(), targetMac, true)) {
                                z = true;
                            } else if ((((classroom == null || (classroomName = classroom.getClassroomName()) == null || !(StringsKt.isBlank(classroomName) ^ true)) ? false : true) && StringsKt.contains((CharSequence) scannedDevice.getName(), (CharSequence) classroom.getClassroomName(), true)) || StringsKt.contains((CharSequence) scannedDevice.getName(), (CharSequence) "Calis", true)) {
                                z = true;
                            } else {
                                BleManager bleManager16 = this.bleManager;
                                if (bleManager16 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                                    bleManager16 = null;
                                }
                                if (bleManager16.getIsSimulatedMode() && Intrinsics.areEqual(scannedDevice.getAddress(), "VIRTUAL_CALIS_DEVICE")) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                            }
                        } else {
                            next = null;
                        }
                        found = (BleManager.ScannedDevice) next;
                        if (found != null) {
                            bleManager4 = this.bleManager;
                            if (bleManager4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                                bleManager4 = null;
                            }
                            bleManager4.connect(found.getAddress());
                        } else {
                            String str = targetMac;
                            z2 = str != null || StringsKt.isBlank(str);
                            bleManager = this.bleManager;
                            if (z2) {
                                if (bleManager == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                                    bleManager = null;
                                }
                                if (bleManager.getIsSimulatedMode()) {
                                    bleManager2 = this.bleManager;
                                    if (bleManager2 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                                        bleManager2 = null;
                                    }
                                    bleManager2.connect("VIRTUAL_CALIS_DEVICE");
                                }
                            } else {
                                if (bleManager == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                                    bleManager = null;
                                }
                                bleManager.connect(targetMac);
                            }
                        }
                        bleManager3 = this.bleManager;
                        if (bleManager3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        } else {
                            bleManager5 = bleManager3;
                        }
                        this.currentConnectionState = bleManager5.getConnectionState().getValue();
                        updateToolbarNotificationImmediate();
                        break;
                    } while (!z);
                    found = (BleManager.ScannedDevice) next;
                    if (found != null) {
                        bleManager4 = this.bleManager;
                        if (bleManager4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                            bleManager4 = null;
                        }
                        bleManager4.connect(found.getAddress());
                    } else {
                        String str2 = targetMac;
                        if (str2 != null) {
                        }
                        bleManager = this.bleManager;
                        if (z2) {
                            if (bleManager == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                                bleManager = null;
                            }
                            bleManager.connect(targetMac);
                        } else {
                            if (bleManager == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                                bleManager = null;
                            }
                            if (bleManager.getIsSimulatedMode()) {
                                bleManager2 = this.bleManager;
                                if (bleManager2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                                    bleManager2 = null;
                                }
                                bleManager2.connect("VIRTUAL_CALIS_DEVICE");
                            }
                        }
                    }
                    bleManager3 = this.bleManager;
                    if (bleManager3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                    } else {
                        bleManager5 = bleManager3;
                    }
                    this.currentConnectionState = bleManager5.getConnectionState().getValue();
                    updateToolbarNotificationImmediate();
                    break;
                }
                break;
            case 2126542111:
                if (action.equals(ACTION_UPDATE_NOTIFICATION)) {
                    updateToolbarNotificationImmediate();
                    break;
                }
                break;
        }
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$handleAction$1, reason: invalid class name */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$handleAction$1", f = "CalisBleService.kt", i = {1, 2}, l = {558, 559, 559}, m = "invokeSuspend", n = {"teacher", "teacher"}, s = {"L$0", "L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisBleService.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x0056  */
        /* JADX WARN: Code duplicated, block: B:20:0x006c A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:23:0x0071  */
        /* JADX WARN: Code duplicated, block: B:25:0x0079  */
        /* JADX WARN: Code duplicated, block: B:28:0x008f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:29:0x0090  */
        /* JADX WARN: Code duplicated, block: B:33:0x0098 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:36:0x00a4  */
        /* JADX WARN: Code duplicated, block: B:39:0x00b8  */
        /* JADX WARN: Code duplicated, block: B:41:0x00c0  */
        /* JADX WARN: Code duplicated, block: B:42:0x00c4  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object teacherOnce;
            TeacherEntity teacher;
            CalisDatabase calisDatabase;
            Object activeBoundClassroomSync;
            final ClassroomEntity classroom;
            CalisDatabase calisDatabase2;
            Object anyClassroomSync;
            TeacherEntity teacher2;
            BleManager bleManager;
            BleManager bleManager2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            BleManager bleManager3 = null;
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CalisDatabase calisDatabase3 = CalisBleService.this.database;
                    if (calisDatabase3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("database");
                        calisDatabase3 = null;
                    }
                    this.label = 1;
                    teacherOnce = calisDatabase3.teacherDao().getTeacherOnce(this);
                    if (teacherOnce == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    teacher = (TeacherEntity) teacherOnce;
                    calisDatabase = CalisBleService.this.database;
                    if (calisDatabase == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("database");
                        calisDatabase = null;
                    }
                    this.L$0 = teacher;
                    this.label = 2;
                    activeBoundClassroomSync = calisDatabase.classroomDao().getActiveBoundClassroomSync(this);
                    if (activeBoundClassroomSync == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    classroom = (ClassroomEntity) activeBoundClassroomSync;
                    if (classroom == null) {
                        calisDatabase2 = CalisBleService.this.database;
                        if (calisDatabase2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("database");
                            calisDatabase2 = null;
                        }
                        this.L$0 = teacher;
                        this.label = 3;
                        anyClassroomSync = calisDatabase2.classroomDao().getAnyClassroomSync(this);
                        if (anyClassroomSync == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        teacher2 = teacher;
                        classroom = (ClassroomEntity) anyClassroomSync;
                        teacher = teacher2;
                    }
                    if (teacher != null && classroom != null) {
                        bleManager = CalisBleService.this.bleManager;
                        if (bleManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                            bleManager = null;
                        }
                        if (bleManager.isAuthenticated().getValue().booleanValue()) {
                            bleManager2 = CalisBleService.this.bleManager;
                            if (bleManager2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                            } else {
                                bleManager3 = bleManager2;
                            }
                            BlePacket.StartSessionCommand startSessionCommand = BlePacket.StartSessionCommand.INSTANCE;
                            final CalisBleService calisBleService = CalisBleService.this;
                            bleManager3.sendPacket(startSessionCommand, new Function1() { // from class: com.example.ble.CalisBleService$handleAction$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return CalisBleService.AnonymousClass1.invokeSuspend$lambda$0(calisBleService, classroom, ((Boolean) obj).booleanValue());
                                }
                            });
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    teacherOnce = $result;
                    teacher = (TeacherEntity) teacherOnce;
                    calisDatabase = CalisBleService.this.database;
                    if (calisDatabase == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("database");
                        calisDatabase = null;
                    }
                    this.L$0 = teacher;
                    this.label = 2;
                    activeBoundClassroomSync = calisDatabase.classroomDao().getActiveBoundClassroomSync(this);
                    if (activeBoundClassroomSync == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    classroom = (ClassroomEntity) activeBoundClassroomSync;
                    if (classroom == null) {
                        calisDatabase2 = CalisBleService.this.database;
                        if (calisDatabase2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("database");
                            calisDatabase2 = null;
                        }
                        this.L$0 = teacher;
                        this.label = 3;
                        anyClassroomSync = calisDatabase2.classroomDao().getAnyClassroomSync(this);
                        if (anyClassroomSync == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        teacher2 = teacher;
                        classroom = (ClassroomEntity) anyClassroomSync;
                        teacher = teacher2;
                    }
                    if (teacher != null) {
                        bleManager = CalisBleService.this.bleManager;
                        if (bleManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                            bleManager = null;
                        }
                        if (bleManager.isAuthenticated().getValue().booleanValue()) {
                            bleManager2 = CalisBleService.this.bleManager;
                            if (bleManager2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                            } else {
                                bleManager3 = bleManager2;
                            }
                            BlePacket.StartSessionCommand startSessionCommand2 = BlePacket.StartSessionCommand.INSTANCE;
                            final CalisBleService calisBleService2 = CalisBleService.this;
                            bleManager3.sendPacket(startSessionCommand2, new Function1() { // from class: com.example.ble.CalisBleService$handleAction$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return CalisBleService.AnonymousClass1.invokeSuspend$lambda$0(calisBleService2, classroom, ((Boolean) obj).booleanValue());
                                }
                            });
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    teacher = (TeacherEntity) this.L$0;
                    ResultKt.throwOnFailure($result);
                    activeBoundClassroomSync = $result;
                    classroom = (ClassroomEntity) activeBoundClassroomSync;
                    if (classroom == null) {
                        calisDatabase2 = CalisBleService.this.database;
                        if (calisDatabase2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("database");
                            calisDatabase2 = null;
                        }
                        this.L$0 = teacher;
                        this.label = 3;
                        anyClassroomSync = calisDatabase2.classroomDao().getAnyClassroomSync(this);
                        if (anyClassroomSync == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        teacher2 = teacher;
                        classroom = (ClassroomEntity) anyClassroomSync;
                        teacher = teacher2;
                    }
                    if (teacher != null) {
                        bleManager = CalisBleService.this.bleManager;
                        if (bleManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                            bleManager = null;
                        }
                        if (bleManager.isAuthenticated().getValue().booleanValue()) {
                            bleManager2 = CalisBleService.this.bleManager;
                            if (bleManager2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                            } else {
                                bleManager3 = bleManager2;
                            }
                            BlePacket.StartSessionCommand startSessionCommand3 = BlePacket.StartSessionCommand.INSTANCE;
                            final CalisBleService calisBleService3 = CalisBleService.this;
                            bleManager3.sendPacket(startSessionCommand3, new Function1() { // from class: com.example.ble.CalisBleService$handleAction$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return CalisBleService.AnonymousClass1.invokeSuspend$lambda$0(calisBleService3, classroom, ((Boolean) obj).booleanValue());
                                }
                            });
                        }
                    }
                    return Unit.INSTANCE;
                case 3:
                    teacher2 = (TeacherEntity) this.L$0;
                    ResultKt.throwOnFailure($result);
                    anyClassroomSync = $result;
                    classroom = (ClassroomEntity) anyClassroomSync;
                    teacher = teacher2;
                    if (teacher != null) {
                        bleManager = CalisBleService.this.bleManager;
                        if (bleManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                            bleManager = null;
                        }
                        if (bleManager.isAuthenticated().getValue().booleanValue()) {
                            bleManager2 = CalisBleService.this.bleManager;
                            if (bleManager2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                            } else {
                                bleManager3 = bleManager2;
                            }
                            BlePacket.StartSessionCommand startSessionCommand4 = BlePacket.StartSessionCommand.INSTANCE;
                            final CalisBleService calisBleService4 = CalisBleService.this;
                            bleManager3.sendPacket(startSessionCommand4, new Function1() { // from class: com.example.ble.CalisBleService$handleAction$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return CalisBleService.AnonymousClass1.invokeSuspend$lambda$0(calisBleService4, classroom, ((Boolean) obj).booleanValue());
                                }
                            });
                        }
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        static final Unit invokeSuspend$lambda$0(CalisBleService this$0, ClassroomEntity $classroom, boolean success) {
            BuildersKt__Builders_commonKt.launch$default(this$0.serviceScope, null, null, new CalisBleService$handleAction$1$1$1(success, this$0, $classroom, null), 3, null);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$handleAction$2, reason: invalid class name */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$handleAction$2", f = "CalisBleService.kt", i = {1, 2}, l = {580, 581, 581}, m = "invokeSuspend", n = {"teacher", "teacher"}, s = {"L$0", "L$0"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisBleService.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x0056  */
        /* JADX WARN: Code duplicated, block: B:20:0x006c A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:23:0x0071  */
        /* JADX WARN: Code duplicated, block: B:25:0x0079  */
        /* JADX WARN: Code duplicated, block: B:28:0x008f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:29:0x0090  */
        /* JADX WARN: Code duplicated, block: B:33:0x0098 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:36:0x00a2  */
        /* JADX WARN: Code duplicated, block: B:37:0x00a8  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object teacherOnce;
            TeacherEntity teacher;
            CalisDatabase calisDatabase;
            Object activeBoundClassroomSync;
            final ClassroomEntity classroom;
            CalisDatabase calisDatabase2;
            Object anyClassroomSync;
            TeacherEntity teacher2;
            BleManager bleManager;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            BleManager bleManager2 = null;
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CalisDatabase calisDatabase3 = CalisBleService.this.database;
                    if (calisDatabase3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("database");
                        calisDatabase3 = null;
                    }
                    this.label = 1;
                    teacherOnce = calisDatabase3.teacherDao().getTeacherOnce(this);
                    if (teacherOnce == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    teacher = (TeacherEntity) teacherOnce;
                    calisDatabase = CalisBleService.this.database;
                    if (calisDatabase == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("database");
                        calisDatabase = null;
                    }
                    this.L$0 = teacher;
                    this.label = 2;
                    activeBoundClassroomSync = calisDatabase.classroomDao().getActiveBoundClassroomSync(this);
                    if (activeBoundClassroomSync == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    classroom = (ClassroomEntity) activeBoundClassroomSync;
                    if (classroom == null) {
                        calisDatabase2 = CalisBleService.this.database;
                        if (calisDatabase2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("database");
                            calisDatabase2 = null;
                        }
                        this.L$0 = teacher;
                        this.label = 3;
                        anyClassroomSync = calisDatabase2.classroomDao().getAnyClassroomSync(this);
                        if (anyClassroomSync == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        teacher2 = teacher;
                        classroom = (ClassroomEntity) anyClassroomSync;
                        teacher = teacher2;
                    }
                    if (teacher != null && classroom != null) {
                        bleManager = CalisBleService.this.bleManager;
                        if (bleManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        } else {
                            bleManager2 = bleManager;
                        }
                        BlePacket.EndSessionCommand endSessionCommand = BlePacket.EndSessionCommand.INSTANCE;
                        final CalisBleService calisBleService = CalisBleService.this;
                        bleManager2.sendPacket(endSessionCommand, new Function1() { // from class: com.example.ble.CalisBleService$handleAction$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return CalisBleService.AnonymousClass2.invokeSuspend$lambda$0(calisBleService, classroom, ((Boolean) obj).booleanValue());
                            }
                        });
                    }
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    teacherOnce = $result;
                    teacher = (TeacherEntity) teacherOnce;
                    calisDatabase = CalisBleService.this.database;
                    if (calisDatabase == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("database");
                        calisDatabase = null;
                    }
                    this.L$0 = teacher;
                    this.label = 2;
                    activeBoundClassroomSync = calisDatabase.classroomDao().getActiveBoundClassroomSync(this);
                    if (activeBoundClassroomSync == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    classroom = (ClassroomEntity) activeBoundClassroomSync;
                    if (classroom == null) {
                        calisDatabase2 = CalisBleService.this.database;
                        if (calisDatabase2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("database");
                            calisDatabase2 = null;
                        }
                        this.L$0 = teacher;
                        this.label = 3;
                        anyClassroomSync = calisDatabase2.classroomDao().getAnyClassroomSync(this);
                        if (anyClassroomSync == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        teacher2 = teacher;
                        classroom = (ClassroomEntity) anyClassroomSync;
                        teacher = teacher2;
                    }
                    if (teacher != null) {
                        bleManager = CalisBleService.this.bleManager;
                        if (bleManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        } else {
                            bleManager2 = bleManager;
                        }
                        BlePacket.EndSessionCommand endSessionCommand2 = BlePacket.EndSessionCommand.INSTANCE;
                        final CalisBleService calisBleService2 = CalisBleService.this;
                        bleManager2.sendPacket(endSessionCommand2, new Function1() { // from class: com.example.ble.CalisBleService$handleAction$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return CalisBleService.AnonymousClass2.invokeSuspend$lambda$0(calisBleService2, classroom, ((Boolean) obj).booleanValue());
                            }
                        });
                    }
                    return Unit.INSTANCE;
                case 2:
                    teacher = (TeacherEntity) this.L$0;
                    ResultKt.throwOnFailure($result);
                    activeBoundClassroomSync = $result;
                    classroom = (ClassroomEntity) activeBoundClassroomSync;
                    if (classroom == null) {
                        calisDatabase2 = CalisBleService.this.database;
                        if (calisDatabase2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("database");
                            calisDatabase2 = null;
                        }
                        this.L$0 = teacher;
                        this.label = 3;
                        anyClassroomSync = calisDatabase2.classroomDao().getAnyClassroomSync(this);
                        if (anyClassroomSync == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        teacher2 = teacher;
                        classroom = (ClassroomEntity) anyClassroomSync;
                        teacher = teacher2;
                    }
                    if (teacher != null) {
                        bleManager = CalisBleService.this.bleManager;
                        if (bleManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        } else {
                            bleManager2 = bleManager;
                        }
                        BlePacket.EndSessionCommand endSessionCommand3 = BlePacket.EndSessionCommand.INSTANCE;
                        final CalisBleService calisBleService3 = CalisBleService.this;
                        bleManager2.sendPacket(endSessionCommand3, new Function1() { // from class: com.example.ble.CalisBleService$handleAction$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return CalisBleService.AnonymousClass2.invokeSuspend$lambda$0(calisBleService3, classroom, ((Boolean) obj).booleanValue());
                            }
                        });
                    }
                    return Unit.INSTANCE;
                case 3:
                    teacher2 = (TeacherEntity) this.L$0;
                    ResultKt.throwOnFailure($result);
                    anyClassroomSync = $result;
                    classroom = (ClassroomEntity) anyClassroomSync;
                    teacher = teacher2;
                    if (teacher != null) {
                        bleManager = CalisBleService.this.bleManager;
                        if (bleManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        } else {
                            bleManager2 = bleManager;
                        }
                        BlePacket.EndSessionCommand endSessionCommand4 = BlePacket.EndSessionCommand.INSTANCE;
                        final CalisBleService calisBleService4 = CalisBleService.this;
                        bleManager2.sendPacket(endSessionCommand4, new Function1() { // from class: com.example.ble.CalisBleService$handleAction$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return CalisBleService.AnonymousClass2.invokeSuspend$lambda$0(calisBleService4, classroom, ((Boolean) obj).booleanValue());
                            }
                        });
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        static final Unit invokeSuspend$lambda$0(CalisBleService this$0, ClassroomEntity $classroom, boolean success) {
            BuildersKt__Builders_commonKt.launch$default(this$0.serviceScope, null, null, new CalisBleService$handleAction$2$1$1(success, this$0, $classroom, null), 3, null);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$handleAction$3, reason: invalid class name */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$handleAction$3", f = "CalisBleService.kt", i = {0}, l = {630}, m = "invokeSuspend", n = {"classroom"}, s = {"L$0"})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisBleService.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            ClassroomEntity classroom;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    ClassroomEntity classroom2 = CalisBleService.this.currentClassroom;
                    if (classroom2 != null && classroom2.isActiveSession()) {
                        CalisDatabase calisDatabase = CalisBleService.this.database;
                        if (calisDatabase == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("database");
                            calisDatabase = null;
                        }
                        this.L$0 = SpillingKt.nullOutSpilledVariable(classroom2);
                        this.label = 1;
                        if (calisDatabase.classroomDao().updateClassroom(ClassroomEntity.copy$default(classroom2, null, null, null, null, null, null, null, null, false, false, null, 1791, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        classroom = classroom2;
                    }
                    return Unit.INSTANCE;
                case 1:
                    classroom = (ClassroomEntity) this.L$0;
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final void startForegroundWithToolbar() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("calis_auth_prefs", 0);
        prefs.getString("user_role", null);
        BlePermissionUtils blePermissionUtils = BlePermissionUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        if (!blePermissionUtils.hasPermissions(applicationContext)) {
            Log.e(TAG, "Cannot start foreground service: BLUETOOTH_CONNECT permission is missing.");
            stopSelf();
            return;
        }
        try {
            Pair<Notification, String> pairBuildToolbarNotificationWithFingerprint = buildToolbarNotificationWithFingerprint();
            Notification notification = pairBuildToolbarNotificationWithFingerprint.component1();
            String fingerprint = pairBuildToolbarNotificationWithFingerprint.component2();
            this.lastPostedFingerprint = fingerprint;
            this.lastNotificationPostTime = SystemClock.elapsedRealtime();
            if (Build.VERSION.SDK_INT >= 29) {
                try {
                    startForeground(1001, notification, 16);
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable e) {
                    Log.e(TAG, "Failed startForeground with type connectedDevice: " + e.getMessage(), e);
                    try {
                        startForeground(1001, notification);
                        Unit unit2 = Unit.INSTANCE;
                    } catch (Throwable e2) {
                        Integer.valueOf(Log.e(TAG, "Failed fallback startForeground: " + e2.getMessage(), e2));
                    }
                }
            } else {
                try {
                    startForeground(1001, notification);
                    Unit unit3 = Unit.INSTANCE;
                } catch (Throwable e3) {
                    Integer.valueOf(Log.e(TAG, "Failed startForeground legacy: " + e3.getMessage(), e3));
                }
            }
            Object systemService = getSystemService("notification");
            NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
            if (notificationManager != null) {
                notificationManager.notify(1001, notification);
            }
        } catch (Throwable e4) {
            Log.e(TAG, "Error in startForegroundWithToolbar: " + e4.getMessage(), e4);
        }
    }

    public final void setSessionActiveState(boolean isActive) {
        this.isSessionActiveOverride = Boolean.valueOf(isActive);
        updateToolbarNotificationImmediate();
        updateWakeLock();
    }

    public final void updateToolbarNotificationImmediate() {
        Job job = this.pendingNotificationJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.pendingNotificationJob = null;
        postNotificationNow();
    }

    public final void updateToolbarNotification() {
        long now = SystemClock.elapsedRealtime();
        long timeSinceLast = now - this.lastNotificationPostTime;
        long j = this.NOTIFICATION_MIN_INTERVAL_MS;
        Job job = this.pendingNotificationJob;
        if (timeSinceLast >= j) {
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.pendingNotificationJob = null;
            postNotificationNow();
            return;
        }
        if (job != null) {
            Job job2 = this.pendingNotificationJob;
            if (job2 != null && job2.isActive()) {
                return;
            }
        }
        long delayMs = this.NOTIFICATION_MIN_INTERVAL_MS - timeSinceLast;
        this.pendingNotificationJob = BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new C07581(delayMs, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.example.ble.CalisBleService$updateToolbarNotification$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisBleService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.ble.CalisBleService$updateToolbarNotification$1", f = "CalisBleService.kt", i = {}, l = {719}, m = "invokeSuspend", n = {}, s = {})
    static final class C07581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayMs;
        int label;
        final /* synthetic */ CalisBleService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07581(long j, CalisBleService calisBleService, Continuation<? super C07581> continuation) {
            super(2, continuation);
            this.$delayMs = j;
            this.this$0 = calisBleService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07581(this.$delayMs, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07581) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(this.$delayMs, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            this.this$0.postNotificationNow();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void postNotificationNow() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("calis_auth_prefs", 0);
        String userRole = prefs.getString("user_role", null);
        if (Intrinsics.areEqual(userRole, "TEACHER")) {
            try {
                Pair<Notification, String> pairBuildToolbarNotificationWithFingerprint = buildToolbarNotificationWithFingerprint();
                Notification notification = pairBuildToolbarNotificationWithFingerprint.component1();
                String fingerprint = pairBuildToolbarNotificationWithFingerprint.component2();
                if (Intrinsics.areEqual(fingerprint, this.lastPostedFingerprint)) {
                    return;
                }
                this.lastPostedFingerprint = fingerprint;
                this.lastNotificationPostTime = SystemClock.elapsedRealtime();
                Object systemService = getSystemService("notification");
                NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
                if (notificationManager != null) {
                    notificationManager.notify(1001, notification);
                }
            } catch (Throwable e) {
                Log.e(TAG, "Error in postNotificationNow: " + e.getMessage(), e);
            }
        }
    }

    private final boolean isBluetoothHardwareEnabled() {
        Object systemService = getSystemService("bluetooth");
        BleManager bleManager = null;
        BluetoothManager btManager = systemService instanceof BluetoothManager ? (BluetoothManager) systemService : null;
        BluetoothAdapter adapter = btManager != null ? btManager.getAdapter() : null;
        if (adapter != null && adapter.isEnabled()) {
            return true;
        }
        BleManager bleManager2 = this.bleManager;
        if (bleManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
        } else {
            bleManager = bleManager2;
        }
        return bleManager.isBluetoothEnabled();
    }

    private final Notification buildToolbarNotification() {
        return buildToolbarNotificationWithFingerprint().getFirst();
    }

    /* JADX WARN: Code duplicated, block: B:121:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:123:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:129:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:131:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:132:0x01de A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:133:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:134:0x01e2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:135:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:136:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:139:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:143:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:145:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:149:0x021b  */
    /* JADX WARN: Code duplicated, block: B:150:0x021e  */
    /* JADX WARN: Code duplicated, block: B:153:0x0228  */
    /* JADX WARN: Code duplicated, block: B:154:0x022b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:155:0x022d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:156:0x022f  */
    /* JADX WARN: Code duplicated, block: B:157:0x0232  */
    /* JADX WARN: Code duplicated, block: B:159:0x0237 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:160:0x0239  */
    /* JADX WARN: Code duplicated, block: B:161:0x023c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:162:0x023e  */
    /* JADX WARN: Code duplicated, block: B:163:0x0241  */
    /* JADX WARN: Code duplicated, block: B:166:0x0248 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:167:0x024a  */
    /* JADX WARN: Code duplicated, block: B:168:0x024d  */
    /* JADX WARN: Code duplicated, block: B:171:0x0256  */
    /* JADX WARN: Code duplicated, block: B:172:0x0259 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:173:0x025b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:175:0x025e  */
    /* JADX WARN: Code duplicated, block: B:176:0x0261 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:177:0x0263  */
    /* JADX WARN: Code duplicated, block: B:178:0x0266 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:179:0x0268  */
    /* JADX WARN: Code duplicated, block: B:180:0x026b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:181:0x026d  */
    /* JADX WARN: Code duplicated, block: B:182:0x0270  */
    /* JADX WARN: Code duplicated, block: B:186:0x03a8  */
    /* JADX WARN: Code duplicated, block: B:188:0x03ab  */
    /* JADX WARN: Code duplicated, block: B:189:0x03ae  */
    /* JADX WARN: Code duplicated, block: B:192:0x03e7  */
    /* JADX WARN: Code duplicated, block: B:193:0x03ea  */
    /* JADX WARN: Code duplicated, block: B:197:0x03f5  */
    /* JADX WARN: Code duplicated, block: B:200:0x040d  */
    /* JADX WARN: Code duplicated, block: B:201:0x0414  */
    /* JADX WARN: Code duplicated, block: B:204:0x041d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:205:0x041f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:207:0x0426 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:208:0x0428  */
    /* JADX WARN: Code duplicated, block: B:209:0x042f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:210:0x0431  */
    /* JADX WARN: Code duplicated, block: B:211:0x0436  */
    /* JADX WARN: Code duplicated, block: B:212:0x0438 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:214:0x043f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:219:0x044e  */
    /* JADX WARN: Code duplicated, block: B:220:0x0451  */
    /* JADX WARN: Code duplicated, block: B:223:0x0458 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:224:0x045a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:226:0x045f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:227:0x0461  */
    /* JADX WARN: Code duplicated, block: B:228:0x0464 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:229:0x0466  */
    /* JADX WARN: Code duplicated, block: B:230:0x0469  */
    /* JADX WARN: Code duplicated, block: B:231:0x046b A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:233:0x0470 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:240:0x0483  */
    /* JADX WARN: Code duplicated, block: B:252:0x049e  */
    /* JADX WARN: Code duplicated, block: B:255:0x04a3  */
    /* JADX WARN: Code duplicated, block: B:256:0x04a8  */
    /* JADX WARN: Code duplicated, block: B:262:0x04b8  */
    /* JADX WARN: Code duplicated, block: B:266:0x0514 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:267:0x0516  */
    /* JADX WARN: Code duplicated, block: B:268:0x0519  */
    /* JADX WARN: Code duplicated, block: B:274:0x0525  */
    /* JADX WARN: Code duplicated, block: B:280:0x0533  */
    /* JADX WARN: Code duplicated, block: B:283:0x056b  */
    /* JADX WARN: Code duplicated, block: B:284:0x056e  */
    /* JADX WARN: Code duplicated, block: B:287:0x0574  */
    /* JADX WARN: Code duplicated, block: B:288:0x0579  */
    /* JADX WARN: Code duplicated, block: B:291:0x0581  */
    /* JADX WARN: Code duplicated, block: B:292:0x0584  */
    /* JADX WARN: Code duplicated, block: B:295:0x05a4  */
    /* JADX WARN: Code duplicated, block: B:296:0x05a7  */
    /* JADX WARN: Code duplicated, block: B:298:0x05ab  */
    /* JADX WARN: Code duplicated, block: B:299:0x05b0  */
    /* JADX WARN: Code duplicated, block: B:302:0x05b8  */
    /* JADX WARN: Code duplicated, block: B:303:0x05bd  */
    /* JADX WARN: Code duplicated, block: B:305:0x05c3  */
    /* JADX WARN: Code duplicated, block: B:306:0x05c6  */
    /* JADX WARN: Code duplicated, block: B:309:0x05cc  */
    /* JADX WARN: Code duplicated, block: B:310:0x05cf  */
    /* JADX WARN: Code duplicated, block: B:313:0x05d5  */
    /* JADX WARN: Code duplicated, block: B:314:0x05da  */
    /* JADX WARN: Code duplicated, block: B:317:0x05e2  */
    /* JADX WARN: Code duplicated, block: B:318:0x05e7  */
    /* JADX WARN: Code duplicated, block: B:321:0x05ef  */
    /* JADX WARN: Code duplicated, block: B:322:0x05f2  */
    /* JADX WARN: Code duplicated, block: B:328:0x0636  */
    /* JADX WARN: Code duplicated, block: B:334:0x0651  */
    /* JADX WARN: Code duplicated, block: B:337:0x0687  */
    /* JADX WARN: Code duplicated, block: B:338:0x06bb  */
    /* JADX WARN: Code duplicated, block: B:344:0x06d1  */
    /* JADX WARN: Code duplicated, block: B:346:0x06d4  */
    /* JADX WARN: Code duplicated, block: B:347:0x06d6  */
    /* JADX WARN: Code duplicated, block: B:352:0x06fa  */
    /* JADX WARN: Code duplicated, block: B:357:0x0705  */
    /* JADX WARN: Code duplicated, block: B:74:0x0122 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:77:0x0127  */
    private final Pair<Notification, String> buildToolbarNotificationWithFingerprint() {
        boolean isSessionActive;
        String classroomName;
        ClassroomEntity classroom;
        boolean isConnected;
        Object next;
        String rawDeviceName;
        BleManager.ConnectionState connState;
        boolean isFactoryDefaultName;
        boolean z;
        String classroomName2;
        String str;
        boolean z2;
        String str2;
        String subText;
        String availabilityText;
        String str3;
        String str4;
        boolean isRecentlyActive;
        PendingIntent startSessionPendingIntent;
        PendingIntent endSessionPendingIntent;
        PendingIntent connectPendingIntent;
        RemoteViews compactViews;
        RemoteViews expandedViews;
        String str5;
        String str6;
        int badgeColorInt;
        int badgeBgRes;
        int color;
        boolean isDeviceActive;
        int color2;
        int color3;
        boolean isControlsEnabled;
        int i;
        int color4;
        int connectBgRes;
        int color5;
        PendingIntent pendingIntent;
        int startSessionBg;
        int color6;
        int startSessionIconColor;
        PendingIntent pendingIntent2;
        int i2;
        int color7;
        int color8;
        PendingIntent pendingIntent3;
        PendingIntent pendingIntent4;
        PendingIntent pendingIntent5;
        String uRole;
        boolean isDisconnectVisible;
        boolean isConnectVisible;
        boolean showSessionControls;
        boolean z3;
        boolean z4;
        String classroomName3;
        boolean z5;
        boolean z6;
        String targetMac;
        boolean z7;
        boolean zEquals;
        ClassroomEntity classroom2 = this.currentClassroom;
        boolean isBluetoothEnabled = isBluetoothHardwareEnabled();
        BleManager.ConnectionState connState2 = this.currentConnectionState;
        boolean isConnected2 = connState2 instanceof BleManager.ConnectionState.Connected;
        boolean z8 = connState2 instanceof BleManager.ConnectionState.Scanning;
        boolean isConnecting = connState2 instanceof BleManager.ConnectionState.Connecting;
        boolean isDisconnecting = connState2 instanceof BleManager.ConnectionState.Disconnecting;
        boolean isAuth = this.currentIsAuthenticated;
        DeviceState devState = this.currentDeviceState;
        Boolean bool = this.isSessionActiveOverride;
        if (bool != null) {
            isSessionActive = bool.booleanValue();
        } else {
            isSessionActive = (classroom2 != null && classroom2.isActiveSession()) || devState == DeviceState.SESSION_ACTIVE_OPEN || devState == DeviceState.SESSION_ACTIVE_BUSY;
        }
        BleManager bleManager = this.bleManager;
        if (bleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            bleManager = null;
        }
        List<BleManager.ScannedDevice> value = bleManager.getScannedDevices().getValue();
        boolean isControlsEnabled2 = isBluetoothEnabled && isConnected2 && isAuth && (devState == DeviceState.SESSION_INACTIVE || devState == DeviceState.SESSION_ACTIVE_OPEN || devState == DeviceState.SESSION_ACTIVE_BUSY || devState == DeviceState.AUTHENTICATE);
        if (classroom2 == null || (classroomName = classroom2.getClassroomName()) == null) {
            classroomName = "Calis Classroom";
        }
        String classroomName4 = classroomName;
        String targetMac2 = classroom2 != null ? classroom2.getDeviceAddress() : null;
        long now = System.currentTimeMillis();
        Iterator<T> it = value.iterator();
        while (true) {
            if (!it.hasNext()) {
                classroom = classroom2;
                isConnected = isConnected2;
                next = null;
                break;
            }
            next = it.next();
            BleManager.ScannedDevice scannedDevice = (BleManager.ScannedDevice) next;
            String str7 = targetMac2;
            if (str7 == null || StringsKt.isBlank(str7)) {
                classroom = classroom2;
                isConnected = isConnected2;
                String targetMac3 = targetMac2;
                boolean hasServiceUuid = scannedDevice.getHasServiceUuid();
                if (StringsKt.isBlank(classroomName4)) {
                    z6 = hasServiceUuid;
                    targetMac = targetMac3;
                } else {
                    z6 = hasServiceUuid;
                    targetMac = targetMac3;
                    String targetMac4 = classroomName4;
                    if (StringsKt.equals(scannedDevice.getName(), targetMac4, true)) {
                        classroomName4 = targetMac4;
                    } else {
                        classroomName4 = targetMac4;
                        if (!StringsKt.contains((CharSequence) scannedDevice.getName(), (CharSequence) classroomName4, true)) {
                        }
                        if (!z6 || z7) {
                            zEquals = true;
                        } else {
                            zEquals = false;
                        }
                    }
                    if (z6) {
                        zEquals = true;
                    } else {
                        zEquals = true;
                    }
                }
                z7 = StringsKt.equals(scannedDevice.getName(), "Calis Classroom", true) || StringsKt.contains((CharSequence) scannedDevice.getName(), (CharSequence) "Calis", true);
                if (z6) {
                    zEquals = true;
                } else {
                    zEquals = true;
                }
            } else {
                classroom = classroom2;
                isConnected = isConnected2;
                String targetMac5 = targetMac2;
                zEquals = StringsKt.equals(scannedDevice.getAddress(), targetMac5, true);
                targetMac = targetMac5;
            }
            if (zEquals) {
                break;
            }
            classroom2 = classroom;
            isConnected2 = isConnected;
            targetMac2 = targetMac;
        }
        BleManager.ScannedDevice nearbyDevice = (BleManager.ScannedDevice) next;
        boolean isRecentlyActive2 = nearbyDevice != null && now - nearbyDevice.getLastSeenTimestamp() <= 6000;
        boolean isCalisDeviceReady = isRecentlyActive2;
        BleManager bleManager2 = this.bleManager;
        if (bleManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            bleManager2 = null;
        }
        boolean isUserDisconnected = bleManager2.getIsExplicitlyDisconnected();
        if (isConnected && (connState2 instanceof BleManager.ConnectionState.Connected)) {
            rawDeviceName = ((BleManager.ConnectionState.Connected) connState2).getDeviceName();
        } else {
            rawDeviceName = nearbyDevice != null ? nearbyDevice.getName() : "";
        }
        String receiverDisplayName = "Calis Device";
        if (!StringsKt.isBlank(rawDeviceName)) {
            connState = connState2;
            if (!StringsKt.equals(rawDeviceName, "Setup Calis", true) && !StringsKt.equals(rawDeviceName, "Setup Calis (Virtual)", true) && !StringsKt.equals(rawDeviceName, "Calis Device", true) && !StringsKt.equals(rawDeviceName, "Calis Classroom", true) && !StringsKt.equals(rawDeviceName, "Calis Active Classroom", true)) {
                isFactoryDefaultName = false;
            }
            if (classroom == null && classroom.isBound()) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                classroomName3 = classroom.getClassroomName();
                if (classroomName3 != null || StringsKt.isBlank(classroomName3)) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (!z5) {
                    receiverDisplayName = classroom.getClassroomName();
                } else if (isFactoryDefaultName) {
                    if (classroom != null) {
                        classroomName2 = classroom.getClassroomName();
                    } else {
                        classroomName2 = null;
                    }
                    str = classroomName2;
                    if (str != null) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                    if (!z2) {
                        receiverDisplayName = classroom.getClassroomName();
                    }
                } else {
                    receiverDisplayName = rawDeviceName;
                }
            } else if (isFactoryDefaultName) {
                receiverDisplayName = rawDeviceName;
            } else {
                if (classroom != null) {
                    classroomName2 = classroom.getClassroomName();
                } else {
                    classroomName2 = null;
                }
                str = classroomName2;
                if (str != null || StringsKt.isBlank(str)) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z2) {
                    receiverDisplayName = classroom.getClassroomName();
                }
            }
            String titleText = "CALIS TC • " + receiverDisplayName;
            if (isSessionActive) {
                str2 = "Session Active";
            } else {
                str2 = "Session Inactive";
            }
            String sessionStatusText = str2;
            subText = "Connected";
            if (!isBluetoothEnabled) {
                availabilityText = "Bluetooth Disabled";
            } else if (isConnected) {
                if (isAuth) {
                    str3 = "Connected";
                } else {
                    str3 = "Authenticating...";
                }
                availabilityText = str3;
            } else if (isConnecting) {
                availabilityText = "Connecting...";
            } else if (isDisconnecting) {
                availabilityText = "Disconnecting...";
            } else if (!isUserDisconnected && isCalisDeviceReady) {
                availabilityText = "Active";
            } else if (isCalisDeviceReady) {
                availabilityText = "Active (Advertising)";
            } else {
                availabilityText = "Searching for Device...";
            }
            str4 = "Disconnecting...";
            if (!isBluetoothEnabled) {
                subText = "Bluetooth Off";
            } else if (isConnected) {
                if (!isAuth) {
                    subText = "Authenticating...";
                }
            } else if (isConnecting) {
                subText = "Connecting...";
            } else if (isDisconnecting) {
                subText = "Disconnecting...";
            } else if (isCalisDeviceReady) {
                subText = "Device Available";
            } else {
                subText = "Searching...";
            }
            isRecentlyActive = isRecentlyActive2;
            Intent intent = new Intent(this, (Class<?>) MainActivity.class);
            intent.setFlags(603979776);
            PendingIntent openAppPendingIntent = PendingIntent.getActivity(this, 0, intent, 201326592);
            Intent intent2 = new Intent(this, (Class<?>) CalisBleService.class);
            intent2.setAction(ACTION_ENABLE_AND_START_SESSION);
            PendingIntent enableAndStartPendingIntent = PendingIntent.getService(this, 10, intent2, 201326592);
            Intent startSessionIntent = new Intent(this, (Class<?>) CalisBleService.class);
            startSessionIntent.setAction(ACTION_START_SESSION);
            startSessionPendingIntent = PendingIntent.getService(this, 1, startSessionIntent, 201326592);
            Intent endSessionIntent = new Intent(this, (Class<?>) CalisBleService.class);
            endSessionIntent.setAction(ACTION_END_SESSION);
            endSessionPendingIntent = PendingIntent.getService(this, 2, endSessionIntent, 201326592);
            Intent connectIntent = new Intent(this, (Class<?>) CalisBleService.class);
            connectIntent.setAction(ACTION_CONNECT);
            connectPendingIntent = PendingIntent.getService(this, 3, connectIntent, 201326592);
            Intent disconnectIntent = new Intent(this, (Class<?>) CalisBleService.class);
            disconnectIntent.setAction(ACTION_DISCONNECT);
            PendingIntent disconnectPendingIntent = PendingIntent.getService(this, 4, disconnectIntent, 201326592);
            Intent stopServiceIntent = new Intent(this, (Class<?>) CalisBleService.class);
            stopServiceIntent.setAction(ACTION_STOP_SERVICE);
            PendingIntent stopServicePendingIntent = PendingIntent.getService(this, 5, stopServiceIntent, 201326592);
            compactViews = new RemoteViews(getPackageName(), com.example.R.layout.notification_calis_toolbar_compact);
            expandedViews = new RemoteViews(getPackageName(), com.example.R.layout.notification_calis_toolbar_expanded);
            compactViews.setTextViewText(com.example.R.id.noti_title, "Calis Device");
            int i3 = com.example.R.id.noti_subtitle;
            String str8 = receiverDisplayName != null ? receiverDisplayName : "None";
            if (isSessionActive) {
                str5 = "Active";
            } else {
                str5 = "Inactive";
            }
            compactViews.setTextViewText(i3, str8 + " • " + str5);
            compactViews.setTextViewText(com.example.R.id.noti_status_badge, availabilityText);
            expandedViews.setTextViewText(com.example.R.id.noti_exp_title, "Calis Device");
            int i4 = com.example.R.id.noti_exp_classroom;
            if (receiverDisplayName == null) {
                str6 = "None";
            } else {
                str6 = receiverDisplayName;
            }
            expandedViews.setTextViewText(i4, str6);
            expandedViews.setTextViewText(com.example.R.id.noti_exp_details, isSessionActive ? "Session Active" : "Session Inactive");
            expandedViews.setTextViewText(com.example.R.id.noti_exp_status_badge, availabilityText);
            if (!isBluetoothEnabled) {
                badgeColorInt = Color.parseColor("#EF4444");
            } else if (!isConnected && isAuth) {
                badgeColorInt = Color.parseColor("#0284C7");
            } else if (!isConnected && !isAuth) {
                badgeColorInt = Color.parseColor("#0284C7");
            } else if (isConnecting) {
                badgeColorInt = Color.parseColor("#F59E0B");
            } else if (isDisconnecting) {
                badgeColorInt = Color.parseColor("#9E9E9E");
            } else {
                badgeColorInt = ((isUserDisconnected || !isRecentlyActive) && isCalisDeviceReady) ? Color.parseColor("#0284C7") : Color.parseColor("#0284C7");
            }
            if (!isBluetoothEnabled) {
                badgeBgRes = com.example.R.drawable.bg_noti_badge_red;
            } else if (!isConnected && isAuth) {
                badgeBgRes = com.example.R.drawable.bg_noti_badge_blue;
            } else if (!isConnected && !isAuth) {
                badgeBgRes = com.example.R.drawable.bg_noti_badge_blue;
            } else if (isConnecting) {
                badgeBgRes = com.example.R.drawable.bg_noti_badge_amber;
            } else if (isDisconnecting) {
                badgeBgRes = com.example.R.drawable.bg_noti_badge_gray;
            } else {
                badgeBgRes = ((isUserDisconnected || !isRecentlyActive) && isCalisDeviceReady) ? com.example.R.drawable.bg_noti_badge_blue : com.example.R.drawable.bg_noti_badge_blue;
            }
            if (isSessionActive || !isConnected) {
                color = Color.parseColor("#CBD5E1");
            } else {
                color = Color.parseColor("#0284C7");
            }
            int wavesColorInt = color;
            if (isBluetoothEnabled || (!(isConnected || isConnecting || ((isUserDisconnected && isRecentlyActive) || isCalisDeviceReady || isRecentlyActive)) || (isUserDisconnected && !isRecentlyActive))) {
                isDeviceActive = false;
            } else {
                isDeviceActive = true;
            }
            if (isDeviceActive) {
                color2 = Color.parseColor("#0284C7");
            } else {
                color2 = Color.parseColor("#CBD5E1");
            }
            int espColorInt = color2;
            if (!isConnected || isConnecting) {
                color3 = Color.parseColor("#0284C7");
            } else {
                color3 = Color.parseColor("#CBD5E1");
            }
            int dottedLineColorInt = color3;
            compactViews.setInt(com.example.R.id.noti_badge_container, "setBackgroundResource", badgeBgRes);
            expandedViews.setInt(com.example.R.id.noti_exp_badge_container, "setBackgroundResource", badgeBgRes);
            compactViews.setInt(com.example.R.id.noti_badge_dot, "setColorFilter", badgeColorInt);
            compactViews.setTextColor(com.example.R.id.noti_status_badge, badgeColorInt);
            compactViews.setInt(com.example.R.id.noti_waves_icon, "setColorFilter", wavesColorInt);
            String receiverDisplayName2 = receiverDisplayName;
            compactViews.setInt(com.example.R.id.noti_esp32_icon, "setColorFilter", espColorInt);
            isControlsEnabled = isControlsEnabled2;
            compactViews.setInt(com.example.R.id.noti_dotted_line, "setColorFilter", dottedLineColorInt);
            expandedViews.setInt(com.example.R.id.noti_exp_badge_dot, "setColorFilter", badgeColorInt);
            expandedViews.setTextColor(com.example.R.id.noti_exp_status_badge, badgeColorInt);
            expandedViews.setInt(com.example.R.id.noti_exp_waves_icon, "setColorFilter", wavesColorInt);
            expandedViews.setInt(com.example.R.id.noti_exp_esp32_icon, "setColorFilter", espColorInt);
            expandedViews.setInt(com.example.R.id.noti_exp_dotted_line, "setColorFilter", dottedLineColorInt);
            if (!isDisconnecting) {
                if (isConnecting) {
                    str4 = "Connecting...";
                } else {
                    str4 = "Disconnect";
                }
            }
            String disconnectButtonText = str4;
            if (!isDisconnecting || isConnecting) {
                i = com.example.R.drawable.bg_noti_btn_outlined_disabled;
            } else {
                i = com.example.R.drawable.bg_noti_btn_outlined_blue;
            }
            int disconnectBgRes = i;
            if (!isDisconnecting || isConnecting) {
                color4 = Color.parseColor("#9E9E9E");
            } else {
                color4 = Color.parseColor("#0284C7");
            }
            int disconnectTextColor = color4;
            compactViews.setTextViewText(com.example.R.id.noti_btn_disconnect_text, disconnectButtonText);
            compactViews.setInt(com.example.R.id.noti_btn_disconnect, "setBackgroundResource", disconnectBgRes);
            compactViews.setTextColor(com.example.R.id.noti_btn_disconnect_text, disconnectTextColor);
            expandedViews.setTextViewText(com.example.R.id.noti_exp_btn_disconnect_text, disconnectButtonText);
            expandedViews.setInt(com.example.R.id.noti_exp_btn_disconnect, "setBackgroundResource", disconnectBgRes);
            expandedViews.setTextColor(com.example.R.id.noti_exp_btn_disconnect_text, disconnectTextColor);
            if (isCalisDeviceReady) {
                connectBgRes = com.example.R.drawable.bg_noti_btn_filled_blue;
            } else {
                connectBgRes = com.example.R.drawable.bg_noti_btn_filled_disabled;
            }
            if (isCalisDeviceReady) {
                color5 = Color.parseColor("#FFFFFF");
            } else {
                color5 = Color.parseColor("#9E9E9E");
            }
            int connectTextColor = color5;
            if (isCalisDeviceReady) {
                pendingIntent = connectPendingIntent;
            } else {
                pendingIntent = null;
            }
            PendingIntent connectPending = pendingIntent;
            compactViews.setInt(com.example.R.id.noti_btn_connect, "setBackgroundResource", connectBgRes);
            compactViews.setTextColor(com.example.R.id.noti_btn_connect_text, connectTextColor);
            expandedViews.setInt(com.example.R.id.noti_exp_btn_connect, "setBackgroundResource", connectBgRes);
            expandedViews.setTextColor(com.example.R.id.noti_exp_btn_connect_text, connectTextColor);
            if (isControlsEnabled) {
                startSessionBg = com.example.R.drawable.bg_noti_btn_filled_blue;
            } else {
                startSessionBg = com.example.R.drawable.bg_noti_btn_filled_disabled;
            }
            if (isControlsEnabled) {
                color6 = Color.parseColor("#FFFFFF");
            } else {
                color6 = Color.parseColor("#9E9E9E");
            }
            int startSessionTextColor = color6;
            if (isControlsEnabled) {
                startSessionIconColor = Color.parseColor("#FFFFFF");
            } else {
                startSessionIconColor = Color.parseColor("#9E9E9E");
            }
            if (isControlsEnabled) {
                pendingIntent2 = startSessionPendingIntent;
            } else {
                pendingIntent2 = null;
            }
            PendingIntent startSessionPending = pendingIntent2;
            if (isControlsEnabled) {
                i2 = com.example.R.drawable.bg_noti_btn_outlined_blue;
            } else {
                i2 = com.example.R.drawable.bg_noti_btn_outlined_disabled;
            }
            int endSessionBg = i2;
            if (isControlsEnabled) {
                color7 = Color.parseColor("#0284C7");
            } else {
                color7 = Color.parseColor("#9E9E9E");
            }
            int endSessionTextColor = color7;
            if (isControlsEnabled) {
                color8 = Color.parseColor("#0284C7");
            } else {
                color8 = Color.parseColor("#9E9E9E");
            }
            int endSessionIconColor = color8;
            if (isControlsEnabled) {
                pendingIntent3 = endSessionPendingIntent;
            } else {
                pendingIntent3 = null;
            }
            PendingIntent endSessionPending = pendingIntent3;
            int connectBgRes2 = com.example.R.id.noti_exp_btn_session_start;
            expandedViews.setInt(connectBgRes2, "setBackgroundResource", startSessionBg);
            expandedViews.setTextColor(com.example.R.id.noti_exp_btn_session_start_text, startSessionTextColor);
            expandedViews.setInt(com.example.R.id.noti_exp_btn_session_start_icon, "setColorFilter", startSessionIconColor);
            expandedViews.setInt(com.example.R.id.noti_exp_btn_session_end, "setBackgroundResource", endSessionBg);
            expandedViews.setTextColor(com.example.R.id.noti_exp_btn_session_end_text, endSessionTextColor);
            expandedViews.setInt(com.example.R.id.noti_exp_btn_session_end_icon, "setColorFilter", endSessionIconColor);
            compactViews.setOnClickPendingIntent(com.example.R.id.noti_btn_connect, connectPending);
            int i5 = com.example.R.id.noti_btn_disconnect;
            if (isConnected || isDisconnecting || isConnecting) {
                pendingIntent4 = null;
            } else {
                pendingIntent4 = disconnectPendingIntent;
            }
            compactViews.setOnClickPendingIntent(i5, pendingIntent4);
            compactViews.setOnClickPendingIntent(com.example.R.id.noti_btn_enable, enableAndStartPendingIntent);
            expandedViews.setOnClickPendingIntent(com.example.R.id.noti_exp_btn_connect, connectPending);
            int i6 = com.example.R.id.noti_exp_btn_disconnect;
            if (isConnected || isDisconnecting || isConnecting) {
                pendingIntent5 = null;
            } else {
                pendingIntent5 = disconnectPendingIntent;
            }
            expandedViews.setOnClickPendingIntent(i6, pendingIntent5);
            expandedViews.setOnClickPendingIntent(com.example.R.id.noti_exp_btn_enable, enableAndStartPendingIntent);
            expandedViews.setOnClickPendingIntent(com.example.R.id.noti_exp_btn_session_start, startSessionPending);
            expandedViews.setOnClickPendingIntent(com.example.R.id.noti_exp_btn_session_end, endSessionPending);
            expandedViews.setOnClickPendingIntent(com.example.R.id.noti_exp_btn_terminate, stopServicePendingIntent);
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("calis_auth_prefs", 0);
            uRole = prefs.getString("user_role", null);
            if (isBluetoothEnabled) {
                buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_enable, false);
                buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_enable, false);
                if (!isConnected || isConnecting || isDisconnecting) {
                    isDisconnectVisible = true;
                } else {
                    isDisconnectVisible = false;
                }
                if (isDisconnectVisible) {
                    isConnectVisible = false;
                } else {
                    isConnectVisible = true;
                }
                buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_connect, isConnectVisible);
                buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_disconnect, isDisconnectVisible);
                buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_connect, isConnectVisible);
                buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_disconnect, isDisconnectVisible);
                showSessionControls = Intrinsics.areEqual(uRole, "TEACHER");
                if (showSessionControls || isSessionActive) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                boolean isStartSessionVisible = z3;
                if (showSessionControls || !isSessionActive) {
                    z4 = false;
                } else {
                    z4 = true;
                }
                boolean isEndSessionVisible = z4;
                buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_session_start, isStartSessionVisible);
                buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_session_end, isEndSessionVisible);
            } else {
                buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_connect, false);
                buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_disconnect, false);
                buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_enable, true);
                buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_connect, false);
                buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_disconnect, false);
                buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_enable, true);
                buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_session_start, false);
                buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_session_end, false);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(com.example.R.drawable.ic_notification_logo).setContentTitle(titleText).setContentText(availabilityText + " • " + sessionStatusText).setSubText(subText).setCustomContentView(compactViews).setCustomBigContentView(expandedViews).setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setContentIntent(openAppPendingIntent).setOngoing(true).setOnlyAlertOnce(true).setPriority(0).setVisibility(1);
            Intrinsics.checkNotNullExpressionValue(builder, "setVisibility(...)");
            StringBuilder sb = new StringBuilder();
            sb.append(isBluetoothEnabled).append('|');
            sb.append(connState.getClass().getSimpleName()).append('|');
            sb.append(isAuth).append('|');
            sb.append(isSessionActive).append('|');
            sb.append(isCalisDeviceReady).append('|');
            sb.append(isControlsEnabled).append('|');
            sb.append(receiverDisplayName2).append('|');
            sb.append(availabilityText).append('|');
            sb.append(sessionStatusText).append('|');
            sb.append(disconnectButtonText);
            String fingerprint = sb.toString();
            Intrinsics.checkNotNullExpressionValue(fingerprint, "toString(...)");
            return new Pair<>(builder.build(), fingerprint);
        }
        connState = connState2;
        isFactoryDefaultName = true;
        if (classroom == null) {
            z = false;
        } else {
            z = false;
        }
        if (!z) {
            classroomName3 = classroom.getClassroomName();
            if (classroomName3 != null) {
                z5 = true;
            } else {
                z5 = true;
            }
            if (!z5) {
                receiverDisplayName = classroom.getClassroomName();
            } else if (isFactoryDefaultName) {
                receiverDisplayName = rawDeviceName;
            } else {
                if (classroom != null) {
                    classroomName2 = classroom.getClassroomName();
                } else {
                    classroomName2 = null;
                }
                str = classroomName2;
                if (str != null) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                if (!z2) {
                    receiverDisplayName = classroom.getClassroomName();
                }
            }
        } else if (isFactoryDefaultName) {
            receiverDisplayName = rawDeviceName;
        } else {
            if (classroom != null) {
                classroomName2 = classroom.getClassroomName();
            } else {
                classroomName2 = null;
            }
            str = classroomName2;
            if (str != null) {
                z2 = true;
            } else {
                z2 = true;
            }
            if (!z2) {
                receiverDisplayName = classroom.getClassroomName();
            }
        }
        String titleText2 = "CALIS TC • " + receiverDisplayName;
        if (isSessionActive) {
            str2 = "Session Active";
        } else {
            str2 = "Session Inactive";
        }
        String sessionStatusText2 = str2;
        subText = "Connected";
        if (!isBluetoothEnabled) {
            availabilityText = "Bluetooth Disabled";
        } else if (isConnected) {
            if (isAuth) {
                str3 = "Connected";
            } else {
                str3 = "Authenticating...";
            }
            availabilityText = str3;
        } else if (isConnecting) {
            availabilityText = "Connecting...";
        } else if (isDisconnecting) {
            availabilityText = "Disconnecting...";
        } else if (!isUserDisconnected) {
            if (isCalisDeviceReady) {
                availabilityText = "Active (Advertising)";
            } else {
                availabilityText = "Searching for Device...";
            }
        } else if (isCalisDeviceReady) {
            availabilityText = "Active (Advertising)";
        } else {
            availabilityText = "Searching for Device...";
        }
        str4 = "Disconnecting...";
        if (!isBluetoothEnabled) {
            subText = "Bluetooth Off";
        } else if (isConnected) {
            if (!isAuth) {
                subText = "Authenticating...";
            }
        } else if (isConnecting) {
            subText = "Connecting...";
        } else if (isDisconnecting) {
            subText = "Disconnecting...";
        } else if (isCalisDeviceReady) {
            subText = "Device Available";
        } else {
            subText = "Searching...";
        }
        isRecentlyActive = isRecentlyActive2;
        Intent intent3 = new Intent(this, (Class<?>) MainActivity.class);
        intent3.setFlags(603979776);
        PendingIntent openAppPendingIntent2 = PendingIntent.getActivity(this, 0, intent3, 201326592);
        Intent intent4 = new Intent(this, (Class<?>) CalisBleService.class);
        intent4.setAction(ACTION_ENABLE_AND_START_SESSION);
        PendingIntent enableAndStartPendingIntent2 = PendingIntent.getService(this, 10, intent4, 201326592);
        Intent startSessionIntent2 = new Intent(this, (Class<?>) CalisBleService.class);
        startSessionIntent2.setAction(ACTION_START_SESSION);
        startSessionPendingIntent = PendingIntent.getService(this, 1, startSessionIntent2, 201326592);
        Intent endSessionIntent2 = new Intent(this, (Class<?>) CalisBleService.class);
        endSessionIntent2.setAction(ACTION_END_SESSION);
        endSessionPendingIntent = PendingIntent.getService(this, 2, endSessionIntent2, 201326592);
        Intent connectIntent2 = new Intent(this, (Class<?>) CalisBleService.class);
        connectIntent2.setAction(ACTION_CONNECT);
        connectPendingIntent = PendingIntent.getService(this, 3, connectIntent2, 201326592);
        Intent disconnectIntent2 = new Intent(this, (Class<?>) CalisBleService.class);
        disconnectIntent2.setAction(ACTION_DISCONNECT);
        PendingIntent disconnectPendingIntent2 = PendingIntent.getService(this, 4, disconnectIntent2, 201326592);
        Intent stopServiceIntent2 = new Intent(this, (Class<?>) CalisBleService.class);
        stopServiceIntent2.setAction(ACTION_STOP_SERVICE);
        PendingIntent stopServicePendingIntent2 = PendingIntent.getService(this, 5, stopServiceIntent2, 201326592);
        compactViews = new RemoteViews(getPackageName(), com.example.R.layout.notification_calis_toolbar_compact);
        expandedViews = new RemoteViews(getPackageName(), com.example.R.layout.notification_calis_toolbar_expanded);
        compactViews.setTextViewText(com.example.R.id.noti_title, "Calis Device");
        int i7 = com.example.R.id.noti_subtitle;
        if (receiverDisplayName != null) {
        }
        if (isSessionActive) {
            str5 = "Active";
        } else {
            str5 = "Inactive";
        }
        compactViews.setTextViewText(i7, str8 + " • " + str5);
        compactViews.setTextViewText(com.example.R.id.noti_status_badge, availabilityText);
        expandedViews.setTextViewText(com.example.R.id.noti_exp_title, "Calis Device");
        int i8 = com.example.R.id.noti_exp_classroom;
        if (receiverDisplayName == null) {
            str6 = "None";
        } else {
            str6 = receiverDisplayName;
        }
        expandedViews.setTextViewText(i8, str6);
        expandedViews.setTextViewText(com.example.R.id.noti_exp_details, isSessionActive ? "Session Active" : "Session Inactive");
        expandedViews.setTextViewText(com.example.R.id.noti_exp_status_badge, availabilityText);
        if (!isBluetoothEnabled) {
            badgeColorInt = Color.parseColor("#EF4444");
        } else if (!isConnected) {
            if (!isConnected) {
                if (isConnecting) {
                    badgeColorInt = Color.parseColor("#F59E0B");
                } else if (isDisconnecting) {
                    badgeColorInt = Color.parseColor("#9E9E9E");
                } else if (isUserDisconnected) {
                }
            } else if (isConnecting) {
                badgeColorInt = Color.parseColor("#F59E0B");
            } else if (isDisconnecting) {
                badgeColorInt = Color.parseColor("#9E9E9E");
            } else if (isUserDisconnected) {
            }
        } else if (!isConnected) {
            if (isConnecting) {
                badgeColorInt = Color.parseColor("#F59E0B");
            } else if (isDisconnecting) {
                badgeColorInt = Color.parseColor("#9E9E9E");
            } else if (isUserDisconnected) {
            }
        } else if (isConnecting) {
            badgeColorInt = Color.parseColor("#F59E0B");
        } else if (isDisconnecting) {
            badgeColorInt = Color.parseColor("#9E9E9E");
        } else if (isUserDisconnected) {
        }
        if (!isBluetoothEnabled) {
            badgeBgRes = com.example.R.drawable.bg_noti_badge_red;
        } else if (!isConnected) {
            if (!isConnected) {
                if (isConnecting) {
                    badgeBgRes = com.example.R.drawable.bg_noti_badge_amber;
                } else if (isDisconnecting) {
                    badgeBgRes = com.example.R.drawable.bg_noti_badge_gray;
                } else if (isUserDisconnected) {
                }
            } else if (isConnecting) {
                badgeBgRes = com.example.R.drawable.bg_noti_badge_amber;
            } else if (isDisconnecting) {
                badgeBgRes = com.example.R.drawable.bg_noti_badge_gray;
            } else if (isUserDisconnected) {
            }
        } else if (!isConnected) {
            if (isConnecting) {
                badgeBgRes = com.example.R.drawable.bg_noti_badge_amber;
            } else if (isDisconnecting) {
                badgeBgRes = com.example.R.drawable.bg_noti_badge_gray;
            } else if (isUserDisconnected) {
            }
        } else if (isConnecting) {
            badgeBgRes = com.example.R.drawable.bg_noti_badge_amber;
        } else if (isDisconnecting) {
            badgeBgRes = com.example.R.drawable.bg_noti_badge_gray;
        } else if (isUserDisconnected) {
        }
        if (isSessionActive) {
            color = Color.parseColor("#CBD5E1");
        } else {
            color = Color.parseColor("#CBD5E1");
        }
        int wavesColorInt2 = color;
        if (isBluetoothEnabled) {
            isDeviceActive = false;
        } else {
            isDeviceActive = false;
        }
        if (isDeviceActive) {
            color2 = Color.parseColor("#0284C7");
        } else {
            color2 = Color.parseColor("#CBD5E1");
        }
        int espColorInt2 = color2;
        if (isConnected) {
            color3 = Color.parseColor("#0284C7");
        } else {
            color3 = Color.parseColor("#0284C7");
        }
        int dottedLineColorInt2 = color3;
        compactViews.setInt(com.example.R.id.noti_badge_container, "setBackgroundResource", badgeBgRes);
        expandedViews.setInt(com.example.R.id.noti_exp_badge_container, "setBackgroundResource", badgeBgRes);
        compactViews.setInt(com.example.R.id.noti_badge_dot, "setColorFilter", badgeColorInt);
        compactViews.setTextColor(com.example.R.id.noti_status_badge, badgeColorInt);
        compactViews.setInt(com.example.R.id.noti_waves_icon, "setColorFilter", wavesColorInt2);
        String receiverDisplayName3 = receiverDisplayName;
        compactViews.setInt(com.example.R.id.noti_esp32_icon, "setColorFilter", espColorInt2);
        isControlsEnabled = isControlsEnabled2;
        compactViews.setInt(com.example.R.id.noti_dotted_line, "setColorFilter", dottedLineColorInt2);
        expandedViews.setInt(com.example.R.id.noti_exp_badge_dot, "setColorFilter", badgeColorInt);
        expandedViews.setTextColor(com.example.R.id.noti_exp_status_badge, badgeColorInt);
        expandedViews.setInt(com.example.R.id.noti_exp_waves_icon, "setColorFilter", wavesColorInt2);
        expandedViews.setInt(com.example.R.id.noti_exp_esp32_icon, "setColorFilter", espColorInt2);
        expandedViews.setInt(com.example.R.id.noti_exp_dotted_line, "setColorFilter", dottedLineColorInt2);
        if (!isDisconnecting) {
            if (isConnecting) {
                str4 = "Connecting...";
            } else {
                str4 = "Disconnect";
            }
        }
        String disconnectButtonText2 = str4;
        if (isDisconnecting) {
            i = com.example.R.drawable.bg_noti_btn_outlined_disabled;
        } else {
            i = com.example.R.drawable.bg_noti_btn_outlined_disabled;
        }
        int disconnectBgRes2 = i;
        if (isDisconnecting) {
            color4 = Color.parseColor("#9E9E9E");
        } else {
            color4 = Color.parseColor("#9E9E9E");
        }
        int disconnectTextColor2 = color4;
        compactViews.setTextViewText(com.example.R.id.noti_btn_disconnect_text, disconnectButtonText2);
        compactViews.setInt(com.example.R.id.noti_btn_disconnect, "setBackgroundResource", disconnectBgRes2);
        compactViews.setTextColor(com.example.R.id.noti_btn_disconnect_text, disconnectTextColor2);
        expandedViews.setTextViewText(com.example.R.id.noti_exp_btn_disconnect_text, disconnectButtonText2);
        expandedViews.setInt(com.example.R.id.noti_exp_btn_disconnect, "setBackgroundResource", disconnectBgRes2);
        expandedViews.setTextColor(com.example.R.id.noti_exp_btn_disconnect_text, disconnectTextColor2);
        if (isCalisDeviceReady) {
            connectBgRes = com.example.R.drawable.bg_noti_btn_filled_blue;
        } else {
            connectBgRes = com.example.R.drawable.bg_noti_btn_filled_disabled;
        }
        if (isCalisDeviceReady) {
            color5 = Color.parseColor("#FFFFFF");
        } else {
            color5 = Color.parseColor("#9E9E9E");
        }
        int connectTextColor2 = color5;
        if (isCalisDeviceReady) {
            pendingIntent = connectPendingIntent;
        } else {
            pendingIntent = null;
        }
        PendingIntent connectPending2 = pendingIntent;
        compactViews.setInt(com.example.R.id.noti_btn_connect, "setBackgroundResource", connectBgRes);
        compactViews.setTextColor(com.example.R.id.noti_btn_connect_text, connectTextColor2);
        expandedViews.setInt(com.example.R.id.noti_exp_btn_connect, "setBackgroundResource", connectBgRes);
        expandedViews.setTextColor(com.example.R.id.noti_exp_btn_connect_text, connectTextColor2);
        if (isControlsEnabled) {
            startSessionBg = com.example.R.drawable.bg_noti_btn_filled_blue;
        } else {
            startSessionBg = com.example.R.drawable.bg_noti_btn_filled_disabled;
        }
        if (isControlsEnabled) {
            color6 = Color.parseColor("#FFFFFF");
        } else {
            color6 = Color.parseColor("#9E9E9E");
        }
        int startSessionTextColor2 = color6;
        if (isControlsEnabled) {
            startSessionIconColor = Color.parseColor("#FFFFFF");
        } else {
            startSessionIconColor = Color.parseColor("#9E9E9E");
        }
        if (isControlsEnabled) {
            pendingIntent2 = startSessionPendingIntent;
        } else {
            pendingIntent2 = null;
        }
        PendingIntent startSessionPending2 = pendingIntent2;
        if (isControlsEnabled) {
            i2 = com.example.R.drawable.bg_noti_btn_outlined_blue;
        } else {
            i2 = com.example.R.drawable.bg_noti_btn_outlined_disabled;
        }
        int endSessionBg2 = i2;
        if (isControlsEnabled) {
            color7 = Color.parseColor("#0284C7");
        } else {
            color7 = Color.parseColor("#9E9E9E");
        }
        int endSessionTextColor2 = color7;
        if (isControlsEnabled) {
            color8 = Color.parseColor("#0284C7");
        } else {
            color8 = Color.parseColor("#9E9E9E");
        }
        int endSessionIconColor2 = color8;
        if (isControlsEnabled) {
            pendingIntent3 = endSessionPendingIntent;
        } else {
            pendingIntent3 = null;
        }
        PendingIntent endSessionPending2 = pendingIntent3;
        int connectBgRes3 = com.example.R.id.noti_exp_btn_session_start;
        expandedViews.setInt(connectBgRes3, "setBackgroundResource", startSessionBg);
        expandedViews.setTextColor(com.example.R.id.noti_exp_btn_session_start_text, startSessionTextColor2);
        expandedViews.setInt(com.example.R.id.noti_exp_btn_session_start_icon, "setColorFilter", startSessionIconColor);
        expandedViews.setInt(com.example.R.id.noti_exp_btn_session_end, "setBackgroundResource", endSessionBg2);
        expandedViews.setTextColor(com.example.R.id.noti_exp_btn_session_end_text, endSessionTextColor2);
        expandedViews.setInt(com.example.R.id.noti_exp_btn_session_end_icon, "setColorFilter", endSessionIconColor2);
        compactViews.setOnClickPendingIntent(com.example.R.id.noti_btn_connect, connectPending2);
        int i9 = com.example.R.id.noti_btn_disconnect;
        if (isConnected) {
            pendingIntent4 = null;
        } else {
            pendingIntent4 = null;
        }
        compactViews.setOnClickPendingIntent(i9, pendingIntent4);
        compactViews.setOnClickPendingIntent(com.example.R.id.noti_btn_enable, enableAndStartPendingIntent2);
        expandedViews.setOnClickPendingIntent(com.example.R.id.noti_exp_btn_connect, connectPending2);
        int i10 = com.example.R.id.noti_exp_btn_disconnect;
        if (isConnected) {
            pendingIntent5 = null;
        } else {
            pendingIntent5 = null;
        }
        expandedViews.setOnClickPendingIntent(i10, pendingIntent5);
        expandedViews.setOnClickPendingIntent(com.example.R.id.noti_exp_btn_enable, enableAndStartPendingIntent2);
        expandedViews.setOnClickPendingIntent(com.example.R.id.noti_exp_btn_session_start, startSessionPending2);
        expandedViews.setOnClickPendingIntent(com.example.R.id.noti_exp_btn_session_end, endSessionPending2);
        expandedViews.setOnClickPendingIntent(com.example.R.id.noti_exp_btn_terminate, stopServicePendingIntent2);
        SharedPreferences prefs2 = getApplicationContext().getSharedPreferences("calis_auth_prefs", 0);
        uRole = prefs2.getString("user_role", null);
        if (isBluetoothEnabled) {
            buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_connect, false);
            buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_disconnect, false);
            buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_enable, true);
            buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_connect, false);
            buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_disconnect, false);
            buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_enable, true);
            buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_session_start, false);
            buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_session_end, false);
        } else {
            buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_enable, false);
            buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_enable, false);
            if (isConnected) {
                isDisconnectVisible = true;
            } else {
                isDisconnectVisible = true;
            }
            if (isDisconnectVisible) {
                isConnectVisible = true;
            } else {
                isConnectVisible = false;
            }
            buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_connect, isConnectVisible);
            buildToolbarNotificationWithFingerprint$setViewVisible(compactViews, com.example.R.id.noti_btn_disconnect, isDisconnectVisible);
            buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_connect, isConnectVisible);
            buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_disconnect, isDisconnectVisible);
            showSessionControls = Intrinsics.areEqual(uRole, "TEACHER");
            if (showSessionControls) {
                z3 = false;
            } else {
                z3 = false;
            }
            boolean isStartSessionVisible2 = z3;
            if (showSessionControls) {
                z4 = false;
            } else {
                z4 = false;
            }
            boolean isEndSessionVisible2 = z4;
            buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_session_start, isStartSessionVisible2);
            buildToolbarNotificationWithFingerprint$setViewVisible(expandedViews, com.example.R.id.noti_exp_btn_session_end, isEndSessionVisible2);
        }
        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(com.example.R.drawable.ic_notification_logo).setContentTitle(titleText2).setContentText(availabilityText + " • " + sessionStatusText2).setSubText(subText).setCustomContentView(compactViews).setCustomBigContentView(expandedViews).setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setContentIntent(openAppPendingIntent2).setOngoing(true).setOnlyAlertOnce(true).setPriority(0).setVisibility(1);
        Intrinsics.checkNotNullExpressionValue(builder2, "setVisibility(...)");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(isBluetoothEnabled).append('|');
        sb2.append(connState.getClass().getSimpleName()).append('|');
        sb2.append(isAuth).append('|');
        sb2.append(isSessionActive).append('|');
        sb2.append(isCalisDeviceReady).append('|');
        sb2.append(isControlsEnabled).append('|');
        sb2.append(receiverDisplayName3).append('|');
        sb2.append(availabilityText).append('|');
        sb2.append(sessionStatusText2).append('|');
        sb2.append(disconnectButtonText2);
        String fingerprint2 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(fingerprint2, "toString(...)");
        return new Pair<>(builder2.build(), fingerprint2);
    }

    private static final void buildToolbarNotificationWithFingerprint$setViewVisible(RemoteViews rv, int viewId, boolean visible) {
        rv.setViewVisibility(viewId, visible ? 0 : 8);
    }

    private final void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationManager manager = (NotificationManager) systemService;
            NotificationChannel existingChannel = manager.getNotificationChannel(CHANNEL_ID);
            if (existingChannel == null) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "CALIS TC Session Toolbar", 3);
                channel.setDescription("Quick access toolbar for Teacher Controller (TC) to manage classroom sessions and Calis device connection");
                channel.setShowBadge(false);
                channel.setLockscreenVisibility(1);
                channel.setSound(null, null);
                channel.enableVibration(false);
                manager.createNotificationChannel(channel);
            }
        }
    }
}
