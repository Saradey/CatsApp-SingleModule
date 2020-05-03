package com.evgeny.goncharov.catapp.feature.search.cats.repository

import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedModelResponse
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.ChooseCatBreedResponse
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiCatSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchCatRepositoryImpl @Inject constructor(
    private val api: ApiCatSearch,
    private val dao: CatsWallDao
) : ISearchCatRepository {


    override suspend fun loadFromDatabase(text: String): List<CatCatched> =
        withContext(Dispatchers.IO) {
            if (text.isEmpty()) {
                listOf()
            } else {
                mapModelsFromDatabase(dao.getCatLike("%$text%")?.sortedBy {
                    it.name
                } ?: listOf())
            }
        }


    private fun mapModelsFromDatabase(list: List<CatBreedModelResponse>): List<CatCatched> {
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
        return mapModels(response)
    }


    private fun mapModels(list: List<ChooseCatBreedResponse>): List<CatCatched> {
        return list.map {
            CatCatched(
                it.name ?: "-",
                it.id
            )
        }
    }


}