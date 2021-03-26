package com.mahmoud_ashraf.dailyforecastapplication.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Format date-time info readable day-date-time
 */
fun String.toReadableDateTime(): String? {
    try {
    val currentDate =  Date (this.toLong().times(1000))
    val dateFormat =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault())
        return dateFormat.format(currentDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return null
}

