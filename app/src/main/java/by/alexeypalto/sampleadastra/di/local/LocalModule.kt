package by.alexeypalto.sampleadastra.di.local

import android.content.Context
import by.alexeypalto.sampleadastra.local.database.AdastraDB
import by.alexeypalto.sampleadastra.local.database.dao.MemesDao
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalModule.ProvidesModule::class])
interface LocalModule {

    @Module
    object ProvidesModule {

        @Provides
        @Singleton
        fun providesDataBase(context: Context): AdastraDB {
            return AdastraDB.getInstance(context)
        }

        @Provides
        @Singleton
        fun providesProfileDao(projectDatabase: Lazy<AdastraDB>): MemesDao {
            return projectDatabase.get().memesDao()
        }
    }
}