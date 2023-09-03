package com.example.credproject.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.example.credproject.controller.ExpandableViewController
import com.example.credproject.R
import com.example.credproject.constants.State
import com.google.android.material.snackbar.Snackbar

//image view
class ExpandableViewWithImage(context: Context, attrs: AttributeSet?, val controller: ExpandableViewController) : ExpandableView(context, attrs){

    private val parentCustomView = LayoutInflater.from(context).inflate(R.layout.layout_with_image, null)

    init {
        addView(parentCustomView)
        parentCustomView.findViewById<LinearLayout>(R.id.collapsable_view).visibility = View.GONE
        // Set the initial state of the view to collapsed.
        state = State.NoneExpanded

        initViews()
    }

    private fun initViews(){
        parentCustomView.findViewById<Button>(R.id.cta).setOnClickListener {
            val snackbar = Snackbar.make(this, "Money Sent", Snackbar.LENGTH_SHORT)
            // Show the Snackbar
            snackbar.show()
        }
    }
    override fun onExpand() {
        // Show the collapsable view.
        state = State.ImageExpanded
        controller.setStateValue(State.ImageExpanded)
        parentCustomView.findViewById<LinearLayout>(R.id.collapsable_view).visibility = View.VISIBLE
    }

    override fun onCollapse() {
        // Hide the collapsable view.
        state = State.NoneExpanded
        parentCustomView.findViewById<LinearLayout>(R.id.collapsable_view).visibility = View.GONE
    }

    fun toggle() {
        if (state == State.ImageExpanded) {
            onCollapse()
        } else {
            onExpand()
            controller.setStateValue(State.ImageExpanded)
        }
    }
}