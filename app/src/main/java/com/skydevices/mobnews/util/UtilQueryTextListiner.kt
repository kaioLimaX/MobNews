package com.skydevices.mobnews.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.coroutineScope
import com.skydevices.mobnews.util.Constants.Companion.SEARCH_NEW_DELAY
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class UtilQueryTextListiner(
    lifecycle: Lifecycle,
    private val utilQueryTextListiner: (String?) -> Unit
) : androidx.appcompat.widget.SearchView.OnQueryTextListener, LifecycleObserver {
    private val coroutineScope = lifecycle.coroutineScope
    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                delay(SEARCH_NEW_DELAY)
                utilQueryTextListiner(newText)

            }

        }
        return false
    }

}