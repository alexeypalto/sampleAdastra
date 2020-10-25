package by.alexeypalto.sampleadastra.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<VS : Any>(view: View,
                               private val onItemClickListener: ((item: VS, view: View, position: Int) -> Unit)? = null,
                               private val onItemLongClickListener: ((item: VS, view: View, position: Int) -> Unit)? = null)
    : RecyclerView.ViewHolder(view) {

    var item: BaseListItem<VS>? = null

    fun renderItem(holderItem: BaseListItem<VS>?) {
        item = holderItem
        holderItem?.let {
            it.renderView(itemView, adapterPosition)
            setClickListeners(itemView, it)
        }
    }

    fun payloadItem(holderItem: BaseListItem<VS>?, payloads: MutableList<Any>) {
        item = holderItem
        holderItem?.let {
            it.payloadView(itemView, adapterPosition, payloads)
            setClickListeners(itemView, it)
        }
    }


    fun selectItem(isSelected: Boolean) {
        (item as? SelectableItem)?.selectItem(isSelected)
    }

    private fun setClickListeners(view: View?, holderItem: BaseListItem<VS>) {
        onItemClickListener?.let {
            view?.setOnClickListener {
                it(holderItem.viewState, view, adapterPosition)
            }
        }
        onItemLongClickListener?.let {
            view?.setOnLongClickListener {
                it(holderItem.viewState, view, adapterPosition)
                true
            }
        }

        view?.setOnLongClickListener {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.invoke(holderItem.viewState, view, adapterPosition)
                return@setOnLongClickListener true
            }
            return@setOnLongClickListener false
        }
    }

}