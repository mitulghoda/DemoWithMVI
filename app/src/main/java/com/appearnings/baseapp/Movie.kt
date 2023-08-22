package com.appearnings.baseapp

data class Movie(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isSelected: Boolean = false,
)
