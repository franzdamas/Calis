package com.example.data.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\fHÆ\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010!\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\tHÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0018¨\u0006%"}, d2 = {"Lcom/example/data/db/TaskSubmissionEntity;", "", "id", "", "taskId", "", "studentId", "studentName", "score", "", "timestamp", "isMarked", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;IJZ)V", "getId", "()J", "getTaskId", "()Ljava/lang/String;", "getStudentId", "getStudentName", "getScore", "()I", "getTimestamp", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TaskSubmissionEntity {
    public static final int $stable = 0;
    private final long id;
    private final boolean isMarked;
    private final int score;
    private final String studentId;
    private final String studentName;
    private final String taskId;
    private final long timestamp;

    public static /* synthetic */ TaskSubmissionEntity copy$default(TaskSubmissionEntity taskSubmissionEntity, long j, String str, String str2, String str3, int i, long j2, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = taskSubmissionEntity.id;
        }
        long j3 = j;
        if ((i2 & 2) != 0) {
            str = taskSubmissionEntity.taskId;
        }
        String str4 = str;
        if ((i2 & 4) != 0) {
            str2 = taskSubmissionEntity.studentId;
        }
        String str5 = str2;
        if ((i2 & 8) != 0) {
            str3 = taskSubmissionEntity.studentName;
        }
        return taskSubmissionEntity.copy(j3, str4, str5, str3, (i2 & 16) != 0 ? taskSubmissionEntity.score : i, (i2 & 32) != 0 ? taskSubmissionEntity.timestamp : j2, (i2 & 64) != 0 ? taskSubmissionEntity.isMarked : z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTaskId() {
        return this.taskId;
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
    public final int getScore() {
        return this.score;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getIsMarked() {
        return this.isMarked;
    }

    public final TaskSubmissionEntity copy(long id, String taskId, String studentId, String studentName, int score, long timestamp, boolean isMarked) {
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        return new TaskSubmissionEntity(id, taskId, studentId, studentName, score, timestamp, isMarked);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskSubmissionEntity)) {
            return false;
        }
        TaskSubmissionEntity taskSubmissionEntity = (TaskSubmissionEntity) other;
        return this.id == taskSubmissionEntity.id && Intrinsics.areEqual(this.taskId, taskSubmissionEntity.taskId) && Intrinsics.areEqual(this.studentId, taskSubmissionEntity.studentId) && Intrinsics.areEqual(this.studentName, taskSubmissionEntity.studentName) && this.score == taskSubmissionEntity.score && this.timestamp == taskSubmissionEntity.timestamp && this.isMarked == taskSubmissionEntity.isMarked;
    }

    public int hashCode() {
        return (((((((((((Long.hashCode(this.id) * 31) + this.taskId.hashCode()) * 31) + this.studentId.hashCode()) * 31) + this.studentName.hashCode()) * 31) + Integer.hashCode(this.score)) * 31) + Long.hashCode(this.timestamp)) * 31) + Boolean.hashCode(this.isMarked);
    }

    public String toString() {
        return "TaskSubmissionEntity(id=" + this.id + ", taskId=" + this.taskId + ", studentId=" + this.studentId + ", studentName=" + this.studentName + ", score=" + this.score + ", timestamp=" + this.timestamp + ", isMarked=" + this.isMarked + ")";
    }

    public TaskSubmissionEntity(long id, String taskId, String studentId, String studentName, int score, long timestamp, boolean isMarked) {
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        this.id = id;
        this.taskId = taskId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.score = score;
        this.timestamp = timestamp;
        this.isMarked = isMarked;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TaskSubmissionEntity(long j, String str, String str2, String str3, int i, long j2, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        long j3;
        boolean z2;
        if ((i2 & 1) == 0) {
            j3 = j;
        } else {
            j3 = 0;
        }
        if ((i2 & 64) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
        this(j3, str, str2, str3, i, j2, z2);
    }

    public final long getId() {
        return this.id;
    }

    public final String getTaskId() {
        return this.taskId;
    }

    public final String getStudentId() {
        return this.studentId;
    }

    public final String getStudentName() {
        return this.studentName;
    }

    public final int getScore() {
        return this.score;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final boolean isMarked() {
        return this.isMarked;
    }
}
