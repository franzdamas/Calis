package com.example.data.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003JY\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006'"}, d2 = {"Lcom/example/data/db/ApprovedStudentEntity;", "", "id", "", "classroomId", "", "studentId", "studentName", "sex", "lrn", "birth", "approvedAt", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getId", "()J", "getClassroomId", "()Ljava/lang/String;", "getStudentId", "getStudentName", "getSex", "getLrn", "getBirth", "getApprovedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ApprovedStudentEntity {
    public static final int $stable = 0;
    private final long approvedAt;
    private final long birth;
    private final String classroomId;
    private final long id;
    private final String lrn;
    private final String sex;
    private final String studentId;
    private final String studentName;

    public static /* synthetic */ ApprovedStudentEntity copy$default(ApprovedStudentEntity approvedStudentEntity, long j, String str, String str2, String str3, String str4, String str5, long j2, long j3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = approvedStudentEntity.id;
        }
        return approvedStudentEntity.copy(j, (i & 2) != 0 ? approvedStudentEntity.classroomId : str, (i & 4) != 0 ? approvedStudentEntity.studentId : str2, (i & 8) != 0 ? approvedStudentEntity.studentName : str3, (i & 16) != 0 ? approvedStudentEntity.sex : str4, (i & 32) != 0 ? approvedStudentEntity.lrn : str5, (i & 64) != 0 ? approvedStudentEntity.birth : j2, (i & 128) != 0 ? approvedStudentEntity.approvedAt : j3);
    }

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
    public final long getApprovedAt() {
        return this.approvedAt;
    }

    public final ApprovedStudentEntity copy(long id, String classroomId, String studentId, String studentName, String sex, String lrn, long birth, long approvedAt) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(sex, "sex");
        Intrinsics.checkNotNullParameter(lrn, "lrn");
        return new ApprovedStudentEntity(id, classroomId, studentId, studentName, sex, lrn, birth, approvedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ApprovedStudentEntity)) {
            return false;
        }
        ApprovedStudentEntity approvedStudentEntity = (ApprovedStudentEntity) other;
        return this.id == approvedStudentEntity.id && Intrinsics.areEqual(this.classroomId, approvedStudentEntity.classroomId) && Intrinsics.areEqual(this.studentId, approvedStudentEntity.studentId) && Intrinsics.areEqual(this.studentName, approvedStudentEntity.studentName) && Intrinsics.areEqual(this.sex, approvedStudentEntity.sex) && Intrinsics.areEqual(this.lrn, approvedStudentEntity.lrn) && this.birth == approvedStudentEntity.birth && this.approvedAt == approvedStudentEntity.approvedAt;
    }

    public int hashCode() {
        return (((((((((((((Long.hashCode(this.id) * 31) + this.classroomId.hashCode()) * 31) + this.studentId.hashCode()) * 31) + this.studentName.hashCode()) * 31) + this.sex.hashCode()) * 31) + this.lrn.hashCode()) * 31) + Long.hashCode(this.birth)) * 31) + Long.hashCode(this.approvedAt);
    }

    public String toString() {
        return "ApprovedStudentEntity(id=" + this.id + ", classroomId=" + this.classroomId + ", studentId=" + this.studentId + ", studentName=" + this.studentName + ", sex=" + this.sex + ", lrn=" + this.lrn + ", birth=" + this.birth + ", approvedAt=" + this.approvedAt + ")";
    }

    public ApprovedStudentEntity(long id, String classroomId, String studentId, String studentName, String sex, String lrn, long birth, long approvedAt) {
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
        this.approvedAt = approvedAt;
    }

    public /* synthetic */ ApprovedStudentEntity(long j, String str, String str2, String str3, String str4, String str5, long j2, long j3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, str, str2, str3, str4, str5, (i & 64) != 0 ? 0L : j2, (i & 128) != 0 ? System.currentTimeMillis() : j3);
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

    public final long getApprovedAt() {
        return this.approvedAt;
    }
}
