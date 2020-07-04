package com.barros.architecturecompare.mvi.interfaces

interface View<S : State> {
    fun render(state: S)
}
