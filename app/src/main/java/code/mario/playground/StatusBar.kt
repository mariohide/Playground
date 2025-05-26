package code.mario.playground

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun StatusBarEffect(isLight: Boolean = true, color: Color = Color.Transparent) {
    val view = LocalView.current
    val context = LocalContext.current
    val window = (context as Activity).window
    LaunchedEffect(Unit) {
        window.statusBarColor = color.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isLight
    }
}
