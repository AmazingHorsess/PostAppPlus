package com.postappplus.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel(){

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UserChanged -> {
                _state.value = _state.value.copy(usernameText = event.user)
            }
            is LoginEvent.UserPasswordChanged -> {
                _state.value = _state.value.copy(passwordText = event.password)
            }
            is LoginEvent.ShowPassword -> {
                _state.value = _state.value.copy(isPasswordVisible = event.isShowed)
            }
            is LoginEvent.Sumbit -> {
                submitData()
            }
        }
    }

    private fun submitData(){

    }

sealed class ValidationEvent {
    object Success: ValidationEvent()

}






}