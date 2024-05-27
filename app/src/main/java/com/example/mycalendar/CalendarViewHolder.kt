package com.example.mycalendar

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalendar.adapters.CalendarAdapter.OnItemListener


class CalendarViewHolder(itemView: View, private val onItemListener: OnItemListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val dayOfMonth: TextView

    init {
        dayOfMonth = itemView.findViewById<TextView>(R.id.cellDayText)
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        onItemListener.onItemClick(getAdapterPosition(), dayOfMonth.getText() as String)
    }
}