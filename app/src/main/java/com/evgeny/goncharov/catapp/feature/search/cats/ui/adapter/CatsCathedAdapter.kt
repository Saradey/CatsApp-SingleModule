package com.evgeny.goncharov.catapp.feature.search.cats.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.search.cats.ui.holder.CatsCathedHolder

class CatsCathedAdapter(
    private val listener: CatsCathedHolder.CatsCathedHolderListener
) : RecyclerView.Adapter<CatsCathedHolder>() {

    var models: List<CatCatched> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsCathedHolder {
        return CatsCathedHolder(
            listener,
            LayoutInflater.from(parent.context).inflate(R.layout.holder_cathed_cat, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return models.size
    }

    
    override fun onBindViewHolder(holder: CatsCathedHolder, position: Int) {
        holder.bind(models[position])
    }
}