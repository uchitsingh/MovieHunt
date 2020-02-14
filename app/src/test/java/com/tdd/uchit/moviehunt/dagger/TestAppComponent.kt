package com.tdd.uchit.moviehunt.dagger

import com.tdd.uchit.moviehunt.viewmodel.MovieViewModelTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface TestAppComponent : AppComponent {
    fun inject(movieViewModelTest: MovieViewModelTest)
}