package com.postappplus.feature_auth.domain.repository

import com.postappplus.core.util.SimpleResource

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String): SimpleResource


    suspend fun register(
        email: String,
        username: String,
        password: String,
        repeatedPassword : String ): SimpleResource


    suspend fun authenticate(): SimpleResource
}