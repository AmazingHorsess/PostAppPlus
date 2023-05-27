package com.postappplus.core.util

sealed class Screens(val route:String){
    object SplashScreen : Screens("splash_screen")
    object RegisterScreen : Screens("register_screen")
    object LoginScreen : Screens("login_screen")
    object HomeScreen : Screens("home_screen")
    object MainFeedScreen : Screens("main_feed_screen")
    object PostDetailScreen : Screens("post_detail_screen")
    object ProfileScreen : Screens("profile_screen")
    object EditProfileScreen : Screens("edit_profile_screen")
    object PersonalListScreen : Screens("personal_list_screen")
    object AddPostScreen : Screens("add_post_screen")
    object EditPostScreen : Screens("edit_post_screen")




}






