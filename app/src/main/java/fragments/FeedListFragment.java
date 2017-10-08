package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.myapplication.FeedAdapter;
import com.example.guest.myapplication.FeedItem;
import com.example.guest.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav on 10/7/17.
 */

public class FeedListFragment extends BaseFragment implements FeedViewContact.View {

    private static final String LOG_TAG = "FeedListFragment";

    private RecyclerView mRecyclerView;

    private FeedAdapter mFeedAdapter;

    private FeedListPresenter mFeedListPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFeedListPresenter = new FeedListPresenter(this);

//        Handling Orientation changes. This also avoids memory leaks due to asynctask
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        findViews(view);
        bindViews();

        return view;
    }

    private void findViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
    }

    private void bindViews() {
        mFeedAdapter = new FeedAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mFeedAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mFeedListPresenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFeedListPresenter.stop();
    }

    @Override
    public String getFragmentTag() {
        return FeedListFragment.class.getName();
    }


    @Override
    public void onItemLoaded(List<FeedItem> feedItemList) {
        if (feedItemList != null) {
            mFeedAdapter.setFeedItems(feedItemList);
            mFeedAdapter.notifyDataSetChanged();
        }
    }
}
