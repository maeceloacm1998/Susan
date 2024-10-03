package com.app.home.feature.chat.domain

import com.app.home.feature.chat.data.ChatRepository
import com.app.home.feature.home.data.HomeRepository
import com.app.home.feature.locationpermission.domain.GetLocationActiveUseCase
import com.google.android.gms.maps.model.LatLng

class GetChatCurrentLocationUseCase(
    private val getLocationActiveUseCase: GetLocationActiveUseCase,
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(): LatLng? {
        chatRepository.run {
            if (getLocationActiveUseCase()) {
                return handleCurrentLocation()
            }
        }

        return LatLng(0.0, 0.0)
    }
}