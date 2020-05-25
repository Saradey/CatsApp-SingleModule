package com.evgeny.goncharov.catapp.feature.search.cats.ui.view

import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.extension.setHintTextColor
import com.evgeny.goncharov.catapp.extension.setTextColor
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.search.cats.di.SearchCatSubcomponent
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.search.cats.ui.SearchCatFragment
import com.evgeny.goncharov.catapp.feature.search.cats.ui.adapter.CatsCathedAdapter
import kotlinx.android.synthetic.main.fragment_search_cat.view.*
import javax.inject.Inject

class SearchCatViewImpl {

    @Inject
    lateinit var fragment: SearchCatFragment

    private lateinit var adapter: CatsCathedAdapter

    private var content: View? = null


    fun init() {
        SearchCatSubcomponent.component?.inject(this)
        initUi()
    }


    private fun initUi() {
        initSearchView()
        initToolbar()
        initAdapterAndRecycle()
    }


    private fun initAdapterAndRecycle() {
        adapter = CatsCathedAdapter(::chooseCat)
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
            initEditTextSearchView(srcSearchCat)
        }
    }


    private fun initEditTextSearchView(searchView: SearchView) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            searchView.setHintTextColor(R.color.color_dark_grey_hint)
        } else {
            searchView.setHintTextColor(R.color.white_hint)
            searchView.setTextColor(R.color.white)
        }
    }


    fun hideStubAndListAndShowProgress() {
        content?.apply {
            crvContainerCats.setVisibilityBool(false)
            txvCatsStubNotFound.setVisibilityBool(false)
            showProgress()
        }
    }


    fun hideProgressAndShowStub() {
        content?.apply {
            txvCatsStubNotFound.setVisibilityBool(true)
            hideProgress()
        }
    }


    fun hideProgressAndShowModels() {
        content?.apply {
            hideProgress()
            crvContainerCats.setVisibilityBool(true)
        }
    }


    fun setCatsCatched(models: List<CatCatched>?) {
        adapter.models = models ?: emptyList()
    }


    private fun chooseCat(id: String) {
        fragment.chooseCat(id)
    }


    private fun showProgress() {
        content?.prgLoad?.setVisibilityBool(true)
    }


    private fun hideProgress() {
        content?.prgLoad?.setVisibilityBool(false)
    }


    fun attachView(view: View) {
        content = view
    }
}