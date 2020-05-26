package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.CatDescriptionSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.CatDescriptionFragment
import kotlinx.android.synthetic.main.fragment_cat_description.view.*
import javax.inject.Inject

class CatDescriptionView {

    @Inject
    lateinit var fragment: CatDescriptionFragment

    private var content: View? = null

    fun init() {
        CatDescriptionSubcomponent.component?.inject(this)
        initUi()
    }


    private fun initUi() {
        initToolbar()
    }


    private fun initToolbar() {
        content?.apply {
            (toolbar as Toolbar).apply {
                setNavigationIcon(R.drawable.ic_arrow_back_black)
                setNavigationOnClickListener {
                    fragment.clickBack()
                }
                setTitle(R.string.description_cat_title_toolbar)
            }
        }
    }


    fun setCatDescription(model: CatDescriptionModel?) {
        model?.let {
            content?.apply {
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
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    }
                }
                Glide.with(this)
                    .load(model.urlImage)
                    .centerCrop()
                    .into(imvCat)
            }
        }
    }


    fun showAllContent() {
        hideProgress()
        content?.apply {
            grpAllContent.setVisibilityBool(true)
        }
    }


    fun attachView(view: View) {
        content = view
    }


    fun showProgress() {
        content?.prgLoad?.setVisibilityBool(true)
    }


    private fun hideProgress() {
        content?.prgLoad?.setVisibilityBool(false)
    }


    fun showStubSomethingWrong() {
        content?.prgLoad?.setVisibilityBool(false)
        content?.grpStubWallCat?.setVisibilityBool(true)
    }
}