package by.alexeypalto.sampleadastra.di.presentation

import androidx.lifecycle.ViewModelProvider
import by.alexeypalto.sampleadastra.di.FragmentFactory
import by.alexeypalto.sampleadastra.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [
//    MainActivityModule::class,
    MemesModule::class
])
interface PresentationModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindFragmentFactory(factory: FragmentFactory): androidx.fragment.app.FragmentFactory
}