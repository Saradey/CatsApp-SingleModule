package com.evgeny.goncharov.catapp.feature.wall.cats.model.response

import com.google.gson.annotations.SerializedName

data class Weight(
    @SerializedName("imperial") val imperial: String? = null,
    @SerializedName("metric") val metric: String? = null
)