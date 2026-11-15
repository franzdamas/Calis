package com.example.data.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/example/data/db/TaskDeliveryEntity;", "", "taskId", "", "studentId", "received", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getTaskId", "()Ljava/lang/String;", "getStudentId", "getReceived", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TaskDeliveryEntity {
    public static final int $stable = 0;
    private final boolean received;
    private final String studentId;
    private final String taskId;

    public static /* synthetic */ TaskDeliveryEntity copy$default(TaskDeliveryEntity taskDeliveryEntity, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = taskDeliveryEntity.taskId;
        }
        if ((i & 2) != 0) {
            str2 = taskDeliveryEntity.studentId;
        }
        if ((i & 4) != 0) {
            z = taskDeliveryEntity.received;
        }
        return taskDeliveryEntity.copy(str, str2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTaskId() {
        return this.taskId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getStudentId() {
        return this.studentId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getReceived() {
        return this.received;
    }

    public final TaskDeliveryEntity copy(String taskId, String studentId, boolean received) {
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        return new TaskDeliveryEntity(taskId, studentId, received);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskDeliveryEntity)) {
            return false;
        }
        TaskDeliveryEntity taskDeliveryEntity = (TaskDeliveryEntity) other;
        return Intrinsics.areEqual(this.taskId, taskDeliveryEntity.taskId) && Intrinsics.areEqual(this.studentId, taskDeliveryEntity.studentId) && this.received == taskDeliveryEntity.received;
    }

    public int hashCode() {
        return (((this.taskId.hashCode() * 31) + this.studentId.hashCode()) * 31) + Boolean.hashCode(this.received);
    }

    public String toString() {
        return "TaskDeliveryEntity(taskId=" + this.taskId + ", studentId=" + this.studentId + ", received=" + this.received + ")";
    }

    public TaskDeliveryEntity(String taskId, String studentId, boolean received) {
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        this.taskId = taskId;
        this.studentId = studentId;
        this.received = received;
    }

    public /* synthetic */ TaskDeliveryEntity(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? false : z);
    }

    public final String getTaskId() {
        return this.taskId;
    }

    public final String getStudentId() {
        return this.studentId;
    }

    public final boolean getReceived() {
        return this.received;
    }
}
