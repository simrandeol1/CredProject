package com.example.credproject

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.credproject.constants.State
import com.example.credproject.controller.ExpandableViewController
import com.example.credproject.customviews.ExpandableViewWithEdittext
import com.example.credproject.customviews.ExpandableViewWithImage
import com.example.credproject.customviews.ExpandableViewWithRadio
import com.example.credproject.viewmodel.StackViewModel

class MainActivity: AppCompatActivity() {

    private var prevState = State.NoneExpanded

    private lateinit var expandableViewWithEdittext: ExpandableViewWithEdittext
    private lateinit var expandableViewWithRadio: ExpandableViewWithRadio
    private lateinit var expandableViewWithTextAndImage: ExpandableViewWithImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this)[StackViewModel::class.java]

        val controller = ExpandableViewController(viewModel)

        expandableViewWithEdittext = ExpandableViewWithEdittext(this, null, controller)

        viewModel.editTextValue.observe(this) { text ->
            // Update the text of the view.
            expandableViewWithEdittext.textView.text = "You will Pay: $text"
        }

        controller.setStateValue(State.NoneExpanded)

        val cardViewParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        cardViewParams.setMargins(5, -30, 5, 0)
        expandableViewWithEdittext.layoutParams = cardViewParams

        expandableViewWithEdittext.setOnClickListener {
            if(prevState != State.EditTextExpanded)
                collapseOthers()
            expandableViewWithEdittext.toggle()
        }

        findViewById<LinearLayout>(R.id.main).addView(expandableViewWithEdittext, cardViewParams)

        expandableViewWithRadio = ExpandableViewWithRadio(this, null, controller)

        expandableViewWithRadio.setOnClickListener {
            if(prevState != State.RadioExpanded)
                collapseOthers()
            expandableViewWithRadio.toggle()
        }

        viewModel.radioValue.observe(this) { text ->
            // Update the text of the view.
            expandableViewWithRadio.textView.text = "Selected Bank: $text"
        }

        viewModel.expandState.observe(this) {
            prevState = it
        }

        findViewById<LinearLayout>(R.id.main).addView(expandableViewWithRadio, cardViewParams)

        expandableViewWithTextAndImage = ExpandableViewWithImage(this, null, controller)

        expandableViewWithTextAndImage.setOnClickListener {
            if(prevState != State.ImageExpanded)
                collapseOthers()
            expandableViewWithTextAndImage.toggle()
        }

        findViewById<LinearLayout>(R.id.main).addView(expandableViewWithTextAndImage, cardViewParams)

    }

    private fun collapseOthers(){
        when(prevState){
            State.RadioExpanded ->{
                expandableViewWithRadio.onCollapse()
            }
            State.ImageExpanded->{
                expandableViewWithTextAndImage.onCollapse()
            }
            State.EditTextExpanded->{
                expandableViewWithEdittext.onCollapse()
            }
            else -> {
            }

        }
    }
}