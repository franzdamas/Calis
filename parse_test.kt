fun parseTimeToMinutes(raw: String): Int {
    val trimmed = raw.trim().uppercase()
    if (trimmed.isEmpty()) return -1
    
    // check if it contains AM/PM
    val isPM = trimmed.contains("PM")
    val isAM = trimmed.contains("AM")
    val clean = trimmed.replace("AM", "").replace("PM", "").trim()
    
    val parts = clean.split(":")
    var hour = 0
    var min = 0
    if (parts.size >= 2) {
        hour = parts[0].toIntOrNull() ?: 0
        min = parts[1].toIntOrNull() ?: 0
    } else if (clean.length >= 3) {
        hour = clean.dropLast(2).toIntOrNull() ?: 0
        min = clean.takeLast(2).toIntOrNull() ?: 0
    }
    
    if (isPM && hour < 12) hour += 12
    if (isAM && hour == 12) hour = 0
    
    return hour * 60 + min
}

fun isCurrentTimeHighlighted(rawTime: String): Boolean {
    // not used standalone, will use range
    return false
}
