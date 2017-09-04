package dto;

import org.json.JSONObject;

import java.util.HashMap;

import constants.ApiConstants;

/**
 * Created by Gaurav on 9/3/17.
 */

public class Airlines {

    private HashMap<String, String> airlinesList;

    public Airlines(JSONObject jsonObject) {
        airlinesList = new HashMap<>();
        airlinesList.put(ApiConstants.AIR_INDIA, jsonObject.optString(ApiConstants.AIR_INDIA));
        airlinesList.put(ApiConstants.SPICEJET, jsonObject.optString(ApiConstants.SPICEJET));
        airlinesList.put(ApiConstants.GO_AIR, jsonObject.optString(ApiConstants.GO_AIR));
        airlinesList.put(ApiConstants.INDIGO, jsonObject.optString(ApiConstants.INDIGO));
        airlinesList.put(ApiConstants.JET_AIRWAYS, jsonObject.optString(ApiConstants.JET_AIRWAYS));
    }

    public HashMap<String, String> getAirlinesList() {
        return airlinesList;
    }

    public String getAirlineName(String code) {
        return airlinesList.get(code);
    }
}
