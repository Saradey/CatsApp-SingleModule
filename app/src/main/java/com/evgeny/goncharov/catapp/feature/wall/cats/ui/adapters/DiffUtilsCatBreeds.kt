package com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedValueObject

class DiffUtilsCatBreeds : DiffUtil.ItemCallback<CatBreedValueObject>() {

    override fun areItemsTheSame(oldItem: CatBreedValueObject, newItem: CatBreedValueObject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatBreedValueObject, newItem: CatBreedValueObject): Boolean {
        return oldItem == newItem
    }

}