package it.stefanodp91.android.calendar

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager


/**
 * Created by Stefano De Pascalis on 04/10/22.
 */
class CustomGridLayoutManager(context: Context?, spanCount: Int) :
    GridLayoutManager(context, spanCount) {
    private var isScrollEnabled = true

    fun setScrollEnabled(flag: Boolean) {
        isScrollEnabled = flag
    }

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }
}