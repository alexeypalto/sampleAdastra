package by.alexeypalto.sampleadastra.datas.repository

import by.alexeypalto.sampleadastra.datas.datasource.MemesDataSource
import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.domain.repository.MemesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemesRepositoryImpl @Inject constructor(
    private val memesDataSource: MemesDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : MemesRepository {

    override suspend fun getMemes(count: Int): List<Meme> {
        return withContext(ioDispatcher) {
            memesDataSource.getMemes(count)
        }
    }

    override suspend fun getFavoriteMemes(): List<Meme> {
        return withContext(ioDispatcher) {
            memesDataSource.getFavoriteMemes()
        }
    }

    override suspend fun addMemeToFavorite(meme: Meme) {
        withContext(ioDispatcher) {
            memesDataSource.addMemeToFavorites(meme)
        }
    }

    override suspend fun removeMemeFromFavorites(id: String) {
        withContext(ioDispatcher) {
            memesDataSource.removeMemeFromFavorites(id)
        }
    }

    override suspend fun isMemeExist(id: String): Boolean {
        return withContext(ioDispatcher) {
            memesDataSource.isMemeExist(id)
        }
    }

}