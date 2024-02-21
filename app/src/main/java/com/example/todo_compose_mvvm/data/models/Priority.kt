package com.example.todo_compose_mvvm.data.models

import androidx.compose.ui.graphics.Color
import com.example.todo_compose_mvvm.ui.theme.HighPriorityColor
import com.example.todo_compose_mvvm.ui.theme.LowPriorityColor
import com.example.todo_compose_mvvm.ui.theme.MediumPriorityColor
import com.example.todo_compose_mvvm.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
