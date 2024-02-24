package com.example.todo_compose_mvvm.navigation

import androidx.navigation.NavHostController
import com.example.todo_compose_mvvm.util.Action
import com.example.todo_compose_mvvm.util.Constants.LIST_SCREEN
import com.example.todo_compose_mvvm.util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    val task: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true}
        }
    }

    val list: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
}