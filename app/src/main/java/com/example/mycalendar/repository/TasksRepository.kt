package com.example.mycalendar.repository

import com.example.mycalendar.api.RetrofitInstance
import com.example.mycalendar.model.userId

class TasksRepository() {
    suspend fun getTasksList(userId: userId) =
        RetrofitInstance.api.getTaskLists(userId)
}