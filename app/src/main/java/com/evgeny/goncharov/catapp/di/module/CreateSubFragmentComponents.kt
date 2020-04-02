package com.evgeny.goncharov.catapp.di.module

import com.evgeny.goncharov.catapp.feature.wall.cats.di.component.WallCatsSubcomponent
import dagger.Module

@Module(
    subcomponents = [WallCatsSubcomponent::class]
)
interface CreateSubFragmentComponents