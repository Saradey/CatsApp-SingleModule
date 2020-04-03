package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseViewImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.IWallCatsViewModel
import kotlinx.android.synthetic.main.toolbar.view.*
import javax.inject.Inject

class WallCatsViewImpl : BaseViewImpl(),
    IWallCatsView {

    @Inject
    lateinit var viewModel: IWallCatsViewModel


    override fun init() {
        initUi()
        WallCatsFragment.component.inject(this)
        initLiveData()
//        viewModel.initWallCat()
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
        viewModel.getCatWallLiveData().observeForever {
            //TODO сюда сетаем в адаптер
        }
    }
}