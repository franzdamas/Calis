package com.example.data.db;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.google.android.gms.common.Scopes;
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

/* JADX INFO: compiled from: StudentProfileDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0011H\u0016J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0096@¢\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/example/data/db/StudentProfileDao_Impl;", "Lcom/example/data/db/StudentProfileDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfStudentProfileEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/db/StudentProfileEntity;", "__updateAdapterOfStudentProfileEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "insertStudentProfile", "", Scopes.PROFILE, "(Lcom/example/data/db/StudentProfileEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStudentProfile", "getStudentProfile", "Lkotlinx/coroutines/flow/Flow;", "getStudentProfileOnce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStudentProfileById", "sid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearStudentProfiles", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StudentProfileDao_Impl implements StudentProfileDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<StudentProfileEntity> __insertAdapterOfStudentProfileEntity;
    private final EntityDeleteOrUpdateAdapter<StudentProfileEntity> __updateAdapterOfStudentProfileEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public StudentProfileDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfStudentProfileEntity = new EntityInsertAdapter<StudentProfileEntity>() { // from class: com.example.data.db.StudentProfileDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `student_profiles` (`sid`,`fullName`,`sex`,`lrn`,`birth`,`createdAt`) VALUES (?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, StudentProfileEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6930bindText(1, entity.getSid());
                statement.mo6930bindText(2, entity.getFullName());
                statement.mo6930bindText(3, entity.getSex());
                statement.mo6930bindText(4, entity.getLrn());
                statement.mo6928bindLong(5, entity.getBirth());
                statement.mo6928bindLong(6, entity.getCreatedAt());
            }
        };
        this.__updateAdapterOfStudentProfileEntity = new EntityDeleteOrUpdateAdapter<StudentProfileEntity>() { // from class: com.example.data.db.StudentProfileDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `student_profiles` SET `sid` = ?,`fullName` = ?,`sex` = ?,`lrn` = ?,`birth` = ?,`createdAt` = ? WHERE `sid` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, StudentProfileEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6930bindText(1, entity.getSid());
                statement.mo6930bindText(2, entity.getFullName());
                statement.mo6930bindText(3, entity.getSex());
                statement.mo6930bindText(4, entity.getLrn());
                statement.mo6928bindLong(5, entity.getBirth());
                statement.mo6928bindLong(6, entity.getCreatedAt());
                statement.mo6930bindText(7, entity.getSid());
            }
        };
    }

    @Override // com.example.data.db.StudentProfileDao
    public Object insertStudentProfile(final StudentProfileEntity profile, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.StudentProfileDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StudentProfileDao_Impl.insertStudentProfile$lambda$0(this.f$0, profile, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertStudentProfile$lambda$0(StudentProfileDao_Impl this$0, StudentProfileEntity $profile, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfStudentProfileEntity.insert(_connection, $profile);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.StudentProfileDao
    public Object updateStudentProfile(final StudentProfileEntity profile, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.StudentProfileDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StudentProfileDao_Impl.updateStudentProfile$lambda$1(this.f$0, profile, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit updateStudentProfile$lambda$1(StudentProfileDao_Impl this$0, StudentProfileEntity $profile, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfStudentProfileEntity.handle(_connection, $profile);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.StudentProfileDao
    public Flow<StudentProfileEntity> getStudentProfile() {
        final String _sql = "SELECT * FROM student_profiles LIMIT 1";
        return FlowUtil.createFlow(this.__db, false, new String[]{"student_profiles"}, new Function1() { // from class: com.example.data.db.StudentProfileDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StudentProfileDao_Impl.getStudentProfile$lambda$2(_sql, (SQLiteConnection) obj);
            }
        });
    }

    static final StudentProfileEntity getStudentProfile$lambda$2(String $_sql, SQLiteConnection _connection) {
        StudentProfileEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfSid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sid");
            int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
            int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
            int _columnIndexOfLrn = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lrn");
            int _columnIndexOfBirth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "birth");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            if (_stmt.step()) {
                String _tmpSid = _stmt.getText(_columnIndexOfSid);
                String _tmpFullName = _stmt.getText(_columnIndexOfFullName);
                String _tmpSex = _stmt.getText(_columnIndexOfSex);
                String _tmpLrn = _stmt.getText(_columnIndexOfLrn);
                long _tmpBirth = _stmt.getLong(_columnIndexOfBirth);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                _result = new StudentProfileEntity(_tmpSid, _tmpFullName, _tmpSex, _tmpLrn, _tmpBirth, _tmpCreatedAt);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.StudentProfileDao
    public Object getStudentProfileOnce(Continuation<? super StudentProfileEntity> continuation) {
        final String _sql = "SELECT * FROM student_profiles LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.StudentProfileDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StudentProfileDao_Impl.getStudentProfileOnce$lambda$3(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final StudentProfileEntity getStudentProfileOnce$lambda$3(String $_sql, SQLiteConnection _connection) {
        StudentProfileEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfSid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sid");
            int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
            int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
            int _columnIndexOfLrn = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lrn");
            int _columnIndexOfBirth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "birth");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            if (_stmt.step()) {
                String _tmpSid = _stmt.getText(_columnIndexOfSid);
                String _tmpFullName = _stmt.getText(_columnIndexOfFullName);
                String _tmpSex = _stmt.getText(_columnIndexOfSex);
                String _tmpLrn = _stmt.getText(_columnIndexOfLrn);
                long _tmpBirth = _stmt.getLong(_columnIndexOfBirth);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                _result = new StudentProfileEntity(_tmpSid, _tmpFullName, _tmpSex, _tmpLrn, _tmpBirth, _tmpCreatedAt);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.StudentProfileDao
    public Object getStudentProfileById(final String sid, Continuation<? super StudentProfileEntity> continuation) {
        final String _sql = "SELECT * FROM student_profiles WHERE sid = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.StudentProfileDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StudentProfileDao_Impl.getStudentProfileById$lambda$4(_sql, sid, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final StudentProfileEntity getStudentProfileById$lambda$4(String $_sql, String $sid, SQLiteConnection _connection) {
        StudentProfileEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $sid);
            int _columnIndexOfSid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sid");
            int _columnIndexOfFullName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "fullName");
            int _columnIndexOfSex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sex");
            int _columnIndexOfLrn = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lrn");
            int _columnIndexOfBirth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "birth");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            if (_stmt.step()) {
                String _tmpSid = _stmt.getText(_columnIndexOfSid);
                String _tmpFullName = _stmt.getText(_columnIndexOfFullName);
                String _tmpSex = _stmt.getText(_columnIndexOfSex);
                String _tmpLrn = _stmt.getText(_columnIndexOfLrn);
                long _tmpBirth = _stmt.getLong(_columnIndexOfBirth);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                _result = new StudentProfileEntity(_tmpSid, _tmpFullName, _tmpSex, _tmpLrn, _tmpBirth, _tmpCreatedAt);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.StudentProfileDao
    public Object clearStudentProfiles(Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM student_profiles";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.StudentProfileDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StudentProfileDao_Impl.clearStudentProfiles$lambda$5(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit clearStudentProfiles$lambda$5(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.step();
            return Unit.INSTANCE;
        } finally {
            _stmt.close();
        }
    }

    /* JADX INFO: compiled from: StudentProfileDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/db/StudentProfileDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
