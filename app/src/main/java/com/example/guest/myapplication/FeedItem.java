package com.example.guest.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by Gaurav on 10/7/17.
 */

public class FeedItem<T> implements Parcelable {

    public enum FeedType {

        PEOPLE, QUOTE, PLACE;
    }
    private T mData;

    private FeedType mFeedType;

    public FeedItem(@NonNull T data, FeedType type) {
        this.mData = data;
        this.mFeedType = type;
    }

    public final T getData() {
        return mData;
    }

    public final FeedType getFeedType() {
        return mFeedType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedItem<?> that = (FeedItem<?>) o;

        if (mData != null ? !mData.equals(that.mData) : that.mData != null) return false;
        return mFeedType == that.mFeedType;

    }

    protected FeedItem(Parcel in) {
    }

    public static final Creator<FeedItem> CREATOR = new Creator<FeedItem>() {
        @Override
        public FeedItem createFromParcel(Parcel in) {
            return new FeedItem(in);
        }

        @Override
        public FeedItem[] newArray(int size) {
            return new FeedItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }


}
