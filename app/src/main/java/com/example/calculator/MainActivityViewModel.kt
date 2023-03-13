package com.example.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _currentExpression = MutableLiveData<String>()
    val currentExpression: LiveData<String> get() = _currentExpression
    
    private val calcExpression = CalcExpression()
    
    fun changeExpression(operation: Operation) {
        if (operation == Operation.RESET) {
            _currentExpression.value = ""
            calcExpression.resetExpression();
            return
        }
        
        _currentExpression.value = calcExpression.getValidatedExpression(currentExpression.value, operation)
    }
}