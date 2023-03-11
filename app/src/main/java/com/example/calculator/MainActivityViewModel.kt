package com.example.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _currentExpression = MutableLiveData<String>()
    val currentExpression: LiveData<String> get() = _currentExpression
    
    private val calculateExpression = CalculateExpression()
    
    fun changeExpression(operation: Operation) {
        if (operation == Operation.RESET) {
            _currentExpression.value = ""
            return
        }
        
        _currentExpression.value = calculateExpression.getValidatedExpression(currentExpression.value, operation)
    }
}