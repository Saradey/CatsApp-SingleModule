package com.evgeny.goncharov.catapp.feature.wall.cats.gateway

import com.evgeny.goncharov.catapp.exception.ChooseCateNullPointerException
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatDescriptionDAO
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.GetChooseCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreed
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.ChooseCatBreed
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescription
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiCatSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatDescriptionGatewayImpl @Inject constructor(
    private val api: ApiCatSearch,
    private val dao: CatDescriptionDAO,
    private val daoWallCat: CatsWallDao
) : CatDescriptionGateway {


    override suspend fun loadChooseCatFromInternet(catId: String): CatDescription? =
        withContext(Dispatchers.IO) {
            val model = api.getCatDescriptionAsync(
                GetChooseCatRequest(catId).createRequest()
            ).await().firstOrNull()
            model?.let {
                dao.insert(model)
            } ?: let {
                throw ChooseCateNullPointerException()
            }
            mapModel(model)
        }

    override suspend fun loadChooseCatFromDatabase(catId: String): CatDescription? =
        withContext(Dispatchers.IO) {
            val model = dao.selectModelFromId(catId)
            mapModel(model)
        }

    private fun mapModel(model: ChooseCatBreed?): CatDescription? {
        return if (model != null) {
            CatDescription(
                name = model.name ?: "-",
                urlImage = getUrlImageFromDataBase(model.id) ?: "null",
                origin = model.origin ?: "-",
                lifeSpan = model.lifeSpan ?: "-",
                weight = model.weight?.metric ?: "-",
                temperament = model.temperament ?: "-",
                description = model.description ?: "-",
                urlWiki = model.wikipediaUrl ?: "-"
            )
        } else {
            null
        }
    }

    private fun getUrlImageFromDataBase(id: String?) = daoWallCat.getCatFromId(id ?: "")?.urlImageCat

    override suspend fun loadChooseCatFromDatabaseSpare(catId: String) =
        withContext(Dispatchers.IO) {
            val model = daoWallCat.getCatFromId(catId)
            mapModel(model)
        }

    private fun mapModel(model: CatBreed?): CatDescription? {
        return CatDescription(
            name = model?.name ?: "-",
            urlImage = getUrlImageFromDataBase(model?.id) ?: "null",
            origin = model?.origin ?: "-",
            lifeSpan = model?.lifeSpan ?: "-",
            weight = model?.weight?.metric ?: "-",
            temperament = model?.temperament ?: "-",
            description = model?.description ?: "-",
            urlWiki = model?.wikipediaUrl ?: "-"
        )
    }
}