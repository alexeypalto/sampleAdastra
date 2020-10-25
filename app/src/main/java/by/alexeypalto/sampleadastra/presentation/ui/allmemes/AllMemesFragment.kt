package by.alexeypalto.sampleadastra.presentation.ui.allmemes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.presentation.base.BaseFragment
import by.alexeypalto.sampleadastra.presentation.base.delegates.ViewRecyclerDelegate
import by.alexeypalto.sampleadastra.presentation.extensions.convertDpToPx
import by.alexeypalto.sampleadastra.presentation.extensions.injectViewModel
import by.alexeypalto.sampleadastra.presentation.ui.items.MemeItem
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewState
import kotlinx.android.synthetic.main.layout_recycler.*
import javax.inject.Inject

private const val RECYCLER_TOP_PADDING = 8 //dp

class AllMemesFragment @Inject constructor(factory: ViewModelProvider.Factory): BaseFragment(R.layout.fragment_all_memes) {

    private val viewModel by lazy { injectViewModel<AllMemesViewModel>(factory) }

    private val recyclerDelegate by lazy {
        ViewRecyclerDelegate(
            onItemClickListener = ::onItemClick,
            mapper = ::mapViewState
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        recyclerDelegate.init(this, viewModel.recyclerDelegate)
    }

    private fun initView() {
        setTitle(R.string.all_memes_title)
        with(recycler) {
            setPadding(paddingLeft, context.convertDpToPx(RECYCLER_TOP_PADDING), paddingRight, paddingBottom)
            clipToPadding = false
        }
    }

    override fun onDestroyView() {
        recyclerDelegate.destroy()
        super.onDestroyView()
    }

    private fun mapViewState(vs: MemeViewState) = MemeItem(vs)


    private fun onItemClick(vs: MemeViewState, v: View, position: Int) {

    }
}