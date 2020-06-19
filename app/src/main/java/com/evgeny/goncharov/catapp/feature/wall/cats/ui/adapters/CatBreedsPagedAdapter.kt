package com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedValueObject
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.holders.CatBreedViewHolder

class CatBreedsPagedAdapter(
    diffUtils: DiffUtil.ItemCallback<CatBreedValueObject>,
    private val listener: CatBreedViewHolder.CatBreedViewHolderListener
) : PagedListAdapter<CatBreedValueObject, CatBreedViewHolder>(diffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.holder_cat_breed, parent, false)
        return CatBreedViewHolder(view, listener)
    }


    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}