package com.example.data.db;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: Daos.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H'J \u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\nJ\u0016\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\u0011¨\u0006\u0012À\u0006\u0003"}, d2 = {"Lcom/example/data/db/PendingRegistrationDao;", "", "getPendingRegistrations", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/data/db/PendingRegistrationEntity;", "classroomId", "", "getPendingRegistration", "studentId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPendingRegistration", "", "pending", "(Lcom/example/data/db/PendingRegistrationEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePendingRegistration", "clearPendingForClassroom", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PendingRegistrationDao {
    Object clearPendingForClassroom(String str, Continuation<? super Unit> continuation);

    Object deletePendingRegistration(String str, String str2, Continuation<? super Unit> continuation);

    Object getPendingRegistration(String str, String str2, Continuation<? super PendingRegistrationEntity> continuation);

    Flow<List<PendingRegistrationEntity>> getPendingRegistrations(String classroomId);

    Object insertPendingRegistration(PendingRegistrationEntity pendingRegistrationEntity, Continuation<? super Unit> continuation);
}
