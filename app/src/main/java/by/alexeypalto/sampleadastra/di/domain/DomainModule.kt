package by.alexeypalto.sampleadastra.di.domain

import by.alexeypalto.sampleadastra.di.domain.repository.RepositoryModule
import by.alexeypalto.sampleadastra.di.domain.usecases.UseCasesModule
import by.alexeypalto.sampleadastra.domain.errors.ErrorHandler
import by.alexeypalto.sampleadastra.remote.errors.ErrorHandlerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [UseCasesModule::class, RepositoryModule::class])
interface DomainModule {

    @Binds
    @Singleton
    fun bindErrorHandler(handler: ErrorHandlerImpl): ErrorHandler
}