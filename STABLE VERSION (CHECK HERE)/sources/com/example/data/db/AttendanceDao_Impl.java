package com.example.data.db;

import androidx.core.app.NotificationCompat;
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

/* JADX INFO: compiled from: AttendanceDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0013J$\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000e2\u0006\u0010\u0019\u001a\u00020\u0011H\u0016J(\u0010\u001a\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001eH\u0096@¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/example/data/db/AttendanceDao_Impl;", "Lcom/example/data/db/AttendanceDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfAttendanceEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/db/AttendanceEntity;", "insertOrUpdateAttendance", "", "attendance", "(Lcom/example/data/db/AttendanceEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAttendanceForClassroom", "Lkotlinx/coroutines/flow/Flow;", "", "classroomId", "", "getAttendanceForClassroomSync", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAttendanceForDate", "date", "getAttendanceForDateSync", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAttendanceForStudent", "studentId", "getAttendanceRecord", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAttendanceById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AttendanceDao_Impl implements AttendanceDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<AttendanceEntity> __insertAdapterOfAttendanceEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public AttendanceDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfAttendanceEntity = new EntityInsertAdapter<AttendanceEntity>() { // from class: com.example.data.db.AttendanceDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `attendance` (`id`,`classroomId`,`studentId`,`studentName`,`date`,`status`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, AttendanceEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6928bindLong(1, entity.getId());
                statement.mo6930bindText(2, entity.getClassroomId());
                statement.mo6930bindText(3, entity.getStudentId());
                statement.mo6930bindText(4, entity.getStudentName());
                statement.mo6930bindText(5, entity.getDate());
                statement.mo6930bindText(6, entity.getStatus());
                statement.mo6928bindLong(7, entity.getTimestamp());
            }
        };
    }

    @Override // com.example.data.db.AttendanceDao
    public Object insertOrUpdateAttendance(final AttendanceEntity attendance, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.AttendanceDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AttendanceDao_Impl.insertOrUpdateAttendance$lambda$0(this.f$0, attendance, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertOrUpdateAttendance$lambda$0(AttendanceDao_Impl this$0, AttendanceEntity $attendance, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfAttendanceEntity.insert(_connection, $attendance);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.AttendanceDao
    public Flow<List<AttendanceEntity>> getAttendanceForClassroom(final String classroomId) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        final String _sql = "SELECT * FROM attendance WHERE classroomId = ? ORDER BY timestamp DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"attendance"}, new Function1() { // from class: com.example.data.db.AttendanceDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AttendanceDao_Impl.getAttendanceForClassroom$lambda$1(_sql, classroomId, (SQLiteConnection) obj);
            }
        });
    }

    static final List getAttendanceForClassroom$lambda$1(String $_sql, String $classroomId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $classroomId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
            int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, NotificationCompat.CATEGORY_STATUS);
            int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                String _tmpDate = _stmt.getText(_columnIndexOfDate);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                AttendanceEntity _item = new AttendanceEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpDate, _tmpStatus, _tmpTimestamp);
                _result.add(_item);
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.AttendanceDao
    public Object getAttendanceForClassroomSync(final String classroomId, Continuation<? super List<AttendanceEntity>> continuation) {
        final String _sql = "SELECT * FROM attendance WHERE classroomId = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.AttendanceDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AttendanceDao_Impl.getAttendanceForClassroomSync$lambda$2(_sql, classroomId, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final List getAttendanceForClassroomSync$lambda$2(String $_sql, String $classroomId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $classroomId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
            int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, NotificationCompat.CATEGORY_STATUS);
            int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                String _tmpDate = _stmt.getText(_columnIndexOfDate);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                AttendanceEntity _item = new AttendanceEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpDate, _tmpStatus, _tmpTimestamp);
                _result.add(_item);
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.AttendanceDao
    public Flow<List<AttendanceEntity>> getAttendanceForDate(final String classroomId, final String date) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        Intrinsics.checkNotNullParameter(date, "date");
        final String _sql = "SELECT * FROM attendance WHERE classroomId = ? AND date = ? ORDER BY studentName ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"attendance"}, new Function1() { // from class: com.example.data.db.AttendanceDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AttendanceDao_Impl.getAttendanceForDate$lambda$3(_sql, classroomId, date, (SQLiteConnection) obj);
            }
        });
    }

    static final List getAttendanceForDate$lambda$3(String $_sql, String $classroomId, String $date, SQLiteConnection _connection) throws Throwable {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $classroomId);
            try {
                _stmt.mo6930bindText(2, $date);
                int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
                int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
                int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
                int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
                int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
                int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, NotificationCompat.CATEGORY_STATUS);
                int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
                List _result = new ArrayList();
                while (_stmt.step()) {
                    long _tmpId = _stmt.getLong(_columnIndexOfId);
                    String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                    String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                    String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                    String _tmpDate = _stmt.getText(_columnIndexOfDate);
                    String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                    long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                    AttendanceEntity _item = new AttendanceEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpDate, _tmpStatus, _tmpTimestamp);
                    _result.add(_item);
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

    @Override // com.example.data.db.AttendanceDao
    public Object getAttendanceForDateSync(final String classroomId, final String date, Continuation<? super List<AttendanceEntity>> continuation) {
        final String _sql = "SELECT * FROM attendance WHERE classroomId = ? AND date = ? ORDER BY studentName ASC";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.AttendanceDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AttendanceDao_Impl.getAttendanceForDateSync$lambda$4(_sql, classroomId, date, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final List getAttendanceForDateSync$lambda$4(String $_sql, String $classroomId, String $date, SQLiteConnection _connection) throws Throwable {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $classroomId);
            try {
                _stmt.mo6930bindText(2, $date);
                int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
                int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
                int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
                int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
                int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
                int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, NotificationCompat.CATEGORY_STATUS);
                int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
                List _result = new ArrayList();
                while (_stmt.step()) {
                    long _tmpId = _stmt.getLong(_columnIndexOfId);
                    String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                    String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                    String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                    String _tmpDate = _stmt.getText(_columnIndexOfDate);
                    String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                    long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                    AttendanceEntity _item = new AttendanceEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpDate, _tmpStatus, _tmpTimestamp);
                    _result.add(_item);
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

    @Override // com.example.data.db.AttendanceDao
    public Flow<List<AttendanceEntity>> getAttendanceForStudent(final String studentId) {
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        final String _sql = "SELECT * FROM attendance WHERE studentId = ? ORDER BY timestamp DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"attendance"}, new Function1() { // from class: com.example.data.db.AttendanceDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AttendanceDao_Impl.getAttendanceForStudent$lambda$5(_sql, studentId, (SQLiteConnection) obj);
            }
        });
    }

    static final List getAttendanceForStudent$lambda$5(String $_sql, String $studentId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $studentId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
            int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, NotificationCompat.CATEGORY_STATUS);
            int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                String _tmpDate = _stmt.getText(_columnIndexOfDate);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                AttendanceEntity _item = new AttendanceEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpDate, _tmpStatus, _tmpTimestamp);
                _result.add(_item);
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.AttendanceDao
    public Object getAttendanceRecord(final String classroomId, final String studentId, final String date, Continuation<? super AttendanceEntity> continuation) {
        final String _sql = "SELECT * FROM attendance WHERE classroomId = ? AND studentId = ? AND date = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.AttendanceDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AttendanceDao_Impl.getAttendanceRecord$lambda$6(_sql, classroomId, studentId, date, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final AttendanceEntity getAttendanceRecord$lambda$6(String $_sql, String $classroomId, String $studentId, String $date, SQLiteConnection _connection) throws Throwable {
        AttendanceEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $classroomId);
            try {
                _stmt.mo6930bindText(2, $studentId);
                try {
                    _stmt.mo6930bindText(3, $date);
                    int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
                    int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
                    int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
                    int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
                    int _columnIndexOfDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "date");
                    int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, NotificationCompat.CATEGORY_STATUS);
                    int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
                    if (_stmt.step()) {
                        long _tmpId = _stmt.getLong(_columnIndexOfId);
                        String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                        String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                        String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                        String _tmpDate = _stmt.getText(_columnIndexOfDate);
                        String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                        long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                        _result = new AttendanceEntity(_tmpId, _tmpClassroomId, _tmpStudentId, _tmpStudentName, _tmpDate, _tmpStatus, _tmpTimestamp);
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
                _stmt.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // com.example.data.db.AttendanceDao
    public Object deleteAttendanceById(final long id, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM attendance WHERE id = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.AttendanceDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AttendanceDao_Impl.deleteAttendanceById$lambda$7(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteAttendanceById$lambda$7(String $_sql, long $id, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6928bindLong(1, $id);
            _stmt.step();
            return Unit.INSTANCE;
        } finally {
            _stmt.close();
        }
    }

    /* JADX INFO: compiled from: AttendanceDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/db/AttendanceDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
