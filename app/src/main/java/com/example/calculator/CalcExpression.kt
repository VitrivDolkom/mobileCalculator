package com.example.calculator

import kotlin.math.floor

class CalcExpression(private val errorMessage: String) {
    private var firstOperandSign: Operation? = null
    private var firstOperand: String = ""
    private var secondOperand: String = ""
    private var expressionOperation: Operation? = null
    private var isError = false
    
    private fun calculate(): String {
        var firstNumber = firstOperand.toDouble()
        val secondNumber = secondOperand.toDouble()
        var result = 0.0
        
        if (firstOperandSign != null) {
            firstNumber *= -1
        }
        
        when (expressionOperation) {
            Operation.MULTIPLICATION -> result = firstNumber * secondNumber
            Operation.PLUS -> result = firstNumber + secondNumber
            Operation.MINUS -> result = firstNumber - secondNumber
            Operation.DIVIDE -> result = firstNumber / secondNumber
            Operation.PERCENT -> result = firstNumber % secondNumber
            else -> isError = true
        }
        
        if (result.toInt().toDouble() == result) {
            return result.toInt().toString()
        }
        
        val roundedResult = floor(result * 100.0) / 100.0
        return roundedResult.toString()
    }
    
    private fun getExpression(): String {
        var expression = ""
        if (firstOperandSign != null) {
            expression += firstOperandSign!!.Symbol
        }
        
        expression += firstOperand
        
        if (expressionOperation != null) {
            expression += expressionOperation!!.Symbol + secondOperand
        }
        
        return expression
    }
    
    private fun onNumberOperation(operation: Operation) {
        if (expressionOperation == null) {
            firstOperand += operation.Symbol
        } else {
            secondOperand += operation.Symbol
        }
    }
    
    private fun onMathOperation(expression: String?, operation: Operation) {
        if (expression == "" || expression == null || isError || ((expressionOperation != null) && secondOperand == "" && operation == Operation.EQUAL)) {
            isError = true // if (isError == true) => extra assignment, but i don`t want to make another if () ...
            return
        }
        
        // change expression operation
        if ((expressionOperation != null) && secondOperand == "") {
            expressionOperation = operation
            return
        }
        
        // change the sign of the first operand
        if (operation == Operation.PLUS_MINUS) {
            firstOperandSign = if (firstOperandSign == null) Operation.MINUS else null
            return
        }
        
        // add operation
        if (expressionOperation == null) {
            expressionOperation = operation
            return
        }
        
        // calculate new expression
        val result = calculate()
        resetExpression()
        
        if (result[0] == Operation.MINUS.Symbol) {
            firstOperandSign = Operation.MINUS
            firstOperand = (-result.toDouble()).toString()
        } else {
            firstOperandSign = null
            firstOperand = result
        }
        
        expressionOperation = if (operation != Operation.EQUAL) operation else null
    }
    
    fun resetExpression() {
        firstOperandSign = null
        firstOperand = ""
        secondOperand = ""
        expressionOperation = null
        isError = false
    }
    
    fun eraseSymbol(): String {
        if (secondOperand != "") {
            secondOperand = secondOperand.replaceFirst(".$".toRegex(), "")
        } else if (expressionOperation != null) {
            expressionOperation = null
        } else if (firstOperand != "") {
            firstOperand = firstOperand.replaceFirst(".$".toRegex(), "")
        } else if (firstOperandSign != null) {
            firstOperandSign = null
        }
        
        return getExpression()
    }
    
    fun getValidatedExpression(expression: String?, operation: Operation): String {
        if (operation.isNumber()) {
            onNumberOperation(operation)
        } else {
            onMathOperation(expression, operation)
        }
        
        if (isError) {
            return errorMessage
        }
        
        isError = false
        return getExpression()
    }
}
