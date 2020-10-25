package by.alexeypalto.sampleadastra.datas.repository

import by.alexeypalto.sampleadastra.datas.datasource.MemesDataSource
import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.domain.repository.MemesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemesRepositoryImpl @Inject constructor(
    private val localMemesDataSource: MemesDataSource,
    private val remoteMemesDataSource: MemesDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : MemesRepository {

    override suspend fun getMemes(count: Int): List<Meme> {
        return withContext(ioDispatcher) {
            remoteMemesDataSource.getMemes(count)
        }
    }

    override suspend fun getFavoriteMemes(): Flow<List<Meme>> {
        return withContext(ioDispatcher) {
            localMemesDataSource.getFavoriteMemes().map { list -> list ?: emptyList() }
        }
    }

    override suspend fun addMemeToFavorite(meme: Meme) {
        withContext(ioDispatcher) {
            localMemesDataSource.addMemeToFavorites(meme)
        }
    }

    override suspend fun removeMemeFromFavorites(id: Int) {
        withContext(ioDispatcher) {
            localMemesDataSource.removeMemeFromFavorites(id)
        }
    }
}