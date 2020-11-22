package com.example.pma2.Repository;

import com.example.pma2.Classes.SpinnerSubjectClass;
import com.example.pma2.Interfaces.GetDataError;
import com.example.pma2.Interfaces.SpinnerDataReady;
import com.example.pma2.Model.SubjectModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitRepository {

    SpinnerDataReady spinnerDataReady;
    GetDataError getDataError;


    public void getSubject()
    {
        RetrofitManager retrofitManager = RetrofitManager.getInstance();
        Retrofit retrofit = retrofitManager.getRetrofit();

        RetrofitGetDataInterface getInterface = retrofit.create(RetrofitGetDataInterface.class);
        Call<List<SubjectModel>> call = getInterface.getSubjects();
        call.enqueue(new Callback<List<SubjectModel>>() {
            @Override
            public void onResponse(Call<List<SubjectModel>> call, Response<List<SubjectModel>> response) {
                ArrayList<SpinnerSubjectClass> responseData = new ArrayList<>();
                if (!response.isSuccessful())
                {
                    getDataError.errorPopUp(Integer.toString(response.code()));
                }
                else
                {
                    List<SubjectModel> subjectResponse = response.body();

                    for (SubjectModel subject: subjectResponse)
                    {
                        responseData.add(new SpinnerSubjectClass(subject.getId(), subject.getTitle()));
                    }
                    spinnerDataReady.subjectDataReady(responseData);
                }
            }

            @Override
            public void onFailure(Call<List<SubjectModel>> call, Throwable t) {
                getDataError.errorPopUp("Error: " + t.getMessage());
            }
        });
    }

    public RetrofitRepository(SpinnerDataReady spinnerDataReady, GetDataError dataError) {
        this.spinnerDataReady = spinnerDataReady;
        this.getDataError = dataError;
    }
}
