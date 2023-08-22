package com.appearnings.baseapp

sealed class MyEvent {
    object Loading : MyEvent()
    data class DataLoaded(val movieList: List<Movie>) : MyEvent()
    data class ClickOnData(val movie: Movie) : MyEvent()
}
