package by.alexeypalto.sampleadastra.di.presentation

import androidx.lifecycle.ViewModelProvider
import by.alexeypalto.sampleadastra.domain.errors.ErrorHandler
import by.alexeypalto.sampleadastra.domain.uscases.GetMemesUseCase
import by.alexeypalto.sampleadastra.presentation.ui.allmemes.AllMemesFragment
import by.alexeypalto.sampleadastra.presentation.ui.allmemes.AllMemesViewModel
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewStateMapper
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
interface AllMemesModule {

    @Module
    object ProvidesModule {
        @Provides
        fun providesViewModule(
            fragment: AllMemesFragment,
            getAllMemesUseCase: GetMemesUseCase,
            mapper: MemeViewStateMapper,
            errorHandler: ErrorHandler
        ): AllMemesViewModel {
            return ViewModelProvider(
                fragment,
                AllMemesViewModel.Factory(getAllMemesUseCase, mapper, errorHandler)
            )[AllMemesViewModel::class.java]
        }
    }

    @ContributesAndroidInjector(modules = [ProvidesModule::class])
    fun bindAllMemesFragment(): AllMemesFragment
}