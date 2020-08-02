package com.evgeny.goncharov.catapp.common.language.manager

import android.content.Context
import com.evgeny.goncharov.catapp.common.Language
import com.evgeny.goncharov.catapp.consts.RU_CODE
import com.evgeny.goncharov.catapp.consts.TAG_APPLICATION_CONTEXT
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class LanguageManagerImpl @Inject constructor(
    @Named(TAG_APPLICATION_CONTEXT) val context: Context
) : ILanguageManager {


    companion object {
        private const val LANGUAGE_SHARED_PREF_CODE = "LANGUAGE_SHARED_PREF_CODE"
        private const val LANGUAGE_VALUE_PREF_CODE = "LANGUAGE_VALUE_PREF_CODE"
    }


    override fun getAppLanguage(): String {
        val shared = context.getSharedPreferences(LANGUAGE_SHARED_PREF_CODE, Context.MODE_PRIVATE)
        return shared.getString(LANGUAGE_VALUE_PREF_CODE, null) ?: RU_CODE
    }


    override fun getAppLanguageEnum(): Language {
        return when (getAppLanguage()) {
            RU_CODE -> Language.RU
            else -> Language.EN
        }
    }


    override fun chooseLanguage(langCode: String) {
        val shared = context.getSharedPreferences(LANGUAGE_SHARED_PREF_CODE, Context.MODE_PRIVATE)
        val editor = shared.edit()
        editor.putString(LANGUAGE_VALUE_PREF_CODE, langCode)
        editor.apply()
    }


    override fun getUserSelectedLanguageBlocking(): Locale {
        return Locale(getAppLanguage())
    }

}