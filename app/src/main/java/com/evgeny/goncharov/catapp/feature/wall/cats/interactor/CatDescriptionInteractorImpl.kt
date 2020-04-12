package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import com.evgeny.goncharov.catapp.feature.wall.cats.repository.ICatDescriptionRepository
import javax.inject.Inject

class CatDescriptionInteractorImpl @Inject constructor(
    private val repository: ICatDescriptionRepository
) : ICatDescriptionInteractor {

    private var catId = ""


    override fun setCatId(catId: String) {
        this.catId = catId
    }


}