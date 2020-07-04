package com.barros.architecturecompare.mvi.states

import com.barros.architecturecompare.mvi.interfaces.Intent

sealed class MviIntent : Intent {
    object RefreshList : MviIntent()
    object FetchList : MviIntent()
}
