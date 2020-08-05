package com.evgeny.goncharov.catapp.feature.settings.di

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.settings.interactor.SettingsInteractor
import com.evgeny.goncharov.catapp.feature.settings.interactor.SettingsInteractorImpl
import com.evgeny.goncharov.catapp.feature.settings.gateway.SettingsGateway
import com.evgeny.goncharov.catapp.feature.settings.gateway.SettingsGatewayImpl
import dagger.Binds
import dagger.Module

@Module
interface SettingsBindModule {

    @Binds
    @FragmentScope
    fun bindSettingsRepository(repository: SettingsGatewayImpl): SettingsGateway

    @Binds
    @FragmentScope
    fun bindSettingsInteractor(interactor: SettingsInteractorImpl): SettingsInteractor

}