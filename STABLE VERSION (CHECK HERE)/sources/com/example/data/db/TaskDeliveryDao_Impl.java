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

/* JADX INFO: compiled from: TaskDeliveryDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0013J \u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/example/data/db/TaskDeliveryDao_Impl;", "Lcom/example/data/db/TaskDeliveryDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfTaskDeliveryEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/db/TaskDeliveryEntity;", "insertDelivery", "", "delivery", "(Lcom/example/data/db/TaskDeliveryEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeliveriesForTask", "Lkotlinx/coroutines/flow/Flow;", "", "taskId", "", "getDeliveriesForTaskSync", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDelivery", "studentId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TaskDeliveryDao_Impl implements TaskDeliveryDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<TaskDeliveryEntity> __insertAdapterOfTaskDeliveryEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public TaskDeliveryDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfTaskDeliveryEntity = new EntityInsertAdapter<TaskDeliveryEntity>() { // from class: com.example.data.db.TaskDeliveryDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `task_deliveries` (`taskId`,`studentId`,`received`) VALUES (?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, TaskDeliveryEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6930bindText(1, entity.getTaskId());
                statement.mo6930bindText(2, entity.getStudentId());
                statement.mo6928bindLong(3, entity.getReceived() ? 1L : 0L);
            }
        };
    }

    @Override // com.example.data.db.TaskDeliveryDao
    public Object insertDelivery(final TaskDeliveryEntity delivery, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.TaskDeliveryDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDeliveryDao_Impl.insertDelivery$lambda$0(this.f$0, delivery, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertDelivery$lambda$0(TaskDeliveryDao_Impl this$0, TaskDeliveryEntity $delivery, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfTaskDeliveryEntity.insert(_connection, $delivery);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.TaskDeliveryDao
    public Flow<List<TaskDeliveryEntity>> getDeliveriesForTask(final String taskId) {
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        final String _sql = "SELECT * FROM task_deliveries WHERE taskId = ?";
        return FlowUtil.createFlow(this.__db, false, new String[]{"task_deliveries"}, new Function1() { // from class: com.example.data.db.TaskDeliveryDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDeliveryDao_Impl.getDeliveriesForTask$lambda$1(_sql, taskId, (SQLiteConnection) obj);
            }
        });
    }

    static final List getDeliveriesForTask$lambda$1(String $_sql, String $taskId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $taskId);
            int _columnIndexOfTaskId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taskId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfReceived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "received");
            List _result = new ArrayList();
            while (_stmt.step()) {
                String _tmpTaskId = _stmt.getText(_columnIndexOfTaskId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                int _tmp = (int) _stmt.getLong(_columnIndexOfReceived);
                boolean _tmpReceived = _tmp != 0;
                TaskDeliveryEntity _item = new TaskDeliveryEntity(_tmpTaskId, _tmpStudentId, _tmpReceived);
                _result.add(_item);
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.TaskDeliveryDao
    public Object getDeliveriesForTaskSync(final String taskId, Continuation<? super List<TaskDeliveryEntity>> continuation) {
        final String _sql = "SELECT * FROM task_deliveries WHERE taskId = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.TaskDeliveryDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDeliveryDao_Impl.getDeliveriesForTaskSync$lambda$2(_sql, taskId, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final List getDeliveriesForTaskSync$lambda$2(String $_sql, String $taskId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $taskId);
            int _columnIndexOfTaskId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taskId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfReceived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "received");
            List _result = new ArrayList();
            while (_stmt.step()) {
                String _tmpTaskId = _stmt.getText(_columnIndexOfTaskId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                int _tmp = (int) _stmt.getLong(_columnIndexOfReceived);
                boolean _tmpReceived = _tmp != 0;
                TaskDeliveryEntity _item = new TaskDeliveryEntity(_tmpTaskId, _tmpStudentId, _tmpReceived);
                _result.add(_item);
            }
            _stmt.close();
            return _result;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.db.TaskDeliveryDao
    public Object getDelivery(final String taskId, final String studentId, Continuation<? super TaskDeliveryEntity> continuation) {
        final String _sql = "SELECT * FROM task_deliveries WHERE taskId = ? AND studentId = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.TaskDeliveryDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TaskDeliveryDao_Impl.getDelivery$lambda$3(_sql, taskId, studentId, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final TaskDeliveryEntity getDelivery$lambda$3(String $_sql, String $taskId, String $studentId, SQLiteConnection _connection) {
        TaskDeliveryEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $taskId);
            _stmt.mo6930bindText(2, $studentId);
            int _columnIndexOfTaskId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taskId");
            int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
            int _columnIndexOfReceived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "received");
            if (_stmt.step()) {
                String _tmpTaskId = _stmt.getText(_columnIndexOfTaskId);
                String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                int _tmp = (int) _stmt.getLong(_columnIndexOfReceived);
                boolean _tmpReceived = _tmp != 0;
                _result = new TaskDeliveryEntity(_tmpTaskId, _tmpStudentId, _tmpReceived);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    /* JADX INFO: compiled from: TaskDeliveryDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/db/TaskDeliveryDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
