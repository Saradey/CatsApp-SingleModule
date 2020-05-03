package com.evgeny.goncharov.catapp.feature.search.cats.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.feature.search.cats.di.SearchCatSubcomponent
import com.evgeny.goncharov.catapp.feature.search.cats.ui.view.ISearchCatView
import com.evgeny.goncharov.catapp.feature.search.cats.ui.view.SearchCatViewImpl
import com.evgeny.goncharov.catapp.feature.search.cats.view.model.ISearchCatViewModel
import javax.inject.Inject


class SearchCatFragment : BaseFragment<ISearchCatView>() {

    @Inject
    lateinit var viewModel: ISearchCatViewModel

    @Inject
    lateinit var factory: SearchCatSubcomponent.Factory


    companion object {
        lateinit var component: SearchCatSubcomponent
        fun getInstance() = SearchCatFragment()
    }


    override fun getLayoutId(): Int = R.layout.fragment_search_cat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.component.inject(this)
        component = factory.plus()
        viewModel.initInject()
    }


    override fun init(content: View) {
        initView(content)
        view.init()
        initLiveData()
    }


    private fun initLiveData() {
        initUiEvents()
        initCatsCathed()
    }


    private fun initCatsCathed() {
        viewModel.getLiveDataCatsCathed().observe(this, Observer {
            view.setCatsCatched(it)
        })
    }


    private fun initUiEvents() {
        viewModel.getUiEventsLiveData().observe(this, Observer {
            when (it) {
                BaseEventsUi.EventShowProgressAndHideStubAndHideModels -> view.hideStubAndListAndShowProgress()
                BaseEventsUi.EventHideProgressAndShowStub -> view.hideProgressAndShowStub()
                BaseEventsUi.EventHideProgressAndShowRecycleView -> view.hideProgressAndShowModels()
            }
        })
    }


    override fun initView(content: View) {
        view = SearchCatViewImpl()
        view.attachView(content)
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
}