package com.postappplus.presentation.login



data class LoginState(

    val usernameText: String = "",
    val passwordText: String = "",
    var isPasswordVisible: Boolean = false,
    val usernameError: Boolean? = null,
    val passwordError: Boolean? = null

)


