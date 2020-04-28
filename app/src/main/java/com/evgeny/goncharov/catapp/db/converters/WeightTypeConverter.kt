package com.evgeny.goncharov.catapp.db.converters

import androidx.room.TypeConverter
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.WeightResponse

class WeightTypeConverter {


    @TypeConverter
    fun fromWeight(model: WeightResponse): String {
        return "${model.imperial};${model.metric}"
    }

    @TypeConverter
    fun toWeight(model: String): WeightResponse {
        val models = model.split(";")
        return WeightResponse(
            imperial = models[0],
            metric = models[1]
        )
    }

}