package com.example.vpncheck;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BaseApiService {

    @GET
    Call<JsonObject> getNextEvent(@Url String name);
}
