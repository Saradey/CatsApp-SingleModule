package com.evgeny.goncharov.catapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evgeny.goncharov.catapp.common.ActivityLifeCycle
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.common.navigation.INavigation
import com.evgeny.goncharov.catapp.common.theme.manager.IThemeManager
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var component: ActivitySubcomponent
    }

    @Inject
    lateinit var factory: ActivitySubcomponent.Factory

    @Inject
    lateinit var navigation: INavigation

    @Inject
    lateinit var themeManager: IThemeManager

    @Inject
    lateinit var router: IMainRouter


    override fun onCreate(savedInstanceState: Bundle?) {
        initDaggerGraph()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLifeCycle()
        navigation.attachActivity(this)
        if (savedInstanceState == null) {
            router.showSlashScreen()
        }
    }


    private fun initDaggerGraph() {
        App.component.inject(this)
        component = factory.plus(this)
    }


    private fun initLifeCycle() {
        val cycle = ActivityLifeCycle(this, navigation)
        lifecycle.addObserver(cycle)
    }


    override fun onBackPressed() {
        router.onBackPressed()
    }


}

