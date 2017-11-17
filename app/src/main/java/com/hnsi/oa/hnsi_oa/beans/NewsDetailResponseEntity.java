package com.hnsi.oa.hnsi_oa.beans;

/**
 * Created by Zheng on 2017/11/4.
 */

public class NewsDetailResponseEntity {
    private NewsDetailEntity news;

    public NewsDetailEntity getNews() {
        return news;
    }

    public void setNews(NewsDetailEntity news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "NewsDetailResponseEntity{" +
                "news=" + news +
                '}';
    }
}
