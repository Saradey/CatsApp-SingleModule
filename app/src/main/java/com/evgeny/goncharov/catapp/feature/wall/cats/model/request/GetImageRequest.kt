package com.evgeny.goncharov.catapp.feature.wall.cats.model.request

import com.evgeny.goncharov.catapp.consts.GET_PARAM_BREED_ID

class GetImageRequest(
    private val breedId: String
) {
    fun createRequest(): Map<String, String> {
        return hashMapOf<String, String>(Pair(GET_PARAM_BREED_ID, breedId))
    }
}