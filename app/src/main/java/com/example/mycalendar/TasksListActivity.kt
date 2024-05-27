package com.example.mycalendar

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalendar.adapters.TaskAdapter
import com.example.mycalendar.api.RetrofitInstance
import com.example.mycalendar.repository.TasksRepository
import com.example.mycalendar.utils.Resource
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycalendar.model.Task
import com.example.mycalendar.model.TaskDetail
import java.nio.file.Files.list

class TasksListActivity : AppCompatActivity() {

    lateinit var tasksRecyclerView: RecyclerView
    lateinit var tasksAdapter: TaskAdapter
    val TAG = "TasksListActivity"
    lateinit var viewModel: TasksViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks_list)
        val tasksRepository = TasksRepository()
        val viewModelProviderFactory = TasksViewModelFactory(tasksRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(TasksViewModel::class.java)
        setupRecyclerView()

        viewModel.tasks.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        //Log.i(TAG, "onCreate: "+newsResponse.tasks.toString())
                        //newsResponse.tasks = listOf(Task(TaskDetail("hello","Rev"),1))
                        tasksAdapter.differ.submitList(newsResponse.tasks.toList())
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                }
            }
        })
    }

    private fun setupRecyclerView() {
        tasksRecyclerView = findViewById(R.id.TasksRV)
        tasksRecyclerView.adapter = TaskAdapter()
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}