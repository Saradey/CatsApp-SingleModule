package com.evgeny.goncharov.catapp.feature.wall.cats.model.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weight")
data class WeightResponse(
    @SerializedName("imperial") var imperial: String? = null,
    @SerializedName("metric") var metric: String? = null
)