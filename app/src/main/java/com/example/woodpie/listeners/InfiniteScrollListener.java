package com.example.woodpie.listeners;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/**
 * Created by asmita on 18/8/15.
 */
public abstract class InfiniteScrollListener implements OnScrollListener
{
    private int visibleThreshold = 10;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 0;

    public InfiniteScrollListener()
    {

    }

    public InfiniteScrollListener(int visibleThreshold)
    {
        this.visibleThreshold = visibleThreshold;
    }

    public InfiniteScrollListener(int visibleThreshold, int startingPageIndex)
    {
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startingPageIndex;
        this.currentPage = startingPageIndex;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if (totalItemCount < previousTotalItemCount)
        {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0)
                this.loading = true;
        }
        if (loading && (totalItemCount > previousTotalItemCount))
        {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage++;
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold))
        {
            onLoadMore(currentPage + 1, totalItemCount);
            loading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemsCount);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {

    }
}
