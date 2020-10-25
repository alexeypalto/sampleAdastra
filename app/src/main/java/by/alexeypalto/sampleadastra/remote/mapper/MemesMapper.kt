package by.alexeypalto.sampleadastra.remote.mapper

import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.remote.models.MemeResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemesMapper @Inject constructor(): Mapper<MemeResponse, Meme> {

    override fun invoke(response: MemeResponse): Meme {
        return Meme(
            id = response.id,
            url = response.url,
            createdAt = response.createdAt
        )
    }
}