package com.postappplus.core.presentation.components

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun ToggleButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    onChekedChange: (Boolean) -> Unit,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    secondIcon: @Composable () -> Unit
    ){
    var isSecondIcon by remember { mutableStateOf(false) }
    IconButton(
        modifier = modifier,
        onClick = {
            isSecondIcon = !isSecondIcon
            onClick()
        }
    ) {
        if (isSecondIcon) {
            secondIcon()
        } else {
            icon()
        }


    }

}