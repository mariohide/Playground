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

@Composable
fun MainScreen(
    onNavToButtons: () -> Unit,
    onNavToStatus: () -> Unit,
    doDarkTheme: () -> Unit,
    onNavToIntrinsicSize: () -> Unit,
    onNavToSystemPadding: () -> Unit,
    onNavToI18n: () -> Unit,
    onNavToMap: () -> Unit,
    onNavToTerms:()-> Unit,
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
            SwapFilledTonalButton(text = "Buttons", onClick = { onNavToButtons() })
            SwapFilledTonalButton(text = "Status", onClick = { onNavToStatus() })
            SwapFilledTonalButton(text = "IntrinsicSize") { onNavToIntrinsicSize() }
            SwapFilledTonalButton(text = "SystemPadding") { onNavToSystemPadding() }
            SwapFilledTonalButton(text = "I18n") { onNavToI18n() }
            SwapFilledTonalButton(text = "Map") { onNavToMap() }
            SwapFilledTonalButton(text = "Terms") { onNavToTerms() }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            SwapFilledTonalButton(text = "dark") {
                doDarkTheme()
            }
        }
    }
}