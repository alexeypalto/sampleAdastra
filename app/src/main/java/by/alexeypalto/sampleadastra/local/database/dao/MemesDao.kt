package by.alexeypalto.sampleadastra.local.database.dao

import androidx.room.*
import by.alexeypalto.sampleadastra.local.database.entities.MemeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMemeToFavorites(meme: MemeEntity)

    @Query("DELETE FROM Meme WHERE id == :id")
    suspend fun removeMemeFromFavorites(id: Int)

    @Query("SELECT * FROM Meme")
    fun getFavoriteMemes(): Flow<List<MemeEntity>?>
}