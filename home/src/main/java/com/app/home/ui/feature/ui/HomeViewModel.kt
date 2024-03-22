package com.app.home.ui.feature.ui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.service.location.GetLocationUseCase
import com.app.core.service.location.model.PermissionEvent
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
class HomeViewModel(
    private val context: Context,
    private val getLocationUseCase: GetLocationUseCase
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
        viewModelState.update {
            it.copy(
                hospitals = listOf(
                    LatLng(1.35, 17.87),
                    LatLng(1.32, 17.80)
                )
            )
        }
    }


    @RequiresApi(Build.VERSION_CODES.S)
    fun handle(event: PermissionEvent) {
        when (event) {
            PermissionEvent.Granted -> {
                viewModelScope.launch {
                    getLocationUseCase().collect { location ->
                        onUpdateCurrentLocation(location)
                    }
                }
            }

            PermissionEvent.Revoked -> {
                onUpdateRevokedPermission(true)
            }
        }
    }
    private fun onUpdateRevokedPermission(state: Boolean) {
        viewModelState.update { it.copy(isRevokedPermissions = state) }
    }
    private fun onUpdateCurrentLocation(location: LatLng?) {
        viewModelState.update { it.copy(currentLocation = location, isLoading = false) }
    }
}