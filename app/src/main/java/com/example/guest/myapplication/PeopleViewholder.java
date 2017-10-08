package com.example.guest.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import constants.ApiConstants;
import dto.Feed;
import fragments.FeedDetailFragment;
import networking.VolleyManager;

/**
 * Created by Gaurav on 10/7/17.
 */

public class PeopleViewholder extends FeedViewholder<FeedItem> implements View.OnClickListener {

    private TextView tvText;
    private TextView tvLike;
    private TextView tvUnlike;
    private TextView tvName;
    private NetworkImageView ivImage;
    private FeedItem feedItem;
    private LinearLayout llContainer;

    PeopleViewholder(View itemView) {
        super(itemView);
        tvText = (TextView) itemView.findViewById(R.id.tv_text);
        tvLike = (TextView) itemView.findViewById(R.id.tv_like);
        tvUnlike = (TextView) itemView.findViewById(R.id.tv_unlike);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        ivImage = (NetworkImageView) itemView.findViewById(R.id.iv_image);
        llContainer = (LinearLayout) itemView.findViewById(R.id.ll_container);
    }

    @Override
    public void bindView(FeedItem data) {
        feedItem = data;
        Feed feed = (Feed)data.getData();
        tvText.setText(feed.getText());
        tvName.setText(feed.getName());
        ivImage.setDefaultImageResId(R.drawable.ic_image_black_24dp);
        ivImage.setErrorImageResId(R.drawable.ic_image_black_24dp);
        ivImage.setImageUrl(feed.getImageUrl(), VolleyManager.getInstance().getImageLoader());
        setLikeButton(feed.isLike());
        tvLike.setOnClickListener(this);
        tvUnlike.setOnClickListener(this);
        llContainer.setOnClickListener(this);
    }

    private void setLikeButton(boolean show) {
        if (!show) {
            tvLike.setVisibility(View.VISIBLE);
            tvUnlike.setVisibility(View.GONE);
        } else {
            tvLike.setVisibility(View.GONE);
            tvUnlike.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_like:
                ((Feed)feedItem.getData()).setLike(true);
                setLikeButton(true);
                break;
            case R.id.tv_unlike:
                ((Feed)feedItem.getData()).setLike(false);
                setLikeButton(false);
                break;
            case R.id.ll_container:
                Bundle bundle = new Bundle();
                bundle.putParcelable(ApiConstants.FEED_ITEM, feedItem);
                FeedDetailFragment feedDetailFragment = FeedDetailFragment.getInstance(bundle);
                ((MainActivity)v.getContext()).startFragmentTransition(feedDetailFragment);
                break;
        }
    }
}
