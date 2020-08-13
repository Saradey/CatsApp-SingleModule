package com.evgeny.goncharov.catapp.feature.wall.cats.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.common.MainThreadExecutor
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.extension.injectViewModel
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedView
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.CatBreedsPagedAdapter
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.DiffUtilsCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.PageKeyedDataSourceCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.WallCatsEvents
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.holders.CatBreedViewHolder
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.WallCatsViewModel
import kotlinx.android.synthetic.main.fragment_wall_cats.grpStubWallCat
import kotlinx.android.synthetic.main.fragment_wall_cats.rcvCatBreeds
import kotlinx.android.synthetic.main.fragment_wall_cats.swrlContainer
import kotlinx.android.synthetic.main.fragment_wall_cats.toolbar
import java.util.concurrent.Executors
import javax.inject.Inject

class WallCatsFragment : BaseFragment(), CatBreedViewHolder.CatBreedViewHolderListener {

    companion object {
        fun getInstance() = WallCatsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WallCatsViewModel

    private lateinit var uiLiveData: LiveData<WallCatsEvents>

    private lateinit var dataSource: PageKeyedDataSourceCatBreeds

    private var mainThreadExecutor = MainThreadExecutor()

    private lateinit var adapter: CatBreedsPagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySubcomponent.component.inject(this)
        viewModel = injectViewModel(viewModelFactory)
        initLiveData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_wall_cats
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }

    private fun initUi() {
        initToolbar()
        initPagedAdapterAndRecycle()
        initFirstSwipeRefreshLayout()
    }

    private fun initLiveData() {
        uiLiveData = viewModel.getUiEventsLiveData()
        uiLiveData.observe(this, Observer {
            when (it) {
                WallCatsEvents.EventShowProgressAndHideStub -> {
                    hideStubSomethingWrong()
                    showProgress()
                }
                WallCatsEvents.EventSomethingWrong -> {
                    showStubSomethingWrong()
                }
                WallCatsEvents.EventHideProgressAndInitRefreshLayout -> {
                    hideProgress()
                    initSwipeRefreshLayout()
                }
            }
        })
    }

    override fun clickCatUrlBreed(urlImage: String?) {
        urlImage?.let {
            val uri = Uri.parse(urlImage)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            if (intent.resolveActivity(activity?.packageManager!!) != null) {
                startActivity(intent)
            }
        }
    }

    override fun clickCatBreed(id: String?) {
        id?.let {
            viewModel.clickCatBreed(id)
        }
    }

    private fun initFirstSwipeRefreshLayout() {
        swrlContainer.setOnRefreshListener {
            swrlContainer.isRefreshing = false
        }
    }

    private fun initSwipeRefreshLayout() {
        grpStubWallCat?.setVisibilityBool(false)
        swrlContainer.setOnRefreshListener {
            initPagedAdapterAndRecycle()
            swrlContainer.isRefreshing = false
            initFirstSwipeRefreshLayout()
        }
    }

    private fun initPagedAdapterAndRecycle() {
        adapter = CatBreedsPagedAdapter(DiffUtilsCatBreeds(), this)
        dataSource = PageKeyedDataSourceCatBreeds(viewModel)
        val pagedConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(15)
            .build()
        val pagedList = PagedList.Builder<Int, CatBreedView>(dataSource, pagedConfig)
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
                    viewModel.clickMenuSearchCat()
                    true
                }
                R.id.menuSettings -> {
                    viewModel.clickMenuSettings()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (uiLiveData as SingleLiveEvent<WallCatsEvents>).call()
    }
}