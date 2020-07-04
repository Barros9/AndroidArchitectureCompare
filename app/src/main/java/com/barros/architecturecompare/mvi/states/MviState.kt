package com.barros.architecturecompare.mvi.states

import com.barros.architecturecompare.model.RedditItem
import com.barros.architecturecompare.mvi.interfaces.State

data class MviState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val redditItemList: List<RedditItem> = listOf()
) : State
