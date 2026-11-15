package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        TeacherEntity::class,
        StudentProfileEntity::class,
        ClassroomEntity::class,
        ApprovedStudentEntity::class,
        PendingRegistrationEntity::class,
        AttendanceEntity::class,
        TaskEntity::class,
        TaskSubmissionEntity::class,
        SyncRecordEntity::class,
        TaskDeliveryEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class CalisDatabase : RoomDatabase() {

    abstract fun teacherDao(): TeacherDao
    abstract fun studentProfileDao(): StudentProfileDao
    abstract fun classroomDao(): ClassroomDao
    abstract fun approvedStudentDao(): ApprovedStudentDao
    abstract fun pendingRegistrationDao(): PendingRegistrationDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun taskDao(): TaskDao
    abstract fun taskSubmissionDao(): TaskSubmissionDao
    abstract fun syncRecordDao(): SyncRecordDao
    abstract fun taskDeliveryDao(): TaskDeliveryDao

    companion object {
        @Volatile
        private var INSTANCE: CalisDatabase? = null

        fun getDatabase(context: Context): CalisDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CalisDatabase::class.java,
                    "calis_mobile_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
