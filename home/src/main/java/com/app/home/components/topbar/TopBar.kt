package com.app.home.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.core.ui.theme.CustomDimensions
import com.app.core.ui.theme.Primary
import com.app.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarCustom(
    modifier: Modifier = Modifier,
    onCreateNewChatListener: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(end = CustomDimensions.padding30),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val image = painterResource(id = R.drawable.ic_susan_white)

                Image(
                    painter = image,
                    contentDescription = "Logo Susan",
                    modifier = Modifier
                        .size(
                            height = CustomDimensions.padding30,
                            width = CustomDimensions.padding80
                        ),
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Primary,
            navigationIconContentColor = Primary
        ),
        navigationIcon = {
            IconButton(onClick = { onCreateNewChatListener() }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Criar nova conversa",
                    tint = Color.White
                )
            }
        },
    )
}

@Preview
@Composable
fun ToolbarPreview() {
    ToolbarCustom(
        onCreateNewChatListener = {}
    )
}

@Preview
@Composable
fun ToolbarNavigationPreview() {
    ToolbarCustom(
        onCreateNewChatListener = {}
    )
}