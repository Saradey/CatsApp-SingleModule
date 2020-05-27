package com.evgeny.goncharov.catapp.feature.search.cats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.feature.search.cats.di.SearchCatSubcomponent
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


    override fun getLayoutId(): Int {
        return R.layout.fragment_search_cat
    }
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }


    private fun initUi() {
        mySearchView.initAdapterAndRecycle(::chooseCat)
        mySearchView.initToolbar(::clickNavigationBack)
        mySearchView.initSearchView(::setInputTextSearchView)
    }


    private fun initLiveData() {
        initUiEvents()
        initCatsCathed()
    }


    private fun initCatsCathed() {
        viewModel.getLiveDataCatsCathed().observe(this, Observer {
            mySearchView.setCatsCatched(it)
        })
    }


    private fun initUiEvents() {
        uiLiveData = viewModel.getUiEventsLiveData()
        uiLiveData.observe(this, Observer {
            when (it) {
                SearchCatEvents.EventShowProgressAndHideStubAndHideModels -> mySearchView.hideStubAndListAndShowProgress()
                SearchCatEvents.EventHideProgressAndShowStub -> mySearchView.hideProgressAndShowStub()
                SearchCatEvents.EventHideProgressAndShowRecycleView -> mySearchView.hideProgressAndShowModels()
            }
        })
    }


    private fun clickNavigationBack() {
        viewModel.clickNavigationBack()
    }


    private fun setInputTextSearchView(newText: String?) {
        viewModel.setInputTextSearchView(newText ?: "")
    }


    private fun chooseCat(id: String) {
        viewModel.chooseCat(id)
    }


    override fun onDestroy() {
        super.onDestroy()
        SearchCatSubcomponent.component = null
        (uiLiveData as SingleLiveEvent<SearchCatEvents>).call()
    }
}