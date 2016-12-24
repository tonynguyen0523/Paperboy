package com.example.android.paperboy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tonynguyen on 12/7/16.
 */

public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mWebTitle;

    private TextView mSectionName;

    private TextView mWebPubDate;

    private Context mContext;

    private News news;

    private String url;

    public NewsHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mSectionName = (TextView) itemView.findViewById(R.id.section);
        mWebTitle = (TextView) itemView.findViewById(R.id.heading);
        mWebPubDate = (TextView) itemView.findViewById(R.id.web_pub_date);

        itemView.setOnClickListener(this);
    }

    public void bindNews(News news) {

        this.news = news;
        this.mWebTitle.setText(news.getWebTitle());
        this.mSectionName.setText(news.getSectionName());
        this.mWebPubDate.setText(news.getWebPublicationDate());
    }

    /**
     * When user click on card, will send user to website of article
     */
    @Override
    public void onClick(View view) {

        // Get url of the article clicked
        url = news.getUrl();

        // Convert string url to Uri object
        Uri newsUri = Uri.parse(url);

        // Create  and start intent to launch browser
        Intent webIntent = new Intent(Intent.ACTION_VIEW, newsUri);
        mContext.startActivity(webIntent);

    }
}