package com.evgeny.goncharov.catapp.feature.wall.cats.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cat_breed")
data class CatBreedModelResponse(
    @SerializedName("description") val description: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("life_span") val lifeSpan: String? = null,
    @SerializedName("id") @PrimaryKey val id: String = "",
    @SerializedName("origin") val origin: String? = null,
    @SerializedName("temperament") val temperament: String? = null,
    @SerializedName("wikipedia_url") val wikipediaUrl: String? = null,
    @SerializedName("weight") val weight: WeightResponse? = null,
    @Expose(serialize = false) var urlImageCat: String? = null
)