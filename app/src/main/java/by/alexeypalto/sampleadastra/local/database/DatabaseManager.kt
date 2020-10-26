package by.alexeypalto.sampleadastra.local.database

import android.content.Context
import androidx.room.Room

private const val DB_NAME = "adastra_sample.db"

@Volatile
lateinit var database: AdastraDatabase

object DatabaseManager {

    @Synchronized
    fun createDb(context: Context) {
        database = Room.databaseBuilder(context, AdastraDatabase::class.java, DB_NAME)
            .allowMainThreadQueries()
            .build()
    }
}