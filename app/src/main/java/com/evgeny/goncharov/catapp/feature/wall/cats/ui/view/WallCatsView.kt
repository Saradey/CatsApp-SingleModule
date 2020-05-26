package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.common.MainThreadExecutor
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.WallCatsSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.CatBreedsPagedAdapter
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.DiffUtilsCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.PageKeyedDataSourceCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.holders.CatBreedViewHolder
import kotlinx.android.synthetic.main.fragment_wall_cats.view.*
import kotlinx.android.synthetic.main.toolbar.view.toolbar
import java.util.concurrent.Executors
import javax.inject.Inject

class WallCatsView : CatBreedViewHolder.CatBreedViewHolderListener {

    @Inject
    lateinit var fragment: WallCatsFragment

    @Inject
    lateinit var dataSource: PageKeyedDataSourceCatBreeds

    @Inject
    lateinit var mainThreadExecutor: MainThreadExecutor

    private var content: View? = null

    private lateinit var adapter: CatBreedsPagedAdapter


    fun init() {
        WallCatsSubcomponent.component?.inject(this)
        initUi()
    }


    private fun initUi() {
        initToolbar()
        initPagedAdapterAndRecycle()
        initFirstSwipeRefreshLayout()
    }


    private fun initFirstSwipeRefreshLayout() {
        content?.apply {
            swrlContainer.setOnRefreshListener {
                swrlContainer.isRefreshing = false
            }
        }
    }


    fun attachView(view: View) {
        content = view
    }


    fun initSwipeRefreshLayout() {
        content?.grpStubWallCat?.setVisibilityBool(false)
        content?.apply {
            swrlContainer.setOnRefreshListener {
                initPagedAdapterAndRecycle()
                swrlContainer.isRefreshing = false
                initFirstSwipeRefreshLayout()
            }
        }
    }


    private fun initPagedAdapterAndRecycle() {
        adapter = CatBreedsPagedAdapter(DiffUtilsCatBreeds(), this)
        val pagedConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(15)
            .build()
        val pagedList = PagedList.Builder<Int, CatBreedModel>(dataSource, pagedConfig)
            .setNotifyExecutor(mainThreadExecutor)
            .setFetchExecutor(Executors.newCachedThreadPool())
            .build()
        adapter.submitList(pagedList)
        content?.apply {
            rcvCatBreeds.layoutManager = LinearLayoutManager(context)
            rcvCatBreeds.adapter = adapter
        }
    }


    private fun initToolbar() {
        content?.apply {
            toolbar.setTitle(R.string.wall_cat_toolbar_title)
            toolbar.inflateMenu(R.menu.menu_wall_cats)
            toolbar.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.menuSearchCat -> {
                        fragment.clickMenuSearchCat()
                        true
                    }
                    R.id.menuSettings -> {
                        fragment.clickMenuSettings()
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }


    override fun clickCatUrlBreed(urlImage: String?) {
        fragment.clickCatUrlBreed(urlImage)
    }


    override fun clickCatBreed(id: String?) {
        fragment.clickCatBreed(id)
    }


    fun showProgress() {
        content?.prgLoad?.setVisibilityBool(true)
    }


    fun hideProgress() {
        content?.prgLoad?.setVisibilityBool(false)
    }


    fun showStubSomethingWrong() {
        content?.prgLoad?.setVisibilityBool(false)
        content?.grpStubWallCat?.setVisibilityBool(true)
    }

    fun hideStubSomethingWrong() {
        content?.grpStubWallCat?.setVisibilityBool(false)
    }
}