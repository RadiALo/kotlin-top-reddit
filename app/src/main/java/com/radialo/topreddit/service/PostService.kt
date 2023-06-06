package com.radialo.topreddit.service

import com.radialo.topreddit.model.Post

interface PostService {
    suspend fun loadFirstPage() : List<Post>
    suspend fun loadNextPage() : List<Post>
    suspend fun loadPrevPage() : List<Post>
    suspend fun reloadPage() : List<Post>
}