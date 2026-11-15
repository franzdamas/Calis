package com.example.ble;

import io.ktor.sse.ServerSentEventKt;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ManufacturerState.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lcom/example/ble/DeviceState;", "", "manufacturerHex", "", "displayName", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getManufacturerHex", "()Ljava/lang/String;", "getDisplayName", "FACTORY_MODE", "REGISTERING", "AUTHENTICATE", "SESSION_INACTIVE", "SESSION_ACTIVE_OPEN", "SESSION_ACTIVE_BUSY", "UNKNOWN", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum DeviceState {
    FACTORY_MODE("FFFF", "Factory Mode (Unregistered)"),
    REGISTERING("F1FF", "Registering"),
    AUTHENTICATE("FF00", "Authenticate/Idle"),
    SESSION_INACTIVE("FF01", "Session Inactive"),
    SESSION_ACTIVE_OPEN("0011", "Session Active (Open)"),
    SESSION_ACTIVE_BUSY("0111", "Session Busy"),
    UNKNOWN("0000", "Unknown State");

    private final String displayName;
    private final String manufacturerHex;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<DeviceState> getEntries() {
        return $ENTRIES;
    }

    DeviceState(String manufacturerHex, String displayName) {
        this.manufacturerHex = manufacturerHex;
        this.displayName = displayName;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final String getManufacturerHex() {
        return this.manufacturerHex;
    }

    /* JADX INFO: compiled from: ManufacturerState.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, d2 = {"Lcom/example/ble/DeviceState$Companion;", "", "<init>", "()V", "fromHex", "Lcom/example/ble/DeviceState;", "hex", "", "fromBytes", "bytes", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DeviceState fromHex(String hex) {
            Intrinsics.checkNotNullParameter(hex, "hex");
            String cleanHex = StringsKt.replace$default(hex, ServerSentEventKt.SPACE, "", false, 4, (Object) null).toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(cleanHex, "toUpperCase(...)");
            if (StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "0111", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "1101", false, 2, (Object) null)) {
                return DeviceState.SESSION_ACTIVE_BUSY;
            }
            if (StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "0011", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "1100", false, 2, (Object) null) || Intrinsics.areEqual(cleanHex, "11") || StringsKt.endsWith$default(cleanHex, "11", false, 2, (Object) null)) {
                return DeviceState.SESSION_ACTIVE_OPEN;
            }
            if (StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "FF01", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "01FF", false, 2, (Object) null)) {
                return DeviceState.SESSION_INACTIVE;
            }
            if (StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "FF00", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "00FF", false, 2, (Object) null)) {
                return DeviceState.AUTHENTICATE;
            }
            if (StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "F1FF", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "FFF1", false, 2, (Object) null)) {
                return DeviceState.REGISTERING;
            }
            return StringsKt.contains$default((CharSequence) cleanHex, (CharSequence) "FFFF", false, 2, (Object) null) ? DeviceState.FACTORY_MODE : DeviceState.UNKNOWN;
        }

        public final DeviceState fromBytes(byte[] bytes) {
            if (bytes != null) {
                if (!(bytes.length == 0)) {
                    if (bytes.length == 1 && (bytes[0] & UByte.MAX_VALUE) == 17) {
                        return DeviceState.SESSION_ACTIVE_OPEN;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : bytes) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String str = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                        sb.append(str);
                    }
                    String hex = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(hex, "toString(...)");
                    DeviceState state = fromHex(hex);
                    if (state != DeviceState.UNKNOWN) {
                        return state;
                    }
                    for (byte b2 : bytes) {
                        if ((b2 & UByte.MAX_VALUE) == 17) {
                            return DeviceState.SESSION_ACTIVE_OPEN;
                        }
                    }
                    return DeviceState.UNKNOWN;
                }
            }
            return DeviceState.UNKNOWN;
        }
    }
}
