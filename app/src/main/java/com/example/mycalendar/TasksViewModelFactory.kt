package com.example.mycalendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycalendar.repository.TasksRepository

class TasksViewModelFactory(
    val tasksRepository: TasksRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(tasksRepository) as T
    }
}