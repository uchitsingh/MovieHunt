package com.tdd.uchit.moviehunt

import android.app.Application
import com.tdd.uchit.moviehunt.dagger.AppComponent
import com.tdd.uchit.moviehunt.dagger.AppModule
import com.tdd.uchit.moviehunt.dagger.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger()
        Timber.plant(Timber.DebugTree())
    }

    private fun initDagger(): AppComponent =
        DaggerAppComponent.builder().appModule(AppModule(this))
            .build()
}