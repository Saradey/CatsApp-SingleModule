package com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view

data class CatBreedView(
    val name: String? = null,
    val description: String? = null,
    val id: String? = null,
    var urlImage: String? = null,
    val wikipediaUrl: String? = null
)