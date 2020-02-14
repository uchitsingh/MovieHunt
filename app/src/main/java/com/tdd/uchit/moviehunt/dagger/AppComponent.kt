package com.tdd.uchit.moviehunt.dagger

import com.tdd.uchit.moviehunt.ui.MovieActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(movieActivity: MovieActivity)
}