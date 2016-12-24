package com.example.android.paperboy;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String mURL = "http://content.guardianapis.com/search?q=debate&tag=politics/politics";

    private NewsAdapter adapter;

    private List<News> newsList;

    private TextView mEmptyTextView;

    private ProgressBar mProgressBar;

    private RecyclerView mList;

    private RecyclerView.LayoutManager mLayoutManager;

    private String formattedDate =
            new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create newsList
        newsList = new ArrayList<>();

        // Create empty text view object
        mEmptyTextView = (TextView) findViewById(R.id.empty_text_view);

        // Create prograss bar object
        mProgressBar = (ProgressBar) findViewById(R.id.loading_spinner);

        // Initiate adapter
        adapter = new NewsAdapter(this, R.layout.card_view, newsList);

        mLayoutManager = new LinearLayoutManager(this);
        mList = (RecyclerView) findViewById(R.id.list);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(mLayoutManager);
        mList.setAdapter(adapter);

        // Check if there is internet connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            getLoaderManager().initLoader(0, null, this);

        } else {

            // If no internet show empty text view
            // But first hide progress bar.
            mProgressBar.setVisibility(View.GONE);
            mEmptyTextView.setText(R.string.no_internet);
        }
    }


    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        // Create uri
        Uri baseUri = Uri.parse(mURL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("from-date", formattedDate);
        uriBuilder.appendQueryParameter("api-key", "test");

        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {

        // Hide progress bar
        mProgressBar.setVisibility(View.GONE);

        // Check if list is null
        if (newsList != null && !newsList.isEmpty()) {
            adapter.setNewsList(newsList);
        } else {
            // If null then display empty text
            mEmptyTextView.setText(R.string.no_data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.setNewsList(null);
    }
}


