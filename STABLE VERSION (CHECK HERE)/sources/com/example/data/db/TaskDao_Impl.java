package com.example.data.db;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import io.ktor.http.LinkHeader;
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

/* JADX INFO: compiled from: TaskDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0012H\u0096@¢\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001a\u001a\u00020\u0014H\u0096@¢\u0006\u0002\u0010\u0016J\u0016\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u0014H\u0096@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/example/data/db/TaskDao_Impl;", "Lcom/example/data/db/TaskDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfTaskEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/db/TaskEntity;", "__updateAdapterOfTaskEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "insertTask", "", "task", "(Lcom/example/data/db/TaskEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTask", "getTasksForClassroom", "Lkotlinx/coroutines/flow/Flow;", "", "classroomId", "", "getTasksForClassroomSync", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTasksSync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTaskById", "tkid", "deleteTaskById", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TaskDao_Impl implements TaskDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<TaskEntity> __insertAdapterOfTaskEntity;
    private final EntityDeleteOrUpdateAdapter<TaskEntity> __updateAdapterOfTaskEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public TaskDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfTaskEntity = new EntityInsertAdapter<TaskEntity>() { // from class: com.example.data.db.TaskDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `tasks` (`tkid`,`classroomId`,`title`,`description`,`deadline`,`datePosted`,`maxScore`) VALUES (?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, TaskEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6930bindText(1, entity.getTkid());
                statement.mo6930bindText(2, entity.getClassroomId());
                statement.mo6930bindText(3, entity.getTitle());
                statement.mo6930bindText(4, entity.getDescription());
                statement.mo6928bindLong(5, entity.getDeadline());
                statement.mo6928bindLong(6, entity.getDatePosted());
                statement.mo6928bindLong(7, entity.getMaxScore());
            }
        };
        this.__updateAdapterOfTaskEntity = new EntityDeleteOrUpdateAdapter<TaskEntity>() { // from class: com.example.data.db.TaskDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `tasks` SET `tkid` = ?,`classroomId` = ?,`title` = ?,`description` = ?,`deadline` = ?,`datePosted` = ?,`maxScore` = ? WHERE `tkid` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, TaskEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6930bindText(1, entity.getTkid());
                statement.mo6930bindText(2, entity.getClassroomId());
                statement.mo6930bindText(3, entity.getTitle());
                statement.mo6930bindText(4, entity.getDescription());
                statement.mo6928bindLong(5, entity.getDeadline());
                statement.mo6928bindLong(6, entity.getDatePosted());
                statement.mo6928bindLong(7, entity.getMaxScore());
                statement.mo6930bindText(8, entity.getTkid());
            }
        };
    }

    @Override // com.example.data.db.TaskDao
    public Object insertTask(final TaskEntity task, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.TaskDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDao_Impl.insertTask$lambda$0(this.f$0, task, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertTask$lambda$0(TaskDao_Impl this$0, TaskEntity $task, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfTaskEntity.insert(_connection, $task);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.TaskDao
    public Object updateTask(final TaskEntity task, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.TaskDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDao_Impl.updateTask$lambda$1(this.f$0, task, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit updateTask$lambda$1(TaskDao_Impl this$0, TaskEntity $task, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfTaskEntity.handle(_connection, $task);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.TaskDao
    public Flow<List<TaskEntity>> getTasksForClassroom(final String classroomId) {
        Intrinsics.checkNotNullParameter(classroomId, "classroomId");
        final String _sql = "SELECT * FROM tasks WHERE classroomId = ? ORDER BY datePosted DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"tasks"}, new Function1() { // from class: com.example.data.db.TaskDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDao_Impl.getTasksForClassroom$lambda$2(_sql, classroomId, (SQLiteConnection) obj);
            }
        });
    }

    static final List getTasksForClassroom$lambda$2(String $_sql, String $classroomId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _argIndex = 1;
        try {
            _stmt.mo6930bindText(1, $classroomId);
            int _columnIndexOfTkid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tkid");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, LinkHeader.Parameters.Title);
            int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
            int _columnIndexOfDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deadline");
            int _columnIndexOfDatePosted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "datePosted");
            int _columnIndexOfMaxScore = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "maxScore");
            List _result = new ArrayList();
            while (_stmt.step()) {
                String _tmpTkid = _stmt.getText(_columnIndexOfTkid);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpTitle = _stmt.getText(_columnIndexOfTitle);
                String _tmpDescription = _stmt.getText(_columnIndexOfDescription);
                long _tmpDeadline = _stmt.getLong(_columnIndexOfDeadline);
                long _tmpDatePosted = _stmt.getLong(_columnIndexOfDatePosted);
                int _argIndex2 = _argIndex;
                int _tmpMaxScore = (int) _stmt.getLong(_columnIndexOfMaxScore);
                TaskEntity _item = new TaskEntity(_tmpTkid, _tmpClassroomId, _tmpTitle, _tmpDescription, _tmpDeadline, _tmpDatePosted, _tmpMaxScore);
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

    @Override // com.example.data.db.TaskDao
    public Object getTasksForClassroomSync(final String classroomId, Continuation<? super List<TaskEntity>> continuation) {
        final String _sql = "SELECT * FROM tasks WHERE classroomId = ? ORDER BY datePosted ASC";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.TaskDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDao_Impl.getTasksForClassroomSync$lambda$3(_sql, classroomId, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final List getTasksForClassroomSync$lambda$3(String $_sql, String $classroomId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _argIndex = 1;
        try {
            _stmt.mo6930bindText(1, $classroomId);
            int _columnIndexOfTkid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tkid");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, LinkHeader.Parameters.Title);
            int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
            int _columnIndexOfDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deadline");
            int _columnIndexOfDatePosted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "datePosted");
            int _columnIndexOfMaxScore = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "maxScore");
            List _result = new ArrayList();
            while (_stmt.step()) {
                String _tmpTkid = _stmt.getText(_columnIndexOfTkid);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpTitle = _stmt.getText(_columnIndexOfTitle);
                String _tmpDescription = _stmt.getText(_columnIndexOfDescription);
                long _tmpDeadline = _stmt.getLong(_columnIndexOfDeadline);
                long _tmpDatePosted = _stmt.getLong(_columnIndexOfDatePosted);
                int _argIndex2 = _argIndex;
                int _tmpMaxScore = (int) _stmt.getLong(_columnIndexOfMaxScore);
                TaskEntity _item = new TaskEntity(_tmpTkid, _tmpClassroomId, _tmpTitle, _tmpDescription, _tmpDeadline, _tmpDatePosted, _tmpMaxScore);
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

    @Override // com.example.data.db.TaskDao
    public Object getAllTasksSync(Continuation<? super List<TaskEntity>> continuation) {
        final String _sql = "SELECT * FROM tasks ORDER BY deadline ASC";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.TaskDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDao_Impl.getAllTasksSync$lambda$4(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final List getAllTasksSync$lambda$4(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfTkid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tkid");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, LinkHeader.Parameters.Title);
            int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
            int _columnIndexOfDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deadline");
            int _columnIndexOfDatePosted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "datePosted");
            int _columnIndexOfMaxScore = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "maxScore");
            List _result = new ArrayList();
            while (_stmt.step()) {
                String _tmpTkid = _stmt.getText(_columnIndexOfTkid);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpTitle = _stmt.getText(_columnIndexOfTitle);
                String _tmpDescription = _stmt.getText(_columnIndexOfDescription);
                long _tmpDeadline = _stmt.getLong(_columnIndexOfDeadline);
                long _tmpDatePosted = _stmt.getLong(_columnIndexOfDatePosted);
                int _tmpMaxScore = (int) _stmt.getLong(_columnIndexOfMaxScore);
                TaskEntity _item = new TaskEntity(_tmpTkid, _tmpClassroomId, _tmpTitle, _tmpDescription, _tmpDeadline, _tmpDatePosted, _tmpMaxScore);
                _result.add(_item);
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.TaskDao
    public Object getTaskById(final String tkid, Continuation<? super TaskEntity> continuation) {
        final String _sql = "SELECT * FROM tasks WHERE tkid = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.TaskDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDao_Impl.getTaskById$lambda$5(_sql, tkid, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final TaskEntity getTaskById$lambda$5(String $_sql, String $tkid, SQLiteConnection _connection) {
        TaskEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $tkid);
            int _columnIndexOfTkid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tkid");
            int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
            int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, LinkHeader.Parameters.Title);
            int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
            int _columnIndexOfDeadline = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deadline");
            int _columnIndexOfDatePosted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "datePosted");
            int _columnIndexOfMaxScore = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "maxScore");
            if (_stmt.step()) {
                String _tmpTkid = _stmt.getText(_columnIndexOfTkid);
                String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                String _tmpTitle = _stmt.getText(_columnIndexOfTitle);
                String _tmpDescription = _stmt.getText(_columnIndexOfDescription);
                long _tmpDeadline = _stmt.getLong(_columnIndexOfDeadline);
                long _tmpDatePosted = _stmt.getLong(_columnIndexOfDatePosted);
                int _tmpMaxScore = (int) _stmt.getLong(_columnIndexOfMaxScore);
                _result = new TaskEntity(_tmpTkid, _tmpClassroomId, _tmpTitle, _tmpDescription, _tmpDeadline, _tmpDatePosted, _tmpMaxScore);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.db.TaskDao
    public Object deleteTaskById(final String tkid, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM tasks WHERE tkid = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.TaskDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDao_Impl.deleteTaskById$lambda$6(_sql, tkid, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteTaskById$lambda$6(String $_sql, String $tkid, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $tkid);
            _stmt.step();
            return Unit.INSTANCE;
        } finally {
            _stmt.close();
        }
    }

    /* JADX INFO: compiled from: TaskDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/db/TaskDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
