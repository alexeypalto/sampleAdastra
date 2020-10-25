package by.alexeypalto.sampleadastra.presentation.viewstate

import by.alexeypalto.sampleadastra.domain.models.Meme
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemeViewStateMapper @Inject constructor(): Mapper<Meme, MemeViewState> {

    override fun invoke(entity: Meme): MemeViewState {
        return MemeViewState(
            id = entity.id,
            url = entity.url,
            createdAt = entity.createdAt
        )
    }
}