package com.postappplus.domain.use_case.validation_use_case

import android.util.Patterns
import com.postappplus.domain.contracts.EmailValidator

class ValidateEmail : EmailValidator {
    override fun validate(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "E-Mail не может быть пустым"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Неверный E-Mail"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}