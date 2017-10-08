package fragments;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import com.example.guest.myapplication.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dto.Feed;
import utils.Utils;

/**
 * Created by Gaurav on 10/8/17.
 */

public class FeedListPresenter implements FeedViewContact.Presenter {

    private FeedViewContact.View mView;

    private List<FeedItem> mFeedList;

    FeedListPresenter(FeedViewContact.View view) {
        mFeedList = new ArrayList<>();
        mView = view;
        getFeeds();
    }

    private FragmentActivity getActivity() {
        return ((FeedListFragment) mView).getActivity();
    }

    @Override
    public void start() {
        mView.onItemLoaded(mFeedList);
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("Feed.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void getFeeds() {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    JSONArray arr = new JSONArray(loadJSONFromAsset());

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        Feed feed = new Feed(obj);
                        FeedItem feedItem = null;
                        if (feed.getTitle().equalsIgnoreCase(FeedItem.FeedType.PEOPLE.toString())) {
                            feedItem = new FeedItem<>(feed, FeedItem.FeedType.PEOPLE);
                        } else if (feed.getTitle().equalsIgnoreCase(FeedItem.FeedType.PLACE.toString())) {
                            feedItem = new FeedItem<>(feed, FeedItem.FeedType.PLACE);
                        } else {
                            feedItem = new FeedItem<>(feed, FeedItem.FeedType.QUOTE);
                        }
                        mFeedList.add(feedItem);
                    }
                    Utils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.onItemLoaded(mFeedList);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        task.execute();
    }


    @Override
    public void stop() {

    }
}
