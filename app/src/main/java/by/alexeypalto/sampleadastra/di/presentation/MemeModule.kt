package by.alexeypalto.sampleadastra.di.presentation

import androidx.lifecycle.ViewModelProvider
import by.alexeypalto.sampleadastra.domain.uscases.AddMemeToFavoriteUseCase
import by.alexeypalto.sampleadastra.domain.uscases.IsMemeExistUseCase
import by.alexeypalto.sampleadastra.domain.uscases.RemoveMemeFromFavoriteUseCase
import by.alexeypalto.sampleadastra.presentation.ui.meme.MemeFragment
import by.alexeypalto.sampleadastra.presentation.ui.meme.MemeViewModel
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewStateMapper
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
interface MemeModule {

    @Module
    object ProvidesModule {
        @Provides
        fun providesViewModule(
            fragment: MemeFragment,
            addMemeToFavoriteUseCase: AddMemeToFavoriteUseCase,
            removeMemeFromFavoriteUseCase: RemoveMemeFromFavoriteUseCase,
            isMemeExistUseCase: IsMemeExistUseCase,
            mapperViewStateMapper: MemeViewStateMapper
        ): MemeViewModel {
            return ViewModelProvider(
                fragment,
                MemeViewModel.Factory(addMemeToFavoriteUseCase, removeMemeFromFavoriteUseCase, isMemeExistUseCase, mapperViewStateMapper)
            )[MemeViewModel::class.java]
        }
    }

    @ContributesAndroidInjector(modules = [ProvidesModule::class])
    fun bindMemeFragment(): MemeFragment
}