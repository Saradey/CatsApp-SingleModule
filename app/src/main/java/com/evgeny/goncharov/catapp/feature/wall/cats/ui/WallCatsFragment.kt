package com.evgeny.goncharov.catapp.feature.wall.cats.ui

import android.view.View
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment

class WallCatsFragment : BaseFragment<IWallCatsView>() {

    companion object {
        fun getInstance() = WallCatsFragment()
            .apply {
                //TODO inject
            }
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_wall_cats
    }


    override fun init(content: View) {
        initBaseView(content)
        initLiveData()
    }


    override fun initBaseView(content: View) {
        view = WallCatsViewImpl()
        view.attachView(content)
    }


    override fun initLiveData() {

    }


}