package com.postappplus.domain.use_case.validation_use_case

import com.postappplus.domain.contracts.UsernameValidator

class ValidateUsername: UsernameValidator {
    override fun validate(username: String):ValidationResult{
        if (username.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "Имя пользователя не может быть пустым"
            )
        }
        if (username.length < 4){
            return ValidationResult(
                successful = false,
                errorMessage = "Имя пользователя должно содержать не менее 4 символов"
            )
        }
        //TODO: Check username = username return false
        return ValidationResult(
            successful = true
        )

    }

}