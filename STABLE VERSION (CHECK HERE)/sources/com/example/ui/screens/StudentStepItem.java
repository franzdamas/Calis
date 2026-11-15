package com.example.ui.screens;

import androidx.compose.runtime.Composer;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StudentMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B6\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0015\b\u0002\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\u001b\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nHÆ\u0003¢\u0006\u0002\u0010\u0012JC\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0015\b\u0002\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nHÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R \u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\n¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/example/ui/screens/StudentStepItem;", "", LinkHeader.Parameters.Title, "", "description", "isCompleted", "", "actionButton", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function2;)V", "getTitle", "()Ljava/lang/String;", "getDescription", "()Z", "getActionButton", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function2;)Lcom/example/ui/screens/StudentStepItem;", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
final /* data */ class StudentStepItem {
    private final Function2<Composer, Integer, Unit> actionButton;
    private final String description;
    private final boolean isCompleted;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StudentStepItem copy$default(StudentStepItem studentStepItem, String str, String str2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = studentStepItem.title;
        }
        if ((i & 2) != 0) {
            str2 = studentStepItem.description;
        }
        if ((i & 4) != 0) {
            z = studentStepItem.isCompleted;
        }
        if ((i & 8) != 0) {
            function2 = studentStepItem.actionButton;
        }
        return studentStepItem.copy(str, str2, z, function2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsCompleted() {
        return this.isCompleted;
    }

    public final Function2<Composer, Integer, Unit> component4() {
        return this.actionButton;
    }

    public final StudentStepItem copy(String title, String description, boolean isCompleted, Function2<? super Composer, ? super Integer, Unit> actionButton) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        return new StudentStepItem(title, description, isCompleted, actionButton);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StudentStepItem)) {
            return false;
        }
        StudentStepItem studentStepItem = (StudentStepItem) other;
        return Intrinsics.areEqual(this.title, studentStepItem.title) && Intrinsics.areEqual(this.description, studentStepItem.description) && this.isCompleted == studentStepItem.isCompleted && Intrinsics.areEqual(this.actionButton, studentStepItem.actionButton);
    }

    public int hashCode() {
        return (((((this.title.hashCode() * 31) + this.description.hashCode()) * 31) + Boolean.hashCode(this.isCompleted)) * 31) + (this.actionButton == null ? 0 : this.actionButton.hashCode());
    }

    public String toString() {
        return "StudentStepItem(title=" + this.title + ", description=" + this.description + ", isCompleted=" + this.isCompleted + ", actionButton=" + this.actionButton + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StudentStepItem(String title, String description, boolean isCompleted, Function2<? super Composer, ? super Integer, Unit> function2) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.actionButton = function2;
    }

    public /* synthetic */ StudentStepItem(String str, String str2, boolean z, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, (i & 8) != 0 ? null : function2);
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getDescription() {
        return this.description;
    }

    public final boolean isCompleted() {
        return this.isCompleted;
    }

    public final Function2<Composer, Integer, Unit> getActionButton() {
        return this.actionButton;
    }
}
