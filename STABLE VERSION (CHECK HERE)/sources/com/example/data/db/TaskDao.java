package com.example.data.db;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: Daos.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H'J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H§@¢\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\tJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\t¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lcom/example/data/db/TaskDao;", "", "getTasksForClassroom", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/data/db/TaskEntity;", "classroomId", "", "getTasksForClassroomSync", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTasksSync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTaskById", "tkid", "insertTask", "", "task", "(Lcom/example/data/db/TaskEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTask", "deleteTaskById", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface TaskDao {
    Object deleteTaskById(String str, Continuation<? super Unit> continuation);

    Object getAllTasksSync(Continuation<? super List<TaskEntity>> continuation);

    Object getTaskById(String str, Continuation<? super TaskEntity> continuation);

    Flow<List<TaskEntity>> getTasksForClassroom(String classroomId);

    Object getTasksForClassroomSync(String str, Continuation<? super List<TaskEntity>> continuation);

    Object insertTask(TaskEntity taskEntity, Continuation<? super Unit> continuation);

    Object updateTask(TaskEntity taskEntity, Continuation<? super Unit> continuation);
}
