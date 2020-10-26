package by.alexeypalto.sampleadastra.di

import android.content.Context
import by.alexeypalto.sampleadastra.AdastraApp
import by.alexeypalto.sampleadastra.di.data.DataModule
import by.alexeypalto.sampleadastra.di.domain.DomainModule
import by.alexeypalto.sampleadastra.di.presentation.PresentationModule
import by.alexeypalto.sampleadastra.datas.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    PresentationModule::class,
    DomainModule::class,
    DataModule::class,
    RemoteModule::class,
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun injectApp(application: AdastraApp)
}