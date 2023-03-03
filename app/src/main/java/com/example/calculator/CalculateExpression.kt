package com.example.calculator

import kotlin.math.floor

class CalculateExpression {
    private val availActions = arrayOf("+", "-", "÷", "%", "×")
    private val tabooActions = arrayOf("±")
    
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
        
        // get action, first and second numbers in expression
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
        
        // check divide by zero
        if (actionSymbol.toString() == "÷" && secondNumber == "0") {
            return "Error"
        }
        
        var result = 0.0
        
        // calculate expression
        when (actionSymbol.toString()) {
            "+" -> result = firstNumber.toDouble() + secondNumber.toDouble()
            "-" -> result = firstNumber.toDouble() - secondNumber.toDouble()
            "%" -> result = firstNumber.toDouble() % secondNumber.toDouble()
            "×" -> result = firstNumber.toDouble() * secondNumber.toDouble()
            "÷" -> result = firstNumber.toDouble() / secondNumber.toDouble()
        }
        
        // extra zero in double number
        if (result.toInt().toDouble() == result) {
            return result.toInt().toString()
        }
        
        val roundedResult = floor(result * 100.0) / 100.0
        
        return roundedResult.toString()
    }
    
    fun validatedExpressionWithNewSymbol(newSymbol: String, expressionText: String): String {
        
        // show error message
        if (newSymbol in tabooActions || (expressionText == "" && newSymbol in availActions) || (expressionText == "Error" && newSymbol in availActions)) {
            return "Error"
        }
        
        // show new expression if there was error or empty line
        if (expressionText == "Error") {
            return newSymbol
        } else if (expressionText == "") {
            return expressionText + newSymbol
        }
        
        // replace last action
        val lastSymbol = expressionText[expressionText.count() - 1].toString()
        if (lastSymbol in availActions && newSymbol in availActions) {
            return expressionText.replaceFirst(".$".toRegex(), "") + newSymbol
        }
        
        // add number
        if (newSymbol !in availActions && newSymbol != "=") {
            return expressionText + newSymbol
        }
        
        // add action
        if (!isActionInExpression(expressionText) && newSymbol in availActions) {
            return expressionText + newSymbol
        }
        
        // calculate
        val answer = calculate(expressionText)
        
        // print answer if error or equal action
        if (answer == "Error" || newSymbol == "=") {
            return answer
        }
        
        // print answer if new action
        if (newSymbol in availActions && isActionInExpression(expressionText)) {
            return answer + newSymbol
        }
        
        return expressionText + newSymbol
    }
}
