package dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import constants.ApiConstants;
import utils.Utils;

/**
 * Created by Gaurav on 9/3/17.
 */

public class Flight {

    private String originalCode;
    private String destinationCode;
    private String departureTime;
    private String arrivalTime;
    private String airlineCode;
    private String airlineClass;
    private ArrayList<Fare> fareList;
    private ArrayList<Flight> flightList;

    public Flight() {

    }

    private Flight(JSONObject jsonObject) {
        originalCode = jsonObject.optString(ApiConstants.ORIGIN_CODE);
        destinationCode = jsonObject.optString(ApiConstants.DESTINATION_CODE);
        departureTime = jsonObject.optString(ApiConstants.DEPARTURE_TIME);
        arrivalTime = jsonObject.optString(ApiConstants.ARRIVAL_TIME);
        fareList = new Fare().fromJsonArray(jsonObject.optJSONArray(ApiConstants.FARES));
        airlineCode = jsonObject.optString(ApiConstants.AIRLINE_CODE);
        airlineClass = jsonObject.optString(ApiConstants.CLASS);
    }

    public ArrayList<Flight> fromJsonArray(JSONArray flights) {
        flightList = new ArrayList<>();
        if (!Utils.isEmpty(flights)) {
            for (int i = 0; i < flights.length(); i++) {
                try {
                    flightList.add(new Flight(flights.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return flightList;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public String getAirlineClass() {
        return airlineClass;
    }

    public ArrayList<Fare> getFareList() {
        return fareList;
    }

    public ArrayList<Flight> getFlightList() {
        return flightList;
    }

}
