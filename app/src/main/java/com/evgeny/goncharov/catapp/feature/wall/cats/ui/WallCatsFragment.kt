package com.evgeny.goncharov.catapp.feature.wall.cats.ui

import android.view.View
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.di.component.WallCatsSubcomponent
import javax.inject.Inject

class WallCatsFragment : BaseFragment<IWallCatsView>() {

    @Inject
    lateinit var factory: WallCatsSubcomponent.Factory


    companion object {
        fun getInstance() = WallCatsFragment()
            .apply {
                MainActivity.component.inject(this)
            }

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