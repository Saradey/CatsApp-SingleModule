package com.evgeny.goncharov.catapp.di.components

import com.evgeny.goncharov.catapp.App
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.di.module.app.AppBindsModule
import com.evgeny.goncharov.catapp.di.module.app.CreateSubActivityComponent
import com.evgeny.goncharov.catapp.di.module.app.DatabaseModule
import com.evgeny.goncharov.catapp.di.module.app.RestModule
import com.evgeny.goncharov.catapp.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        DatabaseModule::class,
        RestModule::class,
        CreateSubActivityComponent::class,
        AppBindsModule::class
    ]
)
interface AppComponent {

    companion object {
        lateinit var component: AppComponent

        fun init(app: App): AppComponent {
            component = DaggerAppComponent.factory()
                .plus(app)
            return component
        }
    }

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun plus(@BindsInstance app: App): AppComponent
    }
}