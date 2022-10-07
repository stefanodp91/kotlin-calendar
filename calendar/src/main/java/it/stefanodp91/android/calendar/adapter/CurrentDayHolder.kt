package it.stefanodp91.android.calendar.adapter

import androidx.recyclerview.widget.RecyclerView
import it.stefanodp91.android.calendar.CalendarItem
import it.stefanodp91.android.calendar.databinding.ViewCurrentDayBinding

/**
 * Created by Stefano De Pascalis on 04/10/22.
 */
class CurrentDayHolder(var binding: ViewCurrentDayBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        calendarItem: CalendarItem,
        index: Int,
        callback: (index: Int) -> Unit
    ) {
        binding.day.text = calendarItem.label

        binding.root.setOnClickListener {
            callback.invoke( index)
        }
    }
}
