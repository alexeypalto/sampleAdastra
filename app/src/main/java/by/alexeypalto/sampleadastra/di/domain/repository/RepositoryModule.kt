package by.alexeypalto.sampleadastra.di.domain.repository

import by.alexeypalto.sampleadastra.datas.datasource.MemesDataSource
import by.alexeypalto.sampleadastra.datas.repository.MemesRepositoryImpl
import by.alexeypalto.sampleadastra.domain.repository.MemesRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module(includes = [RepositoryModule.ProvideModule::class])
interface RepositoryModule {

    @Module
    object ProvideModule {

        @Provides
        fun provideMemesRepository(memesDataSource: MemesDataSource,
                                   ioDispatcher: CoroutineDispatcher): MemesRepository {
            return MemesRepositoryImpl(memesDataSource, ioDispatcher)
        }
    }
}