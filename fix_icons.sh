sed -i 's/Icons.Default.ArrowBack/Icons.AutoMirrored.Filled.ArrowBack/g' app/src/main/java/com/example/ui/screens/TeacherMainScreen.kt
sed -i 's/Icons.Default.KeyboardArrowRight/Icons.AutoMirrored.Filled.KeyboardArrowRight/g' app/src/main/java/com/example/ui/screens/TeacherMainScreen.kt
sed -i 's/Icons.Default.KeyboardArrowRight/Icons.AutoMirrored.Filled.KeyboardArrowRight/g' app/src/main/java/com/example/ui/screens/StudentMainScreen.kt
sed -i 's/Icons.Default.ArrowBack/Icons.AutoMirrored.Filled.ArrowBack/g' app/src/main/java/com/example/ui/screens/StudentMainScreen.kt

sed -i 's/import androidx.compose.material.icons.automirrored.filled.ArrowBack//g' app/src/main/java/com/example/ui/screens/TeacherMainScreen.kt
sed -i 's/import androidx.compose.material.icons.automirrored.filled.ArrowBack//g' app/src/main/java/com/example/ui/screens/StudentMainScreen.kt
sed -i 's/import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight//g' app/src/main/java/com/example/ui/screens/TeacherMainScreen.kt
sed -i 's/import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight//g' app/src/main/java/com/example/ui/screens/StudentMainScreen.kt

sed -i 's/import androidx.compose.material.icons.Icons/import androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.automirrored.filled.ArrowBack\nimport androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight/g' app/src/main/java/com/example/ui/screens/TeacherMainScreen.kt

sed -i 's/import androidx.compose.material.icons.Icons/import androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.automirrored.filled.ArrowBack\nimport androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight/g' app/src/main/java/com/example/ui/screens/StudentMainScreen.kt

