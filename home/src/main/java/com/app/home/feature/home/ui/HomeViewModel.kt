package com.app.home.feature.home.ui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.service.phonedialog.OpenPhoneDialogUseCase
import com.app.home.feature.home.domain.GetHomeCurrentLocationUseCase
import com.app.home.feature.home.domain.ObserveHomeCurrentLocationUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.S)
class HomeViewModel(
    private val getHomeCurrentLocationUseCase: GetHomeCurrentLocationUseCase,
    private val openPhoneDialogUseCase: OpenPhoneDialogUseCase,
    private val observeHomeCurrentLocationUseCase: ObserveHomeCurrentLocationUseCase,
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

    fun onInfoWindowClick(marker: LatLng) {
        // TODO: click on info window
    }

    fun onClickEmergencyPhone(context: Context) {
        val phone = "192"
        openPhoneDialogUseCase(context, phone)
    }

    fun onClickSearchEmergency() {
        // TODO: abrir fluxo de buscar emergencia
    }

    private fun observable() {
        viewModelScope.launch {
            getHomeCurrentLocationUseCase()
            observeHomeCurrentLocationUseCase().collect { currentLocation ->
                if (currentLocation != null) {
                    onUpdateCurrentLocation(currentLocation)
                } else {
                    handleGetLocation()
                }
            }
        }
    }

    private fun handleGetLocation() {
        viewModelScope.launch {
            val currentLocation = getHomeCurrentLocationUseCase()
            onUpdateCurrentLocation(currentLocation)
        }
    }

    private fun onUpdateCurrentLocation(location: LatLng?) {
        viewModelState.update {
            it.copy(
                currentLocation = location,
                isLoading = false,
            )
        }
    }
}