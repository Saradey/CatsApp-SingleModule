package com.evgeny.goncharov.catapp.di.module.app

import android.content.Context
import androidx.room.Room
import com.evgeny.goncharov.catapp.consts.TAG_APPLICATION_CONTEXT
import com.evgeny.goncharov.catapp.consts.TAG_IO_SCOPE_GET_FROM_APP
import com.evgeny.goncharov.catapp.db.AppDatabase
import com.evgeny.goncharov.catapp.di.scope.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DatabaseModule {

    @AppScope
    @Provides
    fun provideDataBase(@Named(TAG_APPLICATION_CONTEXT) context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "pisa").build()

}