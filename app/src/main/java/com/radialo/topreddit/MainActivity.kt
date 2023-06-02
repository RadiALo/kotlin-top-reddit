package com.radialo.topreddit

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.radialo.topreddit.adapter.PostAdapter
import com.radialo.topreddit.model.Post
import com.radialo.topreddit.service.impl.RedditPostService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private val workerPool = Executors.newFixedThreadPool(1)
    private val postService = RedditPostService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView: ListView = findViewById<View>(R.id.posts_list) as ListView
        val submit = workerPool.submit(postService)
        while (!submit.isDone);
        listView.adapter = PostAdapter(this, R.layout.item_post, submit.get())
    }
}