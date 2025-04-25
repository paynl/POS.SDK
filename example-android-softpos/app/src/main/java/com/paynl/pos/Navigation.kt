package com.paynl.pos

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paynl.pos.ui.pages.SplashScreen
import com.paynl.pos.ui.pages.HomeScreen
import com.paynl.pos.ui.pages.activation.OnboardingScreen
import com.paynl.pos.ui.viewModel.HomeViewModel
import com.paynl.pos.ui.viewModel.OnboardingViewModel
import com.paynl.pos.ui.viewModel.SplashViewModel
import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Onboarding

@Serializable
object Home

@Composable
fun PayNlNavigation(context: Context) {
    val navController = rememberNavController()

    Box(modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        NavHost(navController = navController, startDestination = Splash) {
            composable<Splash> { SplashScreen(
                viewModel = SplashViewModel(context = context, navHostController = navController)
            ) }
            composable<Onboarding> {
                val viewModel: OnboardingViewModel = viewModel()
                viewModel.navController = navController

                OnboardingScreen(viewModel = viewModel)
            }
            composable<Home> {
                val viewModel: HomeViewModel = viewModel()
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}

