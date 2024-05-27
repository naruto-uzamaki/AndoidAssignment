package com.example.mycalendar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalendar.R
import com.example.mycalendar.model.Task

class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var taskName = itemView.findViewById<TextView>(R.id.task_name)
        var taskDsc = itemView.findViewById<TextView>(R.id.task_desc)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.task_id == newItem.task_id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task =  differ.currentList[position]
        holder.apply {
            taskName.text = task.task_detail?.title
            taskDsc.text = task.task_detail?.description
        }
    }
}