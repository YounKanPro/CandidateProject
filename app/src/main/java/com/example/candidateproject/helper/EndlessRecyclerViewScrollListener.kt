package com.example.candidateproject.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerViewScrollListener: RecyclerView.OnScrollListener {

        private var visibleThreshold = 5

        private var currentPage = 0

        private var previousTotalItemCount = 0

        private var loading = true

        private val startingPageIndex = 0
        var mLayoutManager: RecyclerView.LayoutManager

        constructor(layoutManager: LinearLayoutManager) {
            mLayoutManager = layoutManager
        }


        fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
            var maxSize = 0
            for (i in lastVisibleItemPositions.indices) {
                if (i == 0) {
                    maxSize = lastVisibleItemPositions[i]
                } else if (lastVisibleItemPositions[i] > maxSize) {
                    maxSize = lastVisibleItemPositions[i]
                }
            }
            return maxSize
        }

        override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
            var lastVisibleItemPosition = 0
            val totalItemCount = mLayoutManager.itemCount
            lastVisibleItemPosition = (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()

            if (totalItemCount < previousTotalItemCount) {
                currentPage = startingPageIndex
                previousTotalItemCount = totalItemCount
                if (totalItemCount == 0) {
                    loading = true
                }
            }

            if (loading && totalItemCount > previousTotalItemCount) {
                loading = false
                previousTotalItemCount = totalItemCount
            }

            if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
                currentPage++
                onLoadMore(currentPage, totalItemCount, view)
                loading = true
            }
        }

        fun resetState() {
            currentPage = startingPageIndex
            previousTotalItemCount = 0
            loading = true
            mLayoutManager.removeAllViews()
        }

        abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?)
}