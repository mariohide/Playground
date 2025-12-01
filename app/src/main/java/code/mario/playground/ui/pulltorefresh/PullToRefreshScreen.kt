package code.mario.playground.ui.pulltorefresh

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshScreen(viewModel: PullToRefreshViewModel = viewModel<PullToRefreshViewModel>()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        isRefreshing = state.isRefreshing,
        onRefresh = viewModel::refresh
    ) {

    }
}