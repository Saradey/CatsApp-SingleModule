package com.evgeny.goncharov.catapp.base

sealed class BaseEventsUi {

    object EventsShowProgress : BaseEventsUi()

    object EventsHideProgress : BaseEventsUi()


}