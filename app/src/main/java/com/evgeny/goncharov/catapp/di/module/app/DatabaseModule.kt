package com.evgeny.goncharov.catapp.di.module.app

import android.content.Context
import androidx.room.Room
import com.evgeny.goncharov.catapp.consts.DATA_BASE_NAME
import com.evgeny.goncharov.catapp.db.AppDatabase
import com.evgeny.goncharov.catapp.di.qualified.AppContext
import com.evgeny.goncharov.catapp.di.scope.AppScope
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatDescriptionDAO
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @AppScope
    @Provides
    @JvmStatic
    fun provideDataBase(@AppContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATA_BASE_NAME).build()

    @Provides
    @JvmStatic
    fun provideCatDescriptionDAO(appDatabase: AppDatabase): CatDescriptionDAO =
        appDatabase.createCatDescriptionDAO()

    @Provides
    @JvmStatic
    fun provideCatsWallDao(appDatabase: AppDatabase) =
        appDatabase.createCatsWallDao()
}