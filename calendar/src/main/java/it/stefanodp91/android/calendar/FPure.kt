package it.stefanodp91.android.calendar

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Stefano De Pascalis on 04/10/22.
 */
fun generateDaysOfWeek(): Array<CalendarItem> {
    return getDaysOfWeek().map {
        return@map CalendarItem(it.toString(), ViewType.DAY_OF_WEEK)
    }.toTypedArray()
}

fun generateShift(calendar: Calendar): ArrayList<CalendarItem> {
    val shift = arrayListOf<CalendarItem>()

    val maxPadding: Int? =
        getDaysOfWeek().indices.find {
            return@find getFirstDayOfWeek(calendar).lowercase() == getDaysOfWeek()[it]?.lowercase()
        }

    for (i in 1..maxPadding!!) {
        val item = CalendarItem("", ViewType.MOCK)
        shift.add(item)
    }

    return shift
}

fun generateDaysOfMonth(calendar: Calendar): Array<CalendarItem> {
    // Here we get the maximum days for the date specified
    // in the calendar.
    val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    return IntArray(maxDay) { it + 1 }.map {
        val item = CalendarItem(it.toString(), ViewType.DAY_OF_MONTH)

        // check if it is today
        if (isSameMonthAndYear(calendar.time, Calendar.getInstance().time) &&
            it == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ) {
            item.defaultViewType = ViewType.CURRENT_DAY
            item.viewType = ViewType.CURRENT_DAY
        }

        return@map item
    }.toTypedArray()
}

fun getDaysOfWeek(): ArrayList<String?> {

    val calendar = Calendar.getInstance()

    val format = SimpleDateFormat("EEE", Locale.ITALY)

    val days = arrayOfNulls<String>(7)
    val delta =
        -calendar[GregorianCalendar.DAY_OF_WEEK] + 2 //add 2 if your week start on monday

    calendar.add(Calendar.DAY_OF_MONTH, delta)
    for (i in 0..6) {
        days[i] = format.format(calendar.time)
        calendar.add(Calendar.DAY_OF_MONTH, 1)
    }

    return ArrayList(days.asList())
}

fun getFirstDayOfWeek(calendar: Calendar): String {
    val format = SimpleDateFormat("EEEEEE", Locale.ITALY)

    calendar[Calendar.HOUR_OF_DAY] = 0 // ! clear would not reset the hour of day !

    calendar.clear(Calendar.MINUTE)
    calendar.clear(Calendar.SECOND)
    calendar.clear(Calendar.MILLISECOND)

    // get start of the month
    calendar[Calendar.DAY_OF_MONTH] = 1
    // Log.d("cal", "Start of the month:       ${cal.time}")

    val dayOfWeek = format.format(calendar.time).lowercase()
    // Log.d("cal", "dayOfWeek:       $dayOfWeek")

    return dayOfWeek
}

fun isSameMonthAndYear(date1: Date, date2: Date): Boolean {
    val cal1 = Calendar.getInstance()
    cal1.time = date1

    val cal2 = Calendar.getInstance()
    cal2.time = date2

    val format = SimpleDateFormat("MM-yyyy", Locale.ITALY)

    val d1 = format.format(cal1.time).lowercase()

    val d2 = format.format(cal2.time).lowercase()

    return d1 == d2
}