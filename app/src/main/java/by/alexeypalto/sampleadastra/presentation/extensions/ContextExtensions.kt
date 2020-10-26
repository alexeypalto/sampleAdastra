package by.alexeypalto.sampleadastra.presentation.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.LayoutRes

fun Context.inflateLayout(@LayoutRes layoutResId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View = LayoutInflater.from(this).inflate(layoutResId, parent, attachToRoot)

fun Context.convertDpToPx(dp: Int): Int = (dp * resources.displayMetrics.density + 0.5f).toInt()

fun Context.getDrawableFromTheme(@AttrRes shapeId: Int): Drawable? {
    val attrs = intArrayOf(shapeId)
    val ta = this.obtainStyledAttributes(attrs)
    val drawableFromTheme = ta.getDrawable(0)
    ta.recycle()
    return drawableFromTheme
}