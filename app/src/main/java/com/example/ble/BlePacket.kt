package com.example.ble

sealed class BlePacket {
    // Teacher Commands
    data class RegCommand(val cid: String, val tid: String, val cls: String, val tc: String) : BlePacket() {
        fun encode(): String = "REG|$cid|$tid|$cls|$tc"
    }

    data class AuthCommand(val tid: String, val cid: String) : BlePacket() {
        fun encode(): String = "AUTH|$tid|$cid"
    }

    object StartSessionCommand : BlePacket() {
        fun encode(): String = "START_SESSION"
    }

    object EndSessionCommand : BlePacket() {
        fun encode(): String = "END_SESSION"
    }

    data class TaskCommand(
        val tkid: String,
        val title: String,
        val desc: String,
        val deadline: Long,
        val datePosted: Long,
        val score: String, // 3 digits e.g. "100" or "000"
        val rid: Int
    ) : BlePacket() {
        fun encode(): String {
            val effectiveDesc = if (desc.isBlank()) title else desc
            return "TK|$tkid|$title|$effectiveDesc|$deadline|$datePosted|$score|$rid"
        }
    }

    object UnregCommand : BlePacket() {
        fun encode(): String = "UNREG"
    }

    // Student Requests
    data class StudentRegRequest(
        val sid: String,
        val st: String,
        val sex: String,
        val lrn: String,
        val birth: Long,
        val rid: Int = 0
    ) : BlePacket() {
        fun encodeForDevice(): String = if (rid != 0) "ST_REG|$sid|$st|$sex|$lrn|$birth|$rid" else "ST_REG|$sid|$st|$sex|$lrn|$birth"
        fun encodeWithRid(): String = "ST_REG|$sid|$st|$sex|$lrn|$birth|$rid"
    }

    data class CheckInRequest(
        val sid: String,
        val rid: Int = 0
    ) : BlePacket() {
        fun encodeForDevice(): String = if (rid != 0) "CHK_IN|$sid|$rid" else "CHK_IN|$sid"
        fun encodeWithRid(): String = "CHK_IN|$sid|$rid"
    }

    data class TaskSubmissionRequest(
        val sid: String,
        val tkid: String,
        val score: String,
        val rid: Int = 0
    ) : BlePacket() {
        fun encodeForDevice(): String = if (rid != 0) "TK_SBM|$sid|$tkid|$score|$rid" else "TK_SBM|$sid|$tkid|$score"
        fun encodeWithRid(): String = "TK_SBM|$sid|$tkid|$score|$rid"
    }

    data class RequestUpdateRequest(
        val sid: String,
        val rid: Int = 0
    ) : BlePacket() {
        fun encodeForDevice(): String = if (rid != 0) "REQ_UPD|$sid|$rid" else "REQ_UPD|$sid"
        fun encodeWithRid(): String = "REQ_UPD|$sid|$rid"
    }

    // Device Responses to Teacher
    object RegisteredResponse : BlePacket()
    object UnregisteredResponse : BlePacket()
    object AuthOkResponse : BlePacket()
    object AuthFailedResponse : BlePacket()

    // Teacher Responses to Student (Forwarded via Device)
    data class RegApprovedResponse(
        val cid: String,
        val cls: String,
        val tc: String,
        val days: String,
        val start: String,
        val grace: String,
        val end: String,
        val rid: Int
    ) : BlePacket() {
        fun encode(): String = "REG_APPROVED|$cid|$cls|$tc|$days|$start|$grace|$end|$rid"
    }

    data class RegRejectedResponse(val rid: Int) : BlePacket() {
        fun encode(): String = "REG_REJECTED|$rid"
    }

    data class AtdAcceptedResponse(val timestamp: Long, val rid: Int) : BlePacket() {
        fun encode(): String = "ATD_ACCEPTED|$timestamp|$rid"
    }

    data class AtdRejectedResponse(val rid: Int) : BlePacket() {
        fun encode(): String = "ATD_REJECTED|$rid"
    }

    data class MarkedResponse(val timestamp: Long, val rid: Int) : BlePacket() {
        fun encode(): String = "MARKED|$timestamp|$rid"
    }

    data class SchedResponse(
        val days: String,
        val start: String,
        val grace: String,
        val end: String,
        val rid: Int
    ) : BlePacket() {
        fun encode(): String = "SCHED|$days|$start|$grace|$end|$rid"
    }

    data class DoneResponse(val rid: Int) : BlePacket() {
        fun encode(): String = "DONE|$rid"
    }

    data class UnknownPacket(val raw: String) : BlePacket()
}
