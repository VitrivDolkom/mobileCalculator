package com.example.calculator

class CalculateExpression {
    companion object {
        val numberOperations = arrayOf(
            Operation.ZERO,
            Operation.ONE,
            Operation.TWO,
            Operation.THREE,
            Operation.FOUR,
            Operation.FIVE,
            Operation.SIX,
            Operation.SEVEN,
            Operation.EIGHT,
            Operation.NINE
        )
        
        val mathOperations = arrayOf(
            Operation.PLUS,
            Operation.MINUS,
            Operation.DIVIDE,
            Operation.PERCENT,
            Operation.MULTIPLICATION,
            Operation.COMMA,
            Operation.EQUAL
        )
        
        const val errorMessage = "Error"
    }
    
    private var firstOperandSign: Operation? = null
    private var firstOperand = ""
    private var secondOperand = ""
    private var expressionOperation: Operation? = null
    private var isError = false
    
    private fun isActionInExpression(expression: String): Boolean {
        val regex = ".*[+\\-*%±×÷].*".toRegex()
        return regex.containsMatchIn(expression)
    }
    
    private fun calculate(expression: String) {
    }
    
    private fun getExpression(): String {
        var expression = ""
        if (firstOperandSign != null) {
            expression += firstOperandSign.toString()
        }
        
        expression += firstOperand
        
        if (expressionOperation != null) {
            expression += expressionOperation.toString() + secondOperand
        }
        
        return expression
    }
    
    private fun changeFirstOperandSign() {
        if (firstOperandSign == null) {
            firstOperandSign = Operation.MINUS
            return
        }
        
        firstOperandSign = null
    }
    
    private fun onNumberOperation(expression: String?, operation: Operation) {
        if (expressionOperation == null) {
            firstOperand += operation.Symbol
        } else {
            secondOperand += operation.Symbol
        }
    }
    
    private fun onMathOperation(expression: String?, operation: Operation) {
        if (expression == null) {
            isError = true
            return
        }
        
        if (expressionOperation != null && expression.indexOf(expressionOperation.toString()) == (expression.length - 1)) {
            expressionOperation = operation
            return
        }
        
        if (operation == Operation.PLUS_MINUS) {
            changeFirstOperandSign()
            return
        }
    }
    
    fun getValidatedExpression(expression: String?, operation: Operation): String {
        if (operation in numberOperations) {
            onNumberOperation(expression, operation)
        } else {
            onMathOperation(expression, operation)
        }
        
        if (isError) {
            return errorMessage;
        }
        
        return getExpression()
    }
}
