package com.radialo.topredditapp.model

import java.util.Date

data class Post(
    val id: String,
    val author: String,
    val title: String,
    val data: Date,
    val thumbnail: String,
    val commentsCount: Int
)