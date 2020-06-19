package com.evgeny.goncharov.catapp.feature.wall.cats.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedValueObject

@Dao
interface CatsWallDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWallCat(model: List<CatBreedValueObject>)

    @Query("SELECT * FROM cat_breed")
    fun getCatBreed(): List<CatBreedValueObject>

    @Query("SELECT * FROM cat_breed WHERE id = :id")
    fun getCatFromId(id: String): CatBreedValueObject?

    @Query("SELECT * FROM cat_breed WHERE name OR id LIKE :value")
    fun getCatLike(value: String): List<CatBreedValueObject>?

}