package com.evgeny.goncharov.catapp.base

sealed class BaseEventsUi {

    //base
    object EventShowProgress : BaseEventsUi()

    object EventHideProgress : BaseEventsUi()

    object EventSomethingWrong : BaseEventsUi()

    //WallCats
    object EventHideProgressAndInitRefreshLayout : BaseEventsUi()


    //CatDescription
    object EventHideProgressAndShowSomethingWrong : BaseEventsUi()

    object EventHideProgressAndShowContent : BaseEventsUi()


    //SearchCat
    object EventShowProgressAndHideStubAndHideModels : BaseEventsUi()

    object EventHideProgressAndShowStub : BaseEventsUi()

    object EventHideProgressAndShowRecycleView : BaseEventsUi()

}