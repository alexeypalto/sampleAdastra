package by.alexeypalto.sampleadastra.presentation.ui.meme

import androidx.lifecycle.ViewModel
import by.alexeypalto.sampleadastra.domain.uscases.AddMemeToFavoriteUseCase
import by.alexeypalto.sampleadastra.domain.uscases.RemoveMemeFromFavoriteUseCase
import javax.inject.Inject

class MemeViewModel @Inject constructor(
    private val addMemeToFavoriteUseCase: AddMemeToFavoriteUseCase,
    private val removeMemeFromFavoriteUseCase: RemoveMemeFromFavoriteUseCase
) : ViewModel() {

}