package by.alexeypalto.sampleadastra.domain.uscases

import by.alexeypalto.sampleadastra.domain.models.Meme

interface GetFavoriteMemesUseCase {

    suspend operator fun invoke(): List<Meme>
}