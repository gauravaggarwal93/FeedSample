package dto;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import constants.ApiConstants;

/**
 * Created by Gaurav on 10/7/17.
 */

public class Feed implements Parcelable {

    private String name;
    private String imageUrl;
    private String title;
    private String text;
    private Long time;
    private String description;
    private boolean like;

    public Feed(JSONObject jsonObject) {
        name = jsonObject.optString(ApiConstants.NAME);
        imageUrl = jsonObject.optString(ApiConstants.IMAGE_URL);
        title = jsonObject.optString(ApiConstants.TITLE);
        text = jsonObject.optString(ApiConstants.TEXT);
        time = jsonObject.optLong(ApiConstants.TIME);
        description = jsonObject.optString(ApiConstants.DESCRIPTION);
    }

    protected Feed(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        title = in.readString();
        text = in.readString();
        description = in.readString();
        like = in.readByte() != 0;
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Long getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeString(title);
        dest.writeString(text);
        dest.writeString(description);
        dest.writeByte((byte) (like ? 1 : 0));
    }
}
