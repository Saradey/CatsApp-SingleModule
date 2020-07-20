package com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters

import androidx.paging.PageKeyedDataSource
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedView
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.WallCatsViewModel
import kotlinx.coroutines.*


class PageKeyedDataSourceCatBreeds(
    private val viewModel: WallCatsViewModel
) : PageKeyedDataSource<Int, CatBreedView>() {

    private var mainScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private var page = 0


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CatBreedView>
    ) {
        mainScope.launch {
            val result = viewModel.initWallCat()
            page++
            callback.onResult(result, 0, page)
        }
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CatBreedView>) {
        mainScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            val result = viewModel.loadNextCats(params.key)
            page++
            callback.onResult(result, page)
        }
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CatBreedView>) {}

}