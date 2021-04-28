package com.example.vpncheck;

import android.content.Context;

public class UtilsApi {

    Context context;
    public UtilsApi(Context context) {
        this.context = context;
    }
    public static BaseApiService getAPIService(){
//        return RetrofitClient.getClient("https://api.tazkereh.app/").create(BaseApiService.class);
        return RetrofitClient.getClient("https://www.shahrekhabar.com/").create(BaseApiService.class);
    }
    public static BaseApiService getAPIServiceMedia(){
//        return RetrofitClient.getClient("https://api.tazkereh.app/").create(BaseApiService.class);
        return RetrofitClient.getClient("https://www.shahrekhabar.com/").create(BaseApiService.class);
    }
}
