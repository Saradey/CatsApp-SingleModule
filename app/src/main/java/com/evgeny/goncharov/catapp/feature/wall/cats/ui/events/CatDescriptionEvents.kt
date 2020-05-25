package com.evgeny.goncharov.catapp.feature.wall.cats.ui.events

sealed class CatDescriptionEvents {

    object EventShowProgress : CatDescriptionEvents()

    object EventHideProgressAndShowContent : CatDescriptionEvents()

    object EventHideProgressAndShowSomethingWrong : CatDescriptionEvents()

}