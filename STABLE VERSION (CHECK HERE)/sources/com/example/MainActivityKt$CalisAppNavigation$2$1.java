package com.example;

import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import com.example.ui.viewmodel.AuthViewModel;
import com.example.ui.viewmodel.StudentViewModel;
import com.example.ui.viewmodel.TeacherViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.MainActivityKt$CalisAppNavigation$2$1", f = "MainActivity.kt", i = {0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6}, l = {ComposerKt.referenceKey, 215, 218, 226, 232, 238, 242}, m = "invokeSuspend", n = {"startTime", "role", "startTime", "hasAcct", "elapsed", "role", "startTime", "hasAcct", "elapsed", "role", "startTime", "hasAcct", "waitStart", "role", "startTime", "hasAcct", "waitStart", "role", "startTime", "hasAcct", "elapsed", "role", "startTime", "hasAcct", "elapsed"}, s = {"J$0", "L$0", "J$0", "Z$0", "J$1", "L$0", "J$0", "Z$0", "J$1", "L$0", "J$0", "Z$0", "J$1", "L$0", "J$0", "Z$0", "J$1", "L$0", "J$0", "Z$0", "J$1", "L$0", "J$0", "Z$0", "J$1"})
final class MainActivityKt$CalisAppNavigation$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $activeScreen$delegate;
    final /* synthetic */ MutableState<Integer> $authScreenInitialStep$delegate;
    final /* synthetic */ AuthViewModel $authViewModel;
    final /* synthetic */ boolean $isAlreadyRunningInBackground;
    final /* synthetic */ MutableState<String> $startupStatusText$delegate;
    final /* synthetic */ StudentViewModel $studentViewModel;
    final /* synthetic */ TeacherViewModel $teacherViewModel;
    long J$0;
    long J$1;
    Object L$0;
    boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivityKt$CalisAppNavigation$2$1(boolean z, AuthViewModel authViewModel, TeacherViewModel teacherViewModel, StudentViewModel studentViewModel, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<Integer> mutableState3, Continuation<? super MainActivityKt$CalisAppNavigation$2$1> continuation) {
        super(2, continuation);
        this.$isAlreadyRunningInBackground = z;
        this.$authViewModel = authViewModel;
        this.$teacherViewModel = teacherViewModel;
        this.$studentViewModel = studentViewModel;
        this.$activeScreen$delegate = mutableState;
        this.$startupStatusText$delegate = mutableState2;
        this.$authScreenInitialStep$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivityKt$CalisAppNavigation$2$1(this.$isAlreadyRunningInBackground, this.$authViewModel, this.$teacherViewModel, this.$studentViewModel, this.$activeScreen$delegate, this.$startupStatusText$delegate, this.$authScreenInitialStep$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivityKt$CalisAppNavigation$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:26:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:28:0x010a  */
    /* JADX WARN: Code duplicated, block: B:30:0x0123 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:31:0x0124  */
    /* JADX WARN: Code duplicated, block: B:33:0x0128  */
    /* JADX WARN: Code duplicated, block: B:36:0x014a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:38:0x015a  */
    /* JADX WARN: Code duplicated, block: B:40:0x0160  */
    /* JADX WARN: Code duplicated, block: B:61:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:63:0x020f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:64:0x0210  */
    /* JADX WARN: Code duplicated, block: B:66:0x021a  */
    /* JADX WARN: Code duplicated, block: B:69:0x023d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:70:0x023e  */
    /* JADX WARN: Code duplicated, block: B:73:0x025a  */
    /* JADX WARN: Code duplicated, block: B:76:0x0261  */
    /* JADX WARN: Code duplicated, block: B:77:0x0264  */
    /* JADX WARN: Code duplicated, block: B:87:0x00df A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:? A[LOOP:1: B:18:0x00bf->B:89:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x01e7 -> B:58:0x01ea). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:42:0x0182
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            Method dump skipped, instruction units count: 644
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.MainActivityKt$CalisAppNavigation$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
