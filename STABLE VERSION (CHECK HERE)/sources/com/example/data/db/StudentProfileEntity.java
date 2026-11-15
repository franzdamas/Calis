package com.example.data.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006!"}, d2 = {"Lcom/example/data/db/StudentProfileEntity;", "", "sid", "", "fullName", "sex", "lrn", "birth", "", "createdAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getSid", "()Ljava/lang/String;", "getFullName", "getSex", "getLrn", "getBirth", "()J", "getCreatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StudentProfileEntity {
    public static final int $stable = 0;
    private final long birth;
    private final long createdAt;
    private final String fullName;
    private final String lrn;
    private final String sex;
    private final String sid;

    public static /* synthetic */ StudentProfileEntity copy$default(StudentProfileEntity studentProfileEntity, String str, String str2, String str3, String str4, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = studentProfileEntity.sid;
        }
        if ((i & 2) != 0) {
            str2 = studentProfileEntity.fullName;
        }
        if ((i & 4) != 0) {
            str3 = studentProfileEntity.sex;
        }
        if ((i & 8) != 0) {
            str4 = studentProfileEntity.lrn;
        }
        if ((i & 16) != 0) {
            j = studentProfileEntity.birth;
        }
        if ((i & 32) != 0) {
            j2 = studentProfileEntity.createdAt;
        }
        long j3 = j2;
        long j4 = j;
        return studentProfileEntity.copy(str, str2, str3, str4, j4, j3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSid() {
        return this.sid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getFullName() {
        return this.fullName;
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
    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final StudentProfileEntity copy(String sid, String fullName, String sex, String lrn, long birth, long createdAt) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(sex, "sex");
        Intrinsics.checkNotNullParameter(lrn, "lrn");
        return new StudentProfileEntity(sid, fullName, sex, lrn, birth, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StudentProfileEntity)) {
            return false;
        }
        StudentProfileEntity studentProfileEntity = (StudentProfileEntity) other;
        return Intrinsics.areEqual(this.sid, studentProfileEntity.sid) && Intrinsics.areEqual(this.fullName, studentProfileEntity.fullName) && Intrinsics.areEqual(this.sex, studentProfileEntity.sex) && Intrinsics.areEqual(this.lrn, studentProfileEntity.lrn) && this.birth == studentProfileEntity.birth && this.createdAt == studentProfileEntity.createdAt;
    }

    public int hashCode() {
        return (((((((((this.sid.hashCode() * 31) + this.fullName.hashCode()) * 31) + this.sex.hashCode()) * 31) + this.lrn.hashCode()) * 31) + Long.hashCode(this.birth)) * 31) + Long.hashCode(this.createdAt);
    }

    public String toString() {
        return "StudentProfileEntity(sid=" + this.sid + ", fullName=" + this.fullName + ", sex=" + this.sex + ", lrn=" + this.lrn + ", birth=" + this.birth + ", createdAt=" + this.createdAt + ")";
    }

    public StudentProfileEntity(String sid, String fullName, String sex, String lrn, long birth, long createdAt) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(sex, "sex");
        Intrinsics.checkNotNullParameter(lrn, "lrn");
        this.sid = sid;
        this.fullName = fullName;
        this.sex = sex;
        this.lrn = lrn;
        this.birth = birth;
        this.createdAt = createdAt;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ StudentProfileEntity(String str, String str2, String str3, String str4, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        long jCurrentTimeMillis;
        if ((i & 32) == 0) {
            jCurrentTimeMillis = j2;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        this(str, str2, str3, str4, j, jCurrentTimeMillis);
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getFullName() {
        return this.fullName;
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

    public final long getCreatedAt() {
        return this.createdAt;
    }
}
