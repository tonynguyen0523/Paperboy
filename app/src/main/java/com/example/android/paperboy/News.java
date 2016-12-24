package com.example.android.paperboy;

/**
 * Created by tonynguyen on 12/7/16.
 */

public class News {

    private String mWebTitle;

    private String mSectionName;

    private String mUrl;

    private String mWebPublicationDate;

    public News(String webtitle, String sectionName, String url, String webPublicationDate) {

        mWebTitle = webtitle;
        mSectionName = sectionName;
        mUrl = url;
        mWebPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {

        return mWebTitle;
    }

    public String getSectionName() {

        return mSectionName;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }

}