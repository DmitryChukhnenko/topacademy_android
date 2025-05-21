package com.example.topacademy_android

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient

interface WeatherApi {
    @GET("bin/api.pl")
    suspend fun getWeatherForecast(
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("product") product: String = "civillight",
        @Query("output") output: String = "json"
    ): Response<WeatherResponse>
}

object RetrofitClient {
    private const val BASE_URL = "https://www.7timer.info/"

    val weatherApi: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(WeatherApi::class.java)
    }
}