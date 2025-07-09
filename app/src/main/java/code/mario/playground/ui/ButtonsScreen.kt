package code.mario.playground.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import code.mario.playground.ui.theme.PlaygroundTheme

@Composable
fun ButtonsScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        SwapFilledTonalButton(text = "FilledTonalButton") {}
    }
}

@Composable
fun SwapFilledTonalButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = ButtonDefaults.filledTonalShape,
    onClick: () -> Unit
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 2.dp)
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun PrevButtons() {
    PlaygroundTheme(darkTheme = false) {
        ButtonsScreen()
    }
}