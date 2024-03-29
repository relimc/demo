package com.example.hilttest

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityComponent::class)
class NetworkUtil {
    @Provides
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }
}