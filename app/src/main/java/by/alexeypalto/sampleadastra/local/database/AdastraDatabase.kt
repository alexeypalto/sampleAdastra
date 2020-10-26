package by.alexeypalto.sampleadastra.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.alexeypalto.sampleadastra.local.database.converter.DateConverter
import by.alexeypalto.sampleadastra.local.database.dao.MemesDao
import by.alexeypalto.sampleadastra.local.database.entities.MemeEntity

@Database(entities = [MemeEntity::class], version = 5, exportSchema = false)
@TypeConverters(value = [DateConverter::class])
abstract class AdastraDatabase : RoomDatabase() {

    abstract fun memesDao(): MemesDao
}
