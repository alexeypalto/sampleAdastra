package by.alexeypalto.sampleadastra.di.domain.usecases

import by.alexeypalto.sampleadastra.domain.uscases.*
import dagger.Binds
import dagger.Module

@Module
interface MemesUseCasesModule {

    @Binds
    fun bindGetMemesUseCase(useCase: GetMemesUseCaseImpl): GetMemesUseCase

    @Binds
    fun bindGetFavoriteMemesUseCase(useCase: GetFavoriteMemesUseCaseImpl): GetFavoriteMemesUseCase

    @Binds
    fun bindAddMemeToFavoriteUseCase(useCase: AddMemeToFavoriteUseCaseImpl): AddMemeToFavoriteUseCase

    @Binds
    fun bindRemoveMemeFromFavoriteUseCase(useCase: RemoveMemeFromFavoriteUseCaseImpl): RemoveMemeFromFavoriteUseCase
}