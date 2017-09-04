package dto;

import org.json.JSONObject;
import constants.ApiConstants;

/**
 * Created by Gaurav on 9/3/17.
 */

//Singleton class - So that these codes can be used everywhere in the code

public class Appendix {

    private Airlines airlines;

    private Airports airports;

    private Providers providers;

    private static Appendix sInstance;

    public static synchronized Appendix getInstance() {
        if (sInstance == null) {
            synchronized (Appendix.class) {
                if (sInstance == null) {
                    sInstance = new Appendix();
                }
            }
        }
        return sInstance;
    }

    private Appendix() {

    }

    public Appendix fromJsonObject(JSONObject jsonObject) {
        airlines = new Airlines(jsonObject.optJSONObject(ApiConstants.AIRLINES));
        airports = new Airports(jsonObject.optJSONObject(ApiConstants.AIRPORTS));
        providers = new Providers(jsonObject.optJSONObject(ApiConstants.PROVIDERS));
        return this;
    }

    public Airlines getAirlines() {
        return airlines;
    }

    public Airports getAirports() {
        return airports;
    }

    public Providers getProviders() {
        return providers;
    }

}
