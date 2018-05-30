package com.selfaps.zemingotest.model;

import com.selfaps.zemingotest.utils.RssParser;

/**
 * Created by Ulike Anton on 30.05.2018.
 */

public abstract class ResultListener {
    public abstract void onSucsess(String result);
    public abstract void onError(Throwable error);
}