package com.example

import com.example.data.db.ApprovedStudentEntity
import com.example.data.db.AttendanceEntity
import com.example.data.db.ClassroomEntity
import com.example.data.db.TaskEntity
import com.example.data.db.TaskSubmissionEntity
import com.example.util.CsvExportUtils
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CsvExportUtilsTest {

    @Test
    fun attendanceExport_includesExactDaysInColumns_andSeparatesMaleAndFemaleAlphabeticallyWithoutSummaries() {
        val classroom = ClassroomEntity(
            cid = "CR1",
            teacherId = "T1",
            classroomName = "Grade 10 - Emerald",
            teacherName = "Ms. Santos",
            days = "M-F",
            startTime = "08:00",
            graceTime = "08:15",
            endTime = "17:00"
        )
        val students = listOf(
            ApprovedStudentEntity(classroomId = "CR1", studentId = "ST_BOY2", studentName = "Zachary Cruz", sex = "Male", lrn = "123456789012"),
            ApprovedStudentEntity(classroomId = "CR1", studentId = "ST_BOY1", studentName = "Adam Brown", sex = "Male", lrn = "123456789013"),
            ApprovedStudentEntity(classroomId = "CR1", studentId = "ST_GIRL2", studentName = "Zoe Diaz", sex = "Female", lrn = "123456789014"),
            ApprovedStudentEntity(classroomId = "CR1", studentId = "ST_GIRL1", studentName = "Bella Adams", sex = "Female", lrn = "123456789015")
        )
        val logs = listOf(
            AttendanceEntity(classroomId = "CR1", studentId = "ST_BOY1", studentName = "Adam Brown", status = "PRESENT", date = "2025-01-10", timestamp = 1736496000),
            AttendanceEntity(classroomId = "CR1", studentId = "ST_BOY2", studentName = "Zachary Cruz", status = "LATE", date = "2025-01-10", timestamp = 1736496100),
            AttendanceEntity(classroomId = "CR1", studentId = "ST_GIRL1", studentName = "Bella Adams", status = "PRESENT", date = "2025-01-11", timestamp = 1736582400),
            AttendanceEntity(classroomId = "CR1", studentId = "ST_GIRL2", studentName = "Zoe Diaz", status = "ABSENT", date = "2025-01-11", timestamp = 1736582500)
        )

        val config = CsvExportUtils.AttendanceExportConfig(
            selectedMonths = setOf("2025-01"),
            isMatrixLayout = true
        )

        val csv = CsvExportUtils.generateAttendanceCsv(classroom, students, logs, config)

        // Month & Year header, No., Present, Absent, Late, Student Name, LRN headers must be present
        assertTrue("Contains Month & Year header", csv.contains("Month & Year: January 2025"))
        assertTrue("Contains column header", csv.contains("No.,Present,Absent,Late,Student Name,LRN"))
        assertTrue("Contains day column 10", csv.contains(",10"))
        assertTrue("Contains day column 11", csv.contains(",11"))

        // Separated by Male and Female sections without count
        assertTrue("Contains Male header", csv.contains("MALE\n"))
        assertTrue("Contains Female header", csv.contains("FEMALE\n"))

        // Student row contains Name followed by LRN formatted for Excel text string
        assertTrue("Contains student name and LRN formatted for Excel", csv.contains("Adam Brown,=\"123456789013\""))

        // Adam should appear before Zachary in Male section
        val adamIndex = csv.indexOf("Adam Brown")
        val zacharyIndex = csv.indexOf("Zachary Cruz")
        val bellaIndex = csv.indexOf("Bella Adams")
        val zoeIndex = csv.indexOf("Zoe Diaz")

        assertTrue("Adam before Zachary", adamIndex < zacharyIndex)
        assertTrue("Boys before Girls", zacharyIndex < bellaIndex)
        assertTrue("Bella before Zoe", bellaIndex < zoeIndex)

        // Verifying presence markers: Present, Late, Absent
        assertTrue("Contains Present marker", csv.contains("Present"))
        assertTrue("Contains Late marker", csv.contains("Late"))

        // Summaries and averages must be completely absent
        assertFalse("No Total Present", csv.contains("Total Present"))
        assertFalse("No Total Late", csv.contains("Total Late"))
        assertFalse("No Total Absent", csv.contains("Total Absent"))
        assertFalse("No Attendance Rate", csv.contains("Attendance Rate"))
        assertFalse("No % symbol in attendance", csv.contains("%"))
    }

    @Test
    fun tasksExport_organizesTasksByColumn_andSeparatesMaleAndFemaleAlphabeticallyWithoutSummariesOrAverages() {
        val classroom = ClassroomEntity(
            cid = "CR1",
            teacherId = "T1",
            classroomName = "Grade 10 - Emerald",
            teacherName = "Ms. Santos",
            days = "M-F",
            startTime = "08:00",
            graceTime = "08:15",
            endTime = "17:00"
        )
        val students = listOf(
            ApprovedStudentEntity(classroomId = "CR1", studentId = "ST_BOY1", studentName = "Charlie Davis", sex = "Male", lrn = "123456789012"),
            ApprovedStudentEntity(classroomId = "CR1", studentId = "ST_GIRL1", studentName = "Alice Evans", sex = "Female", lrn = "123456789013")
        )
        val task1 = TaskEntity(tkid = "TK1", classroomId = "CR1", title = "Quiz 1", description = "Test", deadline = 1736496000, datePosted = 1736400000, maxScore = 50)
        val task2 = TaskEntity(tkid = "TK2", classroomId = "CR1", title = "Project 1", description = "App", deadline = 1736596000, datePosted = 1736400000, maxScore = 100)

        val submissions = mapOf(
            "TK1" to listOf(
                TaskSubmissionEntity(taskId = "TK1", studentId = "ST_BOY1", studentName = "Charlie Davis", score = 45, timestamp = 1736496000),
                TaskSubmissionEntity(taskId = "TK1", studentId = "ST_GIRL1", studentName = "Alice Evans", score = 50, timestamp = 1736496000)
            ),
            "TK2" to listOf(
                TaskSubmissionEntity(taskId = "TK2", studentId = "ST_BOY1", studentName = "Charlie Davis", score = 90, timestamp = 1736496000),
                TaskSubmissionEntity(taskId = "TK2", studentId = "ST_GIRL1", studentName = "Alice Evans", score = 95, timestamp = 1736496000)
            )
        )

        val config = CsvExportUtils.TasksExportConfig()

        val csv = CsvExportUtils.generateTasksCsv(classroom, students, listOf(task1, task2), submissions, config)

        // Columns for each task and LRN header
        assertTrue("Contains column header", csv.contains("No.,Student Name,LRN"))
        assertTrue("Contains Quiz 1 column", csv.contains(",Quiz 1,"))
        assertTrue("Contains Project 1 column", csv.contains(",Project 1"))
        assertTrue("Contains score 45", csv.contains(",45,"))
        assertTrue("Contains score 90", csv.contains(",90"))

        // Section headers without count
        assertTrue("Contains Male section", csv.contains("MALE\n"))
        assertTrue("Contains Female section", csv.contains("FEMALE\n"))

        // Summaries and averages must be completely absent
        assertFalse("No Total Score column", csv.contains("Total Score"))
        assertFalse("No Percentage column", csv.contains("Percentage"))
        assertFalse("No CLASS AVERAGE row", csv.contains("CLASS AVERAGE"))
        assertFalse("No % symbol in tasks", csv.contains("%"))
    }

    @Test
    fun attendanceExport_respectsCustomColumnOrder() {
        val classroom = ClassroomEntity(
            cid = "CR1",
            teacherId = "T1",
            classroomName = "Grade 10 - Emerald",
            teacherName = "Ms. Santos",
            days = "M-F",
            startTime = "08:00",
            graceTime = "08:15",
            endTime = "17:00"
        )
        val students = listOf(
            ApprovedStudentEntity(classroomId = "CR1", studentId = "ST_1", studentName = "John Doe", sex = "Male", lrn = "123456789012")
        )
        val logs = listOf(
            AttendanceEntity(classroomId = "CR1", studentId = "ST_1", studentName = "John Doe", status = "PRESENT", date = "2025-01-10", timestamp = 1736496000)
        )

        // Custom order: Student Name, LRN, No., Present, Absent, Late, [Date]
        val customOrder = listOf(
            CsvExportUtils.AttendanceColumnKey.STUDENT_NAME,
            CsvExportUtils.AttendanceColumnKey.LRN,
            CsvExportUtils.AttendanceColumnKey.NO,
            CsvExportUtils.AttendanceColumnKey.PRESENT,
            CsvExportUtils.AttendanceColumnKey.ABSENT,
            CsvExportUtils.AttendanceColumnKey.LATE,
            CsvExportUtils.AttendanceColumnKey.DATES
        )

        val config = CsvExportUtils.AttendanceExportConfig(
            selectedMonths = setOf("2025-01"),
            columnOrder = customOrder
        )

        val csv = CsvExportUtils.generateAttendanceCsv(classroom, students, logs, config)

        assertTrue("Contains custom header order", csv.contains("Student Name,LRN,No.,Present,Absent,Late,10"))
        assertTrue("Contains custom row data order", csv.contains("John Doe,=\"123456789012\",1,1,0,0,Present"))
    }
}
