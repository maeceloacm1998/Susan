package com.app.home.components.textfieldchat

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldChat(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    onPressDoneListener: () -> Unit,
    minHeight: Dp = 56.dp,
    maxHeight: Dp = 130.dp,
    minWidth: Dp = 0.dp,
    maxWidth: Dp = 280.dp
) {
    var isFocused by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            if (!isFocused && value.isEmpty()) {
                Text(
                    text = label,
                    color = Primary,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        },
        modifier = modifier
            .heightIn(min = minHeight, max = maxHeight)
            .widthIn(min = minWidth, max = maxWidth)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        shape = RoundedCornerShape(CustomDimensions.padding40),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { onPressDoneListener() }),
        maxLines = Int.MAX_VALUE // Allow multi-line input
    )
}

@Preview
@Composable
fun TextFieldChatPreview() {
    TextFieldChat(
        value = "",
        onValueChange = {},
        label = "Type a message",
        onPressDoneListener = {}
    )
}