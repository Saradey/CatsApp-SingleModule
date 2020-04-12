package com.evgeny.goncharov.catapp.feature.wall.cats.model.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "choose_cat_breed")
data class ChooseCatBreedResponse(
    @SerializedName("id") var id: String = "",
    @SerializedName("weight") var weight: WeightResponse? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("temperament") var temperament: String? = null,
    @SerializedName("origin") var origin: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("life_span") var lifeSpan: String? = null,
    @SerializedName("wikipedia_url") var wikipediaUrl: String? = null
)