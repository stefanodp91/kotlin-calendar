package it.stefanodp91.android.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat


/**
 * Created by Stefano De Pascalis on 04/10/22.
 */


class CircularTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    private val backgroundTint: Int

    init {
        // real work here

        // real work here
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircularTextView,
            0,
            0
        )

        try {
            backgroundTint =
                a.getColor(
                    R.styleable.CircularTextView_android_backgroundTint,
                    ContextCompat.getColor(context, R.color.black)
                )
        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle()
        }
    }

    override fun draw(canvas: Canvas) {
        val circlePaint = Paint()
        circlePaint.color = backgroundTint
        circlePaint.flags = Paint.ANTI_ALIAS_FLAG
        val strokePaint = Paint()
        strokePaint.flags = Paint.ANTI_ALIAS_FLAG
        val h = this.height
        val w = this.width
        val diameter = if (h > w) h else w
        val radius = diameter / 2
        this.height = diameter
        this.width = diameter
        canvas.drawCircle(
            (diameter / 2).toFloat(),
            (diameter / 2).toFloat(),
            radius.toFloat(),
            strokePaint
        )
        canvas.drawCircle(
            (diameter / 2).toFloat(),
            (diameter / 2).toFloat(),
            radius.toFloat(),
            circlePaint
        )
        super.draw(canvas)
    }
}