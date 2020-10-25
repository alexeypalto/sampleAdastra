package by.alexeypalto.sampleadastra.domain.uscases

import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.domain.repository.MemesRepository
import javax.inject.Inject

class GetMemesUseCaseImpl @Inject constructor(private val memesRepository: MemesRepository): GetMemesUseCase {

    override suspend fun invoke(count: Int): List<Meme> {
        return memesRepository.getMemes(count)
    }
}