package com.barros.architecturecompare.mvp

import android.util.Log
import com.barros.architecturecompare.model.RedditItem
import com.barros.architecturecompare.model.RedditResponse
import com.barros.architecturecompare.model.RedditService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MvpPresenter(private val view: View, private val search: String) {

    private val TAG = MvpPresenter::class.qualifiedName

    init {
        fetchItems()
    }

    private fun fetchItems() {
        RedditService().getResult(search).enqueue(object : Callback<RedditResponse> {
            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                Log.e(TAG, "Error -> ${t.message}")
                view.onError()
            }

            override fun onResponse(
                call: Call<RedditResponse>,
                response: Response<RedditResponse>
            ) {
                val redditItemList = response.body()?.data?.children?.map {
                    val item = it.data
                    RedditItem(item.title, item.thumbnail)
                }
                view.setValues(redditItemList ?: mutableListOf())
                Log.d(TAG, "Success -> size: ${redditItemList?.size}")
            }
        })
    }

    fun onRefresh() {
        fetchItems()
    }

    interface View {
        fun setValues(values: List<RedditItem>)
        fun onError()
    }
}
