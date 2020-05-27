package com.evgeny.goncharov.catapp.feature.wall.cats.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.extension.injectViewModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.WallCatsEvents
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.holders.CatBreedViewHolder
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.WallCatsViewModel
import kotlinx.android.synthetic.main.fragment_wall_cats.*
import kotlinx.android.synthetic.main.fragment_wall_cats.view.*
import javax.inject.Inject

class WallCatsFragment : Fragment(), CatBreedViewHolder.CatBreedViewHolderListener {

    companion object {
        fun getInstance() = WallCatsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WallCatsViewModel

    private lateinit var uiLiveData: LiveData<WallCatsEvents>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySubcomponent.component.inject(this)
        viewModel = injectViewModel(viewModelFactory)
        initLiveData()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return with(inflater.inflate(R.layout.fragment_wall_cats, container, false)) {
            myView.init()
            this
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }


    private fun initUi() {
        myView.initToolbar(::clickMenuSearchCat, ::clickMenuSettings)
        myView.initPagedAdapterAndRecycle(viewModel, this)
        myView.initFirstSwipeRefreshLayout()
    }


    private fun initLiveData() {
        uiLiveData = viewModel.getUiEventsLiveData()
        uiLiveData.observe(this, Observer {
            when (it) {
                WallCatsEvents.EventShowProgressAndHideStub -> {
                    myView.hideStubSomethingWrong()
                    myView.showProgress()
                }
                WallCatsEvents.EventSomethingWrong -> {
                    myView.showStubSomethingWrong()
                }
                WallCatsEvents.EventHideProgressAndInitRefreshLayout -> {
                    myView.hideProgress()
                    myView.initSwipeRefreshLayout(viewModel, this)
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


    private fun clickMenuSearchCat() {
        viewModel.clickMenuSearchCat()
    }


    private fun clickMenuSettings() {
        viewModel.clickMenuSettings()
    }


    override fun onDestroy() {
        super.onDestroy()
        (uiLiveData as SingleLiveEvent<WallCatsEvents>).call()
    }
}