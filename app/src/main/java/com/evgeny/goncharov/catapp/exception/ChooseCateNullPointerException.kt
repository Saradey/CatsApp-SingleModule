package com.evgeny.goncharov.catapp.exception

class ChooseCateNullPointerException : Exception(ERROR_MESSAGE) {
    companion object {
        const val ERROR_MESSAGE = "Cat choose is bad choose"
    }
}