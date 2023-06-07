package com.radialo.topreddit.model

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

    fun getHoursAgoFormatted() : String {
        val hours = getHoursAgo()
        return if (hours == 1L) {
            "1 hour ago"
        } else if (hours < 24L) {
            "$hours hours ago"
        } else if (hours == 24L) {
            "1 day ago"
        } else {
            val days = hours / 24L
            "$days days ago"
        }
    }
}