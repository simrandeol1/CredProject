package com.example.credproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.credproject.constants.State

class StackViewModel: ViewModel() {

    private var _editTextValue = MutableLiveData<String>()
    val editTextValue: LiveData<String>
        get() = _editTextValue

    private var _radioValue = MutableLiveData<String>()

    val radioValue: LiveData<String>
        get() = _radioValue

    private var _expandState = MutableLiveData<State>()
    val expandState: LiveData<State>
        get() = _expandState

    fun setEditTextValue(value: String){
        _editTextValue.value = value
    }

    fun setRadioValue(value: String){
        _radioValue.value = value
    }

    fun setStateValue(value: State){
        _expandState.value = value
    }
}