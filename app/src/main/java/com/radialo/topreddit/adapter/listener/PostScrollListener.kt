package com.radialo.topreddit.adapter.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radialo.topreddit.adapter.PostAdapter
import com.radialo.topreddit.service.PostService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostScrollListener(private val postService : PostService) : RecyclerView.OnScrollListener() {
    private val scope = CoroutineScope(Dispatchers.Main)

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
}