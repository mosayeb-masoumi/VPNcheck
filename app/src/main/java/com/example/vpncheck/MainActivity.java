package com.example.vpncheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {


    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check();
            }
        });


    }

    private void check() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String urlip = "http://checkip.amazonaws.com/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlip, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //   txtIP.setText(response);
//
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.shahrekhabar.com/"));
//                startActivity(browserIntent);
                checkConnection(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("TAG", "onErrorResponse: ");
            }
        });

        queue.add(stringRequest);
    }

    public void checkConnection(String response){

        String myUrl = "http://www.geoplugin.net/json.gp?ip="+response ;
        BaseApiService sev = UtilsApi.getAPIServiceMedia();
        sev.getNextEvent(myUrl).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JSONObject msg;
                    JSONObject jsonObject = null;
                    try {

                        jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                    try {

                        String   mssg= jsonObject.getString("geoplugin_region");
                        String   mssg2= jsonObject.getString("geoplugin_countryName");

                        String region = mssg;
                        String countryName = mssg2;

                        Toast.makeText(MainActivity.this, ""+countryName, Toast.LENGTH_SHORT).show();
                        Log.i("TAG", "onResponse: ");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.i("TAG", "onErrorResponse: ");

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.i("TAG", "onErrorResponse: ");
            }
        });
    }
}