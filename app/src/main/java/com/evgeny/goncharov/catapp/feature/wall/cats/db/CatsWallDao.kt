package com.evgeny.goncharov.catapp.feature.wall.cats.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedModelResponse

@Dao
interface CatsWallDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWallCat(model: List<CatBreedModelResponse>)

    @Query("SELECT * FROM cat_breed")
    fun getCatBreed(): List<CatBreedModelResponse>

    @Query("SELECT * FROM cat_breed WHERE id = :id")
    fun getCatFromId(id: String): CatBreedModelResponse?

}