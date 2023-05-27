package com.postappplus.feature_auth.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.postappplus.R
import com.postappplus.feature_auth.presentation.login.openSansExtraBold
import com.postappplus.ui.theme.angularBlue
import com.postappplus.ui.theme.angularOrange
import com.postappplus.ui.theme.angularPink
import com.postappplus.ui.theme.angularViolet
import com.postappplus.core.util.Screens
import com.postappplus.core.util.UiEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalTextApi::class)
@Composable
fun SplashScreen(
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    navController: NavController,
    onPopBackStack: () -> Unit = {},
    onNavigate: (String) -> Unit ={},
    viewModel: SplashViewModel = hiltViewModel(),

) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0.3f)
    }
    val overShootInterpolator = remember {
        OvershootInterpolator(1f)
        
    }
    LaunchedEffect(key1 = true, ){
        scale.animateTo(
            targetValue = 1f,

            animationSpec = tween(
                durationMillis = 850,
                easing = {
                    overShootInterpolator.getInterpolation(it)
                }
            )
        )
        delay(1000)
        scale.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
        delay(100)
        navController.navigate(Screens.LoginScreen.route){
            popUpTo(Screens.SplashScreen.route){
                inclusive = true

            }
        }


    }
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.Navigate -> {
                    delay(850)
                    onPopBackStack()
                    onNavigate(event.route)
                }
                else -> Unit
            }
        }
    }







    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .scale(scale.value),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tg),
            contentDescription = "",
        )
        Layout(
            content = { TelegramLogoPlus() },
            modifier = Modifier
                .absoluteOffset(x = 210.dp, y = 428.dp)
        ) { measurables, constraints ->
            val placeable = measurables.firstOrNull()?.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable?.placeRelative(0, 0)
            }
        }
        Text(
            modifier = Modifier
                .offset(x = 0.dp, y = 170.dp),

            text = "Добро пожаловать в Telegram+",
            style = TextStyle(
                brush = Brush.linearGradient(
                    listOf(
                        angularBlue,
                        angularViolet,
                        angularPink,
                        angularOrange
                    )
                ),
                fontSize = 30.sp,
                fontFamily = openSansExtraBold,
                textAlign = TextAlign.Center
            )
        )

        // Ваш контент
    }

}