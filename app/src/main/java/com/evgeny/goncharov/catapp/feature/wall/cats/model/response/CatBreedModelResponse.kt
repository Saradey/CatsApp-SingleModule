package com.evgeny.goncharov.catapp.feature.wall.cats.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cat_breed")
data class CatBreedModelResponse(
    @SerializedName("description") val description: String,
    @SerializedName("name") val name: String,
    @SerializedName("life_span") val lifeSpan: String,
    @SerializedName("id") @PrimaryKey val id: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("wikipedia_url") val wikipediaUrl: String
)