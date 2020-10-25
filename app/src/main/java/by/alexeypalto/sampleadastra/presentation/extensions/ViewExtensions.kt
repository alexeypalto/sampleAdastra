package by.alexeypalto.sampleadastra.presentation.extensions

import android.view.View

fun View.show(show: Boolean, invisible: Boolean = false) {
    visibility = if (show) View.VISIBLE else if (invisible) View.INVISIBLE else View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.convertDpToPx(dp: Int) = context.convertDpToPx(dp)
