package csc.livia.weatherapp

import android.app.Application

import dagger.hilt.android.HiltAndroidApp



@HiltAndroidApp
class WeatherApplication: Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
//    lateinit var container: AppContainer
//
//    override fun onCreate() {
//        super.onCreate()
//        container = AppContainerImpl(this)
//    }
    override fun onCreate() {
        super.onCreate()

    }

}