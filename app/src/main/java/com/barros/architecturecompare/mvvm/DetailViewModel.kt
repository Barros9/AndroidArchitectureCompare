package com.barros.architecturecompare.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barros.architecturecompare.model.RedditItem

class DetailViewModel(item: RedditItem) : ViewModel() {

    private val _redditItem = MutableLiveData<RedditItem>()
    val redditItem: LiveData<RedditItem>
        get() = _redditItem

    init {
        _redditItem.value = item
    }
}
