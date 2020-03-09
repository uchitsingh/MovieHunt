package com.tdd.uchit.moviehunt.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "data")
@JsonClass(generateAdapter = true)
data class Data(
    @ColumnInfo(name = "genre")
    @Json(name = "genre")
    val genre: String?,
    @ColumnInfo(name = "id")
    @Json(name = "id")
    val id: Int?,
    @ColumnInfo(name = "poster")
    @Json(name = "poster")
    val poster: String?,
    @ColumnInfo(name = "title")
    @Json(name = "title")
    val title: String?,
    @ColumnInfo(name = "year")
    @Json(name = "year")
    val year: String?,
    @Json(name = "plot")
    val plot: String?
)