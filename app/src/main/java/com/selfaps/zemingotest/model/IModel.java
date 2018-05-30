package com.selfaps.zemingotest.model;


import com.selfaps.zemingotest.utils.RssParser;

import java.util.HashMap;


public interface IModel{

    void connect();
    void disconnect();
    void updateList(String[] links,ResultListener listener);
    RssParser.RssFeed getFeed(final String feedName);
}
