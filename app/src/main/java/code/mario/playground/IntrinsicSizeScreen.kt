package code.mario.playground

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IntrinsicSizeScreen() {
    val density = LocalDensity.current
    var count by remember { mutableIntStateOf(1) }
    var columnHeight by remember { mutableStateOf(0.dp) }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState())
    ) {
        Box(Modifier.systemBarsPadding().padding(16.dp).fillMaxWidth().clip(RoundedCornerShape(22.dp))) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(columnHeight),
                alignment = Alignment.TopCenter,
                painter = painterResource(R.drawable.welcome_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .onSizeChanged {
                        columnHeight = with(density) { it.height.toDp() }
                    }) {
                repeat(count) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                }
            }
        }
        FilledTonalButton(onClick = { count++ }) {
            Text(text = "Increase")
        }

        FilledTonalButton(onClick = { count-- }) {
            Text(text = "Decrease")
        }
    }
}

@Preview
@Composable
private fun PrevIntrinsicSizeScreen() {
    IntrinsicSizeScreen()
}