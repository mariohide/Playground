package code.mario.playground.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 固定标签栏，接收标签、选中标签下标和选中标签的回调
 */
@Composable
fun FixedTabs(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        indicator = {
            Box(modifier = Modifier.tabIndicatorOffset(it[selectedTabIndex])) {
                Box(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .size(width = 16.dp, height = 4.dp)
                        .background(TabRowDefaults.secondaryContentColor, RoundedCornerShape(100))
                )
            }
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp),
                selected = index == selectedTabIndex,
                onClick = { onTabSelected(index) },
                content = {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        fontSize = if (index != selectedTabIndex) 14.sp else 28.sp
                    )
                },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFixedTabs() {
    PlaygroundTheme {
        Surface {
            Column(Modifier.fillMaxSize()) {
                FixedTabs(
                    tabs = listOf("Tab1", "Tab2", "Tab3"),
                    selectedTabIndex = 0,
                    onTabSelected = {}
                )
            }

        }
    }
}