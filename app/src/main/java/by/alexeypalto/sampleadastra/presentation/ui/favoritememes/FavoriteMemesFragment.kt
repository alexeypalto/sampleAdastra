package by.alexeypalto.sampleadastra.presentation.ui.favoritememes

import androidx.lifecycle.ViewModelProvider
import by.alexeypalto.sampleadastra.R
import by.alexeypalto.sampleadastra.di.Injectable
import by.alexeypalto.sampleadastra.presentation.base.BaseFragment
import by.alexeypalto.sampleadastra.presentation.extensions.injectViewModel
import javax.inject.Inject

class FavoriteMemesFragment @Inject constructor(factory: ViewModelProvider.Factory) : BaseFragment(R.layout.fragment_recycler) {

    private val viewModel by lazy { injectViewModel<FavoriteMemesViewModel>(factory) }


}