package com.example.credproject.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.example.credproject.constants.State

// abstract layer for stack framework
abstract class ExpandableView(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    var state = State.NoneExpanded

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var h = heightMeasureSpec
        if (state != State.NoneExpanded) {
            h = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        }

        for (view in children) {
            view.measure(widthMeasureSpec, h)
        }

        val height = children.sumOf { it.measuredHeight }

        setMeasuredDimension(widthMeasureSpec, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var y = 0
        for (view in children) {
            view.layout(left, y, right, y + view.measuredHeight)
            y += view.measuredHeight
        }
    }

    abstract fun onExpand()

    abstract fun onCollapse()

}