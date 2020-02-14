package com.tdd.uchit.moviehunt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tdd.uchit.moviehunt.utils.DataConverter
import com.tdd.uchit.moviehunt.data.model.MovieResponse

@Database(entities = [MovieResponse::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}