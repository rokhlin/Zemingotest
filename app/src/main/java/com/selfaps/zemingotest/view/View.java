package com.selfaps.zemingotest.view;


import com.selfaps.zemingotest.model.RssItem;
import com.selfaps.zemingotest.utils.RssParser;

import java.util.ArrayList;

public interface View extends MvpView {
    void showList();
    String[] getCategories();
    void showProgress();
    void hideProgress();
    void setLastVisited(String label);


}
