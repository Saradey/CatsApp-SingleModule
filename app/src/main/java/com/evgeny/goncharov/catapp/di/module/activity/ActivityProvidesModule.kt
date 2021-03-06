package com.evgeny.goncharov.catapp.di.module.activity

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.feature.search.cats.ui.SearchCatFragment
import com.evgeny.goncharov.catapp.feature.search.cats.view.model.SearchCatViewModel
import com.evgeny.goncharov.catapp.feature.search.cats.view.model.SearchCatViewModelImpl
import com.evgeny.goncharov.catapp.feature.settings.ui.SettingsFragment
import com.evgeny.goncharov.catapp.feature.settings.view.model.SettingsViewModel
import com.evgeny.goncharov.catapp.feature.settings.view.model.SettingsViewModelImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.CatDescriptionFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.CatDescriptionViewModel
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.CatDescriptionViewModelImpl
import dagger.Module
import dagger.Provides

@Module
object ActivityProvidesModule {


    @Provides
    @JvmStatic
    fun provideFragmentManager(activity: MainActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    @JvmStatic
    fun provideCatDescriptionFragment(fragmentManager: FragmentManager): CatDescriptionFragment {
        return fragmentManager.findFragmentByTag(
            CatDescriptionFragment::class.java.name
        ) as CatDescriptionFragment
    }

    @Provides
    @JvmStatic
    fun provideCatDescriptionViewModel(fragment: CatDescriptionFragment): CatDescriptionViewModel =
        ViewModelProviders.of(fragment).get(CatDescriptionViewModelImpl::class.java)

    @Provides
    @JvmStatic
    fun provideWallCatsFragment(fragmentManager: FragmentManager): WallCatsFragment {
        return fragmentManager.findFragmentByTag(
            WallCatsFragment::class.java.name
        ) as WallCatsFragment
    }

    @Provides
    @JvmStatic
    fun provideSearchCatFragment(fragmentManager: FragmentManager): SearchCatFragment {
        return fragmentManager.findFragmentByTag(SearchCatFragment::class.java.name) as SearchCatFragment
    }

    @Provides
    @JvmStatic
    fun provideSearchCatViewModel(fragment: SearchCatFragment): SearchCatViewModel =
        ViewModelProviders.of(fragment).get(SearchCatViewModelImpl::class.java)

    @Provides
    @JvmStatic
    fun provideSettingsFragment(fragmentManager: FragmentManager): SettingsFragment {
        return fragmentManager.findFragmentByTag(SettingsFragment::class.java.name) as SettingsFragment
    }

    @Provides
    @JvmStatic
    fun provideSettingsViewModel(fragment: SettingsFragment): SettingsViewModel =
        ViewModelProviders.of(fragment).get(SettingsViewModelImpl::class.java)
}