package dto;

import org.json.JSONObject;

/**
 * Created by Gaurav on 9/2/17.
 */

//Creating a separate class if we require all the location details in future

public class Location {

    private String city;

    public Location(){
    }

    Location(JSONObject jsonObject) {
        city = jsonObject.optString("city");
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
