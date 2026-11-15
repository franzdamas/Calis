package com.example.data.db;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006$"}, d2 = {"Lcom/example/data/db/AttendanceEntity;", "", "id", "", "classroomId", "", "studentId", "studentName", "date", NotificationCompat.CATEGORY_STATUS, "timestamp", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getId", "()J", "getClassroomId", "()Ljava/lang/String;", "getStudentId", "getStudentName", "getDate", "getStatus", "getTimestamp", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AttendanceEntity {
    public static final int $stable = 0;
    private final String classroomId;
    private final String date;
    private final long id;
    private final String status;
    private final String studentId;
    private final String studentName;
    private final long timestamp;

    public static /* synthetic */ AttendanceEntity copy$default(AttendanceEntity attendanceEntity, long j, String str, String str2, String str3, String str4, String str5, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = attendanceEntity.id;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            str = attendanceEntity.classroomId;
        }
        String str6 = str;
        if ((i & 4) != 0) {
            str2 = attendanceEntity.studentId;
        }
        String str7 = str2;
        if ((i & 8) != 0) {
            str3 = attendanceEntity.studentName;
        }
        return attendanceEntity.copy(j3, str6, str7, str3, (i & 16) != 0 ? attendanceEntity.date : str4, (i & 32) != 0 ? attendanceEntity.status : str5, (i & 64) != 0 ? attendanceEntity.timestamp : j2);
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
    public final String getDate() {
        return this.date;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    public final AttendanceEntity copy(long id, String classroomId, String studentId, String studentName, String date, String status, long timestamp) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(status, "status");
        return new AttendanceEntity(id, classroomId, studentId, studentName, date, status, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AttendanceEntity)) {
            return false;
        }
        AttendanceEntity attendanceEntity = (AttendanceEntity) other;
        return this.id == attendanceEntity.id && Intrinsics.areEqual(this.classroomId, attendanceEntity.classroomId) && Intrinsics.areEqual(this.studentId, attendanceEntity.studentId) && Intrinsics.areEqual(this.studentName, attendanceEntity.studentName) && Intrinsics.areEqual(this.date, attendanceEntity.date) && Intrinsics.areEqual(this.status, attendanceEntity.status) && this.timestamp == attendanceEntity.timestamp;
    }

    public int hashCode() {
        return (((((((((((Long.hashCode(this.id) * 31) + this.classroomId.hashCode()) * 31) + this.studentId.hashCode()) * 31) + this.studentName.hashCode()) * 31) + this.date.hashCode()) * 31) + this.status.hashCode()) * 31) + Long.hashCode(this.timestamp);
    }

    public String toString() {
        return "AttendanceEntity(id=" + this.id + ", classroomId=" + this.classroomId + ", studentId=" + this.studentId + ", studentName=" + this.studentName + ", date=" + this.date + ", status=" + this.status + ", timestamp=" + this.timestamp + ")";
    }

    public AttendanceEntity(long id, String classroomId, String studentId, String studentName, String date, String status, long timestamp) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(status, "status");
        this.id = id;
        this.classroomId = classroomId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.date = date;
        this.status = status;
        this.timestamp = timestamp;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ AttendanceEntity(long j, String str, String str2, String str3, String str4, String str5, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        long j3;
        if ((i & 1) == 0) {
            j3 = j;
        } else {
            j3 = 0;
        }
        this(j3, str, str2, str3, str4, str5, j2);
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

    public final String getDate() {
        return this.date;
    }

    public final String getStatus() {
        return this.status;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
