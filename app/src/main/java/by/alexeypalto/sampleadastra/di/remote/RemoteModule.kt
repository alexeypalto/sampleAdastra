package by.alexeypalto.sampleadastra.di.remote

import by.alexeypalto.sampleadastra.remote.service.MemesService
import by.alexeypalto.sampleadastra.di.qualifier.ApiClient
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
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
    fun providesApiOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @ApiClient
    fun providesApiRetrofit(@ApiClient okHttpClient: OkHttpClient,
                            converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    @ApiClient
    fun providesProfileService(@ApiClient retrofit: Retrofit): MemesService {
        return retrofit.create(MemesService::class.java)
    }

}