package com.evgeny.goncharov.catapp.feature.settings.di

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.settings.ui.view.SettingsViewImpl
import com.evgeny.goncharov.catapp.feature.settings.view.model.SettingsViewModelImpl
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SettingsProvideModule::class,
        SettingsBindModule::class
    ]
)
@FragmentScope
interface SettingsSubcomponent {

    fun inject(settingsViewModelImpl: SettingsViewModelImpl)

    fun inject(view: SettingsViewImpl)


    @Subcomponent.Factory
    interface Factory {
        fun plus(): SettingsSubcomponent
    }

}