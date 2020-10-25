package by.alexeypalto.sampleadastra.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Meme")
data class MemeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "createdAt")
    val createdAt: Date
)