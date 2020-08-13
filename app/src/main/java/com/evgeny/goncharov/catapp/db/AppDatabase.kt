package com.evgeny.goncharov.catapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.evgeny.goncharov.catapp.consts.VERSION_DATA_BASE
import com.evgeny.goncharov.catapp.db.converters.WeightTypeConverter
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatDescriptionDAO
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreed
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.ChooseCatBreed

@Database(
    version = VERSION_DATA_BASE, entities = [
        CatBreed::class,
        ChooseCatBreed::class
    ]
)
@TypeConverters(
    value = [
        WeightTypeConverter::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun createCatsWallDao(): CatsWallDao

    abstract fun createCatDescriptionDAO(): CatDescriptionDAO
}