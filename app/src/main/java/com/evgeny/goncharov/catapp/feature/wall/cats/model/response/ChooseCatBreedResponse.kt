package com.evgeny.goncharov.catapp.feature.wall.cats.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "choose_cat_breed")
data class ChooseCatBreedResponse(
    @SerializedName("id") @PrimaryKey val id: String = "",
    @SerializedName("weight") val weight: WeightResponse? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("temperament") val temperament: String? = null,
    @SerializedName("origin") val origin: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("life_span") val lifeSpan: String? = null,
    @SerializedName("wikipedia_url") val wikipediaUrl: String? = null
)