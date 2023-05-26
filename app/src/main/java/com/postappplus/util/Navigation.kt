package com.postappplus.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination =  Screens.SplashScreen.route
    ){
        composable(route = Screens.SplashScreen.route){

        }
        composable(route = Screens.LoginScreen.route){

        }
        composable(route = Screens.RegisterScreen.route){

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