package com.tdd.uchit.moviehunt.dagger

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tdd.uchit.moviehunt.App
import com.tdd.uchit.moviehunt.data.db.MovieDatabase
import com.tdd.uchit.moviehunt.data.db.MovieDao
import com.tdd.uchit.moviehunt.data.remote.MovieService
import com.tdd.uchit.moviehunt.data.repository.MovieRepository
import com.tdd.uchit.moviehunt.data.repository.MovieRepositoryImpl
import com.tdd.uchit.moviehunt.utils.Constant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class AppModule(private var app: App) {

    @Provides
    @Singleton
    open fun provideMovieRepository(
        movieDao: MovieDao,
        movieService: MovieService
    ): MovieRepository {
        return MovieRepositoryImpl(movieDao, movieService)
    }

    @Provides
    @Singleton
    open fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()

    @Provides
    @Singleton
    open fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    open fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    open fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    open fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(1000, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    open fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    open fun provideAppDatabase(context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, Constant.MOVIE_DATABASE).build()

    @Provides
    @Singleton
    open fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    open fun provideApp(): App = app

}