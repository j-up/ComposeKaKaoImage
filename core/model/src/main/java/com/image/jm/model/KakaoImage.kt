package com.image.jm.model

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class KakaoImage(val thumbnail: String, val dateTime: String, val isBookmark: Boolean = false) {
    fun dateToString(): String {
        val inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val dateTime = OffsetDateTime.parse(dateTime, inputFormatter)

        val outputFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.KOREA)

        return dateTime.format(outputFormatter)
    }
}

private const val DATE_PATTERN = "yyyy-MM-dd HH시 mm분"
