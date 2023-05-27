package com.postappplus.feature_auth.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.postappplus.core.domain.states.PasswordTextFieldState
import com.postappplus.core.domain.states.StandardTextFieldState
import com.postappplus.core.util.Resource
import com.postappplus.core.util.SimpleResource
import com.postappplus.core.util.UiEvent
import com.postappplus.core.util.UiText

import com.postappplus.feature_auth.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(

    private val registerUseCase: RegisterUseCase
): ViewModel(){
    private val _emailState = mutableStateOf(StandardTextFieldState())
    val emailState: State<StandardTextFieldState> = _emailState

    private val _usernameState = mutableStateOf(StandardTextFieldState())
    val usernameState: State<StandardTextFieldState> = _usernameState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _repeatedPasswordState = mutableStateOf(PasswordTextFieldState())
    val repeatedPasswordState: State<PasswordTextFieldState> = _repeatedPasswordState

    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> = _registerState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _onRegister = MutableSharedFlow<Unit>(replay = 1)
    val onRegister = _onRegister.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredUsername -> {
                _usernameState.value = _usernameState.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredPassword -> {
                _passwordState.value = _passwordState.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredRepeatPassword -> {
                _repeatedPasswordState.value = _repeatedPasswordState.value.copy(
                    text = event.value

                )
            }
            is RegisterEvent.EnteredEmail -> {
                _emailState.value = _emailState.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.TogglePasswordVisibility -> {
                _passwordState.value = _passwordState.value.copy(
                    isPasswordVisible = !passwordState.value.isPasswordVisible
                )
            }
            is RegisterEvent.ToggleRepeatPasswordVisibility -> {
                _repeatedPasswordState.value = _repeatedPasswordState.value.copy(
                    isPasswordVisible = !repeatedPasswordState.value.isPasswordVisible
                )

            }
            is RegisterEvent.Sumbit -> {
                register()
            }
        }
    }
    private fun register(){
        viewModelScope.launch {
            _usernameState.value = usernameState.value.copy(error = null)
            _emailState.value = emailState.value.copy(error = null)
            _passwordState.value = passwordState.value.copy(error = null)
            _repeatedPasswordState.value = repeatedPasswordState.value.copy(error = null)
            _registerState.value = RegisterState(isLoading = true)



            val registerResult = registerUseCase(
                email = emailState.value.text,
                username = usernameState.value.text,
                password = passwordState.value.text,
                repeatedPassword = repeatedPasswordState.value.text
            )
            if (registerResult.emailError != null) {
                _emailState.value = emailState.value.copy(
                    error = registerResult.emailError
                )
            }
            if (registerResult.usernameError != null) {
                _usernameState.value = _usernameState.value.copy(
                    error = registerResult.usernameError
                )
            }
            if (registerResult.passwordError != null) {
                _passwordState.value = _passwordState.value.copy(
                    error = registerResult.passwordError
                )
            }
            if (registerResult.repeatedPasswordError != null){
                _repeatedPasswordState.value = _repeatedPasswordState.value.copy(
                    error = registerResult.repeatedPasswordError
                )
            }

            when(registerResult.result){
                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackBar(UiText.DynamicString("Successfully registered!")))
                    _onRegister.emit(Unit)



                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackBar(registerResult.result.uiText ?: UiText.unknownError())
                    )

                }
                null -> {

                }
            }





        }
    }


    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}