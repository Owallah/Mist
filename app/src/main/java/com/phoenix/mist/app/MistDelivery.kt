package com.phoenix.mist.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phoenix.mist.LocationService
import com.phoenix.mist.data.home.HomeViewModel
import com.phoenix.mist.navigation.MistRouter
import com.phoenix.mist.navigation.Screen
import com.phoenix.mist.screens.HomeScreen
import com.phoenix.mist.screens.LoginScreen
import com.phoenix.mist.screens.SignUpScreen
import com.phoenix.mist.screens.TermsAndConditionsScreen

@Composable
fun MistDelivery(homeViewModel: HomeViewModel = viewModel()) {

    homeViewModel.checkForActiveSession()


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            MistRouter.navigateTo(Screen.HomeScreen)
        }

        Crossfade(targetState = MistRouter.currentScreen, label = "") { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen(
//                        state = viewModel.state.value,
//                        setupClusterManager = viewModel::setupClusterManager,
//                        calculateZoneViewCenter = viewModel::calculateZoneLatLngBounds
                        locationService = LocationService()
                    )
                }
            }
        }

    }
}