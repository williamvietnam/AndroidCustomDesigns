package com.material.design.components.recyclerview;

import android.graphics.Rect;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class EndlessRecyclerViewNestedScrollView implements NestedScrollView.OnScrollChangeListener {
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 5;
    // The current offset index of data you have loaded
    private int currentPage = 0;
    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;
    // True if we are still waiting for the last set of data to load.
    private boolean loading = true;
    // Sets the starting page index
    private int startingPageIndex = 0;
    private boolean hasMoreData = true;
    RecyclerView.LayoutManager mLayoutManager;

    public EndlessRecyclerViewNestedScrollView(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public EndlessRecyclerViewNestedScrollView(GridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    public EndlessRecyclerViewNestedScrollView(StaggeredGridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (v.getChildAt(v.getChildCount() - 1) != null) {
            if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                    scrollY > oldScrollY) {
                int lastVisibleItemPosition = 0;
                int totalItemCount = mLayoutManager.getItemCount();

                if (mLayoutManager instanceof StaggeredGridLayoutManager) {
                    int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null);
                    // get maximum element within the list
                    lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
                } else if (mLayoutManager instanceof GridLayoutManager) {
                    lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                } else if (mLayoutManager instanceof LinearLayoutManager) {
                    lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                }

                // If the total item count is zero and the previous isn't, assume the
                // list is invalidated and should be reset back to initial state
//        if (totalItemCount < previousTotalItemCount) {
//            this.currentPage = this.startingPageIndex;
//            this.previousTotalItemCount = totalItemCount;
//            if (totalItemCount == 0) {
//                this.loading = true;
//            }
//        }
                // If itâ€™s still loading, we check to see if the dataset count has
                // changed, if so we conclude it has finished loading and update the current page
                // number and total item count.
//        if (loading && (totalItemCount > previousTotalItemCount)) {
//            loading = false;
//            previousTotalItemCount = totalItemCount;
//        }

                // If it isnâ€™t currently loading, we check to see if we have breached
                // the visibleThreshold and need to reload more data.
                // If we do need to reload some more data, we execute onLoadMore to fetch the data.
                // threshold should reflect how many total columns there are too
                if (!loading && hasMoreData && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
                    currentPage++;
                    onLoadMore(currentPage, totalItemCount, v);
                    loading = true;
                }
            }
        }
        Rect mReact = new Rect();
        v.getHitRect(mReact);
        scrollView(mReact);
    }

    public void setLoading(boolean isLoading, boolean reversePage) {
        this.loading = isLoading;
        // use for error case when loading api)
        if (reversePage) {
            if (currentPage > 0) {
                currentPage--;
            }
        }
    }

    // Call this method whenever performing new searches
    public void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = true;
        hasMoreData = true;
    }

    // Defines the process for actually loading more data based on page
    public abstract void onLoadMore(int page, int totalItemsCount, NestedScrollView view);

    public abstract void scrollView(Rect r);

    public void setHasMoreData(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
    }

}



