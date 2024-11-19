package com.app.home.feature.chat.ui

import android.Manifest
import android.content.pm.PackageManager
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.core.components.screenloading.ScreenLoading
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
    val context = LocalContext.current
    val uiState by chatViewModel.uiState.collectAsStateWithLifecycle()
    val timerState by chatViewModel.timerFlow.collectAsStateWithLifecycle()

    RequestAudioPermission(onPermissionGranted = {}, onPermissionDenied = {})

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val matches = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

            if (matches != null) {
                chatViewModel.onCreateRecordingMessage(
                    textAudio = matches[0],
                    timer = timerState
                )
            }
        }

        chatViewModel.onStopRecordingAudio()
    }

    LaunchedEffect(Unit) {
        chatViewModel.onUpdateMessage()
    }

    ChatRoute(
        uiState = uiState,
        timer = timerState,
        onCreateNewChatListener = { chatViewModel.onCreateNewChat(context) },
        onPressStartAudio = {
            chatViewModel.onStartRecordingAudio(speechRecognizerLauncher)
        },
        onPressSendMessage = {
            chatViewModel.onCreateSendMessage()
        },
        onChangeUserMessage = { message ->
            chatViewModel.onChangeUserMessageState(message)
        }
    )
}

@Composable
fun ChatRoute(
    uiState: ChatUiState,
    timer: Int,
    onCreateNewChatListener: () -> Unit,
    onPressStartAudio: () -> Unit,
    onPressSendMessage: (message: String) -> Unit,
    onChangeUserMessage: (message: String) -> Unit
) {
    when {
        uiState.isLoading -> ScreenLoading()
        else -> ChatScreen(
            uiState = uiState,
            timer = timer,
            onCreateNewChatListener = onCreateNewChatListener,
            onPressStartAudio = onPressStartAudio,
            onPressSendMessage = onPressSendMessage,
            onChangeUserMessage = onChangeUserMessage
        )
    }
}