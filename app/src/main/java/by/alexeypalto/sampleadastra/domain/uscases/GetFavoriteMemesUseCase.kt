package by.alexeypalto.sampleadastra.domain.uscases

import by.alexeypalto.sampleadastra.domain.models.Meme
import kotlinx.coroutines.flow.Flow

interface GetFavoriteMemesUseCase {

    suspend operator fun invoke(): Flow<List<Meme>>
}