package com.example.todo_compose_mvvm.ui.screens.list

import android.annotation.SuppressLint
import android.view.PixelCopy.Request
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo_compose_mvvm.R
import com.example.todo_compose_mvvm.data.models.Priority
import com.example.todo_compose_mvvm.data.models.ToDoTask
import com.example.todo_compose_mvvm.ui.theme.HighPriorityColor
import com.example.todo_compose_mvvm.ui.theme.LARGEST_PADDING
import com.example.todo_compose_mvvm.ui.theme.LARGE_PADDING
import com.example.todo_compose_mvvm.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.todo_compose_mvvm.util.Action
import com.example.todo_compose_mvvm.util.RequestState
import com.example.todo_compose_mvvm.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ListContent(
    lowPriorityTasks: List<ToDoTask>,
    highPriorityTasks: List<ToDoTask>,
    sortState: RequestState<Priority>,
    searchTasks: RequestState<List<ToDoTask>>,
    allTasks: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    searchAppBarState: SearchAppBarState,
    onSwipeToDelete: (Action, ToDoTask) -> Unit
) {
    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = searchTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete = onSwipeToDelete
                    )
                }
            }

            sortState.data == Priority.NONE -> {
                if (allTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = allTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete = onSwipeToDelete
                    )
                }
            }

            sortState.data == Priority.LOW -> {
                HandleListContent(
                    tasks = lowPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen,
                    onSwipeToDelete = onSwipeToDelete
                )
            }

            sortState.data == Priority.HIGH -> {
                HandleListContent(
                    tasks = highPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen,
                    onSwipeToDelete = onSwipeToDelete
                )
            }
        }
    }
}

@Composable
fun HandleListContent(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTasks(
            tasks = tasks,
            navigateToTaskScreen = navigateToTaskScreen,
            onSwipeToDelete = onSwipeToDelete
        )
    }

}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit
) {
    LazyColumn {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ) { task ->
            val dismissState = rememberDismissState()
            val dismissDirection = dismissState.dismissDirection
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

            if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                val scope = rememberCoroutineScope()
                scope.launch {
                    delay(300)
                    onSwipeToDelete(Action.DELETE, task)
                }
            }

            val degrees by animateFloatAsState(
                targetValue = if (dismissState.targetValue == DismissValue.Default)
                    0f
                else
                    -45f,
                label = "Delete Icon Rotation"
            )

            var itemAppeared by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = true) {
                itemAppeared = true
            }

            AnimatedVisibility(
                visible = itemAppeared && !isDismissed,
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )

            ) {
                SwipeToDismiss(
                    state = dismissState,
                    background = { RedBackground(degrees = degrees) },
                    dismissContent = {
                        TaskItem(
                            toDoTask = task,
                            navigateToTaskScreen = navigateToTaskScreen
                        )
                    },
                    directions = setOf(DismissDirection.EndToStart)
                )
            }
        }
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }

                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun RedBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HighPriorityColor)
            .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier.rotate(degrees = degrees),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = Color.White
        )
    }
}

@Composable
@Preview
fun RedBackgroundPreview() {
    Column(modifier = Modifier.height(100.dp)) {
        RedBackground(degrees = 0f)
    }
}

@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(
        toDoTask =
        ToDoTask(
            0,
            "Title",
            "Description",
            priority = Priority.HIGH
        ),
        navigateToTaskScreen = {}
    )
}