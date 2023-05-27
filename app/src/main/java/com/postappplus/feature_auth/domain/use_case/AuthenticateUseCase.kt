package com.postappplus.feature_auth.domain.use_case

import com.postappplus.core.util.SimpleResource
import com.postappplus.feature_auth.domain.repository.AuthRepository

class AuthenticateUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): SimpleResource = repository.authenticate()
}