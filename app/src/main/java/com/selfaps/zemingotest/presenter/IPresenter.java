package com.selfaps.zemingotest.presenter;

import com.selfaps.zemingotest.model.RssItem;
import com.selfaps.zemingotest.view.View;

import java.util.ArrayList;


public interface IPresenter<V extends View> {

    void attachView(V view);
    void detachView();
    int getCount();
    void destroy();
    void loadFeed(String[][] feed);
    ArrayList<RssItem> getFeed(String... links);
    void markPageVisited(String title);
}
