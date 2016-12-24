package com.example.android.paperboy;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by tonynguyen on 12/7/16.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        // if there is no url then return early
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<News> result = QueryUtil.fetchNewsData(mUrl);
        return result;
    }
}
