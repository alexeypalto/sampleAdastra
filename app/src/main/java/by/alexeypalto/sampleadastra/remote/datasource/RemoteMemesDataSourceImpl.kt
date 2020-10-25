package by.alexeypalto.sampleadastra.remote.datasource

import by.alexeypalto.sampleadastra.datas.datasource.MemesDataSource
import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.remote.mapper.MemesMapper
import by.alexeypalto.sampleadastra.remote.service.MemesService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteMemesDataSourceImpl @Inject constructor(private val service: MemesService,
                                                    private val mapper: MemesMapper): MemesDataSource {

    override suspend fun getMemes(count: Int): List<Meme> {
        return service.getRandomMemes(count).map(mapper::invoke)
    }

    override suspend fun getFavoriteMemes(): Flow<List<Meme>?> {
        throw IllegalStateException("You cannot get favorite memes from remote")
    }

    override suspend fun addMemeToFavorites(meme: Meme) {
        throw IllegalStateException("You cannot add meme to remote")

    }

    override suspend fun removeMemeFromFavorites(id: Int) {
        throw IllegalStateException("You cannot remove meme from remote")
    }
}