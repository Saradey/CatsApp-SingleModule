package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.MainThread
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
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

class WallCatsView : LinearLayout, CatBreedViewHolder.CatBreedViewHolderListener {

    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, @StyleRes style: Int) : super(
        context,
        attr,
        style
    )

    @Inject
    lateinit var fragment: WallCatsFragment

    @Inject
    lateinit var dataSource: PageKeyedDataSourceCatBreeds

    @Inject
    lateinit var mainThreadExecutor: MainThreadExecutor

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
        swrlContainer.setOnRefreshListener {
            swrlContainer.isRefreshing = false
        }
    }


    fun initSwipeRefreshLayout() {
        grpStubWallCat?.setVisibilityBool(false)
        swrlContainer.setOnRefreshListener {
            initPagedAdapterAndRecycle()
            swrlContainer.isRefreshing = false
            initFirstSwipeRefreshLayout()
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
        rcvCatBreeds.layoutManager = LinearLayoutManager(context)
        rcvCatBreeds.adapter = adapter
    }


    private fun initToolbar() {
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


    override fun clickCatUrlBreed(urlImage: String?) {
        fragment.clickCatUrlBreed(urlImage)
    }


    override fun clickCatBreed(id: String?) {
        fragment.clickCatBreed(id)
    }


    fun showProgress() {
        prgLoad?.setVisibilityBool(true)
    }


    fun hideProgress() {
        prgLoad?.setVisibilityBool(false)
    }


    fun showStubSomethingWrong() {
        prgLoad?.setVisibilityBool(false)
        grpStubWallCat?.setVisibilityBool(true)
    }


    fun hideStubSomethingWrong() {
        grpStubWallCat?.setVisibilityBool(false)
    }
}