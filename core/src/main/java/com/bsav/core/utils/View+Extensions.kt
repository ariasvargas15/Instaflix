package com.bsav.core.utils

import android.view.View
import com.bsav.core.R
import com.google.android.material.snackbar.Snackbar

fun View.showErrorMessage() {
    Snackbar.make(this, R.string.something_went_wrong, Snackbar.LENGTH_INDEFINITE).apply {
        setAction(R.string.dismiss_message) {
            dismiss()
        }
        show()
    }
}

fun View.showInternetNotAvailable(tryAgain: () -> Unit) {
    Snackbar.make(this, R.string.there_is_no_internet, Snackbar.LENGTH_LONG).apply {
        setAction(R.string.try_again_message) {
            tryAgain()
            dismiss()
        }
        show()
    }
}
