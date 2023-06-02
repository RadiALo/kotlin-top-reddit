package com.radialo.topreddit.service

import com.radialo.topreddit.model.Post

interface PostService {
    fun getByPage() : List<Post>
}