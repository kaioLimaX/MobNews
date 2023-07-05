package com.skydevices.mobnews.util

import java.text.SimpleDateFormat
import java.util.Locale

object UtilDateTime {
    fun formatDateTime(dateTimeString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("'Publicado:' dd/MM/yyyy 'ás 'HH:mm", Locale.getDefault())

        val date = inputFormat.parse(dateTimeString)
        return outputFormat.format(date!!)
    }
}