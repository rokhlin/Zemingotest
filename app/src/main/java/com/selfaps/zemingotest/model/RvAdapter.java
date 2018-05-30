package com.selfaps.zemingotest.model;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selfaps.zemingotest.R;
import com.selfaps.zemingotest.presenter.Presenter;
import com.selfaps.zemingotest.utils.Constants;
import com.selfaps.zemingotest.view.ThirdActivity;


public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RssHolder> {
    private Presenter presenter;
    private String[] category;

    public RvAdapter(Presenter presenter, String[] category) {
        this.presenter = presenter;
        this.category = category;
    }

    @NonNull
    @Override
    public RssHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);
        return new RssHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RssHolder holder,int position) {
        final RssItem item = getItem(position);

        holder.content.setText(item.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.markPageVisited(item.title);
                Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                intent.putExtra(Constants.EXTRAS_LINK, item.link);
                v.getContext().startActivity(intent);
            }
        });
    }



    private RssItem getItem(int position) {
        return presenter.getFeed(category).get(position);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public class RssHolder extends RecyclerView.ViewHolder{
        TextView content;

        public RssHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.tv_content);
        }
    }

}
