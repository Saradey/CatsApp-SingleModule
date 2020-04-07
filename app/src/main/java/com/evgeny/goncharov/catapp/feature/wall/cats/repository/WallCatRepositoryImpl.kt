package com.evgeny.goncharov.catapp.feature.wall.cats.repository

import com.evgeny.goncharov.catapp.consts.TAG_IO_SCOPE_GET_FROM_APP
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.GetImageRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedImageResponse
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedModelResponse
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiBreeds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WallCatRepositoryImpl @Inject constructor(
    private val api: ApiBreeds,
    private val daoWallCat: CatsWallDao,
    @Named(TAG_IO_SCOPE_GET_FROM_APP) private val coroutineScopeIo: CoroutineScope
) : IWallCatRepository {


    override suspend fun loadWallCatFromInternet(request: WallCatRequest) =
        withContext(Dispatchers.IO) {
            val result = api.getBreedsAsync(
                request.createRequest()
            ).await()
            loadAllImage(result)
            daoWallCat.insertWallCat(result)
            val resultMap = mapResponse(result)
            resultMap
        }


    override suspend fun loadWallCatFromDatabase(): List<CatBreedModel> =
        withContext(Dispatchers.IO) {
            val result = daoWallCat.getCatBreed()
            val mapResult = mapResponse(result)
            mapResult
        }


    private fun mapResponse(modelResponse: List<CatBreedModelResponse>): List<CatBreedModel> {
        return modelResponse.map { modelDb ->
            CatBreedModel(
                name = modelDb.name,
                description = modelDb.description,
                id = modelDb.id,
                wikipediaUrl = modelDb.wikipediaUrl,
                urlImage = modelDb.urlImageCat
            )
        }
    }


    private suspend fun loadAllImage(result: List<CatBreedModelResponse>) {
        result.forEach { response ->
            response.urlImageCat = getUrlImage(
                GetImageRequest(response.id)
            )
        }
    }


    private suspend fun getUrlImage(request: GetImageRequest): String? {
        var result = listOf<CatBreedImageResponse>()
        try {
            result = api.getImageUrlAsync(
                request.createRequest()
            ).await()
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
        return suspendCoroutine { continuation ->
            continuation.resume(result.firstOrNull()?.url)
        }
    }

}