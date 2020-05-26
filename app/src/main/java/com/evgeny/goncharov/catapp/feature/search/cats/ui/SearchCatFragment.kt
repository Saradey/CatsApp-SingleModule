package com.evgeny.goncharov.catapp.feature.search.cats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.feature.search.cats.di.SearchCatSubcomponent
import com.evgeny.goncharov.catapp.feature.search.cats.ui.events.SearchCatEvents
import com.evgeny.goncharov.catapp.feature.search.cats.ui.view.SearchCatView
import com.evgeny.goncharov.catapp.feature.search.cats.view.model.ISearchCatViewModel
import kotlinx.android.synthetic.main.fragment_search_cat.*
import kotlinx.android.synthetic.main.fragment_search_cat.view.*
import kotlinx.android.synthetic.main.fragment_search_cat.view.mySearchView
import javax.inject.Inject


class SearchCatFragment : Fragment() {

    @Inject
    lateinit var viewModel: ISearchCatViewModel

    @Inject
    lateinit var factory: SearchCatSubcomponent.Factory

    private lateinit var uiLiveData: LiveData<SearchCatEvents>


    companion object {
        fun getInstance() = SearchCatFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_cat, container, false)
        init(view)
        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySubcomponent.component.inject(this)
        SearchCatSubcomponent.component = factory.plus()
        viewModel.initInject()
    }


    private fun init(content: View) {
        content.mySearchView.init()
        initLiveData()
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


    fun clickNavigationBack() {
        viewModel.clickNavigationBack()
    }


    fun setInputTextSearchView(newText: String?) {
        viewModel.setInputTextSearchView(newText ?: "")
    }


    fun chooseCat(id: String) {
        viewModel.chooseCat(id)
    }


    override fun onDestroy() {
        super.onDestroy()
        SearchCatSubcomponent.component = null
        (uiLiveData as SingleLiveEvent<SearchCatEvents>).call()
    }
}