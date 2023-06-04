package com.radialo.topreddit.adapter

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.radialo.topreddit.R
import com.radialo.topreddit.model.Post
import com.radialo.topreddit.util.ImageUrlReader
import com.squareup.picasso.Picasso
import kotlin.time.Duration.Companion.hours

class PostAdapter(
    context: Context?,
    resourceId: Int,
    items: List<Post?>?
) : ArrayAdapter<Post>(context!!, resourceId, items!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val post = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        val authorView = view.findViewById<TextView>(R.id.author_text)
        val titleView = view.findViewById<TextView>(R.id.title_text)
        val dateView = view.findViewById<TextView>(R.id.date_text)
        val commentsCount = view.findViewById<TextView>(R.id.comments_count_text)
        val thumbnail = view.findViewById<ImageView>(R.id.thumbnail_image)
        titleView.text = post?.title
        authorView.text = post?.author
        val hours = post?.getHoursAgo() ?: 0
        if (hours == 1L) {
            dateView.text = "1 hour ago"
        } else if (hours < 24L) {
            dateView.text = hours.toString() + " hours ago"
        } else if (hours == 24L) {
            dateView.text = "1 day ago"
        } else if (hours < 168L) {
            dateView.text = (hours.toString()) + "days ago"
        } else if (hours == 168L){
            dateView.text = "1 month ago"
        } else {
            dateView.text = (hours / 168L).toString() + " monhts ago"
        }
        if (post?.thumbnail != null) {
            thumbnail.visibility = View.VISIBLE
            Picasso.with(context)
                .load(post.thumbnail)
                .into(thumbnail)
        }
        commentsCount.text = post?.commentsCount.toString()
        return view
    }
}