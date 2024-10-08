package com.app.home.feature.chat.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.core.service.location.domain.GetLastCurrentLocationUseCase
import com.app.core.service.location.domain.GetLocationUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ChatRepositoryImpl(
    private val getLocationUseCase: GetLocationUseCase,
    private val getLastCurrentLocationUseCase: GetLastCurrentLocationUseCase
): ChatRepository {
    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun handleCurrentLocation(): LatLng? {
        return getLocationUseCase().first()
    }

    override suspend fun observeCurrentLocation(): Flow<LatLng?> = getLastCurrentLocationUseCase()
}