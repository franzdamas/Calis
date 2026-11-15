package com.example.ble

import android.util.Log

object PacketParser {
    private const val TAG = "PacketParser"

    fun parse(raw: String): BlePacket {
        val trimmed = raw.trim()
        if (trimmed.isEmpty()) return BlePacket.UnknownPacket(raw)

        val parts = trimmed.split("|")
        val cmd = parts[0]

        return try {
            when (cmd) {
                // Device Responses to Teacher
                "REGISTERED" -> BlePacket.RegisteredResponse
                "UNREGISTERED" -> BlePacket.UnregisteredResponse
                "AUTH_OK" -> BlePacket.AuthOkResponse
                "AUTH_FAILED" -> BlePacket.AuthFailedResponse

                // Teacher Commands
                "REG" -> {
                    if (parts.size >= 5) {
                        BlePacket.RegCommand(parts[1], parts[2], parts[3], parts[4])
                    } else BlePacket.UnknownPacket(raw)
                }

                "AUTH" -> {
                    if (parts.size >= 3) {
                        BlePacket.AuthCommand(parts[1], parts[2])
                    } else BlePacket.UnknownPacket(raw)
                }

                "START_SESSION" -> BlePacket.StartSessionCommand
                "END_SESSION" -> BlePacket.EndSessionCommand
                "UNREG" -> BlePacket.UnregCommand

                "TK" -> {
                    if (parts.size >= 8) {
                        BlePacket.TaskCommand(
                            tkid = parts[1],
                            title = parts[2],
                            desc = parts[3],
                            deadline = parts[4].toLongOrNull() ?: 0L,
                            datePosted = parts[5].toLongOrNull() ?: 0L,
                            score = parts[6],
                            rid = parts[7].toIntOrNull() ?: 0
                        )
                    } else if (parts.size >= 7) {
                        BlePacket.TaskCommand(
                            tkid = parts[1],
                            title = parts[2],
                            desc = parts[3],
                            deadline = parts[4].toLongOrNull() ?: 0L,
                            datePosted = parts[5].toLongOrNull() ?: 0L,
                            score = parts[6],
                            rid = 0
                        )
                    } else BlePacket.UnknownPacket(raw)
                }

                // Student Requests
                "ST_REG" -> {
                    if (parts.size >= 7) {
                        BlePacket.StudentRegRequest(
                            sid = parts[1],
                            st = parts[2],
                            sex = parts[3],
                            lrn = parts[4],
                            birth = parts[5].toLongOrNull() ?: 0L,
                            rid = parts[6].toIntOrNull() ?: 0
                        )
                    } else if (parts.size >= 6) {
                        BlePacket.StudentRegRequest(
                            sid = parts[1],
                            st = parts[2],
                            sex = parts[3],
                            lrn = parts[4],
                            birth = parts[5].toLongOrNull() ?: 0L
                        )
                    } else BlePacket.UnknownPacket(raw)
                }

                "CHK_IN" -> {
                    if (parts.size >= 3) {
                        BlePacket.CheckInRequest(parts[1], parts[2].toIntOrNull() ?: 0)
                    } else if (parts.size >= 2) {
                        BlePacket.CheckInRequest(parts[1])
                    } else BlePacket.UnknownPacket(raw)
                }

                "TK_SBM" -> {
                    if (parts.size >= 5) {
                        BlePacket.TaskSubmissionRequest(
                            sid = parts[1],
                            tkid = parts[2],
                            score = parts[3],
                            rid = parts[4].toIntOrNull() ?: 0
                        )
                    } else if (parts.size >= 4) {
                        BlePacket.TaskSubmissionRequest(
                            sid = parts[1],
                            tkid = parts[2],
                            score = parts[3]
                        )
                    } else BlePacket.UnknownPacket(raw)
                }

                "REQ_UPD" -> {
                    if (parts.size >= 3) {
                        BlePacket.RequestUpdateRequest(parts[1], parts[2].toIntOrNull() ?: 0)
                    } else if (parts.size >= 2) {
                        BlePacket.RequestUpdateRequest(parts[1])
                    } else BlePacket.UnknownPacket(raw)
                }

                // Teacher Responses forwarded to Student
                "REG_APPROVED" -> {
                    if (parts.size >= 9) {
                        BlePacket.RegApprovedResponse(
                            cid = parts[1],
                            cls = parts[2],
                            tc = parts[3],
                            days = parts[4],
                            start = parts[5],
                            grace = parts[6],
                            end = parts[7],
                            rid = parts[8].toIntOrNull() ?: 0
                        )
                    } else if (parts.size >= 8) {
                        BlePacket.RegApprovedResponse(
                            cid = parts[1],
                            cls = parts[2],
                            tc = parts[3],
                            days = parts[4],
                            start = parts[5],
                            grace = parts[6],
                            end = parts[7],
                            rid = 0
                        )
                    } else BlePacket.UnknownPacket(raw)
                }

                "REG_REJECTED" -> {
                    if (parts.size >= 2) {
                        BlePacket.RegRejectedResponse(parts[1].toIntOrNull() ?: 0)
                    } else if (parts.size >= 1) {
                        BlePacket.RegRejectedResponse(0)
                    } else BlePacket.UnknownPacket(raw)
                }

                "ATD_ACCEPTED" -> {
                    if (parts.size >= 3) {
                        BlePacket.AtdAcceptedResponse(
                            timestamp = parts[1].toLongOrNull() ?: 0L,
                            rid = parts[2].toIntOrNull() ?: 0
                        )
                    } else if (parts.size >= 2) {
                        BlePacket.AtdAcceptedResponse(
                            timestamp = parts[1].toLongOrNull() ?: 0L,
                            rid = 0
                        )
                    } else BlePacket.UnknownPacket(raw)
                }

                "ATD_REJECTED" -> {
                    if (parts.size >= 2) {
                        BlePacket.AtdRejectedResponse(parts[1].toIntOrNull() ?: 0)
                    } else if (parts.size >= 1) {
                        BlePacket.AtdRejectedResponse(0)
                    } else BlePacket.UnknownPacket(raw)
                }

                "MARKED" -> {
                    if (parts.size >= 3) {
                        BlePacket.MarkedResponse(
                            timestamp = parts[1].toLongOrNull() ?: 0L,
                            rid = parts[2].toIntOrNull() ?: 0
                        )
                    } else if (parts.size >= 2) {
                        BlePacket.MarkedResponse(
                            timestamp = parts[1].toLongOrNull() ?: 0L,
                            rid = 0
                        )
                    } else BlePacket.UnknownPacket(raw)
                }

                "SCHED" -> {
                    if (parts.size >= 6) {
                        BlePacket.SchedResponse(
                            days = parts[1],
                            start = parts[2],
                            grace = parts[3],
                            end = parts[4],
                            rid = parts[5].toIntOrNull() ?: 0
                        )
                    } else if (parts.size >= 5) {
                        BlePacket.SchedResponse(
                            days = parts[1],
                            start = parts[2],
                            grace = parts[3],
                            end = parts[4],
                            rid = 0
                        )
                    } else BlePacket.UnknownPacket(raw)
                }

                "DONE" -> {
                    if (parts.size >= 2) {
                        BlePacket.DoneResponse(parts[1].toIntOrNull() ?: 0)
                    } else if (parts.size >= 1) {
                        BlePacket.DoneResponse(0)
                    } else BlePacket.UnknownPacket(raw)
                }

                else -> BlePacket.UnknownPacket(raw)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing packet: $raw", e)
            BlePacket.UnknownPacket(raw)
        }
    }
}
