package com.selfaps.zemingotest.model;

/**
 * Created by Ulike Anton on 30.05.2018.
 */

public class  RssItem {
    public  String title;
    public  String description;
    public  String link;
    public  String pubDate;

    public String toString()
    {
        return (this.title + ": " + this.pubDate + "n" + this.description);
    }
}