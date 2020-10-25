package by.alexeypalto.sampleadastra.domain.repository

import by.alexeypalto.sampleadastra.domain.models.Meme
import kotlinx.coroutines.flow.Flow

interface MemesRepository {
    suspend fun getMemes(count: Int): List<Meme>
    suspend fun getFavoriteMemes(): Flow<List<Meme>>
    suspend fun addMemeToFavorite(meme: Meme)
    suspend fun removeMemeFromFavorites(id: Int)
}