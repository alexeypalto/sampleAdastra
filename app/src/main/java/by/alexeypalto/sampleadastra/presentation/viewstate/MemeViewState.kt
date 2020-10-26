package by.alexeypalto.sampleadastra.presentation.viewstate

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class MemeViewState(
    val id: String,
    val url: String,
    val createdAt: Date
) : Parcelable