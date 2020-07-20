package com.evgeny.goncharov.catapp.feature.wall.cats.gateway

import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.GetImageRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedImage
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreed
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedView
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiBreeds
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class WallCatGatewayImpl @Inject constructor(
    private val api: ApiBreeds,
    private val daoWallCat: CatsWallDao
) : IWallCatGateway {

    private val coroutineScopeIo = CoroutineScope(Dispatchers.IO + SupervisorJob())


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


    override suspend fun loadWallCatFromDatabase(): List<CatBreedView> =
        withContext(Dispatchers.IO) {
            val result = daoWallCat.getCatBreed()
            val mapResult = mapResponse(result.sortedBy {
                it.name
            })
            mapResult
        }


    private fun mapResponse(modelResponse: List<CatBreed>): List<CatBreedView> {
        return modelResponse.map { modelDb ->
            CatBreedView(
                name = modelDb.name,
                description = modelDb.description,
                id = modelDb.id,
                wikipediaUrl = modelDb.wikipediaUrl,
                urlImage = modelDb.urlImageCat
            )
        }
    }


    private suspend fun loadAllImage(result: List<CatBreed>) {
        val jobs = mutableListOf<Job>()
        result.forEach { response ->
            val job = coroutineScopeIo.launch {
                response.urlImageCat = getUrlImage(
                    GetImageRequest(response.id)
                )
            }
            jobs.add(job)
        }
        jobs.forEach {
            it.join()
        }
    }


    private suspend fun getUrlImage(request: GetImageRequest): String? {
        var result = emptyList<CatBreedImage>()
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