package com.evgeny.goncharov.catapp.feature.wall.cats.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.ChooseCatBreedResponse

@Dao
interface CatDescriptionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: ChooseCatBreedResponse)

    @Query("SELECT * FROM choose_cat_breed WHERE name = :name")
    fun selectModelFromId(name: String): ChooseCatBreedResponse

}