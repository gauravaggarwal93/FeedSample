package com.example.guest.myapplication;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Gaurav on 10/7/17.
 */

public abstract class FeedViewholder<T> extends RecyclerView.ViewHolder {

    public FeedViewholder(View itemView) {
        super(itemView);
    }

    public abstract void bindView(T data);
}
