package com.app.home.feature.chat.domain

import android.util.Log
import com.app.home.feature.chat.data.ChatRepository
import com.app.home.feature.chat.data.external.models.EmergencyRequest
import com.app.home.feature.chat.data.external.models.EmergencyResponse
import java.io.IOException

class GetChatEmergencyUseCase(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(message: String): Result<EmergencyResponse> {
        return try {
            val emergencyRequest = EmergencyRequest(
                lat = chatRepository.handleCurrentLocation()?.latitude ?: 0.0,
                lng = chatRepository.handleCurrentLocation()?.longitude ?: 0.0,
                message = message
            )
            chatRepository.searchEmergency(emergencyRequest)
        } catch (e: IOException) {
            Log.e("GetChatEmergencyUseCase", "Network error occurred: ${e.message}", e)
            Result.failure(e)
        } catch (e: Exception) {
            Log.e("GetChatEmergencyUseCase", "Exception occurred: ${e.message}", e)
            Result.failure(e)
        }
    }
}