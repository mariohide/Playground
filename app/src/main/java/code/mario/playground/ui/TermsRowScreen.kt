package code.mario.playground.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.onLayoutRectChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun TermsRowScreen() {
    var lineHeightPx by remember { mutableFloatStateOf(0f) }
    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Row {
            Checkbox(
                onCheckedChange = {},
                checked = true,
                modifier = Modifier.alignBy { it.measuredHeight.div(2) })
            Text(
                modifier = Modifier.alignBy { lineHeightPx.div(2).roundToInt() },
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                fontSize = 22.sp,
                onTextLayout = { layoutResult ->
                    // 获取第一行的高度（像素）
                    if (layoutResult.lineCount > 0) {
                        lineHeightPx = layoutResult.getLineBottom(0) - layoutResult.getLineTop(0)
                    }
                }
            )
        }
    }
}


@Preview(backgroundColor = 0xffffffff, showBackground = true)
@Composable
private fun PrevTermsRowScreen() {
    TermsRowScreen()
}