package com.evgeny.goncharov.catapp.feature.wall.cats.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiBreeds
import javax.inject.Inject

class WallCatRepositoryImpl @Inject constructor(
    private val api: ApiBreeds,
    private val daoWallCat: CatsWallDao
) : IWallCatRepository {


    override suspend fun loadWallCat(request: WallCatRequest) {
        val result = api.getBreedsAsync(
            request.createRequest()
        ).await()
        daoWallCat.insertWallCat(result)
    }


    override fun getLiveDataCatBreedModel(): LiveData<List<CatBreedModel>> {
        val liveData = daoWallCat.getCatBreed()
        return Transformations.map(liveData) {
            val listMap = it.map { modelDb ->
                CatBreedModel(
                    name = modelDb.name,
                    description = modelDb.description,
                    id = modelDb.id,
                    urlImage = null,
                    wikipediaUrl = modelDb.wikipediaUrl
                )
            }
            listMap
        }
    }
}