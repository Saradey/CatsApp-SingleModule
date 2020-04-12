package com.evgeny.goncharov.catapp.di.module.activity

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.di.scope.ActivityScope
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.CatDescriptionFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.CatDescriptionViewModelImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.ICatDescriptionViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityProvidesModule {

    @Provides
    @ActivityScope
    fun provideMainRouter(router: IMainRouter) = router


    @Provides
    @ActivityScope
    fun provideFragmentManager(activity: MainActivity): FragmentManager {
        return activity.supportFragmentManager
    }


    @Provides
    fun provideCatDescriptionFragment(fragmentManager: FragmentManager): CatDescriptionFragment {
        return fragmentManager.getFragment(
            Bundle(),
            CatDescriptionFragment::class.java.name
        ) as CatDescriptionFragment
    }


    @Provides
    fun provideCatDescriptionViewModel(fragment: CatDescriptionFragment): ICatDescriptionViewModel =
        ViewModelProviders.of(fragment).get(CatDescriptionViewModelImpl::class.java)

}