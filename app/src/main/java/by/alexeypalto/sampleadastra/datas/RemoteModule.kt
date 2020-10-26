package by.alexeypalto.sampleadastra.datas

import by.alexeypalto.sampleadastra.di.qualifier.ApiClient
import by.alexeypalto.sampleadastra.remote.service.MemesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object RemoteModule {

    private const val NETWORK_TIMEOUT = 30L // Second

    @Provides
    @Singleton
    @ApiClient
    internal fun providesApiService(): MemesService {
        val client = createHttpClient()

        return Retrofit.Builder()
            .baseUrl("https://${MemesService.SERVICE_PREFIX}/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MemesService::class.java)
    }

    private fun createHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

}