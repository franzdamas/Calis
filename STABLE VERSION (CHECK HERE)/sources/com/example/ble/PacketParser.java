package com.example.ble;

import android.util.Log;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PacketParser.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/example/ble/PacketParser;", "", "<init>", "()V", "TAG", "", "parse", "Lcom/example/ble/BlePacket;", "raw", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PacketParser {
    public static final int $stable = 0;
    public static final PacketParser INSTANCE = new PacketParser();
    private static final String TAG = "PacketParser";

    private PacketParser() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final BlePacket parse(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        String trimmed = StringsKt.trim((CharSequence) raw).toString();
        if (trimmed.length() == 0) {
            return new BlePacket.UnknownPacket(raw);
        }
        List parts = StringsKt.split$default((CharSequence) trimmed, new String[]{"|"}, false, 0, 6, (Object) null);
        String cmd = (String) parts.get(0);
        try {
            switch (cmd.hashCode()) {
                case -2027976660:
                    if (cmd.equals("MARKED")) {
                        if (parts.size() >= 3) {
                            Long longOrNull = StringsKt.toLongOrNull((String) parts.get(1));
                            long jLongValue = longOrNull != null ? longOrNull.longValue() : 0L;
                            Integer intOrNull = StringsKt.toIntOrNull((String) parts.get(2));
                            return new BlePacket.MarkedResponse(jLongValue, intOrNull != null ? intOrNull.intValue() : 0);
                        }
                        if (parts.size() < 2) {
                            return new BlePacket.UnknownPacket(raw);
                        }
                        Long longOrNull2 = StringsKt.toLongOrNull((String) parts.get(1));
                        return new BlePacket.MarkedResponse(longOrNull2 != null ? longOrNull2.longValue() : 0L, 0);
                    }
                    break;
                case -1838260842:
                    if (cmd.equals("ST_REG")) {
                        if (parts.size() >= 7) {
                            String str = (String) parts.get(1);
                            String str2 = (String) parts.get(2);
                            String str3 = (String) parts.get(3);
                            String str4 = (String) parts.get(4);
                            Long longOrNull3 = StringsKt.toLongOrNull((String) parts.get(5));
                            long jLongValue2 = longOrNull3 != null ? longOrNull3.longValue() : 0L;
                            Integer intOrNull2 = StringsKt.toIntOrNull((String) parts.get(6));
                            return new BlePacket.StudentRegRequest(str, str2, str3, str4, jLongValue2, intOrNull2 != null ? intOrNull2.intValue() : 0);
                        }
                        if (parts.size() < 6) {
                            return new BlePacket.UnknownPacket(raw);
                        }
                        String str5 = (String) parts.get(1);
                        String str6 = (String) parts.get(2);
                        String str7 = (String) parts.get(3);
                        String str8 = (String) parts.get(4);
                        Long longOrNull4 = StringsKt.toLongOrNull((String) parts.get(5));
                        return new BlePacket.StudentRegRequest(str5, str6, str7, str8, longOrNull4 != null ? longOrNull4.longValue() : 0L, 0, 32, null);
                    }
                    break;
                case -1817942506:
                    if (cmd.equals("TK_SBM")) {
                        if (parts.size() < 5) {
                            return parts.size() >= 4 ? new BlePacket.TaskSubmissionRequest((String) parts.get(1), (String) parts.get(2), (String) parts.get(3), 0, 8, null) : new BlePacket.UnknownPacket(raw);
                        }
                        String str9 = (String) parts.get(1);
                        String str10 = (String) parts.get(2);
                        String str11 = (String) parts.get(3);
                        Integer intOrNull3 = StringsKt.toIntOrNull((String) parts.get(4));
                        return new BlePacket.TaskSubmissionRequest(str9, str10, str11, intOrNull3 != null ? intOrNull3.intValue() : 0);
                    }
                    break;
                case -1725994533:
                    if (cmd.equals("UNREGISTERED")) {
                        return BlePacket.UnregisteredResponse.INSTANCE;
                    }
                    break;
                case -1384838526:
                    if (cmd.equals("REGISTERED")) {
                        return BlePacket.RegisteredResponse.INSTANCE;
                    }
                    break;
                case -1132650631:
                    if (cmd.equals("START_SESSION")) {
                        return BlePacket.StartSessionCommand.INSTANCE;
                    }
                    break;
                case -1032984948:
                    if (cmd.equals("ATD_REJECTED")) {
                        if (parts.size() < 2) {
                            return parts.size() >= 1 ? new BlePacket.AtdRejectedResponse(0) : new BlePacket.UnknownPacket(raw);
                        }
                        Integer intOrNull4 = StringsKt.toIntOrNull((String) parts.get(1));
                        return new BlePacket.AtdRejectedResponse(intOrNull4 != null ? intOrNull4.intValue() : 0);
                    }
                    break;
                case -744878558:
                    if (cmd.equals("REG_APPROVED")) {
                        if (parts.size() < 9) {
                            return parts.size() >= 8 ? new BlePacket.RegApprovedResponse((String) parts.get(1), (String) parts.get(2), (String) parts.get(3), (String) parts.get(4), (String) parts.get(5), (String) parts.get(6), (String) parts.get(7), 0) : new BlePacket.UnknownPacket(raw);
                        }
                        String str12 = (String) parts.get(1);
                        String str13 = (String) parts.get(2);
                        String str14 = (String) parts.get(3);
                        String str15 = (String) parts.get(4);
                        String str16 = (String) parts.get(5);
                        String str17 = (String) parts.get(6);
                        String str18 = (String) parts.get(7);
                        Integer intOrNull5 = StringsKt.toIntOrNull((String) parts.get(8));
                        return new BlePacket.RegApprovedResponse(str12, str13, str14, str15, str16, str17, str18, intOrNull5 != null ? intOrNull5.intValue() : 0);
                    }
                    break;
                case -455703884:
                    if (cmd.equals("AUTH_FAILED")) {
                        return BlePacket.AuthFailedResponse.INSTANCE;
                    }
                    break;
                case 2679:
                    if (cmd.equals("TK")) {
                        if (parts.size() < 8) {
                            if (parts.size() < 7) {
                                return new BlePacket.UnknownPacket(raw);
                            }
                            String str19 = (String) parts.get(1);
                            String str20 = (String) parts.get(2);
                            String str21 = (String) parts.get(3);
                            Long longOrNull5 = StringsKt.toLongOrNull((String) parts.get(4));
                            long jLongValue3 = longOrNull5 != null ? longOrNull5.longValue() : 0L;
                            Long longOrNull6 = StringsKt.toLongOrNull((String) parts.get(5));
                            return new BlePacket.TaskCommand(str19, str20, str21, jLongValue3, longOrNull6 != null ? longOrNull6.longValue() : 0L, (String) parts.get(6), 0);
                        }
                        String str22 = (String) parts.get(1);
                        String str23 = (String) parts.get(2);
                        String str24 = (String) parts.get(3);
                        Long longOrNull7 = StringsKt.toLongOrNull((String) parts.get(4));
                        long jLongValue4 = longOrNull7 != null ? longOrNull7.longValue() : 0L;
                        Long longOrNull8 = StringsKt.toLongOrNull((String) parts.get(5));
                        long jLongValue5 = longOrNull8 != null ? longOrNull8.longValue() : 0L;
                        String str25 = (String) parts.get(6);
                        Integer intOrNull6 = StringsKt.toIntOrNull((String) parts.get(7));
                        return new BlePacket.TaskCommand(str22, str23, str24, jLongValue4, jLongValue5, str25, intOrNull6 != null ? intOrNull6.intValue() : 0);
                    }
                    break;
                case 81012:
                    if (cmd.equals("REG")) {
                        return parts.size() >= 5 ? new BlePacket.RegCommand((String) parts.get(1), (String) parts.get(2), (String) parts.get(3), (String) parts.get(4)) : new BlePacket.UnknownPacket(raw);
                    }
                    break;
                case 2020776:
                    if (cmd.equals("AUTH")) {
                        return parts.size() >= 3 ? new BlePacket.AuthCommand((String) parts.get(1), (String) parts.get(2)) : new BlePacket.UnknownPacket(raw);
                    }
                    break;
                case 2104194:
                    if (cmd.equals("DONE")) {
                        if (parts.size() < 2) {
                            return parts.size() >= 1 ? new BlePacket.DoneResponse(0) : new BlePacket.UnknownPacket(raw);
                        }
                        Integer intOrNull7 = StringsKt.toIntOrNull((String) parts.get(1));
                        return new BlePacket.DoneResponse(intOrNull7 != null ? intOrNull7.intValue() : 0);
                    }
                    break;
                case 71489491:
                    if (cmd.equals("AUTH_OK")) {
                        return BlePacket.AuthOkResponse.INSTANCE;
                    }
                    break;
                case 78719639:
                    if (cmd.equals("SCHED")) {
                        if (parts.size() < 6) {
                            return parts.size() >= 5 ? new BlePacket.SchedResponse((String) parts.get(1), (String) parts.get(2), (String) parts.get(3), (String) parts.get(4), 0) : new BlePacket.UnknownPacket(raw);
                        }
                        String str26 = (String) parts.get(1);
                        String str27 = (String) parts.get(2);
                        String str28 = (String) parts.get(3);
                        String str29 = (String) parts.get(4);
                        Integer intOrNull8 = StringsKt.toIntOrNull((String) parts.get(5));
                        return new BlePacket.SchedResponse(str26, str27, str28, str29, intOrNull8 != null ? intOrNull8.intValue() : 0);
                    }
                    break;
                case 80903995:
                    if (cmd.equals("UNREG")) {
                        return BlePacket.UnregCommand.INSTANCE;
                    }
                    break;
                case 534632178:
                    if (cmd.equals("END_SESSION")) {
                        return BlePacket.EndSessionCommand.INSTANCE;
                    }
                    break;
                case 1723953589:
                    if (cmd.equals("ATD_ACCEPTED")) {
                        if (parts.size() >= 3) {
                            Long longOrNull9 = StringsKt.toLongOrNull((String) parts.get(1));
                            long jLongValue6 = longOrNull9 != null ? longOrNull9.longValue() : 0L;
                            Integer intOrNull9 = StringsKt.toIntOrNull((String) parts.get(2));
                            return new BlePacket.AtdAcceptedResponse(jLongValue6, intOrNull9 != null ? intOrNull9.intValue() : 0);
                        }
                        if (parts.size() < 2) {
                            return new BlePacket.UnknownPacket(raw);
                        }
                        Long longOrNull10 = StringsKt.toLongOrNull((String) parts.get(1));
                        return new BlePacket.AtdAcceptedResponse(longOrNull10 != null ? longOrNull10.longValue() : 0L, 0);
                    }
                    break;
                case 1756347369:
                    if (cmd.equals("REG_REJECTED")) {
                        if (parts.size() < 2) {
                            return parts.size() >= 1 ? new BlePacket.RegRejectedResponse(0) : new BlePacket.UnknownPacket(raw);
                        }
                        Integer intOrNull10 = StringsKt.toIntOrNull((String) parts.get(1));
                        return new BlePacket.RegRejectedResponse(intOrNull10 != null ? intOrNull10.intValue() : 0);
                    }
                    break;
                case 1813988808:
                    if (cmd.equals("REQ_UPD")) {
                        if (parts.size() < 3) {
                            return parts.size() >= 2 ? new BlePacket.RequestUpdateRequest((String) parts.get(1), 0, 2, null) : new BlePacket.UnknownPacket(raw);
                        }
                        String str30 = (String) parts.get(1);
                        Integer intOrNull11 = StringsKt.toIntOrNull((String) parts.get(2));
                        return new BlePacket.RequestUpdateRequest(str30, intOrNull11 != null ? intOrNull11.intValue() : 0);
                    }
                    break;
                case 1986974590:
                    if (cmd.equals("CHK_IN")) {
                        if (parts.size() < 3) {
                            return parts.size() >= 2 ? new BlePacket.CheckInRequest((String) parts.get(1), 0, 2, null) : new BlePacket.UnknownPacket(raw);
                        }
                        String str31 = (String) parts.get(1);
                        Integer intOrNull12 = StringsKt.toIntOrNull((String) parts.get(2));
                        return new BlePacket.CheckInRequest(str31, intOrNull12 != null ? intOrNull12.intValue() : 0);
                    }
                    break;
            }
            return new BlePacket.UnknownPacket(raw);
        } catch (Exception e) {
            Log.e(TAG, "Error parsing packet: " + raw, e);
            return new BlePacket.UnknownPacket(raw);
        }
    }
}
