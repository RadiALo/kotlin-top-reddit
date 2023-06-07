package com.radialo.topreddit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radialo.topreddit.adapter.PostAdapter
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

        scope.launch {
            postsList.adapter = PostAdapter(ArrayList(withContext(Dispatchers.IO) {
                postService.loadFirstPage()
            }))
        }


        postsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val adapter = recyclerView.adapter as PostAdapter
                val manager = recyclerView.layoutManager as LinearLayoutManager
                if (!adapter.isLoading) {
                    if (manager.itemCount - manager.childCount <=
                        manager.findFirstVisibleItemPosition()
                    ) {
                        adapter.isLoading = true
                        scope.launch {
                            adapter.insertAll(withContext(Dispatchers.IO) {
                                postService.loadNextPage()
                            })
                            adapter.isLoading = false
                        }
                    }
                }
            }
        })
    }
}