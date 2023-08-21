package com.appearnings.baseapp.utility

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.*
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginTop
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appearnings.baseapp.Controller
import com.appearnings.baseapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.File
import java.text.DecimalFormat
import java.util.*
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow


object BindingAdaptersUtil {
    @JvmStatic
    @BindingAdapter(value = ["android:isVisible"], requireAll = false)
    fun isVisible(view: View, isVisible: Boolean) {
        if (isVisible) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["android:pStartColor", "android:pEndColor", "android:pTopLeftCorner", "android:pTopRightCorner", "android:pBottomLeftCorner", "android:pBottomRightCorner", "android:shapeOrientation"],
        requireAll = false
    )
    fun setBackgroundShape(
        view: View,
        pStartColor: String?,
        pEndColor: String?,
        pTopLeftCorner: Float?,
        pTopRightCorner: Float?,
        pBottomLeftCorner: Float?,
        pBottomRightCorner: Float?,
        shapeOrientation: GradientDrawable.Orientation?,
    ) {
        view.background = GradeDrawable(
            Color.parseColor(pStartColor ?: "#543bd4"),
            Color.parseColor(
                pEndColor ?: "#935dbf"
            ),
            pTopLeftCorner ?: 0F,
            pTopRightCorner ?: 0F,
            pBottomLeftCorner ?: 0F,
            pBottomRightCorner ?: 0F,
            shapeOrientation ?: GradientDrawable.Orientation.TOP_BOTTOM
        )
    }

