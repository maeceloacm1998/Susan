package com.app.home.ui.feature.home.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.app.core.service.location.domain.GetLastCurrentLocationUseCase
import com.app.core.service.location.domain.GetLocationUseCase
import com.app.core.service.location.utils.LocationUtils
import com.app.core.service.sharedpreferences.SharedPreferencesBuilder
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class HomeRepositoryImpl(
    val context: Context,
    private val getLocationUseCase: GetLocationUseCase,
    private val getLastCurrentLocationUseCase: GetLastCurrentLocationUseCase,
    private val sharedPreferences: SharedPreferencesBuilder
) : HomeRepository {
    override suspend fun handleNearbyHospitals() {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun handleCurrentLocation(): LatLng? {
        return getLocationUseCase().first()
    }

    override suspend fun observeCurrentLocation(): Flow<LatLng?> = getLastCurrentLocationUseCase()
    override fun isShowOnboarding(): Boolean {
        return sharedPreferences.getBoolean(ONBOARDING_KEY, true)
    }

    override fun onFinishOnboarding() {
        sharedPreferences.putBoolean(ONBOARDING_KEY, false)
    }

    override fun isLocationActive(): Boolean {
        return LocationUtils.checkLocationPermission(context)
    }

    companion object {
        private const val ONBOARDING_KEY = "onboarding_key"
    }
}