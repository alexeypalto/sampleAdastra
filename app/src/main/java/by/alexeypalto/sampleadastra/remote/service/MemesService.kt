package by.alexeypalto.sampleadastra.remote.service

import by.alexeypalto.sampleadastra.remote.models.MemeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MemesService {

    companion object {
        private const val SERVICE_PREFIX = "namo-memes.herokuapp.com"
    }

    @GET("$SERVICE_PREFIX/memes/{count}")
    suspend fun getRandomMemes(@Path("count") count: Int): List<MemeResponse>
}