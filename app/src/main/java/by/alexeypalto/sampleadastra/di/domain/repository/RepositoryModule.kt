package by.alexeypalto.sampleadastra.di.domain.repository

import by.alexeypalto.sampleadastra.datas.datasource.MemesDataSource
import by.alexeypalto.sampleadastra.datas.repository.MemesRepositoryImpl
import by.alexeypalto.sampleadastra.domain.repository.MemesRepository
import by.alexeypalto.sampleadastra.di.qualifier.LocalSource
import by.alexeypalto.sampleadastra.di.qualifier.RemoteSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module(includes = [RepositoryModule.ProvideModule::class])
interface RepositoryModule {

    @Binds
    fun bindMemesRepository(repositoryImpl: MemesRepositoryImpl): MemesRepository

    @Module
    object ProvideModule {

        @Provides
        fun provideMemesRepository(@RemoteSource remoteMemesDataSource: MemesDataSource,
                                   @LocalSource localMemesDataSource: MemesDataSource,
                                   ioDispatcher: CoroutineDispatcher): MemesRepository {
            return MemesRepositoryImpl(remoteMemesDataSource, localMemesDataSource, ioDispatcher)
        }
    }
}