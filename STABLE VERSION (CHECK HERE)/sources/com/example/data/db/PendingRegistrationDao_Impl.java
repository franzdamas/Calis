package com.example.data.db;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: PendingRegistrationDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J \u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/example/data/db/PendingRegistrationDao_Impl;", "Lcom/example/data/db/PendingRegistrationDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfPendingRegistrationEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/db/PendingRegistrationEntity;", "insertPendingRegistration", "", "pending", "(Lcom/example/data/db/PendingRegistrationEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPendingRegistrations", "Lkotlinx/coroutines/flow/Flow;", "", "classroomId", "", "getPendingRegistration", "studentId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePendingRegistration", "clearPendingForClassroom", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PendingRegistrationDao_Impl implements PendingRegistrationDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<PendingRegistrationEntity> __insertAdapterOfPendingRegistrationEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public PendingRegistrationDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfPendingRegistrationEntity = new EntityInsertAdapter<PendingRegistrationEntity>() { // from class: com.example.data.db.PendingRegistrationDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `pending_registrations` (`id`,`classroomId`,`studentId`,`studentName`,`sex`,`lrn`,`birth`,`rid`,`requestedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, PendingRegistrationEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6928bindLong(1, entity.getId());
                statement.mo6930bindText(2, entity.getClassroomId());
                statement.mo6930bindText(3, entity.getStudentId());
                statement.mo6930bindText(4, entity.getStudentName());
                statement.mo6930bindText(5, entity.getSex());
                statement.mo6930bindText(6, entity.getLrn());
                statement.mo6928bindLong(7, entity.getBirth());
                statement.mo6928bindLong(8, entity.getRid());
                statement.mo6928bindLong(9, entity.getRequestedAt());
            }
        };
    }

    @Override // com.example.data.db.PendingRegistrationDao
    public Object insertPendingRegistration(final PendingRegistrationEntity pending, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.PendingRegistrationDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PendingRegistrationDao_Impl.insertPendingRegistration$lambda$0(this.f$0, pending, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertPendingRegistration$lambda$0(PendingRegistrationDao_Impl this$0, PendingRegistrationEntity $pending, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfPendingRegistrationEntity.insert(_connection, $pending);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.PendingRegistrationDao
    public Flow<List<PendingRegistrationEntity>> getPendingRegistrations(final String classroomId) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        final String _sql = "SELECT * FROM pending_registrations WHERE classroomId = ? ORDER BY requestedAt ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"pending_registrations"}, new Function1() { // from class: com.example.data.db.PendingRegistrationDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PendingRegistrationDao_Impl.getPendingRegistrations$lambda$1(_sql, classroomId, (SQLiteConnection) obj);
            }
        });
    }

    static final List getPendingRegistrations$lambda$1(String $_sql, String $classroomId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _argIndex = 1;
        try {
            _stmt.mo6930bindText(1, $classroomId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
            int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
            int _columnIndexOfLrn = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lrn");
            int _columnIndexOfBirth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "birth");
            int _columnIndexOfRid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rid");
            int _columnIndexOfRequestedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requestedAt");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                String _tmpSex = _stmt.getText(_columnIndexOfSex);
                String _tmpLrn = _stmt.getText(_columnIndexOfLrn);
                long _tmpBirth = _stmt.getLong(_columnIndexOfBirth);
                int _argIndex2 = _argIndex;
                int _tmpRid = (int) _stmt.getLong(_columnIndexOfRid);
                long _tmpRequestedAt = _stmt.getLong(_columnIndexOfRequestedAt);
                PendingRegistrationEntity _item = new PendingRegistrationEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpSex, _tmpLrn, _tmpBirth, _tmpRid, _tmpRequestedAt);
                _result.add(_item);
                _argIndex = _argIndex2;
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.PendingRegistrationDao
    public Object getPendingRegistration(final String classroomId, final String studentId, Continuation<? super PendingRegistrationEntity> continuation) {
        final String _sql = "SELECT * FROM pending_registrations WHERE classroomId = ? AND studentId = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.PendingRegistrationDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PendingRegistrationDao_Impl.getPendingRegistration$lambda$2(_sql, classroomId, studentId, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final PendingRegistrationEntity getPendingRegistration$lambda$2(String $_sql, String $classroomId, String $studentId, SQLiteConnection _connection) throws Throwable {
        PendingRegistrationEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $classroomId);
            try {
                _stmt.mo6930bindText(2, $studentId);
                int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
                int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
                int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
                int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
                int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
                int _columnIndexOfLrn = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lrn");
                int _columnIndexOfBirth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "birth");
                int _columnIndexOfRid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rid");
                int _columnIndexOfRequestedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requestedAt");
                if (_stmt.step()) {
                    long _tmpId = _stmt.getLong(_columnIndexOfId);
                    String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                    String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                    String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                    String _tmpSex = _stmt.getText(_columnIndexOfSex);
                    String _tmpLrn = _stmt.getText(_columnIndexOfLrn);
                    long _tmpBirth = _stmt.getLong(_columnIndexOfBirth);
                    int _tmpRid = (int) _stmt.getLong(_columnIndexOfRid);
                    long _tmpRequestedAt = _stmt.getLong(_columnIndexOfRequestedAt);
                    _result = new PendingRegistrationEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpSex, _tmpLrn, _tmpBirth, _tmpRid, _tmpRequestedAt);
                } else {
                    _result = null;
                }
                _stmt.close();
                return _result;
            } catch (Throwable th) {
                th = th;
                _stmt.close();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.example.data.db.PendingRegistrationDao
    public Object deletePendingRegistration(final String classroomId, final String studentId, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM pending_registrations WHERE classroomId = ? AND studentId = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.PendingRegistrationDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PendingRegistrationDao_Impl.deletePendingRegistration$lambda$3(_sql, classroomId, studentId, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deletePendingRegistration$lambda$3(String $_sql, String $classroomId, String $studentId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $classroomId);
            _stmt.mo6930bindText(2, $studentId);
            _stmt.step();
            return Unit.INSTANCE;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.PendingRegistrationDao
    public Object clearPendingForClassroom(final String classroomId, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM pending_registrations WHERE classroomId = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.PendingRegistrationDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PendingRegistrationDao_Impl.clearPendingForClassroom$lambda$4(_sql, classroomId, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit clearPendingForClassroom$lambda$4(String $_sql, String $classroomId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $classroomId);
            _stmt.step();
            return Unit.INSTANCE;
        } finally {
            _stmt.close();
        }
    }

    /* JADX INFO: compiled from: PendingRegistrationDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/db/PendingRegistrationDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }
}
