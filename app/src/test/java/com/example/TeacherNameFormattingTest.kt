package com.example

import com.example.util.BlePermissionUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class TeacherNameFormattingTest {

    @Test
    fun testFormatMiddleInitials_multiWord() {
        assertEquals("A.B.", BlePermissionUtils.formatMiddleInitials("Apple Bee"))
        assertEquals("A.B.", BlePermissionUtils.formatMiddleInitials("Apple B."))
        assertEquals("A.B.", BlePermissionUtils.formatMiddleInitials("A.B."))
        assertEquals("A.B.", BlePermissionUtils.formatMiddleInitials("A. B."))
        assertEquals("A.B.", BlePermissionUtils.formatMiddleInitials("Apple-Bee"))
        assertEquals("A.", BlePermissionUtils.formatMiddleInitials("Apple"))
        assertEquals("B.", BlePermissionUtils.formatMiddleInitials("Bee"))
        assertEquals("A.B.C.", BlePermissionUtils.formatMiddleInitials("Apple Bee Charlie"))
        assertEquals("", BlePermissionUtils.formatMiddleInitials(""))
    }

    @Test
    fun testFormatTeacherName_explicitComponents() {
        val result = BlePermissionUtils.formatTeacherName(
            honorific = "Sir",
            firstName = "Teacher",
            middleName = "Apple Bee",
            surname = "Test"
        )
        assertEquals("Sir Teacher A.B. Test", result)
    }

    @Test
    fun testFormatTeacherNameWithMiddleInitial_fullNameString() {
        // User's specific issue: "Sir Teacher Apple Bee Test" -> "Sir Teacher A.B. Test"
        assertEquals(
            "Sir Teacher A.B. Test",
            BlePermissionUtils.formatTeacherNameWithMiddleInitial("Sir Teacher Apple Bee Test")
        )

        // Previous flawed output formatted with single initial: "Sir Teacher Apple B. Test" -> should now format to "Sir Teacher A.B. Test"
        assertEquals(
            "Sir Teacher A.B. Test",
            BlePermissionUtils.formatTeacherNameWithMiddleInitial("Sir Teacher Apple B. Test")
        )

        // Already formatted: "Sir Teacher A.B. Test" -> stays "Sir Teacher A.B. Test"
        assertEquals(
            "Sir Teacher A.B. Test",
            BlePermissionUtils.formatTeacherNameWithMiddleInitial("Sir Teacher A.B. Test")
        )

        // Without honorific: "Teacher Apple Bee Test" -> "Teacher A.B. Test"
        assertEquals(
            "Teacher A.B. Test",
            BlePermissionUtils.formatTeacherNameWithMiddleInitial("Teacher Apple Bee Test")
        )

        // Single middle name: "Sir Teacher Bee Test" -> "Sir Teacher B. Test"
        assertEquals(
            "Sir Teacher B. Test",
            BlePermissionUtils.formatTeacherNameWithMiddleInitial("Sir Teacher Bee Test")
        )

        // No middle name: "Sir Teacher Test" -> "Sir Teacher Test"
        assertEquals(
            "Sir Teacher Test",
            BlePermissionUtils.formatTeacherNameWithMiddleInitial("Sir Teacher Test")
        )

        // Female honorifics
        assertEquals(
            "Ma'am Maria A.B. Santos",
            BlePermissionUtils.formatTeacherNameWithMiddleInitial("Ma'am Maria Apple Bee Santos")
        )
        assertEquals(
            "Ms. Santos",
            BlePermissionUtils.formatTeacherNameWithMiddleInitial("Ms. Santos")
        )
    }
}
