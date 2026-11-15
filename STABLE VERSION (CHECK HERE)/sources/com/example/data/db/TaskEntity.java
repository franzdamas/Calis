package com.example.data.db;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u000bHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lcom/example/data/db/TaskEntity;", "", "tkid", "", "classroomId", LinkHeader.Parameters.Title, "description", "deadline", "", "datePosted", "maxScore", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJI)V", "getTkid", "()Ljava/lang/String;", "getClassroomId", "getTitle", "getDescription", "getDeadline", "()J", "getDatePosted", "getMaxScore", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TaskEntity {
    public static final int $stable = 0;
    private final String classroomId;
    private final long datePosted;
    private final long deadline;
    private final String description;
    private final int maxScore;
    private final String title;
    private final String tkid;

    public static /* synthetic */ TaskEntity copy$default(TaskEntity taskEntity, String str, String str2, String str3, String str4, long j, long j2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = taskEntity.tkid;
        }
        if ((i2 & 2) != 0) {
            str2 = taskEntity.classroomId;
        }
        if ((i2 & 4) != 0) {
            str3 = taskEntity.title;
        }
        if ((i2 & 8) != 0) {
            str4 = taskEntity.description;
        }
        if ((i2 & 16) != 0) {
            j = taskEntity.deadline;
        }
        if ((i2 & 32) != 0) {
            j2 = taskEntity.datePosted;
        }
        if ((i2 & 64) != 0) {
            i = taskEntity.maxScore;
        }
        int i3 = i;
        long j3 = j2;
        long j4 = j;
        return taskEntity.copy(str, str2, str3, str4, j4, j3, i3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTkid() {
        return this.tkid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getClassroomId() {
        return this.classroomId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getDeadline() {
        return this.deadline;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getDatePosted() {
        return this.datePosted;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getMaxScore() {
        return this.maxScore;
    }

    public final TaskEntity copy(String tkid, String classroomId, String title, String description, long deadline, long datePosted, int maxScore) {
        Intrinsics.checkNotNullParameter(tkid, "tkid");
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        return new TaskEntity(tkid, classroomId, title, description, deadline, datePosted, maxScore);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskEntity)) {
            return false;
        }
        TaskEntity taskEntity = (TaskEntity) other;
        return Intrinsics.areEqual(this.tkid, taskEntity.tkid) && Intrinsics.areEqual(this.classroomId, taskEntity.classroomId) && Intrinsics.areEqual(this.title, taskEntity.title) && Intrinsics.areEqual(this.description, taskEntity.description) && this.deadline == taskEntity.deadline && this.datePosted == taskEntity.datePosted && this.maxScore == taskEntity.maxScore;
    }

    public int hashCode() {
        return (((((((((((this.tkid.hashCode() * 31) + this.classroomId.hashCode()) * 31) + this.title.hashCode()) * 31) + this.description.hashCode()) * 31) + Long.hashCode(this.deadline)) * 31) + Long.hashCode(this.datePosted)) * 31) + Integer.hashCode(this.maxScore);
    }

    public String toString() {
        return "TaskEntity(tkid=" + this.tkid + ", classroomId=" + this.classroomId + ", title=" + this.title + ", description=" + this.description + ", deadline=" + this.deadline + ", datePosted=" + this.datePosted + ", maxScore=" + this.maxScore + ")";
    }

    public TaskEntity(String tkid, String classroomId, String title, String description, long deadline, long datePosted, int maxScore) {
        Intrinsics.checkNotNullParameter(tkid, "tkid");
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        this.tkid = tkid;
        this.classroomId = classroomId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.datePosted = datePosted;
        this.maxScore = maxScore;
    }

    public final String getTkid() {
        return this.tkid;
    }

    public final String getClassroomId() {
        return this.classroomId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getDescription() {
        return this.description;
    }

    public final long getDeadline() {
        return this.deadline;
    }

    public final long getDatePosted() {
        return this.datePosted;
    }

    public final int getMaxScore() {
        return this.maxScore;
    }
}
