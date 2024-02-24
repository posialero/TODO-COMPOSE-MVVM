package com.example.todo_compose_mvvm.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit
) {
    if (openDialog) {
//        AlertDialog(
//            title = {
//                Text(text = title)
//            },
//            onDismissRequest = { /*TODO*/ })
//        {
//
//        }
    }
    
}