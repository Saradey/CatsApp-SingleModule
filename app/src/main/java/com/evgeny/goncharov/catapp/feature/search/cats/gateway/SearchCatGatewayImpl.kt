package com.evgeny.goncharov.catapp.feature.search.cats.gateway

import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreed
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.ChooseCatBreed
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiCatSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SearchCatGatewayImpl @Inject constructor(
    private val api: ApiCatSearch,
    private val dao: CatsWallDao
) : SearchCatGateway {


    override suspend fun loadFromDatabase(text: String): List<CatCatched> =
        withContext(Dispatchers.IO) {
            if (text.isEmpty()) {
                emptyList()
            } else {
                mapModelsFromDatabase(dao.getCatLike("%$text%")?.sortedBy {
                    it.name
                } ?: emptyList())
            }
        }


    private fun mapModelsFromDatabase(list: List<CatBreed>): List<CatCatched> {
        return list.map {
            CatCatched(
                it.name ?: "-",
                it.id
            )
        }
    }


    override suspend fun loadFromInternet(request: Map<String, String>): List<CatCatched> {
        val response = api.getCatDescriptionAsync(request)
            .await()
        return suspendCoroutine { continuation ->
            continuation.resume(
                mapModels(response)
            )
        }
    }


    private fun mapModels(list: List<ChooseCatBreed>): List<CatCatched> {
        return list.map {
            CatCatched(
                it.name ?: "-",
                it.id
            )
        }
    }


}