package code.mario.playground.ui.maps.amap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.amap.api.maps.AMapOptions
import com.amap.api.maps.MapView

@Composable
fun AMap(modifier: Modifier = Modifier, content: (@Composable () -> Unit)? = null) {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context, AMapOptions())
    }
    AndroidView(
        modifier = modifier,
        factory = { mapView },
        onRelease = {
            it.onDestroy()
            it.removeAllViews()
        }
    )
    AMapViewLifecycle(mapView)
}