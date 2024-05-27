package com.example.mycalendar.api

import com.example.mycalendar.model.TaskListResponse
import com.example.mycalendar.model.userId
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface TasksService {

    @POST("/api/getCalendarTaskLists")
    suspend fun getTaskLists(
        @Body userId: userId
    ): Response<TaskListResponse>
}