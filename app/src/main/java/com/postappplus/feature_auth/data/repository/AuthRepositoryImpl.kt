package com.postappplus.feature_auth.data.repository

import android.content.SharedPreferences
import com.postappplus.core.util.Constants.KEY_JWT_TOKEN
import com.postappplus.core.util.Constants.KEY_USER_ID
import com.postappplus.core.util.Resource
import com.postappplus.core.util.SimpleResource
import com.postappplus.core.util.UiText
import com.postappplus.feature_auth.data.remote.AuthApi
import com.postappplus.feature_auth.data.remote.request.CreateAccountRequest
import com.postappplus.feature_auth.data.remote.request.LoginRequest
import com.postappplus.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences,


) : AuthRepository{

    override suspend fun register(
        email: String,
        username: String,
        password: String,
        repeatedPassword: String,
    ): SimpleResource {
        val request = CreateAccountRequest(email, username, password, repeatedPassword)
        return try {
            val response = api.register(request)
            if (response.successful){
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))

                } ?: Resource.Error(UiText.unknownError())
            }


        } catch (e: IOException) {
            Resource.Error(UiText.DynamicString("Couldnt reach server. Check your internet connection"))
        } catch (e: HttpException) {
            Resource.Error(UiText.DynamicString("Something go wrong"))
        }


    }

    override suspend fun login(email: String, password: String): SimpleResource {
        val request = LoginRequest(email, password)
        return try {
            val response = api.login(request)
            if (response.successful) {
                response.data?.let { authResponse ->
                    println("Overriding token with ${authResponse.token}")
                    sharedPreferences.edit()
                        .putString(KEY_JWT_TOKEN, authResponse.token)
                        .putString(KEY_USER_ID, authResponse.userId)
                        .apply()


                }
                Resource.Success(Unit)

            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.unknownError())

            }
        } catch (e: IOException) {
            Resource.Error(UiText.DynamicString("Couldnt reach server. Check your internet connection"))
        } catch (e: HttpException) {
            Resource.Error(UiText.DynamicString("Something go wrong"))
        }
    }



    override suspend fun authenticate(): SimpleResource {
        return try {
            api.authenticate()
            Resource.Success(Unit)
        } catch(e: IOException) {
            Resource.Error(UiText.DynamicString("Couldnt reach server. Check your internet connection"))
        } catch(e: HttpException) {
            Resource.Error(UiText.DynamicString("Something go wrong"))
        }
    }


}


