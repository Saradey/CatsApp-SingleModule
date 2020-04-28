package com.evgeny.goncharov.catapp.feature.splash.screen.ui

import android.graphics.Typeface
import android.view.View
import android.view.animation.AnimationUtils
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.feature.splash.screen.router.ISplashScreenRouter
import kotlinx.android.synthetic.main.fragment_splash_screen.view.*
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named

class SplashScreenFragment : BaseFragment<ISplashScreen>() {

    companion object {
        fun getInstance() = SplashScreenFragment().apply {
            MainActivity.component.inject(this)
        }
    }

    val mainScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    @Inject
    lateinit var router: ISplashScreenRouter

    override fun getLayoutId(): Int {
        return R.layout.fragment_splash_screen
    }


    override fun init(content: View) {
        initFountSplashScreenTitle(content)
        animationView(content)
    }


    private fun initFountSplashScreenTitle(content: View) {
        val typeFace = Typeface.createFromAsset(activity?.assets, "19144.ttf")
        content.txvTitle.typeface = typeFace
    }


    private fun animationView(content: View) {
        mainScope.launch {
//            startAnimation(content)
//            delay(2500)
            goToTheNextFragment()
        }
    }


    private suspend fun startAnimation(content: View) {
        val animationShow = AnimationUtils.loadAnimation(activity, R.anim.show_logo)
        content.imvCat.startAnimation(animationShow)
        content.txvTitle.startAnimation(animationShow)
    }


    private suspend fun goToTheNextFragment() {
        router.gotoTheWallCatFragment()
    }

    override fun initView(content: View) {}

}