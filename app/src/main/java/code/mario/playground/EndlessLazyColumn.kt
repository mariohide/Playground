package code.mario.playground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> RefreshEndlessLazyColumn(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    space: Dp = 12.dp,
    header: (@Composable () -> Unit)? = null,
    loading: Boolean = false,
    refreshing: Boolean = false,
    listState: LazyListState = rememberLazyListState(),
    items: List<T>,
    itemKey: (T) -> Any,
    itemContent: @Composable (T) -> Unit,
    loadMore: () -> Unit,
    refresh: (() -> Unit)? = null,
    canLoadMore: Boolean,
    emptyView: (@Composable () -> Unit)? = { }
) {

    val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }

    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !loading) loadMore()
    }

    PullToRefreshBox(
        modifier = modifier,
        isRefreshing = refreshing,
        onRefresh = { refresh?.invoke() }) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(space),
            contentPadding = contentPadding
        ) {
            if (header != null) {
                item { header() }
            }

            itemsIndexed(items, { _, item -> itemKey(item) }) { index, item ->
                itemContent(item)
//                if (index == items.size - 1) {
//                    if (canLoadMore && !loading && items.isNotEmpty()) loadMore()
//                }
            }
            if (header != null && items.isEmpty()) {
                item {
                    emptyView?.invoke()
                }
            }

            if (loading) {
                item {
                    LoadMoreItem()
                }
            }

            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
        if (header == null && items.isEmpty()) {
            emptyView?.invoke()
        }
    }
}

@Composable
fun <T> EndlessLazyColumn(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    listState: LazyListState = rememberLazyListState(),
    header: (@Composable () -> Unit)? = null,
    items: List<T>,
    itemKey: (T) -> Any,
    itemContent: @Composable (T) -> Unit,
    space: Dp = 12.dp,
    loadMore: () -> Unit
) {
    val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }

    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !loading) loadMore()
    }
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(space),
    ) {
        if (header != null) {
            item { header() }
        }

        items(
            items = items,
            key = { item: T -> itemKey(item) },
        ) { item ->
            itemContent(item)
        }
        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}

@Composable
fun LoadMoreItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

internal fun LazyListState.reachedBottom(buffer: Int = 2): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull() ?: return false
    val reachBottom =
        lastVisibleItem.index != 0 && lastVisibleItem.index == this.layoutInfo.totalItemsCount - 1 - buffer
    return reachBottom
}