package com.postappplus.core.util

import androidx.annotation.StringRes

sealed class UiText {
    data class DynamicString(val value: String) : UiText()


    companion object {
        fun unknownError(): UiText {
            return UiText.DynamicString("Unknown error")
        }
    }
}