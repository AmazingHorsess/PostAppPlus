package com.postappplus.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.postappplus.domain.contracts.EmailValidator
import com.postappplus.domain.contracts.PasswordValidator
import com.postappplus.domain.contracts.RepeatedPasswordValidator
import com.postappplus.domain.contracts.UsernameValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val repeatedPasswordValidator: RepeatedPasswordValidator,
    private val usernameValidator: UsernameValidator
): ViewModel(){
    private var _state = mutableStateOf(RegisterState())
    var state: State<RegisterState> = _state

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredUsername -> {
                _state.value = _state.value.copy(usernameText = event.value)
            }
            is RegisterEvent.EnteredPassword -> {
                _state.value = _state.value.copy(passwordText = event.value)
            }
            is RegisterEvent.EnteredRepeatPassword -> {
                _state.value = _state.value.copy(repeatPasswordText = event.value)
            }
            is RegisterEvent.EnteredEmail -> {
                _state.value = _state.value.copy(emailText = event.value)
            }
            is RegisterEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)
            }
            is RegisterEvent.ToggleRepeatPasswordVisibility -> {
                _state.value = _state.value.copy(isRepeatPasswordVisible = !_state.value.isRepeatPasswordVisible)
            }
            is RegisterEvent.Sumbit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = emailValidator.validate(state.value.emailText)
        val passwordResult = passwordValidator.validate(state.value.passwordText)
        val repeatedPasswordResult = repeatedPasswordValidator.validate(state.value.passwordText, state.value.repeatPasswordText)
        val usernameResult = usernameValidator.validate(state.value.usernameText)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            usernameResult
        ).any { !it.successful }
        if (hasError){
            _state.value = state.value.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                usernameError = usernameResult.errorMessage

            )
            return

        }
        viewModelScope.launch(Dispatchers.Main) {
            validationEventChannel.send(ValidationEvent.Success)
        }


    }
    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}