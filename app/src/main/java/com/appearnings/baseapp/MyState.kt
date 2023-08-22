package com.appearnings.baseapp

data class MyState(
    val isLoading: Boolean,
    val title: String,
    val movies: List<Movie>,
    val movie: Movie?,
) {

    companion object {
        val initial = MyState(
            isLoading = true, title = "IMDb\nTop 10 Movies", movies = emptyList(), movie = null
        )
    }
}
