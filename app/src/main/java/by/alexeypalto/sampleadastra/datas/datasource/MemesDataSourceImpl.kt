package by.alexeypalto.sampleadastra.datas.datasource

import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.local.database.database
import by.alexeypalto.sampleadastra.remote.mapper.MemesMapper
import by.alexeypalto.sampleadastra.remote.service.MemesService
import javax.inject.Inject

class MemesDataSourceImpl @Inject constructor(private val service: MemesService,
                                              private val mapperRemote: MemesMapper,
                                              private val mapperLocal: by.alexeypalto.sampleadastra.local.database.mapper.MemesMapper): MemesDataSource {

    override suspend fun getMemes(count: Int): List<Meme> {
        return service.getRandomMemes(count).map(mapperRemote::invoke)
    }

    override suspend fun getFavoriteMemes(): List<Meme> {
        return database.memesDao().getFavoriteMemes()?.map(mapperLocal::mapToModel) ?: emptyList()
    }

    override suspend fun addMemeToFavorites(meme: Meme) {
        database.memesDao().addMemeToFavorites(mapperLocal.mapToEntity(meme))
    }

    override suspend fun removeMemeFromFavorites(id: String) {
        database.memesDao().removeMemeFromFavorites(id)
    }

    override suspend fun isMemeExist(id: String): Boolean {
        return database.memesDao().isMemeIsExist(id)
    }
}