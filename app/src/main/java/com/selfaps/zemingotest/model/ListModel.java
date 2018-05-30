package com.selfaps.zemingotest.model;

import android.util.Log;

import com.selfaps.zemingotest.network.Loader;
import com.selfaps.zemingotest.utils.RssParser;

import java.util.HashMap;


public class ListModel implements IModel {
    private static final String TAG = ListModel.class.getSimpleName();
    private HashMap<String,RssParser.RssFeed> feeds = new HashMap<>();

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void updateList(final String[] links, final ResultListener listener) {
            //TODO Add Internet Check

            final Loader loader = new Loader(links[1], new ResultListener() {
                @Override
                public void onSucsess(String xmlContent) {
                    RssParser parser = new RssParser(xmlContent);
                    parser.parse();

                    //Update Feeds
                    feeds.put(links[0],parser.getFeed());
                    listener.onSucsess(links[0]);
                }

                @Override
                public void onError(Throwable error) {
                    Log.d(TAG,"Error "+error.getMessage());
                }
            });
            loader.load();



    }

    public RssParser.RssFeed getFeed(final String feedName){
        return feeds.get(feedName);
    }
}
