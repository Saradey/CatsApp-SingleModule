package com.evgeny.goncharov.catapp.feature.wall.cats.model.request

import com.evgeny.goncharov.catapp.consts.GET_PARAM_Q_SEARCH

class GetChooseCatRequest(
    private val nameCat: String
) {

    fun createRequest(): Map<String, String> = hashMapOf(Pair(GET_PARAM_Q_SEARCH, nameCat))
}