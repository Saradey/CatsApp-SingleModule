package com.evgeny.goncharov.catapp.common.language.manager

import com.evgeny.goncharov.catapp.common.Language
import java.util.Locale

interface LanguageManager {

    fun getAppLanguage(): String

    fun getAppLanguageEnum(): Language

    fun chooseLanguage(langCode: String)

    fun getUserSelectedLanguageBlocking(): Locale
}