package ru.android.assistantapp.detailstask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.android.assistantapp.domain.Task

class TasksViewModel : ViewModel() {
    private val _data: MutableList<Task> =
        mutableListOf(Task("task1"), Task("task2"), Task("task3"))

    private val _tasks: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val tasks: LiveData<MutableList<Task>> = _tasks

    var isOrderCompleted: Boolean = false

    init {
        _tasks.postValue(_data)
    }

    fun setStateTask(task: Task, position: Int) {
        _data[position] = task
        _tasks.postValue(_data)
    }
}