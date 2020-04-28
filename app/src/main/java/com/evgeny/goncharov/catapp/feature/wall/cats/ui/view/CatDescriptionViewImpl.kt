package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import com.evgeny.goncharov.catapp.base.BaseViewImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.CatDescriptionFragment

class CatDescriptionViewImpl : BaseViewImpl(), ICatDescriptionView {


    override fun init() {
        CatDescriptionFragment.component.inject(this)
    }


}