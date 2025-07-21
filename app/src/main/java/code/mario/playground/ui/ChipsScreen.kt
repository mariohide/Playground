package code.mario.playground.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ChipsScreen() {
    // 取消最小交互大小限制（一般会限制为 48.dp，这是 chip 上下看起来有「边距」的原因）
    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
        Column(
            Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .safeDrawingPadding()
        ) {
            FlowRow {
                FilterChip(
                    modifier = Modifier
                        .background(Color.Red)
                        .heightIn(32.dp),
                    selected = false,
                    onClick = {},
                    label = { Text(text = "Good") }
                )
                FilterChip(
                    modifier = Modifier
                        .background(Color.Red)
                        .heightIn(32.dp),
                    selected = false,
                    onClick = {},
                    label = { Text(text = "Bad") }
                )
                FilterChip(
                    modifier = Modifier
                        .background(Color.Red)
                        .heightIn(32.dp),
                    selected = false,
                    onClick = {},
                    label = { Text(text = "Ugly") }
                )
                FilterChip(
                    modifier = Modifier
                        .background(Color.Red)
                        .heightIn(32.dp),
                    selected = false,
                    onClick = {},
                    label = { Text(text = "Very Very Very Very Very Very Very Very Very Very Very Very Very Very Very Very Very Very Very Very Very Very Good") }
                )
                FilterChip(
                    modifier = Modifier
                        .background(Color.Red)
                        .heightIn(32.dp),
                    selected = false,
                    onClick = {},
                    label = { Text(text = "Very Bad") }
                )
                FilterChip(
                    modifier = Modifier
                        .background(Color.Red)
                        .heightIn(32.dp),
                    selected = false,
                    onClick = {},
                    label = { Text(text = "Very Ugly") }
                )
            }
        }
    }
}