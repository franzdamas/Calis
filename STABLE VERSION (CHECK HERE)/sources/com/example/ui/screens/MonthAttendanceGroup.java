package com.example.ui.screens;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TeacherMainScreen.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/example/ui/screens/MonthAttendanceGroup;", "", "monthKey", "", "monthName", "dates", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getMonthKey", "()Ljava/lang/String;", "getMonthName", "getDates", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class MonthAttendanceGroup {
    public static final int $stable = 8;
    private final List<String> dates;
    private final String monthKey;
    private final String monthName;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MonthAttendanceGroup copy$default(MonthAttendanceGroup monthAttendanceGroup, String str, String str2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = monthAttendanceGroup.monthKey;
        }
        if ((i & 2) != 0) {
            str2 = monthAttendanceGroup.monthName;
        }
        if ((i & 4) != 0) {
            list = monthAttendanceGroup.dates;
        }
        return monthAttendanceGroup.copy(str, str2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMonthKey() {
        return this.monthKey;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMonthName() {
        return this.monthName;
    }

    public final List<String> component3() {
        return this.dates;
    }

    public final MonthAttendanceGroup copy(String monthKey, String monthName, List<String> dates) {
        Intrinsics.checkNotNullParameter(monthKey, "monthKey");
        Intrinsics.checkNotNullParameter(monthName, "monthName");
        Intrinsics.checkNotNullParameter(dates, "dates");
        return new MonthAttendanceGroup(monthKey, monthName, dates);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MonthAttendanceGroup)) {
            return false;
        }
        MonthAttendanceGroup monthAttendanceGroup = (MonthAttendanceGroup) other;
        return Intrinsics.areEqual(this.monthKey, monthAttendanceGroup.monthKey) && Intrinsics.areEqual(this.monthName, monthAttendanceGroup.monthName) && Intrinsics.areEqual(this.dates, monthAttendanceGroup.dates);
    }

    public int hashCode() {
        return (((this.monthKey.hashCode() * 31) + this.monthName.hashCode()) * 31) + this.dates.hashCode();
    }

    public String toString() {
        return "MonthAttendanceGroup(monthKey=" + this.monthKey + ", monthName=" + this.monthName + ", dates=" + this.dates + ")";
    }

    public MonthAttendanceGroup(String monthKey, String monthName, List<String> dates) {
        Intrinsics.checkNotNullParameter(monthKey, "monthKey");
        Intrinsics.checkNotNullParameter(monthName, "monthName");
        Intrinsics.checkNotNullParameter(dates, "dates");
        this.monthKey = monthKey;
        this.monthName = monthName;
        this.dates = dates;
    }

    public final String getMonthKey() {
        return this.monthKey;
    }

    public final String getMonthName() {
        return this.monthName;
    }

    public final List<String> getDates() {
        return this.dates;
    }
}
