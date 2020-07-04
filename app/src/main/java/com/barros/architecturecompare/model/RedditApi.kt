package com.barros.architecturecompare.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditApi {
    @GET("{value}/top.json")
    fun getTop(@Path("value") value: String): Call<RedditResponse>
}

interface RedditApiCoroutines {
    @GET("{value}/top.json")
    suspend fun getTop(@Path("value") value: String): RedditResponse
}
