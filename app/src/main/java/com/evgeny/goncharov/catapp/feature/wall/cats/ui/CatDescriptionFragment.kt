package com.evgeny.goncharov.catapp.feature.wall.cats.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.CatDescriptionSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.CatDescriptionViewImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.ICatDescriptionView
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.ICatDescriptionViewModel
import javax.inject.Inject

class CatDescriptionFragment : BaseFragment<ICatDescriptionView>() {

    companion object {
        lateinit var component: CatDescriptionSubcomponent
        fun getInstance() = CatDescriptionFragment()
    }

    @Inject
    lateinit var factory: CatDescriptionSubcomponent.Factory

    @Inject
    lateinit var viewModel: ICatDescriptionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.component.inject(this)
        component = factory.plus()
        viewModel.initInjection()
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_cat_description
    }


    override fun init(content: View) {
        initView(content)
        initLiveData()
    }


    private fun initLiveData() {
        viewModel.getLiveDataUiEvents().observe(this, Observer {
            when (it) {
                BaseEventsUi.EventShowProgress -> view.showProgress()
                BaseEventsUi.EventHideProgress -> view.hideProgress()
                BaseEventsUi.EventSomethingWrong -> view.showStubSomethingWrong()
            }
        })
    }


    override fun initView(content: View) {
        view = CatDescriptionViewImpl()
        view.attachView(content)
    }


    fun setCatId(catId: String) {
        viewModel.setCatId(catId)
    }

}