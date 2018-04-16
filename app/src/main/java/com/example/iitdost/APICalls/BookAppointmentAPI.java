package com.example.iitdost.APICalls;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iitdost.BookAppointment.SelectDepartmentFragment;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * Created by sushant on 16/4/18.
 */

public class BookAppointmentAPI extends Application{
    public static Context context;
    private APIConfig apiconfig;
    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public void getDepartmentList(final SelectDepartmentFragment fragment, final List<String> academics,
                                  final List<String> administrative, final List<String> others)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiconfig.URL_UPDATE_DEPARTMENT_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)   {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, response);
                        //Replace lists with newly received ones
                        try {
                            JSONArray responseJSONarray = new JSONArray(response);
                            JSONObject responseJSONobject = responseJSONarray.getJSONObject(0);
                            JSONObject departments=new JSONObject((String)responseJSONobject.get("Departments"));
                            JSONArray acadJSON=departments.getJSONArray("Academics"),
                                    adminJSON=departments.getJSONArray("Administrative"),othersJSON=departments.getJSONArray("Others");
                            academics.clear();
                            for(int i=0;i<acadJSON.length();i++){
                                academics.add(acadJSON.getString(i));
                            }
                            administrative.clear();
                            for(int i=0;i<adminJSON.length();i++){
                                administrative.add(adminJSON.getString(i));
                            }
                            others.clear();
                            for(int i=0;i<othersJSON.length();i++){
                                others.add(othersJSON.getString(i));
                            }

                        }
                        catch(JSONException e){
                            Log.e("JSONException",e.toString());
                        }
                        fragment.notifyAdapters(); //update all adapters
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "could not retreive data");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
