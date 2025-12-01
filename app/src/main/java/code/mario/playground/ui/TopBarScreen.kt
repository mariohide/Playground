package code.mario.playground.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import code.mario.playground.ui.widgets.TopBar
import com.example.ui.theme.AppTypography

@Composable
fun TopBarScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(
            title = "good title",
            onBack = {},
            modifier = Modifier
                .shadow(4.dp)
                .zIndex(1f)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .heightIn(100.dp)
                .background(Color.White)
        ) {
            CenterCheckboxOnFirstLine(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eu justo sit amet turpis efficitur scelerisque quis ac mi.",
                true
            ){}
//            RoundedCornerCheckbox(
//                label = AnnotatedString("label this is a very long label you can see super long, look look good buy you a phone or tv in your bedroom"),
//                isChecked = true
//            ) { }
        }

    }
}

@Preview
@Composable
private fun PrevTopBar() {
    TopBarScreen()
}

@Composable
fun RoundedCornerCheckbox(
    label: AnnotatedString,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    size: Dp = 16.dp,
    checkedColor: Color = MaterialTheme.colorScheme.primary,
    uncheckedColor: Color = Color.White,
    onValueChange: (Boolean) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val checkboxColor: Color by animateColorAsState(
        if (isChecked) checkedColor else uncheckedColor,
        label = ""
    )
    val density = LocalDensity.current
    val duration = 200

    Row(
        modifier = modifier
            .heightIn(48.dp) // height of 48dp to comply with minimum touch target size
            .toggleable(
                interactionSource = interactionSource,
                indication = null,
                value = isChecked,
                role = Role.Checkbox,
                onValueChange = onValueChange
            )
    ) {
        Box(
            modifier = Modifier
                .alignBy(FirstBaseline)
                .size(size)
                .background(color = checkboxColor, shape = CircleShape)
                .border(width = 1.5.dp, color = checkedColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size.toPx() * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
//                Icon(
//                    Icons.Default.Check,
//                    contentDescription = null,
//                    tint = uncheckedColor
//                )
            }
        }
        Spacer(Modifier.width(4.dp))
        Text(
            modifier = Modifier.alignBy(FirstBaseline),
            text = label,
            style = AppTypography.titleLarge.copy(fontSize = 72.sp, lineHeight = 1.2.em)
        )
    }
}

@Composable
fun CheckboxAlignedWithFirstLine(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row {
        // 利用一个透明 Text 创建 baseline 对齐参考
        Box(
            modifier = Modifier.alignBy(FirstBaseline)
        ) {
            // 这个透明的 Text 提供 baseline 给 Checkbox 对齐
            Text(
                text = "A", // 任意单个字符
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Transparent
            )

            // 实际的 Checkbox
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = Modifier
                    .align(Alignment.CenterStart) // 与透明 Text 左上对齐
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.alignBy(FirstBaseline)
        )
    }
}

@Composable
fun CenterCheckboxOnFirstLine(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val density = LocalDensity.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.alignBy { it.measuredHeight/2 }   // 让中心当作 alignment line
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = text,
            // alignByBaseline() == alignBy(FirstBaseline)
            modifier = Modifier.alignBy{ with(density){36.sp.roundToPx()} },
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 72.sp, lineHeight = 1.2.em)
        )
    }
}

