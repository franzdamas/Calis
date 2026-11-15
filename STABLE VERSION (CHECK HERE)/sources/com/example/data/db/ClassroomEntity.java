package com.example.data.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\fHÆ\u0003J\t\u0010%\u001a\u00020\fHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jy\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010(\u001a\u00020\f2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u001aR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u001aR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012¨\u0006-"}, d2 = {"Lcom/example/data/db/ClassroomEntity;", "", "cid", "", "teacherId", "classroomName", "teacherName", "days", "startTime", "graceTime", "endTime", "isActiveSession", "", "isBound", "deviceAddress", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;)V", "getCid", "()Ljava/lang/String;", "getTeacherId", "getClassroomName", "getTeacherName", "getDays", "getStartTime", "getGraceTime", "getEndTime", "()Z", "getDeviceAddress", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ClassroomEntity {
    public static final int $stable = 0;
    private final String cid;
    private final String classroomName;
    private final String days;
    private final String deviceAddress;
    private final String endTime;
    private final String graceTime;
    private final boolean isActiveSession;
    private final boolean isBound;
    private final String startTime;
    private final String teacherId;
    private final String teacherName;

    public static /* synthetic */ ClassroomEntity copy$default(ClassroomEntity classroomEntity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, String str9, int i, Object obj) {
        if ((i & 1) != 0) {
            str = classroomEntity.cid;
        }
        if ((i & 2) != 0) {
            str2 = classroomEntity.teacherId;
        }
        if ((i & 4) != 0) {
            str3 = classroomEntity.classroomName;
        }
        if ((i & 8) != 0) {
            str4 = classroomEntity.teacherName;
        }
        if ((i & 16) != 0) {
            str5 = classroomEntity.days;
        }
        if ((i & 32) != 0) {
            str6 = classroomEntity.startTime;
        }
        if ((i & 64) != 0) {
            str7 = classroomEntity.graceTime;
        }
        if ((i & 128) != 0) {
            str8 = classroomEntity.endTime;
        }
        if ((i & 256) != 0) {
            z = classroomEntity.isActiveSession;
        }
        if ((i & 512) != 0) {
            z2 = classroomEntity.isBound;
        }
        if ((i & 1024) != 0) {
            str9 = classroomEntity.deviceAddress;
        }
        boolean z3 = z2;
        String str10 = str9;
        String str11 = str8;
        boolean z4 = z;
        String str12 = str6;
        String str13 = str7;
        String str14 = str5;
        String str15 = str3;
        return classroomEntity.copy(str, str2, str15, str4, str14, str12, str13, str11, z4, z3, str10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCid() {
        return this.cid;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getIsBound() {
        return this.isBound;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTeacherId() {
        return this.teacherId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getClassroomName() {
        return this.classroomName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTeacherName() {
        return this.teacherName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDays() {
        return this.days;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getGraceTime() {
        return this.graceTime;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getEndTime() {
        return this.endTime;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getIsActiveSession() {
        return this.isActiveSession;
    }

    public final ClassroomEntity copy(String cid, String teacherId, String classroomName, String teacherName, String days, String startTime, String graceTime, String endTime, boolean isActiveSession, boolean isBound, String deviceAddress) {
        Intrinsics.checkNotNullParameter(cid, "cid");
        Intrinsics.checkNotNullParameter(teacherId, "teacherId");
        Intrinsics.checkNotNullParameter(classroomName, "classroomName");
        Intrinsics.checkNotNullParameter(teacherName, "teacherName");
        Intrinsics.checkNotNullParameter(days, "days");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(graceTime, "graceTime");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        return new ClassroomEntity(cid, teacherId, classroomName, teacherName, days, startTime, graceTime, endTime, isActiveSession, isBound, deviceAddress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassroomEntity)) {
            return false;
        }
        ClassroomEntity classroomEntity = (ClassroomEntity) other;
        return Intrinsics.areEqual(this.cid, classroomEntity.cid) && Intrinsics.areEqual(this.teacherId, classroomEntity.teacherId) && Intrinsics.areEqual(this.classroomName, classroomEntity.classroomName) && Intrinsics.areEqual(this.teacherName, classroomEntity.teacherName) && Intrinsics.areEqual(this.days, classroomEntity.days) && Intrinsics.areEqual(this.startTime, classroomEntity.startTime) && Intrinsics.areEqual(this.graceTime, classroomEntity.graceTime) && Intrinsics.areEqual(this.endTime, classroomEntity.endTime) && this.isActiveSession == classroomEntity.isActiveSession && this.isBound == classroomEntity.isBound && Intrinsics.areEqual(this.deviceAddress, classroomEntity.deviceAddress);
    }

    public int hashCode() {
        return (((((((((((((((((((this.cid.hashCode() * 31) + this.teacherId.hashCode()) * 31) + this.classroomName.hashCode()) * 31) + this.teacherName.hashCode()) * 31) + this.days.hashCode()) * 31) + this.startTime.hashCode()) * 31) + this.graceTime.hashCode()) * 31) + this.endTime.hashCode()) * 31) + Boolean.hashCode(this.isActiveSession)) * 31) + Boolean.hashCode(this.isBound)) * 31) + (this.deviceAddress == null ? 0 : this.deviceAddress.hashCode());
    }

    public String toString() {
        return "ClassroomEntity(cid=" + this.cid + ", teacherId=" + this.teacherId + ", classroomName=" + this.classroomName + ", teacherName=" + this.teacherName + ", days=" + this.days + ", startTime=" + this.startTime + ", graceTime=" + this.graceTime + ", endTime=" + this.endTime + ", isActiveSession=" + this.isActiveSession + ", isBound=" + this.isBound + ", deviceAddress=" + this.deviceAddress + ")";
    }

    public ClassroomEntity(String cid, String teacherId, String classroomName, String teacherName, String days, String startTime, String graceTime, String endTime, boolean isActiveSession, boolean isBound, String deviceAddress) {
        Intrinsics.checkNotNullParameter(cid, "cid");
        Intrinsics.checkNotNullParameter(teacherId, "teacherId");
        Intrinsics.checkNotNullParameter(classroomName, "classroomName");
        Intrinsics.checkNotNullParameter(teacherName, "teacherName");
        Intrinsics.checkNotNullParameter(days, "days");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(graceTime, "graceTime");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        this.cid = cid;
        this.teacherId = teacherId;
        this.classroomName = classroomName;
        this.teacherName = teacherName;
        this.days = days;
        this.startTime = startTime;
        this.graceTime = graceTime;
        this.endTime = endTime;
        this.isActiveSession = isActiveSession;
        this.isBound = isBound;
        this.deviceAddress = deviceAddress;
    }

    public /* synthetic */ ClassroomEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, String str9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, (i & 256) != 0 ? false : z, (i & 512) != 0 ? false : z2, (i & 1024) != 0 ? null : str9);
    }

    public final String getCid() {
        return this.cid;
    }

    public final String getTeacherId() {
        return this.teacherId;
    }

    public final String getClassroomName() {
        return this.classroomName;
    }

    public final String getTeacherName() {
        return this.teacherName;
    }

    public final String getDays() {
        return this.days;
    }

    public final String getStartTime() {
        return this.startTime;
    }

    public final String getGraceTime() {
        return this.graceTime;
    }

    public final String getEndTime() {
        return this.endTime;
    }

    public final boolean isActiveSession() {
        return this.isActiveSession;
    }

    public final boolean isBound() {
        return this.isBound;
    }

    public final String getDeviceAddress() {
        return this.deviceAddress;
    }
}
