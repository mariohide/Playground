package code.mario.playground.ui.maps.amap

import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.amap.api.maps.MapView

@Composable
fun AMapViewLifecycle(mapView: MapView) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(context, lifecycle, mapView) {
        val mapLifecycleObserver = mapView.lifecycleObserver()
        val callbacks = mapView.componentCallbacks()
        lifecycle.addObserver(mapLifecycleObserver)
        context.registerComponentCallbacks(callbacks)
        onDispose {
            lifecycle.removeObserver(mapLifecycleObserver)
            context.unregisterComponentCallbacks(callbacks)
        }
    }
}

private fun MapView.lifecycleObserver(
    onCreate: (() -> Unit)? = null,
    onResume: (() -> Unit)? = null,
    onPause: (() -> Unit)? = null,
    onDestroy: (() -> Unit)? = null,
): LifecycleObserver {
    return LifecycleEventObserver { _, event ->
        when(event) {
            Lifecycle.Event.ON_CREATE -> {
                this.onCreate(Bundle())
                onCreate?.invoke()
            }
            Lifecycle.Event.ON_RESUME -> {
                this.onResume()
                onResume?.invoke()
            }
            Lifecycle.Event.ON_PAUSE -> {
                this.onPause()
                onPause?.invoke()
            }
            Lifecycle.Event.ON_DESTROY -> {
                this.map.setOnCameraChangeListener(null)
                this.onDestroy()
                onDestroy?.invoke()
            }
            else -> {}
        }
    }
}
private fun MapView.componentCallbacks(
    onLowMemory: (() -> Unit)? = null,
): ComponentCallbacks {
    return object : ComponentCallbacks {
        override fun onConfigurationChanged(newConfig: Configuration) {}
        override fun onLowMemory() {
            this@componentCallbacks.onLowMemory()
            onLowMemory?.invoke()
        }
    }
}