package com.evgeny.goncharov.catapp.di.module

import com.evgeny.goncharov.catapp.feature.wall.cats.di.WallCatsSubcomponent
import dagger.Module

@Module(
    subcomponents = [WallCatsSubcomponent::class]
)
interface CreateSubFragmentComponents