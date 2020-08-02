package com.evgeny.goncharov.catapp

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evgeny.goncharov.catapp.common.ActivityLifeCycle
import com.evgeny.goncharov.catapp.common.language.manager.ILanguageManager
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.common.navigation.INavigation
import com.evgeny.goncharov.catapp.common.theme.manager.IThemeManager
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.di.components.AppComponent
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ActivitySubcomponent.Factory

    @Inject
    lateinit var navigation: INavigation

    @Inject
    lateinit var themeManager: IThemeManager

    @Inject
    lateinit var router: IMainRouter

    @Inject
    lateinit var languageManager: ILanguageManager

    init {
        initDaggerGraph()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLifeCycle()
        navigation.attachActivity(this)
        savedInstanceState ?: router.showSlashScreen()
    }


    private fun initDaggerGraph() {
        AppComponent.component.inject(this)
        ActivitySubcomponent.component = factory.plus(this)
    }


    private fun initLifeCycle() {
        val cycle = ActivityLifeCycle(this, navigation)
        lifecycle.addObserver(cycle)
    }


    override fun onBackPressed() {
        router.onBackPressed()
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(applySelectedAppLanguage(base))
    }


    private fun applySelectedAppLanguage(context: Context): Context {
        val locale = languageManager.getUserSelectedLanguageBlocking()
        val config = Configuration(context.resources.configuration)
        Locale.setDefault(locale)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }


}

