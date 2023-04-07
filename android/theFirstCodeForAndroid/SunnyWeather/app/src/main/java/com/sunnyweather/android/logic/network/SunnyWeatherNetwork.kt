package com.sunnyweather.android.logic.network

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val TAG = "SunnyWeatherNetwork"

object SunnyWeatherNetwork {
    private val placeService = ServiceCreator.create<PlaceService>()

    // 根据查询字符串，获取对应的地理信息
    // placeService.searchPlaces(query) 封装了一个网络请求，，其返回值是个 Call 对象

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    private val weatherService = ServiceCreator.create(WeatherService::class.java)
    suspend fun getDailyWeather(lng: String, lat: String) = weatherService.getDailyWeather(lng, lat).await()
    suspend fun getRealtimeWeather(lng: String, lat: String) = weatherService.getRealtimeWeather(lng, lat).await()

    // 给 Call 类新增一个拓展函数 await，await 是个挂起函数
    // await 方法用于发送网络请求，处理服务器返回的响应结果，并将其返回，await 会保证网络请求再完成后，在运行协程中的其他代码
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {
            /*
            调用 Call 对象的 enqueue 就会发起网络请求，网络请求耗时的 IO 操作
            Android 不允许在主线程发送网络请求，我们要在子线程或者协程中发起网络请求
            在这里，我们调用 suspendCoroutine 函数来让 enqueue 方法在普通线程中执行
            suspendCoroutine 函数必须在协程作用域或者另一个挂起函数中调用
            await 方法在这里被定义为挂起函数，所以 suspendCoroutine 在这里可以正常使用
            suspendCoroutine 会挂起外部的协程，直到调用 Continuation 的 resume 或者 resumeWithException 方法恢复协程的运行
             */
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    Log.d(TAG, body.toString())
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