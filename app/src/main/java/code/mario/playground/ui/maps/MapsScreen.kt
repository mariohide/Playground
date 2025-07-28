package code.mario.playground.ui.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import code.mario.playground.R
import code.mario.playground.ui.SwapFilledTonalButton
import code.mario.playground.ui.navigation.Route
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.DefaultMapProperties
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@Composable
fun MapsScreen(onNavigation:(Route)-> Unit) {
    Box(Modifier.fillMaxSize()){
        val singapore = LatLng(1.35, 103.87)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            googleMapOptionsFactory = { GoogleMapOptions().mapId("DEMO_MAP_ID") },
            cameraPositionState = cameraPositionState,
        ){
            MarkerComposable(state = rememberUpdatedMarkerState(position = singapore)) {
                Image(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = null)
            }
        }
        Box(Modifier.fillMaxSize().systemBarsPadding().safeDrawingPadding()){
            SwapFilledTonalButton(text = "Chips") {onNavigation(Route.Chips) }
        }
    }
}