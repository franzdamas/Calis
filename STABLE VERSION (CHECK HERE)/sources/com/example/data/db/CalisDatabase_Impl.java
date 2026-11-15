package com.example.data.db;

import androidx.core.app.NotificationCompat;
import androidx.room.InvalidationTracker;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: CalisDatabase_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\"\u0010\u001f\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030!\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030!0\"0 H\u0014J\u0016\u0010#\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020%0!0$H\u0016J*\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\"2\u001a\u0010(\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020%0!\u0012\u0004\u0012\u00020%0 H\u0016J\b\u0010)\u001a\u00020\u0006H\u0016J\b\u0010*\u001a\u00020\bH\u0016J\b\u0010+\u001a\u00020\nH\u0016J\b\u0010,\u001a\u00020\fH\u0016J\b\u0010-\u001a\u00020\u000eH\u0016J\b\u0010.\u001a\u00020\u0010H\u0016J\b\u0010/\u001a\u00020\u0012H\u0016J\b\u00100\u001a\u00020\u0014H\u0016J\b\u00101\u001a\u00020\u0016H\u0016J\b\u00102\u001a\u00020\u0018H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/example/data/db/CalisDatabase_Impl;", "Lcom/example/data/db/CalisDatabase;", "<init>", "()V", "_teacherDao", "Lkotlin/Lazy;", "Lcom/example/data/db/TeacherDao;", "_studentProfileDao", "Lcom/example/data/db/StudentProfileDao;", "_classroomDao", "Lcom/example/data/db/ClassroomDao;", "_approvedStudentDao", "Lcom/example/data/db/ApprovedStudentDao;", "_pendingRegistrationDao", "Lcom/example/data/db/PendingRegistrationDao;", "_attendanceDao", "Lcom/example/data/db/AttendanceDao;", "_taskDao", "Lcom/example/data/db/TaskDao;", "_taskSubmissionDao", "Lcom/example/data/db/TaskSubmissionDao;", "_syncRecordDao", "Lcom/example/data/db/SyncRecordDao;", "_taskDeliveryDao", "Lcom/example/data/db/TaskDeliveryDao;", "createOpenDelegate", "Landroidx/room/RoomOpenDelegate;", "createInvalidationTracker", "Landroidx/room/InvalidationTracker;", "clearAllTables", "", "getRequiredTypeConverterClasses", "", "Lkotlin/reflect/KClass;", "", "getRequiredAutoMigrationSpecClasses", "", "Landroidx/room/migration/AutoMigrationSpec;", "createAutoMigrations", "Landroidx/room/migration/Migration;", "autoMigrationSpecs", "teacherDao", "studentProfileDao", "classroomDao", "approvedStudentDao", "pendingRegistrationDao", "attendanceDao", "taskDao", "taskSubmissionDao", "syncRecordDao", "taskDeliveryDao", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CalisDatabase_Impl extends CalisDatabase {
    public static final int $stable = 8;
    private final Lazy<TeacherDao> _teacherDao = LazyKt.lazy(new Function0() { // from class: com.example.data.db.CalisDatabase_Impl$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CalisDatabase_Impl._teacherDao$lambda$0(this.f$0);
        }
    });
    private final Lazy<StudentProfileDao> _studentProfileDao = LazyKt.lazy(new Function0() { // from class: com.example.data.db.CalisDatabase_Impl$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CalisDatabase_Impl._studentProfileDao$lambda$1(this.f$0);
        }
    });
    private final Lazy<ClassroomDao> _classroomDao = LazyKt.lazy(new Function0() { // from class: com.example.data.db.CalisDatabase_Impl$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CalisDatabase_Impl._classroomDao$lambda$2(this.f$0);
        }
    });
    private final Lazy<ApprovedStudentDao> _approvedStudentDao = LazyKt.lazy(new Function0() { // from class: com.example.data.db.CalisDatabase_Impl$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CalisDatabase_Impl._approvedStudentDao$lambda$3(this.f$0);
        }
    });
    private final Lazy<PendingRegistrationDao> _pendingRegistrationDao = LazyKt.lazy(new Function0() { // from class: com.example.data.db.CalisDatabase_Impl$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CalisDatabase_Impl._pendingRegistrationDao$lambda$4(this.f$0);
        }
    });
    private final Lazy<AttendanceDao> _attendanceDao = LazyKt.lazy(new Function0() { // from class: com.example.data.db.CalisDatabase_Impl$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CalisDatabase_Impl._attendanceDao$lambda$5(this.f$0);
        }
    });
    private final Lazy<TaskDao> _taskDao = LazyKt.lazy(new Function0() { // from class: com.example.data.db.CalisDatabase_Impl$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CalisDatabase_Impl._taskDao$lambda$6(this.f$0);
        }
    });
    private final Lazy<TaskSubmissionDao> _taskSubmissionDao = LazyKt.lazy(new Function0() { // from class: com.example.data.db.CalisDatabase_Impl$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CalisDatabase_Impl._taskSubmissionDao$lambda$7(this.f$0);
        }
    });
    private final Lazy<SyncRecordDao> _syncRecordDao = LazyKt.lazy(new Function0() { // from class: com.example.data.db.CalisDatabase_Impl$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CalisDatabase_Impl._syncRecordDao$lambda$8(this.f$0);
        }
    });
    private final Lazy<TaskDeliveryDao> _taskDeliveryDao = LazyKt.lazy(new Function0() { // from class: com.example.data.db.CalisDatabase_Impl$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CalisDatabase_Impl._taskDeliveryDao$lambda$9(this.f$0);
        }
    });

    static final TeacherDao_Impl _teacherDao$lambda$0(CalisDatabase_Impl this$0) {
        return new TeacherDao_Impl(this$0);
    }

    static final StudentProfileDao_Impl _studentProfileDao$lambda$1(CalisDatabase_Impl this$0) {
        return new StudentProfileDao_Impl(this$0);
    }

    static final ClassroomDao_Impl _classroomDao$lambda$2(CalisDatabase_Impl this$0) {
        return new ClassroomDao_Impl(this$0);
    }

    static final ApprovedStudentDao_Impl _approvedStudentDao$lambda$3(CalisDatabase_Impl this$0) {
        return new ApprovedStudentDao_Impl(this$0);
    }

    static final PendingRegistrationDao_Impl _pendingRegistrationDao$lambda$4(CalisDatabase_Impl this$0) {
        return new PendingRegistrationDao_Impl(this$0);
    }

    static final AttendanceDao_Impl _attendanceDao$lambda$5(CalisDatabase_Impl this$0) {
        return new AttendanceDao_Impl(this$0);
    }

    static final TaskDao_Impl _taskDao$lambda$6(CalisDatabase_Impl this$0) {
        return new TaskDao_Impl(this$0);
    }

    static final TaskSubmissionDao_Impl _taskSubmissionDao$lambda$7(CalisDatabase_Impl this$0) {
        return new TaskSubmissionDao_Impl(this$0);
    }

    static final SyncRecordDao_Impl _syncRecordDao$lambda$8(CalisDatabase_Impl this$0) {
        return new SyncRecordDao_Impl(this$0);
    }

    static final TaskDeliveryDao_Impl _taskDeliveryDao$lambda$9(CalisDatabase_Impl this$0) {
        return new TaskDeliveryDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.room.RoomDatabase
    public RoomOpenDelegate createOpenDelegate() {
        RoomOpenDelegate _openDelegate = new RoomOpenDelegate() { // from class: com.example.data.db.CalisDatabase_Impl$createOpenDelegate$_openDelegate$1
            {
                super(5, "406eed81ffdcf177681a1bf508d53fb9", "669e2372a637beee87414f6dfdaeb755");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void createAllTables(SQLiteConnection connection) throws Exception {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `teachers` (`tid` TEXT NOT NULL, `fullName` TEXT NOT NULL, `sex` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`tid`))");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `student_profiles` (`sid` TEXT NOT NULL, `fullName` TEXT NOT NULL, `sex` TEXT NOT NULL, `lrn` TEXT NOT NULL, `birth` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`sid`))");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `classrooms` (`cid` TEXT NOT NULL, `teacherId` TEXT NOT NULL, `classroomName` TEXT NOT NULL, `teacherName` TEXT NOT NULL, `days` TEXT NOT NULL, `startTime` TEXT NOT NULL, `graceTime` TEXT NOT NULL, `endTime` TEXT NOT NULL, `isActiveSession` INTEGER NOT NULL, `isBound` INTEGER NOT NULL, `deviceAddress` TEXT, PRIMARY KEY(`cid`), FOREIGN KEY(`teacherId`) REFERENCES `teachers`(`tid`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_classrooms_teacherId` ON `classrooms` (`teacherId`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `approved_students` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `classroomId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `studentName` TEXT NOT NULL, `sex` TEXT NOT NULL, `lrn` TEXT NOT NULL, `birth` INTEGER NOT NULL, `approvedAt` INTEGER NOT NULL, FOREIGN KEY(`classroomId`) REFERENCES `classrooms`(`cid`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_approved_students_classroomId` ON `approved_students` (`classroomId`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_approved_students_classroomId_studentId` ON `approved_students` (`classroomId`, `studentId`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `pending_registrations` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `classroomId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `studentName` TEXT NOT NULL, `sex` TEXT NOT NULL, `lrn` TEXT NOT NULL, `birth` INTEGER NOT NULL, `rid` INTEGER NOT NULL, `requestedAt` INTEGER NOT NULL, FOREIGN KEY(`classroomId`) REFERENCES `classrooms`(`cid`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_pending_registrations_classroomId` ON `pending_registrations` (`classroomId`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `attendance` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `classroomId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `studentName` TEXT NOT NULL, `date` TEXT NOT NULL, `status` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, FOREIGN KEY(`classroomId`) REFERENCES `classrooms`(`cid`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_attendance_classroomId` ON `attendance` (`classroomId`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_attendance_classroomId_studentId_date` ON `attendance` (`classroomId`, `studentId`, `date`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `tasks` (`tkid` TEXT NOT NULL, `classroomId` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `deadline` INTEGER NOT NULL, `datePosted` INTEGER NOT NULL, `maxScore` INTEGER NOT NULL, PRIMARY KEY(`tkid`), FOREIGN KEY(`classroomId`) REFERENCES `classrooms`(`cid`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_tasks_classroomId` ON `tasks` (`classroomId`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `task_submissions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `taskId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `studentName` TEXT NOT NULL, `score` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `isMarked` INTEGER NOT NULL, FOREIGN KEY(`taskId`) REFERENCES `tasks`(`tkid`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_task_submissions_taskId` ON `task_submissions` (`taskId`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_task_submissions_taskId_studentId` ON `task_submissions` (`taskId`, `studentId`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `sync_records` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `studentId` TEXT NOT NULL, `classroomId` TEXT NOT NULL, `lastScheduleSyncedAt` INTEGER NOT NULL, `lastTaskSyncedAt` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_sync_records_studentId_classroomId` ON `sync_records` (`studentId`, `classroomId`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `task_deliveries` (`taskId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `received` INTEGER NOT NULL, PRIMARY KEY(`taskId`, `studentId`))");
                SQLite.execSQL(connection, RoomMasterTable.CREATE_QUERY);
                SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '406eed81ffdcf177681a1bf508d53fb9')");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void dropAllTables(SQLiteConnection connection) throws Exception {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `teachers`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `student_profiles`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `classrooms`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `approved_students`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `pending_registrations`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `attendance`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `tasks`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `task_submissions`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `sync_records`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `task_deliveries`");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onCreate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onOpen(SQLiteConnection connection) throws Exception {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "PRAGMA foreign_keys = ON");
                this.this$0.internalInitInvalidationTracker(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPreMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                DBUtil.dropFtsSyncTriggers(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPostMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            @Override // androidx.room.RoomOpenDelegate
            public RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                Map _columnsTeachers = new LinkedHashMap();
                _columnsTeachers.put("tid", new TableInfo.Column("tid", "TEXT", true, 1, null, 1));
                _columnsTeachers.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, 1));
                _columnsTeachers.put("sex", new TableInfo.Column("sex", "TEXT", true, 0, null, 1));
                _columnsTeachers.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                Set _foreignKeysTeachers = new LinkedHashSet();
                Set _indicesTeachers = new LinkedHashSet();
                TableInfo _infoTeachers = new TableInfo("teachers", _columnsTeachers, _foreignKeysTeachers, _indicesTeachers);
                TableInfo _existingTeachers = TableInfo.INSTANCE.read(connection, "teachers");
                if (!_infoTeachers.equals(_existingTeachers)) {
                    return new RoomOpenDelegate.ValidationResult(false, "teachers(com.example.data.db.TeacherEntity).\n Expected:\n" + _infoTeachers + "\n Found:\n" + _existingTeachers);
                }
                Map _columnsStudentProfiles = new LinkedHashMap();
                _columnsStudentProfiles.put("sid", new TableInfo.Column("sid", "TEXT", true, 1, null, 1));
                _columnsStudentProfiles.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, 1));
                _columnsStudentProfiles.put("sex", new TableInfo.Column("sex", "TEXT", true, 0, null, 1));
                _columnsStudentProfiles.put("lrn", new TableInfo.Column("lrn", "TEXT", true, 0, null, 1));
                _columnsStudentProfiles.put("birth", new TableInfo.Column("birth", "INTEGER", true, 0, null, 1));
                _columnsStudentProfiles.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                Set _foreignKeysStudentProfiles = new LinkedHashSet();
                Set _indicesStudentProfiles = new LinkedHashSet();
                TableInfo _infoStudentProfiles = new TableInfo("student_profiles", _columnsStudentProfiles, _foreignKeysStudentProfiles, _indicesStudentProfiles);
                TableInfo _existingStudentProfiles = TableInfo.INSTANCE.read(connection, "student_profiles");
                if (!_infoStudentProfiles.equals(_existingStudentProfiles)) {
                    return new RoomOpenDelegate.ValidationResult(false, "student_profiles(com.example.data.db.StudentProfileEntity).\n Expected:\n" + _infoStudentProfiles + "\n Found:\n" + _existingStudentProfiles);
                }
                Map _columnsClassrooms = new LinkedHashMap();
                _columnsClassrooms.put("cid", new TableInfo.Column("cid", "TEXT", true, 1, null, 1));
                _columnsClassrooms.put("teacherId", new TableInfo.Column("teacherId", "TEXT", true, 0, null, 1));
                _columnsClassrooms.put("classroomName", new TableInfo.Column("classroomName", "TEXT", true, 0, null, 1));
                _columnsClassrooms.put("teacherName", new TableInfo.Column("teacherName", "TEXT", true, 0, null, 1));
                _columnsClassrooms.put("days", new TableInfo.Column("days", "TEXT", true, 0, null, 1));
                _columnsClassrooms.put("startTime", new TableInfo.Column("startTime", "TEXT", true, 0, null, 1));
                _columnsClassrooms.put("graceTime", new TableInfo.Column("graceTime", "TEXT", true, 0, null, 1));
                _columnsClassrooms.put("endTime", new TableInfo.Column("endTime", "TEXT", true, 0, null, 1));
                _columnsClassrooms.put("isActiveSession", new TableInfo.Column("isActiveSession", "INTEGER", true, 0, null, 1));
                _columnsClassrooms.put("isBound", new TableInfo.Column("isBound", "INTEGER", true, 0, null, 1));
                _columnsClassrooms.put("deviceAddress", new TableInfo.Column("deviceAddress", "TEXT", false, 0, null, 1));
                Set _foreignKeysClassrooms = new LinkedHashSet();
                _foreignKeysClassrooms.add(new TableInfo.ForeignKey("teachers", "CASCADE", "NO ACTION", CollectionsKt.listOf("teacherId"), CollectionsKt.listOf("tid")));
                Set _indicesClassrooms = new LinkedHashSet();
                _indicesClassrooms.add(new TableInfo.Index("index_classrooms_teacherId", false, CollectionsKt.listOf("teacherId"), CollectionsKt.listOf("ASC")));
                TableInfo _infoClassrooms = new TableInfo("classrooms", _columnsClassrooms, _foreignKeysClassrooms, _indicesClassrooms);
                TableInfo _existingClassrooms = TableInfo.INSTANCE.read(connection, "classrooms");
                if (!_infoClassrooms.equals(_existingClassrooms)) {
                    return new RoomOpenDelegate.ValidationResult(false, "classrooms(com.example.data.db.ClassroomEntity).\n Expected:\n" + _infoClassrooms + "\n Found:\n" + _existingClassrooms);
                }
                Map _columnsApprovedStudents = new LinkedHashMap();
                _columnsApprovedStudents.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsApprovedStudents.put("classroomId", new TableInfo.Column("classroomId", "TEXT", true, 0, null, 1));
                _columnsApprovedStudents.put("studentId", new TableInfo.Column("studentId", "TEXT", true, 0, null, 1));
                _columnsApprovedStudents.put("studentName", new TableInfo.Column("studentName", "TEXT", true, 0, null, 1));
                _columnsApprovedStudents.put("sex", new TableInfo.Column("sex", "TEXT", true, 0, null, 1));
                _columnsApprovedStudents.put("lrn", new TableInfo.Column("lrn", "TEXT", true, 0, null, 1));
                _columnsApprovedStudents.put("birth", new TableInfo.Column("birth", "INTEGER", true, 0, null, 1));
                _columnsApprovedStudents.put("approvedAt", new TableInfo.Column("approvedAt", "INTEGER", true, 0, null, 1));
                Set _foreignKeysApprovedStudents = new LinkedHashSet();
                _foreignKeysApprovedStudents.add(new TableInfo.ForeignKey("classrooms", "CASCADE", "NO ACTION", CollectionsKt.listOf("classroomId"), CollectionsKt.listOf("cid")));
                Set _indicesApprovedStudents = new LinkedHashSet();
                _indicesApprovedStudents.add(new TableInfo.Index("index_approved_students_classroomId", false, CollectionsKt.listOf("classroomId"), CollectionsKt.listOf("ASC")));
                _indicesApprovedStudents.add(new TableInfo.Index("index_approved_students_classroomId_studentId", true, CollectionsKt.listOf((Object[]) new String[]{"classroomId", "studentId"}), CollectionsKt.listOf((Object[]) new String[]{"ASC", "ASC"})));
                TableInfo _infoApprovedStudents = new TableInfo("approved_students", _columnsApprovedStudents, _foreignKeysApprovedStudents, _indicesApprovedStudents);
                TableInfo _existingApprovedStudents = TableInfo.INSTANCE.read(connection, "approved_students");
                if (!_infoApprovedStudents.equals(_existingApprovedStudents)) {
                    return new RoomOpenDelegate.ValidationResult(false, "approved_students(com.example.data.db.ApprovedStudentEntity).\n Expected:\n" + _infoApprovedStudents + "\n Found:\n" + _existingApprovedStudents);
                }
                Map _columnsPendingRegistrations = new LinkedHashMap();
                _columnsPendingRegistrations.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsPendingRegistrations.put("classroomId", new TableInfo.Column("classroomId", "TEXT", true, 0, null, 1));
                _columnsPendingRegistrations.put("studentId", new TableInfo.Column("studentId", "TEXT", true, 0, null, 1));
                _columnsPendingRegistrations.put("studentName", new TableInfo.Column("studentName", "TEXT", true, 0, null, 1));
                _columnsPendingRegistrations.put("sex", new TableInfo.Column("sex", "TEXT", true, 0, null, 1));
                _columnsPendingRegistrations.put("lrn", new TableInfo.Column("lrn", "TEXT", true, 0, null, 1));
                _columnsPendingRegistrations.put("birth", new TableInfo.Column("birth", "INTEGER", true, 0, null, 1));
                _columnsPendingRegistrations.put("rid", new TableInfo.Column("rid", "INTEGER", true, 0, null, 1));
                _columnsPendingRegistrations.put("requestedAt", new TableInfo.Column("requestedAt", "INTEGER", true, 0, null, 1));
                Set _foreignKeysPendingRegistrations = new LinkedHashSet();
                _foreignKeysPendingRegistrations.add(new TableInfo.ForeignKey("classrooms", "CASCADE", "NO ACTION", CollectionsKt.listOf("classroomId"), CollectionsKt.listOf("cid")));
                Set _indicesPendingRegistrations = new LinkedHashSet();
                _indicesPendingRegistrations.add(new TableInfo.Index("index_pending_registrations_classroomId", false, CollectionsKt.listOf("classroomId"), CollectionsKt.listOf("ASC")));
                TableInfo _infoPendingRegistrations = new TableInfo("pending_registrations", _columnsPendingRegistrations, _foreignKeysPendingRegistrations, _indicesPendingRegistrations);
                TableInfo _existingPendingRegistrations = TableInfo.INSTANCE.read(connection, "pending_registrations");
                if (!_infoPendingRegistrations.equals(_existingPendingRegistrations)) {
                    return new RoomOpenDelegate.ValidationResult(false, "pending_registrations(com.example.data.db.PendingRegistrationEntity).\n Expected:\n" + _infoPendingRegistrations + "\n Found:\n" + _existingPendingRegistrations);
                }
                Map _columnsAttendance = new LinkedHashMap();
                _columnsAttendance.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsAttendance.put("classroomId", new TableInfo.Column("classroomId", "TEXT", true, 0, null, 1));
                _columnsAttendance.put("studentId", new TableInfo.Column("studentId", "TEXT", true, 0, null, 1));
                _columnsAttendance.put("studentName", new TableInfo.Column("studentName", "TEXT", true, 0, null, 1));
                _columnsAttendance.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, 1));
                _columnsAttendance.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "TEXT", true, 0, null, 1));
                _columnsAttendance.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
                Set _foreignKeysAttendance = new LinkedHashSet();
                _foreignKeysAttendance.add(new TableInfo.ForeignKey("classrooms", "CASCADE", "NO ACTION", CollectionsKt.listOf("classroomId"), CollectionsKt.listOf("cid")));
                Set _indicesAttendance = new LinkedHashSet();
                _indicesAttendance.add(new TableInfo.Index("index_attendance_classroomId", false, CollectionsKt.listOf("classroomId"), CollectionsKt.listOf("ASC")));
                _indicesAttendance.add(new TableInfo.Index("index_attendance_classroomId_studentId_date", true, CollectionsKt.listOf((Object[]) new String[]{"classroomId", "studentId", "date"}), CollectionsKt.listOf((Object[]) new String[]{"ASC", "ASC", "ASC"})));
                TableInfo _infoAttendance = new TableInfo("attendance", _columnsAttendance, _foreignKeysAttendance, _indicesAttendance);
                TableInfo _existingAttendance = TableInfo.INSTANCE.read(connection, "attendance");
                if (!_infoAttendance.equals(_existingAttendance)) {
                    return new RoomOpenDelegate.ValidationResult(false, "attendance(com.example.data.db.AttendanceEntity).\n Expected:\n" + _infoAttendance + "\n Found:\n" + _existingAttendance);
                }
                Map _columnsTasks = new LinkedHashMap();
                _columnsTasks.put("tkid", new TableInfo.Column("tkid", "TEXT", true, 1, null, 1));
                _columnsTasks.put("classroomId", new TableInfo.Column("classroomId", "TEXT", true, 0, null, 1));
                _columnsTasks.put(LinkHeader.Parameters.Title, new TableInfo.Column(LinkHeader.Parameters.Title, "TEXT", true, 0, null, 1));
                _columnsTasks.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, 1));
                _columnsTasks.put("deadline", new TableInfo.Column("deadline", "INTEGER", true, 0, null, 1));
                _columnsTasks.put("datePosted", new TableInfo.Column("datePosted", "INTEGER", true, 0, null, 1));
                _columnsTasks.put("maxScore", new TableInfo.Column("maxScore", "INTEGER", true, 0, null, 1));
                Set _foreignKeysTasks = new LinkedHashSet();
                _foreignKeysTasks.add(new TableInfo.ForeignKey("classrooms", "CASCADE", "NO ACTION", CollectionsKt.listOf("classroomId"), CollectionsKt.listOf("cid")));
                Set _indicesTasks = new LinkedHashSet();
                _indicesTasks.add(new TableInfo.Index("index_tasks_classroomId", false, CollectionsKt.listOf("classroomId"), CollectionsKt.listOf("ASC")));
                TableInfo _infoTasks = new TableInfo("tasks", _columnsTasks, _foreignKeysTasks, _indicesTasks);
                TableInfo _existingTasks = TableInfo.INSTANCE.read(connection, "tasks");
                if (!_infoTasks.equals(_existingTasks)) {
                    return new RoomOpenDelegate.ValidationResult(false, "tasks(com.example.data.db.TaskEntity).\n Expected:\n" + _infoTasks + "\n Found:\n" + _existingTasks);
                }
                Map _columnsTaskSubmissions = new LinkedHashMap();
                _columnsTaskSubmissions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsTaskSubmissions.put("taskId", new TableInfo.Column("taskId", "TEXT", true, 0, null, 1));
                _columnsTaskSubmissions.put("studentId", new TableInfo.Column("studentId", "TEXT", true, 0, null, 1));
                _columnsTaskSubmissions.put("studentName", new TableInfo.Column("studentName", "TEXT", true, 0, null, 1));
                _columnsTaskSubmissions.put("score", new TableInfo.Column("score", "INTEGER", true, 0, null, 1));
                _columnsTaskSubmissions.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
                _columnsTaskSubmissions.put("isMarked", new TableInfo.Column("isMarked", "INTEGER", true, 0, null, 1));
                Set _foreignKeysTaskSubmissions = new LinkedHashSet();
                _foreignKeysTaskSubmissions.add(new TableInfo.ForeignKey("tasks", "CASCADE", "NO ACTION", CollectionsKt.listOf("taskId"), CollectionsKt.listOf("tkid")));
                Set _indicesTaskSubmissions = new LinkedHashSet();
                _indicesTaskSubmissions.add(new TableInfo.Index("index_task_submissions_taskId", false, CollectionsKt.listOf("taskId"), CollectionsKt.listOf("ASC")));
                _indicesTaskSubmissions.add(new TableInfo.Index("index_task_submissions_taskId_studentId", true, CollectionsKt.listOf((Object[]) new String[]{"taskId", "studentId"}), CollectionsKt.listOf((Object[]) new String[]{"ASC", "ASC"})));
                TableInfo _infoTaskSubmissions = new TableInfo("task_submissions", _columnsTaskSubmissions, _foreignKeysTaskSubmissions, _indicesTaskSubmissions);
                TableInfo _existingTaskSubmissions = TableInfo.INSTANCE.read(connection, "task_submissions");
                if (!_infoTaskSubmissions.equals(_existingTaskSubmissions)) {
                    return new RoomOpenDelegate.ValidationResult(false, "task_submissions(com.example.data.db.TaskSubmissionEntity).\n Expected:\n" + _infoTaskSubmissions + "\n Found:\n" + _existingTaskSubmissions);
                }
                Map _columnsSyncRecords = new LinkedHashMap();
                _columnsSyncRecords.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsSyncRecords.put("studentId", new TableInfo.Column("studentId", "TEXT", true, 0, null, 1));
                _columnsSyncRecords.put("classroomId", new TableInfo.Column("classroomId", "TEXT", true, 0, null, 1));
                _columnsSyncRecords.put("lastScheduleSyncedAt", new TableInfo.Column("lastScheduleSyncedAt", "INTEGER", true, 0, null, 1));
                _columnsSyncRecords.put("lastTaskSyncedAt", new TableInfo.Column("lastTaskSyncedAt", "INTEGER", true, 0, null, 1));
                Set _foreignKeysSyncRecords = new LinkedHashSet();
                Set _indicesSyncRecords = new LinkedHashSet();
                _indicesSyncRecords.add(new TableInfo.Index("index_sync_records_studentId_classroomId", true, CollectionsKt.listOf((Object[]) new String[]{"studentId", "classroomId"}), CollectionsKt.listOf((Object[]) new String[]{"ASC", "ASC"})));
                TableInfo _infoSyncRecords = new TableInfo("sync_records", _columnsSyncRecords, _foreignKeysSyncRecords, _indicesSyncRecords);
                TableInfo _existingSyncRecords = TableInfo.INSTANCE.read(connection, "sync_records");
                if (!_infoSyncRecords.equals(_existingSyncRecords)) {
                    return new RoomOpenDelegate.ValidationResult(false, "sync_records(com.example.data.db.SyncRecordEntity).\n Expected:\n" + _infoSyncRecords + "\n Found:\n" + _existingSyncRecords);
                }
                Map _columnsTaskDeliveries = new LinkedHashMap();
                _columnsTaskDeliveries.put("taskId", new TableInfo.Column("taskId", "TEXT", true, 1, null, 1));
                _columnsTaskDeliveries.put("studentId", new TableInfo.Column("studentId", "TEXT", true, 2, null, 1));
                _columnsTaskDeliveries.put("received", new TableInfo.Column("received", "INTEGER", true, 0, null, 1));
                Set _foreignKeysTaskDeliveries = new LinkedHashSet();
                Set _indicesTaskDeliveries = new LinkedHashSet();
                TableInfo _infoTaskDeliveries = new TableInfo("task_deliveries", _columnsTaskDeliveries, _foreignKeysTaskDeliveries, _indicesTaskDeliveries);
                TableInfo _existingTaskDeliveries = TableInfo.INSTANCE.read(connection, "task_deliveries");
                return !_infoTaskDeliveries.equals(_existingTaskDeliveries) ? new RoomOpenDelegate.ValidationResult(false, "task_deliveries(com.example.data.db.TaskDeliveryEntity).\n Expected:\n" + _infoTaskDeliveries + "\n Found:\n" + _existingTaskDeliveries) : new RoomOpenDelegate.ValidationResult(true, null);
            }
        };
        return _openDelegate;
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        Map _shadowTablesMap = new LinkedHashMap();
        Map _viewTables = new LinkedHashMap();
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "teachers", "student_profiles", "classrooms", "approved_students", "pending_registrations", "attendance", "tasks", "task_submissions", "sync_records", "task_deliveries");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.performClear(true, "teachers", "student_profiles", "classrooms", "approved_students", "pending_registrations", "attendance", "tasks", "task_submissions", "sync_records", "task_deliveries");
    }

    @Override // androidx.room.RoomDatabase
    protected Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        Map _typeConvertersMap = new LinkedHashMap();
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(TeacherDao.class), TeacherDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(StudentProfileDao.class), StudentProfileDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(ClassroomDao.class), ClassroomDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(ApprovedStudentDao.class), ApprovedStudentDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(PendingRegistrationDao.class), PendingRegistrationDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(AttendanceDao.class), AttendanceDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(TaskDao.class), TaskDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(TaskSubmissionDao.class), TaskSubmissionDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(SyncRecordDao.class), SyncRecordDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(TaskDeliveryDao.class), TaskDeliveryDao_Impl.INSTANCE.getRequiredConverters());
        return _typeConvertersMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        Set _autoMigrationSpecsSet = new LinkedHashSet();
        return _autoMigrationSpecsSet;
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> createAutoMigrations(Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> autoMigrationSpecs) {
        Intrinsics.checkNotNullParameter(autoMigrationSpecs, "autoMigrationSpecs");
        List _autoMigrations = new ArrayList();
        return _autoMigrations;
    }

    @Override // com.example.data.db.CalisDatabase
    public TeacherDao teacherDao() {
        return this._teacherDao.getValue();
    }

    @Override // com.example.data.db.CalisDatabase
    public StudentProfileDao studentProfileDao() {
        return this._studentProfileDao.getValue();
    }

    @Override // com.example.data.db.CalisDatabase
    public ClassroomDao classroomDao() {
        return this._classroomDao.getValue();
    }

    @Override // com.example.data.db.CalisDatabase
    public ApprovedStudentDao approvedStudentDao() {
        return this._approvedStudentDao.getValue();
    }

    @Override // com.example.data.db.CalisDatabase
    public PendingRegistrationDao pendingRegistrationDao() {
        return this._pendingRegistrationDao.getValue();
    }

    @Override // com.example.data.db.CalisDatabase
    public AttendanceDao attendanceDao() {
        return this._attendanceDao.getValue();
    }

    @Override // com.example.data.db.CalisDatabase
    public TaskDao taskDao() {
        return this._taskDao.getValue();
    }

    @Override // com.example.data.db.CalisDatabase
    public TaskSubmissionDao taskSubmissionDao() {
        return this._taskSubmissionDao.getValue();
    }

    @Override // com.example.data.db.CalisDatabase
    public SyncRecordDao syncRecordDao() {
        return this._syncRecordDao.getValue();
    }

    @Override // com.example.data.db.CalisDatabase
    public TaskDeliveryDao taskDeliveryDao() {
        return this._taskDeliveryDao.getValue();
    }
}
