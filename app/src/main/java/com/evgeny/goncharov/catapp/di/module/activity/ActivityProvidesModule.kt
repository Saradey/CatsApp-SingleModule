package com.evgeny.goncharov.catapp.di.module.activity

import android.content.res.Resources
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.consts.TAG_LIFECYCLE_WALL_CAT
import com.evgeny.goncharov.catapp.di.scope.ActivityScope
import com.evgeny.goncharov.catapp.feature.search.cats.ui.SearchCatFragment
import com.evgeny.goncharov.catapp.feature.search.cats.view.model.ISearchCatViewModel
import com.evgeny.goncharov.catapp.feature.search.cats.view.model.SearchCatViewModelImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.CatDescriptionFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.CatDescriptionViewModelImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.ICatDescriptionViewModel
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.IWallCatsViewModel
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.WallCatsViewModelImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ActivityProvidesModule {


    @Provides
    fun provideFragmentManager(activity: MainActivity): FragmentManager {
        return activity.supportFragmentManager
    }


    @Provides
    fun provideCatDescriptionFragment(fragmentManager: FragmentManager): CatDescriptionFragment {
        return fragmentManager.findFragmentByTag(
            CatDescriptionFragment::class.java.name
        ) as CatDescriptionFragment
    }


    @Provides
    fun provideCatDescriptionViewModel(fragment: CatDescriptionFragment): ICatDescriptionViewModel =
        ViewModelProviders.of(fragment).get(CatDescriptionViewModelImpl::class.java)


    @Provides
    fun provideWallCatsFragment(fragmentManager: FragmentManager): WallCatsFragment {
        return fragmentManager.findFragmentByTag(
            WallCatsFragment::class.java.name
        ) as WallCatsFragment
    }


    @Provides
    fun provideWallCatsViewModel(fragment: WallCatsFragment): IWallCatsViewModel =
        ViewModelProviders.of(fragment).get(WallCatsViewModelImpl::class.java)


    @Provides
    @Named(TAG_LIFECYCLE_WALL_CAT)
    fun provideLifeCycleOwner(fragmentManager: FragmentManager): LifecycleOwner {
        return fragmentManager.findFragmentByTag(
            WallCatsFragment::class.java.name
        ) as LifecycleOwner
    }


    @Provides
    fun provideSearchCatFragment(fragmentManager: FragmentManager): SearchCatFragment {
        return fragmentManager.findFragmentByTag(SearchCatFragment::class.java.name) as SearchCatFragment
    }


    @Provides
    fun provideSearchCatViewModel(fragment: SearchCatFragment): ISearchCatViewModel =
        ViewModelProviders.of(fragment).get(SearchCatViewModelImpl::class.java)

}