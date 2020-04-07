package com.evgeny.goncharov.catapp.feature.wall.cats.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cat_breed")
class CatBreedModelResponse{
    @SerializedName("description") var description: String? = null
    @SerializedName("name") var name: String? = null
    @SerializedName("life_span") var lifeSpan: String? = null
    @SerializedName("id") @PrimaryKey var id: String = ""
    @SerializedName("origin") var origin: String? = null
    @SerializedName("temperament") var temperament: String? = null
    @SerializedName("wikipedia_url") var wikipediaUrl: String? = null
    @Expose(serialize = false) var urlImageCat: String? = null
}

