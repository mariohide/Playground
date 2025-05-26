package code.mario.playground

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import code.mario.playground.ui.theme.PlaygroundTheme

val colors = listOf(Color(0xFF673AB7), Color(0xFF3700B3), Color(0xFF03DAC5))

@Composable
fun StatusBarScreen() {
    val view = LocalView.current
    val context = LocalContext.current
    val window = (context as Activity).window
    val listState = rememberLazyListState()
    val headerIndex = 1
    val isHeaderDismissed by remember {
        derivedStateOf {
            val firstVisibleItem = listState.firstVisibleItemIndex
            // 1. 如果第一个可见项的索引已经超过了 Header 的索引，那么 Header 必然吸顶
            // 2. 如果第一个可见项就是 Header，并且它有滚动偏移量（表示列表向上滚动了），
            //    那么 Header 也处于吸顶状态
            firstVisibleItem > headerIndex || (firstVisibleItem == headerIndex && listState.firstVisibleItemScrollOffset > 50)
        }
    }
    var statusColor by remember { mutableStateOf(Color(0x66000000)) }
    var titleBarColor by remember { mutableStateOf(Color(0x00000000)) }
    LaunchedEffect(isHeaderDismissed) {
        statusColor = if (!isHeaderDismissed) Color(0x66000000) else Color(0x00000000)
        window.statusBarColor = statusColor.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isHeaderDismissed
        titleBarColor = if (!isHeaderDismissed) Color(0x00000000) else Color(0xEEFFFFFF)
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
            item { Spacer(Modifier.statusBarsPadding()) }
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.White)
                )
            }
            items(100) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(colors[it % 3])
                )
            }
        }
        Box(Modifier.fillMaxWidth().background(titleBarColor).statusBarsPadding().height(48.dp))
    }
}

@Preview
@Composable
private fun PrevMain() {
    PlaygroundTheme {
        StatusBarScreen()
    }
}