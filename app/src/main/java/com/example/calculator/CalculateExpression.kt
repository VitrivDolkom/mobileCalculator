package com.example.calculator

class CalculateExpression {
    private val availActions = arrayOf("+", "-", "÷", "%", "×")
    private val tabooActions = arrayOf("AC", "±", ",")
    
    private fun isActionInExpression(expression: String): Boolean {
        for (a in expression) {
            for (b in availActions) {
                if (a.toString() == b) {
                    return true
                }
            }
        }
        
        return false
    }
    
    private fun calculate(expression: String): String {
        var actionSymbol = '+'
        var firstNumber = ""
        var secondNumber = ""
        var isActionFound = false
        
        for (symbol in expression) {
            if (symbol.toString() in availActions) {
                actionSymbol = symbol
                isActionFound = true
            } else if (isActionFound) {
                secondNumber += symbol
            } else {
                firstNumber += symbol
            }
        }
        
        if (actionSymbol.toString() == "÷" && secondNumber == "0") {
            return "Error"
        }
        
        var result = 0
        
        when (actionSymbol.toString()) {
            "+" -> result = firstNumber.toInt() + secondNumber.toInt()
            "-" -> result = firstNumber.toInt() - secondNumber.toInt()
            "%" -> result = firstNumber.toInt() % secondNumber.toInt()
            "×" -> result = firstNumber.toInt() * secondNumber.toInt()
            "÷" -> result = firstNumber.toInt() / secondNumber.toInt()
        }
        
        return result.toString()
    }
    
    fun validatedExpressionWithNewSymbol(newSymbol: String, expressionText: String): String {
        val validExpression = expressionText
        
        if (newSymbol in tabooActions || (validExpression == "" && newSymbol in availActions) || (validExpression == "Error" && newSymbol in availActions)) {
            return "Error"
        }
        
        if (validExpression == "Error") {
            return newSymbol
        } else if (validExpression == "") {
            return validExpression + newSymbol
        }
        
        val lastSymbol = validExpression[validExpression.count() - 1].toString()
        if (lastSymbol in availActions && newSymbol in availActions) {
            return validExpression.replaceFirst(".$".toRegex(), "") + newSymbol
        }
        
        if (newSymbol !in availActions && newSymbol != "=") {
            return validExpression + newSymbol
        }
        
        if (!isActionInExpression(validExpression) && newSymbol in availActions) {
            return validExpression + newSymbol
        }
        
        val answer = calculate(validExpression)
        
        if (answer == "Error" || newSymbol == "=") {
            return answer
        }
        
        if (newSymbol in availActions && isActionInExpression(expressionText)) {
            return answer + newSymbol
        }
        
        return validExpression + newSymbol
    }
}
