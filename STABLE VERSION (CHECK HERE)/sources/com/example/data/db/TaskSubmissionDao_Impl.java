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

/* JADX INFO: compiled from: TaskSubmissionDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000e2\u0006\u0010\u0013\u001a\u00020\u0011H\u0016J \u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/example/data/db/TaskSubmissionDao_Impl;", "Lcom/example/data/db/TaskSubmissionDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfTaskSubmissionEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/db/TaskSubmissionEntity;", "insertSubmission", "", "submission", "(Lcom/example/data/db/TaskSubmissionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSubmissionsForTask", "Lkotlinx/coroutines/flow/Flow;", "", "taskId", "", "getSubmissionsForStudent", "studentId", "getSubmission", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TaskSubmissionDao_Impl implements TaskSubmissionDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<TaskSubmissionEntity> __insertAdapterOfTaskSubmissionEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public TaskSubmissionDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfTaskSubmissionEntity = new EntityInsertAdapter<TaskSubmissionEntity>() { // from class: com.example.data.db.TaskSubmissionDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `task_submissions` (`id`,`taskId`,`studentId`,`studentName`,`score`,`timestamp`,`isMarked`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, TaskSubmissionEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6928bindLong(1, entity.getId());
                statement.mo6930bindText(2, entity.getTaskId());
                statement.mo6930bindText(3, entity.getStudentId());
                statement.mo6930bindText(4, entity.getStudentName());
                statement.mo6928bindLong(5, entity.getScore());
                statement.mo6928bindLong(6, entity.getTimestamp());
                statement.mo6928bindLong(7, entity.isMarked() ? 1L : 0L);
            }
        };
    }

    @Override // com.example.data.db.TaskSubmissionDao
    public Object insertSubmission(final TaskSubmissionEntity submission, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.TaskSubmissionDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskSubmissionDao_Impl.insertSubmission$lambda$0(this.f$0, submission, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertSubmission$lambda$0(TaskSubmissionDao_Impl this$0, TaskSubmissionEntity $submission, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfTaskSubmissionEntity.insert(_connection, $submission);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.TaskSubmissionDao
    public Flow<List<TaskSubmissionEntity>> getSubmissionsForTask(final String taskId) {
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        final String _sql = "SELECT * FROM task_submissions WHERE taskId = ? ORDER BY studentName ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"task_submissions"}, new Function1() { // from class: com.example.data.db.TaskSubmissionDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskSubmissionDao_Impl.getSubmissionsForTask$lambda$1(_sql, taskId, (SQLiteConnection) obj);
            }
        });
    }

    static final List getSubmissionsForTask$lambda$1(String $_sql, String $taskId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _tmp = 1;
        try {
            _stmt.mo6930bindText(1, $taskId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfTaskId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taskId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
            int _columnIndexOfScore = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "score");
            int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
            int _columnIndexOfIsMarked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isMarked");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpTaskId = _stmt.getText(_columnIndexOfTaskId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                int _argIndex = _tmp;
                int _tmpScore = (int) _stmt.getLong(_columnIndexOfScore);
                long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfIsMarked);
                boolean _tmpIsMarked = _tmp2 != 0;
                TaskSubmissionEntity _item = new TaskSubmissionEntity(_tmpId, _tmpTaskId, _tmpStudentId, _tmpStudentName, _tmpScore, _tmpTimestamp, _tmpIsMarked);
                _result.add(_item);
                _tmp = _argIndex;
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.TaskSubmissionDao
    public Flow<List<TaskSubmissionEntity>> getSubmissionsForStudent(final String studentId) {
        Intrinsics.checkNotNullParameter(studentId, "studentId");
        final String _sql = "SELECT * FROM task_submissions WHERE studentId = ?";
        return FlowUtil.createFlow(this.__db, false, new String[]{"task_submissions"}, new Function1() { // from class: com.example.data.db.TaskSubmissionDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskSubmissionDao_Impl.getSubmissionsForStudent$lambda$2(_sql, studentId, (SQLiteConnection) obj);
            }
        });
    }

    static final List getSubmissionsForStudent$lambda$2(String $_sql, String $studentId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _tmp = 1;
        try {
            _stmt.mo6930bindText(1, $studentId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfTaskId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taskId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
            int _columnIndexOfScore = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "score");
            int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
            int _columnIndexOfIsMarked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isMarked");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpTaskId = _stmt.getText(_columnIndexOfTaskId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                int _argIndex = _tmp;
                int _tmpScore = (int) _stmt.getLong(_columnIndexOfScore);
                long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfIsMarked);
                boolean _tmpIsMarked = _tmp2 != 0;
                TaskSubmissionEntity _item = new TaskSubmissionEntity(_tmpId, _tmpTaskId, _tmpStudentId, _tmpStudentName, _tmpScore, _tmpTimestamp, _tmpIsMarked);
                _result.add(_item);
                _tmp = _argIndex;
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.TaskSubmissionDao
    public Object getSubmission(final String taskId, final String studentId, Continuation<? super TaskSubmissionEntity> continuation) {
        final String _sql = "SELECT * FROM task_submissions WHERE taskId = ? AND studentId = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.TaskSubmissionDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskSubmissionDao_Impl.getSubmission$lambda$3(_sql, taskId, studentId, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final TaskSubmissionEntity getSubmission$lambda$3(String $_sql, String $taskId, String $studentId, SQLiteConnection _connection) throws Throwable {
        TaskSubmissionEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $taskId);
            try {
                _stmt.mo6930bindText(2, $studentId);
                int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
                int _columnIndexOfTaskId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taskId");
                int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
                int _columnIndexOfStudentName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentName");
                int _columnIndexOfScore = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "score");
                int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
                int _columnIndexOfIsMarked = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isMarked");
                if (_stmt.step()) {
                    long _tmpId = _stmt.getLong(_columnIndexOfId);
                    String _tmpTaskId = _stmt.getText(_columnIndexOfTaskId);
                    String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                    String _tmpStudentName = _stmt.getText(_columnIndexOfStudentName);
                    int _tmpScore = (int) _stmt.getLong(_columnIndexOfScore);
                    long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                    int _tmp = (int) _stmt.getLong(_columnIndexOfIsMarked);
                    boolean _tmpIsMarked = _tmp != 0;
                    _result = new TaskSubmissionEntity(_tmpId, _tmpTaskId, _tmpStudentId, _tmpStudentName, _tmpScore, _tmpTimestamp, _tmpIsMarked);
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

    /* JADX INFO: compiled from: TaskSubmissionDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/db/TaskSubmissionDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
