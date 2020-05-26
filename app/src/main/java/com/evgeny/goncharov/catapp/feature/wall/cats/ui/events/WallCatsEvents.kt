package com.evgeny.goncharov.catapp.feature.wall.cats.ui.events

sealed class WallCatsEvents {

    object EventShowProgressAndHideStub : WallCatsEvents()

    object EventSomethingWrong : WallCatsEvents()

    object EventHideProgressAndInitRefreshLayout : WallCatsEvents()

}