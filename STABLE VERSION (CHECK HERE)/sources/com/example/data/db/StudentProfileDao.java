package com.example.data.db;

import com.google.android.gms.common.Scopes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: Daos.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H'J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004H§@¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH§@¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H§@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H§@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0010\u001a\u00020\fH§@¢\u0006\u0002\u0010\u0006¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/example/data/db/StudentProfileDao;", "", "getStudentProfile", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/data/db/StudentProfileEntity;", "getStudentProfileOnce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStudentProfileById", "sid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertStudentProfile", "", Scopes.PROFILE, "(Lcom/example/data/db/StudentProfileEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStudentProfile", "clearStudentProfiles", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface StudentProfileDao {
    Object clearStudentProfiles(Continuation<? super Unit> continuation);

    Flow<StudentProfileEntity> getStudentProfile();

    Object getStudentProfileById(String str, Continuation<? super StudentProfileEntity> continuation);

    Object getStudentProfileOnce(Continuation<? super StudentProfileEntity> continuation);

    Object insertStudentProfile(StudentProfileEntity studentProfileEntity, Continuation<? super Unit> continuation);

    Object updateStudentProfile(StudentProfileEntity studentProfileEntity, Continuation<? super Unit> continuation);
}
