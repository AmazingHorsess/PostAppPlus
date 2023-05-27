package com.postappplus.core.util

import android.content.Context
import androidx.compose.runtime.Composable

@Composable
fun UiText.asString(): String {
    return when (this) {
        is UiText.DynamicString -> this.value

    }
}

fun UiText.asString(context: Context): String {
    return when (this) {
        is UiText.DynamicString -> this.value

    }
}