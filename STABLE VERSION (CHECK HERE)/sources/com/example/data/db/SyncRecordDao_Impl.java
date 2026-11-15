package com.example.data.db;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
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

/* JADX INFO: compiled from: SyncRecordDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ \u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/example/data/db/SyncRecordDao_Impl;", "Lcom/example/data/db/SyncRecordDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfSyncRecordEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/db/SyncRecordEntity;", "insertOrUpdateSyncRecord", "", "syncRecord", "(Lcom/example/data/db/SyncRecordEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSyncRecord", "studentId", "", "classroomId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SyncRecordDao_Impl implements SyncRecordDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<SyncRecordEntity> __insertAdapterOfSyncRecordEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public SyncRecordDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfSyncRecordEntity = new EntityInsertAdapter<SyncRecordEntity>() { // from class: com.example.data.db.SyncRecordDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `sync_records` (`id`,`studentId`,`classroomId`,`lastScheduleSyncedAt`,`lastTaskSyncedAt`) VALUES (nullif(?, 0),?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, SyncRecordEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo6928bindLong(1, entity.getId());
                statement.mo6930bindText(2, entity.getStudentId());
                statement.mo6930bindText(3, entity.getClassroomId());
                statement.mo6928bindLong(4, entity.getLastScheduleSyncedAt());
                statement.mo6928bindLong(5, entity.getLastTaskSyncedAt());
            }
        };
    }

    @Override // com.example.data.db.SyncRecordDao
    public Object insertOrUpdateSyncRecord(final SyncRecordEntity syncRecord, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.db.SyncRecordDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SyncRecordDao_Impl.insertOrUpdateSyncRecord$lambda$0(this.f$0, syncRecord, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertOrUpdateSyncRecord$lambda$0(SyncRecordDao_Impl this$0, SyncRecordEntity $syncRecord, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfSyncRecordEntity.insert(_connection, $syncRecord);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.db.SyncRecordDao
    public Object getSyncRecord(final String studentId, final String classroomId, Continuation<? super SyncRecordEntity> continuation) {
        final String _sql = "SELECT * FROM sync_records WHERE studentId = ? AND classroomId = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.db.SyncRecordDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SyncRecordDao_Impl.getSyncRecord$lambda$1(_sql, studentId, classroomId, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final SyncRecordEntity getSyncRecord$lambda$1(String $_sql, String $studentId, String $classroomId, SQLiteConnection _connection) throws Throwable {
        SyncRecordEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo6930bindText(1, $studentId);
            try {
                _stmt.mo6930bindText(2, $classroomId);
                int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
                int _columnIndexOfStudentId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "studentId");
                int _columnIndexOfClassroomId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "classroomId");
                int _columnIndexOfLastScheduleSyncedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastScheduleSyncedAt");
                int _columnIndexOfLastTaskSyncedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastTaskSyncedAt");
                if (_stmt.step()) {
                    long _tmpId = _stmt.getLong(_columnIndexOfId);
                    String _tmpStudentId = _stmt.getText(_columnIndexOfStudentId);
                    String _tmpClassroomId = _stmt.getText(_columnIndexOfClassroomId);
                    long _tmpLastScheduleSyncedAt = _stmt.getLong(_columnIndexOfLastScheduleSyncedAt);
                    long _tmpLastTaskSyncedAt = _stmt.getLong(_columnIndexOfLastTaskSyncedAt);
                    _result = new SyncRecordEntity(_tmpId, _tmpStudentId, _tmpClassroomId, _tmpLastScheduleSyncedAt, _tmpLastTaskSyncedAt);
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

    /* JADX INFO: compiled from: SyncRecordDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/db/SyncRecordDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
