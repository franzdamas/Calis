package com.example.data.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/example/data/db/TeacherEntity;", "", "tid", "", "fullName", "sex", "createdAt", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getTid", "()Ljava/lang/String;", "getFullName", "getSex", "getCreatedAt", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TeacherEntity {
    public static final int $stable = 0;
    private final long createdAt;
    private final String fullName;
    private final String sex;
    private final String tid;

    public static /* synthetic */ TeacherEntity copy$default(TeacherEntity teacherEntity, String str, String str2, String str3, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = teacherEntity.tid;
        }
        if ((i & 2) != 0) {
            str2 = teacherEntity.fullName;
        }
        if ((i & 4) != 0) {
            str3 = teacherEntity.sex;
        }
        if ((i & 8) != 0) {
            j = teacherEntity.createdAt;
        }
        String str4 = str3;
        return teacherEntity.copy(str, str2, str4, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTid() {
        return this.tid;
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
    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final TeacherEntity copy(String tid, String fullName, String sex, long createdAt) {
        Intrinsics.checkNotNullParameter(tid, "tid");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(sex, "sex");
        return new TeacherEntity(tid, fullName, sex, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TeacherEntity)) {
            return false;
        }
        TeacherEntity teacherEntity = (TeacherEntity) other;
        return Intrinsics.areEqual(this.tid, teacherEntity.tid) && Intrinsics.areEqual(this.fullName, teacherEntity.fullName) && Intrinsics.areEqual(this.sex, teacherEntity.sex) && this.createdAt == teacherEntity.createdAt;
    }

    public int hashCode() {
        return (((((this.tid.hashCode() * 31) + this.fullName.hashCode()) * 31) + this.sex.hashCode()) * 31) + Long.hashCode(this.createdAt);
    }

    public String toString() {
        return "TeacherEntity(tid=" + this.tid + ", fullName=" + this.fullName + ", sex=" + this.sex + ", createdAt=" + this.createdAt + ")";
    }

    public TeacherEntity(String tid, String fullName, String sex, long createdAt) {
        Intrinsics.checkNotNullParameter(tid, "tid");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(sex, "sex");
        this.tid = tid;
        this.fullName = fullName;
        this.sex = sex;
        this.createdAt = createdAt;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TeacherEntity(String str, String str2, String str3, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str4;
        long jCurrentTimeMillis;
        if ((i & 4) == 0) {
            str4 = str3;
        } else {
            str4 = "M";
        }
        if ((i & 8) == 0) {
            jCurrentTimeMillis = j;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        this(str, str2, str4, jCurrentTimeMillis);
    }

    public final String getTid() {
        return this.tid;
    }

    public final String getFullName() {
        return this.fullName;
    }

    public final String getSex() {
        return this.sex;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }
}