    class GradeDrawable(
        pStartColor: Int,
        pEndColor: Int,
        pTopLeftCorner: Float,
        pTopRightCorner: Float,
        pBottomLeftCorner: Float,
        pBottomRightCorner: Float,
        shapeOrientation: Orientation,
    ) : GradientDrawable(shapeOrientation, intArrayOf(pStartColor, pEndColor)) {
        init {
            shape = RECTANGLE
            cornerRadii = floatArrayOf(
                pTopLeftCorner,
                pTopLeftCorner,
                pTopRightCorner,
                pTopRightCorner,
                pBottomLeftCorner,
                pBottomLeftCorner,
                pBottomRightCorner,
                pBottomRightCorner
            )
            //setCornerRadius(cornerRadius)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["android:isGone"], requireAll = false)
    fun isGone(view: View, isGone: Boolean) {
        if (isGone) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["android:isInvisible"], requireAll = false)
    fun isInvisible(view: View, isInvisible: Boolean) {
        if (isInvisible) {
            view.visibility = View.INVISIBLE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setBgColor"], requireAll = false)
    @Suppress("unused")
    fun setBgColor(view: View, color: String) {
        view.setBackgroundColor(Color.parseColor(color))
    }

    @JvmStatic
    @BindingAdapter(value = ["android:isSelected"], requireAll = false)
    fun isSelected(view: View, isSelected: Boolean) {
        view.isSelected = isSelected
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setImageSelector"], requireAll = false)
    @Suppress("unused")
    fun setImageSelector(view: ImageView, isSelected: Boolean) {
        if (isSelected) ViewUtil.setImageButtonSelector(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setBounceSelector"], requireAll = false)
    @Suppress("unused")
    fun setBounceSelector(view: View, isSelected: Boolean) {
        if (isSelected) ViewUtil.setBounceButtonSelector(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setButtonSelector"], requireAll = false)
    @Suppress("unused")
    fun setButtonSelector(view: View, isSelected: Boolean) {
        if (isSelected) ViewUtil.setButtonSelector(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setScaleSelector"], requireAll = false)
    @Suppress("unused")
    fun setScaleSelector(view: View, isSelected: Boolean) {
        if (isSelected) ViewUtil.setScaleButtonSelector(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["android:removeItemAnimator"], requireAll = false)
    @Suppress("unused")
    fun removeItemAnimator(view: RecyclerView, isRemoved: Boolean) {
        if (isRemoved) view.removeItemAnimator()
    }

    @JvmStatic
    @BindingAdapter(value = ["android:changeItemAnimatorDuration"], requireAll = false)
    @Suppress("unused")
    fun changeItemAnimatorDuration(view: RecyclerView, value: Long) {
        view.changeItemAnimatorDuration(value)
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setTextViewSelector"], requireAll = false)
    @Suppress("unused")
    fun setTextViewSelector(view: View, isSelected: Boolean) {
        if (isSelected) ViewUtil.setTextViewSelector(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setValue"], requireAll = false)
    fun setValue(view: View, value: String?) {
        if (value != null) {
            when (view) {
                is TextView -> {
                    view.text = value
                }

                is AppCompatTextView -> {
                    view.text = value
                }

                is EditText -> {
                    view.setText(value)
                }

                is TextInputEditText -> {
                    view.setText(value)
                }

                is Button -> {
                    view.text = value
                }

                is MaterialButton -> {
                    view.text = value
                }

                is AppCompatRadioButton -> {
                    view.text = value
                }
            }
        } else {
            when (view) {
                is TextView -> {
                    view.text = ""
                }
            }
        }
    }

    @JvmStatic
    @SuppressLint("DefaultLocale")
    @BindingAdapter(value = ["android:setPriceFloatOne", "android:setCurrency"], requireAll = false)
    fun setPriceFloatOne(textView: TextView?, price: Double?, currency: String?) {
        textView!!.text = (String.format(Locale.ENGLISH, "%.0f", price)).plus(" ").plus(currency)
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setStrike"], requireAll = false)
    fun setStrike(textView: TextView, isStrike: Boolean) {
        if (isStrike) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    @JvmStatic
    @BindingAdapter("app:tintColor")
    fun ImageView.setImageTint(color: Any) {
        if (color is Int) setColorFilter(color)
        else if (color is String) {
            setColorFilter(Color.parseColor(color))
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["android:imageUrl", "android:placeHolder", "android:imageSize"], requireAll = false
    )
    fun setImageUrl(imageView: ImageView, url: Any?, placeHolder: Drawable?, imageSize: Int = 0) {
        if (url != null) {
            when (url) {
                is String -> {
                    loadMedia(url, placeHolder, imageView, imageSize)
                }

                is File -> {
                    loadMedia(url, placeHolder, imageView, imageSize)
                }

                else -> {
                    loadMedia(url, placeHolder, imageView, imageSize)
                }
            }
        } else {
            imageView.setImageDrawable(placeHolder)
        }
    }


    private fun loadMedia(url: Any?, placeHolder: Drawable?, imageView: ImageView, imageSize: Int) {
        if (url!! == "dummy_amazon") {
            imageView.setImageResource(R.mipmap.ic_launcher_round)
        } else {
            getRequestOption(placeHolder, imageSize).let {
                Glide.with(Controller.instance.applicationContext).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).apply(it).into(imageView)
            }
        }
    }

    @BindingAdapter(value = ["android:imageResource"], requireAll = false)
    fun setImageResource(imageView: ImageView, icon: Int?) {
        icon?.let { imageView.setImageResource(it) }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["android:setTeam1ProgressRatio", "android:setTeam2ProgressRatio"],
        requireAll = true
    )
    fun setTeamProgressRatio(progressBar: ProgressBar, team1Progress: Int, team2Progress: Int) {
        progressBar.max = team1Progress + team2Progress
        progressBar.progress = when {
            team1Progress > team2Progress -> team1Progress
            team1Progress == team1Progress -> {
                team1Progress
            }

            else -> team2Progress
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setNumberPattern"], requireAll = false)
    fun setNumberPattern(textView: TextView, value: String?) {
        value?.let {
            if ((it == "null").not()) {
                val str = it.split(".")[0]
                val pattern: StringBuilder = StringBuilder(str.reversed())
                for (i in pattern.length downTo 1) {
                    if (i % 3 == 0) {
                        pattern.insert(i, "  ")
                    } else {
                        pattern.insert(i, "")
                    }
                }
                textView.text = pattern.reverse().toString().trim()
            }
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["android:setNumericAdverb", "android:setNumericAdverbPrefix", "android:setNumericAdverbPostfix", "android:setDecimalScale"],
        requireAll = false
    )
    fun setNumericAdverb(
        textView: TextView,
        actualValue: Double,
        prefix: String?,
        postfix: String?,
        decimalScale: String?,
    ) {
        val numberString: String
        var formattedString = ""
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val value = floor(log10(actualValue)).toInt()
        val base = value / 3
        numberString = if (value > 0) {
            if (value >= 3 && base < suffix.size && decimalScale.isNullOrEmpty().not()) {
                DecimalFormat(
                    decimalScale ?: "##0.00"
                ).format(actualValue.toLong() / 10.0.pow((base * 3).toDouble())) + suffix[base]
            } else {
                DecimalFormat(decimalScale ?: "#,##0.00").format(actualValue)
            }
        } else {
            if (actualValue > 0) {
                DecimalFormat(decimalScale ?: "00.00").format(actualValue)
            } else {
                if (decimalScale.isNullOrEmpty().not() && decimalScale == "00.00") "0.00" else "0"
            }
        }

        if (!prefix.isNullOrEmpty()) formattedString += "$prefix "
        formattedString += numberString
        if (!postfix.isNullOrEmpty()) formattedString += " $postfix"
        textView.text = formattedString
//        textView.text = "$actualValue"
    }

    fun setNumericAdverb(
        textView: TextView,
        actualValue: Double,
        decimalScale: String?,
    ) {
        val formattedString = DecimalFormat(decimalScale ?: "#.##").format(actualValue)
        textView.text = formattedString
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setTimer"], requireAll = false)
    fun setTimer(textView: TextView, seconds: Long) {
        var seconds = seconds
        seconds /= 1000
        val s = seconds % 60
        val m = seconds / 60 % 60
        val h = seconds / (60 * 60) % 24
        val d = seconds / (60 * 60 * 24) % 365
        textView.text = if (h > 0) {
            String.format(Locale.ENGLISH, "%02d:%02d:%02d", h, m, s)
        } else if (m > 0) {
            String.format(Locale.ENGLISH, "%02d:%02d", m, s)
        } else {
            String.format(Locale.ENGLISH, "00:%02d", s)
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["android:setBounceSelector", "android:showShadow", "android:justElevate", "android:needRounded"],
        requireAll = false
    )
    fun setBounceSelector(
        view: View,
        isSelected: Boolean,
        showShadow: Boolean?,
        justElevate: Boolean?,
        needRounded: Boolean?,
    ) {
        if (showShadow !== null && showShadow) {
            val showShadowBounds = justElevate ?: false
            val needRoundedShadow = needRounded ?: false
            val viewOutlineProvider: ViewOutlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    outline.setRoundRect(
                        0, 0, view.width, view.height, when {
                            needRoundedShadow -> {
                                (view.height).toFloat()
                            }

                            (view.width - view.height) > 200 -> {
                                (view.height / 8).toFloat()
                            }

                            else -> {
                                (view.height / 3.8).toFloat()
                            }
                        }
                    )
                }
            }

            view.z = ViewUtil.dipToPixel(view, 4).toFloat()
            val param = view.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(3, view.marginTop, 3, 10)
            view.layoutParams = param
            if (!showShadowBounds) {
                view.outlineProvider = viewOutlineProvider
                view.clipToOutline = true
            }
        }
        if (isSelected) {
            ViewUtil.setBounceButtonSelector(view)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["android:setViewBlur"], requireAll = false)
    fun setViewBlur(view: View, showBlur: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val blurScreen = RenderEffect.createBlurEffect(8f, 8f, Shader.TileMode.MIRROR)
            view.setRenderEffect(blurScreen)
        }
    }


    private fun getRequestOption(resId: Drawable?, imageSize: Int): RequestOptions {
        return if (imageSize != 0) {
            RequestOptions().placeholder(resId).error(resId).fallback(resId).override(imageSize)
                .dontAnimate().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        } else {
            RequestOptions().placeholder(resId).error(resId).fallback(resId).dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        }
    }

    @JvmStatic
    @BindingAdapter("android:restoreError")
    fun restoreError(editText: TextInputEditText, shouldRestoreError: Boolean) {
        val viewParent = editText.parent
        if (shouldRestoreError) editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (viewParent is ViewGroup) {
                    val child = viewParent.getParent()
                    if (child is TextInputLayout) {
                        child.isErrorEnabled = false
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}