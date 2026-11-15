package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDao {
    @Query("SELECT * FROM teachers WHERE tid NOT LIKE 'TEACHER_%' LIMIT 1")
    fun getTeacher(): Flow<TeacherEntity?>

    @Query("SELECT * FROM teachers WHERE tid NOT LIKE 'TEACHER_%' LIMIT 1")
    suspend fun getTeacherOnce(): TeacherEntity?

    @Query("SELECT * FROM teachers WHERE tid = :tid LIMIT 1")
    suspend fun getTeacherById(tid: String): TeacherEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacher(teacher: TeacherEntity)

    @Update
    suspend fun updateTeacher(teacher: TeacherEntity)

    @Query("DELETE FROM teachers")
    suspend fun clearTeachers()
}

@Dao
interface StudentProfileDao {
    @Query("SELECT * FROM student_profiles LIMIT 1")
    fun getStudentProfile(): Flow<StudentProfileEntity?>

    @Query("SELECT * FROM student_profiles LIMIT 1")
    suspend fun getStudentProfileOnce(): StudentProfileEntity?

    @Query("SELECT * FROM student_profiles WHERE sid = :sid LIMIT 1")
    suspend fun getStudentProfileById(sid: String): StudentProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentProfile(profile: StudentProfileEntity)

    @Update
    suspend fun updateStudentProfile(profile: StudentProfileEntity)

    @Query("DELETE FROM student_profiles")
    suspend fun clearStudentProfiles()
}

@Dao
interface ClassroomDao {
    @Query("SELECT * FROM classrooms WHERE teacherId = :teacherId LIMIT 1")
    fun getClassroomForTeacher(teacherId: String): Flow<ClassroomEntity?>

    @Query("SELECT * FROM classrooms WHERE cid = :cid LIMIT 1")
    suspend fun getClassroomById(cid: String): ClassroomEntity?

    @Query("SELECT * FROM classrooms WHERE isBound = 1 LIMIT 1")
    fun getActiveBoundClassroom(): Flow<ClassroomEntity?>

    @Query("SELECT * FROM classrooms WHERE isBound = 1 LIMIT 1")
    suspend fun getActiveBoundClassroomSync(): ClassroomEntity?

    @Query("SELECT * FROM classrooms LIMIT 1")
    fun getAnyClassroom(): Flow<ClassroomEntity?>

    @Query("SELECT * FROM classrooms LIMIT 1")
    suspend fun getAnyClassroomSync(): ClassroomEntity?

    @Query("SELECT * FROM classrooms")
    suspend fun getAllClassroomsSync(): List<ClassroomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassroom(classroom: ClassroomEntity)

    @Update
    suspend fun updateClassroom(classroom: ClassroomEntity)

    @Query("DELETE FROM classrooms WHERE cid = :cid")
    suspend fun deleteClassroomById(cid: String)
}

@Dao
interface ApprovedStudentDao {
    @Query("SELECT * FROM approved_students WHERE classroomId = :classroomId ORDER BY studentName ASC")
    fun getApprovedStudents(classroomId: String): Flow<List<ApprovedStudentEntity>>

    @Query("SELECT * FROM approved_students WHERE classroomId = :classroomId ORDER BY studentName ASC")
    suspend fun getApprovedStudentsSync(classroomId: String): List<ApprovedStudentEntity>

    @Query("SELECT * FROM approved_students WHERE classroomId = :classroomId AND studentId = :studentId LIMIT 1")
    suspend fun getApprovedStudent(classroomId: String, studentId: String): ApprovedStudentEntity?

    @Query("SELECT * FROM approved_students WHERE studentId = :studentId LIMIT 1")
    suspend fun getApprovedStudentGlobally(studentId: String): ApprovedStudentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApprovedStudent(approvedStudent: ApprovedStudentEntity)

    @Query("DELETE FROM approved_students WHERE classroomId = :classroomId AND studentId = :studentId")
    suspend fun deleteApprovedStudent(classroomId: String, studentId: String)
}

@Dao
interface PendingRegistrationDao {
    @Query("SELECT * FROM pending_registrations WHERE classroomId = :classroomId ORDER BY requestedAt ASC")
    fun getPendingRegistrations(classroomId: String): Flow<List<PendingRegistrationEntity>>

