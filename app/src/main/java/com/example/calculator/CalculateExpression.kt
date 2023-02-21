package com.example.calculator

class CalculateExpression() {
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
        var result = expressionText
        
        if (newSymbol in tabooActions || (result == "" && newSymbol in availActions) || (result == "Error" && newSymbol in availActions)) {
            return "Error"
        }
        
        if (result == "Error") {
            return newSymbol
        } else if (result == "") {
            return result + newSymbol
        }
        
        val lastSymbol = result[result.count() - 1].toString()
        if (lastSymbol in availActions && newSymbol in availActions) {
            return result.toString().replaceFirst(".$".toRegex(), "") + newSymbol
        }
        
        if (newSymbol == "=") {
            return calculate(result)
        } else if (newSymbol in availActions && isActionInExpression(expressionText)) {
            return calculate(result) + newSymbol
        }
        
        
        return result + newSymbol
    }
}
