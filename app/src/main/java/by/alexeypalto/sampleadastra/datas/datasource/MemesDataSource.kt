package by.alexeypalto.sampleadastra.datas.datasource

import by.alexeypalto.sampleadastra.domain.models.Meme
import kotlinx.coroutines.flow.Flow

interface MemesDataSource {
    suspend fun getMemes(count: Int): List<Meme>
    suspend fun getFavoriteMemes(): Flow<List<Meme>?>
    suspend fun addMemeToFavorites(meme: Meme)
    suspend fun removeMemeFromFavorites(id: Int)
}