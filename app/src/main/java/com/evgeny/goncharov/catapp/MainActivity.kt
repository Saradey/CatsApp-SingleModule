package com.evgeny.goncharov.catapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var component: ActivitySubcomponent
    }

    @Inject
    lateinit var factory: ActivitySubcomponent.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.component.inject(this)
        component = factory.plus(this)
    }


}

