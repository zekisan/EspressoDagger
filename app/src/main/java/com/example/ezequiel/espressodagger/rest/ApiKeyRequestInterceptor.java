package com.example.ezequiel.espressodagger.rest;

import retrofit.RequestInterceptor;

public class ApiKeyRequestInterceptor implements RequestInterceptor {

    private static final String QUERY_APP_ID = "APPID";

    private final String apiKey;

    public ApiKeyRequestInterceptor(String apiKey){
        this.apiKey = apiKey;
    }

    @Override
    public void intercept(RequestFacade request){
        request.addQueryParam(QUERY_APP_ID, apiKey);
    }

}
