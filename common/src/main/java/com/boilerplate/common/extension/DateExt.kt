package com.boilerplate.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(pattern: String = "yyyy-MM-dd HH:mm:ss"
): Date {
    return try {
        SimpleDateFormat(pattern,Locale.US).parse(this)
    } catch (e: Exception) {
        Date()
    }
}

fun Long.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar
}

fun Long.dateFormat(pattern: String = "dd MMM yyyy, HH:mm:ss"): String {
    val sdf = SimpleDateFormat(pattern, Locale.US)
    return sdf.format(Date(this))
}