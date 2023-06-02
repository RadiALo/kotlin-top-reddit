package com.radialo.topreddit.mapper

import com.radialo.topreddit.model.Post
import org.json.JSONObject
import java.util.Date

class PostMapper : GenericMapper<Post> {
    override fun toModel(json: JSONObject): Post {
        return Post(
            json.getString("name"),
            json.getString("author_fullname"),
            json.getString("title"),
            Date(json.getLong("created") * 1000L),
            json.getString("thumbnail"),
            json.getInt("num_comments")
        )
    }
}