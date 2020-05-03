package com.evgeny.goncharov.catapp.feature.search.cats.ui.view

import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseViewImpl
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.search.cats.ui.SearchCatFragment
import com.evgeny.goncharov.catapp.feature.search.cats.ui.adapter.CatsCathedAdapter
import com.evgeny.goncharov.catapp.feature.search.cats.ui.holder.CatsCathedHolder
import kotlinx.android.synthetic.main.fragment_search_cat.view.*
import javax.inject.Inject

class SearchCatViewImpl : BaseViewImpl(), ISearchCatView,
    CatsCathedHolder.CatsCathedHolderListener {

    @Inject
    lateinit var fragment: SearchCatFragment

    private lateinit var adapter: CatsCathedAdapter


    override fun init() {
        SearchCatFragment.component.inject(this)
        initUi()
    }


    private fun initUi() {
        initSearchView()
        initToolbar()
        initAdapterAndRecycle()
    }


    private fun initAdapterAndRecycle() {
        adapter = CatsCathedAdapter(this)
        content?.apply {
            rcvCathedCats.layoutManager = LinearLayoutManager(context)
            rcvCathedCats.adapter = adapter
        }
    }


    private fun initToolbar() {
        content?.apply {
            (toolbar as Toolbar).apply {
                setNavigationIcon(R.drawable.ic_arrow_back_black)
                setNavigationOnClickListener {
                    fragment.clickNavigationBack()
                }
                setTitle(R.string.title_toolbar_search_cat)
            }
        }
    }


    private fun initSearchView() {
        content?.apply {
            srcSearchCat.onActionViewExpanded()
            srcSearchCat.setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?) = true
                    override fun onQueryTextChange(newText: String?): Boolean {
                        fragment.setInputTextSearchView(newText)
                        return true
                    }
                }
            )
        }
    }


    override fun hideStubAndListAndShowProgress() {
        content?.apply {
            crvContainerCats.setVisibilityBool(false)
            txvCatsStubNotFound.setVisibilityBool(false)
            showProgress()
        }
    }


    override fun hideProgressAndShowStub() {
        content?.apply {
            txvCatsStubNotFound.setVisibilityBool(true)
            hideProgress()
        }
    }


    override fun hideProgressAndShowModels() {
        content?.apply {
            hideProgress()
            crvContainerCats.setVisibilityBool(true)
        }
    }


    override fun setCatsCatched(models: List<CatCatched>?) {
        adapter.models = models ?: listOf()
    }


    override fun chooseCat(id: String) {
        fragment.chooseCat(id)
    }

}