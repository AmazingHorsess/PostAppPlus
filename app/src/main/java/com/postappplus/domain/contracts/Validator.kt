package com.postappplus.domain.contracts

import com.postappplus.domain.use_case.validation_use_case.ValidationResult

interface EmailValidator {
    fun validate(email: String): ValidationResult
}

interface PasswordValidator {
    fun validate(password: String): ValidationResult
}

interface RepeatedPasswordValidator {
    fun validate(password: String, repeatedPassword: String): ValidationResult
}

interface UsernameValidator {
    fun validate(username: String): ValidationResult
}