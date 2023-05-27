package com.postappplus.feature_auth.presentation.register

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.postappplus.R
import com.postappplus.core.presentation.components.BaseTextField
import com.postappplus.core.presentation.components.StandardTextField
import com.postappplus.feature_auth.presentation.login.openSansExtraBold
import com.postappplus.ui.theme.PadddingLarge
import com.postappplus.ui.theme.PadddingMedium
import com.postappplus.ui.theme.PadddingSmall
import com.postappplus.ui.theme.PadddingXLarge
import com.postappplus.ui.theme.blueAccent
import com.postappplus.core.util.Screens
import com.postappplus.core.util.UiEvent
import com.postappplus.core.util.asString
import com.postappplus.feature_auth.presentation.util.AuthError
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    scaffoldState: SnackbarHostState,
    navController: NavController,
    ViewModel: RegisterViewModel = hiltViewModel(),
    onPopBackStack:() -> Unit

    ){
    val showSnackbar = remember { mutableStateOf(false) }
    val viewModel: RegisterViewModel = ViewModel
    val usernameState = viewModel.usernameState.value
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val repeatedPasswordState = viewModel.repeatedPasswordState.value
    val registerState = viewModel.registerState.value
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = true) {
        viewModel.onRegister.collect { onPopBackStack() }
    }
    LaunchedEffect(key1 = keyboardController) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {

                    keyboardController?.hide()

                    scaffoldState.showSnackbar(
                        event.uiText.asString(context),
                        duration = SnackbarDuration.Long
                    )
                }
                else -> {}
            }
        }
    }
    if (showSnackbar.value) {
        LaunchedEffect(scaffoldState) {
            scaffoldState.showSnackbar("Success")
        }
    }




    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                PadddingMedium
            )
    ) {

        Spacer(modifier = Modifier.padding(vertical = PadddingLarge))
        Image(
            modifier = Modifier
                .size(98.dp),
            painter = painterResource(
                id = R.drawable.tg
            ),
            contentDescription = "Logo"
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontFamily = openSansExtraBold,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 26.sp,


                        color = MaterialTheme.colorScheme.onBackground

                    )

                ) {
                    append("Зарегистрироваться в ")

                }
                withStyle(
                    SpanStyle(
                        fontFamily = openSansExtraBold,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 26.sp,
                        color = blueAccent
                    )
                ) {
                    append(
                        "Telegram+"
                    )
                }
            })
        Spacer(modifier = Modifier.padding(vertical = PadddingMedium))
        StandardTextField(
            text = usernameState.text,
            onValueChange = { viewModel.onEvent(RegisterEvent.EnteredUsername(it)) },
            error = when (viewModel.usernameState.value.error) {
                is AuthError.FieldEmpty -> {
                    "Это поле не может быть пустым"
                }
                is AuthError.InputTooShort -> {
                   "Это поле не может быть короче 4 символов"
                }
                else -> ""
            },
            hint = "Имя пользователя"
        )
        Spacer(modifier = Modifier.padding(vertical = PadddingSmall))

        StandardTextField(
            text = emailState.text,
            onValueChange = { viewModel.onEvent(RegisterEvent.EnteredEmail(it)) },
            error = when (emailState.error) {
                is AuthError.FieldEmpty -> {
                    "Это поле не может быть пустым"
                }
                is AuthError.InvalidEmail -> {
                    "Неверный формат почты"
                }
                else -> ""
            },
            keyboardType = KeyboardType.Email,
            hint = "Введите E-Mail"
        )

        Spacer(modifier = Modifier.padding(vertical = PadddingSmall))
        StandardTextField(
            text = passwordState.text,
            onValueChange = { viewModel.onEvent(RegisterEvent.EnteredPassword(it)) },
            hint = "Введите пароль",
            keyboardType = KeyboardType.Password,
            error = when (passwordState.error) {
                is AuthError.FieldEmpty -> {
                    "Это поле не может быть пустым"
                }
                is AuthError.InputTooShort -> {
                    "Это поле не может быть короче 6 символов"
                }
                is AuthError.InvalidPassword -> {
                    "Пароль должен содержать минимум одну букву"
                }
                else -> ""
            },
            isPasswordVisible = passwordState.isPasswordVisible,
            onPasswordToggleClick = {
                viewModel.onEvent(RegisterEvent.TogglePasswordVisibility)
            }
        )
        Spacer(modifier = Modifier.padding(vertical = PadddingSmall))
        StandardTextField(
            text = repeatedPasswordState.text,
            onValueChange = { viewModel.onEvent(RegisterEvent.EnteredRepeatPassword(it)) },
            hint = "Повторите пароль",
            keyboardType = KeyboardType.Password,
            error = when (repeatedPasswordState.error) {
                is AuthError.FieldEmpty -> {
                    "Это поле не может быть пустым"
                }

                is AuthError.InvalidPassword -> {
                    "Пароль не совпадает"
                }
                else -> ""
            },
            isPasswordVisible = passwordState.isPasswordVisible,
            onPasswordToggleClick = {
                viewModel.onEvent(RegisterEvent.TogglePasswordVisibility)
            }
        )
        Spacer(modifier = Modifier.padding(vertical = PadddingSmall))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = blueAccent
                ),
                shape = ShapeDefaults.Small,
                onClick = { viewModel.onEvent(RegisterEvent.Sumbit) }, ) {

                Text(
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "Войти",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .size(width = 100.dp, height = 35.dp)
                        .absoluteOffset(x = 0.dp, y = 0.dp)
                )


            }
        }
        Spacer(modifier = Modifier.padding(vertical = PadddingXLarge))
        Text(
            text = buildAnnotatedString {
                append("У вас уже есть аккаунт?")
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = blueAccent
                    )
                ) {
                    append("Логин.")
                }
            },
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .clickable {
                    navController.navigate(
                        Screens.LoginScreen.route
                    )
                }
        )
    }

}

