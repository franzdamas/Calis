import os

with open("app/src/main/java/com/example/ble/PacketParser.kt", "r") as f:
    content = f.read()

# Fix TK
old_tk = """                "TK" -> {
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
                    } else BlePacket.UnknownPacket(raw)
                }"""
new_tk = """                "TK" -> {
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
                }"""
content = content.replace(old_tk, new_tk)

# Fix REG_APPROVED
old_reg_app = """                "REG_APPROVED" -> {
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
                    } else BlePacket.UnknownPacket(raw)
                }"""
new_reg_app = """                "REG_APPROVED" -> {
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
                }"""
content = content.replace(old_reg_app, new_reg_app)

# Fix REG_REJECTED
old_reg_rej = """                "REG_REJECTED" -> {
                    if (parts.size >= 2) {
                        BlePacket.RegRejectedResponse(parts[1].toIntOrNull() ?: 0)
                    } else BlePacket.UnknownPacket(raw)
                }"""
new_reg_rej = """                "REG_REJECTED" -> {
                    if (parts.size >= 2) {
                        BlePacket.RegRejectedResponse(parts[1].toIntOrNull() ?: 0)
                    } else if (parts.size >= 1) {
                        BlePacket.RegRejectedResponse(0)
                    } else BlePacket.UnknownPacket(raw)
                }"""
content = content.replace(old_reg_rej, new_reg_rej)

# Fix ATD_ACCEPTED
old_atd_acc = """                "ATD_ACCEPTED" -> {
                    if (parts.size >= 3) {
                        BlePacket.AtdAcceptedResponse(
                            timestamp = parts[1].toLongOrNull() ?: 0L,
                            rid = parts[2].toIntOrNull() ?: 0
                        )
                    } else BlePacket.UnknownPacket(raw)
                }"""
new_atd_acc = """                "ATD_ACCEPTED" -> {
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
                }"""
content = content.replace(old_atd_acc, new_atd_acc)

# Fix ATD_REJECTED
old_atd_rej = """                "ATD_REJECTED" -> {
                    if (parts.size >= 2) {
                        BlePacket.AtdRejectedResponse(parts[1].toIntOrNull() ?: 0)
                    } else BlePacket.UnknownPacket(raw)
                }"""
new_atd_rej = """                "ATD_REJECTED" -> {
                    if (parts.size >= 2) {
                        BlePacket.AtdRejectedResponse(parts[1].toIntOrNull() ?: 0)
                    } else if (parts.size >= 1) {
                        BlePacket.AtdRejectedResponse(0)
                    } else BlePacket.UnknownPacket(raw)
                }"""
content = content.replace(old_atd_rej, new_atd_rej)

# Fix MARKED
old_marked = """                "MARKED" -> {
                    if (parts.size >= 3) {
                        BlePacket.MarkedResponse(
                            timestamp = parts[1].toLongOrNull() ?: 0L,
                            rid = parts[2].toIntOrNull() ?: 0
                        )
                    } else BlePacket.UnknownPacket(raw)
                }"""
new_marked = """                "MARKED" -> {
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
                }"""
content = content.replace(old_marked, new_marked)

# Fix SCHED
old_sched = """                "SCHED" -> {
                    if (parts.size >= 6) {
                        BlePacket.SchedResponse(
                            days = parts[1],
                            start = parts[2],
                            grace = parts[3],
                            end = parts[4],
                            rid = parts[5].toIntOrNull() ?: 0
                        )
                    } else BlePacket.UnknownPacket(raw)
                }"""
new_sched = """                "SCHED" -> {
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
                }"""
content = content.replace(old_sched, new_sched)

# Fix DONE
old_done = """                "DONE" -> {
                    if (parts.size >= 2) {
                        BlePacket.DoneResponse(parts[1].toIntOrNull() ?: 0)
                    } else BlePacket.UnknownPacket(raw)
                }"""
new_done = """                "DONE" -> {
                    if (parts.size >= 2) {
                        BlePacket.DoneResponse(parts[1].toIntOrNull() ?: 0)
                    } else if (parts.size >= 1) {
                        BlePacket.DoneResponse(0)
                    } else BlePacket.UnknownPacket(raw)
                }"""
content = content.replace(old_done, new_done)

with open("app/src/main/java/com/example/ble/PacketParser.kt", "w") as f:
    f.write(content)

