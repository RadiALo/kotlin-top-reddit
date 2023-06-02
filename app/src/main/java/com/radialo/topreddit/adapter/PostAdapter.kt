package com.radialo.topreddit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.radialo.topreddit.R
import com.radialo.topreddit.model.Post

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
        titleView.text = post?.title
        authorView.text = post?.author
        dateView.text = post?.date.toString()
        commentsCount.text = post?.commentsCount.toString()
        return view
    }
}