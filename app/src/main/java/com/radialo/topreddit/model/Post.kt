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
    fun getHoursAgo() : Int {
        return ((Date().time - date.time) / 3600000L).toInt()
    }
}
