package com.example.mycalendar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalendar.model.TaskListResponse
import com.example.mycalendar.model.userId
import com.example.mycalendar.repository.TasksRepository
import com.example.mycalendar.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class TasksViewModel(
    val taskRepository: TasksRepository
) : ViewModel() {

    val tasks: MutableLiveData<Resource<TaskListResponse>> = MutableLiveData()


    init {
        getTasksList(userId(123))
    }

    private fun getTasksList(userId: userId) = viewModelScope.launch {
        tasks.postValue(Resource.Loading())
        val response = taskRepository.getTasksList(userId)
        Log.i("Revanth", "getTasksList: "+response.isSuccessful)
        tasks.postValue(handleTasksResponse(response))
    }

    private fun handleTasksResponse(response: Response<TaskListResponse>) : Resource<TaskListResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}