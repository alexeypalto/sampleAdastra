package by.alexeypalto.sampleadastra.local.database.mapper

import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.local.database.entities.MemeEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemesMapper @Inject constructor(): Mapper<Meme, MemeEntity> {

    override fun mapToEntity(model: Meme): MemeEntity {
        return MemeEntity(
            id = model.id,
            url = model.url,
            createdAt = model.createdAt
        )
    }

    override fun mapToModel(entity: MemeEntity): Meme {
        return Meme(
            id = entity.id,
            url = entity.url,
            createdAt = entity.createdAt
        )
    }
}