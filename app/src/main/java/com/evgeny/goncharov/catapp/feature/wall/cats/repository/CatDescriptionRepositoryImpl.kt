package com.evgeny.goncharov.catapp.feature.wall.cats.repository

import com.evgeny.goncharov.catapp.exception.ChooseCateNullPointerException
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatDescriptionDAO
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.GetChooseCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.ChooseCatBreedResponse
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiCatSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class CatDescriptionRepositoryImpl @Inject constructor(
    private val api: ApiCatSearch,
    private val dao: CatDescriptionDAO,
    private val daoWallCat: CatsWallDao
) : ICatDescriptionRepository {


    override suspend fun loadChooseCatFromInternet(catId: String): CatDescriptionModel? =
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


    override suspend fun loadChooseCatFromDatabase(catId: String): CatDescriptionModel? =
        withContext(Dispatchers.IO) {
            val model = dao.selectModelFromId(catId)
            mapModel(model)
        }


    private suspend fun mapModel(model: ChooseCatBreedResponse?): CatDescriptionModel? {
        return if (model != null) {
            CatDescriptionModel(
                name = model.name ?: "null",
                urlImage = getUrlImageFromDataBase(model.id) ?: "null",
                origin = model.origin ?: "null",
                lifeSpan = model.lifeSpan ?: "null",
                weight = model.weight?.metric ?: "null",
                temperament = model.temperament ?: "null",
                description = model.description ?: "null",
                urlWiki = model.wikipediaUrl ?: "null"
            )
        } else {
            null
        }
    }


    private suspend fun getUrlImageFromDataBase(id: String) = withContext(Dispatchers.IO) {
        val url = daoWallCat.getCatFromId(id).urlImageCat
        url
    }

}