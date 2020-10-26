package by.alexeypalto.sampleadastra.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.alexeypalto.sampleadastra.local.database.entities.MemeEntity

@Dao
interface MemesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMemeToFavorites(meme: MemeEntity)

    @Query("DELETE FROM Meme WHERE id == :id")
    suspend fun removeMemeFromFavorites(id: String)

    @Query("SELECT * FROM Meme")
    fun getFavoriteMemes(): List<MemeEntity>?

    @Query("SELECT EXISTS(SELECT * FROM Meme WHERE id = :id)")
    fun isMemeIsExist(id : String) : Boolean
}