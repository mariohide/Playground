package code.mario.playground.ui.tabrow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.times

val colors = listOf(Color.White, Color.Black, Color.Blue)

@Composable
fun TabRowRoute(viewModel: TabRowViewModel = viewModel()) {
    TabRowScreen(state = viewModel.state.value, action = viewModel.action)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabRowScreen(state: TabRowViewModel.UiState, action: (TabRowViewModel.UiAction) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        TabbedPager()
//        WtfTabs(state, action)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WtfTabs(state: TabRowViewModel.UiState, action: (TabRowViewModel.UiAction) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(initialPage = state.selectedTab) { state.tabs.size }

        LaunchedEffect(state.selectedTab) {
            if (pagerState.currentPage != state.selectedTab) {
                pagerState.animateScrollToPage(state.selectedTab)

            }
        }

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }
                .distinctUntilChanged()
                .collect { page ->
                    action(TabRowViewModel.UiAction.OnTabChange(page))
                }
        }

        PrimaryScrollableTabRow(
            selectedTabIndex = state.selectedTab,
            indicator = {

            }
        ) {
            state.tabs.forEachIndexed { index, tab ->
                Tab(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(56.dp),
                    selected = state.selectedTab == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } }
                ) {
                    Text(text = tab.name)
                }
            }
        }

        HorizontalPager(state = pagerState) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color = colors[it % colors.size])
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FancyIndicatorTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")

    Column {
        SecondaryTabRow(
            selectedTabIndex = state,
            indicator = {
                FancyIndicator(
                    MaterialTheme.colorScheme.primary,
                    Modifier.tabIndicatorOffset(state)
                )
            }
        ) {
            titles.forEachIndexed { index, title ->
                Tab(selected = state == index, onClick = { state = index }, text = { Text(title) })
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Fancy indicator tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun FancyIndicator(color: Color, modifier: Modifier = Modifier) {
    // Draws a rounded rectangular with border around the Tab, with a 5.dp padding from the edges
    // Color is passed in as a parameter [color]
    Box(
        modifier
            .padding(5.dp)
            .fillMaxSize()
            .border(BorderStroke(2.dp, color), RoundedCornerShape(5.dp))
    )
}

@Composable
fun TabbedPager() {

    val tabs = listOf(
        "Videos",
        "Shorts",
        "Podcasts & RSS & Mail List",
        "Courses",
        "Playlists",
        "Community"
    )

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabs.size }
    )
    val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }
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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ScrollableTabRow(
                divider = {},
                selectedTabIndex = selectedTabIndex,
                edgePadding = 0.dp,
                indicator = { tabPositions ->
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier
                            .smoothTabIndicatorOffset(
                                previousTabPosition = tabPositions[previousTabIndex],
                                newTabPosition = tabPositions[targetTabIndex],
                                swipeProgress = pagerState.currentPageOffsetFraction
                            ),
                        width = 8.dp
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    )
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
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