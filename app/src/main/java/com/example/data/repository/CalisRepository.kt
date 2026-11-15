package com.example.data.repository

import com.example.data.db.ApprovedStudentDao
import com.example.data.db.ApprovedStudentEntity
import com.example.data.db.AttendanceDao
import com.example.data.db.AttendanceEntity
import com.example.data.db.ClassroomDao
import com.example.data.db.ClassroomEntity
import com.example.data.db.PendingRegistrationDao
import com.example.data.db.PendingRegistrationEntity
import com.example.data.db.StudentProfileDao
import com.example.data.db.StudentProfileEntity
import com.example.data.db.SyncRecordDao
import com.example.data.db.SyncRecordEntity
import com.example.data.db.TaskDao
import com.example.data.db.TaskEntity
import com.example.data.db.TaskSubmissionDao
import com.example.data.db.TaskSubmissionEntity
import com.example.data.db.TeacherDao
import com.example.data.db.TeacherEntity
import com.example.util.CsvExportUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalisRepository(
    private val database: com.example.data.db.CalisDatabase,
    private val teacherDao: TeacherDao,
    private val studentProfileDao: StudentProfileDao,
    private val classroomDao: ClassroomDao,
    private val approvedStudentDao: ApprovedStudentDao,
    private val pendingRegistrationDao: PendingRegistrationDao,
    private val attendanceDao: AttendanceDao,
    private val taskDao: TaskDao,
    private val taskSubmissionDao: TaskSubmissionDao,
    private val syncRecordDao: SyncRecordDao
) {
    suspend fun deleteAllData() = withContext(Dispatchers.IO) {
        database.clearAllTables()
    }

    suspend fun getTeacherOnce(): TeacherEntity? = withContext(Dispatchers.IO) {
        teacherDao.getTeacherOnce()
    }

    suspend fun getStudentProfileOnce(): StudentProfileEntity? = withContext(Dispatchers.IO) {
        studentProfileDao.getStudentProfileOnce()
    }
    // --- TEACHER ---
    val currentTeacher: Flow<TeacherEntity?> = teacherDao.getTeacher()

    suspend fun saveTeacher(teacher: TeacherEntity) {
        val existing = teacherDao.getTeacherById(teacher.tid)
        if (existing != null) {
            teacherDao.updateTeacher(teacher)
        } else {
            teacherDao.insertTeacher(teacher)
        }
    }

    suspend fun getTeacherById(tid: String): TeacherEntity? {
        return teacherDao.getTeacherById(tid)
    }

    // --- STUDENT PROFILE ---
    val currentStudentProfile: Flow<StudentProfileEntity?> = studentProfileDao.getStudentProfile()

    suspend fun saveStudentProfile(profile: StudentProfileEntity) {
        val existing = studentProfileDao.getStudentProfileOnce()
        if (existing != null) {
            studentProfileDao.updateStudentProfile(profile)
        } else {
            studentProfileDao.insertStudentProfile(profile)
        }
    }

    suspend fun getStudentProfileById(sid: String): StudentProfileEntity? = withContext(Dispatchers.IO) {
        studentProfileDao.getStudentProfileById(sid)
    }

    // --- CLASSROOM ---
    val activeBoundClassroom: Flow<ClassroomEntity?> = classroomDao.getActiveBoundClassroom()

    suspend fun getActiveBoundClassroomSync(): ClassroomEntity? = withContext(Dispatchers.IO) {
        classroomDao.getActiveBoundClassroomSync()
    }

    fun getClassroomForTeacher(teacherId: String): Flow<ClassroomEntity?> {
        return classroomDao.getClassroomForTeacher(teacherId)
    }

    suspend fun getClassroomById(cid: String): ClassroomEntity? {
        return classroomDao.getClassroomById(cid)
    }

    suspend fun saveClassroom(classroom: ClassroomEntity) {
        val existing = classroomDao.getClassroomById(classroom.cid)
        if (existing != null) {
            classroomDao.updateClassroom(classroom)
        } else {
            classroomDao.insertClassroom(classroom)
        }
    }

    suspend fun updateClassroomSession(cid: String, isActive: Boolean) {
        val existing = classroomDao.getClassroomById(cid)
        if (existing != null) {
            classroomDao.updateClassroom(existing.copy(isActiveSession = isActive))
        }
    }

    suspend fun unregisterClassroom(cid: String) {
        val existing = classroomDao.getClassroomById(cid)
        if (existing != null) {
            classroomDao.updateClassroom(existing.copy(isBound = false, isActiveSession = false, deviceAddress = null))
        }
        // Do NOT delete pending registrations, approved students, attendance, tasks, or TC account data
    }

    // --- PENDING REGISTRATIONS ---
    fun getPendingRegistrations(classroomId: String): Flow<List<PendingRegistrationEntity>> =
        pendingRegistrationDao.getPendingRegistrations(classroomId)

    suspend fun addPendingRegistration(pending: PendingRegistrationEntity) {
        val existing = pendingRegistrationDao.getPendingRegistration(pending.classroomId, pending.studentId)
        if (existing != null) {
            pendingRegistrationDao.insertPendingRegistration(pending.copy(id = existing.id))
        } else {
            pendingRegistrationDao.insertPendingRegistration(pending)
        }
    }

    suspend fun approveStudentRegistration(pending: PendingRegistrationEntity) {
        val approved = ApprovedStudentEntity(
            classroomId = pending.classroomId,
            studentId = pending.studentId,
            studentName = pending.studentName,
            sex = pending.sex,
            lrn = pending.lrn,
            birth = pending.birth
        )
        approvedStudentDao.insertApprovedStudent(approved)
        pendingRegistrationDao.deletePendingRegistration(pending.classroomId, pending.studentId)

        // Automatically mark as PRESENT in today's attendance record
        recordAttendance(
            classroomId = pending.classroomId,
            studentId = pending.studentId,
            studentName = pending.studentName,
            status = "PRESENT"
        )

        // Mark as "UNMARKED" for all previous attendance dates
        val todayDateStr = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date())
        val allLogs = attendanceDao.getAttendanceForClassroomSync(pending.classroomId)
        val previousDates = allLogs.map { it.date }.distinct().filter { it != todayDateStr }
        for (date in previousDates) {
            val existingOnDate = allLogs.firstOrNull { it.date == date }
            val timestamp = existingOnDate?.timestamp ?: (System.currentTimeMillis() / 1000)
            
            val record = AttendanceEntity(
                classroomId = pending.classroomId,
                studentId = pending.studentId,
                studentName = pending.studentName,
                date = date,
                status = "UNMARKED",
                timestamp = timestamp
            )
            attendanceDao.insertOrUpdateAttendance(record)
        }
    }

    suspend fun rejectStudentRegistration(pending: PendingRegistrationEntity) {
        pendingRegistrationDao.deletePendingRegistration(pending.classroomId, pending.studentId)
    }

    // --- APPROVED STUDENTS (MASTERLIST) ---
    fun getApprovedStudents(classroomId: String): Flow<List<ApprovedStudentEntity>> =
        approvedStudentDao.getApprovedStudents(classroomId)

    suspend fun getApprovedStudent(classroomId: String, studentId: String): ApprovedStudentEntity? {
        return approvedStudentDao.getApprovedStudent(classroomId, studentId)
    }

    suspend fun getApprovedStudentGlobally(studentId: String): ApprovedStudentEntity? {
        return approvedStudentDao.getApprovedStudentGlobally(studentId)
    }

    suspend fun isStudentApproved(classroomId: String, studentId: String): Boolean {
        return approvedStudentDao.getApprovedStudent(classroomId, studentId) != null
    }

    suspend fun removeApprovedStudent(classroomId: String, studentId: String) {
        approvedStudentDao.deleteApprovedStudent(classroomId, studentId)
    }

    // --- ATTENDANCE ---
    fun getAttendanceForClassroom(classroomId: String): Flow<List<AttendanceEntity>> =
        attendanceDao.getAttendanceForClassroom(classroomId)

    fun getAttendanceForDate(classroomId: String, date: String): Flow<List<AttendanceEntity>> =
        attendanceDao.getAttendanceForDate(classroomId, date)

    suspend fun getAttendanceForDateSync(classroomId: String, date: String): List<AttendanceEntity> =
        attendanceDao.getAttendanceForDateSync(classroomId, date)

    fun getAttendanceForStudent(studentId: String): Flow<List<AttendanceEntity>> =
        attendanceDao.getAttendanceForStudent(studentId)

    private fun parseCalendarDate(dateStr: String, timestamp: Long): java.util.Calendar? {
        val formats = listOf(
            "MM/dd/yyyy hh:mm a",
            "MM/dd/yyyy HH:mm",
            "MM/dd/yyyy",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd",
            "MM-dd-yyyy",
            "yyyy/MM/dd"
        )
        val trimmed = dateStr.trim()
        for (fmt in formats) {
            try {
                val sdf = SimpleDateFormat(fmt, Locale.US)
                val d = sdf.parse(trimmed) ?: sdf.parse(trimmed.split(" ")[0])
                if (d != null) {
                    val cal = java.util.Calendar.getInstance().apply { time = d }
                    if (cal.get(java.util.Calendar.YEAR) in 2000..2100) return cal
                }
            } catch (_: Exception) {}
        }
        if (timestamp > 0L) {
            val ts = if (timestamp < 10_000_000_000L) timestamp * 1000L else timestamp
            return java.util.Calendar.getInstance().apply { timeInMillis = ts }
        }
        return null
    }

    private fun isSameCalendarDay(dateStr1: String, dateStr2: String, timestamp1: Long = 0L): Boolean {
        if (dateStr1 == dateStr2) return true
        if (dateStr1.isNotBlank() && dateStr2.isNotBlank() && (dateStr1.startsWith(dateStr2) || dateStr2.startsWith(dateStr1))) return true
        val cal1 = parseCalendarDate(dateStr1, timestamp1)
        val cal2 = parseCalendarDate(dateStr2, 0L)
        if (cal1 != null && cal2 != null) {
            return cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR) &&
                    cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR)
        }
        return false
    }

    suspend fun getAttendanceRecord(classroomId: String, studentId: String, dateStr: String): AttendanceEntity? {
        val direct = attendanceDao.getAttendanceRecord(classroomId, studentId, dateStr)
        if (direct != null) return direct

        val formats = listOf("MM/dd/yyyy", "yyyy-MM-dd", "MM-dd-yyyy", "yyyy/MM/dd")
        for (srcFmt in formats) {
            try {
                val parsed = SimpleDateFormat(srcFmt, Locale.US).apply { isLenient = false }.parse(dateStr.trim().split(" ")[0])
                if (parsed != null) {
                    for (targetFmt in formats) {
                        if (targetFmt != srcFmt) {
                            val converted = SimpleDateFormat(targetFmt, Locale.US).format(parsed)
                            val match = attendanceDao.getAttendanceRecord(classroomId, studentId, converted)
                            if (match != null) return match
                        }
                    }
                }
            } catch (_: Exception) {}
        }

        val allLogs = attendanceDao.getAttendanceForClassroomSync(classroomId)
        return allLogs.firstOrNull { log ->
            log.studentId == studentId && isSameCalendarDay(log.date, dateStr, log.timestamp)
        }
    }

    suspend fun recordAttendance(
        classroomId: String,
        studentId: String,
        studentName: String,
        status: String,
        timestamp: Long = System.currentTimeMillis() / 1000
    ) {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        val dateStr = dateFormat.format(Date(timestamp * 1000))

        val existing = getAttendanceRecord(classroomId, studentId, dateStr)

        // Protect checked-in status: do not overwrite an existing PRESENT or LATE check-in with ABSENT
        if (status == "ABSENT" && (existing?.status == "PRESENT" || existing?.status == "LATE")) {
            return
        }

        var finalStatus = status
        val student = approvedStudentDao.getApprovedStudent(classroomId, studentId)
        if (student != null) {
            val recordMs = timestamp * 1000
            val approvedMs = student.approvedAt
            
            // If the record is on a day EARLIER than the approval day
            val recordCal = java.util.Calendar.getInstance().apply { timeInMillis = recordMs }
            val approvedCal = java.util.Calendar.getInstance().apply { timeInMillis = approvedMs }
            
            val isEarlierDay = recordCal.get(java.util.Calendar.YEAR) < approvedCal.get(java.util.Calendar.YEAR) ||
                (recordCal.get(java.util.Calendar.YEAR) == approvedCal.get(java.util.Calendar.YEAR) && 
                 recordCal.get(java.util.Calendar.DAY_OF_YEAR) < approvedCal.get(java.util.Calendar.DAY_OF_YEAR))
                 
            if (isEarlierDay) {
                finalStatus = "UNMARKED"
            }
        }

        val record = AttendanceEntity(
            id = existing?.id ?: 0L,
            classroomId = classroomId,
            studentId = studentId,
            studentName = studentName,
            date = existing?.date ?: dateStr,
            status = finalStatus,
            timestamp = timestamp
        )
        attendanceDao.insertOrUpdateAttendance(record)
    }

    suspend fun recordStudentAttendance(
        classroomId: String,
        studentId: String,
        studentName: String,
        status: String,
        timestamp: Long
    ) {
        val effectiveTs = if (timestamp <= 0L) {
            System.currentTimeMillis() / 1000L
        } else if (timestamp > 1_000_000_000_000L) {
            timestamp / 1000L
        } else {
            timestamp
        }
        val millis = effectiveTs * 1000L
        val dateFormat = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US)
        val dateStr = dateFormat.format(Date(millis))

        var finalStatus = status
        val student = approvedStudentDao.getApprovedStudent(classroomId, studentId)
        if (student != null) {
            val fileDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            val recordDateStr = fileDateFormat.format(Date(millis))
            val approvedDateStr = fileDateFormat.format(Date(student.approvedAt))
            if (recordDateStr < approvedDateStr) {
                finalStatus = "UNMARKED"
            }
        }

        val record = AttendanceEntity(
            classroomId = classroomId,
            studentId = studentId,
            studentName = studentName,
            date = dateStr,
            status = finalStatus,
            timestamp = effectiveTs
        )
        attendanceDao.insertOrUpdateAttendance(record)
    }

    suspend fun updateAttendanceStatus(
        classroomId: String,
        studentId: String,
        dateStr: String,
        newStatus: String
    ) {
        val existing = getAttendanceRecord(classroomId, studentId, dateStr)
        if (existing != null) {
            attendanceDao.insertOrUpdateAttendance(existing.copy(status = newStatus))
        } else {
            val studentName = approvedStudentDao.getApprovedStudent(classroomId, studentId)?.studentName ?: "Unknown"
            val record = AttendanceEntity(
                classroomId = classroomId,
                studentId = studentId,
                studentName = studentName,
                date = dateStr,
                status = newStatus,
                timestamp = System.currentTimeMillis() / 1000
            )
            attendanceDao.insertOrUpdateAttendance(record)
        }
    }

    // --- TASKS ---
    fun getTasksForClassroom(classroomId: String): Flow<List<TaskEntity>> =
        taskDao.getTasksForClassroom(classroomId)

    suspend fun getTasksForClassroomSync(classroomId: String): List<TaskEntity> =
        taskDao.getTasksForClassroomSync(classroomId)

    suspend fun getTaskById(tkid: String): TaskEntity? = taskDao.getTaskById(tkid)

    suspend fun saveTask(task: TaskEntity) {
        val existing = taskDao.getTaskById(task.tkid)
        if (existing != null) {
            taskDao.updateTask(task)
        } else {
            taskDao.insertTask(task)
        }
    }

    suspend fun deleteTask(tkid: String) {
        taskDao.deleteTaskById(tkid)
    }

    // --- TASK SUBMISSIONS ---
    fun getSubmissionsForTask(taskId: String): Flow<List<TaskSubmissionEntity>> =
        taskSubmissionDao.getSubmissionsForTask(taskId)

    fun getSubmissionsForStudent(studentId: String): Flow<List<TaskSubmissionEntity>> =
        taskSubmissionDao.getSubmissionsForStudent(studentId)

    suspend fun recordTaskSubmission(
        taskId: String,
        studentId: String,
        studentName: String,
        score: Int,
        timestamp: Long = System.currentTimeMillis() / 1000
    ) {
        val submission = TaskSubmissionEntity(
            taskId = taskId,
            studentId = studentId,
            studentName = studentName,
            score = score,
            timestamp = timestamp,
            isMarked = true
        )
        taskSubmissionDao.insertSubmission(submission)
    }

    suspend fun getSubmission(taskId: String, studentId: String): TaskSubmissionEntity? {
        return taskSubmissionDao.getSubmission(taskId, studentId)
    }

    // --- SYNC RECORDS ---
    suspend fun getSyncRecord(studentId: String, classroomId: String): SyncRecordEntity? {
        return syncRecordDao.getSyncRecord(studentId, classroomId)
    }

    suspend fun updateSyncRecord(studentId: String, classroomId: String, scheduleTime: Long, taskTime: Long) {
        val record = SyncRecordEntity(
            studentId = studentId,
            classroomId = classroomId,
            lastScheduleSyncedAt = scheduleTime,
            lastTaskSyncedAt = taskTime
        )
        syncRecordDao.insertOrUpdateSyncRecord(record)
    }

    // --- TASK DELIVERIES ---
    fun getDeliveriesForTask(taskId: String): Flow<List<com.example.data.db.TaskDeliveryEntity>> =
        database.taskDeliveryDao().getDeliveriesForTask(taskId)

    suspend fun markTaskAsReceived(taskId: String, studentId: String) {
        val delivery = com.example.data.db.TaskDeliveryEntity(taskId, studentId, received = true)
        database.taskDeliveryDao().insertDelivery(delivery)
    }

    suspend fun hasStudentReceivedTask(taskId: String, studentId: String): Boolean {
        return database.taskDeliveryDao().getDelivery(taskId, studentId)?.received ?: false
    }

    // --- EXPORT FUNCTIONALITY ---
    suspend fun exportAttendanceCsv(classroomId: String, attendanceList: List<AttendanceEntity>): String {
        val sb = StringBuilder()
        sb.append("Attendance Log Export\n")
        sb.append("Classroom ID: $classroomId\n")
        sb.append("Export Date: ${SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US).format(Date())}\n\n")
        sb.append("Date,Student ID,Student Name,Status,Timestamp\n")

        for (att in attendanceList) {
            val formattedTime = SimpleDateFormat("HH:mm:ss", Locale.US).format(Date(att.timestamp * 1000))
            sb.append("${att.date},${att.studentId},\"${att.studentName}\",${att.status},$formattedTime\n")
        }
        return sb.toString()
    }

    suspend fun exportTaskSubmissionsCsv(task: TaskEntity, submissions: List<TaskSubmissionEntity>): String {
        val sb = StringBuilder()
        sb.append("Task Submissions Export\n")
        sb.append("Task Title: \"${task.title}\"\n")
        sb.append("Export Date: ${SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US).format(Date())}\n\n")
        sb.append("No.,Student Name,LRN,Score,Submission Date\n")

        var count = 1
        for (sub in submissions) {
            val studentLrn = approvedStudentDao.getApprovedStudentGlobally(sub.studentId)?.lrn
                ?: studentProfileDao.getStudentProfileById(sub.studentId)?.lrn
                ?: ""
            val formattedDate = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US).format(Date(sub.timestamp * 1000))
            val scoreDisplay = if (task.maxScore > 0) "${sub.score}/${task.maxScore}" else "Submitted"
            sb.append("$count,${CsvExportUtils.escapeCsv(sub.studentName)},${CsvExportUtils.formatLrnForCsv(studentLrn)},${CsvExportUtils.escapeCsv(scoreDisplay)},$formattedDate\n")
            count++
        }
        return sb.toString()
    }
}
