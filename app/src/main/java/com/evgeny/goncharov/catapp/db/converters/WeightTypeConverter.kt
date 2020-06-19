package com.evgeny.goncharov.catapp.db.converters

import androidx.room.TypeConverter
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.WeightDTO

class WeightTypeConverter {


    @TypeConverter
    fun fromWeight(model: WeightDTO): String {
        return "${model.imperial};${model.metric}"
    }

    @TypeConverter
    fun toWeight(model: String): WeightDTO {
        val models = model.split(";")
        return WeightDTO(
            imperial = models[0],
            metric = models[1]
        )
    }

}