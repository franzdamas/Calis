package com.example.data.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/example/data/db/SyncRecordEntity;", "", "id", "", "studentId", "", "classroomId", "lastScheduleSyncedAt", "lastTaskSyncedAt", "<init>", "(JLjava/lang/String;Ljava/lang/String;JJ)V", "getId", "()J", "getStudentId", "()Ljava/lang/String;", "getClassroomId", "getLastScheduleSyncedAt", "getLastTaskSyncedAt", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SyncRecordEntity {
    public static final int $stable = 0;
    private final String classroomId;
    private final long id;
    private final long lastScheduleSyncedAt;
    private final long lastTaskSyncedAt;
    private final String studentId;

    public static /* synthetic */ SyncRecordEntity copy$default(SyncRecordEntity syncRecordEntity, long j, String str, String str2, long j2, long j3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = syncRecordEntity.id;
        }
        long j4 = j;
        if ((i & 2) != 0) {
            str = syncRecordEntity.studentId;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            str2 = syncRecordEntity.classroomId;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            j2 = syncRecordEntity.lastScheduleSyncedAt;
        }
        return syncRecordEntity.copy(j4, str3, str4, j2, (i & 16) != 0 ? syncRecordEntity.lastTaskSyncedAt : j3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getStudentId() {
        return this.studentId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getClassroomId() {
        return this.classroomId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getLastScheduleSyncedAt() {
        return this.lastScheduleSyncedAt;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getLastTaskSyncedAt() {
        return this.lastTaskSyncedAt;
    }

    public final SyncRecordEntity copy(long id, String studentId, String classroomId, long lastScheduleSyncedAt, long lastTaskSyncedAt) {
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        return new SyncRecordEntity(id, studentId, classroomId, lastScheduleSyncedAt, lastTaskSyncedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyncRecordEntity)) {
            return false;
        }
        SyncRecordEntity syncRecordEntity = (SyncRecordEntity) other;
        return this.id == syncRecordEntity.id && Intrinsics.areEqual(this.studentId, syncRecordEntity.studentId) && Intrinsics.areEqual(this.classroomId, syncRecordEntity.classroomId) && this.lastScheduleSyncedAt == syncRecordEntity.lastScheduleSyncedAt && this.lastTaskSyncedAt == syncRecordEntity.lastTaskSyncedAt;
    }

    public int hashCode() {
        return (((((((Long.hashCode(this.id) * 31) + this.studentId.hashCode()) * 31) + this.classroomId.hashCode()) * 31) + Long.hashCode(this.lastScheduleSyncedAt)) * 31) + Long.hashCode(this.lastTaskSyncedAt);
    }

    public String toString() {
        return "SyncRecordEntity(id=" + this.id + ", studentId=" + this.studentId + ", classroomId=" + this.classroomId + ", lastScheduleSyncedAt=" + this.lastScheduleSyncedAt + ", lastTaskSyncedAt=" + this.lastTaskSyncedAt + ")";
    }

    public SyncRecordEntity(long id, String studentId, String classroomId, long lastScheduleSyncedAt, long lastTaskSyncedAt) {
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        this.id = id;
        this.studentId = studentId;
        this.classroomId = classroomId;
        this.lastScheduleSyncedAt = lastScheduleSyncedAt;
        this.lastTaskSyncedAt = lastTaskSyncedAt;
    }

    public /* synthetic */ SyncRecordEntity(long j, String str, String str2, long j2, long j3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, str, str2, (i & 8) != 0 ? 0L : j2, (i & 16) != 0 ? 0L : j3);
    }

    public final long getId() {
        return this.id;
    }

    public final String getStudentId() {
        return this.studentId;
    }

    public final String getClassroomId() {
        return this.classroomId;
    }

    public final long getLastScheduleSyncedAt() {
        return this.lastScheduleSyncedAt;
    }

    public final long getLastTaskSyncedAt() {
        return this.lastTaskSyncedAt;
    }
}
