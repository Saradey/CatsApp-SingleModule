package com.evgeny.goncharov.catapp.feature.search.cats.ui.events

sealed class SearchCatEvents {

    object EventShowProgressAndHideStubAndHideModels : SearchCatEvents()

    object EventHideProgressAndShowStub : SearchCatEvents()

    object EventHideProgressAndShowRecycleView : SearchCatEvents()

}