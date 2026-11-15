package com.example.data.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BS\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\fHÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003Jc\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\fHÖ\u0001J\t\u0010*\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011¨\u0006+"}, d2 = {"Lcom/example/data/db/PendingRegistrationEntity;", "", "id", "", "classroomId", "", "studentId", "studentName", "sex", "lrn", "birth", "rid", "", "requestedAt", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JIJ)V", "getId", "()J", "getClassroomId", "()Ljava/lang/String;", "getStudentId", "getStudentName", "getSex", "getLrn", "getBirth", "getRid", "()I", "getRequestedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PendingRegistrationEntity {
    public static final int $stable = 0;
    private final long birth;
    private final String classroomId;
    private final long id;
    private final String lrn;
    private final long requestedAt;
    private final int rid;
    private final String sex;
    private final String studentId;
    private final String studentName;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getClassroomId() {
        return this.classroomId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getStudentId() {
        return this.studentId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getStudentName() {
        return this.studentName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSex() {
        return this.sex;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getLrn() {
        return this.lrn;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getBirth() {
        return this.birth;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getRid() {
        return this.rid;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getRequestedAt() {
        return this.requestedAt;
    }

    public final PendingRegistrationEntity copy(long id, String classroomId, String studentId, String studentName, String sex, String lrn, long birth, int rid, long requestedAt) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(sex, "sex");
        Intrinsics.checkNotNullParameter(lrn, "lrn");
        return new PendingRegistrationEntity(id, classroomId, studentId, studentName, sex, lrn, birth, rid, requestedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PendingRegistrationEntity)) {
            return false;
        }
        PendingRegistrationEntity pendingRegistrationEntity = (PendingRegistrationEntity) other;
        return this.id == pendingRegistrationEntity.id && Intrinsics.areEqual(this.classroomId, pendingRegistrationEntity.classroomId) && Intrinsics.areEqual(this.studentId, pendingRegistrationEntity.studentId) && Intrinsics.areEqual(this.studentName, pendingRegistrationEntity.studentName) && Intrinsics.areEqual(this.sex, pendingRegistrationEntity.sex) && Intrinsics.areEqual(this.lrn, pendingRegistrationEntity.lrn) && this.birth == pendingRegistrationEntity.birth && this.rid == pendingRegistrationEntity.rid && this.requestedAt == pendingRegistrationEntity.requestedAt;
    }

    public int hashCode() {
        return (((((((((((((((Long.hashCode(this.id) * 31) + this.classroomId.hashCode()) * 31) + this.studentId.hashCode()) * 31) + this.studentName.hashCode()) * 31) + this.sex.hashCode()) * 31) + this.lrn.hashCode()) * 31) + Long.hashCode(this.birth)) * 31) + Integer.hashCode(this.rid)) * 31) + Long.hashCode(this.requestedAt);
    }

    public String toString() {
        return "PendingRegistrationEntity(id=" + this.id + ", classroomId=" + this.classroomId + ", studentId=" + this.studentId + ", studentName=" + this.studentName + ", sex=" + this.sex + ", lrn=" + this.lrn + ", birth=" + this.birth + ", rid=" + this.rid + ", requestedAt=" + this.requestedAt + ")";
    }

    public PendingRegistrationEntity(long id, String classroomId, String studentId, String studentName, String sex, String lrn, long birth, int rid, long requestedAt) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(sex, "sex");
        Intrinsics.checkNotNullParameter(lrn, "lrn");
        this.id = id;
        this.classroomId = classroomId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.lrn = lrn;
        this.birth = birth;
        this.rid = rid;
        this.requestedAt = requestedAt;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PendingRegistrationEntity(long j, String str, String str2, String str3, String str4, String str5, long j2, int i, long j3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        long j4;
        long jCurrentTimeMillis;
        if ((i2 & 1) == 0) {
            j4 = j;
        } else {
            j4 = 0;
        }
        if ((i2 & 256) == 0) {
            jCurrentTimeMillis = j3;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        this(j4, str, str2, str3, str4, str5, j2, i, jCurrentTimeMillis);
    }

    public final long getId() {
        return this.id;
    }

    public final String getClassroomId() {
        return this.classroomId;
    }

    public final String getStudentId() {
        return this.studentId;
    }

    public final String getStudentName() {
        return this.studentName;
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

    public final long getRequestedAt() {
        return this.requestedAt;
    }
}
