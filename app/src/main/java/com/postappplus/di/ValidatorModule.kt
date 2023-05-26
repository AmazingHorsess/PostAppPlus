package com.postappplus.di

import com.postappplus.domain.contracts.EmailValidator
import com.postappplus.domain.contracts.PasswordValidator
import com.postappplus.domain.contracts.RepeatedPasswordValidator
import com.postappplus.domain.contracts.UsernameValidator
import com.postappplus.domain.use_case.validation_use_case.ValidateEmail
import com.postappplus.domain.use_case.validation_use_case.ValidatePassword
import com.postappplus.domain.use_case.validation_use_case.ValidateRepeatedPassword
import com.postappplus.domain.use_case.validation_use_case.ValidateUsername
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ValidatorsModule {

    @Provides
    fun provideEmailValidator(): EmailValidator {
        return ValidateEmail()
    }

    @Provides
    fun providePasswordValidator(): PasswordValidator {
        return ValidatePassword()
    }

    @Provides
    fun provideRepeatedPasswordValidator(): RepeatedPasswordValidator {
        return ValidateRepeatedPassword()
    }

    @Provides
    fun provideUsernameValidator(): UsernameValidator {
        return ValidateUsername()
    }
}