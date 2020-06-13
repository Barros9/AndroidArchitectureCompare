package com.barros.architecturecompare.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RedditService {

    private val api: RedditApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        api = retrofit.create(RedditApi::class.java)
    }

    fun getResult(value: String): Call<RedditResponse> {
        return api.getTop(value)
    }

    companion object {
        private const val BASE_URL = "https://www.reddit.com/r/"
    }
}
