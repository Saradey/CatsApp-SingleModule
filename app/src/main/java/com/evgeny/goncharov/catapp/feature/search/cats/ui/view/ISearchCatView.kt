package com.evgeny.goncharov.catapp.feature.search.cats.ui.view

import com.evgeny.goncharov.catapp.base.IBaseView
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched

interface ISearchCatView : IBaseView {

    fun hideStubAndListAndShowProgress()

    fun hideProgressAndShowStub()

    fun hideProgressAndShowModels()

    fun setCatsCatched(models: List<CatCatched>?)

}