package com.evgeny.goncharov.catapp.feature.wall.cats.ui

import android.view.View
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.di.component.WallCatsSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.IWallCatsView
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.WallCatsViewImpl
import javax.inject.Inject

class WallCatsFragment : BaseFragment<IWallCatsView>() {

    @Inject
    lateinit var factory: WallCatsSubcomponent.Factory

    init {
        MainActivity.component.inject(this)
    }


    companion object {
        fun getInstance() = WallCatsFragment()

        lateinit var component: WallCatsSubcomponent
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_wall_cats
    }


    override fun init(content: View) {
        component = factory.plus(this)
        initView(content)
        view.init()
    }


    override fun initView(content: View) {
        view = WallCatsViewImpl()
        view.attachView(content)
    }

}