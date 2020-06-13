package com.barros.architecturecompare.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val search: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MvvmViewModel::class.java)) {
            return modelClass.getConstructor(String::class.java).newInstance(search)
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
