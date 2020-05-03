package com.evgeny.goncharov.catapp.common.navigation

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.consts.KEY_BUNDLE_CAT_ID
import com.evgeny.goncharov.catapp.consts.TAG_APPLICATION_CONTEXT
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named

class MainRouterImpl @Inject constructor(
    private val navigation: INavigation,
    @Named(TAG_APPLICATION_CONTEXT) val context: Context
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
                Toast.makeText(
                    context,
                    R.string.back_pressed_common,
                    Toast.LENGTH_SHORT
                ).show()
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
}