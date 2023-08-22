package com.appearnings.baseapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appearnings.baseapp.MyEvent.Loading
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    val state = StateReducerFlow(
        initialState = MyState.initial,
        reduceState = ::reduceState,
    )

    private fun reduceState(
        currentState: MyState,
        event: MyEvent,
    ): MyState = when (event) {
        is Loading,
        -> {
            callApiToGetData()
            currentState.copy(isLoading = true, title = "Loading")
        }

        is MyEvent.ClickOnData -> {
            currentState.copy(
                isLoading = false,
                title = "Clicked state",
                movie = event.movie,
                movies = emptyList()
            )
        }

        is MyEvent.DataLoaded -> {
            clickOnSingleData()
            currentState.copy(
                isLoading = false, title = "Data Loaded", movies = arrayListOf(
                    Movie(
                        id = 1,
                        name = "asjkdhajksdh",
                        imageUrl = "asdfajkdhkasj",
                        isSelected = false
                    )
                )
            )
        }
    }

    fun clickOnSingleData() = viewModelScope.launch {
        state.handleEvent(
            MyEvent.ClickOnData(
                Movie(
                    id = 1, name = "asjkdhajksdh", imageUrl = "asdfajkdhkasj", isSelected = false
                )
            )
        )
    }

    private fun callApiToGetData() = viewModelScope.launch {
        //TODO call api to get list
        state.handleEvent(MyEvent.DataLoaded(arrayListOf()))
    }
}
