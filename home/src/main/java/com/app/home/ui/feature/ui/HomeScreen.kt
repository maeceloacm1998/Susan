package com.app.home.ui.feature.ui


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.app.core.ui.theme.Background
import com.app.home.ui.components.makerinfocontainer.MakerInfoContainer
import com.app.home.ui.components.searchfooter.FooterComponent
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen(
    uiState: HomeUiState.HasHospital,
    onInfoWindowClick: (Marker) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        MapsContainer(
            modifier = Modifier.weight(1f),
            hospitals = uiState.hospitals.orEmpty(),
            onInfoWindowClick = onInfoWindowClick
        )
        FooterComponent()
    }
}

@Composable
fun MapsContainer(
    modifier: Modifier = Modifier,
    hospitals: List<LatLng>,
    onInfoWindowClick: (Marker) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        val singapore = LatLng(1.35, 17.87)
        val cameraState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraState,
            properties = MapProperties(
                isMyLocationEnabled = true,
                mapType = MapType.NORMAL,
                isTrafficEnabled = true
            )
        ) {
            hospitals.forEach { position ->
                MarkerInfoWindowContent(
                    state = MarkerState(position = position),
                    title = "Hyde Park",
                    snippet = "Marker in Hyde Park",
                    onInfoWindowClick = onInfoWindowClick
                ) { marker ->
                    MakerInfoContainer(marker.title)
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    val uiState = HomeUiState.HasHospital(
        isLoading = false,
        errorMessages = null,
        hospitals = listOf(
            LatLng(1.35, 17.87),
            LatLng(1.32, 17.80)
        )
    )
    HomeScreen(uiState = uiState, onInfoWindowClick = {})
}

@Preview
@Composable
fun FooterComponentPreview() {
    FooterComponent()
}

@Preview
@Composable
fun MakerInfoContainerPreview() {
    val title = "Teste"
    MakerInfoContainer(title)
}

fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}