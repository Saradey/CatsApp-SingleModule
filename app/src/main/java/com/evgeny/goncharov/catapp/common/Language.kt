package com.evgeny.goncharov.catapp.common

import com.evgeny.goncharov.catapp.consts.EN_CODE
import com.evgeny.goncharov.catapp.consts.RU_CODE

sealed class Language {

    data class RU(val code: String = RU_CODE) : Language()

    data class EN(val code: String = EN_CODE) : Language()

}