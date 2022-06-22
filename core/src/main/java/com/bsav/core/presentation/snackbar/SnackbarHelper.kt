package com.bsav.core.presentation.snackbar

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

fun View.showInternetNotAvailableMessage(indefinite: Boolean = false, tryAgain: () -> Unit) {
    val duration = if (indefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
    Snackbar.make(this, R.string.there_is_no_internet, duration).apply {
        setAction(R.string.try_again_message) {
            tryAgain()
            dismiss()
        }
        show()
    }
}
