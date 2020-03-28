package com.evgeny.goncharov.catapp.di.components

import com.evgeny.goncharov.catapp.App
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.di.module.AppBindsModule
import com.evgeny.goncharov.catapp.di.module.CreateSubActivityComponent
import com.evgeny.goncharov.catapp.di.module.DatabaseModule
import com.evgeny.goncharov.catapp.di.module.RestModule
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

    fun inject(mainActivity: MainActivity)


    @Component.Factory
    interface Factory {
        fun plus(@BindsInstance app: App): AppComponent
    }

}