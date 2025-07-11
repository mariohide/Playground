package code.mario.playground.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import code.mario.playground.ui.navigation.Route

@Composable
fun MainScreen(
    onNavigation: (Route) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .padding(16.dp)
        ) {
            SwapFilledTonalButton(text = "Buttons", onClick = { onNavigation(Route.Buttons) })
            SwapFilledTonalButton(text = "Status", onClick = { onNavigation(Route.Status) })
            SwapFilledTonalButton(text = "IntrinsicSize") { onNavigation(Route.IntrinsicSize) }
            SwapFilledTonalButton(text = "SystemPadding") { onNavigation(Route.SystemPadding) }
            SwapFilledTonalButton(text = "I18n") { onNavigation(Route.I18n) }
            SwapFilledTonalButton(text = "Terms") { onNavigation(Route.Terms) }
            SwapFilledTonalButton(text = "TabRow") { onNavigation(Route.TabRow) }
        }
    }
}