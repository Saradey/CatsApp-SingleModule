package com.evgeny.goncharov.catapp.feature.wall.cats.di.modules

import androidx.lifecycle.LifecycleOwner
import com.evgeny.goncharov.catapp.consts.TAG_LIFECYCLE_CAT_DESC
import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.CatDescriptionInteractorImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.ICatDescriptionInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.CatDescriptionRepositoryImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.ICatDescriptionRepository
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.CatDescriptionFragment
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
interface BindsCatDescriptionModule {

    @Binds
    @FragmentScope
    fun bindCatDescriptionInteractor(interactor: CatDescriptionInteractorImpl):
            ICatDescriptionInteractor


    @Binds
    @FragmentScope
    fun bindCatDescriptionRepository(repository: CatDescriptionRepositoryImpl):
            ICatDescriptionRepository

    @Binds
    @Named(TAG_LIFECYCLE_CAT_DESC)
    fun bindLifeCycle(fragment: CatDescriptionFragment): LifecycleOwner

}