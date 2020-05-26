package com.evgeny.goncharov.catapp.exception

import java.lang.Exception

class ChooseCateNullPointerException : Exception(ERROR_MESSAGE) {
    companion object {
        const val ERROR_MESSAGE = "Cat choose is bad choose"
    }
}