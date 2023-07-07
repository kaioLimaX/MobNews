package com.skydevices.mobnews.model.db

import androidx.room.TypeConverter
import com.skydevices.mobnews.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}