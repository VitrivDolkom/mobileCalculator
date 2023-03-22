package com.example.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    companion object {
        const val errorMessage = "Error"
    }
    
    private val _currentExpression = MutableLiveData<String>()
    val currentExpression: LiveData<String> get() = _currentExpression
    
    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError
    
    private val calcExpression = CalcExpression(errorMessage)
    
    fun onEraseButtonClick() {
        calcExpression.eraseSymbol()
        _currentExpression.value = calcExpression.fullExpression
    }
    
    fun changeExpression(operation: Operation) {
        if (operation == Operation.RESET) {
            calcExpression.resetExpression()
            _currentExpression.value = calcExpression.fullExpression
            return
        }
        
        calcExpression.getValidatedExpression(currentExpression.value, operation)
        _currentExpression.value = calcExpression.fullExpression
        _isError.value = _currentExpression.value == errorMessage
    }
}