package com.postappplus.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.postappplus.core.presentation.components.BaseScaffold
import com.postappplus.core.util.Screens
import com.postappplus.ui.theme.PostAppPlusTheme
import com.postappplus.ui.theme.appBarDark
import com.postappplus.ui.theme.appBarLight
import com.postappplus.core.util.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostAppPlusTheme {
                // A surface container using the 'background' color from the theme
                val systemUiController = rememberSystemUiController()
                val darkTheme = isSystemInDarkTheme()
                val snackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = if (darkTheme) appBarDark else appBarLight
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BaseScaffold(
                        navController = navController,
                        showBottomBar = navBackStackEntry?.destination?.route in listOf(
                            Screens.SplashScreen.route,

                        ),
                        state = snackbarHostState ){
                        Navigation(navController = navController, snackbarHostState)
                        
                    }



                }
            }
        }
    }
}

