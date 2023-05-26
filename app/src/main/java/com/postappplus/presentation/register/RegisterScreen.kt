package com.postappplus.presentation.register

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import com.postappplus.R
import com.postappplus.presentation.components.BaseTextField
import com.postappplus.presentation.login.LoginEvent
import com.postappplus.presentation.login.openSansExtraBold
import com.postappplus.ui.theme.PadddingLarge
import com.postappplus.ui.theme.PadddingMedium
import com.postappplus.ui.theme.PadddingSmall
import com.postappplus.ui.theme.PadddingXLarge
import com.postappplus.ui.theme.blueAccent
import com.postappplus.util.Screens

@Composable
fun RegisterScreen(
    navController: NavController,
    ViewModel: RegisterViewModel = hiltViewModel(),

){
    val viewModel: RegisterViewModel = ViewModel
    val state = viewModel.state.value
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is RegisterViewModel.ValidationEvent.Success -> {
                    Log.d("TAG", "Success")
                }
            }
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
        BaseTextField(
            text = viewModel.state.value.usernameText,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.EnteredUsername(it))
            },
            hint = "Имя пользователя",
            keyboardType = KeyboardType.Text,
            error = state.usernameError != null ,
        )
        if (state.usernameError != null) {
            Text(
                text = state.usernameError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = PadddingSmall))
        BaseTextField(
            text = viewModel.state.value.emailText,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.EnteredEmail(it))
            },
            hint = "E-Mail",
            keyboardType = KeyboardType.Text,
            error = state.emailError != null,
        )
        if (state.emailError != null) {
            Text(
                text = state.emailError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = PadddingSmall))
        BaseTextField(
            text = viewModel.state.value.passwordText,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.EnteredPassword(it))
            },
            hint = "Пароль",
            keyboardType = KeyboardType.Password,
            error = state.passwordError != null,
            isPasswordVisible = viewModel.state.value.isPasswordVisible,
            onPasswordToggleClick = {
                viewModel.onEvent(RegisterEvent.TogglePasswordVisibility)
            }
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = PadddingSmall))
        BaseTextField(
            text = viewModel.state.value.repeatPasswordText,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.EnteredRepeatPassword(it))
            },
            hint = "Повторите пароль",
            keyboardType = KeyboardType.Password,
            error = state.repeatedPasswordError != null,
            isPasswordVisible = viewModel.state.value.isRepeatPasswordVisible,
            onPasswordToggleClick = {
                viewModel.onEvent(RegisterEvent.ToggleRepeatPasswordVisibility)
            }
        )
        if (state.repeatedPasswordError != null) {
            Text(
                text = state.repeatedPasswordError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }

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

