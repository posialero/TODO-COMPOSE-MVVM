package com.example.todo_compose_mvvm.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todo_compose_mvvm.ui.screens.list.ListScreen
import com.example.todo_compose_mvvm.ui.screens.splash.SplashScreen
import com.example.todo_compose_mvvm.ui.viewmodels.SharedViewModel
import com.example.todo_compose_mvvm.util.Constants
import com.example.todo_compose_mvvm.util.toAction

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,
) {
    composable(
        route = Constants.SPLASH_SCREEN,
    ) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}