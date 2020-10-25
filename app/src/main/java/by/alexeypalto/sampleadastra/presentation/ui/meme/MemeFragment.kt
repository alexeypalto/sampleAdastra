package by.alexeypalto.sampleadastra.presentation.ui.meme

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.di.Injectable
import by.alexeypalto.sampleadastra.presentation.base.BaseFragment
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewState
import javax.inject.Inject

private const val KEY_MEME_VS = "KEY_MEME_VS"

class MemeFragment :
    BaseFragment(R.layout.fragment_meme) {

    companion object {
        fun getBundle(memeViewState: MemeViewState): Bundle = bundleOf(KEY_MEME_VS to memeViewState)
    }

}