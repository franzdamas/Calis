package com.example.util

import com.example.data.db.ApprovedStudentEntity
import com.example.data.db.AttendanceEntity
import com.example.data.db.ClassroomEntity
import com.example.data.db.TaskEntity
import com.example.data.db.TaskSubmissionEntity
import com.example.util.AttendanceLabels
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CsvExportUtils {

    fun isBoySex(sex: String?): Boolean {
        val s = sex?.trim()?.lowercase(Locale.getDefault()) ?: return false
        if (s == "m" || s == "male" || s == "boy" || s.startsWith("male") && !s.startsWith("female") || s.contains("boy")) {
            return !s.contains("female")
        }
        return false
    }

    fun isGirlSex(sex: String?): Boolean {
        val s = sex?.trim()?.lowercase(Locale.getDefault()) ?: return false
        return s == "f" || s == "female" || s == "girl" || s.contains("female") || s.contains("girl")
    }

    /**
     * Extracts month key "yyyy-MM" (e.g. "2026-09") from an attendance entity
     */
    fun getMonthKey(att: AttendanceEntity): String {
        val rawDate = att.date.trim()
        if (rawDate.length >= 7 && rawDate[4] == '-') {
            return rawDate.substring(0, 7)
        }
        val millis = if (att.timestamp < 100_000_000_000L) att.timestamp * 1000 else att.timestamp
        if (millis > 0) {
            return SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(Date(millis))
        }
        try {
            val parsed = SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(rawDate.split(" ")[0])
            if (parsed != null) {
                return SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(parsed)
            }
        } catch (_: Exception) {}
        return "Unknown"
    }

    fun formatMonthDisplay(monthKey: String): String {
        try {
            val sdf = SimpleDateFormat("yyyy-MM", Locale.getDefault())
            val date = sdf.parse(monthKey)
            if (date != null) {
                return SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(date)
            }
        } catch (_: Exception) {}
        return monthKey
    }

    fun getNormalizedDate(att: AttendanceEntity): String {
        val rawDate = att.date.trim()
        if (rawDate.length >= 10 && rawDate[4] == '-' && rawDate[7] == '-') {
            return rawDate.substring(0, 10)
        }
        val millis = if (att.timestamp < 100_000_000_000L) att.timestamp * 1000 else att.timestamp
        if (millis > 0) {
            return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date(millis))
        }
        try {
            val parsed = SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(rawDate.split(" ")[0])
            if (parsed != null) {
                return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(parsed)
            }
        } catch (_: Exception) {}
        return rawDate
    }

    fun escapeCsv(value: String): String {
        return if (value.contains(",") || value.contains("\"") || value.contains("\n") || value.contains("\r")) {
            "\"" + value.replace("\"", "\"\"") + "\""
        } else {
            value
        }
    }

    fun formatLrnForCsv(lrn: String): String {
        val trimmed = lrn.trim()
        if (trimmed.isEmpty()) return ""
        // Prevents Excel from converting 12-digit LRN into scientific notation (e.g. 1.23457E+11)
        return "=\"$trimmed\""
    }

    data class StudentItem(
        val studentId: String,
        val studentName: String,
        val sex: String,
        val lrn: String = ""
    )

    fun partitionStudentsBySex(
        approvedStudents: List<ApprovedStudentEntity>,
        additionalStudents: List<Pair<String, String>> = emptyList()
    ): Pair<List<StudentItem>, List<StudentItem>> {
        val map = mutableMapOf<String, StudentItem>()
        for (s in approvedStudents) {
            map[s.studentId] = StudentItem(
                studentId = s.studentId,
                studentName = s.studentName.trim(),
                sex = if (isBoySex(s.sex)) "Male" else if (isGirlSex(s.sex)) "Female" else s.sex.trim(),
                lrn = s.lrn.trim()
            )
        }
        for ((id, name) in additionalStudents) {
            if (!map.containsKey(id)) {
                map[id] = StudentItem(
                    studentId = id,
                    studentName = name.trim(),
                    sex = "Unspecified"
                )
            }
        }

        val boys = map.values
            .filter { isBoySex(it.sex) }
            .sortedBy { it.studentName.lowercase(Locale.getDefault()) }

        val girls = map.values
            .filter { isGirlSex(it.sex) }
            .sortedBy { it.studentName.lowercase(Locale.getDefault()) }

        val others = map.values
            .filter { !isBoySex(it.sex) && !isGirlSex(it.sex) }
            .sortedBy { it.studentName.lowercase(Locale.getDefault()) }

        return Pair(boys, girls + others)
    }

    enum class AttendanceColumnKey(val displayName: String) {
        NO("No."),
        PRESENT("Present"),
        ABSENT("Absent"),
        LATE("Late"),
        STUDENT_NAME("Student Name"),
        LRN("LRN"),
        DATES("[Date]");

        companion object {
            val DEFAULT_ORDER = listOf(NO, PRESENT, ABSENT, LATE, STUDENT_NAME, LRN, DATES)
        }
    }

    data class AttendanceExportConfig(
        val selectedMonths: Set<String>,
        val isMatrixLayout: Boolean = true,
        val presentLabel: String = AttendanceLabels.present,
        val absentLabel: String = AttendanceLabels.absent,
        val lateLabel: String = AttendanceLabels.late,
        val columnOrder: List<AttendanceColumnKey> = AttendanceColumnKey.DEFAULT_ORDER
    )

    fun generateAttendanceCsv(
        classroom: ClassroomEntity?,
        approvedStudents: List<ApprovedStudentEntity>,
        allLogs: List<AttendanceEntity>,
        config: AttendanceExportConfig
    ): String {
        // Filter logs by selected months
        val filteredLogs = if (config.selectedMonths.isEmpty()) {
            allLogs
        } else {
            allLogs.filter { getMonthKey(it) in config.selectedMonths }
        }

        // Always include all approved students and gather additional from logs
        val additional = filteredLogs.map { it.studentId to it.studentName }
        val (boys, girls) = partitionStudentsBySex(approvedStudents, additional)

        val sb = StringBuilder()
        val nowFormatted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val selectedMonthsDisplay = if (config.selectedMonths.isEmpty()) "All Months" else config.selectedMonths.sorted().joinToString(", ") { formatMonthDisplay(it) }

        sb.append("ATTENDANCE LOG & REGISTER EXPORT\n")
        sb.append("Classroom / Section: ${escapeCsv(classroom?.classroomName ?: "Classroom")}\n")
        sb.append("Teacher: ${escapeCsv(classroom?.teacherName ?: "Teacher")}\n")
        sb.append("Export Date: $nowFormatted\n")
        sb.append("Month & Year: $selectedMonthsDisplay\n")
        sb.append("Student Counts: Total=${boys.size + girls.size}, Male=${boys.size}, Female=${girls.size}\n\n")

        // MATRIX LAYOUT: Day (DD) as Columns
        val uniqueDates = filteredLogs.map { getNormalizedDate(it) }.distinct().sorted()

        // Build lookup: (studentId, date) -> latest status
        val statusLookup = mutableMapOf<Pair<String, String>, String>()
        for (log in filteredLogs) {
            val normDate = getNormalizedDate(log)
            val status = log.status.uppercase(Locale.getDefault())
            statusLookup[Pair(log.studentId, normDate)] = status
        }

        val effectiveColumnOrder = if (config.columnOrder.isEmpty()) AttendanceColumnKey.DEFAULT_ORDER else config.columnOrder

        val pLabel = config.presentLabel.ifBlank { AttendanceLabels.present.ifBlank { "Present" } }
        val aLabel = config.absentLabel.ifBlank { AttendanceLabels.absent.ifBlank { "Absent" } }
        val lLabel = config.lateLabel.ifBlank { AttendanceLabels.late.ifBlank { "Late" } }

        // Headers based on effectiveColumnOrder
        val headerCells = mutableListOf<String>()
        for (key in effectiveColumnOrder) {
            when (key) {
                AttendanceColumnKey.NO -> headerCells.add("No.")
                AttendanceColumnKey.PRESENT -> headerCells.add(escapeCsv(pLabel))
                AttendanceColumnKey.ABSENT -> headerCells.add(escapeCsv(aLabel))
                AttendanceColumnKey.LATE -> headerCells.add(escapeCsv(lLabel))
                AttendanceColumnKey.STUDENT_NAME -> headerCells.add("Student Name")
                AttendanceColumnKey.LRN -> headerCells.add("LRN")
                AttendanceColumnKey.DATES -> {
                    for (d in uniqueDates) {
                        val dayOnly = if (d.length >= 10 && d[4] == '-' && d[7] == '-') d.substring(8) else d.split("-").last()
                        headerCells.add(escapeCsv(dayOnly))
                    }
                }
            }
        }
        sb.append(headerCells.joinToString(",")).append("\n")

        fun formatStatusCode(status: String?): String {
            if (status == null || status.equals("UNMARKED", ignoreCase = true)) return "N/A"
            return when (status.uppercase(Locale.getDefault())) {
                "PRESENT" -> pLabel
                "LATE" -> lLabel
                "ABSENT" -> aLabel
                else -> status
            }
        }

        fun appendStudentRows(groupName: String, students: List<StudentItem>): Int {
            if (students.isEmpty()) return 0
            sb.append("${groupName.uppercase(Locale.getDefault())}\n")
            var currentNum = 1
            for (st in students) {
                var presentCount = 0
                var absentCount = 0
                var lateCount = 0

                val dateStatuses = mutableListOf<String>()
                for (d in uniqueDates) {
                    val stStatus = statusLookup[Pair(st.studentId, d)]
                    dateStatuses.add(escapeCsv(formatStatusCode(stStatus)))

                    when (stStatus?.uppercase(Locale.getDefault())) {
                        "PRESENT" -> presentCount++
                        "ABSENT" -> absentCount++
                        "LATE" -> lateCount++
                    }
                }

                val row = mutableListOf<String>()
                for (key in effectiveColumnOrder) {
                    when (key) {
                        AttendanceColumnKey.NO -> row.add(currentNum.toString())
                        AttendanceColumnKey.PRESENT -> row.add(presentCount.toString())
                        AttendanceColumnKey.ABSENT -> row.add(absentCount.toString())
                        AttendanceColumnKey.LATE -> row.add(lateCount.toString())
                        AttendanceColumnKey.STUDENT_NAME -> row.add(escapeCsv(st.studentName))
                        AttendanceColumnKey.LRN -> row.add(formatLrnForCsv(st.lrn))
                        AttendanceColumnKey.DATES -> row.addAll(dateStatuses)
                    }
                }

                sb.append(row.joinToString(",")).append("\n")
                currentNum++
            }
            return students.size
        }

        // 1. Male / Boys first (alphabetical by name)
        appendStudentRows("Male", boys)

        // 2. Female / Girls second (alphabetical by name)
        appendStudentRows("Female", girls)

        return sb.toString()
    }

    class TasksExportConfig

    fun generateTasksCsv(
        classroom: ClassroomEntity?,
        approvedStudents: List<ApprovedStudentEntity>,
        selectedTasks: List<TaskEntity>,
        submissionsMap: Map<String, List<TaskSubmissionEntity>>, // taskId -> submissions
        config: TasksExportConfig = TasksExportConfig()
    ): String {
        // Gather additional students from submissions
        val additional = mutableListOf<Pair<String, String>>()
        for (subs in submissionsMap.values) {
            for (sub in subs) {
                additional.add(sub.studentId to sub.studentName)
            }
        }

        // Partition into Male then Female, each sorted alphabetically (A-Z)
        val (boys, girls) = partitionStudentsBySex(approvedStudents, additional)

        val sb = StringBuilder()
        val nowFormatted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        sb.append("CLASSROOM TASKS & GRADEBOOK EXPORT\n")
        sb.append("Classroom / Section: ${escapeCsv(classroom?.classroomName ?: "Classroom")}\n")
        sb.append("Teacher: ${escapeCsv(classroom?.teacherName ?: "Teacher")}\n")
        sb.append("Export Date: $nowFormatted\n")
        sb.append("Included Tasks: ${selectedTasks.size}\n")
        sb.append("Student Counts: Total=${boys.size + girls.size}, Male=${boys.size}, Female=${girls.size}\n\n")

        // Columns:
        // No., Student Name, LRN, [Task 1], [Task 2], ...
        val headerCells = mutableListOf("No.", "Student Name", "LRN")
        for (task in selectedTasks) {
            headerCells.add(escapeCsv(task.title))
        }
        sb.append(headerCells.joinToString(",")).append("\n")

        fun appendStudentRows(groupName: String, students: List<StudentItem>): Int {
            if (students.isEmpty()) return 0
            sb.append("${groupName.uppercase(Locale.getDefault())}\n")
            var num = 1
            for (st in students) {
                val row = mutableListOf<String>()
                row.add(num.toString())
                row.add(escapeCsv(st.studentName))
                row.add(formatLrnForCsv(st.lrn))

                for (i in selectedTasks.indices) {
                    val task = selectedTasks[i]
                    val sub = submissionsMap[task.tkid]?.find { it.studentId == st.studentId }
                    if (sub != null) {
                        if (task.maxScore > 0) {
                            row.add("${sub.score}")
                        } else {
                            row.add("Submitted")
                        }
                    } else {
                        row.add("") // Keep BLANK for students who haven't submitted!
                    }
                }

                sb.append(row.joinToString(",")).append("\n")
                num++
            }
            return students.size
        }

        // 1. Male first (sorted alphabetically A-Z)
        appendStudentRows("Male", boys)

        // 2. Female second (sorted alphabetically A-Z)
        appendStudentRows("Female", girls)

        return sb.toString()
    }
}
