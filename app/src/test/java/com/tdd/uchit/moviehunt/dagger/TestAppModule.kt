package com.tdd.uchit.moviehunt.dagger

import android.content.Context
import com.squareup.moshi.Moshi
import com.tdd.uchit.moviehunt.App
import com.tdd.uchit.moviehunt.data.db.MovieDatabase
import com.tdd.uchit.moviehunt.data.db.MovieDao
import com.tdd.uchit.moviehunt.data.remote.MovieService
import com.tdd.uchit.moviehunt.data.repository.MovieRepository
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class TestAppModule(app: App) : AppModule(app) {

    override fun provideMovieRepository(
        movieDao: MovieDao,
        movieService: MovieService
    ): MovieRepository = mockk()

    override fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = mockk()
    override fun provideMovieService(retrofit: Retrofit): MovieService = mockk()
    override fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = mockk()
    override fun provideMoshi(): Moshi = mockk()
    override fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        mockk()

    override fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = mockk()
    override fun provideAppDatabase(context: Context): MovieDatabase = mockk()
    override fun provideContext(): Context = mockk()
    override fun provideApp(): App = mockk()
}

