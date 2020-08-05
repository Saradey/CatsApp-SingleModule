package com.evgeny.goncharov.catapp.feature.splash.screen.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.feature.splash.screen.router.SplashScreenRouter
import kotlinx.android.synthetic.main.fragment_splash_screen.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject

class SplashScreenFragment : Fragment() {

    companion object {
        fun getInstance() = SplashScreenFragment().apply {
            ActivitySubcomponent.component.inject(this)
        }
    }

    private val mainScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    @Inject
    lateinit var router: SplashScreenRouter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        init(view)
        return view
    }


    private fun init(content: View) {
        initFountSplashScreenTitle(content)
        animationView(content)
    }


    private fun initFountSplashScreenTitle(content: View) {
        val typeFace = Typeface.createFromAsset(activity?.assets, "19144.ttf")
        content.txvTitle.typeface = typeFace
    }


    private fun animationView(content: View) {
        mainScope.launch {
            startAnimation(content)
            delay(2500)
            goToTheNextFragment()
        }
    }


    private fun startAnimation(content: View) {
        val animationShow = AnimationUtils.loadAnimation(activity, R.anim.show_logo)
        content.imvCat.startAnimation(animationShow)
        content.txvTitle.startAnimation(animationShow)
    }


    private fun goToTheNextFragment() {
        router.gotoTheWallCatFragment()
    }
}