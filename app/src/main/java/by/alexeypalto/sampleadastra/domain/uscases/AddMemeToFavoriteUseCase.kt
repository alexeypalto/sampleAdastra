package by.alexeypalto.sampleadastra.domain.uscases

import by.alexeypalto.sampleadastra.domain.models.Meme

interface AddMemeToFavoriteUseCase {
    suspend operator fun invoke(meme: Meme)
}