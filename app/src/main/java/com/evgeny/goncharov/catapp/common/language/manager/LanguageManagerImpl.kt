package com.evgeny.goncharov.catapp.common.language.manager

import android.content.Context
import android.os.Build
import com.evgeny.goncharov.catapp.consts.TAG_APPLICATION_CONTEXT
import javax.inject.Inject
import javax.inject.Named

class LanguageManagerImpl @Inject constructor(
    @Named(TAG_APPLICATION_CONTEXT) val context: Context
) : ILanguageManager {


    override fun getAppLanguage(): String {
        val systemLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0]
        } else {
            context.resources.configuration.locale
        }
        return systemLocale.language
    }


}