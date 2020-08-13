package com.evgeny.goncharov.catapp.db.converters

import androidx.room.TypeConverter
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.Weight

class WeightTypeConverter {


    @TypeConverter
    fun fromWeight(model: Weight): String {
        return "${model.imperial};${model.metric}"
    }

    @TypeConverter
    fun toWeight(model: String): Weight {
        val models = model.split(";")
        return Weight(
            imperial = models[0],
            metric = models[1]
        )
    }
}