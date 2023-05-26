package com.postappplus.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.postappplus.presentation.login.LoginSreen
import com.postappplus.presentation.login.LoginViewModel
import com.postappplus.presentation.register.RegisterScreen
import com.postappplus.presentation.splash.SplashScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination =  Screens.SplashScreen.route
    ){
        composable(route = Screens.SplashScreen.route){
            SplashScreen(navController = navController)

        }
        composable(route = Screens.LoginScreen.route){
            LoginSreen(navController = navController)

        }
        composable(route = Screens.RegisterScreen.route){
            RegisterScreen(navController = navController)

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