package com.evgeny.goncharov.catapp.feature.search.cats.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.extension.setHintTextColor
import com.evgeny.goncharov.catapp.extension.setTextColor
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.search.cats.ui.adapter.CatsCathedAdapter
import kotlinx.android.synthetic.main.fragment_search_cat.view.*

class SearchCatView : ConstraintLayout {

    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, @StyleRes style: Int) : super(
        context,
        attr,
        style
    )

    private lateinit var adapter: CatsCathedAdapter


    fun initAdapterAndRecycle(listener: (id: String) -> Unit) {
        adapter = CatsCathedAdapter(listener)
        rcvCathedCats.layoutManager = LinearLayoutManager(context)
        rcvCathedCats.adapter = adapter
    }


    fun initToolbar(clickNavigationBack: () -> Unit) {
        (toolbar as Toolbar).apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black)
            setNavigationOnClickListener {
                clickNavigationBack()
            }
            setTitle(R.string.title_toolbar_search_cat)
        }
    }


    fun initSearchView(setInputTextSearchView: (newText: String?) -> Unit) {
        srcSearchCat.onActionViewExpanded()
        srcSearchCat.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = true
                override fun onQueryTextChange(newText: String?): Boolean {
                    setInputTextSearchView(newText)
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


    fun hideStubAndListAndShowProgress() {
        crvContainerCats.setVisibilityBool(false)
        txvCatsStubNotFound.setVisibilityBool(false)
        showProgress()
    }


    fun hideProgressAndShowStub() {
        txvCatsStubNotFound.setVisibilityBool(true)
        hideProgress()
    }


    fun hideProgressAndShowModels() {
        hideProgress()
        crvContainerCats.setVisibilityBool(true)
    }


    fun setCatsCatched(models: List<CatCatched>?) {
        adapter.models = models ?: emptyList()
    }


    private fun showProgress() {
        prgLoad?.setVisibilityBool(true)
    }


    private fun hideProgress() {
        prgLoad?.setVisibilityBool(false)
    }
}