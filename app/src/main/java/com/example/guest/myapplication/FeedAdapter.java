package com.example.guest.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dto.Feed;

/**
 * Created by Gaurav on 10/7/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedViewholder> {

    private List<FeedItem> mFeedItems = new ArrayList<>();

    public void setFeedItems(List<FeedItem> feedItems) {
        mFeedItems = feedItems;
    }

    @Override
    public FeedViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == FeedItem.FeedType.PEOPLE.ordinal()) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_people, parent, false);
            return new PeopleViewholder(inflate);

        } else if (viewType == FeedItem.FeedType.PLACE.ordinal()) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_place, parent, false);
            return new PlaceViewholder(inflate);

        } else if (viewType == FeedItem.FeedType.QUOTE.ordinal()) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_quote, parent, false);
            return new QuoteViewholder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(FeedViewholder holder, int position) {
        if (getItemViewType(position) == FeedItem.FeedType.PEOPLE.ordinal()) {
            PeopleViewholder peopleViewholder = (PeopleViewholder) holder;
            peopleViewholder.bindView(mFeedItems.get(position));
        } else if (getItemViewType(position) == FeedItem.FeedType.PLACE.ordinal()) {
            PlaceViewholder placeViewholder = (PlaceViewholder) holder;
            placeViewholder.bindView(mFeedItems.get(position));
        } else if (getItemViewType(position) == FeedItem.FeedType.QUOTE.ordinal()) {
            QuoteViewholder quoteViewholder = (QuoteViewholder) holder;
            quoteViewholder.bindView(mFeedItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mFeedItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mFeedItems.get(position).getFeedType().ordinal();
    }
}
