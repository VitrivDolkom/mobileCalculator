package com.example.calculator

enum class Operation(val symbol: Char) {
    ZERO('0'), ONE('1'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'), PLUS(
        '+'
    ),
    MINUS('-'), DIVIDE('÷'), PERCENT('%'), PLUS_MINUS('±'), MULTIPLICATION('×'), COMMA('.'), EQUAL('='), RESET('A');
    
    fun isSign(): Boolean = when (this) {
        PLUS, MINUS, DIVIDE, PERCENT, MULTIPLICATION, EQUAL -> true
        else -> false
    }
    
    fun isNumber(): Boolean = when (this) {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, COMMA -> true
        else -> false
    }
}
