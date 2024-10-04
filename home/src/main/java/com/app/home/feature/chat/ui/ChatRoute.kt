package com.app.home.feature.chat.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.core.components.screenloading.ScreenLoading
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RequestAudioPermission(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }

    coroutineScope.launch {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) -> {
                onPermissionGranted()
            }

            else -> {
                launcher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }
    }
}

@Composable
fun ChatRoute(
    chatViewModel: ChatViewModel = koinViewModel()
) {
    val uiState by chatViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var timer by remember { mutableStateOf(0) }

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val matches = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            chatViewModel.onCreateMessage(
                context = context,
                text = matches?.get(0) ?: "",
                timer = timer
            )
        }
        chatViewModel.onStopRecordingAudio()
        timer = 0
    }

    RequestAudioPermission(
        onPermissionGranted = { },
        onPermissionDenied = { /* Handle permission denied */ }
    )

    ChatRoute(
        uiState = uiState,
        onCreateNewChatListener = { },
        onPressStartAudio = {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR")
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale agora")
            }
            chatViewModel.onStartRecordingAudio()
            speechRecognizerLauncher.launch(intent)
        },
        onTimerChange = { timer = it },
        timer = timer
    )
}

@Composable
fun ChatRoute(
    uiState: ChatUiState,
    onCreateNewChatListener: () -> Unit,
    onPressStartAudio: () -> Unit,
    onTimerChange: (Int) -> Unit,
    timer: Int
) {
    when {
        uiState.isLoading -> ScreenLoading()
        else -> ChatScreen(
            uiState = uiState,
            onCreateNewChatListener = onCreateNewChatListener,
            onPressStartAudio = onPressStartAudio,
            onTimerChange = onTimerChange,
            timer = timer
        )
    }
}