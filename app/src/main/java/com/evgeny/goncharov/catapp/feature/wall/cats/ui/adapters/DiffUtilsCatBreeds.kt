package com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel

class DiffUtilsCatBreeds : DiffUtil.ItemCallback<CatBreedModel>() {

    override fun areItemsTheSame(oldItem: CatBreedModel, newItem: CatBreedModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatBreedModel, newItem: CatBreedModel): Boolean {
        return oldItem == newItem
    }

}