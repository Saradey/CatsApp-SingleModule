package com.evgeny.goncharov.catapp.feature.settings.di

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.settings.ui.DialogChooseLanguageApp
import com.evgeny.goncharov.catapp.feature.settings.ui.DialogChooseThemeApp
import com.evgeny.goncharov.catapp.feature.settings.view.model.SettingsViewModelImpl
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SettingsBindModule::class
    ]
)
@FragmentScope
interface SettingsSubcomponent {

    companion object {
        var component: SettingsSubcomponent? = null
    }

    fun inject(settingsViewModelImpl: SettingsViewModelImpl)

    fun inject(dialog: DialogChooseThemeApp)

    fun inject(dialog: DialogChooseLanguageApp)

    @Subcomponent.Factory
    interface Factory {

        fun plus(): SettingsSubcomponent
    }
}