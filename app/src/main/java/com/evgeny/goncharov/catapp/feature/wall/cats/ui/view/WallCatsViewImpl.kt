package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseViewImpl
import com.evgeny.goncharov.catapp.common.MainThreadExecutor
import com.evgeny.goncharov.catapp.consts.TAG_LIFECYCLE_WALL_CAT
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.CatBreedsPagedAdapter
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.DiffUtilsCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.PageKeyedDataSourceCatBreeds
import kotlinx.android.synthetic.main.fragment_wall_cats.view.*
import kotlinx.android.synthetic.main.toolbar.view.toolbar
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Named

class WallCatsViewImpl :
    BaseViewImpl(),
    IWallCatsView {

    @field:[Inject Named(TAG_LIFECYCLE_WALL_CAT)]
    lateinit var lifecycleOwner: LifecycleOwner

    @Inject
    lateinit var dataSource: PageKeyedDataSourceCatBreeds

    @Inject
    lateinit var mainThreadExecutor: MainThreadExecutor

    private lateinit var adapter: CatBreedsPagedAdapter


    override fun init() {
        WallCatsFragment.component.inject(this)
        initUi()
    }


    private fun initUi() {
        initToolbar()
        initPagedAdapterAndRecycle()
    }


    private fun initPagedAdapterAndRecycle() {
        adapter = CatBreedsPagedAdapter(DiffUtilsCatBreeds(), lifecycleOwner as WallCatsFragment)
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

                        true
                    }
                    R.id.menuSettings -> {

                        true
                    }
                    else -> {

                        false
                    }
                }
            }
        }
    }

}