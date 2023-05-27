package com.postappplus.feature_auth.domain.use_case

import com.postappplus.core.util.ValidationUtil

import com.postappplus.feature_auth.domain.models.RegisterResult
import com.postappplus.feature_auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository,

) {
    suspend operator fun invoke(
        email: String,
        username: String,
        password: String,
        repeatedPassword: String
    ) : RegisterResult {
        val emailError = ValidationUtil.validateEmail(email)
        val usernameError = ValidationUtil.validateUsername(username)
        val passwordError = ValidationUtil.validatePassword(password)
        val repeatedPasswordError = ValidationUtil.validateRepeatedPassword(password,repeatedPassword)

        if (emailError != null || usernameError != null || passwordError != null || repeatedPasswordError != null) {
            return RegisterResult(
                usernameError = usernameError,
                emailError = emailError,
                passwordError = passwordError,
                repeatedPasswordError = repeatedPasswordError
            )
        }
        val result = repository.register(email.trim(), username.trim(), password.trim(), repeatedPassword.trim())
        return RegisterResult(result = result)

    }
}