package it.stefanodp91.android.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.stefanodp91.android.calendar.CalendarItem
import it.stefanodp91.android.calendar.ViewType
import it.stefanodp91.android.calendar.databinding.*

/**
 * Created by Stefano De Pascalis on 04/10/22.
 */
class CalendarAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = emptyArray<CalendarItem>()

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType?.asInt ?: super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ViewType.CURRENT_DAY.asInt -> {
                val binding = ViewCurrentDayBinding.inflate(inflater, parent, false)
                CurrentDayHolder(binding)
            }
            ViewType.DAY_OF_MONTH.asInt -> {
                val binding = ViewDayOfMonthBinding.inflate(inflater, parent, false)
                DayOfMonthHolder(binding)
            }
            ViewType.DAY_OF_WEEK.asInt -> {
                val binding = ViewDayOfWeekBinding.inflate(inflater, parent, false)
                DayOfWeekHolder(binding)
            }
            ViewType.SELECTED_DAY.asInt -> {
                val binding = ViewSelectedDayBinding.inflate(inflater, parent, false)
                SelectedDayHolder(binding)
            }
            ViewType.MOCK.asInt -> {
                val binding = ViewMockDayBinding.inflate(inflater, parent, false)
                MockDayHolder(binding)
            }
            else -> {
                val binding = ViewMockDayBinding.inflate(inflater, parent, false)
                MockDayHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ViewType.CURRENT_DAY.asInt -> {
                (holder as CurrentDayHolder).bind(list[position], position, callback)
            }
            ViewType.DAY_OF_MONTH.asInt -> {
                (holder as DayOfMonthHolder).bind(list[position], position, callback)
            }
            ViewType.DAY_OF_WEEK.asInt -> {
                (holder as DayOfWeekHolder).bind(list[position])
            }
            ViewType.SELECTED_DAY.asInt -> {
                (holder as SelectedDayHolder).bind(list[position], position, callback)
            }
            ViewType.MOCK.asInt -> {
                (holder as MockDayHolder).bind(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private val callback: (index: Int) -> Unit = {
        // find current selected index and unselect
        val currentSelectedIndex =
            list.indices.find { el -> list[el].viewType == ViewType.SELECTED_DAY }
        if (currentSelectedIndex != null) {
            list[currentSelectedIndex].viewType = list[currentSelectedIndex].defaultViewType
            notifyItemChanged(currentSelectedIndex)
        }
        // select the new index
        list[it].viewType = ViewType.SELECTED_DAY
        notifyItemChanged(it)
    }
}