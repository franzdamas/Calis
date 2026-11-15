package com.example.data.repository;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.MotionEventCompat;
import com.example.data.db.ApprovedStudentDao;
import com.example.data.db.ApprovedStudentEntity;
import com.example.data.db.AttendanceDao;
import com.example.data.db.AttendanceEntity;
import com.example.data.db.CalisDatabase;
import com.example.data.db.ClassroomDao;
import com.example.data.db.ClassroomEntity;
import com.example.data.db.PendingRegistrationDao;
import com.example.data.db.PendingRegistrationEntity;
import com.example.data.db.StudentProfileDao;
import com.example.data.db.StudentProfileEntity;
import com.example.data.db.SyncRecordDao;
import com.example.data.db.SyncRecordEntity;
import com.example.data.db.TaskDao;
import com.example.data.db.TaskDeliveryDao;
import com.example.data.db.TaskDeliveryEntity;
import com.example.data.db.TaskEntity;
import com.example.data.db.TaskSubmissionDao;
import com.example.data.db.TaskSubmissionEntity;
import com.example.data.db.TeacherDao;
import com.example.data.db.TeacherEntity;
import com.google.android.gms.common.Scopes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: compiled from: CalisRepository.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\u0019H\u0086@¢\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0086@¢\u0006\u0002\u0010\u001aJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0086@¢\u0006\u0002\u0010\u001aJ\u0016\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010%J\u0018\u0010&\u001a\u0004\u0018\u00010\u001c2\u0006\u0010'\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u0016\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u001eH\u0086@¢\u0006\u0002\u0010.J\u0018\u0010/\u001a\u0004\u0018\u00010\u001e2\u0006\u00100\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u0010\u00104\u001a\u0004\u0018\u000102H\u0086@¢\u0006\u0002\u0010\u001aJ\u0016\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020 2\u0006\u00106\u001a\u00020(J\u0018\u00107\u001a\u0004\u0018\u0001022\u0006\u00108\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u0016\u00109\u001a\u00020\u00192\u0006\u0010:\u001a\u000202H\u0086@¢\u0006\u0002\u0010;J\u001e\u0010<\u001a\u00020\u00192\u0006\u00108\u001a\u00020(2\u0006\u0010=\u001a\u00020>H\u0086@¢\u0006\u0002\u0010?J\u0016\u0010@\u001a\u00020\u00192\u0006\u00108\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u001a\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020C0B0 2\u0006\u0010D\u001a\u00020(J\u0016\u0010E\u001a\u00020\u00192\u0006\u0010F\u001a\u00020CH\u0086@¢\u0006\u0002\u0010GJ\u0016\u0010H\u001a\u00020\u00192\u0006\u0010F\u001a\u00020CH\u0086@¢\u0006\u0002\u0010GJ\u0016\u0010I\u001a\u00020\u00192\u0006\u0010F\u001a\u00020CH\u0086@¢\u0006\u0002\u0010GJ\u001a\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020K0B0 2\u0006\u0010D\u001a\u00020(J \u0010L\u001a\u0004\u0018\u00010K2\u0006\u0010D\u001a\u00020(2\u0006\u0010M\u001a\u00020(H\u0086@¢\u0006\u0002\u0010NJ\u0018\u0010O\u001a\u0004\u0018\u00010K2\u0006\u0010M\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u001e\u0010P\u001a\u00020>2\u0006\u0010D\u001a\u00020(2\u0006\u0010M\u001a\u00020(H\u0086@¢\u0006\u0002\u0010NJ\u001e\u0010Q\u001a\u00020\u00192\u0006\u0010D\u001a\u00020(2\u0006\u0010M\u001a\u00020(H\u0086@¢\u0006\u0002\u0010NJ\u001a\u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0B0 2\u0006\u0010D\u001a\u00020(J\"\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0B0 2\u0006\u0010D\u001a\u00020(2\u0006\u0010U\u001a\u00020(J$\u0010V\u001a\b\u0012\u0004\u0012\u00020S0B2\u0006\u0010D\u001a\u00020(2\u0006\u0010U\u001a\u00020(H\u0086@¢\u0006\u0002\u0010NJ\u001a\u0010W\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0B0 2\u0006\u0010M\u001a\u00020(J(\u0010X\u001a\u0004\u0018\u00010S2\u0006\u0010D\u001a\u00020(2\u0006\u0010M\u001a\u00020(2\u0006\u0010Y\u001a\u00020(H\u0086@¢\u0006\u0002\u0010ZJ8\u0010[\u001a\u00020\u00192\u0006\u0010D\u001a\u00020(2\u0006\u0010M\u001a\u00020(2\u0006\u0010\\\u001a\u00020(2\u0006\u0010]\u001a\u00020(2\b\b\u0002\u0010^\u001a\u00020_H\u0086@¢\u0006\u0002\u0010`J6\u0010a\u001a\u00020\u00192\u0006\u0010D\u001a\u00020(2\u0006\u0010M\u001a\u00020(2\u0006\u0010\\\u001a\u00020(2\u0006\u0010]\u001a\u00020(2\u0006\u0010^\u001a\u00020_H\u0086@¢\u0006\u0002\u0010`J.\u0010b\u001a\u00020\u00192\u0006\u0010D\u001a\u00020(2\u0006\u0010M\u001a\u00020(2\u0006\u0010Y\u001a\u00020(2\u0006\u0010c\u001a\u00020(H\u0086@¢\u0006\u0002\u0010dJ\u001a\u0010e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020f0B0 2\u0006\u0010D\u001a\u00020(J\u001c\u0010g\u001a\b\u0012\u0004\u0012\u00020f0B2\u0006\u0010D\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u0018\u0010h\u001a\u0004\u0018\u00010f2\u0006\u0010i\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u0016\u0010j\u001a\u00020\u00192\u0006\u0010k\u001a\u00020fH\u0086@¢\u0006\u0002\u0010lJ\u0016\u0010m\u001a\u00020\u00192\u0006\u0010i\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u001a\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020o0B0 2\u0006\u0010p\u001a\u00020(J\u001a\u0010q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020o0B0 2\u0006\u0010M\u001a\u00020(J8\u0010r\u001a\u00020\u00192\u0006\u0010p\u001a\u00020(2\u0006\u0010M\u001a\u00020(2\u0006\u0010\\\u001a\u00020(2\u0006\u0010s\u001a\u00020t2\b\b\u0002\u0010^\u001a\u00020_H\u0086@¢\u0006\u0002\u0010uJ \u0010v\u001a\u0004\u0018\u00010o2\u0006\u0010p\u001a\u00020(2\u0006\u0010M\u001a\u00020(H\u0086@¢\u0006\u0002\u0010NJ \u0010w\u001a\u0004\u0018\u00010x2\u0006\u0010M\u001a\u00020(2\u0006\u0010D\u001a\u00020(H\u0086@¢\u0006\u0002\u0010NJ.\u0010y\u001a\u00020\u00192\u0006\u0010M\u001a\u00020(2\u0006\u0010D\u001a\u00020(2\u0006\u0010z\u001a\u00020_2\u0006\u0010{\u001a\u00020_H\u0086@¢\u0006\u0002\u0010|J\u001a\u0010}\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020~0B0 2\u0006\u0010p\u001a\u00020(J\u001e\u0010\u007f\u001a\u00020\u00192\u0006\u0010p\u001a\u00020(2\u0006\u0010M\u001a\u00020(H\u0086@¢\u0006\u0002\u0010NJ\u001f\u0010\u0080\u0001\u001a\u00020>2\u0006\u0010p\u001a\u00020(2\u0006\u0010M\u001a\u00020(H\u0086@¢\u0006\u0002\u0010NJ'\u0010\u0081\u0001\u001a\u00020(2\u0006\u0010D\u001a\u00020(2\r\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020S0BH\u0086@¢\u0006\u0003\u0010\u0083\u0001J'\u0010\u0084\u0001\u001a\u00020(2\u0006\u0010k\u001a\u00020f2\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020o0BH\u0086@¢\u0006\u0003\u0010\u0086\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0019\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0 ¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\"R\u0019\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020 ¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\"¨\u0006\u0087\u0001"}, d2 = {"Lcom/example/data/repository/CalisRepository;", "", "database", "Lcom/example/data/db/CalisDatabase;", "teacherDao", "Lcom/example/data/db/TeacherDao;", "studentProfileDao", "Lcom/example/data/db/StudentProfileDao;", "classroomDao", "Lcom/example/data/db/ClassroomDao;", "approvedStudentDao", "Lcom/example/data/db/ApprovedStudentDao;", "pendingRegistrationDao", "Lcom/example/data/db/PendingRegistrationDao;", "attendanceDao", "Lcom/example/data/db/AttendanceDao;", "taskDao", "Lcom/example/data/db/TaskDao;", "taskSubmissionDao", "Lcom/example/data/db/TaskSubmissionDao;", "syncRecordDao", "Lcom/example/data/db/SyncRecordDao;", "<init>", "(Lcom/example/data/db/CalisDatabase;Lcom/example/data/db/TeacherDao;Lcom/example/data/db/StudentProfileDao;Lcom/example/data/db/ClassroomDao;Lcom/example/data/db/ApprovedStudentDao;Lcom/example/data/db/PendingRegistrationDao;Lcom/example/data/db/AttendanceDao;Lcom/example/data/db/TaskDao;Lcom/example/data/db/TaskSubmissionDao;Lcom/example/data/db/SyncRecordDao;)V", "deleteAllData", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTeacherOnce", "Lcom/example/data/db/TeacherEntity;", "getStudentProfileOnce", "Lcom/example/data/db/StudentProfileEntity;", "currentTeacher", "Lkotlinx/coroutines/flow/Flow;", "getCurrentTeacher", "()Lkotlinx/coroutines/flow/Flow;", "saveTeacher", "teacher", "(Lcom/example/data/db/TeacherEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTeacherById", "tid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "currentStudentProfile", "getCurrentStudentProfile", "saveStudentProfile", Scopes.PROFILE, "(Lcom/example/data/db/StudentProfileEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStudentProfileById", "sid", "activeBoundClassroom", "Lcom/example/data/db/ClassroomEntity;", "getActiveBoundClassroom", "getActiveBoundClassroomSync", "getClassroomForTeacher", "teacherId", "getClassroomById", "cid", "saveClassroom", "classroom", "(Lcom/example/data/db/ClassroomEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateClassroomSession", "isActive", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unregisterClassroom", "getPendingRegistrations", "", "Lcom/example/data/db/PendingRegistrationEntity;", "classroomId", "addPendingRegistration", "pending", "(Lcom/example/data/db/PendingRegistrationEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "approveStudentRegistration", "rejectStudentRegistration", "getApprovedStudents", "Lcom/example/data/db/ApprovedStudentEntity;", "getApprovedStudent", "studentId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApprovedStudentGlobally", "isStudentApproved", "removeApprovedStudent", "getAttendanceForClassroom", "Lcom/example/data/db/AttendanceEntity;", "getAttendanceForDate", "date", "getAttendanceForDateSync", "getAttendanceForStudent", "getAttendanceRecord", "dateStr", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordAttendance", "studentName", NotificationCompat.CATEGORY_STATUS, "timestamp", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordStudentAttendance", "updateAttendanceStatus", "newStatus", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTasksForClassroom", "Lcom/example/data/db/TaskEntity;", "getTasksForClassroomSync", "getTaskById", "tkid", "saveTask", "task", "(Lcom/example/data/db/TaskEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTask", "getSubmissionsForTask", "Lcom/example/data/db/TaskSubmissionEntity;", "taskId", "getSubmissionsForStudent", "recordTaskSubmission", "score", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSubmission", "getSyncRecord", "Lcom/example/data/db/SyncRecordEntity;", "updateSyncRecord", "scheduleTime", "taskTime", "(Ljava/lang/String;Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeliveriesForTask", "Lcom/example/data/db/TaskDeliveryEntity;", "markTaskAsReceived", "hasStudentReceivedTask", "exportAttendanceCsv", "attendanceList", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exportTaskSubmissionsCsv", "submissions", "(Lcom/example/data/db/TaskEntity;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CalisRepository {
    public static final int $stable = 8;
    private final Flow<ClassroomEntity> activeBoundClassroom;
    private final ApprovedStudentDao approvedStudentDao;
    private final AttendanceDao attendanceDao;
    private final ClassroomDao classroomDao;
    private final Flow<StudentProfileEntity> currentStudentProfile;
    private final Flow<TeacherEntity> currentTeacher;
    private final CalisDatabase database;
    private final PendingRegistrationDao pendingRegistrationDao;
    private final StudentProfileDao studentProfileDao;
    private final SyncRecordDao syncRecordDao;
    private final TaskDao taskDao;
    private final TaskSubmissionDao taskSubmissionDao;
    private final TeacherDao teacherDao;

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$addPendingRegistration$1, reason: invalid class name */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 1, 1, 2, 2}, l = {128, 130, 132}, m = "addPendingRegistration", n = {"pending", "pending", "existing", "pending", "existing"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.addPendingRegistration(null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$approveStudentRegistration$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4}, l = {145, 146, 149, 158, 172}, m = "approveStudentRegistration", n = {"pending", "approved", "pending", "approved", "pending", "approved", "pending", "approved", "todayDateStr", "pending", "approved", "todayDateStr", "allLogs", "previousDates", "date", "existingOnDate", "record", "timestamp"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7", "L$8", "J$0"})
    static final class C07591 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        C07591(Continuation<? super C07591> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.approveStudentRegistration(null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$exportTaskSubmissionsCsv$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {406, 407}, m = "exportTaskSubmissionsCsv", n = {"task", "submissions", "sb", "sub", "count", "task", "submissions", "sb", "sub", "count"}, s = {"L$0", "L$1", "L$2", "L$4", "I$0", "L$0", "L$1", "L$2", "L$4", "I$0"})
    static final class C07601 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C07601(Continuation<? super C07601> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.exportTaskSubmissionsCsv(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$hasStudentReceivedTask$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 0}, l = {379}, m = "hasStudentReceivedTask", n = {"taskId", "studentId"}, s = {"L$0", "L$1"})
    static final class C07651 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07651(Continuation<? super C07651> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.hasStudentReceivedTask(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$isStudentApproved$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 0}, l = {193}, m = "isStudentApproved", n = {"classroomId", "studentId"}, s = {"L$0", "L$1"})
    static final class C07661 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07661(Continuation<? super C07661> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.isStudentApproved(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$recordAttendance$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {227, 235, 245}, m = "recordAttendance", n = {"classroomId", "studentId", "studentName", NotificationCompat.CATEGORY_STATUS, "dateFormat", "dateStr", "finalStatus", "timestamp", "classroomId", "studentId", "studentName", NotificationCompat.CATEGORY_STATUS, "dateFormat", "dateStr", "finalStatus", "student", "timestamp", "classroomId", "studentId", "studentName", NotificationCompat.CATEGORY_STATUS, "dateFormat", "dateStr", "finalStatus", "student", "existing", "record", "timestamp"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "J$0"})
    static final class C07671 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        C07671(Continuation<? super C07671> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.recordAttendance(null, null, null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$recordStudentAttendance$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {259, 277}, m = "recordStudentAttendance", n = {"classroomId", "studentId", "studentName", NotificationCompat.CATEGORY_STATUS, "dateFormat", "dateStr", "finalStatus", "timestamp", "classroomId", "studentId", "studentName", NotificationCompat.CATEGORY_STATUS, "dateFormat", "dateStr", "finalStatus", "student", "record", "timestamp"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "J$0"})
    static final class C07681 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        C07681(Continuation<? super C07681> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.recordStudentAttendance(null, null, null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$saveClassroom$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 1, 1, 2, 2}, l = {100, LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY, LocationRequestCompat.QUALITY_LOW_POWER}, m = "saveClassroom", n = {"classroom", "classroom", "existing", "classroom", "existing"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
    static final class C07691 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07691(Continuation<? super C07691> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.saveClassroom(null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$saveStudentProfile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 1, 1, 2, 2}, l = {72, 74, 76}, m = "saveStudentProfile", n = {Scopes.PROFILE, Scopes.PROFILE, "existing", Scopes.PROFILE, "existing"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
    static final class C07701 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07701(Continuation<? super C07701> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.saveStudentProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$saveTask$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 1, 1, 2, 2}, l = {313, 315, 317}, m = "saveTask", n = {"task", "task", "existing", "task", "existing"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
    static final class C07711 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07711(Continuation<? super C07711> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.saveTask(null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$saveTeacher$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 1, 1, 2, 2}, l = {56, 58, LockFreeTaskQueueCore.FROZEN_SHIFT}, m = "saveTeacher", n = {"teacher", "teacher", "existing", "teacher", "existing"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
    static final class C07721 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07721(Continuation<? super C07721> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.saveTeacher(null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$unregisterClassroom$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 1, 1}, l = {116, 118}, m = "unregisterClassroom", n = {"cid", "cid", "existing"}, s = {"L$0", "L$0", "L$1"})
    static final class C07731 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07731(Continuation<? super C07731> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.unregisterClassroom(null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$updateAttendanceStatus$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3}, l = {286, 288, 290, 299}, m = "updateAttendanceStatus", n = {"classroomId", "studentId", "dateStr", "newStatus", "classroomId", "studentId", "dateStr", "newStatus", "existing", "classroomId", "studentId", "dateStr", "newStatus", "existing", "classroomId", "studentId", "dateStr", "newStatus", "existing", "studentName", "record"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
    static final class C07741 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C07741(Continuation<? super C07741> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.updateAttendanceStatus(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$updateClassroomSession$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository", f = "CalisRepository.kt", i = {0, 0, 1, 1, 1}, l = {AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 111}, m = "updateClassroomSession", n = {"cid", "isActive", "cid", "existing", "isActive"}, s = {"L$0", "Z$0", "L$0", "L$1", "Z$0"})
    static final class C07751 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C07751(Continuation<? super C07751> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalisRepository.this.updateClassroomSession(null, false, this);
        }
    }

    public CalisRepository(CalisDatabase database, TeacherDao teacherDao, StudentProfileDao studentProfileDao, ClassroomDao classroomDao, ApprovedStudentDao approvedStudentDao, PendingRegistrationDao pendingRegistrationDao, AttendanceDao attendanceDao, TaskDao taskDao, TaskSubmissionDao taskSubmissionDao, SyncRecordDao syncRecordDao) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(teacherDao, "teacherDao");
        Intrinsics.checkNotNullParameter(studentProfileDao, "studentProfileDao");
        Intrinsics.checkNotNullParameter(classroomDao, "classroomDao");
        Intrinsics.checkNotNullParameter(approvedStudentDao, "approvedStudentDao");
        Intrinsics.checkNotNullParameter(pendingRegistrationDao, "pendingRegistrationDao");
        Intrinsics.checkNotNullParameter(attendanceDao, "attendanceDao");
        Intrinsics.checkNotNullParameter(taskDao, "taskDao");
        Intrinsics.checkNotNullParameter(taskSubmissionDao, "taskSubmissionDao");
        Intrinsics.checkNotNullParameter(syncRecordDao, "syncRecordDao");
        this.database = database;
        this.teacherDao = teacherDao;
        this.studentProfileDao = studentProfileDao;
        this.classroomDao = classroomDao;
        this.approvedStudentDao = approvedStudentDao;
        this.pendingRegistrationDao = pendingRegistrationDao;
        this.attendanceDao = attendanceDao;
        this.taskDao = taskDao;
        this.taskSubmissionDao = taskSubmissionDao;
        this.syncRecordDao = syncRecordDao;
        this.currentTeacher = this.teacherDao.getTeacher();
        this.currentStudentProfile = this.studentProfileDao.getStudentProfile();
        this.activeBoundClassroom = this.classroomDao.getActiveBoundClassroom();
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$deleteAllData$2, reason: invalid class name */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository$deleteAllData$2", f = "CalisRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisRepository.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CalisRepository.this.database.clearAllTables();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object deleteAllData(Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$getTeacherOnce$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/data/db/TeacherEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository$getTeacherOnce$2", f = "CalisRepository.kt", i = {}, l = {MotionEventCompat.AXIS_GENERIC_15}, m = "invokeSuspend", n = {}, s = {})
    static final class C07642 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TeacherEntity>, Object> {
        int label;

        C07642(Continuation<? super C07642> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisRepository.this.new C07642(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TeacherEntity> continuation) {
            return ((C07642) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object teacherOnce = CalisRepository.this.teacherDao.getTeacherOnce(this);
                    if (teacherOnce == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return teacherOnce;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object getTeacherOnce(Continuation<? super TeacherEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07642(null), continuation);
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$getStudentProfileOnce$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/data/db/StudentProfileEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository$getStudentProfileOnce$2", f = "CalisRepository.kt", i = {}, l = {50}, m = "invokeSuspend", n = {}, s = {})
    static final class C07632 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super StudentProfileEntity>, Object> {
        int label;

        C07632(Continuation<? super C07632> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisRepository.this.new C07632(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super StudentProfileEntity> continuation) {
            return ((C07632) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object studentProfileOnce = CalisRepository.this.studentProfileDao.getStudentProfileOnce(this);
                    if (studentProfileOnce == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return studentProfileOnce;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object getStudentProfileOnce(Continuation<? super StudentProfileEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07632(null), continuation);
    }

    public final Flow<TeacherEntity> getCurrentTeacher() {
        return this.currentTeacher;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x005f  */
    /* JADX WARN: Code duplicated, block: B:21:0x0074 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x0075  */
    /* JADX WARN: Code duplicated, block: B:25:0x0079  */
    /* JADX WARN: Code duplicated, block: B:27:0x008e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x008f  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object saveTeacher(TeacherEntity teacher, Continuation<? super Unit> continuation) {
        C07721 c07721;
        Object teacherById;
        TeacherEntity existing;
        TeacherDao teacherDao;
        if (continuation instanceof C07721) {
            c07721 = (C07721) continuation;
            if ((c07721.label & Integer.MIN_VALUE) != 0) {
                c07721.label -= Integer.MIN_VALUE;
            } else {
                c07721 = new C07721(continuation);
            }
        } else {
            c07721 = new C07721(continuation);
        }
        Object $result = c07721.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07721.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                TeacherDao teacherDao2 = this.teacherDao;
                String tid = teacher.getTid();
                c07721.L$0 = teacher;
                c07721.label = 1;
                teacherById = teacherDao2.getTeacherById(tid, c07721);
                if (teacherById == coroutine_suspended) {
                    return coroutine_suspended;
                }
                existing = (TeacherEntity) teacherById;
                teacherDao = this.teacherDao;
                if (existing != null) {
                    c07721.L$0 = SpillingKt.nullOutSpilledVariable(teacher);
                    c07721.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07721.label = 2;
                    if (teacherDao.updateTeacher(teacher, c07721) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    c07721.L$0 = SpillingKt.nullOutSpilledVariable(teacher);
                    c07721.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07721.label = 3;
                    if (teacherDao.insertTeacher(teacher, c07721) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                teacher = (TeacherEntity) c07721.L$0;
                ResultKt.throwOnFailure($result);
                teacherById = $result;
                existing = (TeacherEntity) teacherById;
                teacherDao = this.teacherDao;
                if (existing != null) {
                    c07721.L$0 = SpillingKt.nullOutSpilledVariable(teacher);
                    c07721.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07721.label = 2;
                    if (teacherDao.updateTeacher(teacher, c07721) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    c07721.L$0 = SpillingKt.nullOutSpilledVariable(teacher);
                    c07721.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07721.label = 3;
                    if (teacherDao.insertTeacher(teacher, c07721) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 2:
            case 3:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getTeacherById(String tid, Continuation<? super TeacherEntity> continuation) {
        return this.teacherDao.getTeacherById(tid, continuation);
    }

    public final Flow<StudentProfileEntity> getCurrentStudentProfile() {
        return this.currentStudentProfile;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x005b  */
    /* JADX WARN: Code duplicated, block: B:21:0x0070 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x0071  */
    /* JADX WARN: Code duplicated, block: B:25:0x0075  */
    /* JADX WARN: Code duplicated, block: B:27:0x008a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x008b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object saveStudentProfile(StudentProfileEntity profile, Continuation<? super Unit> continuation) {
        C07701 c07701;
        Object studentProfileOnce;
        StudentProfileEntity existing;
        StudentProfileDao studentProfileDao;
        if (continuation instanceof C07701) {
            c07701 = (C07701) continuation;
            if ((c07701.label & Integer.MIN_VALUE) != 0) {
                c07701.label -= Integer.MIN_VALUE;
            } else {
                c07701 = new C07701(continuation);
            }
        } else {
            c07701 = new C07701(continuation);
        }
        Object $result = c07701.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07701.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                StudentProfileDao studentProfileDao2 = this.studentProfileDao;
                c07701.L$0 = profile;
                c07701.label = 1;
                studentProfileOnce = studentProfileDao2.getStudentProfileOnce(c07701);
                if (studentProfileOnce == coroutine_suspended) {
                    return coroutine_suspended;
                }
                existing = (StudentProfileEntity) studentProfileOnce;
                studentProfileDao = this.studentProfileDao;
                if (existing != null) {
                    c07701.L$0 = SpillingKt.nullOutSpilledVariable(profile);
                    c07701.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07701.label = 2;
                    if (studentProfileDao.updateStudentProfile(profile, c07701) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    c07701.L$0 = SpillingKt.nullOutSpilledVariable(profile);
                    c07701.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07701.label = 3;
                    if (studentProfileDao.insertStudentProfile(profile, c07701) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                profile = (StudentProfileEntity) c07701.L$0;
                ResultKt.throwOnFailure($result);
                studentProfileOnce = $result;
                existing = (StudentProfileEntity) studentProfileOnce;
                studentProfileDao = this.studentProfileDao;
                if (existing != null) {
                    c07701.L$0 = SpillingKt.nullOutSpilledVariable(profile);
                    c07701.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07701.label = 2;
                    if (studentProfileDao.updateStudentProfile(profile, c07701) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    c07701.L$0 = SpillingKt.nullOutSpilledVariable(profile);
                    c07701.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07701.label = 3;
                    if (studentProfileDao.insertStudentProfile(profile, c07701) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 2:
            case 3:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$getStudentProfileById$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/data/db/StudentProfileEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository$getStudentProfileById$2", f = "CalisRepository.kt", i = {}, l = {81}, m = "invokeSuspend", n = {}, s = {})
    static final class C07622 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super StudentProfileEntity>, Object> {
        final /* synthetic */ String $sid;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07622(String str, Continuation<? super C07622> continuation) {
            super(2, continuation);
            this.$sid = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisRepository.this.new C07622(this.$sid, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super StudentProfileEntity> continuation) {
            return ((C07622) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object studentProfileById = CalisRepository.this.studentProfileDao.getStudentProfileById(this.$sid, this);
                    if (studentProfileById == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return studentProfileById;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object getStudentProfileById(String sid, Continuation<? super StudentProfileEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07622(sid, null), continuation);
    }

    public final Flow<ClassroomEntity> getActiveBoundClassroom() {
        return this.activeBoundClassroom;
    }

    /* JADX INFO: renamed from: com.example.data.repository.CalisRepository$getActiveBoundClassroomSync$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalisRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/data/db/ClassroomEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.data.repository.CalisRepository$getActiveBoundClassroomSync$2", f = "CalisRepository.kt", i = {}, l = {88}, m = "invokeSuspend", n = {}, s = {})
    static final class C07612 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ClassroomEntity>, Object> {
        int label;

        C07612(Continuation<? super C07612> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CalisRepository.this.new C07612(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ClassroomEntity> continuation) {
            return ((C07612) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object activeBoundClassroomSync = CalisRepository.this.classroomDao.getActiveBoundClassroomSync(this);
                    if (activeBoundClassroomSync == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return activeBoundClassroomSync;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object getActiveBoundClassroomSync(Continuation<? super ClassroomEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07612(null), continuation);
    }

    public final Flow<ClassroomEntity> getClassroomForTeacher(String teacherId) {
        Intrinsics.checkNotNullParameter(teacherId, "teacherId");
        return this.classroomDao.getClassroomForTeacher(teacherId);
    }

    public final Object getClassroomById(String cid, Continuation<? super ClassroomEntity> continuation) {
        return this.classroomDao.getClassroomById(cid, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x005f  */
    /* JADX WARN: Code duplicated, block: B:21:0x0074 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x0075  */
    /* JADX WARN: Code duplicated, block: B:25:0x0079  */
    /* JADX WARN: Code duplicated, block: B:27:0x008e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x008f  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object saveClassroom(ClassroomEntity classroom, Continuation<? super Unit> continuation) {
        C07691 c07691;
        Object classroomById;
        ClassroomEntity existing;
        ClassroomDao classroomDao;
        if (continuation instanceof C07691) {
            c07691 = (C07691) continuation;
            if ((c07691.label & Integer.MIN_VALUE) != 0) {
                c07691.label -= Integer.MIN_VALUE;
            } else {
                c07691 = new C07691(continuation);
            }
        } else {
            c07691 = new C07691(continuation);
        }
        Object $result = c07691.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07691.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ClassroomDao classroomDao2 = this.classroomDao;
                String cid = classroom.getCid();
                c07691.L$0 = classroom;
                c07691.label = 1;
                classroomById = classroomDao2.getClassroomById(cid, c07691);
                if (classroomById == coroutine_suspended) {
                    return coroutine_suspended;
                }
                existing = (ClassroomEntity) classroomById;
                classroomDao = this.classroomDao;
                if (existing != null) {
                    c07691.L$0 = SpillingKt.nullOutSpilledVariable(classroom);
                    c07691.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07691.label = 2;
                    if (classroomDao.updateClassroom(classroom, c07691) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    c07691.L$0 = SpillingKt.nullOutSpilledVariable(classroom);
                    c07691.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07691.label = 3;
                    if (classroomDao.insertClassroom(classroom, c07691) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                classroom = (ClassroomEntity) c07691.L$0;
                ResultKt.throwOnFailure($result);
                classroomById = $result;
                existing = (ClassroomEntity) classroomById;
                classroomDao = this.classroomDao;
                if (existing != null) {
                    c07691.L$0 = SpillingKt.nullOutSpilledVariable(classroom);
                    c07691.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07691.label = 2;
                    if (classroomDao.updateClassroom(classroom, c07691) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    c07691.L$0 = SpillingKt.nullOutSpilledVariable(classroom);
                    c07691.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07691.label = 3;
                    if (classroomDao.insertClassroom(classroom, c07691) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 2:
            case 3:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0072  */
    /* JADX WARN: Code duplicated, block: B:22:0x00a2 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:26:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object updateClassroomSession(String cid, boolean isActive, Continuation<? super Unit> continuation) {
        C07751 c07751;
        Object classroomById;
        boolean isActive2;
        String cid2;
        ClassroomEntity existing;
        ClassroomDao classroomDao;
        ClassroomEntity classroomEntityCopy$default;
        if (continuation instanceof C07751) {
            c07751 = (C07751) continuation;
            if ((c07751.label & Integer.MIN_VALUE) != 0) {
                c07751.label -= Integer.MIN_VALUE;
            } else {
                c07751 = new C07751(continuation);
            }
        } else {
            c07751 = new C07751(continuation);
        }
        Object $result = c07751.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07751.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ClassroomDao classroomDao2 = this.classroomDao;
                c07751.L$0 = SpillingKt.nullOutSpilledVariable(cid);
                c07751.Z$0 = isActive;
                c07751.label = 1;
                classroomById = classroomDao2.getClassroomById(cid, c07751);
                if (classroomById == coroutine_suspended) {
                    return coroutine_suspended;
                }
                isActive2 = isActive;
                cid2 = cid;
                existing = (ClassroomEntity) classroomById;
                if (existing != null) {
                    return Unit.INSTANCE;
                }
                classroomDao = this.classroomDao;
                classroomEntityCopy$default = ClassroomEntity.copy$default(existing, null, null, null, null, null, null, null, null, isActive2, false, null, 1791, null);
                c07751.L$0 = SpillingKt.nullOutSpilledVariable(cid2);
                c07751.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                c07751.Z$0 = isActive2;
                c07751.label = 2;
                if (classroomDao.updateClassroom(classroomEntityCopy$default, c07751) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                boolean isActive3 = c07751.Z$0;
                cid2 = (String) c07751.L$0;
                ResultKt.throwOnFailure($result);
                isActive2 = isActive3;
                classroomById = $result;
                existing = (ClassroomEntity) classroomById;
                if (existing != null) {
                    return Unit.INSTANCE;
                }
                classroomDao = this.classroomDao;
                classroomEntityCopy$default = ClassroomEntity.copy$default(existing, null, null, null, null, null, null, null, null, isActive2, false, null, 1791, null);
                c07751.L$0 = SpillingKt.nullOutSpilledVariable(cid2);
                c07751.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                c07751.Z$0 = isActive2;
                c07751.label = 2;
                if (classroomDao.updateClassroom(classroomEntityCopy$default, c07751) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                boolean z = c07751.Z$0;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0068  */
    /* JADX WARN: Code duplicated, block: B:22:0x0095 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x0096  */
    /* JADX WARN: Code duplicated, block: B:26:0x009a  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object unregisterClassroom(String cid, Continuation<? super Unit> continuation) {
        C07731 c07731;
        Object obj;
        String cid2;
        ClassroomEntity existing;
        ClassroomDao classroomDao;
        ClassroomEntity classroomEntityCopy$default;
        if (continuation instanceof C07731) {
            c07731 = (C07731) continuation;
            if ((c07731.label & Integer.MIN_VALUE) != 0) {
                c07731.label -= Integer.MIN_VALUE;
            } else {
                c07731 = new C07731(continuation);
            }
        } else {
            c07731 = new C07731(continuation);
        }
        Object $result = c07731.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07731.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ClassroomDao classroomDao2 = this.classroomDao;
                c07731.L$0 = SpillingKt.nullOutSpilledVariable(cid);
                c07731.label = 1;
                Object classroomById = classroomDao2.getClassroomById(cid, c07731);
                if (classroomById == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = classroomById;
                cid2 = cid;
                existing = (ClassroomEntity) obj;
                if (existing != null) {
                    return Unit.INSTANCE;
                }
                classroomDao = this.classroomDao;
                classroomEntityCopy$default = ClassroomEntity.copy$default(existing, null, null, null, null, null, null, null, null, false, false, null, 255, null);
                c07731.L$0 = SpillingKt.nullOutSpilledVariable(cid2);
                c07731.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                c07731.label = 2;
                if (classroomDao.updateClassroom(classroomEntityCopy$default, c07731) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                cid2 = (String) c07731.L$0;
                ResultKt.throwOnFailure($result);
                obj = $result;
                existing = (ClassroomEntity) obj;
                if (existing != null) {
                    return Unit.INSTANCE;
                }
                classroomDao = this.classroomDao;
                classroomEntityCopy$default = ClassroomEntity.copy$default(existing, null, null, null, null, null, null, null, null, false, false, null, 255, null);
                c07731.L$0 = SpillingKt.nullOutSpilledVariable(cid2);
                c07731.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                c07731.label = 2;
                if (classroomDao.updateClassroom(classroomEntityCopy$default, c07731) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Flow<List<PendingRegistrationEntity>> getPendingRegistrations(String classroomId) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        return this.pendingRegistrationDao.getPendingRegistrations(classroomId);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x006c  */
    /* JADX WARN: Code duplicated, block: B:22:0x00a6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:26:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:28:0x00c2 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:29:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object addPendingRegistration(PendingRegistrationEntity pending, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object pendingRegistration;
        PendingRegistrationEntity pending2;
        PendingRegistrationEntity existing;
        PendingRegistrationDao pendingRegistrationDao;
        PendingRegistrationEntity pendingRegistrationEntityCopy;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object $result = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PendingRegistrationDao pendingRegistrationDao2 = this.pendingRegistrationDao;
                String classroomId = pending.getClassroomId();
                String studentId = pending.getStudentId();
                anonymousClass1.L$0 = pending;
                anonymousClass1.label = 1;
                pendingRegistration = pendingRegistrationDao2.getPendingRegistration(classroomId, studentId, anonymousClass1);
                if (pendingRegistration == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pending2 = pending;
                existing = (PendingRegistrationEntity) pendingRegistration;
                pendingRegistrationDao = this.pendingRegistrationDao;
                if (existing != null) {
                    pendingRegistrationEntityCopy = pending2.copy((510 & 1) != 0 ? pending2.id : existing.getId(), (510 & 2) != 0 ? pending2.classroomId : null, (510 & 4) != 0 ? pending2.studentId : null, (510 & 8) != 0 ? pending2.studentName : null, (510 & 16) != 0 ? pending2.sex : null, (510 & 32) != 0 ? pending2.lrn : null, (510 & 64) != 0 ? pending2.birth : 0L, (510 & 128) != 0 ? pending2.rid : 0, (510 & 256) != 0 ? pending2.requestedAt : 0L);
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(pending2);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    anonymousClass1.label = 2;
                    if (pendingRegistrationDao.insertPendingRegistration(pendingRegistrationEntityCopy, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(pending2);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    anonymousClass1.label = 3;
                    if (pendingRegistrationDao.insertPendingRegistration(pending2, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                PendingRegistrationEntity pending3 = (PendingRegistrationEntity) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                pending2 = pending3;
                pendingRegistration = $result;
                existing = (PendingRegistrationEntity) pendingRegistration;
                pendingRegistrationDao = this.pendingRegistrationDao;
                if (existing != null) {
                    pendingRegistrationEntityCopy = pending2.copy((510 & 1) != 0 ? pending2.id : existing.getId(), (510 & 2) != 0 ? pending2.classroomId : null, (510 & 4) != 0 ? pending2.studentId : null, (510 & 8) != 0 ? pending2.studentName : null, (510 & 16) != 0 ? pending2.sex : null, (510 & 32) != 0 ? pending2.lrn : null, (510 & 64) != 0 ? pending2.birth : 0L, (510 & 128) != 0 ? pending2.rid : 0, (510 & 256) != 0 ? pending2.requestedAt : 0L);
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(pending2);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    anonymousClass1.label = 2;
                    if (pendingRegistrationDao.insertPendingRegistration(pendingRegistrationEntityCopy, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(pending2);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    anonymousClass1.label = 3;
                    if (pendingRegistrationDao.insertPendingRegistration(pending2, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 2:
            case 3:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x00f3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:27:0x011c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x011d  */
    /* JADX WARN: Code duplicated, block: B:31:0x014c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x016c A[LOOP:2: B:33:0x0166->B:35:0x016c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:39:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:45:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:48:0x01df  */
    /* JADX WARN: Code duplicated, block: B:51:0x01f6 A[LOOP:1: B:46:0x01d9->B:51:0x01f6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:55:0x0201  */
    /* JADX WARN: Code duplicated, block: B:56:0x0206  */
    /* JADX WARN: Code duplicated, block: B:60:0x026c A[LOOP:0: B:43:0x01c5->B:60:0x026c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:65:0x026b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x01fc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x01f9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x01b0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x019b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object approveStudentRegistration(PendingRegistrationEntity pending, Continuation<? super Unit> continuation) {
        C07591 c07591;
        PendingRegistrationEntity pending2;
        ApprovedStudentEntity approved;
        PendingRegistrationDao pendingRegistrationDao;
        String classroomId;
        String studentId;
        ApprovedStudentEntity approved2;
        PendingRegistrationEntity pending3;
        String classroomId2;
        String studentId2;
        String studentName;
        ApprovedStudentEntity approved3;
        PendingRegistrationEntity pending4;
        String todayDateStr;
        Object attendanceForClassroomSync;
        Collection arrayList;
        Iterator it;
        Collection arrayList2;
        String todayDateStr2;
        ApprovedStudentEntity approved4;
        PendingRegistrationEntity pending5;
        Iterable allLogs;
        List previousDates;
        Iterator it2;
        String date;
        Iterable iterable;
        Iterator it3;
        Object next;
        AttendanceEntity existingOnDate;
        long jCurrentTimeMillis;
        AttendanceEntity record;
        AttendanceDao attendanceDao;
        CalisRepository calisRepository;
        Iterable iterable2;
        CalisRepository calisRepository2 = this;
        if (continuation instanceof C07591) {
            c07591 = (C07591) continuation;
            if ((c07591.label & Integer.MIN_VALUE) != 0) {
                c07591.label -= Integer.MIN_VALUE;
            } else {
                c07591 = calisRepository2.new C07591(continuation);
            }
        } else {
            c07591 = calisRepository2.new C07591(continuation);
        }
        C07591 c07592 = c07591;
        Object $result = c07592.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07592.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ApprovedStudentEntity approved5 = new ApprovedStudentEntity(0L, pending.getClassroomId(), pending.getStudentId(), pending.getStudentName(), pending.getSex(), pending.getLrn(), pending.getBirth(), 0L, 129, null);
                ApprovedStudentDao approvedStudentDao = calisRepository2.approvedStudentDao;
                pending2 = pending;
                c07592.L$0 = pending2;
                c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved5);
                c07592.label = 1;
                if (approvedStudentDao.insertApprovedStudent(approved5, c07592) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                approved = approved5;
                pendingRegistrationDao = calisRepository2.pendingRegistrationDao;
                classroomId = pending2.getClassroomId();
                studentId = pending2.getStudentId();
                c07592.L$0 = pending2;
                c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved);
                c07592.label = 2;
                if (pendingRegistrationDao.deletePendingRegistration(classroomId, studentId, c07592) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                approved2 = approved;
                pending3 = pending2;
                classroomId2 = pending3.getClassroomId();
                studentId2 = pending3.getStudentId();
                studentName = pending3.getStudentName();
                c07592.L$0 = pending3;
                c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved2);
                c07592.label = 3;
                if (recordAttendance$default(calisRepository2, classroomId2, studentId2, studentName, "PRESENT", 0L, c07592, 16, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                approved3 = approved2;
                pending4 = pending3;
                todayDateStr = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                AttendanceDao attendanceDao2 = calisRepository2.attendanceDao;
                String classroomId3 = pending4.getClassroomId();
                c07592.L$0 = pending4;
                c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved3);
                c07592.L$2 = todayDateStr;
                c07592.label = 4;
                attendanceForClassroomSync = attendanceDao2.getAttendanceForClassroomSync(classroomId3, c07592);
                if (attendanceForClassroomSync == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Iterable allLogs2 = (List) attendanceForClassroomSync;
                Iterable iterable3 = allLogs2;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                it = iterable3.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AttendanceEntity) it.next()).getDate());
                }
                Iterable iterableDistinct = CollectionsKt.distinct((List) arrayList);
                arrayList2 = new ArrayList();
                for (Object obj : iterableDistinct) {
                    if (!Intrinsics.areEqual((String) obj, todayDateStr)) {
                        arrayList2.add(obj);
                    }
                }
                List previousDates2 = (List) arrayList2;
                todayDateStr2 = todayDateStr;
                approved4 = approved3;
                pending5 = pending4;
                allLogs = allLogs2;
                previousDates = previousDates2;
                it2 = previousDates2.iterator();
                calisRepository2 = this;
                while (it2.hasNext()) {
                    date = (String) it2.next();
                    iterable = allLogs;
                    it3 = iterable.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            next = it3.next();
                            iterable2 = iterable;
                            if (Intrinsics.areEqual(((AttendanceEntity) next).getDate(), date)) {
                                iterable = iterable2;
                            }
                        } else {
                            next = null;
                        }
                    }
                    existingOnDate = (AttendanceEntity) next;
                    if (existingOnDate != null) {
                        jCurrentTimeMillis = existingOnDate.getTimestamp();
                    } else {
                        jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                    }
                    long timestamp = jCurrentTimeMillis;
                    record = new AttendanceEntity(0L, pending5.getClassroomId(), pending5.getStudentId(), pending5.getStudentName(), date, "UNMARKED", timestamp, 1, null);
                    attendanceDao = calisRepository2.attendanceDao;
                    c07592.L$0 = pending5;
                    calisRepository = calisRepository2;
                    c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved4);
                    c07592.L$2 = SpillingKt.nullOutSpilledVariable(todayDateStr2);
                    c07592.L$3 = allLogs;
                    c07592.L$4 = SpillingKt.nullOutSpilledVariable(previousDates);
                    c07592.L$5 = it2;
                    c07592.L$6 = SpillingKt.nullOutSpilledVariable(date);
                    c07592.L$7 = SpillingKt.nullOutSpilledVariable(existingOnDate);
                    c07592.L$8 = SpillingKt.nullOutSpilledVariable(record);
                    c07592.J$0 = timestamp;
                    c07592.label = 5;
                    if (attendanceDao.insertOrUpdateAttendance(record, c07592) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    calisRepository2 = calisRepository;
                }
                return Unit.INSTANCE;
            case 1:
                approved = (ApprovedStudentEntity) c07592.L$1;
                pending2 = (PendingRegistrationEntity) c07592.L$0;
                ResultKt.throwOnFailure($result);
                pendingRegistrationDao = calisRepository2.pendingRegistrationDao;
                classroomId = pending2.getClassroomId();
                studentId = pending2.getStudentId();
                c07592.L$0 = pending2;
                c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved);
                c07592.label = 2;
                if (pendingRegistrationDao.deletePendingRegistration(classroomId, studentId, c07592) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                approved2 = approved;
                pending3 = pending2;
                classroomId2 = pending3.getClassroomId();
                studentId2 = pending3.getStudentId();
                studentName = pending3.getStudentName();
                c07592.L$0 = pending3;
                c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved2);
                c07592.label = 3;
                if (recordAttendance$default(calisRepository2, classroomId2, studentId2, studentName, "PRESENT", 0L, c07592, 16, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                approved3 = approved2;
                pending4 = pending3;
                todayDateStr = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                AttendanceDao attendanceDao3 = calisRepository2.attendanceDao;
                String classroomId4 = pending4.getClassroomId();
                c07592.L$0 = pending4;
                c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved3);
                c07592.L$2 = todayDateStr;
                c07592.label = 4;
                attendanceForClassroomSync = attendanceDao3.getAttendanceForClassroomSync(classroomId4, c07592);
                if (attendanceForClassroomSync == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Iterable allLogs3 = (List) attendanceForClassroomSync;
                Iterable iterable4 = allLogs3;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable4, 10));
                it = iterable4.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AttendanceEntity) it.next()).getDate());
                }
                Iterable iterableDistinct2 = CollectionsKt.distinct((List) arrayList);
                arrayList2 = new ArrayList();
                while (r13.hasNext()) {
                    if (!Intrinsics.areEqual((String) obj, todayDateStr)) {
                        arrayList2.add(obj);
                    }
                }
                List previousDates3 = (List) arrayList2;
                todayDateStr2 = todayDateStr;
                approved4 = approved3;
                pending5 = pending4;
                allLogs = allLogs3;
                previousDates = previousDates3;
                it2 = previousDates3.iterator();
                calisRepository2 = this;
                while (it2.hasNext()) {
                    date = (String) it2.next();
                    iterable = allLogs;
                    it3 = iterable.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            next = it3.next();
                            iterable2 = iterable;
                            if (Intrinsics.areEqual(((AttendanceEntity) next).getDate(), date)) {
                                iterable = iterable2;
                            }
                        } else {
                            next = null;
                        }
                    }
                    existingOnDate = (AttendanceEntity) next;
                    if (existingOnDate != null) {
                        jCurrentTimeMillis = existingOnDate.getTimestamp();
                    } else {
                        jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                    }
                    long timestamp2 = jCurrentTimeMillis;
                    record = new AttendanceEntity(0L, pending5.getClassroomId(), pending5.getStudentId(), pending5.getStudentName(), date, "UNMARKED", timestamp2, 1, null);
                    attendanceDao = calisRepository2.attendanceDao;
                    c07592.L$0 = pending5;
                    calisRepository = calisRepository2;
                    c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved4);
                    c07592.L$2 = SpillingKt.nullOutSpilledVariable(todayDateStr2);
                    c07592.L$3 = allLogs;
                    c07592.L$4 = SpillingKt.nullOutSpilledVariable(previousDates);
                    c07592.L$5 = it2;
                    c07592.L$6 = SpillingKt.nullOutSpilledVariable(date);
                    c07592.L$7 = SpillingKt.nullOutSpilledVariable(existingOnDate);
                    c07592.L$8 = SpillingKt.nullOutSpilledVariable(record);
                    c07592.J$0 = timestamp2;
                    c07592.label = 5;
                    if (attendanceDao.insertOrUpdateAttendance(record, c07592) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    calisRepository2 = calisRepository;
                }
                return Unit.INSTANCE;
            case 2:
                ApprovedStudentEntity approved6 = (ApprovedStudentEntity) c07592.L$1;
                PendingRegistrationEntity pending6 = (PendingRegistrationEntity) c07592.L$0;
                ResultKt.throwOnFailure($result);
                approved2 = approved6;
                pending3 = pending6;
                classroomId2 = pending3.getClassroomId();
                studentId2 = pending3.getStudentId();
                studentName = pending3.getStudentName();
                c07592.L$0 = pending3;
                c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved2);
                c07592.label = 3;
                if (recordAttendance$default(calisRepository2, classroomId2, studentId2, studentName, "PRESENT", 0L, c07592, 16, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                approved3 = approved2;
                pending4 = pending3;
                todayDateStr = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                AttendanceDao attendanceDao4 = calisRepository2.attendanceDao;
                String classroomId5 = pending4.getClassroomId();
                c07592.L$0 = pending4;
                c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved3);
                c07592.L$2 = todayDateStr;
                c07592.label = 4;
                attendanceForClassroomSync = attendanceDao4.getAttendanceForClassroomSync(classroomId5, c07592);
                if (attendanceForClassroomSync == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Iterable allLogs4 = (List) attendanceForClassroomSync;
                Iterable iterable5 = allLogs4;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable5, 10));
                it = iterable5.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AttendanceEntity) it.next()).getDate());
                }
                Iterable iterableDistinct3 = CollectionsKt.distinct((List) arrayList);
                arrayList2 = new ArrayList();
                while (r13.hasNext()) {
                    if (!Intrinsics.areEqual((String) obj, todayDateStr)) {
                        arrayList2.add(obj);
                    }
                }
                List previousDates4 = (List) arrayList2;
                todayDateStr2 = todayDateStr;
                approved4 = approved3;
                pending5 = pending4;
                allLogs = allLogs4;
                previousDates = previousDates4;
                it2 = previousDates4.iterator();
                calisRepository2 = this;
                while (it2.hasNext()) {
                    date = (String) it2.next();
                    iterable = allLogs;
                    it3 = iterable.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            next = it3.next();
                            iterable2 = iterable;
                            if (Intrinsics.areEqual(((AttendanceEntity) next).getDate(), date)) {
                                iterable = iterable2;
                            }
                        } else {
                            next = null;
                        }
                    }
                    existingOnDate = (AttendanceEntity) next;
                    if (existingOnDate != null) {
                        jCurrentTimeMillis = existingOnDate.getTimestamp();
                    } else {
                        jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                    }
                    long timestamp3 = jCurrentTimeMillis;
                    record = new AttendanceEntity(0L, pending5.getClassroomId(), pending5.getStudentId(), pending5.getStudentName(), date, "UNMARKED", timestamp3, 1, null);
                    attendanceDao = calisRepository2.attendanceDao;
                    c07592.L$0 = pending5;
                    calisRepository = calisRepository2;
                    c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved4);
                    c07592.L$2 = SpillingKt.nullOutSpilledVariable(todayDateStr2);
                    c07592.L$3 = allLogs;
                    c07592.L$4 = SpillingKt.nullOutSpilledVariable(previousDates);
                    c07592.L$5 = it2;
                    c07592.L$6 = SpillingKt.nullOutSpilledVariable(date);
                    c07592.L$7 = SpillingKt.nullOutSpilledVariable(existingOnDate);
                    c07592.L$8 = SpillingKt.nullOutSpilledVariable(record);
                    c07592.J$0 = timestamp3;
                    c07592.label = 5;
                    if (attendanceDao.insertOrUpdateAttendance(record, c07592) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    calisRepository2 = calisRepository;
                }
                return Unit.INSTANCE;
            case 3:
                ApprovedStudentEntity approved7 = (ApprovedStudentEntity) c07592.L$1;
                PendingRegistrationEntity pending7 = (PendingRegistrationEntity) c07592.L$0;
                ResultKt.throwOnFailure($result);
                pending4 = pending7;
                approved3 = approved7;
                todayDateStr = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                AttendanceDao attendanceDao5 = calisRepository2.attendanceDao;
                String classroomId6 = pending4.getClassroomId();
                c07592.L$0 = pending4;
                c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved3);
                c07592.L$2 = todayDateStr;
                c07592.label = 4;
                attendanceForClassroomSync = attendanceDao5.getAttendanceForClassroomSync(classroomId6, c07592);
                if (attendanceForClassroomSync == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Iterable allLogs5 = (List) attendanceForClassroomSync;
                Iterable iterable6 = allLogs5;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable6, 10));
                it = iterable6.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AttendanceEntity) it.next()).getDate());
                }
                Iterable iterableDistinct4 = CollectionsKt.distinct((List) arrayList);
                arrayList2 = new ArrayList();
                while (r13.hasNext()) {
                    if (!Intrinsics.areEqual((String) obj, todayDateStr)) {
                        arrayList2.add(obj);
                    }
                }
                List previousDates5 = (List) arrayList2;
                todayDateStr2 = todayDateStr;
                approved4 = approved3;
                pending5 = pending4;
                allLogs = allLogs5;
                previousDates = previousDates5;
                it2 = previousDates5.iterator();
                calisRepository2 = this;
                while (it2.hasNext()) {
                    date = (String) it2.next();
                    iterable = allLogs;
                    it3 = iterable.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            next = it3.next();
                            iterable2 = iterable;
                            if (Intrinsics.areEqual(((AttendanceEntity) next).getDate(), date)) {
                                iterable = iterable2;
                            }
                        } else {
                            next = null;
                        }
                    }
                    existingOnDate = (AttendanceEntity) next;
                    if (existingOnDate != null) {
                        jCurrentTimeMillis = existingOnDate.getTimestamp();
                    } else {
                        jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                    }
                    long timestamp4 = jCurrentTimeMillis;
                    record = new AttendanceEntity(0L, pending5.getClassroomId(), pending5.getStudentId(), pending5.getStudentName(), date, "UNMARKED", timestamp4, 1, null);
                    attendanceDao = calisRepository2.attendanceDao;
                    c07592.L$0 = pending5;
                    calisRepository = calisRepository2;
                    c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved4);
                    c07592.L$2 = SpillingKt.nullOutSpilledVariable(todayDateStr2);
                    c07592.L$3 = allLogs;
                    c07592.L$4 = SpillingKt.nullOutSpilledVariable(previousDates);
                    c07592.L$5 = it2;
                    c07592.L$6 = SpillingKt.nullOutSpilledVariable(date);
                    c07592.L$7 = SpillingKt.nullOutSpilledVariable(existingOnDate);
                    c07592.L$8 = SpillingKt.nullOutSpilledVariable(record);
                    c07592.J$0 = timestamp4;
                    c07592.label = 5;
                    if (attendanceDao.insertOrUpdateAttendance(record, c07592) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    calisRepository2 = calisRepository;
                }
                return Unit.INSTANCE;
            case 4:
                todayDateStr = (String) c07592.L$2;
                approved3 = (ApprovedStudentEntity) c07592.L$1;
                pending4 = (PendingRegistrationEntity) c07592.L$0;
                ResultKt.throwOnFailure($result);
                attendanceForClassroomSync = $result;
                Iterable allLogs6 = (List) attendanceForClassroomSync;
                Iterable iterable7 = allLogs6;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable7, 10));
                it = iterable7.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AttendanceEntity) it.next()).getDate());
                }
                Iterable iterableDistinct5 = CollectionsKt.distinct((List) arrayList);
                arrayList2 = new ArrayList();
                while (r13.hasNext()) {
                    if (!Intrinsics.areEqual((String) obj, todayDateStr)) {
                        arrayList2.add(obj);
                    }
                }
                List previousDates6 = (List) arrayList2;
                todayDateStr2 = todayDateStr;
                approved4 = approved3;
                pending5 = pending4;
                allLogs = allLogs6;
                previousDates = previousDates6;
                it2 = previousDates6.iterator();
                calisRepository2 = this;
                while (it2.hasNext()) {
                    date = (String) it2.next();
                    iterable = allLogs;
                    it3 = iterable.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            next = it3.next();
                            iterable2 = iterable;
                            if (Intrinsics.areEqual(((AttendanceEntity) next).getDate(), date)) {
                                iterable = iterable2;
                            }
                        } else {
                            next = null;
                        }
                    }
                    existingOnDate = (AttendanceEntity) next;
                    if (existingOnDate != null) {
                        jCurrentTimeMillis = existingOnDate.getTimestamp();
                    } else {
                        jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                    }
                    long timestamp5 = jCurrentTimeMillis;
                    record = new AttendanceEntity(0L, pending5.getClassroomId(), pending5.getStudentId(), pending5.getStudentName(), date, "UNMARKED", timestamp5, 1, null);
                    attendanceDao = calisRepository2.attendanceDao;
                    c07592.L$0 = pending5;
                    calisRepository = calisRepository2;
                    c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved4);
                    c07592.L$2 = SpillingKt.nullOutSpilledVariable(todayDateStr2);
                    c07592.L$3 = allLogs;
                    c07592.L$4 = SpillingKt.nullOutSpilledVariable(previousDates);
                    c07592.L$5 = it2;
                    c07592.L$6 = SpillingKt.nullOutSpilledVariable(date);
                    c07592.L$7 = SpillingKt.nullOutSpilledVariable(existingOnDate);
                    c07592.L$8 = SpillingKt.nullOutSpilledVariable(record);
                    c07592.J$0 = timestamp5;
                    c07592.label = 5;
                    if (attendanceDao.insertOrUpdateAttendance(record, c07592) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    calisRepository2 = calisRepository;
                }
                return Unit.INSTANCE;
            case 5:
                long j = c07592.J$0;
                it2 = (Iterator) c07592.L$5;
                previousDates = (List) c07592.L$4;
                allLogs = (List) c07592.L$3;
                todayDateStr2 = (String) c07592.L$2;
                approved4 = (ApprovedStudentEntity) c07592.L$1;
                pending5 = (PendingRegistrationEntity) c07592.L$0;
                ResultKt.throwOnFailure($result);
                while (it2.hasNext()) {
                    date = (String) it2.next();
                    iterable = allLogs;
                    it3 = iterable.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            next = it3.next();
                            iterable2 = iterable;
                            if (Intrinsics.areEqual(((AttendanceEntity) next).getDate(), date)) {
                                iterable = iterable2;
                            }
                        } else {
                            next = null;
                        }
                    }
                    existingOnDate = (AttendanceEntity) next;
                    if (existingOnDate != null) {
                        jCurrentTimeMillis = existingOnDate.getTimestamp();
                    } else {
                        jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                    }
                    long timestamp6 = jCurrentTimeMillis;
                    record = new AttendanceEntity(0L, pending5.getClassroomId(), pending5.getStudentId(), pending5.getStudentName(), date, "UNMARKED", timestamp6, 1, null);
                    attendanceDao = calisRepository2.attendanceDao;
                    c07592.L$0 = pending5;
                    calisRepository = calisRepository2;
                    c07592.L$1 = SpillingKt.nullOutSpilledVariable(approved4);
                    c07592.L$2 = SpillingKt.nullOutSpilledVariable(todayDateStr2);
                    c07592.L$3 = allLogs;
                    c07592.L$4 = SpillingKt.nullOutSpilledVariable(previousDates);
                    c07592.L$5 = it2;
                    c07592.L$6 = SpillingKt.nullOutSpilledVariable(date);
                    c07592.L$7 = SpillingKt.nullOutSpilledVariable(existingOnDate);
                    c07592.L$8 = SpillingKt.nullOutSpilledVariable(record);
                    c07592.J$0 = timestamp6;
                    c07592.label = 5;
                    if (attendanceDao.insertOrUpdateAttendance(record, c07592) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    calisRepository2 = calisRepository;
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object rejectStudentRegistration(PendingRegistrationEntity pending, Continuation<? super Unit> continuation) {
        Object objDeletePendingRegistration = this.pendingRegistrationDao.deletePendingRegistration(pending.getClassroomId(), pending.getStudentId(), continuation);
        return objDeletePendingRegistration == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeletePendingRegistration : Unit.INSTANCE;
    }

    public final Flow<List<ApprovedStudentEntity>> getApprovedStudents(String classroomId) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        return this.approvedStudentDao.getApprovedStudents(classroomId);
    }

    public final Object getApprovedStudent(String classroomId, String studentId, Continuation<? super ApprovedStudentEntity> continuation) {
        return this.approvedStudentDao.getApprovedStudent(classroomId, studentId, continuation);
    }

    public final Object getApprovedStudentGlobally(String studentId, Continuation<? super ApprovedStudentEntity> continuation) {
        return this.approvedStudentDao.getApprovedStudentGlobally(studentId, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object isStudentApproved(String classroomId, String studentId, Continuation<? super Boolean> continuation) {
        C07661 c07661;
        Object approvedStudent;
        if (continuation instanceof C07661) {
            c07661 = (C07661) continuation;
            if ((c07661.label & Integer.MIN_VALUE) != 0) {
                c07661.label -= Integer.MIN_VALUE;
            } else {
                c07661 = new C07661(continuation);
            }
        } else {
            c07661 = new C07661(continuation);
        }
        Object $result = c07661.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07661.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ApprovedStudentDao approvedStudentDao = this.approvedStudentDao;
                c07661.L$0 = SpillingKt.nullOutSpilledVariable(classroomId);
                c07661.L$1 = SpillingKt.nullOutSpilledVariable(studentId);
                c07661.label = 1;
                approvedStudent = approvedStudentDao.getApprovedStudent(classroomId, studentId, c07661);
                if (approvedStudent == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                approvedStudent = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Boxing.boxBoolean(approvedStudent != null);
    }

    public final Object removeApprovedStudent(String classroomId, String studentId, Continuation<? super Unit> continuation) {
        Object objDeleteApprovedStudent = this.approvedStudentDao.deleteApprovedStudent(classroomId, studentId, continuation);
        return objDeleteApprovedStudent == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteApprovedStudent : Unit.INSTANCE;
    }

    public final Flow<List<AttendanceEntity>> getAttendanceForClassroom(String classroomId) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        return this.attendanceDao.getAttendanceForClassroom(classroomId);
    }

    public final Flow<List<AttendanceEntity>> getAttendanceForDate(String classroomId, String date) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        Intrinsics.checkNotNullParameter(date, "date");
        return this.attendanceDao.getAttendanceForDate(classroomId, date);
    }

    public final Object getAttendanceForDateSync(String classroomId, String date, Continuation<? super List<AttendanceEntity>> continuation) {
        return this.attendanceDao.getAttendanceForDateSync(classroomId, date, continuation);
    }

    public final Flow<List<AttendanceEntity>> getAttendanceForStudent(String studentId) {
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        return this.attendanceDao.getAttendanceForStudent(studentId);
    }

    public final Object getAttendanceRecord(String classroomId, String studentId, String dateStr, Continuation<? super AttendanceEntity> continuation) {
        return this.attendanceDao.getAttendanceRecord(classroomId, studentId, dateStr, continuation);
    }

    public static /* synthetic */ Object recordAttendance$default(CalisRepository calisRepository, String str, String str2, String str3, String str4, long j, Continuation continuation, int i, Object obj) {
        long jCurrentTimeMillis;
        if ((i & 16) == 0) {
            jCurrentTimeMillis = j;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        }
        return calisRepository.recordAttendance(str, str2, str3, str4, jCurrentTimeMillis, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0118  */
    /* JADX WARN: Code duplicated, block: B:23:0x0132  */
    /* JADX WARN: Code duplicated, block: B:24:0x0135  */
    /* JADX WARN: Code duplicated, block: B:28:0x0168 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:29:0x0169  */
    /* JADX WARN: Code duplicated, block: B:32:0x017f  */
    /* JADX WARN: Code duplicated, block: B:33:0x0184  */
    /* JADX WARN: Code duplicated, block: B:36:0x01e0 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:37:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:7:0x001e  */
    public final Object recordAttendance(String classroomId, String studentId, String studentName, String status, long timestamp, Continuation<? super Unit> continuation) {
        C07671 c07671;
        SimpleDateFormat dateFormat;
        String studentName2;
        String finalStatus;
        String dateStr;
        Object obj;
        String status2;
        ApprovedStudentEntity student;
        Object obj2;
        String finalStatus2;
        Object obj3;
        String finalStatus3;
        String dateStr2;
        String studentName3;
        ApprovedStudentEntity student2;
        SimpleDateFormat dateFormat2;
        String studentName4;
        String classroomId2;
        String studentId2;
        long timestamp2;
        String approvedDateStr;
        AttendanceEntity existing;
        AttendanceEntity record;
        long id;
        AttendanceDao attendanceDao;
        String classroomId3 = classroomId;
        String studentId3 = studentId;
        long timestamp3 = timestamp;
        if (continuation instanceof C07671) {
            c07671 = (C07671) continuation;
            if ((c07671.label & Integer.MIN_VALUE) != 0) {
                c07671.label -= Integer.MIN_VALUE;
            } else {
                c07671 = new C07671(continuation);
            }
        } else {
            c07671 = new C07671(continuation);
        }
        Object $result = c07671.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07671.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String dateStr3 = dateFormat.format(new Date(1000 * timestamp3));
                ApprovedStudentDao approvedStudentDao = this.approvedStudentDao;
                c07671.L$0 = classroomId3;
                c07671.L$1 = studentId3;
                studentName2 = studentName;
                c07671.L$2 = studentName2;
                c07671.L$3 = SpillingKt.nullOutSpilledVariable(status);
                c07671.L$4 = dateFormat;
                c07671.L$5 = dateStr3;
                c07671.L$6 = status;
                c07671.J$0 = timestamp3;
                c07671.label = 1;
                Object approvedStudent = approvedStudentDao.getApprovedStudent(classroomId3, studentId3, c07671);
                if (approvedStudent == coroutine_suspended) {
                    return coroutine_suspended;
                }
                finalStatus = dateStr3;
                dateStr = status;
                obj = approvedStudent;
                status2 = status;
                student = (ApprovedStudentEntity) obj;
                if (student != null) {
                    obj2 = coroutine_suspended;
                    approvedDateStr = dateFormat.format(new Date(student.getApprovedAt()));
                    Intrinsics.checkNotNull(approvedDateStr);
                    if (finalStatus.compareTo(approvedDateStr) < 0) {
                        finalStatus2 = "UNMARKED";
                    }
                    AttendanceDao attendanceDao2 = this.attendanceDao;
                    Intrinsics.checkNotNull(finalStatus);
                    c07671.L$0 = classroomId3;
                    c07671.L$1 = studentId3;
                    c07671.L$2 = studentName2;
                    c07671.L$3 = SpillingKt.nullOutSpilledVariable(status2);
                    c07671.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat);
                    c07671.L$5 = finalStatus;
                    c07671.L$6 = finalStatus2;
                    c07671.L$7 = SpillingKt.nullOutSpilledVariable(student);
                    c07671.J$0 = timestamp3;
                    c07671.label = 2;
                    $result = attendanceDao2.getAttendanceRecord(classroomId3, studentId3, finalStatus, c07671);
                    obj3 = obj2;
                    if ($result == obj3) {
                        return obj3;
                    }
                    finalStatus3 = finalStatus2;
                    dateStr2 = finalStatus;
                    studentName3 = studentName2;
                    student2 = student;
                    dateFormat2 = dateFormat;
                    studentName4 = status2;
                    classroomId2 = classroomId3;
                    studentId2 = studentId3;
                    timestamp2 = timestamp3;
                    existing = (AttendanceEntity) $result;
                    if (existing != null) {
                        id = existing.getId();
                    } else {
                        id = 0;
                    }
                    Intrinsics.checkNotNull(dateStr2);
                    record = new AttendanceEntity(id, classroomId2, studentId2, studentName3, dateStr2, finalStatus3, timestamp2);
                    attendanceDao = this.attendanceDao;
                    c07671.L$0 = SpillingKt.nullOutSpilledVariable(classroomId2);
                    c07671.L$1 = SpillingKt.nullOutSpilledVariable(studentId2);
                    c07671.L$2 = SpillingKt.nullOutSpilledVariable(studentName3);
                    c07671.L$3 = SpillingKt.nullOutSpilledVariable(studentName4);
                    c07671.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat2);
                    c07671.L$5 = SpillingKt.nullOutSpilledVariable(dateStr2);
                    c07671.L$6 = SpillingKt.nullOutSpilledVariable(finalStatus3);
                    c07671.L$7 = SpillingKt.nullOutSpilledVariable(student2);
                    c07671.L$8 = SpillingKt.nullOutSpilledVariable(existing);
                    c07671.L$9 = SpillingKt.nullOutSpilledVariable(record);
                    c07671.J$0 = timestamp2;
                    c07671.label = 3;
                    if (attendanceDao.insertOrUpdateAttendance(record, c07671) == obj3) {
                        return obj3;
                    }
                    return Unit.INSTANCE;
                }
                obj2 = coroutine_suspended;
                finalStatus2 = dateStr;
                AttendanceDao attendanceDao3 = this.attendanceDao;
                Intrinsics.checkNotNull(finalStatus);
                c07671.L$0 = classroomId3;
                c07671.L$1 = studentId3;
                c07671.L$2 = studentName2;
                c07671.L$3 = SpillingKt.nullOutSpilledVariable(status2);
                c07671.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat);
                c07671.L$5 = finalStatus;
                c07671.L$6 = finalStatus2;
                c07671.L$7 = SpillingKt.nullOutSpilledVariable(student);
                c07671.J$0 = timestamp3;
                c07671.label = 2;
                $result = attendanceDao3.getAttendanceRecord(classroomId3, studentId3, finalStatus, c07671);
                obj3 = obj2;
                if ($result == obj3) {
                    return obj3;
                }
                finalStatus3 = finalStatus2;
                dateStr2 = finalStatus;
                studentName3 = studentName2;
                student2 = student;
                dateFormat2 = dateFormat;
                studentName4 = status2;
                classroomId2 = classroomId3;
                studentId2 = studentId3;
                timestamp2 = timestamp3;
                existing = (AttendanceEntity) $result;
                if (existing != null) {
                    id = existing.getId();
                } else {
                    id = 0;
                }
                Intrinsics.checkNotNull(dateStr2);
                record = new AttendanceEntity(id, classroomId2, studentId2, studentName3, dateStr2, finalStatus3, timestamp2);
                attendanceDao = this.attendanceDao;
                c07671.L$0 = SpillingKt.nullOutSpilledVariable(classroomId2);
                c07671.L$1 = SpillingKt.nullOutSpilledVariable(studentId2);
                c07671.L$2 = SpillingKt.nullOutSpilledVariable(studentName3);
                c07671.L$3 = SpillingKt.nullOutSpilledVariable(studentName4);
                c07671.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat2);
                c07671.L$5 = SpillingKt.nullOutSpilledVariable(dateStr2);
                c07671.L$6 = SpillingKt.nullOutSpilledVariable(finalStatus3);
                c07671.L$7 = SpillingKt.nullOutSpilledVariable(student2);
                c07671.L$8 = SpillingKt.nullOutSpilledVariable(existing);
                c07671.L$9 = SpillingKt.nullOutSpilledVariable(record);
                c07671.J$0 = timestamp2;
                c07671.label = 3;
                if (attendanceDao.insertOrUpdateAttendance(record, c07671) == obj3) {
                    return obj3;
                }
                return Unit.INSTANCE;
            case 1:
                timestamp3 = c07671.J$0;
                dateStr = (String) c07671.L$6;
                finalStatus = (String) c07671.L$5;
                dateFormat = (SimpleDateFormat) c07671.L$4;
                status2 = (String) c07671.L$3;
                studentName2 = (String) c07671.L$2;
                studentId3 = (String) c07671.L$1;
                classroomId3 = (String) c07671.L$0;
                ResultKt.throwOnFailure($result);
                obj = $result;
                student = (ApprovedStudentEntity) obj;
                if (student != null) {
                    obj2 = coroutine_suspended;
                    approvedDateStr = dateFormat.format(new Date(student.getApprovedAt()));
                    Intrinsics.checkNotNull(approvedDateStr);
                    if (finalStatus.compareTo(approvedDateStr) < 0) {
                        finalStatus2 = "UNMARKED";
                    }
                    AttendanceDao attendanceDao4 = this.attendanceDao;
                    Intrinsics.checkNotNull(finalStatus);
                    c07671.L$0 = classroomId3;
                    c07671.L$1 = studentId3;
                    c07671.L$2 = studentName2;
                    c07671.L$3 = SpillingKt.nullOutSpilledVariable(status2);
                    c07671.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat);
                    c07671.L$5 = finalStatus;
                    c07671.L$6 = finalStatus2;
                    c07671.L$7 = SpillingKt.nullOutSpilledVariable(student);
                    c07671.J$0 = timestamp3;
                    c07671.label = 2;
                    $result = attendanceDao4.getAttendanceRecord(classroomId3, studentId3, finalStatus, c07671);
                    obj3 = obj2;
                    if ($result == obj3) {
                        return obj3;
                    }
                    finalStatus3 = finalStatus2;
                    dateStr2 = finalStatus;
                    studentName3 = studentName2;
                    student2 = student;
                    dateFormat2 = dateFormat;
                    studentName4 = status2;
                    classroomId2 = classroomId3;
                    studentId2 = studentId3;
                    timestamp2 = timestamp3;
                    existing = (AttendanceEntity) $result;
                    if (existing != null) {
                        id = existing.getId();
                    } else {
                        id = 0;
                    }
                    Intrinsics.checkNotNull(dateStr2);
                    record = new AttendanceEntity(id, classroomId2, studentId2, studentName3, dateStr2, finalStatus3, timestamp2);
                    attendanceDao = this.attendanceDao;
                    c07671.L$0 = SpillingKt.nullOutSpilledVariable(classroomId2);
                    c07671.L$1 = SpillingKt.nullOutSpilledVariable(studentId2);
                    c07671.L$2 = SpillingKt.nullOutSpilledVariable(studentName3);
                    c07671.L$3 = SpillingKt.nullOutSpilledVariable(studentName4);
                    c07671.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat2);
                    c07671.L$5 = SpillingKt.nullOutSpilledVariable(dateStr2);
                    c07671.L$6 = SpillingKt.nullOutSpilledVariable(finalStatus3);
                    c07671.L$7 = SpillingKt.nullOutSpilledVariable(student2);
                    c07671.L$8 = SpillingKt.nullOutSpilledVariable(existing);
                    c07671.L$9 = SpillingKt.nullOutSpilledVariable(record);
                    c07671.J$0 = timestamp2;
                    c07671.label = 3;
                    if (attendanceDao.insertOrUpdateAttendance(record, c07671) == obj3) {
                        return obj3;
                    }
                    return Unit.INSTANCE;
                }
                obj2 = coroutine_suspended;
                finalStatus2 = dateStr;
                AttendanceDao attendanceDao5 = this.attendanceDao;
                Intrinsics.checkNotNull(finalStatus);
                c07671.L$0 = classroomId3;
                c07671.L$1 = studentId3;
                c07671.L$2 = studentName2;
                c07671.L$3 = SpillingKt.nullOutSpilledVariable(status2);
                c07671.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat);
                c07671.L$5 = finalStatus;
                c07671.L$6 = finalStatus2;
                c07671.L$7 = SpillingKt.nullOutSpilledVariable(student);
                c07671.J$0 = timestamp3;
                c07671.label = 2;
                $result = attendanceDao5.getAttendanceRecord(classroomId3, studentId3, finalStatus, c07671);
                obj3 = obj2;
                if ($result == obj3) {
                    return obj3;
                }
                finalStatus3 = finalStatus2;
                dateStr2 = finalStatus;
                studentName3 = studentName2;
                student2 = student;
                dateFormat2 = dateFormat;
                studentName4 = status2;
                classroomId2 = classroomId3;
                studentId2 = studentId3;
                timestamp2 = timestamp3;
                existing = (AttendanceEntity) $result;
                if (existing != null) {
                    id = existing.getId();
                } else {
                    id = 0;
                }
                Intrinsics.checkNotNull(dateStr2);
                record = new AttendanceEntity(id, classroomId2, studentId2, studentName3, dateStr2, finalStatus3, timestamp2);
                attendanceDao = this.attendanceDao;
                c07671.L$0 = SpillingKt.nullOutSpilledVariable(classroomId2);
                c07671.L$1 = SpillingKt.nullOutSpilledVariable(studentId2);
                c07671.L$2 = SpillingKt.nullOutSpilledVariable(studentName3);
                c07671.L$3 = SpillingKt.nullOutSpilledVariable(studentName4);
                c07671.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat2);
                c07671.L$5 = SpillingKt.nullOutSpilledVariable(dateStr2);
                c07671.L$6 = SpillingKt.nullOutSpilledVariable(finalStatus3);
                c07671.L$7 = SpillingKt.nullOutSpilledVariable(student2);
                c07671.L$8 = SpillingKt.nullOutSpilledVariable(existing);
                c07671.L$9 = SpillingKt.nullOutSpilledVariable(record);
                c07671.J$0 = timestamp2;
                c07671.label = 3;
                if (attendanceDao.insertOrUpdateAttendance(record, c07671) == obj3) {
                    return obj3;
                }
                return Unit.INSTANCE;
            case 2:
                long timestamp4 = c07671.J$0;
                ApprovedStudentEntity student3 = (ApprovedStudentEntity) c07671.L$7;
                String finalStatus4 = (String) c07671.L$6;
                String dateStr4 = (String) c07671.L$5;
                SimpleDateFormat dateFormat3 = (SimpleDateFormat) c07671.L$4;
                String status3 = (String) c07671.L$3;
                String studentName5 = (String) c07671.L$2;
                String studentId4 = (String) c07671.L$1;
                String classroomId4 = (String) c07671.L$0;
                ResultKt.throwOnFailure($result);
                finalStatus3 = finalStatus4;
                dateStr2 = dateStr4;
                studentName3 = studentName5;
                student2 = student3;
                studentName4 = status3;
                obj3 = coroutine_suspended;
                dateFormat2 = dateFormat3;
                classroomId2 = classroomId4;
                studentId2 = studentId4;
                timestamp2 = timestamp4;
                existing = (AttendanceEntity) $result;
                if (existing != null) {
                    id = existing.getId();
                } else {
                    id = 0;
                }
                Intrinsics.checkNotNull(dateStr2);
                record = new AttendanceEntity(id, classroomId2, studentId2, studentName3, dateStr2, finalStatus3, timestamp2);
                attendanceDao = this.attendanceDao;
                c07671.L$0 = SpillingKt.nullOutSpilledVariable(classroomId2);
                c07671.L$1 = SpillingKt.nullOutSpilledVariable(studentId2);
                c07671.L$2 = SpillingKt.nullOutSpilledVariable(studentName3);
                c07671.L$3 = SpillingKt.nullOutSpilledVariable(studentName4);
                c07671.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat2);
                c07671.L$5 = SpillingKt.nullOutSpilledVariable(dateStr2);
                c07671.L$6 = SpillingKt.nullOutSpilledVariable(finalStatus3);
                c07671.L$7 = SpillingKt.nullOutSpilledVariable(student2);
                c07671.L$8 = SpillingKt.nullOutSpilledVariable(existing);
                c07671.L$9 = SpillingKt.nullOutSpilledVariable(record);
                c07671.J$0 = timestamp2;
                c07671.label = 3;
                if (attendanceDao.insertOrUpdateAttendance(record, c07671) == obj3) {
                    return obj3;
                }
                return Unit.INSTANCE;
            case 3:
                long j = c07671.J$0;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:22:0x0117  */
    /* JADX WARN: Code duplicated, block: B:23:0x011c  */
    /* JADX WARN: Code duplicated, block: B:26:0x0178 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:27:0x0179  */
    /* JADX WARN: Code duplicated, block: B:7:0x001e  */
    public final Object recordStudentAttendance(String classroomId, String studentId, String studentName, String status, long timestamp, Continuation<? super Unit> continuation) {
        C07681 c07681;
        long j;
        Object approvedStudent;
        String status2;
        String dateStr;
        String studentName2;
        SimpleDateFormat dateFormat;
        String finalStatus;
        String classroomId2;
        String studentId2;
        long timestamp2;
        ApprovedStudentEntity student;
        String finalStatus2;
        AttendanceEntity record;
        AttendanceDao attendanceDao;
        String recordDateStr;
        String approvedDateStr;
        if (continuation instanceof C07681) {
            c07681 = (C07681) continuation;
            if ((c07681.label & Integer.MIN_VALUE) != 0) {
                c07681.label -= Integer.MIN_VALUE;
            } else {
                c07681 = new C07681(continuation);
            }
        } else {
            c07681 = new C07681(continuation);
        }
        Object $result = c07681.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07681.label) {
            case 0:
                j = 1000;
                ResultKt.throwOnFailure($result);
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
                String dateStr2 = dateFormat2.format(new Date(timestamp * 1000));
                ApprovedStudentDao approvedStudentDao = this.approvedStudentDao;
                c07681.L$0 = classroomId;
                c07681.L$1 = studentId;
                c07681.L$2 = studentName;
                c07681.L$3 = SpillingKt.nullOutSpilledVariable(status);
                c07681.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat2);
                c07681.L$5 = dateStr2;
                c07681.L$6 = status;
                c07681.J$0 = timestamp;
                c07681.label = 1;
                approvedStudent = approvedStudentDao.getApprovedStudent(classroomId, studentId, c07681);
                if (approvedStudent == coroutine_suspended) {
                    return coroutine_suspended;
                }
                status2 = status;
                dateStr = dateStr2;
                studentName2 = studentName;
                dateFormat = dateFormat2;
                finalStatus = status;
                classroomId2 = classroomId;
                studentId2 = studentId;
                timestamp2 = timestamp;
                student = (ApprovedStudentEntity) approvedStudent;
                if (student != null) {
                    SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    recordDateStr = fileDateFormat.format(new Date(timestamp2 * j));
                    approvedDateStr = fileDateFormat.format(new Date(student.getApprovedAt()));
                    Intrinsics.checkNotNull(approvedDateStr);
                    if (recordDateStr.compareTo(approvedDateStr) < 0) {
                        finalStatus2 = "UNMARKED";
                    } else {
                        finalStatus2 = finalStatus;
                    }
                } else {
                    finalStatus2 = finalStatus;
                }
                Intrinsics.checkNotNull(dateStr);
                record = new AttendanceEntity(0L, classroomId2, studentId2, studentName2, dateStr, finalStatus2, timestamp2, 1, null);
                attendanceDao = this.attendanceDao;
                c07681.L$0 = SpillingKt.nullOutSpilledVariable(classroomId2);
                c07681.L$1 = SpillingKt.nullOutSpilledVariable(studentId2);
                c07681.L$2 = SpillingKt.nullOutSpilledVariable(studentName2);
                c07681.L$3 = SpillingKt.nullOutSpilledVariable(status2);
                c07681.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat);
                c07681.L$5 = SpillingKt.nullOutSpilledVariable(dateStr);
                c07681.L$6 = SpillingKt.nullOutSpilledVariable(finalStatus2);
                c07681.L$7 = SpillingKt.nullOutSpilledVariable(student);
                c07681.L$8 = SpillingKt.nullOutSpilledVariable(record);
                c07681.J$0 = timestamp2;
                c07681.label = 2;
                if (attendanceDao.insertOrUpdateAttendance(record, c07681) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                long timestamp3 = c07681.J$0;
                finalStatus = (String) c07681.L$6;
                String dateStr3 = (String) c07681.L$5;
                SimpleDateFormat dateFormat3 = (SimpleDateFormat) c07681.L$4;
                status2 = (String) c07681.L$3;
                String studentName3 = (String) c07681.L$2;
                j = 1000;
                String studentId3 = (String) c07681.L$1;
                String classroomId3 = (String) c07681.L$0;
                ResultKt.throwOnFailure($result);
                approvedStudent = $result;
                dateStr = dateStr3;
                dateFormat = dateFormat3;
                studentName2 = studentName3;
                classroomId2 = classroomId3;
                studentId2 = studentId3;
                timestamp2 = timestamp3;
                student = (ApprovedStudentEntity) approvedStudent;
                if (student != null) {
                    SimpleDateFormat fileDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    recordDateStr = fileDateFormat2.format(new Date(timestamp2 * j));
                    approvedDateStr = fileDateFormat2.format(new Date(student.getApprovedAt()));
                    Intrinsics.checkNotNull(approvedDateStr);
                    if (recordDateStr.compareTo(approvedDateStr) < 0) {
                        finalStatus2 = "UNMARKED";
                    } else {
                        finalStatus2 = finalStatus;
                    }
                } else {
                    finalStatus2 = finalStatus;
                }
                Intrinsics.checkNotNull(dateStr);
                record = new AttendanceEntity(0L, classroomId2, studentId2, studentName2, dateStr, finalStatus2, timestamp2, 1, null);
                attendanceDao = this.attendanceDao;
                c07681.L$0 = SpillingKt.nullOutSpilledVariable(classroomId2);
                c07681.L$1 = SpillingKt.nullOutSpilledVariable(studentId2);
                c07681.L$2 = SpillingKt.nullOutSpilledVariable(studentName2);
                c07681.L$3 = SpillingKt.nullOutSpilledVariable(status2);
                c07681.L$4 = SpillingKt.nullOutSpilledVariable(dateFormat);
                c07681.L$5 = SpillingKt.nullOutSpilledVariable(dateStr);
                c07681.L$6 = SpillingKt.nullOutSpilledVariable(finalStatus2);
                c07681.L$7 = SpillingKt.nullOutSpilledVariable(student);
                c07681.L$8 = SpillingKt.nullOutSpilledVariable(record);
                c07681.J$0 = timestamp2;
                c07681.label = 2;
                if (attendanceDao.insertOrUpdateAttendance(record, c07681) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                long j2 = c07681.J$0;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:24:0x0118 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x0119  */
    /* JADX WARN: Code duplicated, block: B:28:0x011e  */
    /* JADX WARN: Code duplicated, block: B:30:0x0137 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:31:0x0138  */
    /* JADX WARN: Code duplicated, block: B:34:0x0145  */
    /* JADX WARN: Code duplicated, block: B:36:0x014b  */
    /* JADX WARN: Code duplicated, block: B:39:0x019e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:40:0x019f  */
    /* JADX WARN: Code duplicated, block: B:7:0x001e  */
    public final Object updateAttendanceStatus(String classroomId, String studentId, String dateStr, String newStatus, Continuation<? super Unit> continuation) {
        C07741 c07741;
        Object attendanceRecord;
        String newStatus2;
        AttendanceEntity existing;
        Object approvedStudent;
        String newStatus3;
        String classroomId2;
        String studentId2;
        String dateStr2;
        AttendanceEntity existing2;
        AttendanceDao attendanceDao;
        AttendanceEntity attendanceEntityCopy$default;
        ApprovedStudentEntity approvedStudentEntity;
        String studentName;
        AttendanceEntity record;
        AttendanceDao attendanceDao2;
        String classroomId3 = classroomId;
        String studentId3 = studentId;
        String dateStr3 = dateStr;
        if (continuation instanceof C07741) {
            c07741 = (C07741) continuation;
            if ((c07741.label & Integer.MIN_VALUE) != 0) {
                c07741.label -= Integer.MIN_VALUE;
            } else {
                c07741 = new C07741(continuation);
            }
        } else {
            c07741 = new C07741(continuation);
        }
        Object $result = c07741.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07741.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                AttendanceDao attendanceDao3 = this.attendanceDao;
                c07741.L$0 = classroomId3;
                c07741.L$1 = studentId3;
                c07741.L$2 = dateStr3;
                c07741.L$3 = newStatus;
                c07741.label = 1;
                attendanceRecord = attendanceDao3.getAttendanceRecord(classroomId3, studentId3, dateStr3, c07741);
                if (attendanceRecord == coroutine_suspended) {
                    return coroutine_suspended;
                }
                newStatus2 = newStatus;
                existing = (AttendanceEntity) attendanceRecord;
                if (existing != null) {
                    attendanceDao = this.attendanceDao;
                    attendanceEntityCopy$default = AttendanceEntity.copy$default(existing, 0L, null, null, null, null, newStatus2, 0L, 95, null);
                    c07741.L$0 = SpillingKt.nullOutSpilledVariable(classroomId3);
                    c07741.L$1 = SpillingKt.nullOutSpilledVariable(studentId3);
                    c07741.L$2 = SpillingKt.nullOutSpilledVariable(dateStr3);
                    c07741.L$3 = SpillingKt.nullOutSpilledVariable(newStatus2);
                    c07741.L$4 = SpillingKt.nullOutSpilledVariable(existing);
                    c07741.label = 2;
                    if (attendanceDao.insertOrUpdateAttendance(attendanceEntityCopy$default, c07741) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                ApprovedStudentDao approvedStudentDao = this.approvedStudentDao;
                c07741.L$0 = classroomId3;
                c07741.L$1 = studentId3;
                c07741.L$2 = dateStr3;
                c07741.L$3 = newStatus2;
                c07741.L$4 = SpillingKt.nullOutSpilledVariable(existing);
                c07741.label = 3;
                approvedStudent = approvedStudentDao.getApprovedStudent(classroomId3, studentId3, c07741);
                if (approvedStudent == coroutine_suspended) {
                    return coroutine_suspended;
                }
                newStatus3 = newStatus2;
                classroomId2 = classroomId3;
                studentId2 = studentId3;
                dateStr2 = dateStr3;
                existing2 = existing;
                approvedStudentEntity = (ApprovedStudentEntity) approvedStudent;
                if (approvedStudentEntity != null || (studentName = approvedStudentEntity.getStudentName()) == null) {
                    studentName = "Unknown";
                }
                String studentName2 = studentName;
                record = new AttendanceEntity(0L, classroomId2, studentId2, studentName2, dateStr2, newStatus3, System.currentTimeMillis() / 1000, 1, null);
                attendanceDao2 = this.attendanceDao;
                c07741.L$0 = SpillingKt.nullOutSpilledVariable(classroomId2);
                c07741.L$1 = SpillingKt.nullOutSpilledVariable(studentId2);
                c07741.L$2 = SpillingKt.nullOutSpilledVariable(dateStr2);
                c07741.L$3 = SpillingKt.nullOutSpilledVariable(newStatus3);
                c07741.L$4 = SpillingKt.nullOutSpilledVariable(existing2);
                c07741.L$5 = SpillingKt.nullOutSpilledVariable(studentName2);
                c07741.L$6 = SpillingKt.nullOutSpilledVariable(record);
                c07741.label = 4;
                if (attendanceDao2.insertOrUpdateAttendance(record, c07741) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                String newStatus4 = (String) c07741.L$3;
                dateStr3 = (String) c07741.L$2;
                studentId3 = (String) c07741.L$1;
                classroomId3 = (String) c07741.L$0;
                ResultKt.throwOnFailure($result);
                newStatus2 = newStatus4;
                attendanceRecord = $result;
                existing = (AttendanceEntity) attendanceRecord;
                if (existing != null) {
                    attendanceDao = this.attendanceDao;
                    attendanceEntityCopy$default = AttendanceEntity.copy$default(existing, 0L, null, null, null, null, newStatus2, 0L, 95, null);
                    c07741.L$0 = SpillingKt.nullOutSpilledVariable(classroomId3);
                    c07741.L$1 = SpillingKt.nullOutSpilledVariable(studentId3);
                    c07741.L$2 = SpillingKt.nullOutSpilledVariable(dateStr3);
                    c07741.L$3 = SpillingKt.nullOutSpilledVariable(newStatus2);
                    c07741.L$4 = SpillingKt.nullOutSpilledVariable(existing);
                    c07741.label = 2;
                    if (attendanceDao.insertOrUpdateAttendance(attendanceEntityCopy$default, c07741) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                ApprovedStudentDao approvedStudentDao2 = this.approvedStudentDao;
                c07741.L$0 = classroomId3;
                c07741.L$1 = studentId3;
                c07741.L$2 = dateStr3;
                c07741.L$3 = newStatus2;
                c07741.L$4 = SpillingKt.nullOutSpilledVariable(existing);
                c07741.label = 3;
                approvedStudent = approvedStudentDao2.getApprovedStudent(classroomId3, studentId3, c07741);
                if (approvedStudent == coroutine_suspended) {
                    return coroutine_suspended;
                }
                newStatus3 = newStatus2;
                classroomId2 = classroomId3;
                studentId2 = studentId3;
                dateStr2 = dateStr3;
                existing2 = existing;
                approvedStudentEntity = (ApprovedStudentEntity) approvedStudent;
                if (approvedStudentEntity != null) {
                    studentName = "Unknown";
                } else {
                    studentName = "Unknown";
                }
                String studentName3 = studentName;
                record = new AttendanceEntity(0L, classroomId2, studentId2, studentName3, dateStr2, newStatus3, System.currentTimeMillis() / 1000, 1, null);
                attendanceDao2 = this.attendanceDao;
                c07741.L$0 = SpillingKt.nullOutSpilledVariable(classroomId2);
                c07741.L$1 = SpillingKt.nullOutSpilledVariable(studentId2);
                c07741.L$2 = SpillingKt.nullOutSpilledVariable(dateStr2);
                c07741.L$3 = SpillingKt.nullOutSpilledVariable(newStatus3);
                c07741.L$4 = SpillingKt.nullOutSpilledVariable(existing2);
                c07741.L$5 = SpillingKt.nullOutSpilledVariable(studentName3);
                c07741.L$6 = SpillingKt.nullOutSpilledVariable(record);
                c07741.label = 4;
                if (attendanceDao2.insertOrUpdateAttendance(record, c07741) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            case 3:
                AttendanceEntity existing3 = (AttendanceEntity) c07741.L$4;
                String newStatus5 = (String) c07741.L$3;
                String dateStr4 = (String) c07741.L$2;
                String studentId4 = (String) c07741.L$1;
                String classroomId4 = (String) c07741.L$0;
                ResultKt.throwOnFailure($result);
                approvedStudent = $result;
                newStatus3 = newStatus5;
                classroomId2 = classroomId4;
                studentId2 = studentId4;
                dateStr2 = dateStr4;
                existing2 = existing3;
                approvedStudentEntity = (ApprovedStudentEntity) approvedStudent;
                if (approvedStudentEntity != null) {
                    studentName = "Unknown";
                } else {
                    studentName = "Unknown";
                }
                String studentName4 = studentName;
                record = new AttendanceEntity(0L, classroomId2, studentId2, studentName4, dateStr2, newStatus3, System.currentTimeMillis() / 1000, 1, null);
                attendanceDao2 = this.attendanceDao;
                c07741.L$0 = SpillingKt.nullOutSpilledVariable(classroomId2);
                c07741.L$1 = SpillingKt.nullOutSpilledVariable(studentId2);
                c07741.L$2 = SpillingKt.nullOutSpilledVariable(dateStr2);
                c07741.L$3 = SpillingKt.nullOutSpilledVariable(newStatus3);
                c07741.L$4 = SpillingKt.nullOutSpilledVariable(existing2);
                c07741.L$5 = SpillingKt.nullOutSpilledVariable(studentName4);
                c07741.L$6 = SpillingKt.nullOutSpilledVariable(record);
                c07741.label = 4;
                if (attendanceDao2.insertOrUpdateAttendance(record, c07741) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 4:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Flow<List<TaskEntity>> getTasksForClassroom(String classroomId) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        return this.taskDao.getTasksForClassroom(classroomId);
    }

    public final Object getTasksForClassroomSync(String classroomId, Continuation<? super List<TaskEntity>> continuation) {
        return this.taskDao.getTasksForClassroomSync(classroomId, continuation);
    }

    public final Object getTaskById(String tkid, Continuation<? super TaskEntity> continuation) {
        return this.taskDao.getTaskById(tkid, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x005f  */
    /* JADX WARN: Code duplicated, block: B:21:0x0074 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x0075  */
    /* JADX WARN: Code duplicated, block: B:25:0x0079  */
    /* JADX WARN: Code duplicated, block: B:27:0x008e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x008f  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object saveTask(TaskEntity task, Continuation<? super Unit> continuation) {
        C07711 c07711;
        Object taskById;
        TaskEntity existing;
        TaskDao taskDao;
        if (continuation instanceof C07711) {
            c07711 = (C07711) continuation;
            if ((c07711.label & Integer.MIN_VALUE) != 0) {
                c07711.label -= Integer.MIN_VALUE;
            } else {
                c07711 = new C07711(continuation);
            }
        } else {
            c07711 = new C07711(continuation);
        }
        Object $result = c07711.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07711.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                TaskDao taskDao2 = this.taskDao;
                String tkid = task.getTkid();
                c07711.L$0 = task;
                c07711.label = 1;
                taskById = taskDao2.getTaskById(tkid, c07711);
                if (taskById == coroutine_suspended) {
                    return coroutine_suspended;
                }
                existing = (TaskEntity) taskById;
                taskDao = this.taskDao;
                if (existing != null) {
                    c07711.L$0 = SpillingKt.nullOutSpilledVariable(task);
                    c07711.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07711.label = 2;
                    if (taskDao.updateTask(task, c07711) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    c07711.L$0 = SpillingKt.nullOutSpilledVariable(task);
                    c07711.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07711.label = 3;
                    if (taskDao.insertTask(task, c07711) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                task = (TaskEntity) c07711.L$0;
                ResultKt.throwOnFailure($result);
                taskById = $result;
                existing = (TaskEntity) taskById;
                taskDao = this.taskDao;
                if (existing != null) {
                    c07711.L$0 = SpillingKt.nullOutSpilledVariable(task);
                    c07711.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07711.label = 2;
                    if (taskDao.updateTask(task, c07711) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    c07711.L$0 = SpillingKt.nullOutSpilledVariable(task);
                    c07711.L$1 = SpillingKt.nullOutSpilledVariable(existing);
                    c07711.label = 3;
                    if (taskDao.insertTask(task, c07711) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 2:
            case 3:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object deleteTask(String tkid, Continuation<? super Unit> continuation) {
        Object objDeleteTaskById = this.taskDao.deleteTaskById(tkid, continuation);
        return objDeleteTaskById == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteTaskById : Unit.INSTANCE;
    }

    public final Flow<List<TaskSubmissionEntity>> getSubmissionsForTask(String taskId) {
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        return this.taskSubmissionDao.getSubmissionsForTask(taskId);
    }

    public final Flow<List<TaskSubmissionEntity>> getSubmissionsForStudent(String studentId) {
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        return this.taskSubmissionDao.getSubmissionsForStudent(studentId);
    }

    public static /* synthetic */ Object recordTaskSubmission$default(CalisRepository calisRepository, String str, String str2, String str3, int i, long j, Continuation continuation, int i2, Object obj) {
        long jCurrentTimeMillis;
        if ((i2 & 16) == 0) {
            jCurrentTimeMillis = j;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        }
        return calisRepository.recordTaskSubmission(str, str2, str3, i, jCurrentTimeMillis, continuation);
    }

    public final Object recordTaskSubmission(String taskId, String studentId, String studentName, int score, long timestamp, Continuation<? super Unit> continuation) {
        TaskSubmissionEntity submission = new TaskSubmissionEntity(0L, taskId, studentId, studentName, score, timestamp, true, 1, null);
        Object objInsertSubmission = this.taskSubmissionDao.insertSubmission(submission, continuation);
        return objInsertSubmission == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInsertSubmission : Unit.INSTANCE;
    }

    public final Object getSubmission(String taskId, String studentId, Continuation<? super TaskSubmissionEntity> continuation) {
        return this.taskSubmissionDao.getSubmission(taskId, studentId, continuation);
    }

    public final Object getSyncRecord(String studentId, String classroomId, Continuation<? super SyncRecordEntity> continuation) {
        return this.syncRecordDao.getSyncRecord(studentId, classroomId, continuation);
    }

    public final Object updateSyncRecord(String studentId, String classroomId, long scheduleTime, long taskTime, Continuation<? super Unit> continuation) {
        SyncRecordEntity record = new SyncRecordEntity(0L, studentId, classroomId, scheduleTime, taskTime, 1, null);
        Object objInsertOrUpdateSyncRecord = this.syncRecordDao.insertOrUpdateSyncRecord(record, continuation);
        return objInsertOrUpdateSyncRecord == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInsertOrUpdateSyncRecord : Unit.INSTANCE;
    }

    public final Flow<List<TaskDeliveryEntity>> getDeliveriesForTask(String taskId) {
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        return this.database.taskDeliveryDao().getDeliveriesForTask(taskId);
    }

    public final Object markTaskAsReceived(String taskId, String studentId, Continuation<? super Unit> continuation) {
        TaskDeliveryEntity delivery = new TaskDeliveryEntity(taskId, studentId, true);
        Object objInsertDelivery = this.database.taskDeliveryDao().insertDelivery(delivery, continuation);
        return objInsertDelivery == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInsertDelivery : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object hasStudentReceivedTask(String taskId, String studentId, Continuation<? super Boolean> continuation) {
        C07651 c07651;
        Object delivery;
        if (continuation instanceof C07651) {
            c07651 = (C07651) continuation;
            if ((c07651.label & Integer.MIN_VALUE) != 0) {
                c07651.label -= Integer.MIN_VALUE;
            } else {
                c07651 = new C07651(continuation);
            }
        } else {
            c07651 = new C07651(continuation);
        }
        Object $result = c07651.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c07651.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                TaskDeliveryDao taskDeliveryDao = this.database.taskDeliveryDao();
                c07651.L$0 = SpillingKt.nullOutSpilledVariable(taskId);
                c07651.L$1 = SpillingKt.nullOutSpilledVariable(studentId);
                c07651.label = 1;
                delivery = taskDeliveryDao.getDelivery(taskId, studentId, c07651);
                if (delivery == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                delivery = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        TaskDeliveryEntity taskDeliveryEntity = (TaskDeliveryEntity) delivery;
        return Boxing.boxBoolean(taskDeliveryEntity != null ? taskDeliveryEntity.getReceived() : false);
    }

    public final Object exportAttendanceCsv(String classroomId, List<AttendanceEntity> list, Continuation<? super String> continuation) {
        StringBuilder sb = new StringBuilder();
        sb.append("Attendance Log Export\n");
        sb.append("Classroom ID: " + classroomId + "\n");
        sb.append("Export Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()) + "\n\n");
        sb.append("Date,Student ID,Student Name,Status,Timestamp\n");
        for (AttendanceEntity att : list) {
            String formattedTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date(att.getTimestamp() * 1000));
            sb.append(att.getDate() + "," + att.getStudentId() + ",\"" + att.getStudentName() + "\"," + att.getStatus() + "," + formattedTime + "\n");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:19:0x010e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x010f  */
    /* JADX WARN: Code duplicated, block: B:23:0x0120  */
    /* JADX WARN: Code duplicated, block: B:29:0x0156 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:32:0x015b  */
    /* JADX WARN: Code duplicated, block: B:33:0x016f  */
    /* JADX WARN: Code duplicated, block: B:36:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:37:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0127 -> B:34:0x0181). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0154 -> B:30:0x0157). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object exportTaskSubmissionsCsv(com.example.data.db.TaskEntity r23, java.util.List<com.example.data.db.TaskSubmissionEntity> r24, kotlin.coroutines.Continuation<? super java.lang.String> r25) {
        /*
            Method dump skipped, instruction units count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.data.repository.CalisRepository.exportTaskSubmissionsCsv(com.example.data.db.TaskEntity, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
