package com.evgeny.goncharov.catapp.feature.wall.cats.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.CatDescriptionSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescription
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.CatDescriptionEvents
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.ICatDescriptionViewModel
import kotlinx.android.synthetic.main.fragment_cat_description.*
import javax.inject.Inject

class CatDescriptionFragment : BaseFragment() {

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


    override fun getLayoutId(): Int {
        return R.layout.fragment_cat_description
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        initLiveData()
    }


    private fun initUi() {
        initToolbar()
    }


    private fun initLiveData() {
        initUiEventsLiveData()
        initCatDescriptionLiveData()
    }


    private fun initCatDescriptionLiveData() {
        viewModel.getCatDescriptionLiveData().observe(this, Observer {
            setCatDescription(it)
        })
    }


    private fun initUiEventsLiveData() {
        uiLiveData = viewModel.getLiveDataUiEvents()
        uiLiveData.observe(this, Observer {
            when (it) {
                CatDescriptionEvents.EventShowProgress -> showProgress()
                CatDescriptionEvents.EventHideProgressAndShowContent -> showAllContent()
                CatDescriptionEvents.EventHideProgressAndShowSomethingWrong -> showStubSomethingWrong()
            }
        })
    }


    fun setCatId(catId: String) {
        this.catId = catId
    }


    private fun initToolbar() {
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black)
            setNavigationOnClickListener {
                viewModel.clickBack()
            }
            setTitle(R.string.description_cat_title_toolbar)
        }
    }


    private fun setCatDescription(model: CatDescription?) {
        model?.let {
            txvNameCat.text = resources.getString(R.string.name_cat_title, model.name)
            txvOrigin.text = resources.getString(R.string.origin_cat_title, model.origin)
            txvWeight.text = resources.getString(R.string.weight_cat_title, model.weight)
            txvLifeSpan.text = resources.getString(R.string.life_span_cat_title, model.lifeSpan)
            txvTemperament.text =
                resources.getString(R.string.temperament_cat_title, model.temperament)
            txvDescription.text =
                resources.getString(R.string.description_cat_title, model.description)
            llButtonWiki.setOnClickListener {
                val uri = Uri.parse(model.urlWiki)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                if (intent.resolveActivity(context!!.packageManager) != null) {
                    context?.startActivity(intent)
                }
            }
            Glide.with(this)
                .load(model.urlImage)
                .centerCrop()
                .into(imvCat)
        }
    }


    private fun showAllContent() {
        hideProgress()
        grpAllContent.setVisibilityBool(true)
    }


    override fun onDestroy() {
        super.onDestroy()
        CatDescriptionSubcomponent.component = null
        (uiLiveData as SingleLiveEvent<CatDescriptionEvents>).call()
    }
}