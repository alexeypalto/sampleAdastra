package by.alexeypalto.sampleadastra.di.data

import by.alexeypalto.sampleadastra.datas.datasource.MemesDataSource
import by.alexeypalto.sampleadastra.datas.datasource.MemesDataSourceImpl
import by.alexeypalto.sampleadastra.di.qualifier.ApiClient
import by.alexeypalto.sampleadastra.remote.mapper.MemesMapper
import by.alexeypalto.sampleadastra.remote.service.MemesService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [DataModule.ProvidesModule::class])
interface DataModule {

    @Module
    object ProvidesModule {
        @Provides
        fun providesIODispatcher(): CoroutineDispatcher {
            return Dispatchers.IO
        }

        @Provides
        fun provideMemesDataSource(@ApiClient service: MemesService,
                                   mapperRemote: MemesMapper,
                                   mapperLocal: by.alexeypalto.sampleadastra.local.database.mapper.MemesMapper): MemesDataSource {
            return MemesDataSourceImpl(service, mapperRemote, mapperLocal)
        }
    }
}