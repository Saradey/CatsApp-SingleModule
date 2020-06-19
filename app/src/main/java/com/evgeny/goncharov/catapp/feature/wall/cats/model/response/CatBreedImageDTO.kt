package com.evgeny.goncharov.catapp.feature.wall.cats.model.response

import com.google.gson.annotations.SerializedName

data class CatBreedImageDTO(
    @SerializedName("url") val url: String
)