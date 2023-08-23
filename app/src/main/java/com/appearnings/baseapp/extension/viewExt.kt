package com.appearnings.baseapp.extension

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

@ColorInt
fun View.getColor(@ColorRes colorRes: Int) = context.getColorCompat(colorRes)
fun View.getColorList(@ColorRes res: Int) = context.getColorListCompat(res)
fun View.getDrawable(@DrawableRes res: Int) = context.getDrawableCompat(res)

@ColorInt
fun View.getThemeColor(@AttrRes res: Int) = context.getThemeColor(res)


fun View.showTopSnackBar(
    @StringRes messageRes: Int,
    @BaseTransientBottomBar.Duration length: Int,
) {
    Snackbar.make(this, messageRes, length).apply {
        if (view.layoutParams is CoordinatorLayout.LayoutParams) {
            val params = view.layoutParams as CoordinatorLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
        } else if (view.layoutParams is FrameLayout.LayoutParams) {
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
        }
        animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        show()
    }
}


val ViewGroup.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)
