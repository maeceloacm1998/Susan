package com.app.core.utils

import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import kotlin.random.Random

object Utils {
    fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
    }

    fun generateRandomId(): Int {
        return Random.nextInt()
    }

    fun onLaunchAudioRecording(speech: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR")
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale agora")
        }

        speech.launch(intent)
    }
}