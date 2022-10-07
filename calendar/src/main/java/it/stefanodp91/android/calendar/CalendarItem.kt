package it.stefanodp91.android.calendar

/**
 * Created by Stefano De Pascalis on 04/10/22.
 */
class CalendarItem(
    var label: String,
    var defaultViewType: ViewType,
    var viewType: ViewType = defaultViewType
)

enum class ViewType(var asInt: Int) {
    DAY_OF_MONTH(1),
    CURRENT_DAY(2),
    DAY_OF_WEEK(3),
    SELECTED_DAY(4),
    MOCK(5)
}