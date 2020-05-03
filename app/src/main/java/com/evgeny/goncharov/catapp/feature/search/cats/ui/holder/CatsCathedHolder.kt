package com.evgeny.goncharov.catapp.feature.search.cats.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import kotlinx.android.synthetic.main.holder_cathed_cat.view.*

class CatsCathedHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(catCatched: CatCatched) {
        itemView.apply {
            txvTitleName.text = catCatched.catName
            txvIdTitle.text = catCatched.catId

            setOnClickListener {
                //TODO клик на выбранного кота
            }
        }
    }


}