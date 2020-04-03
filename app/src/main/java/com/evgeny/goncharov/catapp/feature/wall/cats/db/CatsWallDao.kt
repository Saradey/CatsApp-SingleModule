package com.evgeny.goncharov.catapp.feature.wall.cats.db

import androidx.room.Dao
import androidx.room.Insert
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedModelResponse

@Dao
interface CatsWallDao {

    @Insert
    fun insertWallCat(model: List<CatBreedModelResponse>)


}