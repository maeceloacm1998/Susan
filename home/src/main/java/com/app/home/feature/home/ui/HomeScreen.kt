package com.app.home.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.components.screenerror.ScreenError
import com.app.core.components.screenloading.LoadingContent
import com.app.core.ui.theme.Background
import com.app.home.components.makerinfocontainer.MakerInfoContainer
import com.app.home.components.searchfooter.FooterComponent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    cameraState: CameraPositionState,
    onInfoWindowClick: (Marker) -> Unit
) {
    LoadingContent(
        empty = when (uiState) {
            is HomeUiState.HasHospital -> {
                check(uiState is HomeUiState.HasHospital)
                checkNotNull(uiState.hospitals?.isEmpty())
            }
            is HomeUiState.NoHospital -> false
        },
        emptyContent = { ScreenError() },
        content = {
            LaunchedEffect(key1 = uiState.currentLocation, key2 = uiState.isLoading) {
                uiState.currentLocation?.let { cameraState.centerOnLocation(it) }
            }

            check(uiState is HomeUiState.HasHospital)
            HomeContainer(
                uiState = uiState,
                cameraState = cameraState,
                onInfoWindowClick = onInfoWindowClick
            )
        }
    )

}

@Composable
fun HomeContainer(
    uiState: HomeUiState.HasHospital,
    cameraState: CameraPositionState,
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
            onInfoWindowClick = onInfoWindowClick,
            cameraState = cameraState
        )
        FooterComponent()
    }
}

@Composable
fun MapsContainer(
    modifier: Modifier = Modifier,
    hospitals: List<LatLng>,
    cameraState: CameraPositionState,
    onInfoWindowClick: (Marker) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraState,
            properties = MapProperties(
                isMyLocationEnabled = true,
                mapType = MapType.NORMAL,
                isTrafficEnabled = false
            )
        ) {
            hospitals.forEach { position ->
                MarkerInfoWindowContent(
                    state = MarkerState(position = position),
                    title = "Hyde Park",
                    snippet = "Marker in Hyde Park",
                    draggable = true,
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
        ),
        showOnboarding = false,
        currentLocation = null,
        isLocationActive = false
    )
    val cameraState = rememberCameraPositionState()

    HomeScreen(uiState = uiState,
        cameraState = cameraState, onInfoWindowClick = {})
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

suspend fun CameraPositionState.centerOnLocation(
    location: LatLng
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        location,
        15f
    ),
    durationMs = 3000
)