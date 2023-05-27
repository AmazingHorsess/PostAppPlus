package com.postappplus.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.postappplus.feature_auth.data.remote.AuthApi
import com.postappplus.feature_auth.data.repository.AuthRepositoryImpl
import com.postappplus.feature_auth.domain.repository.AuthRepository
import com.postappplus.feature_auth.domain.use_case.LoginUseCase
import com.postappplus.feature_auth.domain.use_case.RegisterUseCase
import com.postappplus.feature_auth.domain.use_case.AuthenticateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // Настройки для OkHttpClient, если необходимо
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(client: OkHttpClient): AuthApi = Retrofit.Builder()
        .baseUrl(AuthApi.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, sharedPreferences: SharedPreferences): AuthRepository =
        AuthRepositoryImpl(api,sharedPreferences)

    @Provides
    @Singleton
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase =
        RegisterUseCase(repository)



    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase =
        LoginUseCase(repository)

    @Provides
    @Singleton
    fun provideAuthenticationUseCase(repository: AuthRepository): AuthenticateUseCase =
        AuthenticateUseCase(repository)





}