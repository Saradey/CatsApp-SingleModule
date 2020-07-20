package com.evgeny.goncharov.catapp.feature.wall.cats.ui.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedView
import kotlinx.android.synthetic.main.holder_cat_breed.view.*

class CatBreedViewHolder(view: View, private val listener: CatBreedViewHolderListener) :
    RecyclerView.ViewHolder(view) {

    fun bind(item: CatBreedView?) {
        item?.let {
            itemView.txvBreedName.text = item.name
            itemView.txvBreedDescription.text = item.description
            Glide.with(itemView)
                .load(item.urlImage)
                .apply(
                    RequestOptions()
                        .override(100, 100)
                        .centerCrop()
                )
                .into(itemView.imvShowCat)
            itemView.imbWiki.setOnClickListener {
                listener.clickCatUrlBreed(item.wikipediaUrl)
            }

            itemView.cnlContainerCat.setOnClickListener {
                listener.clickCatBreed(item.id)
            }
        }
    }


    interface CatBreedViewHolderListener {
        fun clickCatBreed(id: String?)
        fun clickCatUrlBreed(urlImage: String?)
    }

}