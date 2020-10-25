package by.alexeypalto.sampleadastra.presentation.base

import android.widget.TextView

interface ProceedError {
    fun proceedError(exception: Exception?, messageView: TextView? = null, action: (() -> Unit)? = null)
}