    @Query("SELECT * FROM pending_registrations WHERE classroomId = :classroomId AND studentId = :studentId LIMIT 1")
    suspend fun getPendingRegistration(classroomId: String, studentId: String): PendingRegistrationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPendingRegistration(pending: PendingRegistrationEntity)

    @Query("DELETE FROM pending_registrations WHERE classroomId = :classroomId AND studentId = :studentId")
    suspend fun deletePendingRegistration(classroomId: String, studentId: String)

    @Query("DELETE FROM pending_registrations WHERE classroomId = :classroomId")
    suspend fun clearPendingForClassroom(classroomId: String)
}

@Dao
interface AttendanceDao {
    @Query("SELECT * FROM attendance WHERE classroomId = :classroomId ORDER BY timestamp DESC")
    fun getAttendanceForClassroom(classroomId: String): Flow<List<AttendanceEntity>>

    @Query("SELECT * FROM attendance WHERE classroomId = :classroomId")
    suspend fun getAttendanceForClassroomSync(classroomId: String): List<AttendanceEntity>

    @Query("SELECT * FROM attendance WHERE classroomId = :classroomId AND date LIKE :datePrefix || '%' ORDER BY studentName ASC")
    fun getAttendanceForDate(classroomId: String, datePrefix: String): Flow<List<AttendanceEntity>>

    @Query("SELECT * FROM attendance WHERE classroomId = :classroomId AND date LIKE :datePrefix || '%' ORDER BY studentName ASC")
    suspend fun getAttendanceForDateSync(classroomId: String, datePrefix: String): List<AttendanceEntity>

    @Query("SELECT * FROM attendance WHERE studentId = :studentId ORDER BY timestamp DESC")
    fun getAttendanceForStudent(studentId: String): Flow<List<AttendanceEntity>>

    @Query("SELECT * FROM attendance WHERE classroomId = :classroomId AND studentId = :studentId AND date LIKE :datePrefix || '%' LIMIT 1")
    suspend fun getAttendanceRecord(classroomId: String, studentId: String, datePrefix: String): AttendanceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAttendance(attendance: AttendanceEntity)

    @Query("DELETE FROM attendance WHERE id = :id")
    suspend fun deleteAttendanceById(id: Long)
}

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE classroomId = :classroomId ORDER BY datePosted DESC")
    fun getTasksForClassroom(classroomId: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE classroomId = :classroomId ORDER BY datePosted ASC")
    suspend fun getTasksForClassroomSync(classroomId: String): List<TaskEntity>

    @Query("SELECT * FROM tasks ORDER BY deadline ASC")
    suspend fun getAllTasksSync(): List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE tkid = :tkid LIMIT 1")
    suspend fun getTaskById(tkid: String): TaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE tkid = :tkid")
    suspend fun deleteTaskById(tkid: String)
}

@Dao
interface TaskSubmissionDao {
    @Query("SELECT * FROM task_submissions WHERE taskId = :taskId ORDER BY studentName ASC")
    fun getSubmissionsForTask(taskId: String): Flow<List<TaskSubmissionEntity>>

    @Query("SELECT * FROM task_submissions WHERE studentId = :studentId")
    fun getSubmissionsForStudent(studentId: String): Flow<List<TaskSubmissionEntity>>

    @Query("SELECT * FROM task_submissions WHERE taskId = :taskId AND studentId = :studentId LIMIT 1")
    suspend fun getSubmission(taskId: String, studentId: String): TaskSubmissionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubmission(submission: TaskSubmissionEntity)
}

@Dao
interface SyncRecordDao {
    @Query("SELECT * FROM sync_records WHERE studentId = :studentId AND classroomId = :classroomId LIMIT 1")
    suspend fun getSyncRecord(studentId: String, classroomId: String): SyncRecordEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateSyncRecord(syncRecord: SyncRecordEntity)
}

@Dao
interface TaskDeliveryDao {
    @Query("SELECT * FROM task_deliveries WHERE taskId = :taskId")
    fun getDeliveriesForTask(taskId: String): Flow<List<TaskDeliveryEntity>>

    @Query("SELECT * FROM task_deliveries WHERE taskId = :taskId")
    suspend fun getDeliveriesForTaskSync(taskId: String): List<TaskDeliveryEntity>

    @Query("SELECT * FROM task_deliveries WHERE taskId = :taskId AND studentId = :studentId LIMIT 1")
    suspend fun getDelivery(taskId: String, studentId: String): TaskDeliveryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDelivery(delivery: TaskDeliveryEntity)
}
