package com.evgeny.goncharov.catapp.di.components

import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.di.module.ActivityBindsModule
import com.evgeny.goncharov.catapp.di.module.CreateSubFragmentComponents
import com.evgeny.goncharov.catapp.di.scope.ActivityScope
import dagger.BindsInstance
import dagger.Subcomponent


@ActivityScope
@Subcomponent(
    modules = [
        ActivityBindsModule::class,
        CreateSubFragmentComponents::class
    ]
)
interface ActivitySubcomponent {


    @Subcomponent.Factory
    interface Factory {
        fun plus(@BindsInstance activity: MainActivity): ActivitySubcomponent
    }

}