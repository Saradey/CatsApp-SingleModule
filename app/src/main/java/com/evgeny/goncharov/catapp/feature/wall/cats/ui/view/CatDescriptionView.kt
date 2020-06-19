package com.evgeny.goncharov.catapp.feature.wall.cats.ui.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import androidx.annotation.StyleRes
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionDTO
import kotlinx.android.synthetic.main.fragment_cat_description.view.*

class CatDescriptionView : ConstraintLayout {

    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, @StyleRes style: Int) : super(
        context,
        attr,
        style
    )


    fun initToolbar(clickBack : () -> Unit) {
        (toolbar as Toolbar).apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black)
            setNavigationOnClickListener {
                clickBack()
            }
            setTitle(R.string.description_cat_title_toolbar)
        }
    }


    fun setCatDescription(model: CatDescriptionDTO?) {
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


    fun showAllContent() {
        hideProgress()
        grpAllContent.setVisibilityBool(true)
    }


    fun showProgress() {
        prgLoad?.setVisibilityBool(true)
    }


    private fun hideProgress() {
        prgLoad?.setVisibilityBool(false)
    }


    fun showStubSomethingWrong() {
        prgLoad?.setVisibilityBool(false)
        grpStubWallCat?.setVisibilityBool(true)
    }
}