package com.example.data.db;

import androidx.room.EntityDeleteOrUpdateAdapter;
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

/* JADX INFO: compiled from: ClassroomDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u0013H\u0096@¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0011H\u0016J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\bH\u0096@¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0011H\u0016J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\bH\u0096@¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u001dH\u0096@¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0013H\u0096@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/example/data/db/ClassroomDao_Impl;", "Lcom/example/data/db/ClassroomDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfClassroomEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/db/ClassroomEntity;", "__updateAdapterOfClassroomEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "insertClassroom", "", "classroom", "(Lcom/example/data/db/ClassroomEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateClassroom", "getClassroomForTeacher", "Lkotlinx/coroutines/flow/Flow;", "teacherId", "", "getClassroomById", "cid", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveBoundClassroom", "getActiveBoundClassroomSync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAnyClassroom", "getAnyClassroomSync", "getAllClassroomsSync", "", "deleteClassroomById", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ClassroomDao_Impl implements ClassroomDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<ClassroomEntity> __insertAdapterOfClassroomEntity;
    private final EntityDeleteOrUpdateAdapter<ClassroomEntity> __updateAdapterOfClassroomEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public ClassroomDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfClassroomEntity = new EntityInsertAdapter<ClassroomEntity>() { // from class: com.example.data.db.ClassroomDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `classrooms` (`cid`,`teacherId`,`classroomName`,`teacherName`,`days`,`startTime`,`graceTime`,`endTime`,`isActiveSession`,`isBound`,`deviceAddress`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, ClassroomEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6930bindText(1, entity.getCid());
                statement.mo6930bindText(2, entity.getTeacherId());
                statement.mo6930bindText(3, entity.getClassroomName());
                statement.mo6930bindText(4, entity.getTeacherName());
                statement.mo6930bindText(5, entity.getDays());
                statement.mo6930bindText(6, entity.getStartTime());
                statement.mo6930bindText(7, entity.getGraceTime());
                statement.mo6930bindText(8, entity.getEndTime());
                statement.mo6928bindLong(9, entity.isActiveSession() ? 1L : 0L);
                statement.mo6928bindLong(10, entity.isBound() ? 1L : 0L);
                String deviceAddress = entity.getDeviceAddress();
                if (deviceAddress == null) {
                    statement.mo6929bindNull(11);
                } else {
                    statement.mo6930bindText(11, deviceAddress);
                }
            }
        };
        this.__updateAdapterOfClassroomEntity = new EntityDeleteOrUpdateAdapter<ClassroomEntity>() { // from class: com.example.data.db.ClassroomDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `classrooms` SET `cid` = ?,`teacherId` = ?,`classroomName` = ?,`teacherName` = ?,`days` = ?,`startTime` = ?,`graceTime` = ?,`endTime` = ?,`isActiveSession` = ?,`isBound` = ?,`deviceAddress` = ? WHERE `cid` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, ClassroomEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6930bindText(1, entity.getCid());
                statement.mo6930bindText(2, entity.getTeacherId());
                statement.mo6930bindText(3, entity.getClassroomName());
                statement.mo6930bindText(4, entity.getTeacherName());
                statement.mo6930bindText(5, entity.getDays());
                statement.mo6930bindText(6, entity.getStartTime());
                statement.mo6930bindText(7, entity.getGraceTime());
                statement.mo6930bindText(8, entity.getEndTime());
                statement.mo6928bindLong(9, entity.isActiveSession() ? 1L : 0L);
                statement.mo6928bindLong(10, entity.isBound() ? 1L : 0L);
                String deviceAddress = entity.getDeviceAddress();
                if (deviceAddress == null) {
                    statement.mo6929bindNull(11);
                } else {
                    statement.mo6930bindText(11, deviceAddress);
                }
                statement.mo6930bindText(12, entity.getCid());
            }
        };
    }

    @Override // com.example.data.db.ClassroomDao
    public Object insertClassroom(final ClassroomEntity classroom, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.ClassroomDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClassroomDao_Impl.insertClassroom$lambda$0(this.f$0, classroom, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertClassroom$lambda$0(ClassroomDao_Impl this$0, ClassroomEntity $classroom, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfClassroomEntity.insert(_connection, $classroom);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.ClassroomDao
    public Object updateClassroom(final ClassroomEntity classroom, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.ClassroomDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClassroomDao_Impl.updateClassroom$lambda$1(this.f$0, classroom, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit updateClassroom$lambda$1(ClassroomDao_Impl this$0, ClassroomEntity $classroom, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfClassroomEntity.handle(_connection, $classroom);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.ClassroomDao
    public Flow<ClassroomEntity> getClassroomForTeacher(final String teacherId) {
        Intrinsics.checkNotNullParameter(teacherId, "teacherId");
        final String _sql = "SELECT * FROM classrooms WHERE teacherId = ? LIMIT 1";
        return FlowUtil.createFlow(this.__db, false, new String[]{"classrooms"}, new Function1() { // from class: com.example.data.db.ClassroomDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClassroomDao_Impl.getClassroomForTeacher$lambda$2(_sql, teacherId, (SQLiteConnection) obj);
            }
        });
    }

    static final ClassroomEntity getClassroomForTeacher$lambda$2(String $_sql, String $teacherId, SQLiteConnection _connection) {
        ClassroomEntity _result;
        String _tmpDeviceAddress;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $teacherId);
            int _columnIndexOfCid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cid");
            int _columnIndexOfTeacherId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherId");
            int _columnIndexOfClassroomName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomName");
            int _columnIndexOfTeacherName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherName");
            int _columnIndexOfDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "days");
            int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
            int _columnIndexOfGraceTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "graceTime");
            int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
            int _columnIndexOfIsActiveSession = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isActiveSession");
            int _columnIndexOfIsBound = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isBound");
            int _columnIndexOfDeviceAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceAddress");
            if (_stmt.step()) {
                String _tmpCid = _stmt.getText(_columnIndexOfCid);
                String _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId);
                String _tmpClassroomName = _stmt.getText(_columnIndexOfClassroomName);
                String _tmpTeacherName = _stmt.getText(_columnIndexOfTeacherName);
                String _tmpDays = _stmt.getText(_columnIndexOfDays);
                String _tmpStartTime = _stmt.getText(_columnIndexOfStartTime);
                String _tmpGraceTime = _stmt.getText(_columnIndexOfGraceTime);
                String _tmpEndTime = _stmt.getText(_columnIndexOfEndTime);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsActiveSession);
                boolean _tmpIsActiveSession = _tmp != 0;
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsBound);
                boolean _tmpIsBound = _tmp_1 != 0;
                if (_stmt.isNull(_columnIndexOfDeviceAddress)) {
                    _tmpDeviceAddress = null;
                } else {
                    String _tmpDeviceAddress2 = _stmt.getText(_columnIndexOfDeviceAddress);
                    _tmpDeviceAddress = _tmpDeviceAddress2;
                }
                _result = new ClassroomEntity(_tmpCid, _tmpTeacherId, _tmpClassroomName, _tmpTeacherName, _tmpDays, _tmpStartTime, _tmpGraceTime, _tmpEndTime, _tmpIsActiveSession, _tmpIsBound, _tmpDeviceAddress);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.ClassroomDao
    public Object getClassroomById(final String cid, Continuation<? super ClassroomEntity> continuation) {
        final String _sql = "SELECT * FROM classrooms WHERE cid = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.ClassroomDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClassroomDao_Impl.getClassroomById$lambda$3(_sql, cid, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final ClassroomEntity getClassroomById$lambda$3(String $_sql, String $cid, SQLiteConnection _connection) {
        ClassroomEntity _result;
        String _tmpDeviceAddress;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $cid);
            int _columnIndexOfCid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cid");
            int _columnIndexOfTeacherId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherId");
            int _columnIndexOfClassroomName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomName");
            int _columnIndexOfTeacherName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherName");
            int _columnIndexOfDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "days");
            int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
            int _columnIndexOfGraceTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "graceTime");
            int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
            int _columnIndexOfIsActiveSession = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isActiveSession");
            int _columnIndexOfIsBound = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isBound");
            int _columnIndexOfDeviceAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceAddress");
            if (_stmt.step()) {
                String _tmpCid = _stmt.getText(_columnIndexOfCid);
                String _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId);
                String _tmpClassroomName = _stmt.getText(_columnIndexOfClassroomName);
                String _tmpTeacherName = _stmt.getText(_columnIndexOfTeacherName);
                String _tmpDays = _stmt.getText(_columnIndexOfDays);
                String _tmpStartTime = _stmt.getText(_columnIndexOfStartTime);
                String _tmpGraceTime = _stmt.getText(_columnIndexOfGraceTime);
                String _tmpEndTime = _stmt.getText(_columnIndexOfEndTime);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsActiveSession);
                boolean _tmpIsActiveSession = _tmp != 0;
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsBound);
                boolean _tmpIsBound = _tmp_1 != 0;
                if (_stmt.isNull(_columnIndexOfDeviceAddress)) {
                    _tmpDeviceAddress = null;
                } else {
                    String _tmpDeviceAddress2 = _stmt.getText(_columnIndexOfDeviceAddress);
                    _tmpDeviceAddress = _tmpDeviceAddress2;
                }
                _result = new ClassroomEntity(_tmpCid, _tmpTeacherId, _tmpClassroomName, _tmpTeacherName, _tmpDays, _tmpStartTime, _tmpGraceTime, _tmpEndTime, _tmpIsActiveSession, _tmpIsBound, _tmpDeviceAddress);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.ClassroomDao
    public Flow<ClassroomEntity> getActiveBoundClassroom() {
        final String _sql = "SELECT * FROM classrooms WHERE isBound = 1 LIMIT 1";
        return FlowUtil.createFlow(this.__db, false, new String[]{"classrooms"}, new Function1() { // from class: com.example.data.db.ClassroomDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClassroomDao_Impl.getActiveBoundClassroom$lambda$4(_sql, (SQLiteConnection) obj);
            }
        });
    }

    static final ClassroomEntity getActiveBoundClassroom$lambda$4(String $_sql, SQLiteConnection _connection) {
        ClassroomEntity _result;
        String _tmpDeviceAddress;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfCid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cid");
            int _columnIndexOfTeacherId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherId");
            int _columnIndexOfClassroomName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomName");
            int _columnIndexOfTeacherName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherName");
            int _columnIndexOfDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "days");
            int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
            int _columnIndexOfGraceTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "graceTime");
            int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
            int _columnIndexOfIsActiveSession = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isActiveSession");
            int _columnIndexOfIsBound = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isBound");
            int _columnIndexOfDeviceAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceAddress");
            if (_stmt.step()) {
                String _tmpCid = _stmt.getText(_columnIndexOfCid);
                String _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId);
                String _tmpClassroomName = _stmt.getText(_columnIndexOfClassroomName);
                String _tmpTeacherName = _stmt.getText(_columnIndexOfTeacherName);
                String _tmpDays = _stmt.getText(_columnIndexOfDays);
                String _tmpStartTime = _stmt.getText(_columnIndexOfStartTime);
                String _tmpGraceTime = _stmt.getText(_columnIndexOfGraceTime);
                String _tmpEndTime = _stmt.getText(_columnIndexOfEndTime);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsActiveSession);
                boolean _tmpIsActiveSession = _tmp != 0;
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsBound);
                boolean _tmpIsBound = _tmp_1 != 0;
                if (_stmt.isNull(_columnIndexOfDeviceAddress)) {
                    _tmpDeviceAddress = null;
                } else {
                    String _tmpDeviceAddress2 = _stmt.getText(_columnIndexOfDeviceAddress);
                    _tmpDeviceAddress = _tmpDeviceAddress2;
                }
                _result = new ClassroomEntity(_tmpCid, _tmpTeacherId, _tmpClassroomName, _tmpTeacherName, _tmpDays, _tmpStartTime, _tmpGraceTime, _tmpEndTime, _tmpIsActiveSession, _tmpIsBound, _tmpDeviceAddress);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.ClassroomDao
    public Object getActiveBoundClassroomSync(Continuation<? super ClassroomEntity> continuation) {
        final String _sql = "SELECT * FROM classrooms WHERE isBound = 1 LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.ClassroomDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClassroomDao_Impl.getActiveBoundClassroomSync$lambda$5(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final ClassroomEntity getActiveBoundClassroomSync$lambda$5(String $_sql, SQLiteConnection _connection) {
        ClassroomEntity _result;
        String _tmpDeviceAddress;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfCid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cid");
            int _columnIndexOfTeacherId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherId");
            int _columnIndexOfClassroomName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomName");
            int _columnIndexOfTeacherName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherName");
            int _columnIndexOfDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "days");
            int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
            int _columnIndexOfGraceTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "graceTime");
            int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
            int _columnIndexOfIsActiveSession = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isActiveSession");
            int _columnIndexOfIsBound = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isBound");
            int _columnIndexOfDeviceAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceAddress");
            if (_stmt.step()) {
                String _tmpCid = _stmt.getText(_columnIndexOfCid);
                String _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId);
                String _tmpClassroomName = _stmt.getText(_columnIndexOfClassroomName);
                String _tmpTeacherName = _stmt.getText(_columnIndexOfTeacherName);
                String _tmpDays = _stmt.getText(_columnIndexOfDays);
                String _tmpStartTime = _stmt.getText(_columnIndexOfStartTime);
                String _tmpGraceTime = _stmt.getText(_columnIndexOfGraceTime);
                String _tmpEndTime = _stmt.getText(_columnIndexOfEndTime);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsActiveSession);
                boolean _tmpIsActiveSession = _tmp != 0;
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsBound);
                boolean _tmpIsBound = _tmp_1 != 0;
                if (_stmt.isNull(_columnIndexOfDeviceAddress)) {
                    _tmpDeviceAddress = null;
                } else {
                    String _tmpDeviceAddress2 = _stmt.getText(_columnIndexOfDeviceAddress);
                    _tmpDeviceAddress = _tmpDeviceAddress2;
                }
                _result = new ClassroomEntity(_tmpCid, _tmpTeacherId, _tmpClassroomName, _tmpTeacherName, _tmpDays, _tmpStartTime, _tmpGraceTime, _tmpEndTime, _tmpIsActiveSession, _tmpIsBound, _tmpDeviceAddress);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.ClassroomDao
    public Flow<ClassroomEntity> getAnyClassroom() {
        final String _sql = "SELECT * FROM classrooms LIMIT 1";
        return FlowUtil.createFlow(this.__db, false, new String[]{"classrooms"}, new Function1() { // from class: com.example.data.db.ClassroomDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClassroomDao_Impl.getAnyClassroom$lambda$6(_sql, (SQLiteConnection) obj);
            }
        });
    }

    static final ClassroomEntity getAnyClassroom$lambda$6(String $_sql, SQLiteConnection _connection) {
        ClassroomEntity _result;
        String _tmpDeviceAddress;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfCid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cid");
            int _columnIndexOfTeacherId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherId");
            int _columnIndexOfClassroomName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomName");
            int _columnIndexOfTeacherName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherName");
            int _columnIndexOfDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "days");
            int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
            int _columnIndexOfGraceTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "graceTime");
            int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
            int _columnIndexOfIsActiveSession = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isActiveSession");
            int _columnIndexOfIsBound = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isBound");
            int _columnIndexOfDeviceAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceAddress");
            if (_stmt.step()) {
                String _tmpCid = _stmt.getText(_columnIndexOfCid);
                String _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId);
                String _tmpClassroomName = _stmt.getText(_columnIndexOfClassroomName);
                String _tmpTeacherName = _stmt.getText(_columnIndexOfTeacherName);
                String _tmpDays = _stmt.getText(_columnIndexOfDays);
                String _tmpStartTime = _stmt.getText(_columnIndexOfStartTime);
                String _tmpGraceTime = _stmt.getText(_columnIndexOfGraceTime);
                String _tmpEndTime = _stmt.getText(_columnIndexOfEndTime);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsActiveSession);
                boolean _tmpIsActiveSession = _tmp != 0;
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsBound);
                boolean _tmpIsBound = _tmp_1 != 0;
                if (_stmt.isNull(_columnIndexOfDeviceAddress)) {
                    _tmpDeviceAddress = null;
                } else {
                    String _tmpDeviceAddress2 = _stmt.getText(_columnIndexOfDeviceAddress);
                    _tmpDeviceAddress = _tmpDeviceAddress2;
                }
                _result = new ClassroomEntity(_tmpCid, _tmpTeacherId, _tmpClassroomName, _tmpTeacherName, _tmpDays, _tmpStartTime, _tmpGraceTime, _tmpEndTime, _tmpIsActiveSession, _tmpIsBound, _tmpDeviceAddress);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.ClassroomDao
    public Object getAnyClassroomSync(Continuation<? super ClassroomEntity> continuation) {
        final String _sql = "SELECT * FROM classrooms LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.ClassroomDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClassroomDao_Impl.getAnyClassroomSync$lambda$7(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final ClassroomEntity getAnyClassroomSync$lambda$7(String $_sql, SQLiteConnection _connection) {
        ClassroomEntity _result;
        String _tmpDeviceAddress;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfCid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cid");
            int _columnIndexOfTeacherId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherId");
            int _columnIndexOfClassroomName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomName");
            int _columnIndexOfTeacherName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherName");
            int _columnIndexOfDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "days");
            int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
            int _columnIndexOfGraceTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "graceTime");
            int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
            int _columnIndexOfIsActiveSession = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isActiveSession");
            int _columnIndexOfIsBound = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isBound");
            int _columnIndexOfDeviceAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceAddress");
            if (_stmt.step()) {
                String _tmpCid = _stmt.getText(_columnIndexOfCid);
                String _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId);
                String _tmpClassroomName = _stmt.getText(_columnIndexOfClassroomName);
                String _tmpTeacherName = _stmt.getText(_columnIndexOfTeacherName);
                String _tmpDays = _stmt.getText(_columnIndexOfDays);
                String _tmpStartTime = _stmt.getText(_columnIndexOfStartTime);
                String _tmpGraceTime = _stmt.getText(_columnIndexOfGraceTime);
                String _tmpEndTime = _stmt.getText(_columnIndexOfEndTime);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsActiveSession);
                boolean _tmpIsActiveSession = _tmp != 0;
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsBound);
                boolean _tmpIsBound = _tmp_1 != 0;
                if (_stmt.isNull(_columnIndexOfDeviceAddress)) {
                    _tmpDeviceAddress = null;
                } else {
                    String _tmpDeviceAddress2 = _stmt.getText(_columnIndexOfDeviceAddress);
                    _tmpDeviceAddress = _tmpDeviceAddress2;
                }
                _result = new ClassroomEntity(_tmpCid, _tmpTeacherId, _tmpClassroomName, _tmpTeacherName, _tmpDays, _tmpStartTime, _tmpGraceTime, _tmpEndTime, _tmpIsActiveSession, _tmpIsBound, _tmpDeviceAddress);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.ClassroomDao
    public Object getAllClassroomsSync(Continuation<? super List<ClassroomEntity>> continuation) {
        final String _sql = "SELECT * FROM classrooms";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.ClassroomDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClassroomDao_Impl.getAllClassroomsSync$lambda$8(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final List getAllClassroomsSync$lambda$8(String $_sql, SQLiteConnection _connection) {
        String _tmpDeviceAddress;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _tmp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cid");
            int _columnIndexOfTeacherId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherId");
            int _columnIndexOfClassroomName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomName");
            int _columnIndexOfTeacherName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "teacherName");
            int _columnIndexOfDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "days");
            int _columnIndexOfStartTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "startTime");
            int _columnIndexOfGraceTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "graceTime");
            int _columnIndexOfEndTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "endTime");
            int _columnIndexOfIsActiveSession = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isActiveSession");
            int _columnIndexOfIsBound = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isBound");
            int _columnIndexOfDeviceAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceAddress");
            List _result = new ArrayList();
            while (_stmt.step()) {
                String _tmpCid = _stmt.getText(_tmp);
                String _tmpTeacherId = _stmt.getText(_columnIndexOfTeacherId);
                String _tmpClassroomName = _stmt.getText(_columnIndexOfClassroomName);
                String _tmpTeacherName = _stmt.getText(_columnIndexOfTeacherName);
                String _tmpDays = _stmt.getText(_columnIndexOfDays);
                String _tmpStartTime = _stmt.getText(_columnIndexOfStartTime);
                String _tmpGraceTime = _stmt.getText(_columnIndexOfGraceTime);
                String _tmpEndTime = _stmt.getText(_columnIndexOfEndTime);
                int _columnIndexOfCid = _tmp;
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfIsActiveSession);
                boolean _tmpIsActiveSession = _tmp2 != 0;
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsBound);
                boolean _tmpIsBound = _tmp_1 != 0;
                if (_stmt.isNull(_columnIndexOfDeviceAddress)) {
                    _tmpDeviceAddress = null;
                } else {
                    String _tmpDeviceAddress2 = _stmt.getText(_columnIndexOfDeviceAddress);
                    _tmpDeviceAddress = _tmpDeviceAddress2;
                }
                ClassroomEntity _item = new ClassroomEntity(_tmpCid, _tmpTeacherId, _tmpClassroomName, _tmpTeacherName, _tmpDays, _tmpStartTime, _tmpGraceTime, _tmpEndTime, _tmpIsActiveSession, _tmpIsBound, _tmpDeviceAddress);
                _result.add(_item);
                _tmp = _columnIndexOfCid;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.ClassroomDao
    public Object deleteClassroomById(final String cid, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM classrooms WHERE cid = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.ClassroomDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ClassroomDao_Impl.deleteClassroomById$lambda$9(_sql, cid, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteClassroomById$lambda$9(String $_sql, String $cid, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $cid);
            _stmt.step();
            return Unit.INSTANCE;
        } finally {
            _stmt.close();
        }
    }

    /* JADX INFO: compiled from: ClassroomDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/db/ClassroomDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
