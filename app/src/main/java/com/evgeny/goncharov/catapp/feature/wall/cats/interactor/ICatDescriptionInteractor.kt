package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel

interface ICatDescriptionInteractor {

    fun setCatId(catId: String)

    suspend fun loadChooseCat(): CatDescriptionModel?

}