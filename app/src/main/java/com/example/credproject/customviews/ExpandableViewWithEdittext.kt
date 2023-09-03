package com.example.credproject.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.credproject.controller.ExpandableViewController
import com.example.credproject.R
import com.example.credproject.constants.State

// edittext view
class ExpandableViewWithEdittext(context: Context, attrs: AttributeSet?, private val controller: ExpandableViewController) : ExpandableView(context, attrs){

    private val parentCustomView = LayoutInflater.from(context).inflate(R.layout.layout_with_edittext, null)
    private lateinit var editText: EditText
    lateinit var textView: TextView

    init {

        addView(parentCustomView)
        parentCustomView.findViewById<LinearLayout>(R.id.collapsable_view).visibility = View.GONE

        // Set the initial state of the view to collapsed.
        state = State.NoneExpanded

        initViews()

    }

    private fun initViews(){
        editText = parentCustomView.findViewById(R.id.edittext_view)
        textView = parentCustomView.findViewById(R.id.text_view)
        parentCustomView.findViewById<Button>(R.id.cta).setOnClickListener {
            controller.setEditTextValue(editText.text.toString())
        }
    }

    override fun onExpand() {
        // Show the collapsable view.
        state = State.EditTextExpanded
        controller.setStateValue(State.EditTextExpanded)
        parentCustomView.findViewById<LinearLayout>(R.id.collapsable_view).visibility = View.VISIBLE
    }

    override fun onCollapse() {
        // Hide the collapsable view.
        state = State.NoneExpanded
        parentCustomView.findViewById<LinearLayout>(R.id.collapsable_view).visibility = View.GONE
    }

    fun toggle() {
        if (state == State.EditTextExpanded) {
            onCollapse()
        } else {
            onExpand()
            controller.setStateValue(State.EditTextExpanded)
        }
    }
}