package com.barros.architecturecompare.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RedditService {

    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun <T> createService(service: Class<T>): T = retrofit.create(service)

    companion object {
        const val BASE_URL = "https://www.reddit.com/r/"
    }
}
