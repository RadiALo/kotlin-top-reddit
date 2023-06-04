package com.radialo.topreddit

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.radialo.topreddit.adapter.PostAdapter
import com.radialo.topreddit.service.impl.RedditPostService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private val workerPool = Executors.newFixedThreadPool(1)
    private val postService = RedditPostService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val prevButton = findViewById<Button>(R.id.prev_button)
        val nextButton = findViewById<Button>(R.id.next_button)
        postService.loadFirstPage()
        prevButton.setOnClickListener {
            postService.loadNextPage()
        }
        nextButton.setOnClickListener {
            postService.loadPrevPage()
        }
    }
}