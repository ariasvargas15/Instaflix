package com.bsav.core.utils

import android.view.View
import com.bsav.core.R
import com.google.android.material.snackbar.Snackbar

fun View.showErrorMessage(action: () -> Unit) {
    Snackbar.make(this, R.string.something_went_wrong, Snackbar.LENGTH_INDEFINITE).apply {
        setAction(R.string.try_again_message) {
            action()
            dismiss()
        }
        show()
    }
}
