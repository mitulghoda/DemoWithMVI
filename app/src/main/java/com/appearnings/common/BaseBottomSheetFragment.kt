package com.appearnings.common

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.appearnings.baseapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.cancel

abstract class BaseBottomSheetFragment<V : ViewBinding>(
    val bindingFactory: (LayoutInflater) -> V,
    private val isExpand: Boolean = true,
    private val isFullScreen: Boolean = false,
) : BottomSheetDialogFragment(), CoroutineScope by CoroutineScope(
    Main
) {

    val TAG = javaClass.name
    open fun init() {
        setVariables()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogStyle)
    }

    open fun setVariables() {}

    open fun parseArguments() {}

    open fun resume() {}
    open fun pause() {}

    open fun actions() {}

    open fun setUpViews() {
        if (isExpand) dialog!!.setOnShowListener { dialog ->
            dialog as BottomSheetDialog
            if (isFullScreen) {
                setupFullHeight(dialog)
            } else {
                dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    open fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
                ?: return
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    open fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    lateinit var mContext: Context

    val binding: V by lazy { bindingFactory(layoutInflater) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        resume()
        super.onResume()
    }

    override fun onPause() {
        pause()
        super.onPause()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        parseArguments()
        init()
        setUpViews()
        actions()
        return binding.root
    }

    fun snackBar(msg: String, view: View? = dialog?.window?.decorView) {
        view?.let { Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show() }
    }

    fun toast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

    fun showAlert(
        title: String,
        msg: String? = null,
        @DrawableRes icon: Int? = null,
        setCancelable: Boolean = true,
        buttonName: String,
        buttonClick: () -> Unit,
    ) {
        AlertDialog.Builder(mContext).setTitle(title).also {
            if (msg != null) it.setMessage(msg)
            if (icon != null) it.setIcon(icon)
        }.setCancelable(setCancelable).setNeutralButton(buttonName) { _, _ ->
            buttonClick()
        }.create().show()
    }

    override fun onDestroy() {
        this.cancel()
        super.onDestroy()
    }

    open fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, "")
    }

    fun goBack() {
        dismiss()
    }

}
