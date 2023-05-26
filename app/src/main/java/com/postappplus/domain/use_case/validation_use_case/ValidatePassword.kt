package com.postappplus.domain.use_case.validation_use_case

import com.postappplus.domain.contracts.PasswordValidator

class ValidatePassword: PasswordValidator {
    override fun validate(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль не может быть пустым"
            )
        }
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль должен содержать не менее 6 символов"
            )
        }
        val containsLetterAndDigit = password.any { it.isLetter() || it.isDigit() }
        if (!containsLetterAndDigit){
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль должен содержать хотя бы одну букву и цифру"
            )
        }
        return ValidationResult(
            successful = true,

        )

    }
}