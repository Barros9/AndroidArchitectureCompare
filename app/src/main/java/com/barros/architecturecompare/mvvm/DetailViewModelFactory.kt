package com.barros.architecturecompare.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.barros.architecturecompare.model.RedditItem

class DetailViewModelFactory(
    private val item: RedditItem
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return modelClass.getConstructor(RedditItem::class.java).newInstance(item)
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
