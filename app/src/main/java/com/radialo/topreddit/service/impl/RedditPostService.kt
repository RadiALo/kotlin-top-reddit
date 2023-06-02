package com.radialo.topreddit.service.impl

import com.radialo.topreddit.mapper.PostMapper
import com.radialo.topreddit.model.Post
import com.radialo.topreddit.service.PostService
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URI
import java.net.URL
import java.util.concurrent.Callable

class RedditPostService : PostService, Callable<List<Post>> {
    private var postMapper: PostMapper = PostMapper()
    private var client = OkHttpClient();
    private var url: String = "http://www.reddit.com/top.json"

    private var limit: Int = 5
    private var after: String? = null

    override fun getByPage(): List<Post> {
        val url = URL(url
                + "?limit=" + limit.toString()
                + if (after != null) "&after=" + after else "");
        val request = Request.Builder().url(url).build()
        try {
            val response = client.newCall(request).execute()
            val json = JSONObject(response.body?.string())
            return postMapper.toModel(json
                .getJSONObject("data")
                .getJSONArray("children"))
        } catch (e: Exception) {
            throw java.lang.RuntimeException("Can't get top posts from Reddit", e)
        }
    }

    override fun call(): List<Post> {
        return getByPage()
    }
}