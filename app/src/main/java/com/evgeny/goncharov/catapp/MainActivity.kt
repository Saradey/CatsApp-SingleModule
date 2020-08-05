package com.evgeny.goncharov.catapp

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evgeny.goncharov.catapp.common.ActivityLifeCycle
import com.evgeny.goncharov.catapp.common.language.manager.LanguageManager
import com.evgeny.goncharov.catapp.common.navigation.MainRouter
import com.evgeny.goncharov.catapp.common.navigation.Navigation
import com.evgeny.goncharov.catapp.common.theme.manager.ThemeManager
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.di.components.AppComponent
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ActivitySubcomponent.Factory

    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var themeManager: ThemeManager

    @Inject
    lateinit var router: MainRouter

    @Inject
    lateinit var languageManager: LanguageManager

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

