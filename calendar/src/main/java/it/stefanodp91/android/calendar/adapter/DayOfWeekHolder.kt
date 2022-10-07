package it.stefanodp91.android.calendar.adapter

import androidx.recyclerview.widget.RecyclerView
import it.stefanodp91.android.calendar.CalendarItem
import it.stefanodp91.android.calendar.ViewType
import it.stefanodp91.android.calendar.databinding.ViewDayOfWeekBinding

/**
 * Created by Stefano De Pascalis on 04/10/22.
 */
class DayOfWeekHolder(var binding: ViewDayOfWeekBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(calendarItem: CalendarItem) {
        binding.day.text = calendarItem.label
    }
}