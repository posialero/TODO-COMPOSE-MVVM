package com.example.todo_compose_mvvm.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo_compose_mvvm.R
import com.example.todo_compose_mvvm.ui.viewmodels.SharedViewModel
import com.example.todo_compose_mvvm.util.SearchAppBarState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
        sharedViewModel.readSortState()
    }

    val action by sharedViewModel.action
    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchTasks by sharedViewModel.searchedTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by sharedViewModel.highPriorityTasks.collectAsState()

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    sharedViewModel.handleDatabaseActions(action = action)

    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                ListContent(
                    lowPriorityTasks = lowPriorityTasks,
                    highPriorityTasks = highPriorityTasks,
                    sortState = sortState,
                    searchTasks = searchTasks,
                    allTasks = allTasks,
                    navigateToTaskScreen = navigateToTaskScreen,
                    searchAppBarState = searchAppBarState
                )
            }
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
@Preview
private fun ListFabPreview() {
    ListFab(onFabClicked = {})
}

//@Composable
//@Preview
//private fun ListScreenPreview() {
//    ListScreen(navigateToTaskScreen = {}, sharedViewModel = {})
//}