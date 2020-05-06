package com.evgeny.goncharov.catapp.feature.settings.di

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.settings.interactor.ISettingsInteractor
import com.evgeny.goncharov.catapp.feature.settings.interactor.SettingsInteractorImpl
import com.evgeny.goncharov.catapp.feature.settings.repository.ISettingsRepository
import com.evgeny.goncharov.catapp.feature.settings.repository.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface SettingsBindModule {

    @Binds
    @FragmentScope
    fun bindSettingsRepository(repository: SettingsRepositoryImpl): ISettingsRepository

    @Binds
    @FragmentScope
    fun bindSettingsInteractor(interactor: SettingsInteractorImpl): ISettingsInteractor

}