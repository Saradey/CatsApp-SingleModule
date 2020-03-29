package com.evgeny.goncharov.catapp.feature.wall.cats.ui

import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseViewImpl
import kotlinx.android.synthetic.main.toolbar.view.*

class WallCatsViewImpl : BaseViewImpl(), IWallCatsView {


    override fun init() {
        initUi()
    }


    private fun initUi() {
        initToolbar()
    }


    private fun initToolbar() {
        content?.apply {
            toolbar.setTitle(R.string.wall_cat_toolbar_title)
            toolbar.inflateMenu(R.menu.menu_wall_cats)
            toolbar.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.menuSearchCat -> {

                        true
                    }
                    R.id.menuSettings -> {

                        true
                    }
                    else -> {

                        false
                    }
                }
            }
        }
    }


    override fun initLiveData() {

    }
}