package by.alexeypalto.sampleadastra.presentation.ui.favoritememes

import android.os.Bundle
import android.view.View
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.di.Injectable
import by.alexeypalto.sampleadastra.presentation.base.BaseFragment
import by.alexeypalto.sampleadastra.presentation.base.delegates.ViewRecyclerDelegate
import by.alexeypalto.sampleadastra.presentation.extensions.convertDpToPx
import by.alexeypalto.sampleadastra.presentation.ui.items.MemeItem
import by.alexeypalto.sampleadastra.presentation.ui.meme.MemeFragment
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewState
import kotlinx.android.synthetic.main.layout_recycler.*
import javax.inject.Inject

private const val RECYCLER_TOP_PADDING = 8 //dp
private const val RECYCLER_BOTTOM_PADDING = 4 //dp

class FavoriteMemesFragment: BaseFragment(R.layout.fragment_favorite_memes), Injectable {

    companion object {
        fun newInstance() = FavoriteMemesFragment()
    }

    @Inject
    lateinit var viewModel: FavoriteMemesViewModel

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
        setTitle(R.string.favorite_memes_title)
        with(recycler) {
            setPadding(paddingLeft, context.convertDpToPx(RECYCLER_TOP_PADDING), paddingRight, context.convertDpToPx(RECYCLER_BOTTOM_PADDING))
            clipToPadding = false
        }
    }

    override fun onResume() {
        viewModel.refresh()
        super.onResume()
    }

    override fun onDestroyView() {
        recyclerDelegate.destroy()
        super.onDestroyView()
    }

    private fun mapViewState(vs: MemeViewState) = MemeItem(vs)


    private fun onItemClick(vs: MemeViewState, v: View, position: Int) {
        pushFragment(MemeFragment.newInstance(vs))
    }

}