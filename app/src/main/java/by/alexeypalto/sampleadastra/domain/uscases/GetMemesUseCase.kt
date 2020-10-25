package by.alexeypalto.sampleadastra.domain.uscases

import by.alexeypalto.sampleadastra.domain.models.Meme

interface GetMemesUseCase {
    suspend operator fun invoke(count: Int): List<Meme>
}