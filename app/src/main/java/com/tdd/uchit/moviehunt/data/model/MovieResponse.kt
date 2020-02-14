package com.tdd.uchit.moviehunt.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tdd.uchit.moviehunt.utils.DataConverter

@Entity(tableName = "movies")
@JsonClass(generateAdapter = true)
data class MovieResponse(

    @PrimaryKey
    @ColumnInfo(name = "data")
    @Json(name = "data")
    @TypeConverters(DataConverter::class)
    val `data`: List<Data>
)