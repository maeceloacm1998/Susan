package com.app.home.ui.feature.home.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.core.service.location.domain.GetLastCurrentLocationUseCase
import com.app.core.service.location.domain.GetLocationUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class HomeRepositoryImpl(
    private val getLocationUseCase: GetLocationUseCase,
    private val getLastCurrentLocationUseCase: GetLastCurrentLocationUseCase
) : HomeRepository {
    private val showOnboarding: MutableStateFlow<Boolean> = MutableStateFlow(true)

    override suspend fun handleNearbyHospitals() {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun handleCurrentLocation(): LatLng? {
        return getLocationUseCase().first()
    }

    override suspend fun observeCurrentLocation(): Flow<LatLng?> = getLastCurrentLocationUseCase()
    override suspend fun observeShowOnboarding(): Flow<Boolean> = showOnboarding
}