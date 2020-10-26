package by.alexeypalto.sampleadastra.presentation.ui.meme

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.di.Injectable
import by.alexeypalto.sampleadastra.presentation.base.BaseFragment
import by.alexeypalto.sampleadastra.presentation.extensions.convertToString
import by.alexeypalto.sampleadastra.presentation.extensions.show
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewState
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_meme.*
import javax.inject.Inject

private const val KEY_MEME_VS = "KEY_MEME_VS"

class MemeFragment : BaseFragment(R.layout.fragment_meme), Injectable {

    companion object {
        fun newInstance(memeViewState: MemeViewState) = MemeFragment().apply {
            arguments = bundleOf(KEY_MEME_VS to memeViewState)
        }
    }

    private val viewState by lazy {
        arguments?.getParcelable<MemeViewState>(KEY_MEME_VS)
            ?: throw IllegalStateException("Need meme view state")
    }

    @Inject
    lateinit var viewModel: MemeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        setTitle(R.string.meme_title)
        Glide.with(this)
            .load(viewState.url)
            .placeholder(R.drawable.ic_placeholder_fallback)
            .fallback(R.drawable.ic_placeholder_fallback)
            .into(fm_image)
        fm_date.text = getString(R.string.meme_created_text, viewState.createdAt.convertToString())
        fm_action.setOnClickListener { actionClick() }
    }

    private fun initViewModel() {
        viewModel.checkIsMemeExists(viewState.id)
        viewModel.isFavoriteMemeLiveData.observe(viewLifecycleOwner, {
            fm_action.show()
            fm_action.text =
                if (it) context?.getString(R.string.meme_remove_action_text)
                else context?.getString(R.string.meme_add_action_text)
        })
    }

    private fun actionClick() {
        viewModel.actionDB(viewState)
    }
}