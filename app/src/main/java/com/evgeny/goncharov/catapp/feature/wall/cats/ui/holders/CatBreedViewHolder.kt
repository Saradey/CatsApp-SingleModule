package com.evgeny.goncharov.catapp.feature.wall.cats.ui.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import kotlinx.android.synthetic.main.item_cat_breed.view.*

class CatBreedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: CatBreedModel?) {
        item?.let {
            itemView.txvBreedName.text = item.name
            itemView.txvBreedDescription.text = item.description
            itemView.imbWiki.setOnClickListener {
                //TODO попытаться открыть неявным интентом
            }
        }
    }


}