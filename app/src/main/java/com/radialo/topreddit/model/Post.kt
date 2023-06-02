package com.radialo.topreddit.model

import java.util.Date

data class Post(
    val name: String,
    val author: String,
    val title: String,
    val data: Date,
    val thumbnail: String,
    val commentsCount: Int
)