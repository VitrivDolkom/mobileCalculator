package com.example.calculator

class CalculateExpression {

    fun calculate(actionSymbol: Char, expressionText1: String, expressionText2: String): Int {
        val expression1 = expressionText1.toInt()
        val expression2 = expressionText2.toInt()

        when(actionSymbol) {
            '+' -> return expression1 + expression2
        }

        return expression1
    }
}

