package com.evgeny.goncharov.catapp.feature.wall.cats.ui

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
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.CatDescriptionSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.CatDescriptionEvents
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.CatDescriptionView
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.ICatDescriptionViewModel
import kotlinx.android.synthetic.main.fragment_cat_description.*
import kotlinx.android.synthetic.main.fragment_cat_description.view.*
import javax.inject.Inject

class CatDescriptionFragment : Fragment() {

    companion object {
        fun getInstance(idCat: String?) = CatDescriptionFragment().apply {
            setCatId(idCat ?: "")
        }
    }

    @Inject
    lateinit var factory: CatDescriptionSubcomponent.Factory

    @Inject
    lateinit var viewModel: ICatDescriptionViewModel

    private var catId: String? = null

    private lateinit var uiLiveData: LiveData<CatDescriptionEvents>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySubcomponent.component.inject(this)
        CatDescriptionSubcomponent.component = factory.plus()
        if (savedInstanceState == null) {
            viewModel.initInjection()
            viewModel.setCatId(catId ?: "")
        }
        viewModel.loadChooseCat()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cat_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        initLiveData()
    }


    private fun initUi() {
        myView.initToolbar(::clickBack)
    }


    private fun initLiveData() {
        initUiEventsLiveData()
        initCatDescriptionLiveData()
    }


    private fun initCatDescriptionLiveData() {
        viewModel.getCatDescriptionLiveData().observe(this, Observer {
            myView.setCatDescription(it)
        })
    }


    private fun initUiEventsLiveData() {
        uiLiveData = viewModel.getLiveDataUiEvents()
        uiLiveData.observe(this, Observer {
            when (it) {
                CatDescriptionEvents.EventShowProgress -> myView.showProgress()
                CatDescriptionEvents.EventHideProgressAndShowContent -> myView.showAllContent()
                CatDescriptionEvents.EventHideProgressAndShowSomethingWrong -> myView.showStubSomethingWrong()
            }
        })
    }


    fun setCatId(catId: String) {
        this.catId = catId
    }


    fun clickBack() {
        viewModel.clickBack()
    }


    override fun onDestroy() {
        super.onDestroy()
        CatDescriptionSubcomponent.component = null
        (uiLiveData as SingleLiveEvent<CatDescriptionEvents>).call()
    }
}