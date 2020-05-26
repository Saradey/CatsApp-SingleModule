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
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.WallCatsSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.WallCatsEvents
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.WallCatsView
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.IWallCatsViewModel
import javax.inject.Inject

class WallCatsFragment : Fragment() {

    companion object {
        fun getInstance() = WallCatsFragment()
    }

    @Inject
    lateinit var factory: WallCatsSubcomponent.Factory

    @Inject
    lateinit var viewModel: IWallCatsViewModel

    private lateinit var myView: WallCatsView

    private lateinit var uiLiveData: LiveData<WallCatsEvents>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySubcomponent.component.inject(this)
        WallCatsSubcomponent.component = factory.plus()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wall_cats, container, false)
        init(view)
        return view
    }


    private fun init(content: View) {
        initView(content)
        viewModel.initInject()
        initLiveData()
        myView.init()
    }


    private fun initView(content: View) {
        myView = WallCatsView()
        myView.attachView(content)
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
                    myView.initSwipeRefreshLayout()
                }
            }
        })
    }


    fun clickCatUrlBreed(urlImage: String?) {
        urlImage?.let {
            val uri = Uri.parse(urlImage)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            if (intent.resolveActivity(activity?.packageManager!!) != null) {
                startActivity(intent)
            }
        }
    }


    fun clickCatBreed(id: String?) {
        id?.let {
            viewModel.clickCatBreed(id)
        }
    }

    fun clickMenuSearchCat() {
        viewModel.clickMenuSearchCat()
    }


    fun clickMenuSettings() {
        viewModel.clickMenuSettings()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        (uiLiveData as SingleLiveEvent<WallCatsEvents>).call()
    }
}