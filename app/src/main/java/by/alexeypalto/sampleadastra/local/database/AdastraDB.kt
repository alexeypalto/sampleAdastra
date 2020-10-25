package by.alexeypalto.sampleadastra.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.alexeypalto.sampleadastra.local.database.converter.DateConverter
import by.alexeypalto.sampleadastra.local.database.dao.MemesDao
import by.alexeypalto.sampleadastra.local.database.entities.MemeEntity
import javax.inject.Singleton

@Database(version = 4, entities = [MemeEntity::class])
@TypeConverters(value = [DateConverter::class])
@Singleton
abstract class AdastraDB: RoomDatabase() {

    abstract fun memesDao(): MemesDao

    companion object {

        private const val DB_NAME = "adastra_sample.db"

        @Volatile
        private var INSTANCE: AdastraDB? = null

        fun getInstance(context: Context): AdastraDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context): AdastraDB {
            return Room.databaseBuilder(context, AdastraDB::class.java, DB_NAME).build()
        }

    }
}
