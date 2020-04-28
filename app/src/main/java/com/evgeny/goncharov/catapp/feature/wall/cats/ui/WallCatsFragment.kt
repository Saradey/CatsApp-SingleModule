package com.evgeny.goncharov.catapp.feature.wall.cats.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.WallCatsSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.holders.CatBreedViewHolder
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.IWallCatsView
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.WallCatsViewImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.IWallCatsViewModel
import javax.inject.Inject

class WallCatsFragment : BaseFragment<IWallCatsView>(),
    CatBreedViewHolder.CatBreedViewHolderListener {

    @Inject
    lateinit var factory: WallCatsSubcomponent.Factory

    @Inject
    lateinit var viewModel: IWallCatsViewModel


    companion object {
        fun getInstance() = WallCatsFragment()
        lateinit var component: WallCatsSubcomponent
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.component.inject(this)
        component = factory.plus(this)
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_wall_cats
    }


    override fun init(content: View) {
        initView(content)
        viewModel.initInject()
        initLiveData()
        view.init()
    }


    override fun initView(content: View) {
        view = WallCatsViewImpl()
        view.attachView(content)
    }


    private fun initLiveData() {
        viewModel.getUiEventsLiveData().observe(this, Observer {
            when (it) {
                BaseEventsUi.EventsShowProgress -> view.showProgress()
                BaseEventsUi.EventsHideProgress -> view.hideProgress()
                BaseEventsUi.SomethingWrong -> view.showStubSomethingWrong()
            }
        })
    }


    override fun clickCatBreed(id: String?) {
        id?.let {
            viewModel.clickCatBreed(id)
        }
    }
}