package com.postappplus.domain.use_case.validation_use_case

import com.postappplus.domain.contracts.RepeatedPasswordValidator

class ValidateRepeatedPassword: RepeatedPasswordValidator {

    override fun validate(password: String, repeatedPassword: String): ValidationResult {
        if (password.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль не может быть пустым"
            )
        }
        if(password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "Пароли не совпадают"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}