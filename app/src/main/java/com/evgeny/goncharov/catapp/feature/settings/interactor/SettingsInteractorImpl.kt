package com.evgeny.goncharov.catapp.feature.settings.interactor

import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.common.theme.manager.IThemeManager
import com.evgeny.goncharov.catapp.feature.settings.repository.ISettingsRepository
import javax.inject.Inject

class SettingsInteractorImpl @Inject constructor(
    private val repository: ISettingsRepository,
    private val themeManager: IThemeManager,
    private val mainRouter: IMainRouter
) : ISettingsInteractor {


    override fun clickBack() {
        mainRouter.onBackPressed()
    }


}