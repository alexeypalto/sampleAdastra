package by.alexeypalto.sampleadastra.presentation.ui.views

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.annotation.DrawableRes
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.presentation.extensions.convertDpToPx
import by.alexeypalto.sampleadastra.presentation.extensions.getDrawableFromTheme

private const val IMAGE_SIZE = 28f //dp

private const val SAVED_MENU_ITEM = "SAVED_MENU_ITEM"
private const val SUPER_SAVED_STATE = "SUPER_SAVED_STATE"

class BottomNavigation @JvmOverloads constructor(context: Context,
                                                 attrs: AttributeSet? = null) : RadioGroup(context, attrs), View.OnClickListener {

    var onTabClickListener: ((menuItem: NavigationMenu) -> Unit)? = null
    var onTabReselectListener: ((menuItem: NavigationMenu) -> Unit)? = null

    private var checkedItem: NavigationMenu? = null
        set(value) {
            value?.let {
                if (field != it) {
                    field = it
                    onTabClickListener?.invoke(it)
                    selectItem(it)
                } else {
                    onTabReselectListener?.invoke(it)
                }
            }
        }

    private val itemList: MutableList<NavigationItem> = mutableListOf()

    init {
        orientation = HORIZONTAL
        NavigationMenu.values().forEach {
            val view = NavigationItem(context)
            val params = LayoutParams(0, LayoutParams.MATCH_PARENT, 1f)
            with(view) {
                tag = it
                setOnClickListener(this@BottomNavigation)
                setImageResource(it.iconResPassive)
                background = context.getDrawableFromTheme(R.attr.selectableItemBackgroundBorderless)
                itemList.add(this)
            }
            addView(view, params)
        }
    }

    override fun onClick(v: View?) {
        if (v?.tag is NavigationMenu) {
            val item = v.tag as? NavigationMenu
            checkedItem = item
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putParcelable(SUPER_SAVED_STATE, super.onSaveInstanceState())
        bundle.putString(SAVED_MENU_ITEM, checkedItem?.name)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        (state as? Bundle)?.let {
            super.onRestoreInstanceState(it.getParcelable(SUPER_SAVED_STATE))
            checkedItem = NavigationMenu.valueOf(it.getString(SAVED_MENU_ITEM, NavigationMenu.HOME.name))
        }
    }

    fun selectTabAtPosition(index: Int) {
        checkedItem = NavigationMenu.values()[index]
    }

    private fun selectItem(newValue: NavigationMenu) {
        post {
            itemList.forEach { imageView ->
                (imageView.tag as? NavigationMenu)?.let {
                    imageView.setImageResource(if (it == newValue) {
                        it.iconResActive
                    } else {
                        it.iconResPassive
                    })
                }
            }
        }
    }

    enum class NavigationMenu(@DrawableRes internal val iconResPassive: Int, @DrawableRes internal val iconResActive: Int) {
        HOME(R.drawable.ic_home_passive, R.drawable.ic_home_active),
        FAVORITE(R.drawable.ic_favorite_passive, R.drawable.ic_favorite_active)
    }

    private class NavigationItem(context: Context) : FrameLayout(context) {

        private val itemIcon = ImageView(context).apply {
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            layoutParams = LayoutParams(
                    context.convertDpToPx(IMAGE_SIZE.toInt()),
                    context.convertDpToPx(IMAGE_SIZE.toInt()),
                    Gravity.CENTER
            )
        }

        init {
            addView(itemIcon)
        }

        fun setImageResource(imageId: Int) {
            itemIcon.setImageResource(imageId)
        }
    }

}