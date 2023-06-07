package com.radialo.topreddit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radialo.topreddit.adapter.PostAdapter
import com.radialo.topreddit.adapter.listener.PostScrollListener
import com.radialo.topreddit.service.impl.RedditPostService
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private val postService = RedditPostService()

    private lateinit var postsList : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postsList = findViewById(R.id.posts_list)
        postsList.layoutManager = LinearLayoutManager(this)
        // Get first page
        scope.launch {
            postsList.adapter = PostAdapter(ArrayList(withContext(Dispatchers.IO) {
                postService.loadFirstPage()
            }), this@MainActivity)
        }
        // Set loading new posts on scroll
        postsList.addOnScrollListener(PostScrollListener(postService))
    }
}
