package com.postappplus.core.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.postappplus.feature_auth.presentation.login.LoginSreen
import com.postappplus.feature_auth.presentation.register.RegisterScreen
import com.postappplus.feature_auth.presentation.splash.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: SnackbarHostState
){
    NavHost(
        navController = navController,
        startDestination =  Screens.SplashScreen.route,
        modifier = Modifier.fillMaxSize()

    ){
        composable(Screens.SplashScreen.route){
            SplashScreen(
                navController = navController,
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate,

            )

        }
        composable(Screens.LoginScreen.route){
            LoginSreen(
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState,

                onLogin = {
                    navController.popBackStack(Screens.LoginScreen.route, inclusive = true)
                    //navController.navigate(Screens..route)
                },
            )

        }
        composable(route = Screens.RegisterScreen.route){
            RegisterScreen(
                navController = navController,
                scaffoldState = scaffoldState,
                onPopBackStack = navController::popBackStack
            )

        }
        composable(route = Screens.HomeScreen.route){

        }
        composable(route = Screens.MainFeedScreen.route){

        }
        composable(route = Screens.PostDetailScreen.route){

        }
        composable(route = Screens.ProfileScreen.route){

        }
        composable(route = Screens.EditProfileScreen.route){

        }
        composable(route = Screens.PersonalListScreen.route){

        }
        composable(route = Screens.AddPostScreen.route){

        }
        composable(route = Screens.EditPostScreen.route){

        }

    }
}