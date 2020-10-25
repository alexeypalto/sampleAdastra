package by.alexeypalto.sampleadastra.domain.uscases

import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.domain.repository.MemesRepository
import javax.inject.Inject

class AddMemeToFavoriteUseCaseImpl @Inject constructor(private val memesRepository: MemesRepository): AddMemeToFavoriteUseCase {
    override suspend fun invoke(meme: Meme) {
        memesRepository.addMemeToFavorite(meme)
    }
}