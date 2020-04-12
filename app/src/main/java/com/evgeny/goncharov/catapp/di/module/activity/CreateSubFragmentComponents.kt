package com.evgeny.goncharov.catapp.di.module.activity

import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.CatDescriptionSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.WallCatsSubcomponent
import dagger.Module

@Module(
    subcomponents = [
        WallCatsSubcomponent::class,
        CatDescriptionSubcomponent::class
    ]
)
interface CreateSubFragmentComponents