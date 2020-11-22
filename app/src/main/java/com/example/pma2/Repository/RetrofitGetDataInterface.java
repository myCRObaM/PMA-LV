package com.example.pma2.Repository;

import com.example.pma2.Model.SubjectModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitGetDataInterface {
    @GET("/posts")
    Call<List<SubjectModel>> getSubjects();
}
