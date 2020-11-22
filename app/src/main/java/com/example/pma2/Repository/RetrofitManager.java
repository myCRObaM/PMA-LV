package com.example.pma2.Repository;

import com.example.pma2.Classes.SpinnerSubjectClass;
import com.example.pma2.Interfaces.SpinnerDataReady;
import com.example.pma2.Model.SubjectModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static RetrofitManager oAdapter;
    private Retrofit retrofit;
    public SpinnerDataReady spinnerDataReady;


    public static RetrofitManager getInstance()
    {
        if (oAdapter == null)
        {
            oAdapter = new RetrofitManager();
        }
        return oAdapter;
    }

    public RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getLogger(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return  httpClient.addInterceptor(logging).build();
    }


    public Retrofit getRetrofit()
    {
        return this.retrofit;
    }
}
