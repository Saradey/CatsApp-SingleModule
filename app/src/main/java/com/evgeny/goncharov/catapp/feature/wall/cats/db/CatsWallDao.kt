package com.evgeny.goncharov.catapp.feature.wall.cats.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreed

@Dao
interface CatsWallDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWallCat(model: List<CatBreed>)

    @Query("SELECT * FROM cat_breed")
    fun getCatBreed(): List<CatBreed>

    @Query("SELECT * FROM cat_breed WHERE id = :id")
    fun getCatFromId(id: String): CatBreed?

    @Query("SELECT * FROM cat_breed WHERE name OR id LIKE :value")
    fun getCatLike(value: String): List<CatBreed>?
}