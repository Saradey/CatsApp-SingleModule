package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.StyleRes
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.common.MainThreadExecutor
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.CatBreedsPagedAdapter
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.DiffUtilsCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.PageKeyedDataSourceCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.holders.CatBreedViewHolder
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.WallCatsViewModel
import kotlinx.android.synthetic.main.fragment_wall_cats.view.*
import kotlinx.android.synthetic.main.toolbar.view.toolbar
import java.util.concurrent.Executors
import javax.inject.Inject

class WallCatsView : LinearLayout {

    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, @StyleRes style: Int) : super(
        context,
        attr,
        style
    )

    private lateinit var dataSource: PageKeyedDataSourceCatBreeds

    @Inject
    lateinit var mainThreadExecutor: MainThreadExecutor

    private lateinit var adapter: CatBreedsPagedAdapter


    fun init() {
        ActivitySubcomponent.component.inject(this)
    }


    fun initFirstSwipeRefreshLayout() {
        swrlContainer.setOnRefreshListener {
            swrlContainer.isRefreshing = false
        }
    }


    fun initSwipeRefreshLayout(
        vm: WallCatsViewModel,
        listener: CatBreedViewHolder.CatBreedViewHolderListener
    ) {
        grpStubWallCat?.setVisibilityBool(false)
        swrlContainer.setOnRefreshListener {
            initPagedAdapterAndRecycle(vm, listener)
            swrlContainer.isRefreshing = false
            initFirstSwipeRefreshLayout()
        }
    }


    fun initPagedAdapterAndRecycle(
        vm: WallCatsViewModel,
        listener: CatBreedViewHolder.CatBreedViewHolderListener
    ) {
        adapter = CatBreedsPagedAdapter(DiffUtilsCatBreeds(), listener)
        dataSource = PageKeyedDataSourceCatBreeds(vm)
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


    fun initToolbar(
        clickMenuSearchCat: () -> Unit,
        clickMenuSettings: () -> Unit
    ) {
        toolbar.setTitle(R.string.wall_cat_toolbar_title)
        toolbar.inflateMenu(R.menu.menu_wall_cats)
        toolbar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.menuSearchCat -> {
                    clickMenuSearchCat()
                    true
                }
                R.id.menuSettings -> {
                    clickMenuSettings()
                    true
                }
                else -> {
                    false
                }
            }
        }
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