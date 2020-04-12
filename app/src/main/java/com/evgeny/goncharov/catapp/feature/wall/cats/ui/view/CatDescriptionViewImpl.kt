package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.evgeny.goncharov.catapp.base.BaseViewImpl
import com.evgeny.goncharov.catapp.consts.TAG_LIFECYCLE_CAT_DESC
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.CatDescriptionFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.ICatDescriptionViewModel
import javax.inject.Inject
import javax.inject.Named

class CatDescriptionViewImpl : BaseViewImpl(), ICatDescriptionView {

    @Inject
    lateinit var viewModel: ICatDescriptionViewModel

    @field:[Inject Named(TAG_LIFECYCLE_CAT_DESC)]
    lateinit var lifecycleOwner: LifecycleOwner


    override fun init() {
        CatDescriptionFragment.component.inject(this)
        viewModel.loadChooseCat()
    }


    override fun initLiveData() {
        viewModel.getCatDescriptionLiveData().observe(lifecycleOwner, Observer { model ->

        })
    }


}