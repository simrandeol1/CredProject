package com.example.credproject.controller

import com.example.credproject.viewmodel.StackViewModel
import com.example.credproject.constants.State

class ExpandableViewController(private val viewModel: StackViewModel) {

    fun setEditTextValue(value: String){
        viewModel.setEditTextValue(value)
    }

    fun setRadioValue(value: String){
        viewModel.setRadioValue(value)
    }

    fun setStateValue(value: State){
        viewModel.setStateValue(value)
    }
}