package code.mario.playground.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import code.mario.playground.ui.navigation.AppNavHost
import code.mario.playground.ui.theme.PlaygroundTheme

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaygroundTheme {
                PlaygroundApp()
            }
        }
    }
}

@Composable
fun PlaygroundApp() {
    var isDark by remember { mutableStateOf(false) }
    LaunchedEffect(isDark) {
        Log.d(TAG, "PlaygroundApp: is dark: $isDark")
    }
    PlaygroundTheme(darkTheme = isDark) {
        Surface {
            AppNavHost(modifier = Modifier.fillMaxSize()) {
                isDark = !isDark
            }
        }
    }
}
