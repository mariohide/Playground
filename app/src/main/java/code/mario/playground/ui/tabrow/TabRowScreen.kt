package code.mario.playground.ui.tabrow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import code.mario.playground.ui.SwapFilledTonalButton
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun TabRowRoute(viewModel: TabRowViewModel = viewModel()) {
    TabRowScreen(state = viewModel.state.value, action = viewModel.action)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabRowScreen(state: TabRowViewModel.UiState, action: (TabRowViewModel.UiAction) -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        TabbedPager(state.tabs, state.selectedTab, action)
        SwapFilledTonalButton(text = "to 3", modifier = Modifier.align(Alignment.BottomEnd)) {
            action(TabRowViewModel.UiAction.OnTabChange(3))
        }
    }
}

@Composable
fun TabbedPager(
    tabs: List<TabRowViewModel.Tab>,
    selectedTabIndex: Int,
    action: (TabRowViewModel.UiAction.OnTabChange) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabs.size }
    )

    var previousTabIndex by remember { mutableIntStateOf(0) }
    var targetTabIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(pagerState.currentPageOffsetFraction) {
        val scrollFraction = pagerState.currentPageOffsetFraction
        if (scrollFraction > 0) {
            previousTabIndex = pagerState.currentPage
            targetTabIndex = previousTabIndex + 1
        }
        if (scrollFraction < 0) {
            previousTabIndex = pagerState.currentPage
            targetTabIndex = previousTabIndex - 1
        }
    }

    LaunchedEffect(pagerState.settledPage) {
        action(TabRowViewModel.UiAction.OnTabChange(pagerState.settledPage))
    }

    LaunchedEffect(selectedTabIndex) {
        if (!pagerState.isScrollInProgress && selectedTabIndex != pagerState.settledPage) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
    }

    Column(Modifier.fillMaxSize()) {
        ScrollableTabRow(
            divider = {},
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .smoothTabIndicatorOffset(
                            previousTabPosition = tabPositions[previousTabIndex],
                            newTabPosition = tabPositions[targetTabIndex],
                            swipeProgress = pagerState.currentPageOffsetFraction
                        )
                ) {
                    Box(
                        Modifier
                            .align(Alignment.BottomCenter)
                            .width(16.dp)
                            .height(2.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title.name) },
                    selected = selectedTabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { pageIndex ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Page $pageIndex")
            }
        }
    }
}

fun Modifier.smoothTabIndicatorOffset(
    previousTabPosition: TabPosition,
    newTabPosition: TabPosition,
    swipeProgress: Float
): Modifier {
    val currentTabWidth =
        previousTabPosition.width + (newTabPosition.width - previousTabPosition.width) * abs(
            swipeProgress
        )
    val indicatorOffset =
        previousTabPosition.left + (newTabPosition.left - previousTabPosition.left) * abs(
            swipeProgress
        )
    return fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset { IntOffset(x = indicatorOffset.roundToPx(), y = 0) }
        .width(currentTabWidth)
}