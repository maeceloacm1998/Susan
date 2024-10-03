package com.app.home.feature.chat.domain

import com.app.home.feature.chat.data.ChatRepository
import com.app.home.feature.home.data.HomeRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class ObserveChatCurrentLocationUseCase(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(): Flow<LatLng?> = chatRepository.observeCurrentLocation()
}