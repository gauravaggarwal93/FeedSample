package dto;

import org.json.JSONObject;

import java.util.HashMap;

import constants.ApiConstants;

/**
 * Created by Gaurav on 9/3/17.
 */

public class Providers {
    private HashMap<String, String> providersList;

    public Providers(JSONObject jsonObject) {
        providersList = new HashMap<>();
        providersList.put(ApiConstants.MAKE_MY_TRIP, jsonObject.optString(ApiConstants.MAKE_MY_TRIP));
        providersList.put(ApiConstants.MUSAFIR, jsonObject.optString(ApiConstants.MUSAFIR));
        providersList.put(ApiConstants.YATRA, jsonObject.optString(ApiConstants.YATRA));
        providersList.put(ApiConstants.CLEAR_TRIP, jsonObject.optString(ApiConstants.CLEAR_TRIP));
    }

    public HashMap<String, String> getProvidersList() {
        return providersList;
    }

    public String getProviderName(String code) {
        return providersList.get(code);
    }

}
