package code.mario.playground.ui.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import code.mario.playground.ui.maps.amap.AMap
import com.amap.api.maps.MapsInitializer

@Composable
fun MapScreen() {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        MapsInitializer.updatePrivacyShow(context, true, true)
        MapsInitializer.updatePrivacyAgree(context, true)
    }
    Box(Modifier.fillMaxSize()) {
        AMap(modifier = Modifier.fillMaxSize())
    }
}