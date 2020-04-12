package com.evgeny.goncharov.catapp.feature.wall.cats.ui

import android.view.View
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.CatDescriptionSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.CatDescriptionViewImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.ICatDescriptionView
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.ICatDescriptionViewModel
import javax.inject.Inject

class CatDescriptionFragment : BaseFragment<ICatDescriptionView>() {


    companion object {
        lateinit var component: CatDescriptionSubcomponent

        fun getInstance(idCat: String) = CatDescriptionFragment().apply {
            setCatId(idCat)
        }
    }

    @Inject
    lateinit var factory: CatDescriptionSubcomponent.Factory

    @Inject
    lateinit var viewModel: ICatDescriptionViewModel


    init {
        MainActivity.component.inject(this)
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_cat_description
    }


    override fun init(content: View) {
        component = factory.plus()

    }


    override fun initView(content: View) {
        view = CatDescriptionViewImpl()
        view.attachView(content)
    }


    private fun setCatId(catId: String) {
        viewModel.setCatId(catId)
    }

}