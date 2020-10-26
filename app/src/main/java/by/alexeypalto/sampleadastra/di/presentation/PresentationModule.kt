package by.alexeypalto.sampleadastra.di.presentation

import dagger.Module

@Module(includes = [
    AllMemesModule::class,
    FavoriteMemesModule::class,
    MemeModule::class
])
interface PresentationModule