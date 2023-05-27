package com.postappplus.feature_auth.domain.models

import com.postappplus.core.util.SimpleResource
import com.postappplus.feature_auth.presentation.util.AuthError

data class LoginResult(
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)