package by.alexeypalto.sampleadastra.di.presentation

import androidx.lifecycle.ViewModelProvider
import by.alexeypalto.sampleadastra.domain.errors.ErrorHandler
import by.alexeypalto.sampleadastra.domain.uscases.GetFavoriteMemesUseCase
import by.alexeypalto.sampleadastra.presentation.ui.favoritememes.FavoriteMemesFragment
import by.alexeypalto.sampleadastra.presentation.ui.favoritememes.FavoriteMemesViewModel
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewStateMapper
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
interface FavoriteMemesModule {

    @Module
    object ProvidesModule {
        @Provides
        fun providesViewModule(
            fragment: FavoriteMemesFragment,
            getFavoriteMemesUseCase: GetFavoriteMemesUseCase,
            memesViewStateMapper: MemeViewStateMapper,
            dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
            errorHandler: ErrorHandler
        ): FavoriteMemesViewModel {
            return ViewModelProvider(
                fragment,
                FavoriteMemesViewModel.Factory(getFavoriteMemesUseCase, memesViewStateMapper, dispatcherIO, errorHandler)
            )[FavoriteMemesViewModel::class.java]
        }

    }

    @ContributesAndroidInjector(modules = [ProvidesModule::class])
    fun bindFavMemesFragment(): FavoriteMemesFragment
}