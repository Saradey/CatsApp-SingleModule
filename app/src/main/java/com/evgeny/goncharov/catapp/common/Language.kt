package com.evgeny.goncharov.catapp.common

import com.evgeny.goncharov.catapp.consts.EN_CODE
import com.evgeny.goncharov.catapp.consts.RU_CODE


enum class Language(
    val code: String
) {
    RU(RU_CODE),
    EN(EN_CODE)
}