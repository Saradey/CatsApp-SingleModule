package com.evgeny.goncharov.catapp.feature.wall.cats.di.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.IWallCatsViewModel
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.WallCatsViewModelImpl
import dagger.Module
import dagger.Provides

@Module
class WallCatsProvidesModule {

    @Provides
    @FragmentScope
    fun provideViewModel(fragment: WallCatsFragment): IWallCatsViewModel =
        ViewModelProviders.of(fragment).get(WallCatsViewModelImpl::class.java)

    @Provides
    @FragmentScope
    fun provideLiveDataEventsUi(): LiveData<BaseEventsUi> = MutableLiveData<BaseEventsUi>()


}