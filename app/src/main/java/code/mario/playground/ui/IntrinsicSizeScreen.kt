package code.mario.playground.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import code.mario.playground.R

@Composable
fun IntrinsicSizeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.width(intrinsicSize = IntrinsicSize.Min)) {
            Image(
                modifier = Modifier.width(100.dp),
                painter = painterResource(R.drawable.pic_bg),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Box(Modifier.fillMaxWidth().heightIn(24.dp)){
                BasicText(
                    modifier = Modifier.matchParentSize(),
                    text = "12345678900090909090ffffffff9009990",
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = .1.sp,
                        maxFontSize = 72.sp,
                        stepSize = 1.sp
                    ),
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
private fun PrevIntrinsicSizeScreen() {
    IntrinsicSizeScreen()
}