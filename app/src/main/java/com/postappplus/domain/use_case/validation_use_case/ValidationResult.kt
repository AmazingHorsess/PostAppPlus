package com.postappplus.domain.use_case.validation_use_case

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)