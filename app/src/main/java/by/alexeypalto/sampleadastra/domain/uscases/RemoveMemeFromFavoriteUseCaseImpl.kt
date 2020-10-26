package by.alexeypalto.sampleadastra.domain.uscases

import by.alexeypalto.sampleadastra.domain.repository.MemesRepository
import javax.inject.Inject

class RemoveMemeFromFavoriteUseCaseImpl @Inject constructor(private val memesRepository: MemesRepository): RemoveMemeFromFavoriteUseCase {
    override suspend fun invoke(id: String) {
        memesRepository.removeMemeFromFavorites(id)
    }
}