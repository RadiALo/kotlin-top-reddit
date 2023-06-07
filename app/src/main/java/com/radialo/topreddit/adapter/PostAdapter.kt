package com.radialo.topreddit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.radialo.topreddit.R
import com.radialo.topreddit.model.Post
import com.squareup.picasso.Picasso

class PostAdapter(private val dataSet: ArrayList<Post>, private val context : Context)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var isLoading = false

    private fun insert(post : Post) {
        dataSet.add(post)
        notifyItemInserted(dataSet.size - 1)
    }

    fun insertAll(posts : List<Post>) {
        for (post in posts) {
            insert(post)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        // Set post values
        holder.authorView.text = dataSet[position].author
        holder.titleView.text = dataSet[position].title
        // Set formatted date and comments count
        holder.dateView.text = context.resources
            .getQuantityText(R.plurals.hours_ago, dataSet[position].getHoursAgo())
        holder.commentsCount.text = context.resources
            .getQuantityText(R.plurals.comments_count, dataSet[position].commentsCount)
        // Load and set image or hide it
        if (dataSet[position].thumbnail != "") {
            holder.thumbnail.visibility = View.VISIBLE
            Picasso.with(holder.thumbnail.context)
                .load(dataSet[position].thumbnail)
                .into(holder.thumbnail)
        } else {
            holder.thumbnail.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class PostViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val authorView : TextView
        val titleView : TextView
        val dateView : TextView
        val commentsCount : TextView
        val thumbnail : ImageView

        init {
            authorView = view.findViewById(R.id.author_text)
            titleView = view.findViewById(R.id.title_text)
            dateView = view.findViewById(R.id.date_text)
            commentsCount = view.findViewById(R.id.comments_count_text)
            thumbnail = view.findViewById(R.id.thumbnail_image)
        }
    }
}
