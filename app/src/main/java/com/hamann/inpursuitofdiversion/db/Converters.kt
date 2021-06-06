package com.hamann.inpursuitofdiversion.db

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    @TypeConverter
    fun stringToDate(stringDate: String): Date? {
        val sdf = SimpleDateFormat("YYYY-MM-dd")
        return try {
            sdf.parse(stringDate)
        } catch (t: Throwable) {
            null
        }
    }

    @TypeConverter
    fun dateToString(date: Date?): String {
        val sdf = SimpleDateFormat("YYYY-MM-dd")
        return if (date == null) {
            ""
        } else {
            sdf.format(date)
        }
    }
}