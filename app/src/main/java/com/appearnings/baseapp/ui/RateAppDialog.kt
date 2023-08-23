package com.appearnings.baseapp.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.appearnings.baseapp.R
import com.appearnings.baseapp.databinding.SampleDialogBinding
import com.appearnings.baseapp.extension.setSafeOnClickListener
import com.appearnings.baseapp.extension.viewBinding
import com.appearnings.common.BaseBottomSheetDialogFragment

class RateAppDialog : BaseBottomSheetDialogFragment(R.layout.sample_dialog) {
    private val binding: SampleDialogBinding by viewBinding()

    companion object {
        const val ACTION_KEY = "RateAppDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(false)
        isCancelable = false
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSubmit.setOnClickListener {
                when {
                    ratingBar.rating > 3 -> {
                        // Excellent rating
                        dialog?.dismiss()
                    }

                    else -> {

                    }
                }
            }
            buttonClose.setSafeOnClickListener {
                dialog?.dismiss()
            }
        }
    }
}
