package com.evgeny.goncharov.catapp.feature.wall.cats.repository

import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedModelResponse
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiBreeds
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WallCatRepositoryImpl @Inject constructor(
    private val api: ApiBreeds,
    private val daoWallCat : CatsWallDao
) : IWallCatRepository {


    override suspend fun loadWallCat(request: WallCatRequest): List<CatBreedModel> {
        val result = api.getBreedsAsync(
            request.createRequest()
        ).await()
        return suspendCoroutine { continuation ->
            continuation.resume(
                mapModelCatWallResponse(result)
            )
        }
    }


    private fun mapModelCatWallResponse(result: List<CatBreedModelResponse>): List<CatBreedModel> {
        return result.map { catBreed ->
            CatBreedModel(
                name = catBreed.name,
                description = catBreed.description,
                id = catBreed.id,
                urlImage = null,
                wikipediaUrl = catBreed.wikipediaUrl
            )
        }
    }

}