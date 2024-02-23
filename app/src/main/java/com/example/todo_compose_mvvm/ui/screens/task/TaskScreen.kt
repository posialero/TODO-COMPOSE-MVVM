package com.example.todo_compose_mvvm.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.todo_compose_mvvm.data.models.ToDoTask
import com.example.todo_compose_mvvm.ui.viewmodels.SharedViewModel
import com.example.todo_compose_mvvm.util.Action

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title by sharedViewModel.title
    val description by sharedViewModel.description
    val priority by sharedViewModel.priority

    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen)
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                TaskContent(
                    title = title,
                    onTitleChange = { title ->
                        sharedViewModel.updateTitle(title)
                    },
                    description = description,
                    onDescriptionChange = { description ->
                        sharedViewModel.description.value = description
                    },
                    priority = priority,
                    onPrioritySelected = { priority ->
                        sharedViewModel.priority.value = priority
                    }
                )
            }
        }
    )
}