package com.example.data.db;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
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

/* JADX INFO: compiled from: TeacherDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0011H\u0016J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0096@¢\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/example/data/db/TeacherDao_Impl;", "Lcom/example/data/db/TeacherDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfTeacherEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/db/TeacherEntity;", "__updateAdapterOfTeacherEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "insertTeacher", "", "teacher", "(Lcom/example/data/db/TeacherEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTeacher", "getTeacher", "Lkotlinx/coroutines/flow/Flow;", "getTeacherOnce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTeacherById", "tid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearTeachers", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TeacherDao_Impl implements TeacherDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<TeacherEntity> __insertAdapterOfTeacherEntity;
    private final EntityDeleteOrUpdateAdapter<TeacherEntity> __updateAdapterOfTeacherEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public TeacherDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfTeacherEntity = new EntityInsertAdapter<TeacherEntity>() { // from class: com.example.data.db.TeacherDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `teachers` (`tid`,`fullName`,`sex`,`createdAt`) VALUES (?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, TeacherEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6930bindText(1, entity.getTid());
                statement.mo6930bindText(2, entity.getFullName());
                statement.mo6930bindText(3, entity.getSex());
                statement.mo6928bindLong(4, entity.getCreatedAt());
            }
        };
        this.__updateAdapterOfTeacherEntity = new EntityDeleteOrUpdateAdapter<TeacherEntity>() { // from class: com.example.data.db.TeacherDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `teachers` SET `tid` = ?,`fullName` = ?,`sex` = ?,`createdAt` = ? WHERE `tid` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, TeacherEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6930bindText(1, entity.getTid());
                statement.mo6930bindText(2, entity.getFullName());
                statement.mo6930bindText(3, entity.getSex());
                statement.mo6928bindLong(4, entity.getCreatedAt());
                statement.mo6930bindText(5, entity.getTid());
            }
        };
    }

    @Override // com.example.data.db.TeacherDao
    public Object insertTeacher(final TeacherEntity teacher, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.TeacherDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TeacherDao_Impl.insertTeacher$lambda$0(this.f$0, teacher, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertTeacher$lambda$0(TeacherDao_Impl this$0, TeacherEntity $teacher, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfTeacherEntity.insert(_connection, $teacher);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.TeacherDao
    public Object updateTeacher(final TeacherEntity teacher, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.TeacherDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TeacherDao_Impl.updateTeacher$lambda$1(this.f$0, teacher, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit updateTeacher$lambda$1(TeacherDao_Impl this$0, TeacherEntity $teacher, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfTeacherEntity.handle(_connection, $teacher);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.TeacherDao
    public Flow<TeacherEntity> getTeacher() {
        final String _sql = "SELECT * FROM teachers WHERE tid NOT LIKE 'TEACHER_%' LIMIT 1";
        return FlowUtil.createFlow(this.__db, false, new String[]{"teachers"}, new Function1() { // from class: com.example.data.db.TeacherDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TeacherDao_Impl.getTeacher$lambda$2(_sql, (SQLiteConnection) obj);
            }
        });
    }

    static final TeacherEntity getTeacher$lambda$2(String $_sql, SQLiteConnection _connection) {
        TeacherEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfTid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tid");
            int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
            int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            if (_stmt.step()) {
                String _tmpTid = _stmt.getText(_columnIndexOfTid);
                String _tmpFullName = _stmt.getText(_columnIndexOfFullName);
                String _tmpSex = _stmt.getText(_columnIndexOfSex);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                _result = new TeacherEntity(_tmpTid, _tmpFullName, _tmpSex, _tmpCreatedAt);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.TeacherDao
    public Object getTeacherOnce(Continuation<? super TeacherEntity> continuation) {
        final String _sql = "SELECT * FROM teachers WHERE tid NOT LIKE 'TEACHER_%' LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.TeacherDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TeacherDao_Impl.getTeacherOnce$lambda$3(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final TeacherEntity getTeacherOnce$lambda$3(String $_sql, SQLiteConnection _connection) {
        TeacherEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfTid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tid");
            int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
            int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            if (_stmt.step()) {
                String _tmpTid = _stmt.getText(_columnIndexOfTid);
                String _tmpFullName = _stmt.getText(_columnIndexOfFullName);
                String _tmpSex = _stmt.getText(_columnIndexOfSex);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                _result = new TeacherEntity(_tmpTid, _tmpFullName, _tmpSex, _tmpCreatedAt);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.TeacherDao
    public Object getTeacherById(final String tid, Continuation<? super TeacherEntity> continuation) {
        final String _sql = "SELECT * FROM teachers WHERE tid = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.TeacherDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TeacherDao_Impl.getTeacherById$lambda$4(_sql, tid, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final TeacherEntity getTeacherById$lambda$4(String $_sql, String $tid, SQLiteConnection _connection) {
        TeacherEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $tid);
            int _columnIndexOfTid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tid");
            int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
            int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            if (_stmt.step()) {
                String _tmpTid = _stmt.getText(_columnIndexOfTid);
                String _tmpFullName = _stmt.getText(_columnIndexOfFullName);
                String _tmpSex = _stmt.getText(_columnIndexOfSex);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                _result = new TeacherEntity(_tmpTid, _tmpFullName, _tmpSex, _tmpCreatedAt);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.TeacherDao
    public Object clearTeachers(Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM teachers";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.TeacherDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TeacherDao_Impl.clearTeachers$lambda$5(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit clearTeachers$lambda$5(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.step();
            return Unit.INSTANCE;
        } finally {
            _stmt.close();
        }
    }

    /* JADX INFO: compiled from: TeacherDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/db/TeacherDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
