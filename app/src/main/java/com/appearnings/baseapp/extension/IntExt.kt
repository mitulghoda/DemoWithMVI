package com.appearnings.baseapp.extension

fun Int.toBoolean(): Boolean = (this == 1)
fun Int.hasFlag(flag: Int) = flag and this == flag
fun Int.withFlag(flag: Int) = this or flag
fun Int.minusFlag(flag: Int) = this and flag.inv()
