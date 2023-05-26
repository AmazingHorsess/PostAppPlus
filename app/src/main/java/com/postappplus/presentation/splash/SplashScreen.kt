package com.postappplus.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.rotationMatrix
import androidx.navigation.NavController
import com.postappplus.R
import com.postappplus.presentation.login.openSansExtraBold
import com.postappplus.ui.theme.angularBlue
import com.postappplus.ui.theme.angularOrange
import com.postappplus.ui.theme.angularPink
import com.postappplus.ui.theme.angularViolet
import com.postappplus.ui.theme.openSans
import com.postappplus.util.Screens
import kotlinx.coroutines.delay

@OptIn(ExperimentalTextApi::class)
@Composable
fun SplashScreen(
    navController: NavController
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