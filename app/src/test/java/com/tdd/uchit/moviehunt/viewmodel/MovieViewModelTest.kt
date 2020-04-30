package com.tdd.uchit.moviehunt.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tdd.uchit.moviehunt.App
import com.tdd.uchit.moviehunt.dagger.DaggerTestAppComponent
import com.tdd.uchit.moviehunt.dagger.TestAppModule
import com.tdd.uchit.moviehunt.data.model.Data
import com.tdd.uchit.moviehunt.data.model.MovieResponse
import com.tdd.uchit.moviehunt.data.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Maybe
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import javax.inject.Inject

@RunWith(BlockJUnit4ClassRunner::class)
class MovieViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Inject
    lateinit var movieRepository: MovieRepository

    @MockK
    private lateinit var movieeViewModel: MovieViewModel

    private val datas = listOf(
        Data("genre", 1, "pster", "title", "year"," plot"),
        Data("genre1", 1, "pster1", "title1", "year1", "plot1"),
        Data("genre2", 2, "pster2", "title2", "year2", "plor2")
    )

    private val datas1 = listOf(
        Data("genre1", 1, "pster1", "title1", "year1", "plot1")
    )
    private val movieResponse = MovieResponse(datas)
    private val movieResponse1 = MovieResponse(datas1)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        val testComponent = DaggerTestAppComponent.builder()
            .appModule(TestAppModule(App()))
            .build()

        testComponent.inject(this)
        movieeViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun fetchMovies_successTest() {
        //given
        every { movieRepository.fetchMovies() } returns(Maybe.just(movieResponse))

        //when
        movieeViewModel.fetchMovies()

        //then
        Assert.assertEquals(movieResponse, movieeViewModel.moviesObservable.value)
    }

    @Test
    fun fetchMovies_search_genreTest() {
        val genreSearch = "genre1    "

        //given
        every { movieRepository.fetchMovies() } returns(Maybe.just(movieResponse))

        //when
        movieeViewModel.fetchMovies()

        //then
        Assert.assertEquals(movieResponse, movieeViewModel.moviesObservable.value)

        movieeViewModel.search(genreSearch)

        Assert.assertEquals(movieResponse1, movieeViewModel.searchObservable.value)
    }
}