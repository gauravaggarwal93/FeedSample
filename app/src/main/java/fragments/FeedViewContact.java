package fragments;

import com.example.guest.myapplication.FeedItem;

import java.util.List;

/**
 * Created by Gaurav on 10/8/17.
 */

public interface FeedViewContact {

    interface View {
        void onItemLoaded(List<FeedItem> feedItemList);
    }

    interface Presenter {
        void start();

        void stop();
    }
}
