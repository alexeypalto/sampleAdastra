package by.alexeypalto.sampleadastra.domain.uscases

interface IsMemeExistUseCase {
    suspend operator fun invoke(id: String): Boolean
}