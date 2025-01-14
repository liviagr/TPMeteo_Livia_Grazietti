package csc.livia.weatherapp.data.source.api

import androidx.test.espresso.core.internal.deps.dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import kotlinx.coroutines.flow.internal.NopCollector.emit

@Provides
fun createRetrofit(baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
