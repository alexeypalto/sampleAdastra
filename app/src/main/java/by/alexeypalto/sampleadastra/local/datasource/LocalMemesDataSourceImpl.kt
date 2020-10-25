package by.alexeypalto.sampleadastra.local.datasource

import by.alexeypalto.sampleadastra.datas.datasource.MemesDataSource
import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.local.database.dao.MemesDao
import by.alexeypalto.sampleadastra.local.database.mapper.MemesMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class LocalMemesDataSourceImpl @Inject constructor(private val dao: MemesDao,
                                                   private val mapper: MemesMapper): MemesDataSource {

    override suspend fun getMemes(count: Int): List<Meme> {
        throw IllegalStateException("You cannot get all memes from remote")
    }

    override suspend fun getFavoriteMemes(): Flow<List<Meme>?> {
        return dao.getFavoriteMemes().transform { entity ->
            if (entity != null) emit(entity.map(mapper::mapToModel))
        }
    }

    override suspend fun addMemeToFavorites(meme: Meme) {
        dao.addMemeToFavorites(mapper.mapToEntity(meme))
    }

    override suspend fun removeMemeFromFavorites(id: Int) {
        dao.removeMemeFromFavorites(id)
    }
}