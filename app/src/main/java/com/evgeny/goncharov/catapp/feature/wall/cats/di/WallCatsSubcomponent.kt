package com.evgeny.goncharov.catapp.feature.wall.cats.di

import dagger.Subcomponent

@Subcomponent(

)
interface WallCatsSubcomponent {


    @Subcomponent.Factory
    interface Factory {
        fun plus(): WallCatsSubcomponent
    }

}