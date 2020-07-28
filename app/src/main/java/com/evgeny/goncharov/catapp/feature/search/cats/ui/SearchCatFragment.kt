package com.evgeny.goncharov.catapp.feature.search.cats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.extension.setHintTextColor
import com.evgeny.goncharov.catapp.extension.setTextColor
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.search.cats.di.SearchCatSubcomponent
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.search.cats.ui.adapter.CatsCathedAdapter
import com.evgeny.goncharov.catapp.feature.search.cats.ui.events.SearchCatEvents
import com.evgeny.goncharov.catapp.feature.search.cats.view.model.ISearchCatViewModel
import kotlinx.android.synthetic.main.fragment_search_cat.*
import kotlinx.android.synthetic.main.fragment_search_cat.view.*
import javax.inject.Inject


class SearchCatFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: ISearchCatViewModel

    @Inject
    lateinit var factory: SearchCatSubcomponent.Factory

    private lateinit var uiLiveData: LiveData<SearchCatEvents>

    private lateinit var adapter: CatsCathedAdapter


    companion object {
        fun getInstance() = SearchCatFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySubcomponent.component.inject(this)
        SearchCatSubcomponent.component = factory.plus()
        viewModel.initInject()
        initLiveData()
    }


    override fun getLayoutId(): Int = R.layout.fragment_search_cat


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }


    private fun initUi() {
        initAdapterAndRecycle()
        initToolbar()
        initSearchView()
    }


    private fun initLiveData() {
        initUiEvents()
        initCatsCathed()
    }


    private fun initCatsCathed() {
        viewModel.getLiveDataCatsCathed().observe(this, Observer {
            setCatsCatched(it)
        })
    }


    private fun initUiEvents() {
        uiLiveData = viewModel.getUiEventsLiveData()
        uiLiveData.observe(this, Observer {
            when (it) {
                SearchCatEvents.EventShowProgressAndHideStubAndHideModels -> hideStubAndListAndShowProgress()
                SearchCatEvents.EventHideProgressAndShowStub -> hideProgressAndShowStub()
                SearchCatEvents.EventHideProgressAndShowRecycleView -> hideProgressAndShowModels()
            }
        })
    }


    private fun chooseCat(id: String) {
        viewModel.chooseCat(id)
    }


    private fun initAdapterAndRecycle() {
        adapter = CatsCathedAdapter(::chooseCat)
        rcvCathedCats.layoutManager = LinearLayoutManager(context)
        rcvCathedCats.adapter = adapter
    }


    private fun initToolbar() {
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black)
            setNavigationOnClickListener {
                viewModel.clickNavigationBack()
            }
            setTitle(R.string.title_toolbar_search_cat)
        }
    }


    private fun initSearchView() {
        srcSearchCat.onActionViewExpanded()
        srcSearchCat.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = true
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.setInputTextSearchView(newText ?: "")
                    return true
                }
            }
        )
        initEditTextSearchView(srcSearchCat)
    }


    private fun initEditTextSearchView(searchView: SearchView) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            searchView.setHintTextColor(R.color.color_dark_grey_hint)
        } else {
            searchView.setHintTextColor(R.color.white_hint)
            searchView.setTextColor(R.color.white)
        }
    }


    private fun hideStubAndListAndShowProgress() {
        crvContainerCats.setVisibilityBool(false)
        txvCatsStubNotFound.setVisibilityBool(false)
        showProgress()
    }


    private fun hideProgressAndShowStub() {
        txvCatsStubNotFound.setVisibilityBool(true)
        hideProgress()
    }


    private fun hideProgressAndShowModels() {
        hideProgress()
        crvContainerCats.setVisibilityBool(true)
    }


    private fun setCatsCatched(models: List<CatCatched>?) {
        adapter.models = models ?: emptyList()
    }


    override fun onDestroy() {
        super.onDestroy()
        SearchCatSubcomponent.component = null
        hideKeyboard()
        (uiLiveData as SingleLiveEvent<SearchCatEvents>).call()
    }
}