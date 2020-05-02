package com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view

data class CatDescriptionModel(
    val name: String,
    val urlImage: String,
    val origin: String,
    val lifeSpan: String,
    val weight: String,
    val temperament: String,
    val description: String,
    val urlWiki: String
)