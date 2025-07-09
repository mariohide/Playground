package code.mario.playground.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import code.mario.playground.R
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.example.ui.theme.AppTypography

private const val TAG = "I18nScreen"

@Composable
fun I18nScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val context = LocalContext.current
        LaunchedEffect(context) {
            Log.d(TAG, "I18nScreen: context changed, $context")
        }
        Text(
            text = stringResource(R.string.app_name),
            style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        Image(painter = painterResource(R.drawable.pic_i18n), contentDescription = null)
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(R.drawable.pic_i18n)
                .memoryCachePolicy(CachePolicy.DISABLED)
                .diskCachePolicy(CachePolicy.DISABLED)
                .build(),
            contentDescription = null
        )
    }
}