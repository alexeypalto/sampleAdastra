package by.alexeypalto.sampleadastra.di

import by.alexeypalto.sampleadastra.di.data.DataModule
import by.alexeypalto.sampleadastra.di.domain.DomainModule
import by.alexeypalto.sampleadastra.di.local.LocalModule
import by.alexeypalto.sampleadastra.di.presentation.PresentationModule
import by.alexeypalto.sampleadastra.di.remote.RemoteModule
import dagger.Module

@Module(includes = [
    DomainModule::class,
    DataModule::class,
    LocalModule::class,
    RemoteModule::class,
    PresentationModule::class
])
interface ModelModule