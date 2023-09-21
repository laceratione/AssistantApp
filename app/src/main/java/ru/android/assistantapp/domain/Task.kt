package ru.android.assistantapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.android.assistantapp.detailstask.TaskState

@Parcelize
data class Task(val name: String, var state: TaskState = TaskState.Default) : Parcelable
