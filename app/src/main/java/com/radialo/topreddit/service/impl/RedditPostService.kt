package com.radialo.topreddit.service.impl

import com.radialo.topreddit.mapper.PostMapper
import com.radialo.topreddit.model.Post
import com.radialo.topreddit.service.PostService
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URL
import java.util.concurrent.Callable

class RedditPostService : PostService {
    private var postMapper: PostMapper = PostMapper()
    private var client = OkHttpClient()
    private var url: String = "http://www.reddit.com/top.json"

    private var limit: Int = 10
    private var before: String? = null
    private var after: String? = null
    private var posts: List<Post> = emptyList()

    override suspend fun loadFirstPage(): List<Post> {
        after = null
        before = null
        return getPage()
    }

    override suspend fun loadNextPage(): List<Post> {
        after = posts[posts.size - 1].name
        return getPage()
    }

    override suspend fun loadPrevPage(): List<Post> {
        before = posts[0].name
        return getPage()
    }

    override suspend fun reloadPage(): List<Post> {
        return getPage()
    }

    private suspend fun getPage(): List<Post> {
        val url = URL(url
                + "?limit=" + limit.toString()
                + if (after != null) "&after=" + after else ""
                + if (before != null) "&before=" + before else "")
        after = null
        before = null
        val request = Request.Builder().url(url).build()
        try {
            val response = client.newCall(request).execute()
            val json = JSONObject(response.body?.string())
            posts = postMapper.toModel(json
                .getJSONObject("data")
                .getJSONArray("children"))
            return posts
        } catch (e: Exception) {
            throw java.lang.RuntimeException("Can't get top posts from Reddit", e)
        }
    }
}