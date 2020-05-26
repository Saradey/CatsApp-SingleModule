package com.evgeny.goncharov.catapp.di.module.activity

import com.evgeny.goncharov.catapp.feature.search.cats.di.SearchCatSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.CatDescriptionSubcomponent
import dagger.Module

@Module(
    subcomponents = [
        CatDescriptionSubcomponent::class,
        SearchCatSubcomponent::class,
        SettingsSubcomponent::class
    ]
)
interface CreateSubFragmentComponents