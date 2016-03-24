package com.example.csiwale.diaryapp;

/**
 * Created by csiwale on 2/17/2016.
 */
public class NewsItem {
    private String title;
    private String keywords;
    private String overview;
    private String createdAt;

    public NewsItem(String title, String keywords, String overview, String createdAt) {
        this.title = title;
        this.keywords = keywords;
        this.overview = overview;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
