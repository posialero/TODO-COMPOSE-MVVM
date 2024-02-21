package com.example.todo_compose_mvvm.navigation

import androidx.navigation.NavHostController
import com.example.todo_compose_mvvm.util.Action
import com.example.todo_compose_mvvm.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true}
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
}