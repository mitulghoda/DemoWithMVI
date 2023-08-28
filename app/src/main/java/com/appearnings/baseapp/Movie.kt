package com.appearnings.baseapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isSelected: Boolean = false,
) : Parcelable
