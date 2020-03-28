package com.evgeny.goncharov.catapp.di.module

import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import dagger.Module

@Module(
    subcomponents = [ActivitySubcomponent::class]
)
interface CreateSubActivityComponent