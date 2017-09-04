package dto;

import org.json.JSONObject;

import java.util.HashMap;

import constants.ApiConstants;

/**
 * Created by Gaurav on 9/3/17.
 */

public class Airports {

    private HashMap<String, String> airportsList;

    public Airports(JSONObject jsonObject) {
        airportsList = new HashMap<>();
        airportsList.put(ApiConstants.DELHI, jsonObject.optString(ApiConstants.DELHI));
        airportsList.put(ApiConstants.BOMBAY, jsonObject.optString(ApiConstants.BOMBAY));
    }

    public HashMap<String, String> getAirportsList() {
        return airportsList;
    }

    public String getAirportName(String code) {
        return airportsList.get(code);
    }
}
