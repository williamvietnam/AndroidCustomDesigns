package com.designs.java.components.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.designs.java.utilities.AppLogger;

/**
 * Author: William Nguyen
 */
public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    public static final String TAG = EndlessRecyclerViewScrollListener.class.getCanonicalName();

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 2;
    // The current offset index of data you have loaded
    private int currentPage = 0;
    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;
    // True if we are still waiting for the last set of data to load.
    private boolean loading = false;
    // Sets the starting page index
    private int startingPageIndex = 0;
    private int currentScrollPosition = 0;
    private IOnScrollView listenerOnScrollView;


    RecyclerView.LayoutManager mLayoutManager;

    public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public EndlessRecyclerViewScrollListener(GridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
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

    public void setListenerOnScrollView(IOnScrollView listenerOnScrollView) {
        this.listenerOnScrollView = listenerOnScrollView;
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    @Override
    public void onScrolled(@NonNull RecyclerView view, int dx, int dy) {
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
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
//        if (loading && (totalItemCount > previousTotalItemCount)) {
//            loading = false;
//            previousTotalItemCount = totalItemCount;
//        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            loading = true;
            onLoadMore(currentPage, totalItemCount, view);
        }
        currentScrollPosition += dy;
        if (currentScrollPosition == 0) {
            AppLogger.d(TAG + " onScrolled first position");
            if (listenerOnScrollView != null) {
                listenerOnScrollView.onGoToTop();
            }
        } else {
            if (dy > 0) {
                AppLogger.d(TAG + " onScrolled scroll down");
            } else if (dy < 0) {
                AppLogger.d(TAG + " onScrolled scroll up");
                if (listenerOnScrollView != null) {
                    listenerOnScrollView.onUpView();
                }
            } else {
                AppLogger.d(TAG + " onScrolled None");
            }
        }
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                if (listenerOnScrollView != null) {
                    listenerOnScrollView.onScrollDone();
                }
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                if (listenerOnScrollView != null) {
                    listenerOnScrollView.onScrollingDown();
                }
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                break;

        }
    }

    // Call this method whenever performing new searches
    public void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = false;
    }

    // Defines the process for actually loading more data based on page
    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public interface IOnScrollView {
        void onGoToTop();

        void onScrollingDown();

        void onScrollDone();

        void onUpView();
    }

}
