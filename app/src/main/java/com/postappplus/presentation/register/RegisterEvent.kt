package com.postappplus.presentation.register

sealed class RegisterEvent{
    data class EnteredUsername(val value: String) : RegisterEvent()
    data class EnteredPassword(val value: String) : RegisterEvent()
    data class EnteredRepeatPassword(val value: String) : RegisterEvent()

    data class EnteredEmail(val value: String) : RegisterEvent()
    object ToggleRepeatPasswordVisibility : RegisterEvent()
    object TogglePasswordVisibility : RegisterEvent()
    object Sumbit : RegisterEvent()

}
