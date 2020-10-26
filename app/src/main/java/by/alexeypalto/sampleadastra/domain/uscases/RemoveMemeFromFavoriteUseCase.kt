package by.alexeypalto.sampleadastra.domain.uscases

interface RemoveMemeFromFavoriteUseCase {
    suspend operator fun invoke(id: String)
}