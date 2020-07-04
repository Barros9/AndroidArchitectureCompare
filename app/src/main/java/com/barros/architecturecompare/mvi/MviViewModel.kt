package com.barros.architecturecompare.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barros.architecturecompare.model.RedditItem
import com.barros.architecturecompare.model.RedditServiceCoroutines
import com.barros.architecturecompare.mvi.interfaces.Model
import com.barros.architecturecompare.mvi.states.MviIntent
import com.barros.architecturecompare.mvi.states.MviState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MviViewModel : ViewModel(), Model<MviState, MviIntent> {

    override val intents: Channel<MviIntent> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData<MviState>().apply { value = MviState() }
    override val state: LiveData<MviState>
        get() = _state

    var search = MutableLiveData<String>("")

    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { mviIntent ->
                when (mviIntent) {
                    MviIntent.FetchList -> fetchData()
                    MviIntent.RefreshList -> fetchData()
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { it.copy(isLoading = true) }
                val redditItemList =
                    RedditServiceCoroutines.getResultListCoroutines()
                        .getTop(search.value!!).data.children.map { response ->
                            RedditItem(response.data.title, response.data.thumbnail)
                        }
                updateState { it.copy(isLoading = false, redditItemList = redditItemList) }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    private fun updateState(handler: suspend (state: MviState) -> MviState) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = handler(state.value!!)
        }
    }
}
