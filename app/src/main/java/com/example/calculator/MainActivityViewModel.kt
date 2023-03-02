package com.example.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var expression = ""
    
    val currentExpression: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}