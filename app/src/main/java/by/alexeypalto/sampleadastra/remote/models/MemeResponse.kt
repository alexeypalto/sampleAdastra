package by.alexeypalto.sampleadastra.remote.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class MemeResponse(
    @SerializedName("_id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("createdAt")
    val createdAt: Date
)