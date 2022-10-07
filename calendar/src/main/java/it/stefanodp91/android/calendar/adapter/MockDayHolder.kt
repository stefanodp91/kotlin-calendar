package it.stefanodp91.android.calendar.adapter

import androidx.recyclerview.widget.RecyclerView
import it.stefanodp91.android.calendar.CalendarItem
import it.stefanodp91.android.calendar.databinding.ViewMockDayBinding

/**
 * Created by Stefano De Pascalis on 04/10/22.
 */
class MockDayHolder(var binding: ViewMockDayBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(calendarItem: CalendarItem) {
        binding.day.text = calendarItem.label
    }
}