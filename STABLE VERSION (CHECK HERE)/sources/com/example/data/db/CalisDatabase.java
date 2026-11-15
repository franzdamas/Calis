package com.example.data.db;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CalisDatabase.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&¨\u0006\u0019"}, d2 = {"Lcom/example/data/db/CalisDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "teacherDao", "Lcom/example/data/db/TeacherDao;", "studentProfileDao", "Lcom/example/data/db/StudentProfileDao;", "classroomDao", "Lcom/example/data/db/ClassroomDao;", "approvedStudentDao", "Lcom/example/data/db/ApprovedStudentDao;", "pendingRegistrationDao", "Lcom/example/data/db/PendingRegistrationDao;", "attendanceDao", "Lcom/example/data/db/AttendanceDao;", "taskDao", "Lcom/example/data/db/TaskDao;", "taskSubmissionDao", "Lcom/example/data/db/TaskSubmissionDao;", "syncRecordDao", "Lcom/example/data/db/SyncRecordDao;", "taskDeliveryDao", "Lcom/example/data/db/TaskDeliveryDao;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class CalisDatabase extends RoomDatabase {
    private static volatile CalisDatabase INSTANCE;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public abstract ApprovedStudentDao approvedStudentDao();

    public abstract AttendanceDao attendanceDao();

    public abstract ClassroomDao classroomDao();

    public abstract PendingRegistrationDao pendingRegistrationDao();

    public abstract StudentProfileDao studentProfileDao();

    public abstract SyncRecordDao syncRecordDao();

    public abstract TaskDao taskDao();

    public abstract TaskDeliveryDao taskDeliveryDao();

    public abstract TaskSubmissionDao taskSubmissionDao();

    public abstract TeacherDao teacherDao();

    /* JADX INFO: compiled from: CalisDatabase.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/example/data/db/CalisDatabase$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/example/data/db/CalisDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CalisDatabase getDatabase(Context context) {
            CalisDatabase calisDatabase;
            Intrinsics.checkNotNullParameter(context, "context");
            CalisDatabase calisDatabase2 = CalisDatabase.INSTANCE;
            if (calisDatabase2 != null) {
                return calisDatabase2;
            }
            synchronized (this) {
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                calisDatabase = (CalisDatabase) Room.databaseBuilder(applicationContext, CalisDatabase.class, "calis_mobile_db").fallbackToDestructiveMigration().build();
                Companion companion = CalisDatabase.INSTANCE;
                CalisDatabase.INSTANCE = calisDatabase;
            }
            return calisDatabase;
        }
    }
}
