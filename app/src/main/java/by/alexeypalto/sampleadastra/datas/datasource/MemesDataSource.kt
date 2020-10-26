package by.alexeypalto.sampleadastra.datas.datasource

import by.alexeypalto.sampleadastra.domain.models.Meme

interface MemesDataSource {
    suspend fun getMemes(count: Int): List<Meme>
    suspend fun getFavoriteMemes(): List<Meme>
    suspend fun addMemeToFavorites(meme: Meme)
    suspend fun removeMemeFromFavorites(id: String)
    suspend fun isMemeExist(id: String): Boolean
}