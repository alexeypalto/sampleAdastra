package by.alexeypalto.sampleadastra.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.alexeypalto.sampleadastra.presentation.extensions.inflateLayout

class BaseRecyclerAdapter<VS : Any>(
        val mapper: (VS) -> BaseListItem<VS>,
        private var onItemClickListener: ((item: VS, view: View, position: Int) -> Unit)? = null,
        private var onItemLongClickListener: ((item: VS, view: View, position: Int) -> Unit)? = null) : RecyclerView.Adapter<BaseViewHolder<VS>>() {

    companion object {
        const val HOLDER_TAG_KEY = 0x03000285
    }

    private val dataset = mutableListOf<BaseListItem<VS>>()

    override fun onBindViewHolder(holder: BaseViewHolder<VS>, position: Int) {
        holder.renderItem(dataset[position])
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VS>, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            holder.payloadItem(dataset[position], payloads)
        }
    }

    override fun getItemCount(): Int = dataset.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VS> {
        val view = parent.context.inflateLayout(viewType, parent, false)
        val holder = BaseViewHolder(view, onItemClickListener, onItemLongClickListener)
        view.setTag(HOLDER_TAG_KEY, holder)
        return holder
    }

    override fun getItemViewType(position: Int): Int = dataset[position].getViewId()

    fun replaceElements(newElements: List<VS>) {
        dataset.clear()
        dataset.addAll(newElements.map(mapper))
    }

    fun replaceElementsWithDiffUtil(items: List<VS>, enableAnimation: Boolean = true) {
        val diffUtilCallback = DiffUtilCallback(dataset.toList(), items.map(mapper))
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback, enableAnimation)
        replaceElements(items)
        diffResult.dispatchUpdatesTo(this)
    }
}

class DiffUtilCallback<VS : Any>(private var oldList: List<BaseListItem<VS>>,
                                 private var newList: List<BaseListItem<VS>>) : DiffUtil.Callback() {

    /*
    * Returns the size of the old list.
    */
    override fun getOldListSize(): Int = oldList.size

    /*
    * Returns the size of the new list.
    */
    override fun getNewListSize(): Int = newList.size

    /*
    * Called by the DiffUtil to decide whether two object represent the same Item.
    */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return newItem.areItemsTheSame(oldItem)
    }

    /*
    * Called by the DiffUtil when it wants to check whether two items have the same data.
    * DiffUtil uses this information to detect if the contents of an item has changed.
    */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return newItem.areContentsTheSame(oldItem)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return newItem.getChangePayload(oldItem)
    }
}