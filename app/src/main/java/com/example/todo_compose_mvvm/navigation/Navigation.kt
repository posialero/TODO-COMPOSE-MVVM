package com.example.todo_compose_mvvm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todo_compose_mvvm.navigation.destinations.listComposable
import com.example.todo_compose_mvvm.navigation.destinations.taskComposable
import com.example.todo_compose_mvvm.ui.viewmodels.SharedViewModel
import com.example.todo_compose_mvvm.util.Constants.LIST_SCREEN
import com.example.todo_compose_mvvm.util.Constants.SPLASH_SCREEN

@Composable
fun SetupNavigation(
    sharedViewModel: SharedViewModel,
    navController: NavHostController
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
    }

}