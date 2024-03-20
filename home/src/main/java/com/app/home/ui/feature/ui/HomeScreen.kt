package com.app.home.ui.feature.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.core.components.buttons.TextButtonComponent
import com.app.core.ui.theme.Background
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.GrayDark
import com.app.core.ui.theme.Primary
import com.app.home.R
import com.app.home.ui.components.rating.RatingComponent
import com.app.home.ui.components.rating.RatingComponentModel
import com.app.core.components.textfield.TextFieldCustom
import com.app.home.ui.components.makerinfocontainer.MakerInfoContainer
import com.app.home.ui.components.searchfooter.FooterComponent
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen(uiState: HomeUiState.HasHospital) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        MapsContainer(modifier = Modifier.weight(1f))
        FooterComponent()
    }
}

@Composable
fun MapsContainer(modifier: Modifier = Modifier) {
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
            MarkerInfoWindowContent(
                state = MarkerState(position = singapore),
                title = "Hyde Park",
                snippet = "Marker in Hyde Park"
            ) { marker ->
                MakerInfoContainer(marker.title)
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    val uiState = HomeUiState.HasHospital(
        isLoading = false,
        errorMessages = null
    )
    HomeScreen(uiState = uiState)
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