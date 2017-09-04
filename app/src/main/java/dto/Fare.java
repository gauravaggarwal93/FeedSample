package dto;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import constants.ApiConstants;
import utils.Utils;

/**
 * Created by Gaurav on 9/3/17.
 */

public class Fare implements Parcelable {
    private String providerId;
    private String fare;
    private ArrayList<Fare> faresList;

    public Fare() {

    }

    private Fare(JSONObject jsonObject) {
        providerId = jsonObject.optString(ApiConstants.PROVIDER_ID);
        fare = jsonObject.optString(ApiConstants.FARE);
    }

    protected Fare(Parcel in) {
        providerId = in.readString();
        fare = in.readString();
        faresList = in.createTypedArrayList(Fare.CREATOR);
    }

    public static final Creator<Fare> CREATOR = new Creator<Fare>() {
        @Override
        public Fare createFromParcel(Parcel in) {
            return new Fare(in);
        }

        @Override
        public Fare[] newArray(int size) {
            return new Fare[size];
        }
    };

    public ArrayList<Fare> fromJsonArray(JSONArray fares) {
        faresList = new ArrayList<>();
        if (!Utils.isEmpty(fares)) {
            for (int i = 0; i < fares.length(); i++) {
                try {
                    faresList.add(new Fare(fares.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return faresList;
    }

    public ArrayList<Fare> getFaresList() {
        return faresList;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getFare() {
        return fare;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(providerId);
        dest.writeString(fare);
        dest.writeTypedList(faresList);
    }
}