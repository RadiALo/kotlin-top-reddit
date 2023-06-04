package com.radialo.topreddit.model

import java.time.LocalDate
import java.time.Period
import java.util.Date

data class Post(
    val name: String,
    val author: String,
    val title: String,
    val date: Date,
    val thumbnail: String,
    val commentsCount: Int
) {
    fun getHoursAgo() : Long {
        return (Date().time - date.time) / 3600000L
    }
}