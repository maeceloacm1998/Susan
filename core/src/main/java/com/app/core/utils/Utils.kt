package com.app.core.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.speech.RecognizerIntent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import java.util.Locale
import kotlin.random.Random

object Utils {
    private const val MILLISECONDS_PER_SECOND = 100
    private const val ONE_MINUTE_IN_SECONDS = 60
    const val ONE_SECOND_IN_MILLISECONDS = 1000L

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

    fun calculateAudioDuration(message: String): Int {
        val durationPerCharacter = MILLISECONDS_PER_SECOND
        val totalMilliseconds = message.length * durationPerCharacter
        return (totalMilliseconds / ONE_SECOND_IN_MILLISECONDS).toInt()
    }

    fun formatSeconds(seconds: Int): String {
        val minutes = seconds / ONE_MINUTE_IN_SECONDS
        val remainingSeconds = seconds % ONE_MINUTE_IN_SECONDS
        return String.format(
            locale = Locale("pt", "BR"),
            format = "%02d:%02d", minutes, remainingSeconds
        )
    }

    fun onCopyTextToClipboard(context: Context, message: String, data: String = "") {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", data)
        clipboard.setPrimaryClip(clip)
    }

    fun onOpenGoogleMaps(context: Context, lat: Double, lng: Double) {
        val uri = Uri.parse("google.navigation:q=$lat,$lng")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun onOpenWaze(context: Context, lat: Double, lng: Double) {
        val uri = Uri.parse("waze://?ll=$lat,$lng&navigate=yes")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.waze")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun onOpenUber(context: Context, lat: Double, lng: Double) {
        val uri = Uri.parse("uber://?action=setPickup&pickup=my_location&dropoff[latitude]=$lat&dropoff[longitude]=$lng")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.ubercab")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}