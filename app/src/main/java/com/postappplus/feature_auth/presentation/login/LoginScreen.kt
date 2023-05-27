package com.postappplus.feature_auth.presentation.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.postappplus.R
import com.postappplus.core.presentation.components.BaseTextField
import com.postappplus.core.presentation.components.StandardTextField
import com.postappplus.ui.theme.PadddingMedium
import com.postappplus.ui.theme.PadddingSmall
import com.postappplus.ui.theme.PadddingXLarge
import com.postappplus.ui.theme.blueAccent
import com.postappplus.core.util.Screens
import com.postappplus.core.util.UiEvent
import com.postappplus.core.util.asString
import com.postappplus.feature_auth.presentation.util.AuthError
import kotlinx.coroutines.flow.collectLatest

val openSansExtraBold = FontFamily(Font(R.font.osextrabold))


@Composable
fun LoginSreen(
    scaffoldState: SnackbarHostState,
    onNavigate: (String) -> Unit,
    onLogin: () -> Unit,
    ViewModel: LoginViewModel = hiltViewModel(),
) {
    val viewModel: LoginViewModel = ViewModel
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val state = viewModel.loginState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                is UiEvent.Navigate -> {
                    onNavigate(event.route)
                }
                is UiEvent.OnLogin -> {
                    onLogin()
                }

                else -> {

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

        StandardTextField(
            text = emailState.text,
            onValueChange = { viewModel.onEvent(LoginEvent.EnteredEmail(it)) },
            keyboardType = KeyboardType.Email,
            error = when (emailState.error) {
                is AuthError.FieldEmpty -> "Это поле не может быть пустым"
                else -> ""
            },
            hint = "Username / E-mail"
        )
        Spacer(modifier = Modifier.padding(vertical = PadddingSmall))

        StandardTextField(
            text = passwordState.text,
            onValueChange = { viewModel.onEvent(LoginEvent.EnteredPassword(it)) },
            hint = "Пароль",
            keyboardType = KeyboardType.Password,
            error = when (passwordState.error) {
                is AuthError.FieldEmpty -> "Это поле не может быть пустым"
                else -> ""
            },
            isPasswordVisible = state.isPasswordVisible,
            onPasswordToggleClick = { viewModel.onEvent(LoginEvent.TogglePasswordVisibility) }
        )

        Spacer(modifier = Modifier.padding(vertical = PadddingSmall))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()) {
            OutlinedButton(
                onClick = {
                    /*TODO*/ },
                shape = ShapeDefaults.Large,
                border = BorderStroke(width = 4.dp, color = blueAccent),) {
                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    tint = Color.White,

                    contentDescription = "QrCode",
                )
                Spacer(modifier = Modifier.padding(horizontal = PadddingSmall))
                Text(
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "QR",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .size(width = 60.dp, height = 35.dp)




                )
            }


            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = blueAccent
                ),
                shape = ShapeDefaults.Large,
                onClick = { viewModel.onEvent(LoginEvent.Login) }) {

                Text(
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "Войти",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .size(width = 100.dp, height = 35.dp)

                )


            }

        }
        if (state.isLoading) {
            CircularProgressIndicator()
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
                    onNavigate(Screens.RegisterScreen.route)
                }
        )
        }

    }




