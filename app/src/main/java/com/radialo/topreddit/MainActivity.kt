package com.radialo.topreddit

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.radialo.topreddit.adapter.PostAdapter
import com.radialo.topreddit.model.Post
import com.radialo.topreddit.service.impl.RedditPostService
import kotlinx.coroutines.*
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private val postService = RedditPostService()
    private lateinit var postsList : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postsList = findViewById(R.id.posts_list)
        val prevButton = findViewById<Button>(R.id.prev_button)
        val nextButton = findViewById<Button>(R.id.next_button)
        scope.launch {
            setPosts(withContext(Dispatchers.IO) {
                postService.loadFirstPage()
            })
        }
        prevButton.setOnClickListener {
            scope.launch {
                setPosts(withContext(Dispatchers.IO) {
                    postService.loadPrevPage()
                })
            }
        }
        nextButton.setOnClickListener {
            scope.launch {
                setPosts(withContext(Dispatchers.IO) {
                    postService.loadNextPage()
                })
            }
        }
    }

    private fun setPosts(posts : List<Post>) {
        postsList.adapter = PostAdapter(this, R.layout.item_post, posts)
    }
}