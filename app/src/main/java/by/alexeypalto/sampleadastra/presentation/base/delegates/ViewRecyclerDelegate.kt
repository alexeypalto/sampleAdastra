package by.alexeypalto.sampleadastra.presentation.base.delegates

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.view.postDelayed
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.domain.state.Result
import by.alexeypalto.sampleadastra.presentation.adapter.BaseListItem
import by.alexeypalto.sampleadastra.presentation.adapter.BaseRecyclerAdapter
import by.alexeypalto.sampleadastra.presentation.base.ProceedError
import by.alexeypalto.sampleadastra.presentation.extensions.gone
import by.alexeypalto.sampleadastra.presentation.extensions.show

open class ViewRecyclerDelegate<VS : Any>(
    mapper: (VS) -> BaseListItem<VS>,
    @IdRes private val refreshId: Int? = R.id.refresh,
    @IdRes private val recyclerId: Int = R.id.recycler,
    @IdRes private val progressId: Int = R.id.progress,
    @IdRes private val errorMessageId: Int = R.id.error_message,
    @StringRes private val emptyTextId: Int = R.string.common_empty,
    onItemClickListener: ((item: VS, view: View, position: Int) -> Unit)? = null,
    onItemLongClickListener: ((item: VS, view: View, position: Int) -> Unit)? = null) {

    var refresh: SwipeRefreshLayout? = null
    var recycler: RecyclerView? = null
    var progress: View? = null
    var errorMessage: TextView? = null

    var refreshStatusListener: ((result: Result<Unit>) -> Unit)? = null

    var isEnableDiffUtil = true

    val adapter by lazy {
        BaseRecyclerAdapter(
            mapper = mapper,
            onItemClickListener = onItemClickListener,
            onItemLongClickListener = onItemLongClickListener)
    }

    fun init(fragment: Fragment, recyclerDelegate: ViewModelRecyclerDelegate<VS>) {
        refresh = refreshId?.let { fragment.view?.findViewById(it) }
        recycler = fragment.view?.findViewById(recyclerId)
            ?: throw IllegalArgumentException("Cannot find ${::recyclerId.name}")
        progress = fragment.view?.findViewById(progressId)
            ?: throw IllegalArgumentException("Cannot find ${::progressId.name}")
        errorMessage = fragment.view?.findViewById(errorMessageId)
            ?: throw IllegalArgumentException("Cannot find ${::errorMessageId.name}")
        init(fragment.viewLifecycleOwner, recyclerDelegate, fragment as ProceedError?)
    }

    private fun init(lifecycleOwner: LifecycleOwner, recyclerDelegate: ViewModelRecyclerDelegate<VS>, proceedError: ProceedError?) {
        recycler?.adapter = this@ViewRecyclerDelegate.adapter

        refresh?.setOnRefreshListener {
            recyclerDelegate.refresh()
        }
        recyclerDelegate.itemsList.observe(lifecycleOwner, Observer(::observeItems))
        recyclerDelegate.refreshStateLiveData.observe(lifecycleOwner, {
            when (it) {
                is Result.Loading -> {
                    progress?.show(adapter.itemCount == 0 && refresh?.isRefreshing != true)
                    errorMessage?.gone()
                }
                is Result.Success -> {
                    recycler?.show()
                    progress?.gone()
                    refresh?.isRefreshing = false
                    errorMessage?.setText(emptyTextId)
                    errorMessage?.postDelayed(500) {
                        errorMessage?.show(adapter.itemCount == 0)
                    }
                }
                is Result.Error -> {
                    recycler?.gone()
                    progress?.gone()
                    refresh?.isRefreshing = false
                    proceedError?.proceedError(it.exception, errorMessage)
                }
            }
            refreshStatusListener?.invoke(it)
        })

    }

    fun destroy() {
        refresh = null
        recycler = null
        progress = null
        errorMessage = null
    }

    private fun observeItems(items: List<VS>) {
        if (isEnableDiffUtil) {
            adapter.replaceElementsWithDiffUtil(items)
        } else {
            adapter.replaceElements(items)
        }
        adapter.notifyDataSetChanged()
    }

}