package com.barros.architecturecompare.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barros.architecturecompare.model.RedditApi
import com.barros.architecturecompare.model.RedditItem
import com.barros.architecturecompare.model.RedditService
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
                updateState { MviState(isLoading = true) }
                updateState {
                    MviState(
                        redditItemList = RedditService().createService(RedditApi::class.java)
                            .getTopCoroutines(search.value!!).data.children.map { response ->
                                RedditItem(response.data.title, response.data.thumbnail)
                            }
                    )
                }
            } catch (e: Exception) {
                updateState { MviState(isError = true) }
            }
        }
    }

    private suspend fun updateState(handler: suspend (state: MviState) -> MviState) {
        _state.postValue(handler(state.value!!))
    }
}
