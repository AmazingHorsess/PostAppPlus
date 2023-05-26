package com.postappplus.presentation.register

import androidx.compose.runtime.State

data class RegisterState(
    val usernameText:String = "",
    val passwordText:String = "",
    val repeatPasswordText:String = "",
    val emailText:String = "",
    val isPasswordVisible:Boolean = false,
    val isRepeatPasswordVisible:Boolean = false,
    val emailError:String? = null,
    val usernameError:String? = null,
    val passwordError:String? = null,
    val repeatedPasswordError: String? = null
)
