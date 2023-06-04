package com.radialo.topreddit.service

import com.radialo.topreddit.model.Post

interface PostService {
    fun loadFirstPage() : List<Post>
    fun loadNextPage() : List<Post>
    fun loadPrevPage() : List<Post>
    fun reloadPage() : List<Post>
    fun setPerPage(limit: Int) : List<Post>
}