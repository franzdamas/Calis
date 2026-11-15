package com.example.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "teachers")
data class TeacherEntity(
    @PrimaryKey val tid: String,
    val fullName: String,
    val sex: String = "M",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "student_profiles")
data class StudentProfileEntity(
    @PrimaryKey val sid: String,
    val fullName: String,
    val sex: String,
    val lrn: String,
    val birth: Long,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "classrooms",
    foreignKeys = [
        ForeignKey(
            entity = TeacherEntity::class,
            parentColumns = ["tid"],
            childColumns = ["teacherId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("teacherId")]
)
data class ClassroomEntity(
    @PrimaryKey val cid: String,
    val teacherId: String,
    val classroomName: String,
    val teacherName: String,
    val days: String,
    val startTime: String,
    val graceTime: String,
    val endTime: String,
    val isActiveSession: Boolean = false,
    val isBound: Boolean = false,
    val deviceAddress: String? = null
)

@Entity(
    tableName = "approved_students",
    foreignKeys = [
        ForeignKey(
            entity = ClassroomEntity::class,
            parentColumns = ["cid"],
            childColumns = ["classroomId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("classroomId"), Index(value = ["classroomId", "studentId"], unique = true)]
)
data class ApprovedStudentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classroomId: String,
    val studentId: String,
    val studentName: String,
    val sex: String,
    val lrn: String,
    val birth: Long = 0L,
    val approvedAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "pending_registrations",
    foreignKeys = [
        ForeignKey(
            entity = ClassroomEntity::class,
            parentColumns = ["cid"],
            childColumns = ["classroomId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("classroomId")]
)
data class PendingRegistrationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classroomId: String,
    val studentId: String,
    val studentName: String,
    val sex: String,
    val lrn: String,
    val birth: Long,
    val rid: Int,
    val requestedAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "attendance",
    foreignKeys = [
        ForeignKey(
            entity = ClassroomEntity::class,
            parentColumns = ["cid"],
            childColumns = ["classroomId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("classroomId"), Index(value = ["classroomId", "studentId", "date"], unique = true)]
)
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classroomId: String,
    val studentId: String,
    val studentName: String,
    val date: String,
    val status: String, // "PRESENT", "LATE", "ABSENT"
    val timestamp: Long
)

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = ClassroomEntity::class,
            parentColumns = ["cid"],
            childColumns = ["classroomId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("classroomId")]
)
data class TaskEntity(
    @PrimaryKey val tkid: String,
    val classroomId: String,
    val title: String,
    val description: String,
    val deadline: Long,
    val datePosted: Long,
    val maxScore: Int
)

@Entity(
    tableName = "task_submissions",
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["tkid"],
            childColumns = ["taskId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("taskId"), Index(value = ["taskId", "studentId"], unique = true)]
)
data class TaskSubmissionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val taskId: String,
    val studentId: String,
    val studentName: String,
    val score: Int,
    val timestamp: Long,
    val isMarked: Boolean = true
)

@Entity(
    tableName = "sync_records",
    indices = [Index(value = ["studentId", "classroomId"], unique = true)]
)
data class SyncRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val studentId: String,
    val classroomId: String,
    val lastScheduleSyncedAt: Long = 0,
    val lastTaskSyncedAt: Long = 0
)

@Entity(
    tableName = "task_deliveries",
    primaryKeys = ["taskId", "studentId"]
)
data class TaskDeliveryEntity(
    val taskId: String,
    val studentId: String,
    val received: Boolean = false
)
