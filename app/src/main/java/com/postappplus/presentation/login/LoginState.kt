package com.postappplus.presentation.login



data class LoginState(

    val usernameText: String = "",
    val passwordText: String = "",
    var isPasswordVisible: Boolean = false,
    val usernameError: String = "",
    val passwordError: String = ""

)


