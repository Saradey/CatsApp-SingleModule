package com.evgeny.goncharov.catapp.feature.wall.cats.di.modules

import androidx.lifecycle.MutableLiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.db.AppDatabase
import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatDescriptionDAO
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiCatSearch
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ProvidesCatDescriptionModule {

    @Provides
    @FragmentScope
    fun provideLiveDataEventsUi(): MutableLiveData<BaseEventsUi> = MutableLiveData<BaseEventsUi>()


}