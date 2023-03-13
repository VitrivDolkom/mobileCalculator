package com.example.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    companion object {
        const val errorMessage = "Error"
    }
    
    private val _currentExpression = MutableLiveData<String>()
    val currentExpression: LiveData<String> get() = _currentExpression
    
    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError
    
    private val calcExpression = CalcExpression(errorMessage)
    
    fun onEraseButtonClick() {
        _currentExpression.value = calcExpression.eraseSymbol()
    }
    
    fun changeExpression(operation: Operation) {
        if (operation == Operation.RESET) {
            _currentExpression.value = ""
            calcExpression.resetExpression()
            return
        }
        
        _currentExpression.value = calcExpression.getValidatedExpression(currentExpression.value, operation)
        _isError.value = _currentExpression.value == errorMessage
    }
}