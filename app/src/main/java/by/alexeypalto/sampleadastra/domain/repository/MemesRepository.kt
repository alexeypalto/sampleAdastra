package by.alexeypalto.sampleadastra.domain.repository

import by.alexeypalto.sampleadastra.domain.models.Meme

interface MemesRepository {
    suspend fun getMemes(count: Int): List<Meme>
    suspend fun getFavoriteMemes(): List<Meme>
    suspend fun addMemeToFavorite(meme: Meme)
    suspend fun removeMemeFromFavorites(id: String)
    suspend fun isMemeExist(id: String): Boolean
}