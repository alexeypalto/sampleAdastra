package by.alexeypalto.sampleadastra.presentation.ui.favoritememes

import androidx.lifecycle.ViewModel
import by.alexeypalto.sampleadastra.domain.uscases.AddMemeToFavoriteUseCase
import by.alexeypalto.sampleadastra.domain.uscases.GetFavoriteMemesUseCase
import by.alexeypalto.sampleadastra.domain.uscases.RemoveMemeFromFavoriteUseCase
import javax.inject.Inject

class FavoriteMemesViewModel @Inject constructor(
    private val getFavoriteMemesUseCase: GetFavoriteMemesUseCase,
    private val addMemeToFavoriteUseCase: AddMemeToFavoriteUseCase,
    private val removeMemeFromFavoriteUseCase: RemoveMemeFromFavoriteUseCase
) : ViewModel()  {

}