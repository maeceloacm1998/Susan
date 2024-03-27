package com.app.home.ui.feature.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.service.location.domain.GetLastCurrentLocationUseCase
import com.app.core.service.location.domain.GetLocationUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
class HomeViewModel(
    private val getLocationUseCase: GetLocationUseCase,
    private val getLastCurrentLocationUseCase: GetLastCurrentLocationUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))

    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        observable()

        viewModelState.update {
            it.copy(
                hospitals = listOf(
                    LatLng(1.35, 17.87),
                    LatLng(1.32, 17.80)
                )
            )
        }

    }

    private fun observable() {
        viewModelScope.launch {
            getLastCurrentLocationUseCase().collect { currentLocation ->
                if (currentLocation != null && currentLocation.latitude != 0.0) {
                    onUpdateCurrentLocation(currentLocation)
                }
            }
        }
    }

    private fun handleGetLocation() {
        viewModelScope.launch {
            getLocationUseCase().collect { currentLocation ->
                onUpdateCurrentLocation(currentLocation)
            }
        }
    }

    private fun onUpdateCurrentLocation(location: LatLng?) {
        viewModelState.update { it.copy(currentLocation = location, isLoading = false) }
    }
}