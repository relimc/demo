package com.sunnyweather.android.logic.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {
    private val placeService = ServiceCreator.create<PlaceService>()
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    private val weatherService = ServiceCreator.create(WeatherService::class.java)
    suspend fun getDailyWeather(lng: String, lat: String) = weatherService.getDailyWeather(lng, lat).await()
    suspend fun getRealtimeWeather(lng: String, lat: String) = weatherService.getRealtimeWeather(lng, lat).await()

    // 给 Call 类新增一个拓展函数 await，await 是个挂起函数
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) it.resume(body)
                    else it.resumeWithException(java.lang.RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
        }
    }
}