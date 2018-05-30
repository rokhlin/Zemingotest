package com.selfaps.zemingotest.presenter;

import android.util.Log;

import com.selfaps.zemingotest.model.IModel;
import com.selfaps.zemingotest.model.ListModel;
import com.selfaps.zemingotest.model.ResultListener;
import com.selfaps.zemingotest.model.RssItem;
import com.selfaps.zemingotest.utils.Constants;
import com.selfaps.zemingotest.view.View;

import java.util.ArrayList;
import java.util.HashMap;


public class Presenter implements IPresenter<View> {
    private static final String TAG = Presenter.class.getSimpleName();
    private View view;
    private IModel model;
    private HashMap<String,DelayUpdate> updaters = new HashMap<>();

    public Presenter() {
        this.model = new ListModel();
    }

    @Override
    public void attachView(View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        interruptUpdates();
        view = null;
    }

    /**
     * Interrupt repeated update of the detached list
     */
    private void interruptUpdates() {
        for (DelayUpdate item: updaters.values()){
            item.interrupt();
        }
    }

    @Override
    public int getCount() {
        try {
            ArrayList<RssItem> items =  model.getFeed(Constants.CATEGORY_CARS[0]).getFeedItems();
            if(items == null ) return 0;
            return items.size();
        } catch (Exception e) {
            //Error fix on start
            return 0;
        }
    }

    @Override
    public void destroy() {
        //Clean all resources
        detachView();
        model = null;
    }

    @Override
    public void loadFeed(String[]... feed) {

        for (int i = 0; i <feed.length ; i++) {
            if(updaters.containsKey(feed[i][0])) continue;

            DelayUpdate update = new DelayUpdate();
            update.start(feed[i]);
            updaters.put(feed[i][0],update);
        }
    }


    @Override
    public ArrayList<RssItem> getFeed(String... links) {
        ArrayList<RssItem> items = new ArrayList<>();
        for (String link : links) {
            items.addAll(model.getFeed(link).getFeedItems());
        }
        return items;
    }

    @Override
    public void markPageVisited(String title) {
        view.setLastVisited(title);
    }

    class DelayUpdate {
        private boolean isInterrupted = false;

        public void interrupt(){
            isInterrupted = true;
        }

        private void restartIfAvailable(String[] feed){
            if(isInterrupted) return;
            start(feed);
        }

        public void start(final String[] feed){


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(view != null) view.showProgress();

                        //Start update
                        model.updateList(feed, new ResultListener() {
                            @Override
                            public void onSucsess(String category) {
                                if(view!= null)
                                    view.showList();
                            }

                            @Override
                            public void onError(Throwable error) {
                                Log.d(TAG,"Error on timer "+ error.getMessage());
                            }
                        });

                        view.hideProgress();

                        Thread.sleep(Constants.UPDATE_DELAY);
                        restartIfAvailable(feed);

                    } catch (InterruptedException e) {
                        Log.d(TAG,"InterruptedException on timer "+ e.getMessage());
                        restartIfAvailable(feed);
                    }
                }
            });
            thread.start();
        }
    }

}
