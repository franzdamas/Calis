package com.example.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object ScheduleUtils {

    fun getTodayDateString(): String {
        return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date())
    }

    fun isTodayInSchedule(daysString: String): Boolean {
        if (daysString.isBlank()) return true
        return isDayInSchedule(Calendar.getInstance(), daysString)
    }

    fun isDayInSchedule(calendar: Calendar, daysString: String): Boolean {
        if (daysString.isBlank()) return true
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val (abbr, fullName) = when (dayOfWeek) {
            Calendar.MONDAY -> "MON" to "Monday"
            Calendar.TUESDAY -> "TUE" to "Tuesday"
            Calendar.WEDNESDAY -> "WED" to "Wednesday"
            Calendar.THURSDAY -> "THU" to "Thursday"
            Calendar.FRIDAY -> "FRI" to "Friday"
            Calendar.SATURDAY -> "SAT" to "Saturday"
            Calendar.SUNDAY -> "SUN" to "Sunday"
            else -> "" to ""
        }
        return isDayInSchedule(abbr, fullName, daysString)
    }

    fun isDayInSchedule(abbr: String, fullName: String, daysString: String): Boolean {
        val upper = daysString.uppercase().trim()
        if (upper.isEmpty() || upper == "N/A" || upper == "--" || upper == "NONE" || upper == "ANY" || upper == "N / A") return true

        if (upper.contains("MON-FRI") || upper.contains("MON - FRI") || upper.contains("MONDAY TO FRIDAY") || upper.contains("MONDAY - FRIDAY")) {
            return abbr in listOf("MON", "TUE", "WED", "THU", "FRI")
        }
        if (upper.contains("MON-SAT") || upper.contains("MON - SAT") || upper.contains("MONDAY TO SATURDAY")) {
            return abbr in listOf("MON", "TUE", "WED", "THU", "FRI", "SAT")
        }
        if (upper.contains("DAILY") || upper.contains("EVERYDAY") || upper.contains("MON-SUN") || upper.contains("ALL DAYS")) {
            return true
        }

        if (upper.contains(abbr) || upper.contains(fullName.uppercase())) {
            return true
        }

        // Handle list tokens (e.g. M-T-W-TH-F, M-W-F, T-TH, MON/WED/FRI, MON, WED, FRI)
        val tokens = upper.split(Regex("[-/,\\s]+")).map { it.trim() }.filter { it.isNotEmpty() }
        for (token in tokens) {
            when (token) {
                "M", "MON", "MONDAY" -> if (abbr == "MON") return true
                "T", "TU", "TUE", "TUES", "TUESDAY" -> if (abbr == "TUE") return true
                "W", "WED", "WEDNESDAY" -> if (abbr == "WED") return true
                "TH", "THU", "THUR", "THURSDAY" -> if (abbr == "THU") return true
                "F", "FRI", "FRIDAY" -> if (abbr == "FRI") return true
                "SA", "SAT", "SATURDAY" -> if (abbr == "SAT") return true
                "SU", "SUN", "SUNDAY" -> if (abbr == "SUN") return true
            }
        }

        if (upper.contains("-")) {
            val parts = upper.split("-").map { it.trim() }
            if (parts.size == 2) {
                val dayOrder = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
                val startIdx = dayOrder.indexOfFirst { parts[0].startsWith(it) || it.startsWith(parts[0]) }
                val endIdx = dayOrder.indexOfFirst { parts[1].startsWith(it) || it.startsWith(parts[1]) }
                val currentIdx = dayOrder.indexOf(abbr)
                if (startIdx != -1 && endIdx != -1 && currentIdx != -1) {
                    if (startIdx <= endIdx) {
                        if (currentIdx in startIdx..endIdx) return true
                    } else {
                        if (currentIdx >= startIdx || currentIdx <= endIdx) return true
                    }
                }
            }
        }

        if (abbr == "MON" && (upper.contains("M,") || upper.contains("M-") || upper.startsWith("M"))) return true
        if (abbr == "TUE" && (upper.contains("T,") || upper.contains("TU"))) return true
        if (abbr == "WED" && (upper.contains("W,") || upper.contains("W-"))) return true
        if (abbr == "THU" && (upper.contains("TH,") || upper.contains("TH"))) return true
        if (abbr == "FRI" && (upper.contains("F,") || upper.contains("F-"))) return true

        return false
    }

    fun formatDisplayTime(raw: String): String {
        if (raw.isBlank()) return "--:--"
        val trimmed = raw.trim()
        if (trimmed.contains("AM", ignoreCase = true) || trimmed.contains("PM", ignoreCase = true)) {
            return trimmed
        }
        if (trimmed.length == 4 && trimmed.all { it.isDigit() }) {
            val hour = trimmed.substring(0, 2).toIntOrNull() ?: 0
            val min = trimmed.substring(2, 4)
            val period = if (hour < 12) "AM" else "PM"
            val displayHour = when (hour) {
                0 -> 12
                in 1..12 -> hour
                else -> hour - 12
            }
            return String.format(Locale.US, "%d:%s %s", displayHour, min, period)
        }
        return raw
    }
}
