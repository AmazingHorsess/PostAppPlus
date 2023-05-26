package com.postappplus.presentation.login

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.postappplus.R
import com.postappplus.presentation.components.BaseTextField
import com.postappplus.ui.theme.PadddingMedium
import com.postappplus.ui.theme.PadddingSmall
import com.postappplus.ui.theme.PadddingXLarge
import com.postappplus.ui.theme.blueAccent
import com.postappplus.util.Screens

val openSansExtraBold = FontFamily(Font(R.font.osextrabold))


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginSreen(
    navController: NavController,
    ViewModel: LoginViewModel = hiltViewModel(),
) {
    val viewModel: LoginViewModel = ViewModel
    val state = viewModel.state

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                PadddingMedium
            )
    ) {
        Spacer(modifier = Modifier.padding(vertical = PadddingXLarge))
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
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onBackground

                    )

                ) {
                    append("Зайти в ")
                }
                withStyle(
                    SpanStyle(
                        fontFamily = openSansExtraBold,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp,
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
            viewModel.onEvent(LoginEvent.UserChanged(it))
        },
        hint = "Имя пользователя",
        keyboardType = KeyboardType.Text,
        error = viewModel.state.value.usernameError,
        )
        Spacer(modifier = Modifier.padding(vertical = PadddingSmall))
        BaseTextField(
            text = viewModel.state.value.passwordText,
            onValueChange = {
                viewModel.onEvent(LoginEvent.UserPasswordChanged(it))
            },
            hint = "Пароль",
            keyboardType = KeyboardType.Password,
            error = viewModel.state.value.passwordError,
            isPasswordVisible = viewModel.state.value.isPasswordVisible,
            onPasswordToggleClick = {
                viewModel.onEvent(LoginEvent.ShowPassword(it))
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
                onClick = { /*TODO*/ }) {

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
                append("У вас нету аккаунта?")
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = blueAccent
                    )
                ) {
                    append("Зарегистрироваться")
                }
            },
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .clickable {
                    navController.navigate(
                        Screens.RegisterScreen.route
                    )
                }
        )
    }




    }



