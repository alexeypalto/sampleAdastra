package by.alexeypalto.sampleadastra.presentation.ui.main

import androidx.lifecycle.ViewModel
import by.alexeypalto.sampleadastra.di.keys.ActivityKey
import by.alexeypalto.sampleadastra.di.keys.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


//@Module
//interface MainActivityModule {
//
//    @Module
//    interface ViewModelModule {
//
//        @Binds
//        @IntoMap
//        @ViewModelKey(MainActivityViewModel::class)
//        abstract fun bindViewModel(viewModel: MainActivityViewModel): ViewModel
//    }
//
//    @Binds
//    @ActivityKey(MainActivity::class)
//    fun bindYourAndroidInjector(): MainActivity
//
//}