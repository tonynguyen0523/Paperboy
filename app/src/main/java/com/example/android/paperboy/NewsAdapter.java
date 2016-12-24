package com.example.android.paperboy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tonynguyen on 12/7/16.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

    private List<News> mNewsList;
    private Context mContext;
    private int mItemResource;

    public NewsAdapter(Context context, int itemResource, List<News> newsList) {

        mContext = context;
        mItemResource = itemResource;
        mNewsList = newsList;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(mItemResource, parent, false);
        return new NewsHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {

        News news = mNewsList.get(position);
        holder.bindNews(news);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    /**
     * Set all data into the news list
     */
    public void setNewsList(List<News> news) {
        mNewsList = news;
        notifyDataSetChanged();
    }
}
