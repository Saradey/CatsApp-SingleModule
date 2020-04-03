package com.evgeny.goncharov.catapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evgeny.goncharov.catapp.consts.VERSION_DATA_BASE
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedModelResponse

@Database(version = VERSION_DATA_BASE, entities = [CatBreedModelResponse::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun createCatsWallDao(): CatsWallDao

}