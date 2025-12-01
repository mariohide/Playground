package code.mario.playground.ui.tabrow

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TabRowViewModel : ViewModel() {
    private val _state = mutableStateOf(UiState())
    val state: State<UiState> = _state

    val action: (UiAction) -> Unit = {
        when (it) {
            is UiAction.OnTabChange -> _state.value = state.value.copy(selectedTab = it.index)
        }
    }

    data class UiState(
        val selectedTab: Int = 1,
        val tabs: List<Tab> = Tab.entries
    )

    sealed class UiAction {
        data class OnTabChange(val index: Int) : UiAction()
    }

    enum class Tab {
        FOOD, PEOPLE, NATURE, UNIVERSE, DESIGN
    }
}