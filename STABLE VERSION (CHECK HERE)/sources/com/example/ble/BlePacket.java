package com.example.ble;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BlePacket.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0016\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0016\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./¨\u00060"}, d2 = {"Lcom/example/ble/BlePacket;", "", "<init>", "()V", "RegCommand", "AuthCommand", "StartSessionCommand", "EndSessionCommand", "TaskCommand", "UnregCommand", "StudentRegRequest", "CheckInRequest", "TaskSubmissionRequest", "RequestUpdateRequest", "RegisteredResponse", "UnregisteredResponse", "AuthOkResponse", "AuthFailedResponse", "RegApprovedResponse", "RegRejectedResponse", "AtdAcceptedResponse", "AtdRejectedResponse", "MarkedResponse", "SchedResponse", "DoneResponse", "UnknownPacket", "Lcom/example/ble/BlePacket$AtdAcceptedResponse;", "Lcom/example/ble/BlePacket$AtdRejectedResponse;", "Lcom/example/ble/BlePacket$AuthCommand;", "Lcom/example/ble/BlePacket$AuthFailedResponse;", "Lcom/example/ble/BlePacket$AuthOkResponse;", "Lcom/example/ble/BlePacket$CheckInRequest;", "Lcom/example/ble/BlePacket$DoneResponse;", "Lcom/example/ble/BlePacket$EndSessionCommand;", "Lcom/example/ble/BlePacket$MarkedResponse;", "Lcom/example/ble/BlePacket$RegApprovedResponse;", "Lcom/example/ble/BlePacket$RegCommand;", "Lcom/example/ble/BlePacket$RegRejectedResponse;", "Lcom/example/ble/BlePacket$RegisteredResponse;", "Lcom/example/ble/BlePacket$RequestUpdateRequest;", "Lcom/example/ble/BlePacket$SchedResponse;", "Lcom/example/ble/BlePacket$StartSessionCommand;", "Lcom/example/ble/BlePacket$StudentRegRequest;", "Lcom/example/ble/BlePacket$TaskCommand;", "Lcom/example/ble/BlePacket$TaskSubmissionRequest;", "Lcom/example/ble/BlePacket$UnknownPacket;", "Lcom/example/ble/BlePacket$UnregCommand;", "Lcom/example/ble/BlePacket$UnregisteredResponse;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BlePacket {
    public static final int $stable = 0;

    public /* synthetic */ BlePacket(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private BlePacket() {
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u000e\u001a\u00020\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/example/ble/BlePacket$RegCommand;", "Lcom/example/ble/BlePacket;", "cid", "", "tid", "cls", "tc", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCid", "()Ljava/lang/String;", "getTid", "getCls", "getTc", "encode", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RegCommand extends BlePacket {
        public static final int $stable = 0;
        private final String cid;
        private final String cls;
        private final String tc;
        private final String tid;

        public static /* synthetic */ RegCommand copy$default(RegCommand regCommand, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = regCommand.cid;
            }
            if ((i & 2) != 0) {
                str2 = regCommand.tid;
            }
            if ((i & 4) != 0) {
                str3 = regCommand.cls;
            }
            if ((i & 8) != 0) {
                str4 = regCommand.tc;
            }
            return regCommand.copy(str, str2, str3, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getCid() {
            return this.cid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTid() {
            return this.tid;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCls() {
            return this.cls;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTc() {
            return this.tc;
        }

        public final RegCommand copy(String cid, String tid, String cls, String tc) {
            Intrinsics.checkNotNullParameter(cid, "cid");
            Intrinsics.checkNotNullParameter(tid, "tid");
            Intrinsics.checkNotNullParameter(cls, "cls");
            Intrinsics.checkNotNullParameter(tc, "tc");
            return new RegCommand(cid, tid, cls, tc);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RegCommand)) {
                return false;
            }
            RegCommand regCommand = (RegCommand) other;
            return Intrinsics.areEqual(this.cid, regCommand.cid) && Intrinsics.areEqual(this.tid, regCommand.tid) && Intrinsics.areEqual(this.cls, regCommand.cls) && Intrinsics.areEqual(this.tc, regCommand.tc);
        }

        public int hashCode() {
            return (((((this.cid.hashCode() * 31) + this.tid.hashCode()) * 31) + this.cls.hashCode()) * 31) + this.tc.hashCode();
        }

        public String toString() {
            return "RegCommand(cid=" + this.cid + ", tid=" + this.tid + ", cls=" + this.cls + ", tc=" + this.tc + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RegCommand(String cid, String tid, String cls, String tc) {
            super(null);
            Intrinsics.checkNotNullParameter(cid, "cid");
            Intrinsics.checkNotNullParameter(tid, "tid");
            Intrinsics.checkNotNullParameter(cls, "cls");
            Intrinsics.checkNotNullParameter(tc, "tc");
            this.cid = cid;
            this.tid = tid;
            this.cls = cls;
            this.tc = tc;
        }

        public final String getCid() {
            return this.cid;
        }

        public final String getCls() {
            return this.cls;
        }

        public final String getTc() {
            return this.tc;
        }

        public final String getTid() {
            return this.tid;
        }

        public final String encode() {
            return "REG|" + this.cid + "|" + this.tid + "|" + this.cls + "|" + this.tc;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\n\u001a\u00020\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/example/ble/BlePacket$AuthCommand;", "Lcom/example/ble/BlePacket;", "tid", "", "cid", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTid", "()Ljava/lang/String;", "getCid", "encode", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class AuthCommand extends BlePacket {
        public static final int $stable = 0;
        private final String cid;
        private final String tid;

        public static /* synthetic */ AuthCommand copy$default(AuthCommand authCommand, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = authCommand.tid;
            }
            if ((i & 2) != 0) {
                str2 = authCommand.cid;
            }
            return authCommand.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTid() {
            return this.tid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCid() {
            return this.cid;
        }

        public final AuthCommand copy(String tid, String cid) {
            Intrinsics.checkNotNullParameter(tid, "tid");
            Intrinsics.checkNotNullParameter(cid, "cid");
            return new AuthCommand(tid, cid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AuthCommand)) {
                return false;
            }
            AuthCommand authCommand = (AuthCommand) other;
            return Intrinsics.areEqual(this.tid, authCommand.tid) && Intrinsics.areEqual(this.cid, authCommand.cid);
        }

        public int hashCode() {
            return (this.tid.hashCode() * 31) + this.cid.hashCode();
        }

        public String toString() {
            return "AuthCommand(tid=" + this.tid + ", cid=" + this.cid + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AuthCommand(String tid, String cid) {
            super(null);
            Intrinsics.checkNotNullParameter(tid, "tid");
            Intrinsics.checkNotNullParameter(cid, "cid");
            this.tid = tid;
            this.cid = cid;
        }

        public final String getCid() {
            return this.cid;
        }

        public final String getTid() {
            return this.tid;
        }

        public final String encode() {
            return "AUTH|" + this.tid + "|" + this.cid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/example/ble/BlePacket$StartSessionCommand;", "Lcom/example/ble/BlePacket;", "<init>", "()V", "encode", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class StartSessionCommand extends BlePacket {
        public static final int $stable = 0;
        public static final StartSessionCommand INSTANCE = new StartSessionCommand();

        private StartSessionCommand() {
            super(null);
        }

        public final String encode() {
            return "START_SESSION";
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/example/ble/BlePacket$EndSessionCommand;", "Lcom/example/ble/BlePacket;", "<init>", "()V", "encode", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class EndSessionCommand extends BlePacket {
        public static final int $stable = 0;
        public static final EndSessionCommand INSTANCE = new EndSessionCommand();

        private EndSessionCommand() {
            super(null);
        }

        public final String encode() {
            return "END_SESSION";
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0006\u0010\u0018\u001a\u00020\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u000bHÆ\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020\u000bHÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lcom/example/ble/BlePacket$TaskCommand;", "Lcom/example/ble/BlePacket;", "tkid", "", LinkHeader.Parameters.Title, "desc", "deadline", "", "datePosted", "score", "rid", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;I)V", "getTkid", "()Ljava/lang/String;", "getTitle", "getDesc", "getDeadline", "()J", "getDatePosted", "getScore", "getRid", "()I", "encode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class TaskCommand extends BlePacket {
        public static final int $stable = 0;
        private final long datePosted;
        private final long deadline;
        private final String desc;
        private final int rid;
        private final String score;
        private final String title;
        private final String tkid;

        public static /* synthetic */ TaskCommand copy$default(TaskCommand taskCommand, String str, String str2, String str3, long j, long j2, String str4, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = taskCommand.tkid;
            }
            if ((i2 & 2) != 0) {
                str2 = taskCommand.title;
            }
            if ((i2 & 4) != 0) {
                str3 = taskCommand.desc;
            }
            if ((i2 & 8) != 0) {
                j = taskCommand.deadline;
            }
            if ((i2 & 16) != 0) {
                j2 = taskCommand.datePosted;
            }
            if ((i2 & 32) != 0) {
                str4 = taskCommand.score;
            }
            if ((i2 & 64) != 0) {
                i = taskCommand.rid;
            }
            long j3 = j2;
            long j4 = j;
            String str5 = str3;
            return taskCommand.copy(str, str2, str5, j4, j3, str4, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTkid() {
            return this.tkid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getDeadline() {
            return this.deadline;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getDatePosted() {
            return this.datePosted;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final TaskCommand copy(String tkid, String title, String desc, long deadline, long datePosted, String score, int rid) {
            Intrinsics.checkNotNullParameter(tkid, "tkid");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(score, "score");
            return new TaskCommand(tkid, title, desc, deadline, datePosted, score, rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TaskCommand)) {
                return false;
            }
            TaskCommand taskCommand = (TaskCommand) other;
            return Intrinsics.areEqual(this.tkid, taskCommand.tkid) && Intrinsics.areEqual(this.title, taskCommand.title) && Intrinsics.areEqual(this.desc, taskCommand.desc) && this.deadline == taskCommand.deadline && this.datePosted == taskCommand.datePosted && Intrinsics.areEqual(this.score, taskCommand.score) && this.rid == taskCommand.rid;
        }

        public int hashCode() {
            return (((((((((((this.tkid.hashCode() * 31) + this.title.hashCode()) * 31) + this.desc.hashCode()) * 31) + Long.hashCode(this.deadline)) * 31) + Long.hashCode(this.datePosted)) * 31) + this.score.hashCode()) * 31) + Integer.hashCode(this.rid);
        }

        public String toString() {
            return "TaskCommand(tkid=" + this.tkid + ", title=" + this.title + ", desc=" + this.desc + ", deadline=" + this.deadline + ", datePosted=" + this.datePosted + ", score=" + this.score + ", rid=" + this.rid + ")";
        }

        public final String getTkid() {
            return this.tkid;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final long getDeadline() {
            return this.deadline;
        }

        public final long getDatePosted() {
            return this.datePosted;
        }

        public final String getScore() {
            return this.score;
        }

        public final int getRid() {
            return this.rid;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TaskCommand(String tkid, String title, String desc, long deadline, long datePosted, String score, int rid) {
            super(null);
            Intrinsics.checkNotNullParameter(tkid, "tkid");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(score, "score");
            this.tkid = tkid;
            this.title = title;
            this.desc = desc;
            this.deadline = deadline;
            this.datePosted = datePosted;
            this.score = score;
            this.rid = rid;
        }

        public final String encode() {
            String effectiveDesc = StringsKt.isBlank(this.desc) ? this.title : this.desc;
            return "TK|" + this.tkid + "|" + this.title + "|" + effectiveDesc + "|" + this.deadline + "|" + this.datePosted + "|" + this.score + "|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/example/ble/BlePacket$UnregCommand;", "Lcom/example/ble/BlePacket;", "<init>", "()V", "encode", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnregCommand extends BlePacket {
        public static final int $stable = 0;
        public static final UnregCommand INSTANCE = new UnregCommand();

        private UnregCommand() {
            super(null);
        }

        public final String encode() {
            return "UNREG";
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u0016\u001a\u00020\u0003J\u0006\u0010\u0017\u001a\u00020\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\nHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006%"}, d2 = {"Lcom/example/ble/BlePacket$StudentRegRequest;", "Lcom/example/ble/BlePacket;", "sid", "", "st", "sex", "lrn", "birth", "", "rid", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JI)V", "getSid", "()Ljava/lang/String;", "getSt", "getSex", "getLrn", "getBirth", "()J", "getRid", "()I", "encodeForDevice", "encodeWithRid", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class StudentRegRequest extends BlePacket {
        public static final int $stable = 0;
        private final long birth;
        private final String lrn;
        private final int rid;
        private final String sex;
        private final String sid;
        private final String st;

        public static /* synthetic */ StudentRegRequest copy$default(StudentRegRequest studentRegRequest, String str, String str2, String str3, String str4, long j, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = studentRegRequest.sid;
            }
            if ((i2 & 2) != 0) {
                str2 = studentRegRequest.st;
            }
            if ((i2 & 4) != 0) {
                str3 = studentRegRequest.sex;
            }
            if ((i2 & 8) != 0) {
                str4 = studentRegRequest.lrn;
            }
            if ((i2 & 16) != 0) {
                j = studentRegRequest.birth;
            }
            if ((i2 & 32) != 0) {
                i = studentRegRequest.rid;
            }
            int i3 = i;
            long j2 = j;
            return studentRegRequest.copy(str, str2, str3, str4, j2, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSid() {
            return this.sid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSt() {
            return this.st;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSex() {
            return this.sex;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getLrn() {
            return this.lrn;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getBirth() {
            return this.birth;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final StudentRegRequest copy(String sid, String st, String sex, String lrn, long birth, int rid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(st, "st");
            Intrinsics.checkNotNullParameter(sex, "sex");
            Intrinsics.checkNotNullParameter(lrn, "lrn");
            return new StudentRegRequest(sid, st, sex, lrn, birth, rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StudentRegRequest)) {
                return false;
            }
            StudentRegRequest studentRegRequest = (StudentRegRequest) other;
            return Intrinsics.areEqual(this.sid, studentRegRequest.sid) && Intrinsics.areEqual(this.st, studentRegRequest.st) && Intrinsics.areEqual(this.sex, studentRegRequest.sex) && Intrinsics.areEqual(this.lrn, studentRegRequest.lrn) && this.birth == studentRegRequest.birth && this.rid == studentRegRequest.rid;
        }

        public int hashCode() {
            return (((((((((this.sid.hashCode() * 31) + this.st.hashCode()) * 31) + this.sex.hashCode()) * 31) + this.lrn.hashCode()) * 31) + Long.hashCode(this.birth)) * 31) + Integer.hashCode(this.rid);
        }

        public String toString() {
            return "StudentRegRequest(sid=" + this.sid + ", st=" + this.st + ", sex=" + this.sex + ", lrn=" + this.lrn + ", birth=" + this.birth + ", rid=" + this.rid + ")";
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ StudentRegRequest(String str, String str2, String str3, String str4, long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            int i3;
            if ((i2 & 32) == 0) {
                i3 = i;
            } else {
                i3 = 0;
            }
            this(str, str2, str3, str4, j, i3);
        }

        public final String getSid() {
            return this.sid;
        }

        public final String getSt() {
            return this.st;
        }

        public final String getSex() {
            return this.sex;
        }

        public final String getLrn() {
            return this.lrn;
        }

        public final long getBirth() {
            return this.birth;
        }

        public final int getRid() {
            return this.rid;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StudentRegRequest(String sid, String st, String sex, String lrn, long birth, int rid) {
            super(null);
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(st, "st");
            Intrinsics.checkNotNullParameter(sex, "sex");
            Intrinsics.checkNotNullParameter(lrn, "lrn");
            this.sid = sid;
            this.st = st;
            this.sex = sex;
            this.lrn = lrn;
            this.birth = birth;
            this.rid = rid;
        }

        public final String encodeForDevice() {
            StringBuilder sbAppend;
            int i = this.rid;
            String str = this.sid;
            if (i != 0) {
                String str2 = this.st;
                String str3 = this.sex;
                String str4 = this.lrn;
                long j = this.birth;
                sbAppend = new StringBuilder().append("ST_REG|").append(str).append("|").append(str2).append("|").append(str3).append("|").append(str4).append("|").append(j).append("|").append(this.rid);
            } else {
                String str5 = this.st;
                String str6 = this.sex;
                String str7 = this.lrn;
                sbAppend = new StringBuilder().append("ST_REG|").append(str).append("|").append(str5).append("|").append(str6).append("|").append(str7).append("|").append(this.birth);
            }
            return sbAppend.toString();
        }

        public final String encodeWithRid() {
            return "ST_REG|" + this.sid + "|" + this.st + "|" + this.sex + "|" + this.lrn + "|" + this.birth + "|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\u0003J\u0006\u0010\r\u001a\u00020\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/example/ble/BlePacket$CheckInRequest;", "Lcom/example/ble/BlePacket;", "sid", "", "rid", "", "<init>", "(Ljava/lang/String;I)V", "getSid", "()Ljava/lang/String;", "getRid", "()I", "encodeForDevice", "encodeWithRid", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CheckInRequest extends BlePacket {
        public static final int $stable = 0;
        private final int rid;
        private final String sid;

        public static /* synthetic */ CheckInRequest copy$default(CheckInRequest checkInRequest, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = checkInRequest.sid;
            }
            if ((i2 & 2) != 0) {
                i = checkInRequest.rid;
            }
            return checkInRequest.copy(str, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSid() {
            return this.sid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final CheckInRequest copy(String sid, int rid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            return new CheckInRequest(sid, rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CheckInRequest)) {
                return false;
            }
            CheckInRequest checkInRequest = (CheckInRequest) other;
            return Intrinsics.areEqual(this.sid, checkInRequest.sid) && this.rid == checkInRequest.rid;
        }

        public int hashCode() {
            return (this.sid.hashCode() * 31) + Integer.hashCode(this.rid);
        }

        public String toString() {
            return "CheckInRequest(sid=" + this.sid + ", rid=" + this.rid + ")";
        }

        public /* synthetic */ CheckInRequest(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i2 & 2) != 0 ? 0 : i);
        }

        public final String getSid() {
            return this.sid;
        }

        public final int getRid() {
            return this.rid;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CheckInRequest(String sid, int rid) {
            super(null);
            Intrinsics.checkNotNullParameter(sid, "sid");
            this.sid = sid;
            this.rid = rid;
        }

        public final String encodeForDevice() {
            StringBuilder sbAppend;
            int i = this.rid;
            String str = this.sid;
            if (i != 0) {
                sbAppend = new StringBuilder().append("CHK_IN|").append(str).append("|").append(this.rid);
            } else {
                sbAppend = new StringBuilder().append("CHK_IN|").append(str);
            }
            return sbAppend.toString();
        }

        public final String encodeWithRid() {
            return "CHK_IN|" + this.sid + "|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u0010\u001a\u00020\u0003J\u0006\u0010\u0011\u001a\u00020\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/example/ble/BlePacket$TaskSubmissionRequest;", "Lcom/example/ble/BlePacket;", "sid", "", "tkid", "score", "rid", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getSid", "()Ljava/lang/String;", "getTkid", "getScore", "getRid", "()I", "encodeForDevice", "encodeWithRid", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class TaskSubmissionRequest extends BlePacket {
        public static final int $stable = 0;
        private final int rid;
        private final String score;
        private final String sid;
        private final String tkid;

        public static /* synthetic */ TaskSubmissionRequest copy$default(TaskSubmissionRequest taskSubmissionRequest, String str, String str2, String str3, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = taskSubmissionRequest.sid;
            }
            if ((i2 & 2) != 0) {
                str2 = taskSubmissionRequest.tkid;
            }
            if ((i2 & 4) != 0) {
                str3 = taskSubmissionRequest.score;
            }
            if ((i2 & 8) != 0) {
                i = taskSubmissionRequest.rid;
            }
            return taskSubmissionRequest.copy(str, str2, str3, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSid() {
            return this.sid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTkid() {
            return this.tkid;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final TaskSubmissionRequest copy(String sid, String tkid, String score, int rid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(tkid, "tkid");
            Intrinsics.checkNotNullParameter(score, "score");
            return new TaskSubmissionRequest(sid, tkid, score, rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TaskSubmissionRequest)) {
                return false;
            }
            TaskSubmissionRequest taskSubmissionRequest = (TaskSubmissionRequest) other;
            return Intrinsics.areEqual(this.sid, taskSubmissionRequest.sid) && Intrinsics.areEqual(this.tkid, taskSubmissionRequest.tkid) && Intrinsics.areEqual(this.score, taskSubmissionRequest.score) && this.rid == taskSubmissionRequest.rid;
        }

        public int hashCode() {
            return (((((this.sid.hashCode() * 31) + this.tkid.hashCode()) * 31) + this.score.hashCode()) * 31) + Integer.hashCode(this.rid);
        }

        public String toString() {
            return "TaskSubmissionRequest(sid=" + this.sid + ", tkid=" + this.tkid + ", score=" + this.score + ", rid=" + this.rid + ")";
        }

        public /* synthetic */ TaskSubmissionRequest(String str, String str2, String str3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i2 & 8) != 0 ? 0 : i);
        }

        public final String getSid() {
            return this.sid;
        }

        public final String getTkid() {
            return this.tkid;
        }

        public final String getScore() {
            return this.score;
        }

        public final int getRid() {
            return this.rid;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TaskSubmissionRequest(String sid, String tkid, String score, int rid) {
            super(null);
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(tkid, "tkid");
            Intrinsics.checkNotNullParameter(score, "score");
            this.sid = sid;
            this.tkid = tkid;
            this.score = score;
            this.rid = rid;
        }

        public final String encodeForDevice() {
            StringBuilder sbAppend;
            int i = this.rid;
            String str = this.sid;
            if (i != 0) {
                String str2 = this.tkid;
                String str3 = this.score;
                sbAppend = new StringBuilder().append("TK_SBM|").append(str).append("|").append(str2).append("|").append(str3).append("|").append(this.rid);
            } else {
                String str4 = this.tkid;
                sbAppend = new StringBuilder().append("TK_SBM|").append(str).append("|").append(str4).append("|").append(this.score);
            }
            return sbAppend.toString();
        }

        public final String encodeWithRid() {
            return "TK_SBM|" + this.sid + "|" + this.tkid + "|" + this.score + "|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\u0003J\u0006\u0010\r\u001a\u00020\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/example/ble/BlePacket$RequestUpdateRequest;", "Lcom/example/ble/BlePacket;", "sid", "", "rid", "", "<init>", "(Ljava/lang/String;I)V", "getSid", "()Ljava/lang/String;", "getRid", "()I", "encodeForDevice", "encodeWithRid", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RequestUpdateRequest extends BlePacket {
        public static final int $stable = 0;
        private final int rid;
        private final String sid;

        public static /* synthetic */ RequestUpdateRequest copy$default(RequestUpdateRequest requestUpdateRequest, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = requestUpdateRequest.sid;
            }
            if ((i2 & 2) != 0) {
                i = requestUpdateRequest.rid;
            }
            return requestUpdateRequest.copy(str, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSid() {
            return this.sid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final RequestUpdateRequest copy(String sid, int rid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            return new RequestUpdateRequest(sid, rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RequestUpdateRequest)) {
                return false;
            }
            RequestUpdateRequest requestUpdateRequest = (RequestUpdateRequest) other;
            return Intrinsics.areEqual(this.sid, requestUpdateRequest.sid) && this.rid == requestUpdateRequest.rid;
        }

        public int hashCode() {
            return (this.sid.hashCode() * 31) + Integer.hashCode(this.rid);
        }

        public String toString() {
            return "RequestUpdateRequest(sid=" + this.sid + ", rid=" + this.rid + ")";
        }

        public /* synthetic */ RequestUpdateRequest(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i2 & 2) != 0 ? 0 : i);
        }

        public final String getSid() {
            return this.sid;
        }

        public final int getRid() {
            return this.rid;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RequestUpdateRequest(String sid, int rid) {
            super(null);
            Intrinsics.checkNotNullParameter(sid, "sid");
            this.sid = sid;
            this.rid = rid;
        }

        public final String encodeForDevice() {
            StringBuilder sbAppend;
            int i = this.rid;
            String str = this.sid;
            if (i != 0) {
                sbAppend = new StringBuilder().append("REQ_UPD|").append(str).append("|").append(this.rid);
            } else {
                sbAppend = new StringBuilder().append("REQ_UPD|").append(str);
            }
            return sbAppend.toString();
        }

        public final String encodeWithRid() {
            return "REQ_UPD|" + this.sid + "|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/ble/BlePacket$RegisteredResponse;", "Lcom/example/ble/BlePacket;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RegisteredResponse extends BlePacket {
        public static final int $stable = 0;
        public static final RegisteredResponse INSTANCE = new RegisteredResponse();

        private RegisteredResponse() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/ble/BlePacket$UnregisteredResponse;", "Lcom/example/ble/BlePacket;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnregisteredResponse extends BlePacket {
        public static final int $stable = 0;
        public static final UnregisteredResponse INSTANCE = new UnregisteredResponse();

        private UnregisteredResponse() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/ble/BlePacket$AuthOkResponse;", "Lcom/example/ble/BlePacket;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AuthOkResponse extends BlePacket {
        public static final int $stable = 0;
        public static final AuthOkResponse INSTANCE = new AuthOkResponse();

        private AuthOkResponse() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/ble/BlePacket$AuthFailedResponse;", "Lcom/example/ble/BlePacket;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AuthFailedResponse extends BlePacket {
        public static final int $stable = 0;
        public static final AuthFailedResponse INSTANCE = new AuthFailedResponse();

        private AuthFailedResponse() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0006\u0010\u0018\u001a\u00020\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\u000bHÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006("}, d2 = {"Lcom/example/ble/BlePacket$RegApprovedResponse;", "Lcom/example/ble/BlePacket;", "cid", "", "cls", "tc", "days", "start", "grace", "end", "rid", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getCid", "()Ljava/lang/String;", "getCls", "getTc", "getDays", "getStart", "getGrace", "getEnd", "getRid", "()I", "encode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RegApprovedResponse extends BlePacket {
        public static final int $stable = 0;
        private final String cid;
        private final String cls;
        private final String days;
        private final String end;
        private final String grace;
        private final int rid;
        private final String start;
        private final String tc;

        public static /* synthetic */ RegApprovedResponse copy$default(RegApprovedResponse regApprovedResponse, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = regApprovedResponse.cid;
            }
            if ((i2 & 2) != 0) {
                str2 = regApprovedResponse.cls;
            }
            if ((i2 & 4) != 0) {
                str3 = regApprovedResponse.tc;
            }
            if ((i2 & 8) != 0) {
                str4 = regApprovedResponse.days;
            }
            if ((i2 & 16) != 0) {
                str5 = regApprovedResponse.start;
            }
            if ((i2 & 32) != 0) {
                str6 = regApprovedResponse.grace;
            }
            if ((i2 & 64) != 0) {
                str7 = regApprovedResponse.end;
            }
            if ((i2 & 128) != 0) {
                i = regApprovedResponse.rid;
            }
            String str8 = str7;
            int i3 = i;
            String str9 = str5;
            String str10 = str6;
            return regApprovedResponse.copy(str, str2, str3, str4, str9, str10, str8, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getCid() {
            return this.cid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCls() {
            return this.cls;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTc() {
            return this.tc;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getDays() {
            return this.days;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getStart() {
            return this.start;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getGrace() {
            return this.grace;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getEnd() {
            return this.end;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final RegApprovedResponse copy(String cid, String cls, String tc, String days, String start, String grace, String end, int rid) {
            Intrinsics.checkNotNullParameter(cid, "cid");
            Intrinsics.checkNotNullParameter(cls, "cls");
            Intrinsics.checkNotNullParameter(tc, "tc");
            Intrinsics.checkNotNullParameter(days, "days");
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(grace, "grace");
            Intrinsics.checkNotNullParameter(end, "end");
            return new RegApprovedResponse(cid, cls, tc, days, start, grace, end, rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RegApprovedResponse)) {
                return false;
            }
            RegApprovedResponse regApprovedResponse = (RegApprovedResponse) other;
            return Intrinsics.areEqual(this.cid, regApprovedResponse.cid) && Intrinsics.areEqual(this.cls, regApprovedResponse.cls) && Intrinsics.areEqual(this.tc, regApprovedResponse.tc) && Intrinsics.areEqual(this.days, regApprovedResponse.days) && Intrinsics.areEqual(this.start, regApprovedResponse.start) && Intrinsics.areEqual(this.grace, regApprovedResponse.grace) && Intrinsics.areEqual(this.end, regApprovedResponse.end) && this.rid == regApprovedResponse.rid;
        }

        public int hashCode() {
            return (((((((((((((this.cid.hashCode() * 31) + this.cls.hashCode()) * 31) + this.tc.hashCode()) * 31) + this.days.hashCode()) * 31) + this.start.hashCode()) * 31) + this.grace.hashCode()) * 31) + this.end.hashCode()) * 31) + Integer.hashCode(this.rid);
        }

        public String toString() {
            return "RegApprovedResponse(cid=" + this.cid + ", cls=" + this.cls + ", tc=" + this.tc + ", days=" + this.days + ", start=" + this.start + ", grace=" + this.grace + ", end=" + this.end + ", rid=" + this.rid + ")";
        }

        public final String getCid() {
            return this.cid;
        }

        public final String getCls() {
            return this.cls;
        }

        public final String getTc() {
            return this.tc;
        }

        public final String getDays() {
            return this.days;
        }

        public final String getStart() {
            return this.start;
        }

        public final String getGrace() {
            return this.grace;
        }

        public final String getEnd() {
            return this.end;
        }

        public final int getRid() {
            return this.rid;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RegApprovedResponse(String cid, String cls, String tc, String days, String start, String grace, String end, int rid) {
            super(null);
            Intrinsics.checkNotNullParameter(cid, "cid");
            Intrinsics.checkNotNullParameter(cls, "cls");
            Intrinsics.checkNotNullParameter(tc, "tc");
            Intrinsics.checkNotNullParameter(days, "days");
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(grace, "grace");
            Intrinsics.checkNotNullParameter(end, "end");
            this.cid = cid;
            this.cls = cls;
            this.tc = tc;
            this.days = days;
            this.start = start;
            this.grace = grace;
            this.end = end;
            this.rid = rid;
        }

        public final String encode() {
            return "REG_APPROVED|" + this.cid + "|" + this.cls + "|" + this.tc + "|" + this.days + "|" + this.start + "|" + this.grace + "|" + this.end + "|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/ble/BlePacket$RegRejectedResponse;", "Lcom/example/ble/BlePacket;", "rid", "", "<init>", "(I)V", "getRid", "()I", "encode", "", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RegRejectedResponse extends BlePacket {
        public static final int $stable = 0;
        private final int rid;

        public static /* synthetic */ RegRejectedResponse copy$default(RegRejectedResponse regRejectedResponse, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = regRejectedResponse.rid;
            }
            return regRejectedResponse.copy(i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final RegRejectedResponse copy(int rid) {
            return new RegRejectedResponse(rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof RegRejectedResponse) && this.rid == ((RegRejectedResponse) other).rid;
        }

        public int hashCode() {
            return Integer.hashCode(this.rid);
        }

        public String toString() {
            return "RegRejectedResponse(rid=" + this.rid + ")";
        }

        public RegRejectedResponse(int rid) {
            super(null);
            this.rid = rid;
        }

        public final int getRid() {
            return this.rid;
        }

        public final String encode() {
            return "REG_REJECTED|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/example/ble/BlePacket$AtdAcceptedResponse;", "Lcom/example/ble/BlePacket;", "timestamp", "", "rid", "", "<init>", "(JI)V", "getTimestamp", "()J", "getRid", "()I", "encode", "", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class AtdAcceptedResponse extends BlePacket {
        public static final int $stable = 0;
        private final int rid;
        private final long timestamp;

        public static /* synthetic */ AtdAcceptedResponse copy$default(AtdAcceptedResponse atdAcceptedResponse, long j, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j = atdAcceptedResponse.timestamp;
            }
            if ((i2 & 2) != 0) {
                i = atdAcceptedResponse.rid;
            }
            return atdAcceptedResponse.copy(j, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getTimestamp() {
            return this.timestamp;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final AtdAcceptedResponse copy(long timestamp, int rid) {
            return new AtdAcceptedResponse(timestamp, rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AtdAcceptedResponse)) {
                return false;
            }
            AtdAcceptedResponse atdAcceptedResponse = (AtdAcceptedResponse) other;
            return this.timestamp == atdAcceptedResponse.timestamp && this.rid == atdAcceptedResponse.rid;
        }

        public int hashCode() {
            return (Long.hashCode(this.timestamp) * 31) + Integer.hashCode(this.rid);
        }

        public String toString() {
            return "AtdAcceptedResponse(timestamp=" + this.timestamp + ", rid=" + this.rid + ")";
        }

        public AtdAcceptedResponse(long timestamp, int rid) {
            super(null);
            this.timestamp = timestamp;
            this.rid = rid;
        }

        public final int getRid() {
            return this.rid;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public final String encode() {
            return "ATD_ACCEPTED|" + this.timestamp + "|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/ble/BlePacket$AtdRejectedResponse;", "Lcom/example/ble/BlePacket;", "rid", "", "<init>", "(I)V", "getRid", "()I", "encode", "", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class AtdRejectedResponse extends BlePacket {
        public static final int $stable = 0;
        private final int rid;

        public static /* synthetic */ AtdRejectedResponse copy$default(AtdRejectedResponse atdRejectedResponse, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = atdRejectedResponse.rid;
            }
            return atdRejectedResponse.copy(i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final AtdRejectedResponse copy(int rid) {
            return new AtdRejectedResponse(rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AtdRejectedResponse) && this.rid == ((AtdRejectedResponse) other).rid;
        }

        public int hashCode() {
            return Integer.hashCode(this.rid);
        }

        public String toString() {
            return "AtdRejectedResponse(rid=" + this.rid + ")";
        }

        public AtdRejectedResponse(int rid) {
            super(null);
            this.rid = rid;
        }

        public final int getRid() {
            return this.rid;
        }

        public final String encode() {
            return "ATD_REJECTED|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/example/ble/BlePacket$MarkedResponse;", "Lcom/example/ble/BlePacket;", "timestamp", "", "rid", "", "<init>", "(JI)V", "getTimestamp", "()J", "getRid", "()I", "encode", "", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class MarkedResponse extends BlePacket {
        public static final int $stable = 0;
        private final int rid;
        private final long timestamp;

        public static /* synthetic */ MarkedResponse copy$default(MarkedResponse markedResponse, long j, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j = markedResponse.timestamp;
            }
            if ((i2 & 2) != 0) {
                i = markedResponse.rid;
            }
            return markedResponse.copy(j, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getTimestamp() {
            return this.timestamp;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final MarkedResponse copy(long timestamp, int rid) {
            return new MarkedResponse(timestamp, rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MarkedResponse)) {
                return false;
            }
            MarkedResponse markedResponse = (MarkedResponse) other;
            return this.timestamp == markedResponse.timestamp && this.rid == markedResponse.rid;
        }

        public int hashCode() {
            return (Long.hashCode(this.timestamp) * 31) + Integer.hashCode(this.rid);
        }

        public String toString() {
            return "MarkedResponse(timestamp=" + this.timestamp + ", rid=" + this.rid + ")";
        }

        public MarkedResponse(long timestamp, int rid) {
            super(null);
            this.timestamp = timestamp;
            this.rid = rid;
        }

        public final int getRid() {
            return this.rid;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public final String encode() {
            return "MARKED|" + this.timestamp + "|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\bHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/example/ble/BlePacket$SchedResponse;", "Lcom/example/ble/BlePacket;", "days", "", "start", "grace", "end", "rid", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getDays", "()Ljava/lang/String;", "getStart", "getGrace", "getEnd", "getRid", "()I", "encode", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SchedResponse extends BlePacket {
        public static final int $stable = 0;
        private final String days;
        private final String end;
        private final String grace;
        private final int rid;
        private final String start;

        public static /* synthetic */ SchedResponse copy$default(SchedResponse schedResponse, String str, String str2, String str3, String str4, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = schedResponse.days;
            }
            if ((i2 & 2) != 0) {
                str2 = schedResponse.start;
            }
            if ((i2 & 4) != 0) {
                str3 = schedResponse.grace;
            }
            if ((i2 & 8) != 0) {
                str4 = schedResponse.end;
            }
            if ((i2 & 16) != 0) {
                i = schedResponse.rid;
            }
            int i3 = i;
            String str5 = str3;
            return schedResponse.copy(str, str2, str5, str4, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDays() {
            return this.days;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getStart() {
            return this.start;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getGrace() {
            return this.grace;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getEnd() {
            return this.end;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final SchedResponse copy(String days, String start, String grace, String end, int rid) {
            Intrinsics.checkNotNullParameter(days, "days");
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(grace, "grace");
            Intrinsics.checkNotNullParameter(end, "end");
            return new SchedResponse(days, start, grace, end, rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SchedResponse)) {
                return false;
            }
            SchedResponse schedResponse = (SchedResponse) other;
            return Intrinsics.areEqual(this.days, schedResponse.days) && Intrinsics.areEqual(this.start, schedResponse.start) && Intrinsics.areEqual(this.grace, schedResponse.grace) && Intrinsics.areEqual(this.end, schedResponse.end) && this.rid == schedResponse.rid;
        }

        public int hashCode() {
            return (((((((this.days.hashCode() * 31) + this.start.hashCode()) * 31) + this.grace.hashCode()) * 31) + this.end.hashCode()) * 31) + Integer.hashCode(this.rid);
        }

        public String toString() {
            return "SchedResponse(days=" + this.days + ", start=" + this.start + ", grace=" + this.grace + ", end=" + this.end + ", rid=" + this.rid + ")";
        }

        public final String getDays() {
            return this.days;
        }

        public final String getStart() {
            return this.start;
        }

        public final String getGrace() {
            return this.grace;
        }

        public final String getEnd() {
            return this.end;
        }

        public final int getRid() {
            return this.rid;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SchedResponse(String days, String start, String grace, String end, int rid) {
            super(null);
            Intrinsics.checkNotNullParameter(days, "days");
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(grace, "grace");
            Intrinsics.checkNotNullParameter(end, "end");
            this.days = days;
            this.start = start;
            this.grace = grace;
            this.end = end;
            this.rid = rid;
        }

        public final String encode() {
            return "SCHED|" + this.days + "|" + this.start + "|" + this.grace + "|" + this.end + "|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/ble/BlePacket$DoneResponse;", "Lcom/example/ble/BlePacket;", "rid", "", "<init>", "(I)V", "getRid", "()I", "encode", "", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class DoneResponse extends BlePacket {
        public static final int $stable = 0;
        private final int rid;

        public static /* synthetic */ DoneResponse copy$default(DoneResponse doneResponse, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = doneResponse.rid;
            }
            return doneResponse.copy(i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getRid() {
            return this.rid;
        }

        public final DoneResponse copy(int rid) {
            return new DoneResponse(rid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DoneResponse) && this.rid == ((DoneResponse) other).rid;
        }

        public int hashCode() {
            return Integer.hashCode(this.rid);
        }

        public String toString() {
            return "DoneResponse(rid=" + this.rid + ")";
        }

        public DoneResponse(int rid) {
            super(null);
            this.rid = rid;
        }

        public final int getRid() {
            return this.rid;
        }

        public final String encode() {
            return "DONE|" + this.rid;
        }
    }

    /* JADX INFO: compiled from: BlePacket.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/example/ble/BlePacket$UnknownPacket;", "Lcom/example/ble/BlePacket;", "raw", "", "<init>", "(Ljava/lang/String;)V", "getRaw", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class UnknownPacket extends BlePacket {
        public static final int $stable = 0;
        private final String raw;

        public static /* synthetic */ UnknownPacket copy$default(UnknownPacket unknownPacket, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = unknownPacket.raw;
            }
            return unknownPacket.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getRaw() {
            return this.raw;
        }

        public final UnknownPacket copy(String raw) {
            Intrinsics.checkNotNullParameter(raw, "raw");
            return new UnknownPacket(raw);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UnknownPacket) && Intrinsics.areEqual(this.raw, ((UnknownPacket) other).raw);
        }

        public int hashCode() {
            return this.raw.hashCode();
        }

        public String toString() {
            return "UnknownPacket(raw=" + this.raw + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnknownPacket(String raw) {
            super(null);
            Intrinsics.checkNotNullParameter(raw, "raw");
            this.raw = raw;
        }

        public final String getRaw() {
            return this.raw;
        }
    }
}
