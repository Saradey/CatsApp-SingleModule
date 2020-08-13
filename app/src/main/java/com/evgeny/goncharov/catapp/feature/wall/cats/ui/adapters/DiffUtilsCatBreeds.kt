package com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedView

class DiffUtilsCatBreeds : DiffUtil.ItemCallback<CatBreedView>() {

    override fun areItemsTheSame(oldItem: CatBreedView, newItem: CatBreedView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatBreedView, newItem: CatBreedView): Boolean {
        return oldItem == newItem
    }
}