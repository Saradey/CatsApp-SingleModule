package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import com.evgeny.goncharov.catapp.base.IBaseView
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel

interface ICatDescriptionView : IBaseView {

    fun setCatDescription(model: CatDescriptionModel?)

}