package by.alexeypalto.sampleadastra.di.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import by.alexeypalto.sampleadastra.di.keys.FragmentKey
import by.alexeypalto.sampleadastra.di.keys.ViewModelKey
import by.alexeypalto.sampleadastra.presentation.ui.allmemes.AllMemesFragment
import by.alexeypalto.sampleadastra.presentation.ui.allmemes.AllMemesViewModel
import by.alexeypalto.sampleadastra.presentation.ui.favoritememes.FavoriteMemesFragment
import by.alexeypalto.sampleadastra.presentation.ui.favoritememes.FavoriteMemesViewModel
import by.alexeypalto.sampleadastra.presentation.ui.meme.MemeFragment
import by.alexeypalto.sampleadastra.presentation.ui.meme.MemeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MemesModule {

//    @Binds
//    @ActivityKey(MainActivity::class)
//    fun bindMain(activity: MainActivity): Activity

    @Binds
    @IntoMap
    @FragmentKey(AllMemesFragment::class)
    fun bindsAllMemesFragment(fragment: AllMemesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(FavoriteMemesFragment::class)
    fun bindsFavoriteMemesFragment(fragment: FavoriteMemesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MemeFragment::class)
    fun bindsMemeFragment(fragment: MemeFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(AllMemesViewModel::class)
    fun bindsAllMemesViewModel(fragment: AllMemesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteMemesViewModel::class)
    fun bindsFavoriteMemesViewModel(fragment: FavoriteMemesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MemeViewModel::class)
    fun bindsMemeViewModel(fragment: MemeViewModel): ViewModel

}