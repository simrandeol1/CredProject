package com.example.credproject.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.credproject.controller.ExpandableViewController
import com.example.credproject.R
import com.example.credproject.constants.State

// radio view
class ExpandableViewWithRadio(context: Context, attrs: AttributeSet?, private val controller: ExpandableViewController) : ExpandableView(context, attrs){

    private val parentCustomView = LayoutInflater.from(context).inflate(R.layout.layout_with_radio, null)
    private lateinit var radioGroup: RadioGroup
    lateinit var textView: TextView

    init {
        addView(parentCustomView)
        parentCustomView.findViewById<LinearLayout>(R.id.collapsable_view).visibility = View.GONE
        // Set the initial state of the view to collapsed.
        state = State.NoneExpanded

        initViews()
    }

    private fun initViews(){
        radioGroup = parentCustomView.findViewById(R.id.radioGroup)
        textView = parentCustomView.findViewById(R.id.text_view)

        parentCustomView.findViewById<Button>(R.id.cta).setOnClickListener {

            val radioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            // Get the text of the radio button.
            val radioButtonText = radioButton.text.toString()
            controller.setRadioValue(radioButtonText)
        }
    }

    override fun onExpand() {
        // Show the collapsable view.
        state = State.RadioExpanded
        controller.setStateValue(State.RadioExpanded)
        parentCustomView.findViewById<LinearLayout>(R.id.collapsable_view).visibility = View.VISIBLE
    }

    override fun onCollapse() {
        // Hide the collapsable view.
        state = State.NoneExpanded
        parentCustomView.findViewById<LinearLayout>(R.id.collapsable_view).visibility = View.GONE
    }

     fun toggle() {
         if (state == State.RadioExpanded) {
            onCollapse()
        } else {
            onExpand()
        }
    }

}