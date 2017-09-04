package utils;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import networking.JSONRequest;
import networking.VolleyManager;

/**
 * Created by Gaurav on 9/2/17.
 */

public class ApiUtils {

    private static final String LOG_TAG = "API_UTILS";

    private static String SEARCH_API = "http://www.mocky.io/v2/5979c6731100001e039edcb3";

    public static void getSearchResult(final ApiResponseListener apiResponseListener) {
        String url = SEARCH_API;
        Log.d(LOG_TAG, "URL : " + url);

        JSONRequest jsonRequest = new JSONRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(LOG_TAG, "onsuccess : " + response.toString());
                apiResponseListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "onErrorResponse : " + error);
                apiResponseListener.onFailure(error);
            }
        });

        VolleyManager.getInstance().getRequestQueue().add(jsonRequest);
    }

    public interface ApiResponseListener {

        void onSuccess(JSONObject jsonObject);

        void onFailure(VolleyError volleyError);
    }
}
