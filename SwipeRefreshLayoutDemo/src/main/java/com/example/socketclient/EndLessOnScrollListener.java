package com.example.socketclient;

/**
 * Created by albus on 2017/12/28.
 */
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/** * Created by wnw on 16-5-26. */
public abstract class EndLessOnScrollListener extends  RecyclerView.OnScrollListener{

    //声明一个LinearLayoutManager
    private LinearLayoutManager mLinearLayoutManager;

     private int currentPage = 0;
    //已经加载出来的Item的数量
    private int 已经加载出来的Item的数量;

    //主要用来存储上一个totalItemCount
    private int 上一个已经加载出来的Item的数量 = 0;

    //在屏幕上可见的item数量
    private int 在屏幕上可见的item数量;

    //在屏幕可见的Item中的第一个
    private int 在屏幕可见的Item中的第一个;

    //是否正在上拉数据
    private boolean 是否正在上拉数据 = true;

    public EndLessOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        在屏幕上可见的item数量 = recyclerView.getChildCount();
        已经加载出来的Item的数量 = mLinearLayoutManager.getItemCount();
        在屏幕可见的Item中的第一个 = mLinearLayoutManager.findFirstVisibleItemPosition();
        if(是否正在上拉数据){
            //Log.d("wnwn","在屏幕可见的Item中的第一个: " +在屏幕可见的Item中的第一个);
            //Log.d("wnwn","totalPageCount:" +已经加载出来的Item的数量);
            //Log.d("wnwn", "在屏幕上可见的item数量:" + 在屏幕上可见的item数量)；

            if(已经加载出来的Item的数量 > 上一个已经加载出来的Item的数量){
                //说明数据已经加载结束
                是否正在上拉数据 = false;
                上一个已经加载出来的Item的数量 = 已经加载出来的Item的数量;
            }
        }
        //这里需要好好理解
        if (!是否正在上拉数据 && 已经加载出来的Item的数量 - 在屏幕上可见的item数量 <= 在屏幕可见的Item中的第一个){
            currentPage ++;
            onLoadMore(currentPage);
            是否正在上拉数据 = true;
        }
    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     * */
    public abstract void onLoadMore(int currentPage);}
