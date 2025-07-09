package code.mario.playground.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ui.theme.AppTypography

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    actionText: String? = null,
    isDark: Boolean = false,
    backgroundColor: Color = White,
    onAction: () -> Unit = {},
    onBack: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .background(backgroundColor)
            .statusBarsPadding()
            .heightIn(56.dp),
    ) {
        if (onBack != null) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(48.dp),
                onClick = { onBack() }
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Default.ArrowBack),
                    contentDescription = null,
                    tint = if (isDark) White else Color.Black
                )
            }
        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            style = AppTypography.titleMedium.copy(
                color = if (isDark) White else Color.Black,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
        if (actionText != null) {
            TextButton(modifier = Modifier.align(Alignment.CenterEnd), onClick = onAction) {
                Text(text = actionText, style = AppTypography.titleSmall.copy(Color.Black))
            }
        }
    }
}