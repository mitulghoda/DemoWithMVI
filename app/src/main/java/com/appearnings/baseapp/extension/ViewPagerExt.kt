package com.appearnings.baseapp.extension

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

val ViewPager2.recyclerView: RecyclerView
    get() = (getChildAt(0) as RecyclerView)
