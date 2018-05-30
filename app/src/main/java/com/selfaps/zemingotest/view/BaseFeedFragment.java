package com.selfaps.zemingotest.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.selfaps.zemingotest.R;
import com.selfaps.zemingotest.model.RvAdapter;
import com.selfaps.zemingotest.presenter.Presenter;
import com.selfaps.zemingotest.utils.Constants;



public class BaseFeedFragment extends Fragment implements com.selfaps.zemingotest.view.View{
    private Presenter presenter;
    private RvAdapter adapter;
    private RecyclerView rv;
    private  String[] categories = new String[]{Constants.CATEGORY_CARS[0]};



    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {

        presenter = new Presenter();
        presenter.attachView(this);

        android.view.View rootView = inflater.inflate(R.layout.fragment_cars, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv_news);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);



        adapter = new RvAdapter(presenter, categories);

        rv.setAdapter(adapter);
        presenter.loadFeed(new String[][]{Constants.CATEGORY_CARS});

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(presenter == null)
            presenter = new Presenter();

        presenter.attachView(this);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
        adapter = null;
    }


    @Override
    public void showList() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });

    }

    public String[] getCategories(){
        return categories;
    }

    @Override
    public void showProgress() {
        assert ((SecondActivity)getActivity()) != null;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),"showProgress",Toast.LENGTH_SHORT).show();
                ((SecondActivity)getActivity()).showProgress();
            }
        });

    }

    @Override
    public void hideProgress() {
        assert ((SecondActivity)getActivity()) != null;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),"hideProgress",Toast.LENGTH_SHORT).show();
                ((SecondActivity)getActivity()).hideProgress();
            }
        });
    }

    @Override
    public void setLastVisited(String label) {
        assert ((SecondActivity)getActivity()) != null;
        ((SecondActivity)getActivity()).setLastVisited(label);
    }


}
