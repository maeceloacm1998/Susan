package com.app.home.components.expandablebutton

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.app.core.ui.theme.Secondary

@Composable
fun ExpandableButton(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.Mic,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val size by animateDpAsState(if (isPressed) 58.dp else 50.dp)

    Box(
        modifier = modifier
            .size(size)
            .background(Secondary, CircleShape)
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    isPressed = true
                    tryAwaitRelease()
                    isPressed = false
                    onClick()
                })
            }, contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Expandable Button",
            tint = Color.White
        )
    }
}