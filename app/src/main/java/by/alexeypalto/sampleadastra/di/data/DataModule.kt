package by.alexeypalto.sampleadastra.di.data

import by.alexeypalto.sampleadastra.datas.datasource.MemesDataSource
import by.alexeypalto.sampleadastra.local.datasource.LocalMemesDataSourceImpl
import by.alexeypalto.sampleadastra.remote.datasource.RemoteMemesDataSourceImpl
import by.alexeypalto.sampleadastra.remote.mapper.MemesMapper
import by.alexeypalto.sampleadastra.remote.service.MemesService
import by.alexeypalto.sampleadastra.di.qualifier.ApiClient
import by.alexeypalto.sampleadastra.di.qualifier.LocalSource
import by.alexeypalto.sampleadastra.di.qualifier.RemoteSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [DataModule.ProvidesModule::class])
interface DataModule {

    @Binds
    @RemoteSource
    fun bindRemoteMemesDataSource(dataSource: RemoteMemesDataSourceImpl): MemesDataSource

    @Binds
    @LocalSource
    fun bindLocalMemesDataSource(dataSource: LocalMemesDataSourceImpl): MemesDataSource

    @Module
    object ProvidesModule {
        @Provides
        fun providesIODispatcher(): CoroutineDispatcher {
            return Dispatchers.IO
        }

        @Provides
        @RemoteSource
        fun provideRemoteMemesDataSource(@ApiClient service: MemesService,
                                         mapper: MemesMapper): MemesDataSource {
            return RemoteMemesDataSourceImpl(service, mapper)
        }
    }
}