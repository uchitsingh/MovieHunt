package com.tdd.uchit.moviehunt.utils

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tdd.uchit.moviehunt.data.model.Data

class DataConverter {

    @TypeConverter
    fun fromMovieResponseDataList(data: List<Data>?): String? {
        if (data == null) return null
        val type = object : TypeToken<List<Data>>() {}.type
        return Gson().toJson(data, type)
    }

    @TypeConverter
    fun toMovieResponseDataList(dataString: String?): List<Data>? {
        if (dataString == null) return null
        val type = object : TypeToken<List<Data>>() {}.type
        return Gson().fromJson<List<Data>>(dataString, type)
    }
}