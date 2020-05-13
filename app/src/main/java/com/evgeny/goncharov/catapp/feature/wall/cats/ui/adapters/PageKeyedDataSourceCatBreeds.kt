package com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters

import androidx.paging.PageKeyedDataSource
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.IWallCatsViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class PageKeyedDataSourceCatBreeds(
    private val viewModel: IWallCatsViewModel
) : PageKeyedDataSource<Int, CatBreedModel>() {

    private var mainScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private var page = 0


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CatBreedModel>
    ) {
        mainScope.launch {
            val result = viewModel.initWallCat()
            page++
            callback.onResult(result, 0, page)
        }
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CatBreedModel>) {
        mainScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            val result = viewModel.loadNextCats(params.key)
            page++
            callback.onResult(result, page)
        }
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CatBreedModel>) {}

}