package com.evgeny.goncharov.catapp.feature.search.cats.gateway

import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatchedValueObject
import com.evgeny.goncharov.catapp.feature.wall.cats.db.CatsWallDao
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedValueObject
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.ChooseCatBreedValueObject
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiCatSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchCatGatewayImpl @Inject constructor(
    private val api: ApiCatSearch,
    private val dao: CatsWallDao
) : ISearchCatGateway {


    override suspend fun loadFromDatabase(text: String): List<CatCatchedValueObject> =
        withContext(Dispatchers.IO) {
            if (text.isEmpty()) {
                emptyList()
            } else {
                mapModelsFromDatabase(dao.getCatLike("%$text%")?.sortedBy {
                    it.name
                } ?: emptyList())
            }
        }


    private fun mapModelsFromDatabase(list: List<CatBreedValueObject>): List<CatCatchedValueObject> {
        return list.map {
            CatCatchedValueObject(
                it.name ?: "-",
                it.id
            )
        }
    }


    override suspend fun loadFromInternet(request: Map<String, String>): List<CatCatchedValueObject> {
        val response = api.getCatDescriptionAsync(request)
            .await()
        return mapModels(response)
    }


    private fun mapModels(list: List<ChooseCatBreedValueObject>): List<CatCatchedValueObject> {
        return list.map {
            CatCatchedValueObject(
                it.name ?: "-",
                it.id
            )
        }
    }


}