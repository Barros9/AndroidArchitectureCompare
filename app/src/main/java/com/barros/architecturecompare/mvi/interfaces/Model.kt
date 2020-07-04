package com.barros.architecturecompare.mvi.interfaces

import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel

interface Model<S : State, I : Intent> {
    val state: LiveData<S>
    val intents: Channel<I>
}
