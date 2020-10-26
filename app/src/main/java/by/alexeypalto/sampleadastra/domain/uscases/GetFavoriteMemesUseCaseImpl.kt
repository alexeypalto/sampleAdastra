package by.alexeypalto.sampleadastra.domain.uscases

import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.domain.repository.MemesRepository
import javax.inject.Inject

class GetFavoriteMemesUseCaseImpl @Inject constructor(private val memesRepository: MemesRepository): GetFavoriteMemesUseCase {

    override suspend fun invoke(): List<Meme> {
        return memesRepository.getFavoriteMemes()
    }
}