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

/* JADX INFO: compiled from: ApprovedStudentDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0013J \u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0016J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0013J\u001e\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/example/data/db/ApprovedStudentDao_Impl;", "Lcom/example/data/db/ApprovedStudentDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfApprovedStudentEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/db/ApprovedStudentEntity;", "insertApprovedStudent", "", "approvedStudent", "(Lcom/example/data/db/ApprovedStudentEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApprovedStudents", "Lkotlinx/coroutines/flow/Flow;", "", "classroomId", "", "getApprovedStudentsSync", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApprovedStudent", "studentId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApprovedStudentGlobally", "deleteApprovedStudent", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApprovedStudentDao_Impl implements ApprovedStudentDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<ApprovedStudentEntity> __insertAdapterOfApprovedStudentEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public ApprovedStudentDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfApprovedStudentEntity = new EntityInsertAdapter<ApprovedStudentEntity>() { // from class: com.example.data.db.ApprovedStudentDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `approved_students` (`id`,`classroomId`,`studentId`,`studentName`,`sex`,`lrn`,`birth`,`approvedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, ApprovedStudentEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6928bindLong(1, entity.getId());
                statement.mo6930bindText(2, entity.getClassroomId());
                statement.mo6930bindText(3, entity.getStudentId());
                statement.mo6930bindText(4, entity.getStudentName());
                statement.mo6930bindText(5, entity.getSex());
                statement.mo6930bindText(6, entity.getLrn());
                statement.mo6928bindLong(7, entity.getBirth());
                statement.mo6928bindLong(8, entity.getApprovedAt());
            }
        };
    }

    @Override // com.example.data.db.ApprovedStudentDao
    public Object insertApprovedStudent(final ApprovedStudentEntity approvedStudent, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.ApprovedStudentDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ApprovedStudentDao_Impl.insertApprovedStudent$lambda$0(this.f$0, approvedStudent, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertApprovedStudent$lambda$0(ApprovedStudentDao_Impl this$0, ApprovedStudentEntity $approvedStudent, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfApprovedStudentEntity.insert(_connection, $approvedStudent);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.ApprovedStudentDao
    public Flow<List<ApprovedStudentEntity>> getApprovedStudents(final String classroomId) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        final String _sql = "SELECT * FROM approved_students WHERE classroomId = ? ORDER BY studentName ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"approved_students"}, new Function1() { // from class: com.example.data.db.ApprovedStudentDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ApprovedStudentDao_Impl.getApprovedStudents$lambda$1(_sql, classroomId, (SQLiteConnection) obj);
            }
        });
    }

    static final List getApprovedStudents$lambda$1(String $_sql, String $classroomId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $classroomId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
            int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
            int _columnIndexOfLrn = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lrn");
            int _columnIndexOfBirth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "birth");
            int _columnIndexOfApprovedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "approvedAt");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                String _tmpSex = _stmt.getText(_columnIndexOfSex);
                String _tmpLrn = _stmt.getText(_columnIndexOfLrn);
                long _tmpBirth = _stmt.getLong(_columnIndexOfBirth);
                long _tmpApprovedAt = _stmt.getLong(_columnIndexOfApprovedAt);
                ApprovedStudentEntity _item = new ApprovedStudentEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpSex, _tmpLrn, _tmpBirth, _tmpApprovedAt);
                _result.add(_item);
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.ApprovedStudentDao
    public Object getApprovedStudentsSync(final String classroomId, Continuation<? super List<ApprovedStudentEntity>> continuation) {
        final String _sql = "SELECT * FROM approved_students WHERE classroomId = ? ORDER BY studentName ASC";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.ApprovedStudentDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ApprovedStudentDao_Impl.getApprovedStudentsSync$lambda$2(_sql, classroomId, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final List getApprovedStudentsSync$lambda$2(String $_sql, String $classroomId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $classroomId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
            int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
            int _columnIndexOfLrn = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lrn");
            int _columnIndexOfBirth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "birth");
            int _columnIndexOfApprovedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "approvedAt");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                String _tmpSex = _stmt.getText(_columnIndexOfSex);
                String _tmpLrn = _stmt.getText(_columnIndexOfLrn);
                long _tmpBirth = _stmt.getLong(_columnIndexOfBirth);
                long _tmpApprovedAt = _stmt.getLong(_columnIndexOfApprovedAt);
                ApprovedStudentEntity _item = new ApprovedStudentEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpSex, _tmpLrn, _tmpBirth, _tmpApprovedAt);
                _result.add(_item);
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.ApprovedStudentDao
    public Object getApprovedStudent(final String classroomId, final String studentId, Continuation<? super ApprovedStudentEntity> continuation) {
        final String _sql = "SELECT * FROM approved_students WHERE classroomId = ? AND studentId = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.ApprovedStudentDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ApprovedStudentDao_Impl.getApprovedStudent$lambda$3(_sql, classroomId, studentId, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final ApprovedStudentEntity getApprovedStudent$lambda$3(String $_sql, String $classroomId, String $studentId, SQLiteConnection _connection) throws Throwable {
        ApprovedStudentEntity _result;
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
                int _columnIndexOfApprovedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "approvedAt");
                if (_stmt.step()) {
                    long _tmpId = _stmt.getLong(_columnIndexOfId);
                    String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                    String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                    String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                    String _tmpSex = _stmt.getText(_columnIndexOfSex);
                    String _tmpLrn = _stmt.getText(_columnIndexOfLrn);
                    long _tmpBirth = _stmt.getLong(_columnIndexOfBirth);
                    long _tmpApprovedAt = _stmt.getLong(_columnIndexOfApprovedAt);
                    _result = new ApprovedStudentEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpSex, _tmpLrn, _tmpBirth, _tmpApprovedAt);
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

    @Override // com.example.data.db.ApprovedStudentDao
    public Object getApprovedStudentGlobally(final String studentId, Continuation<? super ApprovedStudentEntity> continuation) {
        final String _sql = "SELECT * FROM approved_students WHERE studentId = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.ApprovedStudentDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ApprovedStudentDao_Impl.getApprovedStudentGlobally$lambda$4(_sql, studentId, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final ApprovedStudentEntity getApprovedStudentGlobally$lambda$4(String $_sql, String $studentId, SQLiteConnection _connection) {
        ApprovedStudentEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $studentId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
            int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
            int _columnIndexOfLrn = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lrn");
            int _columnIndexOfBirth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "birth");
            int _columnIndexOfApprovedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "approvedAt");
            if (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                String _tmpSex = _stmt.getText(_columnIndexOfSex);
                String _tmpLrn = _stmt.getText(_columnIndexOfLrn);
                long _tmpBirth = _stmt.getLong(_columnIndexOfBirth);
                long _tmpApprovedAt = _stmt.getLong(_columnIndexOfApprovedAt);
                _result = new ApprovedStudentEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpSex, _tmpLrn, _tmpBirth, _tmpApprovedAt);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.ApprovedStudentDao
    public Object deleteApprovedStudent(final String classroomId, final String studentId, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM approved_students WHERE classroomId = ? AND studentId = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.ApprovedStudentDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ApprovedStudentDao_Impl.deleteApprovedStudent$lambda$5(_sql, classroomId, studentId, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteApprovedStudent$lambda$5(String $_sql, String $classroomId, String $studentId, SQLiteConnection _connection) {
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

    /* JADX INFO: compiled from: ApprovedStudentDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/db/ApprovedStudentDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
