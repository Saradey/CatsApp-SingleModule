package com.evgeny.goncharov.catapp.feature.settings.di

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.settings.interactor.ISettingsInteractor
import com.evgeny.goncharov.catapp.feature.settings.interactor.SettingsInteractorImpl
import com.evgeny.goncharov.catapp.feature.settings.gateway.ISettingsGateway
import com.evgeny.goncharov.catapp.feature.settings.gateway.SettingsGatewayImpl
import dagger.Binds
import dagger.Module

@Module
interface SettingsBindModule {

    @Binds
    @FragmentScope
    fun bindSettingsRepository(repository: SettingsGatewayImpl): ISettingsGateway

    @Binds
    @FragmentScope
    fun bindSettingsInteractor(interactor: SettingsInteractorImpl): ISettingsInteractor

}