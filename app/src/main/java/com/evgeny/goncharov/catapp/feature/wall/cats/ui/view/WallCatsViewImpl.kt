package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.base.BaseViewImpl
import com.evgeny.goncharov.catapp.common.MainThreadExecutor
import com.evgeny.goncharov.catapp.consts.TAG_ACTIVITY_CONTEXT
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.DiffUtilsCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.PageKeyedDataSourceCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.CatBreedsPagedAdapter
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.IWallCatsViewModel
import kotlinx.android.synthetic.main.fragment_wall_cats.view.*
import kotlinx.android.synthetic.main.toolbar.view.toolbar
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Named

class WallCatsViewImpl :
    BaseViewImpl(),
    IWallCatsView {

    @Inject
    lateinit var viewModel: IWallCatsViewModel

    @Inject
    lateinit var lifecycleOwner: LifecycleOwner

    @Inject
    lateinit var uiEventsLD: MutableLiveData<BaseEventsUi>

    @Inject
    lateinit var dataSource: PageKeyedDataSourceCatBreeds

    @Inject
    lateinit var mainThreadExecutor: MainThreadExecutor

    @field:[Inject Named(TAG_ACTIVITY_CONTEXT)]
    lateinit var context: Context


    private val adapter = CatBreedsPagedAdapter(DiffUtilsCatBreeds())


    override fun init() {
        WallCatsFragment.component.inject(this)
        initUi()
        initLiveData()
    }


    private fun initUi() {
        initToolbar()
        initPagedAdapterAndRecycle()
    }


    private fun initPagedAdapterAndRecycle() {
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


    override fun initLiveData() {
        initEventsUILiveData()
    }


    private fun initEventsUILiveData() {
        uiEventsLD.observe(lifecycleOwner, Observer { event ->
            when (event) {
                BaseEventsUi.EventsShowProgress -> showProgress()
                BaseEventsUi.EventsHideProgress -> hideProgress()
            }
        })
    }


}