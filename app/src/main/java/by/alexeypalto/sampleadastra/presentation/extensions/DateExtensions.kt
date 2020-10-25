package by.alexeypalto.sampleadastra.presentation.extensions

import java.text.SimpleDateFormat
import java.util.*

const val COMMON_FORMAT = "dd MMM, HH:mm"

fun Date.convertToString(format: String? = COMMON_FORMAT, locale: Locale = Locale.ENGLISH): String {
    val converter = SimpleDateFormat(format ?: COMMON_FORMAT, locale)
    return converter.format(this)
}