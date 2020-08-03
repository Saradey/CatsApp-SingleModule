package com.evgeny.goncharov.catapp.common.navigation


import android.os.Bundle
import com.evgeny.goncharov.catapp.consts.KEY_BUNDLE_CAT_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainRouterImpl @Inject constructor(
    private val navigation: INavigation
) : IMainRouter {

    private var countBackPressed = 0

    override fun showSlashScreen() {
        navigation.goTo(Destination.SplashScreen)
    }

    private val coroutineMain = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onBackPressed() {
        if (navigation.getNowMatchFromStack() <= 1) {
            onBackPressedToastShow()
        } else {
            navigation.popStack()
        }
    }


    private fun onBackPressedToastShow() {
        coroutineMain.launch {
            if (countBackPressed == 1) {
                navigation.appFinish()
            } else {
                navigation.showBackPressedWarning()
            }
            countBackPressed++
            delay(2000)
            countBackPressed = 0
        }
    }


    override fun showCatDescription(idCat: String) {
        val bundle = Bundle()
        bundle.putString(KEY_BUNDLE_CAT_ID, idCat)
        navigation.goTo(Destination.CatDescription, bundle)
    }


    override fun goToTheSearchCatFragment() {
        navigation.goTo(Destination.CatSearchScreen)
    }


    override fun goToTheSettingFragment() {
        navigation.goTo(Destination.SettingsScreen)
    }
}