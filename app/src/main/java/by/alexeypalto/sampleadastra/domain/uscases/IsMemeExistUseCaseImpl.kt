package by.alexeypalto.sampleadastra.domain.uscases

import by.alexeypalto.sampleadastra.domain.repository.MemesRepository
import javax.inject.Inject

class IsMemeExistUseCaseImpl @Inject constructor(private val memesRepository: MemesRepository): IsMemeExistUseCase {
    override suspend fun invoke(id: String): Boolean {
        return memesRepository.isMemeExist(id)
    }

}