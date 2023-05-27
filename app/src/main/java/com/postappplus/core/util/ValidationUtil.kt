package com.postappplus.core.util

import android.util.Patterns
import com.postappplus.feature_auth.presentation.util.AuthError

object ValidationUtil {

    fun validatePassword(password: String): AuthError? {
        if (password.isBlank()) {
            return AuthError.FieldEmpty

        }
        if (password.length < 6) {
            return AuthError.InputTooShort

        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if(!containsLettersAndDigits) {
            return AuthError.InvalidPassword

        }
        return null

    }
    fun validateRepeatedPassword(password: String, repeatedPassword: String): AuthError?{
        if (password.isBlank()){
            return AuthError.FieldEmpty

        }
        if(password != repeatedPassword) {
            return AuthError.InvalidPassword

        }
        return null
    }
    fun validateUsername(username: String): AuthError? {
        if (username.isBlank()){
            return AuthError.FieldEmpty

        }
        if (username.length < 4){
            return AuthError.InputTooShort

        }

        return null

    }
    fun validateEmail(email: String): AuthError? {
        val trimmedEmail = email.trim()
        if (trimmedEmail.isBlank()) {
            return AuthError.FieldEmpty
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return AuthError.InvalidEmail

        }
        return null


    }


}


