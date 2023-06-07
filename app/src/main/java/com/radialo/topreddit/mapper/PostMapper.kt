package com.radialo.topreddit.mapper

import com.radialo.topreddit.model.Post
import org.json.JSONObject
import java.util.Date

class PostMapper : GenericMapper<Post> {
    override fun toModel(json: JSONObject): Post {
        return Post(
            json.getString("name"),
            json.getString("author"),
            json.getString("title"),
            Date(json.getLong("created") * 1000L),
            if (json.getBoolean("is_video")) "" else json.getString("thumbnail"),
            json.getString("url"),
            json.getInt("num_comments")
        )
    }
}
