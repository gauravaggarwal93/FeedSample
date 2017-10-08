package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.guest.myapplication.FeedItem;
import com.example.guest.myapplication.R;

import java.util.Date;

import constants.ApiConstants;
import dto.Feed;
import networking.VolleyManager;
import utils.Utils;

/**
 * Created by Gaurav on 10/7/17.
 * <p>
 * This fragment is used for showing feed detail view.
 */

public class FeedDetailFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvDescription;
    private LinearLayout llContainer;
    private FeedItem<Feed> feedItem;
    private TextView tvText;
    private TextView tvLike;
    private TextView tvUnlike;
    private TextView tvName;
    private TextView tvDate;
    private NetworkImageView ivImage;

    public static FeedDetailFragment getInstance(Bundle bundle) {
        FeedDetailFragment FeedDetailFragment = new FeedDetailFragment();
        FeedDetailFragment.setArguments(bundle);
        return FeedDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ApiConstants.FEED_ITEM)) {
            feedItem = getArguments().getParcelable(ApiConstants.FEED_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_detail, container, false);
        findViews(view);
        addView();
        bindViews(view);
        return view;
    }

    private void findViews(View view) {
        tvDescription = (TextView) view.findViewById(R.id.tv_description_msg);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        llContainer = (LinearLayout) view.findViewById(R.id.ll_container);
    }

    private void bindViews(View view) {

        tvLike = (TextView) view.findViewById(R.id.tv_like);
        tvUnlike = (TextView) view.findViewById(R.id.tv_unlike);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        Date date = Utils.getDate(feedItem.getData().getTime());
        tvDate.setText(date.getDate() + " " + "August" + " " + "2017");
        tvLike.setOnClickListener(this);
        tvUnlike.setOnClickListener(this);

        if (feedItem != null && feedItem.getData() != null) {
            tvName.setText(feedItem.getData().getName());
            tvDescription.setText(feedItem.getData().getDescription());
            setLikeButton(feedItem.getData().isLike());
            switch (feedItem.getFeedType()) {
                case PEOPLE:
                    tvText = (TextView) view.findViewById(R.id.tv_text);
                    ivImage = (NetworkImageView) view.findViewById(R.id.iv_image);
                    tvText.setText(feedItem.getData().getText());
                    ivImage.setImageUrl(feedItem.getData().getImageUrl(), VolleyManager.getInstance().getImageLoader());
                    break;
                case QUOTE:
                    tvText = (TextView) view.findViewById(R.id.tv_text);
                    tvText.setText(feedItem.getData().getText());
                    break;
                case PLACE:
                    ivImage = (NetworkImageView) view.findViewById(R.id.iv_image);
                    ivImage.setImageUrl(feedItem.getData().getImageUrl(), VolleyManager.getInstance().getImageLoader());
                    break;
            }
        }
    }

    private void addView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null;
        switch (feedItem.getFeedType()) {
            case PEOPLE:
                view = inflater.inflate(R.layout.item_feed_people, null);
                break;
            case QUOTE:
                view = inflater.inflate(R.layout.item_feed_quote, null);
                break;
            case PLACE:
                view = inflater.inflate(R.layout.item_feed_place, null);
                break;
        }
        if (view != null) {
            llContainer.addView(view, 1);
        }
    }

    @Override
    public String getFragmentTag() {
        return FeedDetailFragment.class.getName();
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
                feedItem.getData().setLike(true);
                setLikeButton(true);
                break;
            case R.id.tv_unlike:
                feedItem.getData().setLike(false);
                setLikeButton(false);
                break;
        }
    }
}
