package com.barros.architecturecompare.mvvm

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barros.architecturecompare.model.RedditApi
import com.barros.architecturecompare.model.RedditItem
import com.barros.architecturecompare.model.RedditResponse
import com.barros.architecturecompare.model.RedditService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MvvmViewModel(private val search: String) : ViewModel() {

    private val tag = MvvmViewModel::class.qualifiedName

    private var _items = MutableLiveData<List<RedditItem>>()
    val items: LiveData<List<RedditItem>>
        get() = _items

    private val _navigateToItem = MutableLiveData<RedditItem>()
    val navigateToItem: LiveData<RedditItem>
        get() = _navigateToItem

    var errorState = MutableLiveData(false)
    val observedGridState = ObservableBoolean(false)
    val observedProgressBarState = ObservableBoolean(false)

    init {
        fetchItems()
    }

    private fun fetchItems() {
        observedProgressBarState.set(true)

        RedditService().createService(RedditApi::class.java).getTop(search)
            .enqueue(object : Callback<RedditResponse> {
                override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                    errorState.value = true
                    observedGridState.set(false)
                    observedProgressBarState.set(false)
                    Log.e(tag, "Error -> ${t.message}")
                }

                override fun onResponse(
                    call: Call<RedditResponse>,
                    response: Response<RedditResponse>
                ) {
                    val redditItemList = response.body()?.data?.children?.map {
                        val item = it.data
                        RedditItem(item.title, item.thumbnail)
                    }
                    _items.value = redditItemList
                    errorState.value = false
                    observedGridState.set(true)
                    observedProgressBarState.set(false)
                    Log.d(tag, "Success -> size: ${redditItemList?.size}")
                }
            })
    }

    fun onRetry() {
        fetchItems()
        observedProgressBarState.set(true)
        observedGridState.set(false)
        errorState.value = false
    }

    fun displayPropertyDetails(item: RedditItem) {
        _navigateToItem.value = item
    }

    fun displayPropertyDetailsComplete() {
        _navigateToItem.value = null
    }
}
