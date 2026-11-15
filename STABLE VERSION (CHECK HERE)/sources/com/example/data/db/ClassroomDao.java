package com.example.data.db;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: Daos.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H'J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004H§@¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H'J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004H§@¢\u0006\u0002\u0010\fJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0010H§@¢\u0006\u0002\u0010\fJ\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H§@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H§@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\t¨\u0006\u0017À\u0006\u0003"}, d2 = {"Lcom/example/data/db/ClassroomDao;", "", "getClassroomForTeacher", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/data/db/ClassroomEntity;", "teacherId", "", "getClassroomById", "cid", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveBoundClassroom", "getActiveBoundClassroomSync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAnyClassroom", "getAnyClassroomSync", "getAllClassroomsSync", "", "insertClassroom", "", "classroom", "(Lcom/example/data/db/ClassroomEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateClassroom", "deleteClassroomById", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ClassroomDao {
    Object deleteClassroomById(String str, Continuation<? super Unit> continuation);

    Flow<ClassroomEntity> getActiveBoundClassroom();

    Object getActiveBoundClassroomSync(Continuation<? super ClassroomEntity> continuation);

    Object getAllClassroomsSync(Continuation<? super List<ClassroomEntity>> continuation);

    Flow<ClassroomEntity> getAnyClassroom();

    Object getAnyClassroomSync(Continuation<? super ClassroomEntity> continuation);

    Object getClassroomById(String str, Continuation<? super ClassroomEntity> continuation);

    Flow<ClassroomEntity> getClassroomForTeacher(String teacherId);

    Object insertClassroom(ClassroomEntity classroomEntity, Continuation<? super Unit> continuation);

    Object updateClassroom(ClassroomEntity classroomEntity, Continuation<? super Unit> continuation);
}
