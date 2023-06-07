package com.radialo.topreddit.service.impl

import com.radialo.topreddit.exception.PostsLoadException
import com.radialo.topreddit.mapper.PostMapper
import com.radialo.topreddit.model.Post
import com.radialo.topreddit.service.PostService
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URL

class RedditPostService : PostService {
    private val postMapper: PostMapper = PostMapper()
    private val client = OkHttpClient()
    private val url: String = "http://www.reddit.com/top.json"

    private var limit: Int = 25
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

    private fun getPage(): List<Post> {
        val url = URL("$url?limit= $limit"
                + if (after != null) "&after=$after" else ""
                + if (before != null) "&before=$before" else "")
        val request = Request.Builder().url(url).build()
        try {
            val response = client.newCall(request).execute()
            val json = JSONObject(response.body?.string())
            posts = postMapper.toModel(json
                .getJSONObject("data")
                .getJSONArray("children"))
            return posts
        } catch (e: Exception) {
            throw PostsLoadException("Can't get top posts from Reddit", e)
        }
    }
}