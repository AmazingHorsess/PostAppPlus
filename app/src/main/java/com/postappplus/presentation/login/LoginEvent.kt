package com.postappplus.presentation.login

sealed class LoginEvent{
    data class UserChanged(val user: String): LoginEvent()
    data class UserPasswordChanged(val password: String): LoginEvent()
    data class ShowPassword(val isShowed: Boolean): LoginEvent()

    object Sumbit: LoginEvent()


}
