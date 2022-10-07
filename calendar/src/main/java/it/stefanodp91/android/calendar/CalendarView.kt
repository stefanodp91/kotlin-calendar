package it.stefanodp91.android.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import it.stefanodp91.android.calendar.adapter.CalendarAdapter
import it.stefanodp91.android.calendar.databinding.ViewCalendarBinding
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Stefano De Pascalis on 04/10/22.
 */
class CalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var binding: ViewCalendarBinding

    private val adapter = CalendarAdapter()

    private lateinit var calendar: Calendar

    init {
        val inflater = LayoutInflater.from(context)

        binding = ViewCalendarBinding.inflate(inflater, this, true)

        setupHeader()
        setupRecyclerView()

        assignDate(
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun setupHeader() {
        binding.prevMonth.setOnClickListener {
            navigate(-1)
        }

        binding.nextMonth.setOnClickListener {
            navigate(1)
        }

        setMonth(Calendar.getInstance().time)
        setYear(Calendar.getInstance().time)
    }

    private fun setupRecyclerView() {
        binding.grid.adapter = adapter
        binding.grid.setHasFixedSize(true)
        binding.grid.layoutManager = CustomGridLayoutManager(context, 7).apply {
            setScrollEnabled(false)
        }
    }

    private fun navigate(amount: Int) {
        calendar.add(Calendar.MONTH, amount)
        setMonth(calendar.time)
        setYear(calendar.time)
        assignDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun setMonth(date: Date) {
        binding.month.text =
            SimpleDateFormat("MMMM", Locale.ITALY).format(date.time)
                .replaceFirstChar {
                    it.uppercase()
                }
    }

    private fun setYear(date: Date) {
        binding.year.text = SimpleDateFormat("yyyy").format(date.time)
    }

    fun assignDate(
        year: Int? = null,
        month: Int? = null,
        date: Int? = null
    ) {

        calendar = Calendar.getInstance()

        // We'll set the date of the calendar to the following
        // date. We can use constant variable in the calendar
        // for months value (JANUARY - DECEMBER).
        // Be informed that month in Java started from 0 instead of 1.
        calendar[year ?: Calendar.getInstance().get(Calendar.YEAR), month ?: Calendar.getInstance()
            .get(Calendar.MONTH)] = date ?: Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        adapter.list = arrayListOf<CalendarItem>().apply {
            addAll(generateDaysOfWeek())
            addAll(generateShift(calendar))
            addAll(generateDaysOfMonth(calendar))
        }.toTypedArray()

        adapter.notifyDataSetChanged()
    }
}